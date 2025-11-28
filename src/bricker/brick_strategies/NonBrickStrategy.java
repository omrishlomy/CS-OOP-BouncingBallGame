package bricker.brick_strategies;

import bricker.BrickerGameManager;
import danogl.GameObject;

/**
 * Represent a collision strategy for a non brick game object.
 * remove the object upon collisions
 * @author Lihi
 * @see bricker.brick_strategies.CollisionStrategy
 */
public class NonBrickStrategy implements  CollisionStrategy {
    private BrickerGameManager gameManager;

    /**
     * Constructor
     * @param gameManager - bricker game manager
     */
    public  NonBrickStrategy(BrickerGameManager gameManager) {
        this.gameManager = gameManager;
    }

    /**
     * handles the collision, removes the object.
     * @param thisObj - the object holding the strategy
     * @param otherObj - the object it collided with.
     */
    @Override
    public void onCollision(GameObject thisObj, GameObject otherObj) {
        gameManager.removeGameObject(thisObj);
    }
}
