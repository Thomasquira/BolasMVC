/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author thomas
 */
public class BasicPhysicsEngine {

    private int speedX, speedY;

    public BasicPhysicsEngine(int speedX, int speedY) {
        this.speedX = speedX;
        this.speedY = speedY;
    }

    public int getSpeedX() {
        return speedX;
    }

    public void setSpeedX(int speedX) {
        this.speedX = speedX;
    }

    public int getSpeedY() {
        return speedY;
    }

    public void setSpeedY(int speedY) {
        this.speedY = speedY;
    }

    public int[] calcularPosicion(int x, int y) {
        return new int[]{x + speedX, y + speedY};

    }
    
    public void reaccionarEvento(BallEvent event) {
        switch(event) {
            case NORTH_COLLISION:
            case SOUTH_COLLISION:
                speedY = -speedY;
                break;
            case EAST_COLLISION:
            case WEST_COLLISION:
                speedX = -speedX;
                break;
            default:
                break;
        }
    }

}
