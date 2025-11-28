/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package view;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;
import javax.imageio.ImageIO;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import model.Ball;
import model.Controlable;
import model.ZonaCritica;

/**
 * @author thomas
 */
public class Viewer extends JPanel implements Runnable {

    private final long lastTime = System.nanoTime();

    private long fpsTimer = System.currentTimeMillis(), tiempoRender;
    private int frames = 0, framesActuales = 0;
    private final Thread thread;
    private final View view;
    private final Set<Integer> teclasActivas = new HashSet<>();
    private Image backgroundImage;

    public Viewer(View view) {
        this.view = view;
        setBackground(Color.WHITE);
        thread = new Thread(this);

        try {
            backgroundImage = ImageIO.read(new File("src/espacio.jpg"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        setFocusable(true);
        requestFocusInWindow();
        addKeyListener(new KeyAdapter() {

            @Override
            public void keyPressed(KeyEvent e) {
                teclasActivas.add(e.getKeyCode());
            }

            @Override
            public void keyReleased(KeyEvent e) {
                teclasActivas.remove(e.getKeyCode());
            }
        });

        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                requestFocusInWindow();
            }
        });

    }

    @Override
    public void run() {
        while (true) {
            long start = System.nanoTime();
            Controlable p = view.getBolaControlable();
            if (p != null) {
                moverControlable();
            }

            repaint();
            tiempoRender = (System.nanoTime() - start) / 1_000_000;

            frames++;
            long now = System.currentTimeMillis();
            if (now - fpsTimer >= 1000) {
                framesActuales = frames;
                frames = 0;
                fpsTimer = now;

                SwingUtilities.invokeLater(() -> {
                    view.getDataPanel().actualizar("FPS", String.valueOf(framesActuales));
                    view.getDataPanel().actualizar("Render Time", tiempoRender + " ms");
                    view.getDataPanel().actualizar("Contador de Bolas", String.valueOf(view.getAllBalls().size()));
                });
            }

            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }

    private void moverControlable() {

        if (teclasActivas.contains(KeyEvent.VK_LEFT)) {
            view.moverBolaIzq();
        }
        if (teclasActivas.contains(KeyEvent.VK_RIGHT)) {
            view.moverBolaDer();
        }
        if (teclasActivas.contains(KeyEvent.VK_UP)) {
            view.moverBolaArr();
        }
        if (teclasActivas.contains(KeyEvent.VK_DOWN)) {
            view.moverBolaAba();
        }
    }

    public void startViewer() {
        thread.start();
    }

    public Thread getThread() {
        return this.thread;
    }

    public void paintBall(Ball ball, Graphics2D g) {
        int diameter = ball.getDIAMETER();

        if (ball instanceof Controlable controlable) {
            BufferedImage img = controlable.getImagen();
            if (img != null) {
                g.drawImage(img, controlable.getX(), controlable.getY(), diameter, diameter, null);
                return;
            }
        }

        g.setColor(ball.getCOLOR());
        g.fillOval(ball.getX(), ball.getY(), diameter, diameter);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        if (backgroundImage != null) {
            g2.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
        }

//        ZonaCritica zona = view.getController().getModel().getZonaCritica();
//        Rectangle rect = zona.getRectangle();

        g2.setColor(Color.LIGHT_GRAY);
//        g2.fill(rect);
        g2.setColor(Color.BLACK);
//        g2.draw(rect);

        for (Ball ball : view.getAllBalls()) {
            paintBall(ball, g2);
        }
    }

}
