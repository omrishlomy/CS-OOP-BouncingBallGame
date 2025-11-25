package bricker.brick_strategies;
import bricker.BrickerGameManager;
import danogl.GameManager;
import danogl.collisions.GameObjectCollection;
import danogl.gui.ImageReader;
import danogl.gui.SoundReader;

import java.util.Random;

public class CollisionStrategyFactory {
 public CollisionStrategyFactory() {
 }

 public CollisionStrategy createStrategyFactory(BrickerGameManager gameManager) {
  Random rand = new Random();
  if (rand.nextBoolean()) {
   return new BasicCollisionStrategy(gameManager.getGameObjectCollection());
  }
  int strategyNum = rand.nextInt(5);
  if(strategyNum==0) {
   return new PuckStrategy(gameManager.getGameObjectCollection(),gameManager.getImageReader(),gameManager.getSoundReader());
  }
  if(strategyNum==1) {
   return new AddPaddleStrategy(gameObjectCollection,gameManager);

  }
  if(strategyNum==2) {
   return new ExplosionStrategy(this);
  }
  if(strategyNum==3) {
   return new AddLifeSrategy(this);
  }
  if(strategyNum==4) {
   return new DoubleStrategy(this);
  }
  return null;
 }
}
