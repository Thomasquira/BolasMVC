/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import Animations.SpriteAnimation;
import Communications.Connections.Channel;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import javax.imageio.ImageIO;


/**
 *
 * @author thomas
 */
public class Ball extends Entity implements Runnable, Serializable {
    protected int speedX, speedY;
    protected final int DIAMETER;
    protected final Color COLOR;
    protected Thread thread;
    protected final BasicPhysicsEngine fisicas;
    private SpriteAnimation explosionAnimation;
    private boolean isExploding = false;
    private long explosionStartTime = 0;
    private static final long EXPLOSION_DURATION = 500;
    private boolean facingRight = true;
    private transient Channel channel;
    
    public Ball(Model model, int minimoTam, int maximoTam, int minimoVel, int maximoVel) {
        super(model, 
              (int) (Math.random() * model.getViewerWidth()),
              (int) (Math.random() * model.getViewerHeight()));
        
        if (minimoTam <= 0) minimoTam = 10;
        if (maximoTam < minimoTam) maximoTam = minimoTam + 10;
        if (minimoVel <= 0) minimoVel = 1;
        if (maximoVel < minimoVel) maximoVel = minimoVel + 2;
        
        this.DIAMETER = minimoTam + (int) (Math.random() * (maximoTam - minimoTam + 1));
        this.COLOR = new Color(
            (int) (Math.random() * 255),
            (int) (Math.random() * 255),
            (int) (Math.random() * 255)
        );
        
        int velocidadX = minimoVel + (int) (Math.random() * (maximoVel - minimoVel + 1));
        int velocidadY = minimoVel + (int) (Math.random() * (maximoVel - minimoVel + 1));
        
        if (Math.random() > 0.5) velocidadX = -velocidadX;
        if (Math.random() > 0.5) velocidadY = -velocidadY;
        
        this.fisicas = new BasicPhysicsEngine(velocidadX, velocidadY, this);
        this.speedX = fisicas.getSpeedX();
        this.speedY = fisicas.getSpeedY();
        
        initAnimations();
        
        this.thread = new Thread(this);
        this.thread.start();
    }
    
    public Ball(Model model, int x, int y, int diametro, Color color) {
        super(model, x, y);
        
        if (diametro <= 0) diametro = 20;
        if (color == null) color = Color.BLUE;
        
        this.DIAMETER = diametro;
        this.COLOR = color;
        
        int velocidadPorDefecto = 3;
        int velocidadX = (Math.random() > 0.5 ? velocidadPorDefecto : -velocidadPorDefecto);
        int velocidadY = (Math.random() > 0.5 ? velocidadPorDefecto : -velocidadPorDefecto);
        
        this.fisicas = new BasicPhysicsEngine(velocidadX, velocidadY, this);
        this.speedX = fisicas.getSpeedX();
        this.speedY = fisicas.getSpeedY();
        
        initAnimations();
        
        this.thread = new Thread(this);
        this.thread.start();
    }
    
    private void initAnimations() {
        try {
            BufferedImage spriteSheet = ImageIO.read(new File("src/sprite.png"));
             explosionAnimation = new SpriteAnimation(
                spriteSheet,
                256,   // anchura de frame
                128,   // altura de frame
                12,    // cuardos totales
                60     // velocidad
        );
        } catch (IOException e) {
            System.err.println("Error cargando spritesheet de explosión");
            explosionAnimation = null;
        }
    }
    
     private void checkDirectionChange(int newSpeedX) {
        if (newSpeedX > 0 && !facingRight) {
            facingRight = true;
        } else if (newSpeedX < 0 && facingRight) {
            facingRight = false;
        }
    }
    
      public void triggerExplosion() {
        if (explosionAnimation != null && !isExploding) {
            isExploding = true;
            explosionStartTime = System.currentTimeMillis();
            explosionAnimation.reset(); 
        }
    }
      
