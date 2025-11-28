/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import model.Ball;
import model.BallEvent;
import model.Model;
import model.ZonaCritica;

/**
 *
 * @author thomas
 */
public class EventManager {

    private final Model model;

    public EventManager(Model model) {
        this.model = model;
    }

    public void manejarEvento(Ball ball, BallEvent event) {
        switch (event) {
            case NORTH_COLLISION:
            case SOUTH_COLLISION:
            case EAST_COLLISION:
            case WEST_COLLISION:
                ball.getFisicas().reaccionarEvento(event);
                break;

//            case GO_IN_SPECIAL_AREA:
////                ZonaCritica zona = model.getZonaCritica();
////                boolean taDentro = zona.goIn(ball);
//
//                if (!taDentro) {
//                    int ballCenterX = ball.getX() + ball.getDIAMETER() / 2;
//                    int ballCenterY = ball.getY() + ball.getDIAMETER() / 2;
//
//                    int rectX = zona.getRectangle().x;
//                    int rectY = zona.getRectangle().y;
//                    int rectW = zona.getRectangle().width;
//                    int rectH = zona.getRectangle().height;
//
//                    int dx = Math.min(Math.abs(ballCenterX - rectX),
//                            Math.abs(ballCenterX - (rectX + rectW)));
//                    int dy = Math.min(Math.abs(ballCenterY - rectY),
//                            Math.abs(ballCenterY - (rectY + rectH)));
//                    if (dx < dy) {
//                        ball.getFisicas().reaccionarEvento(BallEvent.WEST_COLLISION);
//                        
//                    } else {
//                        ball.getFisicas().reaccionarEvento(BallEvent.NORTH_COLLISION);
//                    }
//                }
//                break;
                
            default:
                break;
        }
    }
}
