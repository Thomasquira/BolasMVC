/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Animations;

import java.awt.image.BufferedImage;

/**
 *
 * @author thomas
 */
public class SpriteAnimation {

    private final int frameWidth;
    private final int frameHeight;
    private final int totalFrames;
    private final int cadenceMs;

    private BufferedImage spriteSheet;
    private BufferedImage[] frames;
    private int currentFrame = 0;
    private long lastFrameTime = 0;
    private boolean active = false;

    public SpriteAnimation(BufferedImage sheet, int frameWidth, int frameHeight, int totalFrames, int cadenceMs) {
        this.frameWidth = frameWidth;
        this.frameHeight = frameHeight;
        this.totalFrames = totalFrames;
        this.cadenceMs = cadenceMs;

        loadSpriteSheet(sheet);
        splitSpriteSheet();
    }

    private void loadSpriteSheet(BufferedImage sheet) {
        this.spriteSheet = sheet;
    }

    private void splitSpriteSheet() {
        frames = new BufferedImage[totalFrames];

        int cols = spriteSheet.getWidth() / frameWidth;
        int rows = spriteSheet.getHeight() / frameHeight;
        int index = 0;

        for (int r = 0; r < rows && index < totalFrames; r++) {
            for (int c = 0; c < cols && index < totalFrames; c++) {
                frames[index++] = spriteSheet.getSubimage(
                        c * frameWidth,
                        r * frameHeight,
                        frameWidth,
                        frameHeight
                );
            }
        }
    }

    public void update() {
        if (!active || frames == null) {
            return;
        }

        long currentTime = System.currentTimeMillis();
        if (currentTime - lastFrameTime >= cadenceMs) {
            currentFrame = (currentFrame + 1) % frames.length;
            lastFrameTime = currentTime;

            if (currentFrame == 0) {
                active = false;
            }
        }
    }

    public BufferedImage getCurrentFrame() {
        if (frames == null || frames.length == 0 || currentFrame >= frames.length) {
            return null;
        }
        return frames[currentFrame];
    }

    public void start() {
        active = true;
        currentFrame = 0;
        lastFrameTime = System.currentTimeMillis();
    }

    public void reset() {
        currentFrame = 0;
        lastFrameTime = System.currentTimeMillis();
        active = true;
    }

    public void stop() {
        active = false;
    }

    public boolean isActive() {
        return active;
    }

    public int getFrameWidth() {
        return frameWidth;
    }

    public int getFrameHeight() {
        return frameHeight;
    }

    public int getCurrentFrameIndex() {
        return currentFrame;
    }
}
