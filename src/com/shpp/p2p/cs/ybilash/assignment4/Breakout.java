package com.shpp.p2p.cs.ybilash.assignment4;

import com.shpp.cs.a.graphics.WindowProgram;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Random;

import acm.graphics.*;

/**
 * This class describes the game Breakout
 */

public class Breakout extends WindowProgram implements BreakoutInterface {

    private static final Random random = new Random();
    private Paddle paddle;
    private ArrayList<Ball> ball = new ArrayList<>();

    private final ArrayList<Brick> bricksArray = new ArrayList<>();
    private final ArrayList<BonusBrick> bonusBricksArray = new ArrayList<>();
    /*
    This variables indicates count game tries
     */
    int countOfLives = NTURNS;
    GLabel countOfLivesMessage;
    GLabel highScore;
    GLabel score;
    int sumOfCurrentScore = 0;

    //GImage image = new GImage("C:\\Users\\48791\\Desktop\\background.jpg");

    public void run() {
        addMouseListeners();
        paddle = createPaddle();
        createStartBall();
        createCountOfLives();
        createCountOfScore();
        createBricksRowAndCol();
        waitForClick();
        ballAnimation();
    }


    private void createStartBall() {
        double x = getWidth() / 2.0 - BALL_RADIUS / 2.0;
        double y = getHeight() / 2.0 - BALL_RADIUS / 2.0;
        ball.add(createBall(x, y));
    }

    public void doTripleBall() {
        double x = ball.get(0).getX();
        double y = ball.get(0).getY();
        for (int i = 0; i < TRIPLE_BALLS_COUNT; i++) {
            ball.add(createBall(x, y));
        }
    }

    private void createCountOfScore() {
        score = new GLabel("YOUR SCORE: " + sumOfCurrentScore);
        score.setFont("Comic Sans MS-15");
        score.setColor(Color.BLACK);
        double x = 0;
        double y = 0 + score.getHeight();
        add(score, x, y);
    }

    private void createCountOfLives() {
        countOfLivesMessage = new GLabel("LIVES LEFT: " + countOfLives);
        System.out.println(countOfLivesMessage);
        countOfLivesMessage.setFont("Comic Sans MS-15");
        countOfLivesMessage.setColor(Color.BLACK);
        double x = 0 + countOfLivesMessage.getWidth() * 2.9;
        double y = 0 + countOfLivesMessage.getHeight();
        add(countOfLivesMessage, x, y);
    }

    /**
     * This method creates a label and specifies its font,
     * as well as centers the inscription and adds it to the screen
     *
     * @param msg           parameter that passes the string
     * @param colorsForGame parameter that sets the color
     * @return label information
     */
    private GLabel createLabel(String msg, Color colorsForGame) {
        GLabel labelInformation = new GLabel(msg);
        labelInformation.setFont("Comic Sans MS-30");
        labelInformation.setColor(colorsForGame);
        double x = getWidth() / 2.0 - labelInformation.getWidth() / 2;
        double y = getHeight() / 2.0 - labelInformation.getHeight() / 2;
        add(labelInformation, x, y);
        return labelInformation;
    }

    /**
     * This method creates a string for the case if the player won
     */
    private void showMessageForWinGame() {
        String messageForGameIsWin = "GAME WIN";
        createLabel(messageForGameIsWin, Color.GREEN);
    }

    /**
     * This method creates a string for the case if the player miss
     *
     * @return method createLabel
     */
    private GLabel showMessageForGameMissed() {
        String messageForGameIsMissed = "MISSED, TRY AGAIN";
        return createLabel(messageForGameIsMissed, Color.ORANGE);
    }

    /**
     * This method creates a string for the case if the player lost
     */
    private void showMessageForGameOver() {
        String messageForGameIsOver = "GAME OVER";
        createLabel(messageForGameIsOver, Color.RED);
    }

    /**
     * This method fixes the paddle along the x-axis and y-axis
     * and with the help of thse checks sets its movement
     *
     * @param e the event to be processed
     */
    public void mouseMoved(MouseEvent e) {
        double x = e.getX() - paddle.getWidth() / 2;
        double y = getHeight() - (PADDLE_Y_OFFSET + PADDLE_HEIGHT);
//        if (PADDLE_TEST) {
//            y = e.getY() - paddle.getHeight() / 2;
//        } else {
//            y = getHeight() - (PADDLE_Y_OFFSET + PADDLE_HEIGHT);
//        }
        if (x > getWidth() - paddle.getWidth()) {
            paddle.setLocation(getWidth() - paddle.getWidth(), y);
        } else if (x < 0) {
            paddle.setLocation(0, y);
        } else {
            paddle.setLocation(x, y);
        }
    }


    /**
     * This method creates a paddle, sets its color and displays
     * it on the screen and centers it at the bottom of the screen
     *
     * @return racket
     */
    private Paddle createPaddle() {
        Paddle racket = new Paddle(getWidth(), getHeight());
        add(racket);
        return racket;
    }

