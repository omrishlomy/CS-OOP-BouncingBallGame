package bricker.brick_strategies;

import danogl.GameObject;

/**
 * class decorator for strategies, implements the Collision strategy interface
 */
public abstract class CollisionStrategyDecorator implements CollisionStrategy {

 protected final CollisionStrategy decorated;

 /**
  * Constructor
  * @param decorated
  */
 public CollisionStrategyDecorator(CollisionStrategy decorated) {
  this.decorated = decorated;
 }

 /**
  * activate the onCollision method of the wrapped strategy
  * @param thisObj
  * @param otherObj
  */
 @Override
 public void onCollision(GameObject thisObj, GameObject otherObj) {
  decorated.onCollision(thisObj, otherObj);
 }

 /**
  *
  * @return the count of strategies the brick holds
  */
 @Override
 public int countDecorators(){
  return 1 + decorated.countDecorators();
 }
}

