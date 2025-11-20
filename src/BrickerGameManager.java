import bricker.gameobjects.Ball;
import bricker.gameobjects.Paddle;
import danogl.GameManager;
import danogl.GameObject;
import danogl.gui.*;
import danogl.gui.rendering.Renderable;
import danogl.util.Vector2;

import java.util.Random;


public class BrickerGameManager extends GameManager{

    public BrickerGameManager(String windowTitle, Vector2 windowDimensions) {
        super(windowTitle, windowDimensions);
    }

    // this is just to check the code NEED TO CHANGE !!!!!!
    @Override
    public void initializeGame(ImageReader imageReader, SoundReader soundReader, UserInputListener inputListener, WindowController windowController) {
        super.initializeGame(imageReader, soundReader, inputListener, windowController);
        // ball
        Renderable ballImage = imageReader.readImage("assets/ball.png", true);
        Sound collisionSound = soundReader.readSound("assets/blop.wav");
        GameObject ball = new Ball(new Vector2(0,0), new Vector2(50, 50), ballImage, collisionSound);
        ball.setVelocity(Vector2.DOWN.mult(100));
        ball.setCenter(windowController.getWindowDimensions().mult(0.5F));
        gameObjects().addGameObject(ball);

        // paddle
        Renderable paddleImage = imageReader.readImage("assets/Paddle.png", true);
        GameObject userPaddle = new Paddle(
                Vector2.ZERO,
                new Vector2(100, 10),
                paddleImage,
                inputListener);

        userPaddle.setCenter(
                new Vector2(windowController.getWindowDimensions().x()/2,
                        (int)windowController.getWindowDimensions().y()-30));
        gameObjects().addGameObject(userPaddle);
    }

    public static void main(String[] args) {
       GameManager gameManager =new BrickerGameManager(" gameManager example =",new Vector2(700,500));
       gameManager.run();
      }
 }