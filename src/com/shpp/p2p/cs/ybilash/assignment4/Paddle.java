package com.shpp.p2p.cs.ybilash.assignment4;

import acm.graphics.GRect;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class Paddle extends GRect implements BreakoutInterface, MouseListener {

    private final double screenWidth;
    private final double screenHeight;

    public Paddle(double v, double v1) {
        super(v / 2.0 - PADDLE_WIDTH / 2.0, v1 - (PADDLE_Y_OFFSET + PADDLE_HEIGHT), PADDLE_WIDTH, PADDLE_HEIGHT);
        this.setFilled(true);
        this.setColor(Color.BLACK);
        addMouseListener(this);
        this.screenWidth = v;
        this.screenHeight = v1;
    }


    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {}

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    public void expandPaddleWidth() {
        this.setSize(PADDLE_WIDTH * 2, PADDLE_HEIGHT);
    }

    public void reducePaddleWidth() {
        this.setSize(PADDLE_WIDTH / 2.0, PADDLE_HEIGHT);
    }

    public void returnNormalSize(){
        this.setSize(PADDLE_WIDTH, PADDLE_HEIGHT);
    }
}
