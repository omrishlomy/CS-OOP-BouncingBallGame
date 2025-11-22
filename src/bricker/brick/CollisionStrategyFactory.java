package bricker.brick;

import danogl.collisions.GameObjectCollection;

public class CollisionStrategyFactory {
 public CollisionStrategyFactory() {
 }

 public CollisionStrategy createStrategyFactory(GameObjectCollection gameObjectCollection) {
  return new BasicCollisionStrategy(gameObjectCollection);
 }
}
