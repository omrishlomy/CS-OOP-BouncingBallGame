package bricker.brick_strategies;
import bricker.BrickerGameManager;
import bricker.gameobjects.ExtraPaddle;
import danogl.GameObject;
import danogl.collisions.Layer;

/**
 * class Strategy for adding an extra paddle into the game after colliding with a brick
 */
public class AddPaddleStrategy extends CollisionStrategyDecorator{
 private final BrickerGameManager brickerGameManager;

 /**
  * Strategy constructor
  * @param brickerGameManager
  * @param decorated
  */
 public AddPaddleStrategy(BrickerGameManager brickerGameManager,CollisionStrategy decorated) {
  super(decorated);
  this.brickerGameManager = brickerGameManager;
 }

 /**
  * calles the basic strategy and create an extra paddle to the screen using the game manager
  * @param thisObj
  * @param otherObj
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
