package bricker.brick_strategies;

import danogl.collisions.GameObjectCollection;
import danogl.gui.ImageReader;

public class CollisionStrategyFactory {
 public CollisionStrategyFactory() {
 }

 public CollisionStrategy createStrategyFactory(GameObjectCollection gameObjectCollection, ImageReader imageReader, String strategyName) {
  if (strategyName.equals("Basic")) {
   return new BasicCollisionStrategy(gameObjectCollection);
  }
  if(strategyName.equals("Puck")) {
   return new PuckStrategy(gameObjectCollection, imageReader);
  }
  return null;
 }
}
