package bricker.brick_strategies;

import bricker.BrickerGameManager;
import bricker.gameobjects.Brick;
import danogl.GameObject;
import danogl.collisions.Layer;
import danogl.util.Vector2;

public class AddLifeStrategy implements CollisionStrategy{
 private BrickerGameManager gameManager;
 public AddLifeStrategy(BrickerGameManager gameManager){
  this.gameManager = gameManager;
 }
 @Override
 public void onCollision(GameObject thisObj, GameObject otherObj) {
  Vector2 center = thisObj.getCenter();
  gameManager.createLife(center);
  gameManager.removeGameObject(thisObj,Layer.STATIC_OBJECTS);

 }
}
