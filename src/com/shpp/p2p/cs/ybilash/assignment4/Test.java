package com.shpp.p2p.cs.ybilash.assignment4;
import acm.util.RandomGenerator;

import com.shpp.cs.a.graphics.WindowProgram;

public class Test extends WindowProgram {
    private static final String[] RESULTS = {"A", "B", "C"};
    private static final double[] CHANCES = {0.80, 0.10, 0.10};
    private static final int TRY = 1;

    public static void main(String[] args) {
        RandomGenerator rg = RandomGenerator.getInstance();
        for (int i = 0; i < TRY; i++) System.out.println(RESULTS[getRandomId(rg)]);
    }

    private static int getRandomId(RandomGenerator rg) {

        int[] id = getNewChancesArray(); //for example, we have here 100
        int counter = 0;

        for (int i = 0; i < CHANCES.length; i++)
            for (int j = 0; j < CHANCES[i] * id.length; j++)
                id[counter++] = i;
        System.out.println(counter);

        /*
         * for(int i = 1; i < 3; i++){
         *      for(int j = 0; j < 10; j++){
         *      id[0] = 1;
         *      }
         * }
         */
        return id[rg.nextInt(0, id.length - 1)];// тут рандомно нам геренує id
    }

    private static int[] getNewChancesArray() {

        int countOfNumbersAfterDot = 0;//кількість цифер після крапки
        for (double number : CHANCES) {
            String numberToString = Double.toString(number);
             System.out.println(numberToString);
            int currentValue = numberToString.length() - numberToString.indexOf('.') - 1;//рахує кількість цифер після коми
            //numberToSting = "0.8"                                                      //-1 тут для того щоб копменсувати те що рахунок символів в масиві починається з нуля
            //(0.8) currentValue = 3 - 1 -1= 1;
            if (currentValue > countOfNumbersAfterDot) countOfNumbersAfterDot = currentValue;
            //якщо 1 > 0 тоді countOfNumberAfterDot = 1
        }

        return new int[(int) Math.pow(10, countOfNumbersAfterDot)]; // (10^2 = 100) * (chance 0.01) = 10%
        // return new int[10000]
    }
}
