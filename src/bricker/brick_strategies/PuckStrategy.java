package bricker.brick_strategies;
import bricker.BrickerGameManager;
import danogl.GameObject;
import danogl.gui.*;

/**
 * class for adding 2 puck balls strategy
 */
public class PuckStrategy extends  CollisionStrategyDecorator {
 private BrickerGameManager gameManager;

 /**
  * Constructor
  * @param gameManager
  * @param decorated
  */
 public PuckStrategy(BrickerGameManager gameManager,CollisionStrategy decorated) {
  super(decorated);
	this.gameManager = gameManager;
 }

 /**
  * onCollision method, calls the basic method and than add 2 puck balls to the game
  * @param thisObj
  * @param otherObj
  */
 @Override
 public void onCollision(GameObject thisObj, GameObject otherObj) {
  super.onCollision(thisObj, otherObj);
  gameManager.createPuck(thisObj.getCenter());
  gameManager.createPuck(thisObj.getCenter());
 }
}
