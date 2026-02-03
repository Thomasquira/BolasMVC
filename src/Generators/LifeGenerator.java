/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Generators;

import controller.Controller;
import model.Model;

/**
 *
 * @author thomas
 */
public class LifeGenerator implements Runnable {
    private Model model;
    private Controller controller;
    private boolean active = false;
    private long generationInterval = 1000; // ms
    private int ballsGenerated = 0;
    private int maxBalls = 50; // Límite de bolas
    
    public LifeGenerator(Model model, Controller controller) {
        this.model = model;
        this.controller = controller;
    }
    
    public void setActive(boolean active) {
        this.active = active;
        if (active) {
            System.out.println("LifeGenerator ACTIVADO");
        } else {
            System.out.println("LifeGenerator DESACTIVADO");
        }
    }
    
    public void setGenerationInterval(long interval) {
        this.generationInterval = interval;
    }
    
    public void setMaxBalls(int maxBalls) {
        this.maxBalls = maxBalls;
    }
    
    @Override
    public void run() {
        System.out.println("LifeGenerator iniciado. Intervalo: " + generationInterval + "ms");
        
        while (true) {
            if (active && model.getState() == Model.STATE.PLAY) {
                if (model.getAllBalls().size() < maxBalls) {
                    controller.addBall();
                    ballsGenerated++;
                    
                    if (ballsGenerated % 10 == 0) {
                        System.out.println("LifeGenerator: " + ballsGenerated + " bolas generadas");
                    }
                } else {
                    System.out.println("LifeGenerator: Límite de " + maxBalls + " bolas alcanzado");
                }
                
                try {
                    Thread.sleep(generationInterval);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    System.out.println("LifeGenerator interrumpido");
                    break;
                }
            } else {
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    break;
                }
            }
        }
    }
    
    public int getBallsGenerated() {
        return ballsGenerated;
    }
}

