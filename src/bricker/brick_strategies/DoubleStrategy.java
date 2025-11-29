package bricker.brick_strategies;

import danogl.GameObject;

/**
 * class for holding double strategy
 * @author Omri
 * @see bricker.brick_strategies.CollisionStrategyDecorator
 */
public class DoubleStrategy extends CollisionStrategyDecorator {
 private final CollisionStrategy secondStrategy;

 /**
  * Constructor
  * @param decorated - collision strategy
  * @param secondStrategy - collision strategy to add
  */
 public DoubleStrategy(CollisionStrategy decorated,
					   CollisionStrategy secondStrategy) {
  super(decorated);
  this.secondStrategy = secondStrategy;
 }

 /**
  * on Collision strategy, call the first and second strategies
  * @param thisObj- game object holds the strategy
  * @param otherObj- game object that collided with this object.
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
