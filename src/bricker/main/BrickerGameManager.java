package bricker.main;
import bricker.brick_strategies.CollisionStrategy;
import bricker.brick_strategies.CollisionStrategyFactory;
import bricker.brick_strategies.NonBrickStrategy;
import bricker.gameobjects.*;
import danogl.GameManager;
import danogl.GameObject;
import danogl.collisions.Layer;
import danogl.components.GameObjectPhysics;
import danogl.gui.*;
import danogl.gui.rendering.Renderable;
import danogl.util.Vector2;

import java.awt.event.KeyEvent;

/**
 * class for the Bricker game manager
 */
public class BrickerGameManager extends GameManager{
	 private static final float PADDLE_WIDTH=100;
 	 private static final float PADDLE_HEIGHT=10;
	  private static final int EXTRA_PADDLE_HITS=4;
      private static final int ALLOWED_NUM_EXTRA_PADDLES=1;
      private static final int MAX_NUM_LIVES = 4;
	  private static final int INIT_NUM_LIVES = 3;
      private static final Vector2 HEART_DIMENSIONS=new Vector2(30, 30);
      private static final String LOSE_STRING = "You lose! Play again?";
    private static final String WIN_STRING = "You won! Play again?";
 	 private static final int marginX = 10;
	 private static final int marginY = 10;
	 private static final int spacing = 5;
	 private static final int brickHeight = 15;
	 private static float brickWidth;
	 private float RUNNER_HEIGHT = 500;
	 private float RUNNER_WIDTH = 700;
	 private int colsNum = 1;
	 private int rowsNum = 1;
     private boolean ballOut = false;
	 private ImageReader imageReader;
	 private SoundReader soundReader;
	 private WindowController windowController;
	 private UserInputListener inputListener;
	 private Renderable ballImage;
	 private Renderable brickImage;
	 private Renderable paddleImage;
	 private Renderable puckImage;
	 private Renderable lifeImage;
	 private Renderable backgroundImage;
	 private Sound collisionSound;
	 private Sound explosionSound;
	 private Brick[][] bricksGrid;
     private Ball ball;
     private LifeCounters lifeCounters;
     private int activeBricks = 0;


	 private void createWalls(){
	  //Walls
	  GameObject leftWall = new GameObject(Vector2.ZERO, new Vector2(spacing, RUNNER_HEIGHT),null);
	  leftWall.setTag("LeftWall");
	  GameObject rightWall = new GameObject(new Vector2(RUNNER_WIDTH,0),
              new Vector2(spacing, RUNNER_HEIGHT),null);
	  rightWall.setTag("RightWall");
	  GameObject upperWall = new GameObject(Vector2.ZERO, new Vector2(RUNNER_WIDTH, spacing),null);
	  upperWall.setTag("UpperWall");
      GameObject bottomWall = new GameObject(new Vector2(0, RUNNER_HEIGHT + 100),
              new Vector2(RUNNER_WIDTH, spacing),null);
      leftWall.physics().setMass(GameObjectPhysics.IMMOVABLE_MASS);
      bottomWall.setTag("BottomWall");
      rightWall.physics().setMass(GameObjectPhysics.IMMOVABLE_MASS);
	  gameObjects().addGameObject(leftWall,Layer.STATIC_OBJECTS);
	  gameObjects().addGameObject(rightWall,Layer.STATIC_OBJECTS);
	  gameObjects().addGameObject(upperWall,Layer.STATIC_OBJECTS);
      gameObjects().addGameObject(bottomWall,Layer.STATIC_OBJECTS);
	 }
	 private void createClassicBricks(){
	  CollisionStrategyFactory strategyFactory = new CollisionStrategyFactory(this);
	  for (int row = 0; row < rowsNum; row++) {
	   for (int col = 0; col < colsNum; col++) {

	  CollisionStrategy strategy = strategyFactory.createStrategy();
		float x = marginX + col * (brickWidth + spacing);
		float y = marginY + row * (brickHeight + spacing);

		Brick brick = new Brick(
				new Vector2(x, y),
				new Vector2(brickWidth, brickHeight),
				brickImage,
				strategy,
				row,
				col
		);
		bricksGrid[row][col] = brick;
		gameObjects().addGameObject(brick);
        activeBricks++;
	   }
	  }

	 }
	 private void createBackground(){
	  GameObject background = new GameObject(Vector2.ZERO, new Vector2(RUNNER_WIDTH, RUNNER_HEIGHT), backgroundImage);
	  gameObjects().addGameObject(background,Layer.BACKGROUND);
	 }
	 private void createBall(){
        Ball ball = new Ball(Vector2.ZERO, new Vector2(50, 50), ballImage, collisionSound);
        ball.setCenter(windowController.getWindowDimensions().mult(0.5F));
        gameObjects().addGameObject(ball);
        this.ball = ball;

	 }
	 private void createPaddle(){
        GameObject userPaddle = new Paddle(
                Vector2.ZERO,
                new Vector2(PADDLE_WIDTH, PADDLE_HEIGHT),
                paddleImage,
                inputListener);
        userPaddle.setCenter(
                new Vector2(windowController.getWindowDimensions().x()/2,
                        (int)windowController.getWindowDimensions().y()-30));
        gameObjects().addGameObject(userPaddle);
	 }

