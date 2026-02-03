/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.awt.Graphics2D;

/**
 *
 * @author thomas
 */
public abstract class Entity {
    protected Model model;
    protected int x, y;
    
    public Entity(Model model, int x, int y) {
        this.model = model;
        this.x = x;
        this.y = y;
    }

    public abstract void render(Graphics2D g);
    
    public int getX() { return x; }
    public int getY() { return y; }
    public void setX(int x) { this.x = x; }
    public void setY(int y) { this.y = y; }
    
    public Model getModel() { return model; }
}