    @Override
     public void run() {
        while (model.getState() != Model.STATE.STOP) {
            if (model.getState() == Model.STATE.PLAY) {
                int[] next = fisicas.calcularPosicion(x, y);
                
                boolean hitWall = false;
                
                if (next[0] <= 0) {
                    next[0] = 0;
                    fisicas.setSpeedX(Math.abs(fisicas.getSpeedX()));
                    hitWall = true;
                }
                if (next[0] + DIAMETER >= model.getViewerWidth()) {
                    next[0] = model.getViewerWidth() - DIAMETER;
                    fisicas.setSpeedX(-Math.abs(fisicas.getSpeedX()));
                    hitWall = true;
                }
                if (next[1] <= 0) {
                    next[1] = 0;
                    fisicas.setSpeedY(Math.abs(fisicas.getSpeedY()));
                    hitWall = true;
                }
                if (next[1] + DIAMETER >= model.getViewerHeight()) {
                    next[1] = model.getViewerHeight() - DIAMETER;
                    fisicas.setSpeedY(-Math.abs(fisicas.getSpeedY()));
                    hitWall = true;
                }
                
                checkDirectionChange(fisicas.getSpeedX());
                
                if (hitWall) {
                    triggerExplosion();
                }
                
                x = next[0];
                y = next[1];
                
                if (isExploding) {
                    long currentTime = System.currentTimeMillis();
                    if (currentTime - explosionStartTime > EXPLOSION_DURATION) {
                        isExploding = false;
                    }
                }
            }
            
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                break;
            }
        }
    }
    
    @Override
    public void render(Graphics2D g) {
        if (isExploding && explosionAnimation != null) {
            BufferedImage currentFrame = explosionAnimation.getCurrentFrame();
            if (currentFrame != null) {
                int explosionX = x - (currentFrame.getWidth() - DIAMETER) / 2;
                int explosionY = y - (currentFrame.getHeight() - DIAMETER) / 2;
                
                if (!facingRight) {
                    g.drawImage(currentFrame, 
                               explosionX + currentFrame.getWidth(), 
                               explosionY,
                               -currentFrame.getWidth(), 
                               currentFrame.getHeight(), 
                               null);
                } else {
                    g.drawImage(currentFrame, explosionX, explosionY, null);
                }
            }
        }
        
        Composite originalComposite = g.getComposite();
        if (isExploding) {
            g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.3f));
        }
        
        g.setColor(COLOR);
        g.fillOval(x, y, DIAMETER, DIAMETER);
        g.setColor(Color.BLACK);
        g.drawOval(x, y, DIAMETER, DIAMETER);
        
        if (isExploding) {
            g.setComposite(originalComposite);
        }
        
        if (explosionAnimation != null) {
            explosionAnimation.update();
        }
    }
    
    public boolean isExploding() {
        return isExploding;
    }
    
    public SpriteAnimation getExplosionAnimation() {
        return explosionAnimation;
    }
    
    public boolean isFacingRight() {
        return facingRight;
    }
    
    public int getDIAMETER() {
        return this.DIAMETER;
    }
    
    public Color getCOLOR() {
        return COLOR;
    }
    
    public BasicPhysicsEngine getFisicas() {
        return fisicas;
    }
    
    public int getSpeedX() {
        return speedX;
    }
    
    public int getSpeedY() {
        return speedY;
    }
    
    public boolean isColliding(Ball other) {
        if (other == null || other == this) return false;
        
        double centroX1 = x + DIAMETER / 2.0;
        double centroY1 = y + DIAMETER / 2.0;
        double centroX2 = other.x + other.DIAMETER / 2.0;
        double centroY2 = other.y + other.DIAMETER / 2.0;
        
        double distancia = Math.sqrt(
            Math.pow(centroX1 - centroX2, 2) + 
            Math.pow(centroY1 - centroY2, 2)
        );
        
        return distancia < (DIAMETER / 2.0 + other.DIAMETER / 2.0);
    }
    
    public void activation() {
        if (thread != null && !thread.isAlive()) {
            thread = new Thread(this);
            thread.start();
        }
    }

    public Channel getChannel() {
        return channel;
    }

    public void setChannel(Channel channel) {
        this.channel = channel;
    }
    
    
}
