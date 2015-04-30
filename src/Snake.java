/**
 * Created by Alexander Goldberg (alexander.goldberg25@uga.edu) on 4/19/15.
 * Tracks snake position;
 */
public class Snake {
    private static int[] arrayX = {10, 11, 12, 13, 14};
    private static int[] arrayY = {10, 10, 10, 10, 10};

    private static Food food = new Food();

    //Adds entry to begining
    private static void enqueue(int x, int y) {
        int[] newX = new int[arrayX.length + 1];
        int[] newY = new int[arrayY.length + 1];
        newX[0] = x;
        newY[0] = y;

        for (int i = 0; i < arrayX.length; i++) {
            newX[i + 1] = arrayX[i];
            newY[i + 1] = arrayY[i];
        }

        arrayX = newX;
        arrayY = newY;
    }

    private static void dequeu() {
        int[] newX = new int[arrayX.length - 1];
        int[] newY = new int[arrayY.length - 1];

        for (int i = 0; i < newX.length; i++) {
            newX[i] = arrayX[i];
            newY[i] = arrayY[i];
        }


        arrayY = newY;
        arrayX = newX;


    }

    public static void move(int x, int y) {
        if (x >= 20 || x < 0 || y >= 20 || y < 0) {
            System.out.println("Collision");
            reset();
            Controller.animationTimer.stop();
            return;
        }

        for (int i = 0; i < arrayX.length; i++) {
            if (arrayX[i] == x && arrayY[i] == y) {
                System.out.println("Collision");
                reset();
                Controller.animationTimer.stop();
                return;
            }
        }
        if (food.foodPos()[0] == x && food.foodPos()[1] == y) {
            food = new Food();
            enqueue(x, y);
        } else {
            enqueue(x, y);
            dequeu();
        }
    }

    public static void eat(int x, int y) {
        enqueue(x, y);
    }


    public static boolean snakeAt(int x, int y) {
        for (int i = 0; i < arrayX.length; i++) {
            if (arrayX[i] == x && arrayY[i] == y) {
                return true;
            }
        }
        return false;
    }


    //Together return a full set of data points
    public static int[] snakeX() {
        return arrayX.clone();
    }

    public static int[] snakeY() {
        return arrayY.clone();
    }

    //returns a single x,y point
    public static int[] snakeIXY(int i) {
        if (i < snakeX().length) {
            int[] copy = {arrayX[i], arrayY[i]};
            return copy;
        } else {
            throw new IndexOutOfBoundsException();
        }
    }

    public static int length() {
        return arrayX.length;
    }

    public static void reset() {
        int[] tempX = new int[5];
        int[] tempY = new int[5];

        //  arrayX = {10, 11, 12, 13, 14};
        //  arrayY = {10, 10, 10, 10, 10};
        for (int i = 0; i < tempX.length; i++) {
            tempX[i] = 10 + i;
            tempY[i] = 10;
        }
        arrayX = tempX;
        arrayY = tempY;
        Controller.dirReset();
    }

    public static Food snakeFood() {
        return food;
    }

    @Override
    public String toString() {
        String string = "Snake = {";
        for (int i = 0; i < arrayX.length; i++) {
            string = string + "(" + arrayX[i] + "," + arrayY[i] + ")";
        }
        string = string + "}";
        return string;
    }


}



