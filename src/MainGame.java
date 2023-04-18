import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.*;


public class MainGame extends JPanel implements ActionListener {

    private final int B_WIDTH = 299;
    private final int B_HEIGHT = 299;
    private final int DOT_SIZE = 10;
    private final int ALL_DOTS = 900;
    private final int RAND_POS = 25;
    private final int DELAY = 85;
    private int points = 0;

    private final int x[] = new int[ALL_DOTS];
    private final int y[] = new int[ALL_DOTS];

    private int dots;

    private int cookie_x;


    private int cookie_x2;


    private int cookie_y;

    private int cookie_y2;


    private JLabel score = new JLabel(String.valueOf(dots));

    private boolean leftDirection = false;
    private boolean rightDirection = true;
    private boolean upDirection = false;
    private boolean downDirection = false;
    private boolean inGame = true;

    private Timer timer;
    private Image ball;
    private Image cookie;
    private Image head;

    public MainGame() {

        initiateGame();
    }

    private void initiateGame() {

        addKeyListener(new TAdapter());
        setBackground(Color.black);
        setFocusable(true);

        setPreferredSize(new Dimension(B_WIDTH, B_HEIGHT));
        loadImages();
        startGame();
    }

    private void loadImages() {


        ImageIcon iid = new ImageIcon("src/resources1/head.png");
        ball = iid.getImage();

        ImageIcon iia = new ImageIcon("src/resources1/cookie.png");
        cookie = iia.getImage();

        ImageIcon iih = new ImageIcon("src/resources1/eyes.png");
        head = iih.getImage();
    }

    private void startGame() {

        dots = 3;

        for (int z = 0; z < dots; z++) {
            x[z] = 50 - z * 10;
            y[z] = 50;
        }

        locateCookie();
        locateAnotherCookie();

        timer = new Timer(DELAY, this);
        timer.start();
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        doDrawing(g);
        if(points >5){
            doTwoDrawings(g);
        }
    }

    private void doDrawing(Graphics g) {

        if (inGame) {

            g.drawImage(cookie, cookie_x, cookie_y, this);

            for (int z = 0; z < dots; z++) {
                if (z == 0) {
                    g.drawImage(head, x[z], y[z], this);
                } else {
                    g.drawImage(ball, x[z], y[z], this);
                }
            }

            Toolkit.getDefaultToolkit().sync();

        } else {

            endGame(g);
        }
    }

    private void doTwoDrawings(Graphics g2){
        g2.drawImage(cookie, cookie_x2,cookie_y2,this);
        if (inGame) {

            g2.drawImage(cookie, cookie_x, cookie_y, this);

            for (int z = 0; z < dots; z++) {
                if (z == 0) {
                    g2.drawImage(head, x[z], y[z], this);
                } else {
                    g2.drawImage(ball, x[z], y[z], this);
                }
            }

            Toolkit.getDefaultToolkit().sync();

        } else {

            endGame(g2);
        }
    }

    private void endGame(Graphics g) {

        String msg = "Game Over";
        Font small = new Font("Helvetica", Font.BOLD, 14);
        FontMetrics metr = getFontMetrics(small);

        g.setColor(Color.white);
        g.setFont(small);
        g.drawString(msg, (B_WIDTH - metr.stringWidth(msg)) / 2, B_HEIGHT / 2);
    }

    private void checkCookie() {

        if ((x[0] == cookie_x) && (y[0] == cookie_y)) {

            dots++;
            points++;
            score.setText(String.valueOf(points));
            score.setForeground(Color.white);
            locateCookie();
            locateAnotherCookie();

        }
    }

    private void checkAnotherCookie() {      //Class to add points for the second apple

        if ((x[0] == cookie_x2) && (y[0] == cookie_y2)) {

            dots++;
            points++;
            score.setText(String.valueOf(points));
            score.setForeground(Color.white);
            locateCookie();
            locateAnotherCookie();

        }
    }

    private void move() {

        for (int z = dots; z > 0; z--) {
            x[z] = x[(z - 1)];
            y[z] = y[(z - 1)];
        }

        if (leftDirection) {
            x[0] -= DOT_SIZE;
        }

        if (rightDirection) {
            x[0] += DOT_SIZE;
        }

        if (upDirection) {
            y[0] -= DOT_SIZE;
        }

        if (downDirection) {
            y[0] += DOT_SIZE;
        }
    }

    private void snakeCollision() {

        for (int z = dots; z > 0; z--) {

            if ((z > 4) && (x[0] == x[z]) && (y[0] == y[z])) {
                inGame = false;
            }
        }

        if (y[0] >= B_HEIGHT) {
            inGame = false;
        }

        if (y[0] < 0) {
            inGame = false;
        }

        if (x[0] >= B_WIDTH) {
            inGame = false;
        }

        if (x[0] < 0) {
            inGame = false;
        }

        if (!inGame) {
            timer.stop();
        }
    }

    private void locateCookie() {

        int r = (int) (Math.random() * RAND_POS);
        cookie_x = ((r * DOT_SIZE));

        r = (int) (Math.random() * RAND_POS);
        cookie_y = ((r * DOT_SIZE));

        System.out.println("X "+ cookie_x);
        System.out.println("Y "+ cookie_y);
        System.out.println();
    }
    private void locateAnotherCookie(){
        int r2 = (int) (Math.random() * RAND_POS);
        cookie_x2 =((r2 * DOT_SIZE));

        r2 = (int) (Math.random() * RAND_POS);
        cookie_y2 = ((r2 * DOT_SIZE));

        System.out.println("X2 "+ cookie_x2);
        System.out.println("Y2 "+ cookie_y2);


    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (inGame) {

            checkCookie();
            checkAnotherCookie();
            snakeCollision();
            move();


        }

        repaint();
    }

    private class TAdapter extends KeyAdapter {

        @Override
        public void keyPressed(KeyEvent e) {

            int key = e.getKeyCode();

            if ((key == KeyEvent.VK_A) && (!rightDirection)) {
                leftDirection = true;
                upDirection = false;
                downDirection = false;
            }

            if ((key == KeyEvent.VK_D) && (!leftDirection)) {
                rightDirection = true;
                upDirection = false;
                downDirection = false;
            }

            if ((key == KeyEvent.VK_W) && (!downDirection)) {
                upDirection = true;
                rightDirection = false;
                leftDirection = false;
            }

            if ((key == KeyEvent.VK_S) && (!upDirection)) {
                downDirection = true;
                rightDirection = false;
                leftDirection = false;
            }
        }
    }
}