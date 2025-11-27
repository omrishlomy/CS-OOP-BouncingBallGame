package bricker.brick_strategies;

import bricker.BrickerGameManager;
import danogl.GameObject;

public class NonBrickStrategy implements  CollisionStrategy {
    private BrickerGameManager gameManager;

    public  NonBrickStrategy(BrickerGameManager gameManager) {
        this.gameManager = gameManager;
    }

    @Override
    public void onCollision(GameObject thisObj, GameObject otherObj) {
        gameManager.removeGameObject(thisObj);
    }
}
