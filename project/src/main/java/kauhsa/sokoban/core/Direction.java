/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package kauhsa.sokoban.core;

/**
 *
 * @author mika
 */
public enum Direction {
    UP, DOWN, LEFT, RIGHT;
    
    public Direction opposite() {
        if (this == DOWN) {
            return UP;
        } else if (this == UP) {
            return DOWN;
        } else if (this == LEFT) {
            return RIGHT;
        } else if (this == RIGHT) {
            return LEFT;
        }
        
        return null;
    }
}
