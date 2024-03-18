import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Random;

class GamePanel extends JPanel implements ActionListener, KeyListener {
    private boolean game = true;
    private boolean left = false;
    private boolean right = true;
    private boolean up = false;
    private boolean down = false;

    private int length = 3;
    private final int[] snakeLengthX = new int[750];
    private final int[] snakeLengthY = new int[750];
    int[] foodPosX;
    int[] foodPosY;

    private int foodX;
    private int foodY;

    private Timer timer;
    private int delay = 100;

    private int moves = 0;

    private Random newRandom = new Random();

    private final ImageIcon snakeTitle = new ImageIcon("./snakeimages/assets/snaketitle.jpg");
    private final ImageIcon snakeLeftMouth = new ImageIcon("./snakeimages/assets/leftmouth.png");
    private final ImageIcon snakeRightMouth = new ImageIcon("./snakeimages/assets/rightmouth.png");
    private final ImageIcon snakeUpMouth = new ImageIcon("./snakeimages/assets/upmouth.png");
    private final ImageIcon snakeDownMouth = new ImageIcon("./snakeimages/assets/downmouth.png");
    private final ImageIcon snakeImage = new ImageIcon("./snakeimages/assets/snakeimage.png");
    private final ImageIcon foodImage = new ImageIcon("./snakeimages/assets/enemy.png");
    private int score = 0;

    GamePanel() {
        foodPosX = new int[34];
        foodPosY = new int[23];

        for (int i = 0; i < 34; i++) {
            foodPosX[i] = 25 * (1 + i);
        }
        for (int i = 0; i < 23; i++) {
            foodPosY[i] = 75 + 25 * i;
        }

        this.addKeyListener(this);
        setFocusable(true);
        setFocusTraversalKeysEnabled(true);

        timer = new Timer(delay, this);
        timer.start();

        newFood();
    }

    private void newFood() {
        int foodIndexX = newRandom.nextInt(0, 34);
        int foodIndexY = newRandom.nextInt(0, 23);
        foodX = foodPosX[foodIndexX];
        foodY = foodPosY[foodIndexY];

        for (int i = length - 1; i > 0; i--) {
            if (foodX == snakeLengthX[i] && foodY == snakeLengthY[i]) {
                newFood();
            }
        }
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        g.setColor(Color.WHITE);
        g.drawRect(24, 10, 851, 55);
        // g.drawString("Score:", 750, 32);
        // g.drawString(Integer.toString(score), 800, 32);

        g.drawRect(24, 74, 851, 576);

        snakeTitle.paintIcon(this, g, 25, 11);

        g.setColor(Color.BLACK);
        g.fillRect(25, 75, 850, 575);

        if (moves == 0) {
            snakeLengthX[0] = 100;
            snakeLengthX[1] = 75;
            snakeLengthX[2] = 50;

            snakeLengthY[0] = 100;
            snakeLengthY[1] = 100;
            snakeLengthY[2] = 100;
        }

        if (left) {
            snakeLeftMouth.paintIcon(this, g, snakeLengthX[0], snakeLengthY[0]);
        } else if (right) {
            snakeRightMouth.paintIcon(this, g, snakeLengthX[0], snakeLengthY[0]);
        } else if (up) {
            snakeUpMouth.paintIcon(this, g, snakeLengthX[0], snakeLengthY[0]);
        } else {
            snakeDownMouth.paintIcon(this, g, snakeLengthX[0], snakeLengthY[0]);
        }

        for (int i = 1; i < length; i++) {
            snakeImage.paintIcon(this, g, snakeLengthX[i], snakeLengthY[i]);
        }

        foodImage.paintIcon(this, g, foodX, foodY);

        if (!game) {
            g.setColor(Color.WHITE);
            g.setFont(new Font("Arial", Font.BOLD, 25));
            g.drawString("Game Over", 400, 300);

            g.setFont(new Font("Arial", Font.PLAIN, 25));
            g.drawString("Press space to restart", 400, 350);
        }

        g.setColor(Color.WHITE);
        g.setFont(new Font("Arial", Font.PLAIN, 14));
        g.drawString("Score: " + score, 750, 30);
        g.drawString("Length: " + length, 750, 50);

        g.dispose();
    }

    private void collidesWithFood() {
        Rectangle snakeHeadRect = new Rectangle(snakeLengthX[0], snakeLengthY[0], 25, 25);
        Rectangle foodRect = new Rectangle(foodX, foodY, 25, 25);

        if (snakeHeadRect.intersects(foodRect)) {
            newFood();
            length++;
            score++;
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        for (int i = length - 1; i > 0; i--) {
            snakeLengthX[i] = snakeLengthX[i - 1];
            snakeLengthY[i] = snakeLengthY[i - 1];
        }
        if (left) {
            snakeLengthX[0] -= 25;
        } else if (right) {
            snakeLengthX[0] += 25;
        } else if (up) {
            snakeLengthY[0] -= 25;
        } else {
            snakeLengthY[0] += 25;
        }

        if (snakeLengthX[0] < 25) {
            snakeLengthX[0] = 850;
        } else if (snakeLengthX[0] > 850) {
            snakeLengthX[0] = 25;
        }

        if (snakeLengthY[0] < 75) {
            snakeLengthY[0] = 630;
        } else if (snakeLengthY[0] > 630) {
            snakeLengthY[0] = 75;
        }

        collidesWithFood();
        gameOver();

        repaint();
    }

    private void gameOver() {
        for (int i = 1; i < length; i++) {
            Rectangle snakeHeadRectangle = new Rectangle(snakeLengthX[0], snakeLengthY[0], 25, 25);
            Rectangle snakeOtherBody = new Rectangle(snakeLengthX[i], snakeLengthY[i], 25, 25);

            if (snakeHeadRectangle.intersects(snakeOtherBody)) {
                timer.stop();
                game = false;
            }
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_LEFT && !right) {
            left = true;
            up = false;
            down = false;
            moves++;
        } else if (e.getKeyCode() == KeyEvent.VK_RIGHT && !left) {
            right = true;
            up = false;
            down = false;
            moves++;
        } else if (e.getKeyCode() == KeyEvent.VK_UP && !down) {
            up = true;
            left = false;
            right = false;
            moves++;
        } else if (e.getKeyCode() == KeyEvent.VK_DOWN && !up) {
            down = true;
            left = false;
            right = false;
            moves++;
        } else if (e.getKeyCode() == KeyEvent.VK_SPACE && !game) {
            left = false;
            right = true;
            up = false;
            down = false;
            moves = 0;
            length = 3;
            score = 0;
            game = true;
            timer.start();
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}

public class Main {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Snake Game");
        frame.setSize(900, 700);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);

        GamePanel panel = new GamePanel();
        panel.setBackground(Color.DARK_GRAY);
        frame.add(panel);

        frame.setVisible(true);
    }
}