    /**
     * This method creates a ball,sets color and displays
     * it on the centers on the screen
     *
     * @return ball
     */
    private Ball createBall(double x, double y) {
        Ball ball = new Ball(x, y, BALL_RADIUS, getWidth());
        add(ball);
        return ball;
    }

    /**
     * This method checks the ball for collision with other objects if
     *
     * @return null to rewind the next collision point
     */
    public GObject getCollidingObject(Ball ball) {
        if (getElementAt(ball.getX(), ball.getY()) != null) {
            return getElementAt(ball.getX(), ball.getY());
        } else if (getElementAt(ball.getX() + (2 * BALL_RADIUS), ball.getY()) != null) {
            return getElementAt(ball.getX() + (2 * BALL_RADIUS), ball.getY());
        } else if (getElementAt(ball.getX(), ball.getY() + (2 * BALL_RADIUS)) != null) {
            return getElementAt(ball.getX(), ball.getY() + (2 * BALL_RADIUS));
        } else if (getElementAt(ball.getX() + (2 * BALL_RADIUS), ball.getY() + (2 * BALL_RADIUS)) != null) {
            return getElementAt(ball.getX() + (2 * BALL_RADIUS), ball.getY() + (2 * BALL_RADIUS));
        }

        return null;
    }

    /**
     * This method creates a row and column of bricksArray
     * using two for loops
     */
    private void createBricksRowAndCol() {
        for (int i = 0; i < NBRICK_ROWS; i++) {
            for (int j = 0; j < NBRICKS_PER_ROW; j++) {
                Brick brick = new Brick(i, j, paddle);
                add(brick);
                bricksArray.add(brick);
            }
        }
    }

    /**
     * This method checks the contact of the paddle with the ball and the
     * balls with the bricksArray, it also counts the number of bricksArray
     * that have already knocked out and minus them
     */
    private void checkCollisions(Ball ball) {
        GObject collider = getCollidingObject(ball);
        if (collider == paddle && ball.getVelocityY() > 0 && (ball.getY() + ball.getHeight()) < paddle.getY() + ball.getVelocityY()) {
            ball.changeVelocityY();
        } else if (collider != null && bricksArray.contains(collider)) {

            sumOfCurrentScore += bricksArray.get(bricksArray.indexOf(collider)).getCost();
            System.out.println(sumOfCurrentScore);
            score.setLabel("YOUR SCORE: " + sumOfCurrentScore);
            remove(collider);
            bricksArray.remove(collider);
            if (random.nextFloat() < BONUS_DROP_CHANCE) {
                bonusBrick(collider);
            }
            ball.changeVelocityY();
        }
    }

    private void bonusBrick(GObject collider) {
        BonusBrick bonusBrick = new BonusBrick(collider.getX(), collider.getY(), paddle);
        add(bonusBrick);
        bonusBricksArray.add(bonusBrick);
    }

    /**
     * This method checks whether the ball flew below
     * the y-axis
     *
     * @return the ball coordinate check
     */
    private boolean ballsUnderDown() {
        for (int i = 0; i < ball.size(); i++) {
            if (ball.get(i).getY() + 2 * BALL_RADIUS >= getHeight()) {
                if (ball.size() == 1) {
                    return true;
                } else {
                    remove(ball.get(i));
                    ball.remove(i);
                    i--;
                }
            }
        }
        return false;
    }


    /**
     * This method checks how many attempts the player has left
     * and if the player loses an attempt, this method also subtracts it
     */
    private void isMissed() {
        if (ballsUnderDown()) {
            countOfLives--;
            countOfLivesMessage.setLabel("LIVES LEFT: " + countOfLives);
            remove(ball.get(0));
            if (countOfLives > 0) {
                GLabel toRemove = showMessageForGameMissed();
                waitForClick();
                remove(toRemove);
                createStartBall();
            }
        }
    }

    /**
     * This method checks whether the player has lost or won and
     * displays a message on the screen depending on the result
     */
    private void isGameWin() {
        if (countOfLives > 0) {
            for (Ball currentBall : ball) {
                remove(currentBall);
            }
            remove(paddle);
            showMessageForWinGame();
        } else {
            showMessageForGameOver();
            remove(ball.get(0));
        }
    }

    /**
     * This method sets the speed of the ball along the x-axis from 1 to 3
     * and along the y-axis, using a random generator is sets the speed for the
     * ball along the x-axis
     * Also this method starts the animation of the ball
     */
    private void ballAnimation() {
        waitForClick();
        while (countOfLives > 0 && bricksArray.size() > 0) {
            for (Ball currentBall : ball) {
                currentBall.moveBall();
                checkCollisions(currentBall);
            }
            moveBonuses();
            isMissed();
            pause(PAUSE_TIME);
        }
        isGameWin();
    }

    private void moveBonuses() {
        if (bonusBricksArray.size() == 0) return;

        for (int i = 0; i < bonusBricksArray.size(); i++) {
            BonusBrick current = bonusBricksArray.get(i);
            int effect = current.bonusBrickMove(paddle);
            if (effect == 1) {
                doTripleBall();
            }
            if (current.getY() > getHeight() || current.isCatched()) {
                remove(current);
                bonusBricksArray.remove(current);
            }
        }
    }
}