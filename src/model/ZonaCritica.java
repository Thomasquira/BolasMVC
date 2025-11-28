/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.awt.Dimension;
import java.awt.Rectangle;

/**
 *
 * @author thomas
 */
public class ZonaCritica {

    private Model model;
    private boolean taOcupao = false;
    private Ball bolaDentro;
    private int tiempoMaximoQuedarse = 10000; //en mliisegundos
    private Rectangle rectangle;
    private Dimension posicionInicial, dimensiones;

    public ZonaCritica(Model model) {
        this.model = model;
        this.rectangle = new Rectangle(300, 200, 150, 100);
    }

    public boolean isIn(int x, int y) {
        return rectangle.contains(x, y);
    }

    public synchronized boolean goIn(Ball ball) {

        while (taOcupao && bolaDentro != ball) {
            return false;
        }

        if (bolaDentro == ball) {
            return true;
        }

        taOcupao = true;
        bolaDentro = ball;

        int velx = ball.getFisicas().getSpeedX();
        int vely = ball.getFisicas().getSpeedY();

        ball.getFisicas().setSpeedX(0);
        ball.getFisicas().setSpeedY(0);

        //Pa que permanezca ahí dentro un rato
        new Thread(() -> {
            try {
                Thread.sleep(tiempoMaximoQuedarse);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }

            // Liberar bola
            ball.getFisicas().setSpeedX(velx);
            ball.getFisicas().setSpeedY(vely);
            fuirse(ball);
        }).start();
        return true;
    }

    public synchronized void fuirse(Ball ball) {
        if (bolaDentro == ball) {
            taOcupao = false;
            bolaDentro = null;
            notifyAll();
        }
    }

    public Rectangle getRectangle() {
        return rectangle;
    }

    public Model getModel() {
        return model;
    }

    public void setModel(Model model) {
        this.model = model;
    }

}
