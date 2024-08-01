import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.Set;

import static utils.Constants.*;

public class Player extends Sprite {

    private int moveUp;

    private int moveDown;
    double dx;
    double dy;
    public Player(int moveUp, int moveDown, int spawnX) {
        super(PLAYER_IMAGE_PATH, 0, BOARD_HEIGHT/2 -PLAYER_HEIGHT, PLAYER_WIDTH, PLAYER_HEIGHT);
        this.moveUp = moveUp;
        this.moveDown = moveDown;
        pos.x = spawnX;
    }
    @Override
    public void tick() {
        pos.translate((int)dx, (int)dy);
        pos.x = Math.clamp(pos.x, 0, BOARD_WIDTH - PLAYER_WIDTH);
        pos.y = Math.clamp(pos.y, 0, BOARD_HEIGHT - PLAYER_HEIGHT);
    }
    public double getDy(){return dy;}
    public void handleActiveKeys(Set<Integer> activeKeyCodes) {
        dx = 0;
        dy = 0;
        if (activeKeyCodes.contains(moveUp)) {
            dy -= PLAYER_SPEED;
        }
        if (activeKeyCodes.contains(moveDown)) {
            dy += PLAYER_SPEED;
        }
        normalizeDeltas();
    }

    private void normalizeDeltas() {
        if (dx != 0 && dy != 0) {
            dx /= Math.sqrt(2);
            dy /= Math.sqrt(2);
        }
    }

    public void handleCollision(Sprite other) {
        if(other.getClass().equals(Wall.class)) {
            Point previousPos = new Point(pos.x - (int)dx, pos.y - (int)dy);

            if(dx > 0 && previousPos.x + size.width <= other.getTopLeft().x) {
                pos.x = other.getTopLeft().x - size.width;
            }
            else if(dx < 0 && previousPos.x >= other.getBottomRight().x) {
                pos.x = other.getBottomRight().x;
            }
            if(dy > 0 && previousPos.y + size.height <= other.getTopLeft().y) {
                pos.y = other.getTopLeft().y - size.height;
            }
            else if(dy < 0 && previousPos.y >= other.getBottomRight().y) {
                pos.y = other.getBottomRight().y;
            }
        }
    }
}
