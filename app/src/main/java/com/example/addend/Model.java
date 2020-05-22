package com.example.addend;

import java.util.*;

public class Model {

    private int topNumber, bottomNumber, topUpperBound, bottomUpperBound;
    private static char operation = '+';
    private static String gameVersion;
    private static boolean stillGoing = true;
    Random random = new Random();

    public Model() {
        topUpperBound = (operation == '*') ? 10 : 100;
        bottomUpperBound = operation == '+' ? 100 : operation == '-' ? topUpperBound - 1 : 9;
        changeTopNumber();
        changeBottomNumber();
    }

    public int getTopNumber() {
        return topNumber;
    }

    public int getBottomNumber() {
        return bottomNumber;
    }

    public static char getOperation() {
        return operation;
    }

    public static void setOperation(char newVal) {
        switch (newVal) {
            case 'A':
                operation = '+';
                break;
            case 'S':
                operation = '-';
                break;
            case 'M':
                operation = '*';
                break;
            default:
                throw new IllegalArgumentException("No valid operator!");
        }
    }

    public int returnExpectedResult(int first, int second) {
        switch (operation) {
            case '+':
                return first + second;
            case '-':
                return first - second;
            case '*':
                return first * second;
            default:
                throw new IllegalArgumentException("Now valid operator!");
        }
    }

    public static String getGameVersion(){
        return gameVersion;
    }

    public static void setGameVersion(String version){
        gameVersion = version;
    }

    public boolean isAnswerCorrect(int input) {
        if (gameVersion.equals("Infinite") && !(returnExpectedResult(topNumber, bottomNumber) == input)) {
            stillGoing = false;
        }
        return (returnExpectedResult(topNumber, bottomNumber) == input);
    }

    public static boolean isStillGoing() {
        return stillGoing;
    }

    public static void changeStillGoing(boolean value){
        stillGoing = value;
    }

    public int changeTopNumber() {
        topNumber = random.nextInt(topUpperBound) + 1;
        return topNumber;
    }

    public int changeBottomNumber() {
        bottomNumber = random.nextInt(bottomUpperBound) + 1;
        return bottomNumber;
    }

}
