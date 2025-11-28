/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.awt.*;


/**
 *
 * @author thomas
 */
public class Ball implements Runnable {

    Model model;
    int x, y, speedX, speedY;
    final int DIAMETER;
    final Color COLOR;
    Thread thread;
    final BasicPhysicsEngine fisicas;

    public Ball(Model model, int minimoTam, int maximoTam, int minimoVel, int maximoVel) {
        this.model = model;
        this.x = (int) (Math.random() * model.getViewerWidth());
        this.y = (int) (Math.random() * model.getViewerHeight());
        this.DIAMETER = minimoTam + (int) (Math.random() * (maximoTam - minimoTam + 1));
        this.COLOR = new java.awt.Color(
                (int) (Math.random() * 255),
                (int) (Math.random() * 255),
                (int) (Math.random() * 255)
        );

        this.fisicas = new BasicPhysicsEngine(
                minimoVel + (int) (Math.random() * (maximoVel - minimoVel + 1)),
                minimoVel + (int) (Math.random() * (maximoVel - minimoVel + 1))
        );


        this.thread = new Thread(this);
        this.thread.start();

    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getDIAMETER() {
        return this.DIAMETER;
    }

    public Color getCOLOR() {
        return COLOR;
    }

    @Override
   public void run() {
        while (model.getState() != Model.STATE.STOP) {
            if (model.getState() == Model.STATE.PLAY) {
                int[] next = fisicas.calcularPosicion(x, y);

             
                BallEvent event = model.getEventDetector().detect(this, next[0], next[1]);

         
                if (event != BallEvent.NONE) {
                    model.getController().manejarEventoBola(this, event);
                }

                
                x = next[0];
                y = next[1];
            }

            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }

    public void activation() {

        this.thread.start();
    }


    public BasicPhysicsEngine getFisicas() {
        return fisicas;
    }

    
     public boolean isColliding(Ball other) {
        double dx = (x + DIAMETER / 2.0) - (other.x + other.DIAMETER / 2.0);
        double dy = (y + DIAMETER / 2.0) - (other.y + other.DIAMETER / 2.0);
        double distance = Math.sqrt(dx * dx + dy * dy);
        return distance < (DIAMETER / 2.0 + other.DIAMETER / 2.0);
    }
   }
