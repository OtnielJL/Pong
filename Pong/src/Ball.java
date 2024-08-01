import java.awt.*;

import static utils.Constants.*;
public class Ball extends Sprite {
    private double vx = -13;
    private final Player player1;
    private final Score score;
    private final Player player;
    private double vy = 0;
    public Ball(Player player, Player player1, Score score) {
        super(BALL_IMAGE_PATH, BOARD_WIDTH / 2, BOARD_HEIGHT / 2 - BALL_HEIGHT / 2, BALL_WIDTH, BALL_HEIGHT);
        this.player= player;
        this.player1 = player1;
        this.score = score;
    }
    @Override
    public void tick() {
        pos.x += vx;
        pos.y += vy;
        if ((pos.x < -BALL_WIDTH)){
            pos.x = BOARD_WIDTH/2;
            pos.y = BOARD_HEIGHT/2 - BALL_HEIGHT;
            vx = 13;
            vy = 0;
            score.setRally(0);
            score.incrementPlayer1score();
        }else if ((pos.x > BOARD_WIDTH)){
            pos.x = BOARD_WIDTH/2;
            pos.y = BOARD_HEIGHT/2 - BALL_HEIGHT;
            vx = 13;
            vy = 0;
            score.setRally(0);
            score.incrementPlayerscore();
        }
    }
    public void handleCollision(Sprite other) {
            if(other.getClass().equals(Wall.class)){
                vy= - vy;
            }else{
                vy = 4;
            }
            if (isColliding(player)){
                if (vx < 0){
                    vx = -vx;
                }

                if (player.getDy() < 0){
                    vy = - 1 * Math.abs(vy);
                }

                if (player.getDy() > 0) {
                    vy = Math.abs(vy);
                }


            } else if (isColliding(player1)){
                if (vx > 0 ){
                    vx = -vx;
                }  if (player1.getDy() < 0){
                    vy = - 1 * Math.abs(vy);
                    } if (player1.getDy() > 0){
                    vy = Math.abs(vy);
                }
            }

            if(other.getClass().equals(Player.class)) {
                score.incrementRally();
                vx *= 1.05;
            }

    }

}

