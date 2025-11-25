package bricker.brick_strategies;
import bricker.BrickerGameManager;
import bricker.gameobjects.ExtraPaddle;
import bricker.gameobjects.Paddle;
import danogl.GameObject;
import danogl.collisions.GameObjectCollection;
import danogl.gui.rendering.Renderable;
import danogl.util.Vector2;

public class AddPaddleStrategy {
// private static int paddleNum=1;
// private BrickerGameManager brickerGameManager;
// public AddPaddleStrategy(BrickerGameManager brickerGameManager) {
//  this.brickerGameManager = brickerGameManager;
//  paddleNum++;
// }
// @Override
// public void onCollision(GameObject thisObj, GameObject otherObj) {
//  gameObjectCollection.removeGameObject(thisObj);
//  if(paddleNum==2){
//   return;
//  }
//  Vector2 paddleLocation = new Vector2(brickerGameManager.windowController.getWindowDimensions().x()/2,
//		  brickerGameManager.windowController.getWindowDimensions().y()/2);
//  Renderable paddleImage = brickerGameManager.getImageReader().readImage("assets/Paddle.png",true);
//  ExtraPaddle extraPaddle = new ExtraPaddle(paddleLocation,new Vector2(100,10),paddleImage,
//		  brickerGameManager.userInputListener,
//		  4,
//		  new BasicCollisionStrategy(gameObjectCollection));
//  gameObjectCollection.addGameObject(extraPaddle);
// }
}
