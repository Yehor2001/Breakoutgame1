package com.shpp.p2p.cs.ybilash.assignment4;

import acm.graphics.GOval;
import acm.util.RandomGenerator;

import java.awt.*;

public class Ball extends GOval implements BreakoutInterface {
    private double velocityX = 0;
    private double velocityY = 0;
    //This constant is mean screen width

    private final double screenWidth;
    RandomGenerator rgen = RandomGenerator.getInstance();

    public Ball(double x, double y, double radius, double screenWidth) {
        super(x, y, radius * 2, radius * 2);
        this.setColor(new Color(rgen.nextInt(0, 255), rgen.nextInt(0, 255), rgen.nextInt(0, 255)));
        this.setFilled(true);
        this.screenWidth = screenWidth;
        this.velocityX = rgen.nextDouble(RANDOM_VELOCITY_MIN, RANDOM_VELOCITY_MAX);
        this.velocityY = VELOCITY_Y;
    }


    public void setVelocityX(double velocityX) {
        this.velocityX = velocityX;
    }

    public double getVelocityX() {
        return velocityX;
    }

    public void setVelocityY(double velocityY) {
        this.velocityY = velocityY;
    }

    public double getVelocityY() {
        return velocityY;
    }

    public void changeVelocityY() {
        this.velocityY = -velocityY;
    }

    public void changeVelocityX() {
        this.velocityX = -velocityX;
    }

    public double getRadius() {
        return this.getHeight();
    }

    public void checkWalls() {
        if (this.getX() < 0 || this.getX() + (2 * BALL_RADIUS) > this.screenWidth) {
            this.changeVelocityX();
        }
        if (this.getY() < 0) {
            this.changeVelocityY();
        }
    }

    public void moveBall() {
        this.move(velocityX, velocityY);
        checkWalls();
    }
}
