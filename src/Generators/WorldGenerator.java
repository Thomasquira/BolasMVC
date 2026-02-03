/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Generators;

import controller.Controller;
import java.awt.Color;
import model.Ball;
import model.Model;

/**
 *
 * @author thomas
 */
public class WorldGenerator {
    private Model model;
    private Controller controller;
    
    public WorldGenerator(Model model, Controller controller) {
        this.model = model;
        this.controller = controller;
    }
    
    public void generateInitialWorld() {
        System.out.println("Generando mundo inicial...");
        
//        if (model.getZonaCritica() == null) {
//            model.setZonaCritica(new ZonaCritica(model));
//        }
        
//        for (int i = 0; i < 5; i++) {
//            addRandomBall();
//        }
//        
        System.out.println("Mundo inicial generado con " + model.getAllBalls().size() + " bolas");
    }
    
    public void generateRandomWorld() {
        System.out.println("Generando nuevo mundo aleatorio...");
        
        controller.removeBalls();
        
        int numBolas = 8 + (int)(Math.random() * 12); // Entre 8 y 20 bolas
        for (int i = 0; i < numBolas; i++) {
            addRandomBall();
        }
        
        System.out.println("Mundo aleatorio generado con " + numBolas + " bolas");
    }
    
    public void addRandomBall() {
      
        int minSize = model.getMinSize() > 0 ? model.getMinSize() : 10;
        int maxSize = model.getMaxSize() > 0 ? model.getMaxSize() : 40;
        int minSpeed = model.getMinSpeed() > 0 ? model.getMinSpeed() : 1;
        int maxSpeed = model.getMaxSpeed() > 0 ? model.getMaxSpeed() : 5;
        
     
        if (minSize > maxSize) minSize = maxSize;
        if (minSpeed > maxSpeed) minSpeed = maxSpeed;
        
       
        int size = minSize + (int)(Math.random() * (maxSize - minSize + 1));
        Color color = new Color(
            (int)(Math.random() * 200 + 55), // Evitar colores muy oscuros
            (int)(Math.random() * 200 + 55),
            (int)(Math.random() * 200 + 55)
        );
        
        int x = size + (int)(Math.random() * (model.getViewerWidth() - size * 2));
        int y = size + (int)(Math.random() * (model.getViewerHeight() - size * 2));
        
        Ball ball = new Ball(model, x, y, size, color);
        model.addEntity(ball);
    }
}
