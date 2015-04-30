/**
 * Created by Alexander Goldberg (alexander.goldberg25@uga.edu), and Michael Kovalsky on 4/29/15.
 * file contains static drawing functions
 */

import com.sun.corba.se.impl.orbutil.graph.Graph;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class DrawGame {
    public static void drawGrid(Canvas canvas) {
        GraphicsContext gc = canvas.getGraphicsContext2D();

        gc.setStroke(Color.BLACK);
        gc.setLineWidth(.25);

        //grid settings
        int NUM_COLS = 20;
        int NUM_ROWS = 20;

        int BLOCK_SIZE = 20;

        //Draw vertical lines
        for (int i = 1; i < NUM_COLS; i++)
            gc.strokeLine(i * BLOCK_SIZE, 0, i * BLOCK_SIZE, NUM_ROWS * BLOCK_SIZE);

        //Draw horizontal lines
        for (int i = 1; i < NUM_ROWS; i++)
            gc.strokeLine(0, i * BLOCK_SIZE, NUM_COLS * BLOCK_SIZE, i * BLOCK_SIZE);

        gc.setLineWidth(2);
        gc.rect(1, 1, canvas.getHeight() - 2, canvas.getWidth() - 2);
        gc.stroke();

    }

    public static void fillSquare(Canvas canvas, int x, int y) {
        GraphicsContext gc = canvas.getGraphicsContext2D();
        //Assuming block size of 20
        double BLOCK_SIZE = 20;

        //calc position
        double startX = x * 20;
        double startY = y * 20;
        gc.setFill(Color.RED);
        gc.fillRect(startX, startY, BLOCK_SIZE, BLOCK_SIZE);

    }

    //draws a circular food.
    public static void drawCircleFood(Canvas canvas, int x, int y)
    {
        GraphicsContext gc = canvas.getGraphicsContext2D();

        double CIRCLE_SIZE = 20;

        double startX = x * 20;
        double startY = y * 20;
        gc.setFill(Color.GREEN);
        gc.fillOval(startX, startY, CIRCLE_SIZE, CIRCLE_SIZE);
    }

    //draws the snake.
    public static void drawSnake(Canvas canvas) {
        for (int i = 0; i < Snake.snakeX().length; i++) {
            fillSquare(canvas, Snake.snakeX()[i], Snake.snakeY()[i]);
        }

    }

    //We must reset the canvas each time to prevent artifacts staying on the screen

    public static void clear(Canvas canvas) {
        GraphicsContext gc = canvas.getGraphicsContext2D();
        gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
    }

}
