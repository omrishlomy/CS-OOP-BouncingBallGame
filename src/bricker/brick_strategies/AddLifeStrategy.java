package bricker.brick_strategies;

import bricker.BrickerGameManager;
import bricker.gameobjects.Brick;
import danogl.GameObject;
import danogl.collisions.Layer;
import danogl.util.Vector2;

/**
 * Representing a strategy that drops a heart to the user.
 * handles the collision with this brick.
 * @author Lihi
 * @see bricker.brick_strategies.CollisionStrategy
 */
public class AddLifeStrategy extends CollisionStrategyDecorator{
 private final BrickerGameManager gameManager;

    /**
     * Constructor
     * @param gameManager- bricker game manager
     */
 public AddLifeStrategy(BrickerGameManager gameManager,CollisionStrategy decorated){
  super(decorated);
  this.gameManager = gameManager;
 }

    /**
     * handles the collision with the brick
     * @param thisObj- the brick
     * @param otherObj - object the collided with the brick
     */
     @Override
     public void onCollision(GameObject thisObj, GameObject otherObj) {
        // create a life and remove th brick
	    super.onCollision(thisObj,otherObj);
         Vector2 center = thisObj.getCenter();
          gameManager.createLife(center);
     }
}