 /**
  * Constructor
  * @param windowTitle
  * @param windowDimensions
  * @param numCols
  * @param numRows
  */
     public BrickerGameManager(String windowTitle, Vector2 windowDimensions,int numCols,int numRows) {
        super(windowTitle, windowDimensions);
		RUNNER_HEIGHT = windowDimensions.y();
		RUNNER_WIDTH = windowDimensions.x();
		this.colsNum = numCols;
		this.rowsNum = numRows;
	 	brickWidth = (RUNNER_WIDTH - 2 * marginX - (colsNum - 1) * spacing) / colsNum;
		bricksGrid = new Brick[numRows][numCols];
     }

 /**
  * method called when the game ends
  * @param lose
  */
     public void endGame(boolean lose){
         String toPrint = LOSE_STRING;
         if(!lose){
             toPrint =WIN_STRING;
         }
         if (windowController.openYesNoDialog(toPrint)){
             windowController.resetGame();
             return;
         }
         windowController.closeWindow();
     }

    /**
     * updates the internal state of the game each frame. check for wins and losses.
     * @param deltaTime- time for updating a frame.
     */
    @Override
    public void update(float deltaTime) {
        super.update(deltaTime);
        // check for W press for instant win, or no more active bricks so true win
        if (inputListener.isKeyPressed(KeyEvent.VK_W) || activeBricks == 0) {
            boolean lose = false;
            endGame(lose);
        }
        // if the ball fell down, remove a life
        double ballHeight = ball.getCenter().y();
        // make sure we only remove one life per strike, and not per frame in which the ball is out.
        if (ballHeight > RUNNER_HEIGHT && !ballOut) {
            ballOut = true;
            lifeCounters.loseLife();
            // set ball back in center and start randomly
            ball.setCenter(windowController.getWindowDimensions().mult(0.5F));
            ball.initVelocity();
        }
        if (ball.getCenter().y() <= RUNNER_HEIGHT) {
            ballOut = false;
        }
    }

 /**
  * Game initiallizer, init the game manager fields and create the objects
  * @param imageReader
  * @param soundReader
  * @param inputListener
  * @param windowController
  */
    @Override
    public void initializeGame(ImageReader imageReader, SoundReader soundReader, UserInputListener inputListener, WindowController windowController) {
        super.initializeGame(imageReader, soundReader, inputListener, windowController);
		this.inputListener = inputListener;
		this.windowController = windowController;
		this.imageReader = imageReader;
		this.soundReader = soundReader;
		this.ballImage = imageReader.readImage("assets/ball.png", true);
	    this.brickImage = imageReader.readImage("assets/brick.png", true);
	    this.backgroundImage = imageReader.readImage("assets/DARK_BG2_small.jpeg",
                false);
		this.paddleImage = imageReader.readImage("assets/paddle.png", true);
		this.lifeImage = imageReader.readImage("assets/heart.png", true);
		this.puckImage = imageReader.readImage("assets/mockBall.png", true);
		this.collisionSound = soundReader.readSound("assets/blop.wav");
		this.explosionSound = soundReader.readSound("assets/explosion.wav");
        // ball
		createBall();
        // paddle
		createPaddle();
		//Bricks
	 	createClassicBricks();
		//Walls
		createWalls();
		//Background
	 	createBackground();
         // life counters
        this.lifeCounters = new LifeCounters(Vector2.ZERO, Vector2.ZERO, this.lifeImage,INIT_NUM_LIVES, MAX_NUM_LIVES,
                this);
    }
	public void createPuck(Vector2 location){
        //50 is the ball size, need to replace with constants
         Puck firstPuck = new Puck(location,new Vector2(50,50),puckImage,collisionSound, this);
	 firstPuck.initVelocity();
	 gameObjects().addGameObject(firstPuck);
	}

