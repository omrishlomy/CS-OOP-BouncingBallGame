package bricker.brick_strategies;

import bricker.main.BrickerGameManager;
import java.util.Random;

/**
 * class factory for creating strategies for the game's bricks
 */
public class CollisionStrategyFactory {

 private final BrickerGameManager gameManager;
 private final Random rand = new Random();

 /**
  * Constructor
  * @param gameManager
  */
 public CollisionStrategyFactory(BrickerGameManager gameManager) {
  this.gameManager = gameManager;
 }

 /**
  * create a basic strategy in 50% of the case
  * create another strategy un the other 50% of the case
  * insuring a brick holds 3 strategies top
  * @return
  */
 public CollisionStrategy createStrategy() {

  CollisionStrategy base = new BasicCollisionStrategy(gameManager);

  // 50% chance do nothing
  if (rand.nextBoolean()) {
   return base;
  }

  // Create the first decorator
  CollisionStrategy first = randomDecorator(base);

  // Try to add a double strategy 20% of the time
  if (rand.nextInt(5) == 0) {

   // generate second decorator
   CollisionStrategy second = randomDecorator(base);

   // Check if combining them fits under the limit
   int total = 1 + first.countDecorators() + second.countDecorators();

   if (total <= 3) {
	return new DoubleStrategy(first, second);
   }

   // otherwise, return only the first decorator
  }
  return first;
 }

 /**
  * return special strategy each one with the same probability 0.25
  * ensuring the overall probability for special strategy would be 0.1
  *
  * @param base
  * @return
  */
 private CollisionStrategy randomDecorator(CollisionStrategy base) {
  int n = rand.nextInt(4);

  switch (n) {
   case 0 :
	return new PuckStrategy(gameManager, base);
   case 1:
	return new AddPaddleStrategy(gameManager, base);
   case 2:
	return new ExplosionStrategy(gameManager, base);
   case 3:
	return new AddLifeStrategy(gameManager, base);
   default:
	return null;
  }
 }

}
