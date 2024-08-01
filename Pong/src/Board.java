import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static utils.Constants.*;

public class Board extends JPanel implements ActionListener, KeyListener {
    private final Player player1;
    private final Ball ball;
    private final Wall wall;
    private final Score score;
    private final Wall wall1;
    private final Player player;

    private Boolean running;
    private final List<Sprite> sprites;
    private final Set<Integer> activeKeyCodes;

    public Board() {
        setPreferredSize(new Dimension(BOARD_WIDTH, BOARD_HEIGHT));
        setBackground(Color.BLACK);
        player = new Player(KeyEvent.VK_W,KeyEvent.VK_S, 0);
        player1 = new Player(KeyEvent.VK_UP,KeyEvent.VK_DOWN,BOARD_WIDTH-PLAYER_WIDTH);
        score = new Score();
        ball = new Ball(player,player1,score);
        wall = new Wall(0, BOARD_HEIGHT-WALL_HEIGHT);
        wall1 = new Wall(0, 0 );
        running = true;
        sprites = new ArrayList<>(List.of(player1,player,ball,wall,wall1));
        activeKeyCodes = new HashSet<>();
        new Timer(TICK_DELAY, this).start();
    }

    @Override
    public void actionPerformed(ActionEvent e) {

     if(running) {
         player.handleActiveKeys(activeKeyCodes);
         player1.handleActiveKeys(activeKeyCodes);
         for (Sprite sprite : sprites) {
             sprite.tick();
         }

         for (Sprite sprite : sprites) {
             if (player.isColliding(sprite)) {
                 player.handleCollision(sprite);
             }
             if (player1.isColliding(sprite)) {
                 player1.handleCollision(sprite);
             }
             if (ball.isColliding(sprite)) {
                 ball.handleCollision(sprite);
             }
         }
     }
        repaint();
        checkScore();
    }

    public void checkScore () {
        if (score.getPlayerscore() >= 11) {
            running = false;
        } else if (score.getPlayer1score() >= 11){
            running = false;
        }
    }

    @Override
    public void paint(Graphics graphics) {
        super.paint(graphics);
        for(Sprite sprite : sprites) {
            sprite.draw(graphics, this);
        }
        graphics.setFont(new Font("Times New Roman", Font.BOLD, 50));
        graphics.setColor(Color.WHITE);
        graphics.drawString(String.valueOf(score.getPlayerscore()),40,70);
        graphics.drawString(String.valueOf(score.getPlayer1score()),BOARD_WIDTH - PLAYER_WIDTH - 40,70);
        graphics.drawString(String.valueOf(score.getRally()),BOARD_WIDTH/2,70);

        if (score.getPlayerscore() >= 11) {
            graphics.drawString("Player 1 wins", BOARD_WIDTH/2 - 130, BOARD_HEIGHT/3);
            graphics.drawString("Press SPACE to restart",BOARD_WIDTH/2 - 215, 600);
            graphics.drawString("Highest rally:" + score.gethighScore(),BOARD_WIDTH/2 - 150, 450);
            running = false;
        } else if (score.getPlayer1score() >= 11){
            graphics.drawString("Player 2 wins", BOARD_WIDTH/2 - 130, BOARD_HEIGHT/3);
            graphics.drawString("Press SPACE to restart",BOARD_WIDTH/2 - 215, 600);
            graphics.drawString("Highest rally:" + score.gethighScore(),BOARD_WIDTH/2 - 150 , 450);
            running = false;
        }
    }

    @Override
    public void keyTyped(KeyEvent keyEvent) {
        // Unused
    }

    @Override
    public void keyPressed(KeyEvent keyEvent) {
        activeKeyCodes.add(keyEvent.getKeyCode());

        if(keyEvent.getKeyCode() == keyEvent.VK_SPACE){
            running = true;
            score.setRally(0);
            score.resetScore();

        }
    }

    @Override
    public void keyReleased(KeyEvent keyEvent) {
        activeKeyCodes.remove(keyEvent.getKeyCode());
    }
}
