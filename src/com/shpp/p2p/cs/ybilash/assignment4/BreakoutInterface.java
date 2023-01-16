package com.shpp.p2p.cs.ybilash.assignment4;

import java.awt.*;

public interface BreakoutInterface {
    boolean PADDLE_TEST = true;
    /**
     * Width and height of application window in pixels
     */
    public static final int APPLICATION_WIDTH = 400;
    public static final int APPLICATION_HEIGHT = 600;

    /**
     * Dimensions of game board (usually the same)
     */
    static final int WIDTH = APPLICATION_WIDTH;
    static final int HEIGHT = APPLICATION_HEIGHT;

    /**
     * Dimensions of the paddle
     */
    static final int TRIPLE_BALLS_COUNT = 3;
    static final int PADDLE_WIDTH = 60;
    static final int PADDLE_HEIGHT = 10;

    /**
     * Offset of the paddle up from the bottom
     */
    static final int PADDLE_Y_OFFSET = 30;

    /**
     * Number of bricks per row
     */
    static final int NBRICKS_PER_ROW = 10;

    /**
     * Number of rows of bricks
     */
    static final int NBRICK_ROWS = 10;

    /**
     * Separation between bricks
     */
    static final int BRICK_SEP = 3;

    /**
     * Width of a brick
     */
    static final int BRICK_WIDTH = (WIDTH - (NBRICKS_PER_ROW - 1) * BRICK_SEP) / NBRICKS_PER_ROW;

    /**
     * Height of a brick
     */
    static final int BRICK_HEIGHT = 8;

    /**
     * Radius of the ball in pixels
     */
    static final int BALL_RADIUS = 10;

    /**
     * Offset of the top brick row from the top
     */
    static final int BRICK_Y_OFFSET = 70;

    /**
     * Number of turns
     */
    static final int NTURNS = 3;

    /**
     * This variable indicates the number of frames per second
     */
    static final double PAUSE_TIME = 1000 / 60.0;
    /**
     * This constant is mean max score for one brick
     */
    double RANDOM_VELOCITY_MIN = 1.0;
    double RANDOM_VELOCITY_MAX = 3.0;
    double VELOCITY_Y = 3.0;
    double BONUS_BRICK_VELOCITY_Y_MIN = 0.5;
    double BONUS_BRICK_VELOCITY_Y_MAX = 3.5;
    /**
     * BONUSES CHANCE GENERATION
     */

    double BONUS_DROP_CHANCE = 0.5;
    String[] BONUSES = {"NormalSize", "Expansion", "Reduction", "TripleBall"};
    double[] BONUSES_CHANCES = {0.1, 0.1, 0.1, 0.7};
    Color[] BONUS_COLORS = {Color.BLACK, Color.GREEN, Color.RED, Color.PINK};
}