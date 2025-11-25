package bricker.brick_strategies;
import bricker.BrickerGameManager;
import bricker.gameobjects.ExtraPaddle;
import bricker.gameobjects.Paddle;
import danogl.GameObject;
import danogl.collisions.GameObjectCollection;
import danogl.gui.rendering.Renderable;
import danogl.util.Vector2;

public class AddPaddleStrategy implements CollisionStrategy{
 private BrickerGameManager brickerGameManager;
 private static int paddleNum=1;
 public AddPaddleStrategy(BrickerGameManager brickerGameManager) {
  this.brickerGameManager = brickerGameManager;
 }
 @Override
 public void onCollision(GameObject thisObj, GameObject otherObj) {
  brickerGameManager.removeGameObject(thisObj);
   if(ExtraPaddle.getPaddlesNum()==1){
	return;
   }
  brickerGameManager.createExtraPaddle();
   paddleNum++;
 }
}
