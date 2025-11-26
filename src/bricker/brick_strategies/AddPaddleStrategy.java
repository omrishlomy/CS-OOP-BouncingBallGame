package bricker.brick_strategies;
import bricker.BrickerGameManager;
import bricker.gameobjects.ExtraPaddle;
import bricker.gameobjects.Paddle;
import danogl.GameObject;
import danogl.collisions.GameObjectCollection;
import danogl.collisions.Layer;
import danogl.gui.rendering.Renderable;
import danogl.util.Vector2;

public class AddPaddleStrategy implements CollisionStrategy{
 private BrickerGameManager brickerGameManager;
 public AddPaddleStrategy(BrickerGameManager brickerGameManager) {
  this.brickerGameManager = brickerGameManager;
 }
 @Override
 public void onCollision(GameObject thisObj, GameObject otherObj) {
  brickerGameManager.removeGameObject(thisObj,Layer.STATIC_OBJECTS);
   if(ExtraPaddle.getPaddlesNum()==1){
	return;
   }
  brickerGameManager.createExtraPaddle();
 }
}
