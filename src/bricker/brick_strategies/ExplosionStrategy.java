package bricker.brick_strategies;

import bricker.gameobjects.Brick;
import danogl.GameManager;
import danogl.GameObject;
import danogl.collisions.AABB.AABBCollider;
import danogl.collisions.Collision;
import danogl.collisions.GameObjectCollection;
import danogl.gui.Sound;
import danogl.gui.rendering.Renderable;
import bricker.BrickerGameManager;

// package protected - not part of API
class ExplosionStrategy implements CollisionStrategy {
    private final BrickerGameManager gameManeger;


    // constructor receives explosion sound, the game objects and a list of the bricks
    public ExplosionStrategy(BrickerGameManager gameManeger) {
        this.gameManeger = gameManeger;
    }

    @Override
    public void onCollision(GameObject thisObj, GameObject otherObj) {
        // play the sound
        Sound explosionSound = gameManeger.getExplosionSound();
        explosionSound.play();

        // explode neighbors
        Brick currBrick = (Brick) thisObj;
        int rowInGrid = currBrick.getRowInGrid();
        int colInGrid = currBrick.getColInGrid();

        // get all neighbors
        int[][] neighbors = {{rowInGrid - 1, colInGrid}, {rowInGrid+1, colInGrid},
                {rowInGrid, colInGrid-1}, {rowInGrid,  colInGrid+1}};
        for (int[] neighbor : neighbors) {
            Brick neighborBrick = gameManager.getBrick(neighbor[0], neighbor[1]);

            neighborBrick.onCollisionEnter(currBrick, null);
        }
    }
}
