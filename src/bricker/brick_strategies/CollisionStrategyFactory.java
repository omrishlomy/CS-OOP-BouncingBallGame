package bricker.brick_strategies;
import bricker.BrickerGameManager;
import danogl.GameManager;
import danogl.collisions.GameObjectCollection;
import danogl.gui.ImageReader;
import danogl.gui.SoundReader;

public class CollisionStrategyFactory {
 public CollisionStrategyFactory() {
 }

 public CollisionStrategy createStrategyFactory(GameObjectCollection gameObjectCollection, BrickerGameManager gameManager,String strategyName) {
  if (strategyName.equals("Basic")) {
   return new BasicCollisionStrategy(gameObjectCollection);
  }
  if(strategyName.equals("Puck")) {
   return new PuckStrategy(gameObjectCollection,gameManager.getImageReader(),gameManager.getSoundReader());
  }
  if(strategyName.equals("AddPaddle")) {
   return new AddPaddleStrategy(gameObjectCollection,gameManager);
  }
  return null;
 }
}
