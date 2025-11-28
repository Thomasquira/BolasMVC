/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import controller.Controller;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 *
 * @author thomas
 */
public class Model {

    private final Controller controller;
    private CopyOnWriteArrayList<Ball> ballList;
    private int tamañoMinimo, tamañoMaximo, velocidadMaxima, velocidadMinima;
//    private ZonaCritica zonaCritica;
    private final EventDetector eventDetector;

    public enum STATE {
        PLAY, PAUSE, STOP
    };
    private STATE state;

    public Model(Controller controller) {
        this.controller = controller;
        this.ballList = new CopyOnWriteArrayList<>();
        this.state = STATE.PLAY;
//        this.zonaCritica = new ZonaCritica(this);
        this.eventDetector = new EventDetector(this);
    }

    public void addBall() {
        Ball ball = new Ball(this, tamañoMinimo, tamañoMaximo, velocidadMinima, velocidadMaxima);
        ballList.add(ball);
    }

    public void removeBalls() {
        ballList.clear();
    }

    public CopyOnWriteArrayList<Ball> getAllBalls() {
        return ballList;
    }

    public int getViewerWidth() {
        return controller.getViewerWidth();
    }

    public int getViewerHeight() {
        return controller.getViewerHeight();
    }

    public void setTamaño(int min, int max) {
        this.tamañoMinimo = min;
        this.tamañoMaximo = max;
    }

    public void setVelocidad(int min, int max) {
        this.velocidadMaxima = max;
        this.velocidadMinima = min;
    }

    public STATE getState() {
        return state;
    }

    public void setState(STATE state) {
        this.state = state;
    }

//    public boolean stepAuthorization(int x, int y, Ball ball) {
//        if (zonaCritica.isIn(x, y)) {
//            return zonaCritica.goIn(ball);
//        }
//        return true;
//    }

//    public ZonaCritica getZonaCritica() {
//        return zonaCritica;
//    }
//
//    public void setZonaCritica(ZonaCritica zonaCritica) {
//        this.zonaCritica = zonaCritica;
//    }

    public BallEvent detectEvent(Ball ball, int newX, int newY) {
        if (newY <= 0) {
            return BallEvent.NORTH_COLLISION;
        }
        if (newY + ball.getDIAMETER() >= getViewerHeight()) {
            return BallEvent.SOUTH_COLLISION;
        }
        if (newX <= 0) {
            return BallEvent.WEST_COLLISION;
        }
        if (newX + ball.getDIAMETER() >= getViewerWidth()) {
            return BallEvent.EAST_COLLISION;
        }
//        if (zonaCritica.isIn(newX, newY)) {
//            return BallEvent.GO_IN_SPECIAL_AREA;
//        }
        return BallEvent.NONE;
    }

    public EventDetector getEventDetector() {
        return eventDetector;
    }

    public Controller getController() {
        return controller;
    }

    public void moverControlableIzq() {
        Controlable p = controller.getView().getBolaControlable();
        if (p == null) {
            return;
        }

        if (p.getX() > 0) {
            p.moverIzq();
        }
    }

    
    public void moverControlableDer() {
        Controlable p = controller.getView().getBolaControlable();
        if (p == null) {
            return;
        }

        if (p.getX() + p.getDIAMETER() < controller.getViewerWidth()) {
            p.moverDer();
        }
    }

    public void moverControlableArr() {
        Controlable p = controller.getView().getBolaControlable();
        if (p == null) {
            return;
        }

        if (p.getY() > 0) {
            p.moverArr();
        }
    }

    public void moverControlableAba() {
        Controlable p = controller.getView().getBolaControlable();
        if (p == null) {
            return;
        }

        if (p.getY() + p.getDIAMETER() < controller.getViewerHeight()) {
            p.moverAba();
        }
    }

    
}
