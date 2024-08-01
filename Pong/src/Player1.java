/*import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.Set;
import static utils.Constants.*;
import static utils.Constants.PLAYER_SPEED;
public class Player1 extends Sprite {
    double dx;
    double dy;
    public Player1() {
        super(PLAYER_IMAGE_PATH, BOARD_WIDTH-PLAYER_WIDTH, BOARD_HEIGHT/2 -PLAYER_HEIGHT, PLAYER_WIDTH, PLAYER_HEIGHT);
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
        if (activeKeyCodes.contains(KeyEvent.VK_UP)) {
            dy -= PLAYER_SPEED;
        }
        if (activeKeyCodes.contains(KeyEvent.VK_DOWN)) {
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
*/