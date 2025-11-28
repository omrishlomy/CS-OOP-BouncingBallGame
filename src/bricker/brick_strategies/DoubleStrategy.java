package bricker.brick_strategies;

import danogl.GameObject;

/**
 * class for holding double strategy
 */
public class DoubleStrategy extends CollisionStrategyDecorator {
 private final CollisionStrategy secondStrategy;

 /**
  * Constructor
  * @param decorated
  * @param secondStrategy
  */
 public DoubleStrategy(CollisionStrategy decorated,
					   CollisionStrategy secondStrategy) {
  super(decorated);
  this.secondStrategy = secondStrategy;
 }

 /**
  * on Collision strategy, call the first and second strategies
  * @param thisObj
  * @param otherObj
  */
 @Override
 public void onCollision(GameObject thisObj, GameObject otherObj) {
  super.onCollision(thisObj, otherObj);
  secondStrategy.onCollision(thisObj, otherObj);
 }

 /**
  * return the number of strategies the object holds
  * @return
  */
 @Override
 public int countDecorators() {
  // this decorator counts + decorated + second decorator
  return 1 + decorated.countDecorators() + secondStrategy.countDecorators();
 }
}
