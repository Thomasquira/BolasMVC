/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import java.util.concurrent.CopyOnWriteArrayList;
import model.*;
import view.View;

/**
 *
 * @author thomas
 */
public class Controller {

    private final Model model;
    private final View view;
    private final EventManager eventManager;

    public Controller() {
        this.model = new Model(this);
        this.view = new View(this);
        this.eventManager = new EventManager(model);
    }

    public void addBall() {
        model.addBall();
    }

    public void addBallIndefinitely() {
        while (true) {

        }
    }

    public void removeBalls() {
        model.removeBalls();
    }

    public CopyOnWriteArrayList<Ball> getAllBalls() {
        return model.getAllBalls();
    }

    public int getViewerWidth() {
        return view.getViewerWidth();

    }

    public int getViewerHeight() {
        return view.getViewerHeight();
    }

    public void setTamaño(int min, int max) {
        model.setTamaño(min, max);
    }

    public void setVelocidad(int min, int max) {
        model.setVelocidad(min, max);
    }

    public Model getModel() {
        return model;
    }

    public View getView() {
        return view;
    }

    public void play() {
        model.setState(Model.STATE.PLAY);
        System.out.println("Play");
    }

    public void pause() {
        model.setState(Model.STATE.PAUSE);
        System.out.println("Pause");

    }

    public void stop() {
        model.setState(Model.STATE.STOP);
        model.removeBalls();
        model.setState(Model.STATE.PAUSE);
    }

    public void manejarEventoBola(Ball ball, BallEvent event) {
        eventManager.manejarEvento(ball, event);
    }

    public void moverBolaIzq() {
        model.moverControlableIzq();
    }

    public void moverBolaDer() {
        model.moverControlableDer();
    }

    public void moverBolaArr() {
        model.moverControlableArr();
    }

    public void moverBolaAba() {
        model.moverControlableAba();
    }
    

}
