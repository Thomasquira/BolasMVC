/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;

/**
 *
 * @author thomas
 */
public class Controlable extends Ball {
    private BufferedImage naveImg;
    
    public Controlable(Model model, int x, int y) {
        super(model, x, y, 40, Color.RED); 
        
        try {
            File imgFile = new File("src/nave.png");
            if (imgFile.exists()) {
                naveImg = ImageIO.read(imgFile);
                System.out.println("Imagen de nave cargada correctamente");
            } else {
                System.out.println("Advertencia: No se encontró src/nave.png");
   
                naveImg = new BufferedImage(40, 40, BufferedImage.TYPE_INT_ARGB);
                Graphics2D g2d = naveImg.createGraphics();
                g2d.setColor(Color.RED);
                g2d.fillRect(0, 0, 40, 40);
                g2d.setColor(Color.WHITE);
                g2d.drawString("N", 15, 25);
                g2d.dispose();
            }
        } catch (Exception e) {
            System.err.println("Error cargando imagen de nave: " + e.getMessage());
            naveImg = new BufferedImage(40, 40, BufferedImage.TYPE_INT_ARGB);
            Graphics2D g2d = naveImg.createGraphics();
            g2d.setColor(Color.GREEN);
            g2d.fillOval(0, 0, 40, 40);
            g2d.dispose();
        }
        
        this.fisicas.setSpeedX(0);
        this.fisicas.setSpeedY(0);
        this.speedX = 0;
        this.speedY = 0;
    }
    
    @Override
    public void run() {
        while (model.getState() != Model.STATE.STOP) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                break;
            }
        }
    }
    
    @Override
    public void render(Graphics2D g) {
        if (naveImg != null) {
            g.drawImage(naveImg, x, y, DIAMETER, DIAMETER, null);
        } else {
            g.setColor(Color.RED);
            g.fillRect(x, y, DIAMETER, DIAMETER);
            g.setColor(Color.WHITE);
            g.drawString("N", x + 15, y + 25);
        }
    }
    
    public BufferedImage getImagen() {
        return naveImg;
    }
    
    public void moverIzq() {
        if (x > 0) {
            x = Math.max(0, x - 10);
        }
    }
    
    public void moverDer() {
        int maxX = model.getViewerWidth() - DIAMETER;
        if (x < maxX) {
            x = Math.min(maxX, x + 10);
        }
    }
    
    public void moverArr() {
        if (y > 0) {
            y = Math.max(0, y - 10);
        }
    }
    
    public void moverAba() {
        int maxY = model.getViewerHeight() - DIAMETER;
        if (y < maxY) {
            y = Math.min(maxY, y + 10);
        }
    }
}
