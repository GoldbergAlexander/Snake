/**
 * Created by Alexander Goldberg (alexander.goldberg25@uga.edu) on 4/18/15.
 * This File handles interactions with the the View
 */

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.KeyEvent;
import javafx.util.Duration;

import java.util.ResourceBundle;


public class Controller implements Initializable {

    protected static Timeline animationTimer;
    protected static Direction direction = Direction.WEST;
    @FXML
    protected Label scoreLabel;
    @FXML
    protected Button startButton;
    @FXML
    protected Canvas gameCanvas;

    public static void dirReset() {
        direction = Direction.WEST;
    }

    @FXML
    protected void startButtonHandler(ActionEvent event) {
        Snake.reset();
        animationTimer.play();
    }

    @FXML
    protected void borderPaneKeyPressed(KeyEvent event) {
        switch (event.getCode()) {
            case UP:
                direction = Direction.NORTH;
                break;
            case LEFT:
                direction = Direction.WEST;
                break;
            case DOWN:
                direction = Direction.SOUTH;
                break;
            case RIGHT:
                direction = Direction.EAST;
                break;
        }
    }

    @Override
    public void initialize(java.net.URL args0, ResourceBundle args1) {
        DrawGame.drawGrid(gameCanvas);
        animationTimer = new Timeline(new KeyFrame(
                Duration.millis(500),
                ae -> timerWork()));
        animationTimer.setCycleCount(Animation.INDEFINITE);

    }

    private void timerWork() {
        DrawGame.clear(gameCanvas);
        DrawGame.drawGrid(gameCanvas);
        snakeMoveHandler();
        DrawGame.drawSnake(gameCanvas);
        DrawGame.drawFood(gameCanvas, Snake.snakeFood().foodPos()[0], Snake.snakeFood().foodPos()[1]);
        scoreLabel.setText(String.valueOf(Snake.length() - 5));
    }

    private void snakeMoveHandler() {
        switch (direction) {
            case EAST:
                Snake.move(Snake.snakeIXY(0)[0] + 1, Snake.snakeIXY(0)[1]);
                break;
            case WEST:
                Snake.move(Snake.snakeIXY(0)[0] - 1, Snake.snakeIXY(0)[1]);
                break;
            case SOUTH:
                Snake.move(Snake.snakeIXY(0)[0], Snake.snakeIXY(0)[1] + 1);
                break;
            case NORTH:
                Snake.move(Snake.snakeIXY(0)[0], Snake.snakeIXY(0)[1] - 1);
                break;
        }
    }

    public enum Direction {
        NORTH, SOUTH, EAST, WEST
    }
}
