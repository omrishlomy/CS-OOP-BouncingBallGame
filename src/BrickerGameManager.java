import bricker.brick_strategies.CollisionStrategy;
import bricker.brick_strategies.CollisionStrategyFactory;
import bricker.gameobjects.Ball;
import bricker.gameobjects.Paddle;
import bricker.brick_strategies.Brick;
import danogl.GameManager;
import danogl.GameObject;
import danogl.collisions.GameObjectCollection;
import danogl.collisions.Layer;
import danogl.gui.*;
import danogl.gui.rendering.Renderable;
import danogl.util.Vector2;


public class BrickerGameManager extends GameManager{
 	 private static final int marginX = 10;
	 private static final int marginY = 10;
	 private static final int spacing = 5;
	 private static final int brickHeight = 15;
	 private static float brickWidth;
	 private int bricksInRow = 8;
	 private int bricksInCol = 7;
	 private float RUNNER_HIGHT = 500;
	 private float RUNNER_WIDTH = 700;

	 private void createWalls(){
	  //Walls
	  GameObject leftWall = new GameObject(Vector2.ZERO, new Vector2(spacing, RUNNER_HIGHT),null);
	  GameObject rightWall = new GameObject(new Vector2(RUNNER_WIDTH,0), new Vector2(spacing, RUNNER_HIGHT),null);
	  gameObjects().addGameObject(leftWall);
	  gameObjects().addGameObject(rightWall);
	 }
	 private void createClassicBricks(ImageReader imageReader){
	  Renderable brickImage = imageReader.readImage("assets/brick.png", true);
	  GameObjectCollection gameObjectCollection = gameObjects();
	  CollisionStrategy strategy = new CollisionStrategyFactory().createStrategyFactory(gameObjectCollection,imageReader,"Basic");
	  for (int row = 0; row < bricksInCol; row++) {
	   for (int col = 0; col < bricksInRow; col++) {

		float x = marginX + col * (brickWidth + spacing);
		float y = marginY + row * (brickHeight + spacing);

		Brick brick = new Brick(
				new Vector2(x, y),
				new Vector2(brickWidth, brickHeight),
				brickImage,
				strategy
		);

		gameObjects().addGameObject(brick);
	   }
	  }

	 }
	 private void createBackground(ImageReader imageReader){
	  Renderable backgroundImage = imageReader.readImage("assets/DARK_BG2_small.jpeg", true);
	  GameObject background = new GameObject(Vector2.ZERO, new Vector2(RUNNER_WIDTH, RUNNER_HIGHT), backgroundImage);
	  gameObjects().addGameObject(background,Layer.BACKGROUND);
	 }
     public BrickerGameManager(String windowTitle, Vector2 windowDimensions,int brickInRow,int bricksInCol) {
        super(windowTitle, windowDimensions);
		RUNNER_HIGHT = windowDimensions.y();
		RUNNER_WIDTH = windowDimensions.x();
		this.bricksInRow = brickInRow;
		this.bricksInCol = bricksInCol;
	 	brickWidth = (RUNNER_WIDTH - 2 * marginX - (bricksInRow - 1) * spacing) / bricksInRow;
     }

    // this is just to check the code NEED TO CHANGE !!!!!!
    @Override
    public void initializeGame(ImageReader imageReader, SoundReader soundReader, UserInputListener inputListener, WindowController windowController) {
        super.initializeGame(imageReader, soundReader, inputListener, windowController);
        // ball
        Renderable ballImage = imageReader.readImage("assets/ball.png", true);
        Sound collisionSound = soundReader.readSound("assets/blop.wav");
        GameObject ball = new Ball(Vector2.ZERO, new Vector2(50, 50), ballImage, collisionSound);
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

		//Bricks
	 	createClassicBricks(imageReader);
		//Walls
		createWalls();
		//Background
	 	createBackground(imageReader);

    }

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
 }