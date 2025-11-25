package bricker.brick_strategies;
import bricker.BrickerGameManager;
import danogl.GameManager;
import danogl.collisions.GameObjectCollection;
import danogl.gui.ImageReader;
import danogl.gui.SoundReader;

import java.util.Random;

public class CollisionStrategyFactory {
 private BasicCollisionStrategy basicCollisionStrategy;
 private PuckStrategy puckStrategy;
 private ExplosionStrategy explosionStrategy;
 private AddPaddleStrategy addPaddleStrategy;
 public CollisionStrategyFactory(BrickerGameManager ganeManager) {
  basicCollisionStrategy = new BasicCollisionStrategy(ganeManager);
  puckStrategy = new PuckStrategy(ganeManager);
  explosionStrategy = new ExplosionStrategy(ganeManager);
  addPaddleStrategy = new AddPaddleStrategy(ganeManager);
 }

 public CollisionStrategy createStrategyFactory() {
  Random rand = new Random();
  if (rand.nextBoolean()) {
   return basicCollisionStrategy;
  }
  int strategyNum = rand.nextInt(5);
  if(strategyNum==0) {
   return puckStrategy;
  }
  if(strategyNum==1) {
   return addPaddleStrategy;

  }
  if(strategyNum==2) {
   return explosionStrategy;
  }
  if(strategyNum==3) {
   return basicCollisionStrategy;
  }
  if(strategyNum==4) {
   return basicCollisionStrategy;
  }
  return null;
 }
}
