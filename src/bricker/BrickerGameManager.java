package bricker;
import bricker.brick_strategies.BasicCollisionStrategy;
import bricker.brick_strategies.CollisionStrategy;
import bricker.brick_strategies.CollisionStrategyFactory;
import bricker.gameobjects.*;
import danogl.GameManager;
import danogl.GameObject;
import danogl.collisions.GameObjectCollection;
import danogl.collisions.Layer;
import danogl.components.GameObjectPhysics;
import danogl.gui.*;
import danogl.gui.rendering.Renderable;
import danogl.util.Border;
import danogl.util.Vector2;


public class BrickerGameManager extends GameManager{
     private static final float BALL_VELOCITY=250;
	 private static final float PADDLE_WIDTH=100;
 	 private static final float PADDLE_HEIGHT=10;
	  private static final int EXTRA_PADDLE_HITS=4;
 	 private static final int marginX = 10;
	 private static final int marginY = 10;
	 private static final int spacing = 5;
	 private static final int brickHeight = 15;
	 private static float brickWidth;
	 private float RUNNER_HIGHT = 500;
	 private float RUNNER_WIDTH = 700;
	 private int colsNum = 8;
	 private int rowsNum = 7;
	 private int paddlesNum = 1;
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



	 private void createWalls(){
	  //Walls
	  GameObject leftWall = new GameObject(Vector2.ZERO, new Vector2(spacing, RUNNER_HIGHT),null);
	  leftWall.setTag("LeftWall");
	  GameObject rightWall = new GameObject(new Vector2(RUNNER_WIDTH,0), new Vector2(spacing, RUNNER_HIGHT),null);
	  rightWall.setTag("RightWall");
	  GameObject upperWall = new GameObject(Vector2.ZERO, new Vector2(RUNNER_WIDTH, spacing),null);
	  upperWall.setTag("Wall");
      leftWall.physics().setMass(GameObjectPhysics.IMMOVABLE_MASS);
      rightWall.physics().setMass(GameObjectPhysics.IMMOVABLE_MASS);
	  gameObjects().addGameObject(leftWall);
	  gameObjects().addGameObject(rightWall);
	  gameObjects().addGameObject(upperWall);
	 }
	 private void createClassicBricks(ImageReader imageReader){
	  CollisionStrategyFactory strategyFactory = new CollisionStrategyFactory(this);
	  for (int row = 0; row < rowsNum; row++) {
	   for (int col = 0; col < colsNum; col++) {

	  CollisionStrategy strategy = strategyFactory.createStrategyFactory();
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
	   }
	  }

	 }
	 private void createBackground(ImageReader imageReader){
	  GameObject background = new GameObject(Vector2.ZERO, new Vector2(RUNNER_WIDTH, RUNNER_HIGHT), backgroundImage);
	  gameObjects().addGameObject(background,Layer.BACKGROUND);
	 }
	 private void createBall(){
        GameObject ball = new Ball(Vector2.ZERO, new Vector2(50, 50), ballImage, collisionSound);
        ball.setVelocity(Vector2.DOWN.mult(BALL_VELOCITY));
        ball.setCenter(windowController.getWindowDimensions().mult(0.5F));
        gameObjects().addGameObject(ball);

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
     public BrickerGameManager(String windowTitle, Vector2 windowDimensions,int numCols,int numRows) {
        super(windowTitle, windowDimensions);
		RUNNER_HIGHT = windowDimensions.y();
		RUNNER_WIDTH = windowDimensions.x();
		this.colsNum = numCols;
		this.rowsNum = numRows;
	 	brickWidth = (RUNNER_WIDTH - 2 * marginX - (colsNum - 1) * spacing) / colsNum;
		bricksGrid = new Brick[numRows][numCols];
     }

    // this is just to check the code NEED TO CHANGE !!!!!!
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
	 	createClassicBricks(imageReader);
		//Walls
		createWalls();
		//Background
	 	createBackground(imageReader);

    }
	public void createPuck(Vector2 location){
	 Puck firstPuck = new Puck(location,new Vector2(50,50),puckImage,collisionSound); //50 is the ball size, need to replace with constants
	 firstPuck.initVelocity();
	 gameObjects().addGameObject(firstPuck);
	}
	public void createExtraPaddle(){
	 Vector2 paddleLocation = new Vector2(windowController.getWindowDimensions().x()/2,
			 windowController.getWindowDimensions().y()/2);
	 ExtraPaddle extraPaddle = new ExtraPaddle(paddleLocation,new Vector2(PADDLE_WIDTH,PADDLE_HEIGHT),paddleImage,
			 inputListener,
			 EXTRA_PADDLE_HITS,
			 new BasicCollisionStrategy(this));
	 addGameObject(extraPaddle);
	}

 public SoundReader getSoundReader() {
  return soundReader;
 }

 public ImageReader getImageReader() {
  return imageReader;
 }

 public WindowController getWindowController() {
  return windowController;
 }

 public GameObjectCollection getGameObjectCollection(GameObjectCollection gameObjectCollection) {
  return gameObjectCollection;
 }

 public UserInputListener getUserInputListener() {
  return inputListener;
 }
 public Sound getExplosionSound() {
  return explosionSound;
 }
 public Brick getBrick(int row,int col){
	  if(row<0 || row>=rowsNum || col<0 || col>= colsNum){
	   return null;
	  }
	  return bricksGrid[row][col];
 }
 public void setBrick(int row,int col,Brick brick){
	  this.bricksGrid[row][col]= brick;
 }
 public void addGameObject(GameObject gameObject){
    gameObjects().addGameObject(gameObject);
 }
 public void removeGameObject(GameObject gameObject){
	  gameObjects().removeGameObject(gameObject);
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