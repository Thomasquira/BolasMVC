/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

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
        super(model, 40, 40, 0, 0);
        this.x = x;
        this.y = y;

        try {
            naveImg = ImageIO.read(new File("src/nave.png")); 
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public BufferedImage getImagen() {
        return naveImg;
    }

    public void moverIzq() {
        x = Math.max(0, x - 10);
    }

    public void moverDer() {
        x = Math.min(model.getViewerWidth() - DIAMETER, x + 10);
    }

    public void moverArr() {
        y = Math.max(0, y - 10);
    }

    public void moverAba() {
        y = Math.min(model.getViewerHeight() - DIAMETER, y + 10);
    }

}
