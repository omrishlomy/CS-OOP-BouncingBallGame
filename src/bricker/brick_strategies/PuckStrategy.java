package bricker.brick_strategies;
import bricker.main.BrickerGameManager;
import danogl.GameObject;
import danogl.gui.*;

/**
 * class for adding 2 puck balls strategy
 * @author Omri
 * @see bricker.brick_strategies.CollisionStrategyDecorator
 */
public class PuckStrategy extends  CollisionStrategyDecorator {
 private BrickerGameManager gameManager;

 /**
  * Constructor
  * @param gameManager - game manager
  * @param decorated - collision strategy object
  */
 public PuckStrategy(BrickerGameManager gameManager,CollisionStrategy decorated) {
  super(decorated);
	this.gameManager = gameManager;
 }

 /**
  * onCollision method, calls the basic method and than add 2 puck balls to the game
  * @param thisObj - game object that holds the strategy
  * @param otherObj - game object that collided with this object.
  */
 @Override
 public void onCollision(GameObject thisObj, GameObject otherObj) {
  super.onCollision(thisObj, otherObj);
  gameManager.createPuck(thisObj.getCenter());
  gameManager.createPuck(thisObj.getCenter());
 }
}
