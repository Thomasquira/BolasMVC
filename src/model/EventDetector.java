/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author thomas
 */
public class EventDetector {
    private final Model model;

    public EventDetector(Model model) {
        this.model = model;
    }
    
    public BallEvent detect(Ball ball, int newX, int newY) {
        if (newX <= 0) {
            return BallEvent.WEST_COLLISION;
        }
        if (newX + ball.getDIAMETER() >= model.getViewerWidth()) {
            return BallEvent.EAST_COLLISION;
        }
        if (newY <= 0) {
            return BallEvent.NORTH_COLLISION;
        }
        if (newY + ball.getDIAMETER() >= model.getViewerHeight()) {
            return BallEvent.SOUTH_COLLISION;
        }
//        ZonaCritica zona = model.getZonaCritica();
        
//        if (zona.isIn(newX, newY)) {
//            return BallEvent.GO_IN_SPECIAL_AREA;
//        }
        
        return BallEvent.NONE;
    }
}
