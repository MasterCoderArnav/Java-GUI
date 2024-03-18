import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

class brickBreakerGameplay extends JPanel implements KeyListener, ActionListener {

    private boolean play = false;
    private int score = 0;
    private int totalBrick = 21;
    private Timer timer;
    private int delay = 8;
    private int ballPosX = 120;
    private int ballPosY = 350;
    private int ballXDir = -1;
    private int ballYDir = -2;
    private int playerPosX = 350;
    private boolean bricks[][];

    brickBreakerGameplay() {
        this.addKeyListener(this);
        this.setFocusable(true);
        this.setFocusTraversalKeysEnabled(true);
        timer = new Timer(delay, this);
        timer.start();
        bricks = new boolean[3][7];
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 7; j++) {
                bricks[i][j] = true;
            }
        }
    }

    public void paint(Graphics g) {
        if (totalBrick == 0) {
            g.setColor(Color.RED);
            g.setFont(new Font("serif", Font.BOLD, 30));
            g.drawString("You Won", 350, 300);
            return;
        }
        super.paint(g);
        g.setColor(Color.YELLOW);
        g.fillRect(0, 0, 700, 3);
        g.fillRect(0, 3, 3, 592);
        g.fillRect(697, 3, 3, 592);

        g.setColor(Color.GREEN);
        g.fillRect(playerPosX, 550, 100, 8);

        g.setColor(Color.RED);
        g.fillOval(ballPosX, ballPosY, 20, 20);

        if (ballXDir == 0 && ballYDir == 0 && totalBrick != 0) {
            g.setColor(Color.GREEN);
            g.setFont(new Font("serif", Font.BOLD, 30));
            g.drawString("Game Over!", 300, 300);
        }

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 7; j++) {
                if (bricks[i][j]) {
                    g.setColor(Color.WHITE);
                    g.fillRect(100 * (j + 1), 50 * (i + 1), 90, 40);
                } else {
                    g.setColor(Color.BLACK);
                    g.fillRect(100 * (j + 1), 50 * (i + 1), 90, 40);
                }
            }
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (play) {
            if (new Rectangle(ballPosX, ballPosY, 20, 20).intersects(new Rectangle(playerPosX, 550, 100, 8))) {
                ballYDir = -ballYDir;
            } else if (ballPosX < 0 || ballPosX > 680) {
                ballXDir = -ballXDir;
            } else if (ballPosY < 0) {
                ballYDir = -ballYDir;
            }
            if (ballPosY > 570) {
                play = false;
                ballXDir = 0;
                ballYDir = 0;
            }

            for (int i = 0; i < bricks.length; i++) {
                for (int j = 0; j < bricks[i].length; j++) {
                    if (bricks[i][j]) {
                        int brickX = 100 * (j + 1);
                        int brickY = 50 * (i + 1);

                        Rectangle brickRect = new Rectangle(brickX, brickY, 90, 40);
                        if (new Rectangle(ballPosX, ballPosY, 20, 20).intersects(brickRect)) {
                            bricks[i][j] = false;
                            totalBrick--;
                            score += 5;
                            if (ballPosX + 19 <= brickRect.x || ballPosX + 1 >= brickRect.x + brickRect.width) {
                                ballXDir = -ballXDir;
                            } else {
                                ballYDir = -ballYDir;
                            }
                        }
                    }
                }
            }

            ballPosX += ballXDir;
            ballPosY += ballYDir;
        }
        repaint();
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
            if (playerPosX >= 600) {
                playerPosX = 600;
            } else {
                moveRight();
            }
            repaint();
        } else if (e.getKeyCode() == KeyEvent.VK_LEFT) {
            if (playerPosX < 10) {
                playerPosX = 10;
            } else {
                moveLeft();
            }
            repaint();
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyReleased(KeyEvent e) {

    }

    public void moveRight() {
        play = true;
        playerPosX += 20;
    }

    public void moveLeft() {
        play = true;
        playerPosX -= 20;
    }
}

public class brickBreaker {
    public static void main(String[] args) {
        JFrame frame = new JFrame();
        frame.setTitle("Brick Breaker");
        frame.setSize(700, 600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);

        brickBreakerGameplay gamePlay = new brickBreakerGameplay();
        gamePlay.setBackground(Color.BLACK);
        frame.add(gamePlay);

        frame.setVisible(true);
    }
}
