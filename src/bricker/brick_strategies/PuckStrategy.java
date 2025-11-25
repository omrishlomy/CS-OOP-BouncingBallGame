package bricker.brick_strategies;
import bricker.BrickerGameManager;
import bricker.gameobjects.Puck;
import danogl.GameManager;
import danogl.GameObject;
import danogl.collisions.GameObjectCollection;
import danogl.gui.ImageReader;
import danogl.gui.SoundReader;
import danogl.gui.rendering.Renderable;
import danogl.util.Vector2;
import danogl.gui.*;

public class PuckStrategy implements  CollisionStrategy {
 private BrickerGameManager gameManager;
 public PuckStrategy(BrickerGameManager gameManager) {
	this.gameManager = gameManager;
 }
 @Override
 public void onCollision(GameObject thisObj, GameObject otherObj) {
  gameManager.removeGameObject(thisObj);
  gameManager.createPuck(thisObj.getCenter());
  gameManager.createPuck(thisObj.getCenter());

 }
}
