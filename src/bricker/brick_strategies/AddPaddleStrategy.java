package bricker.brick_strategies;
import bricker.main.BrickerGameManager;
import bricker.gameobjects.ExtraPaddle;
import danogl.GameObject;
import danogl.collisions.Layer;

/**
 * class Strategy for adding an extra paddle into the game after colliding with a brick
 * @author Omri
 * @see bricker.brick_strategies.CollisionStrategyDecorator
 */
public class AddPaddleStrategy extends CollisionStrategyDecorator{
 private final BrickerGameManager brickerGameManager;

 /**
  * Strategy constructor
  * @param brickerGameManager- game manager
  * @param decorated- collision strategy object
  */
 public AddPaddleStrategy(BrickerGameManager brickerGameManager,CollisionStrategy decorated) {
  super(decorated);
  this.brickerGameManager = brickerGameManager;
 }

 /**
  * calles the basic strategy and create an extra paddle to the screen using the game manager
  * @param thisObj - game object that holds the strategy
  * @param otherObj - game object that collided with this object
  */
 @Override
 public void onCollision(GameObject thisObj, GameObject otherObj) {
  super.onCollision(thisObj, otherObj);
   if(ExtraPaddle.getPaddlesNum()==1){
	return;
   }
  brickerGameManager.createExtraPaddle();
 }
}
