package com.shpp.p2p.cs.ybilash.assignment4;

import acm.graphics.GRect;

import java.awt.*;

public class Brick extends GRect implements BreakoutInterface {

    private final int cost;

    public Brick(int row, int col, Paddle paddle) {
        super((double) (((BRICK_WIDTH + BRICK_SEP) * col) + (BRICK_SEP / 4.0)),
                (double) (((BRICK_HEIGHT + BRICK_SEP) * row) + BRICK_WIDTH),
                BRICK_WIDTH,
                BRICK_HEIGHT);
        this.setFilled(true);
        this.setColor(getBrickColor(row));
        this.cost = getBrickCost(row);
    }

    private int getBrickCost(int row) {
        return switch (row % NBRICKS_PER_ROW) {
            case 0, 1 -> 75;
            case 2, 3 -> 40;
            case 4, 5 -> 30;
            case 6, 7 -> 20;
            case 8, 9 -> 10;
            default -> 0;
        };
    }

    private Color getBrickColor(int row) {
        return switch (row % NBRICKS_PER_ROW) {
            case 0, 1 -> Color.RED;
            case 2, 3 -> Color.ORANGE;
            case 4, 5 -> Color.YELLOW;
            case 6, 7 -> Color.GREEN;
            case 8, 9 -> Color.CYAN;
            default -> null;
        };
    }

    public int getCost() {
        return cost;
    }
}