    /**
     * adds extra paddle to the game
     */
	public void createExtraPaddle(){
         // if we already at the allowed number of paddles, do nothing
        if (ExtraPaddle.getPaddlesNum() == ALLOWED_NUM_EXTRA_PADDLES){
            return;
        }
    // else, create an extra paddle and add to play.
	 Vector2 paddleLocation = new Vector2(windowController.getWindowDimensions().x()/2,
			 windowController.getWindowDimensions().y()/2);
	 ExtraPaddle extraPaddle = new ExtraPaddle(paddleLocation,new Vector2(PADDLE_WIDTH,PADDLE_HEIGHT),paddleImage,
			 inputListener,
			 EXTRA_PADDLE_HITS,
			 new NonBrickStrategy(this));
	 addGameObject(extraPaddle);
	}

    /**
     * add a falling heart that gives the use a life
     * @param location start position for the falling heart/
     */
     public void createLife(Vector2 location){
      GameObject fallingHeart = new AddLifeHeart(location, HEART_DIMENSIONS, lifeImage,
              new NonBrickStrategy(this), this); // creates the object
      addGameObject(fallingHeart); // adds to game
     }

    /**
     * getter for sound reader
     * @return sound reader
     */
     public SoundReader getSoundReader() {
      return soundReader;
     }

    /**
     * getter for image reader
     * @return getter for image reader
     */
     public ImageReader getImageReader() {
      return imageReader;
     }

    /**
     * getter for window controller
     * @return window controller
     */
     public WindowController getWindowController() {
      return windowController;
     }

    /**
     * getter for input listener
     * @return input listener
     */
     public UserInputListener getUserInputListener() {
      return inputListener;
     }

    /**
     * getter for explosion sound
     * @return explosion sound
     */
     public Sound getExplosionSound() {
      return explosionSound;
     }

    /**
     * getter for bricks from the grid
     * @param row- row in the grid of the wanted brick
     * @param col- col in the grid of the wanted brick
     * @return object in the grid in position [row,col]
     */
     public Brick getBrick(int row,int col){
          if(row<0 || row>=rowsNum || col<0 || col>= colsNum){
           return null;
          }
          return bricksGrid[row][col];
     }

    /**
     * set a brick
     * @param row - row in the grid of the wanted brick
     * @param col- col in the grid of the wanted brick
     * @param brick- the brick we want to insert in position [row, col] in the grid
     */
     public void setBrick(int row,int col,Brick brick){
              this.bricksGrid[row][col]= brick;
         }

    /**
     * adds a game object to the game
     * @param gameObject- object to add
     */
     public void addGameObject(GameObject gameObject){
        gameObjects().addGameObject(gameObject);
     }

    /**
     * adds a game object to the game
     * @param gameObject- object to add
     * @param layer- layer to add to.
     */
    public void addGameObject(GameObject gameObject, int layer){
        gameObjects().addGameObject(gameObject, layer);
    }

    /**
     * removes an object from the game
     * @param gameObject-the object to remove
     */
     public void removeGameObject(GameObject gameObject){
          gameObjects().removeGameObject(gameObject);
     }

    /**
     * removes an object from the game
     * @param gameObject-the object to remove
     * @param layer- the layer to remove from.
     */
    public void removeGameObject(GameObject gameObject, int layer){
        gameObjects().removeGameObject(gameObject, layer);
    }

    /**
     * main function, start running the game
     * @param args - command line given arguments
     */
    public static void main(String[] args) {
	  if (args.length>=2){
	   int rows = Integer.parseInt(args[0]);
	   int cols = Integer.parseInt(args[1]);
       GameManager gameManager =new BrickerGameManager(" gameManager example =",new Vector2(700,500),rows,cols);
	   gameManager.run();
	  }
	  else{
	   GameManager gameManager =new BrickerGameManager(" gameManager example =",new Vector2(700,500),8,7);
	   gameManager.run();
	  }
      }

    /**
     * getter for active bricks
     * @return active bricks
     */
    public int getActiveBricks() {
        return activeBricks;
    }

    /**
     * setter for active bricks
     * @param activeBricks- number of active bricks
     */
    public void setActiveBricks(int activeBricks) {
        this.activeBricks = activeBricks;
    }

    /**
     * adds a life to the user.
     */
    public void addLife(){
         lifeCounters.addLife();
    }
}