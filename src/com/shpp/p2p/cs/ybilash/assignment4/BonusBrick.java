package com.shpp.p2p.cs.ybilash.assignment4;

import acm.graphics.GRect;
import acm.util.RandomGenerator;

public class BonusBrick extends GRect implements BreakoutInterface {
    private double velocityX;
    private double velocityY;
    private boolean catched = false;
    private String bonusName;
    int bonusId;
    RandomGenerator rgen = RandomGenerator.getInstance();

    public BonusBrick(double row, double col, Paddle paddle) {
        super(row, col, BRICK_WIDTH, BRICK_HEIGHT);
        this.bonusId = randomizeBonusId();
        fixDoubleBonuses(paddle);
        this.bonusName = BONUSES[bonusId];
        this.setFilled(true);
        this.setColor(BONUS_COLORS[bonusId]);
    }

    private int randomizeBonusId() {
        int[] id = getNewChancesArray();
        int counter = 0;
        for (int i = 0; i < BONUSES_CHANCES.length; i++) {
            for (int j = 0; j < BONUSES_CHANCES[i] * id.length; j++) {
                id[counter++] = i;
            }
        }
        return id[rgen.nextInt(0, id.length - 1)];
    }

    private static int[] getNewChancesArray() {

        int countOfNumbersAfterDot = 0;
        for (double number : BONUSES_CHANCES) {
            String numberToString = Double.toString(number);
            int currentValue = numberToString.length() - numberToString.indexOf('.') - 1;

            if (currentValue > countOfNumbersAfterDot) countOfNumbersAfterDot = currentValue;
        }
        return new int[(int) Math.pow(10, countOfNumbersAfterDot)];
    }

    public int bonusBrickMove(Paddle paddle) {
        //fixDoubleBonuses(paddle);
        this.move(0, rgen.nextDouble(BONUS_BRICK_VELOCITY_Y_MIN, BONUS_BRICK_VELOCITY_Y_MAX));
        return checkPaddleCollisions(paddle);
    }

    private void fixDoubleBonuses(Paddle paddle) {
        if (paddle.getColor().equals(BONUS_COLORS[bonusId])) {
            this.setLocation(0, APPLICATION_HEIGHT);
        }
    }

    private int checkPaddleCollisions(Paddle paddle) {
        boolean conditionX = this.getX() + BRICK_WIDTH >= paddle.getX() && this.getX() < paddle.getX() + paddle.getWidth();
        boolean conditionY = this.getY() + BRICK_HEIGHT >= paddle.getY() && this.getY() < paddle.getY() + paddle.getHeight();
        if (conditionX && conditionY) {
            if (bonusName.equals("Expansion") ) {
                paddle.setColor(this.getColor());
                paddle.expandPaddleWidth();
            } else if (bonusName.equals("Reduction")) {
                paddle.setColor(this.getColor());
                paddle.reducePaddleWidth();
            } else if (bonusName.equals("NormalSize")) {
                paddle.setColor(this.getColor());
                paddle.returnNormalSize();
            }else if(bonusName.equals("TripleBall")){
                catched = true;
                return 1;
            }
            catched = true;
        }
        return 0;
    }

    public boolean isCatched() {
        return catched;
    }
}
