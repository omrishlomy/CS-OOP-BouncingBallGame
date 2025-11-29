package bricker.brick_strategies;

import bricker.gameobjects.Brick;
import danogl.GameObject;
import danogl.gui.Sound;
import bricker.main.BrickerGameManager;

/**
 * class impleneting the explosion strategy
 * @author Omri
 * @see bricker.brick_strategies.CollisionStrategyDecorator
 */
class ExplosionStrategy extends CollisionStrategyDecorator {
    private final BrickerGameManager gameManager;


 /**
  * Constructor
  * @param gameManeger- game manager
  * @param decorated - Collision Strategy object
  */
 public ExplosionStrategy(BrickerGameManager gameManeger,CollisionStrategy decorated) {
	 super(decorated);
        this.gameManager = gameManeger;
    }

 /**
  * on collision method - deletes the current brick and call the onCollision method of the 4 nearby bricks
  * @param thisObj- game object that holds the strategy
  * @param otherObj- game object that collided with this object.
  */
    @Override
    public void onCollision(GameObject thisObj, GameObject otherObj) {
        // play the sound
        Sound explosionSound = gameManager.getExplosionSound();
        explosionSound.play();

        // explode neighbors
        Brick currBrick = (Brick) thisObj;
        int rowInGrid = currBrick.getRowInGrid();
        int colInGrid = currBrick.getColInGrid();
		//remove the current brick from game objects and from BricksGrid (to handle recursive call)
	 	super.onCollision(thisObj, otherObj);
        // get all neighbors
        int[][] neighbors = {{rowInGrid - 1, colInGrid}, {rowInGrid+1, colInGrid},
                {rowInGrid, colInGrid-1}, {rowInGrid,  colInGrid+1}};
        for (int[] neighbor : neighbors) {
            Brick neighborBrick = gameManager.getBrick(neighbor[0], neighbor[1]);
            if (neighborBrick != null){
			 neighborBrick.explode(thisObj);

			}
        }
    }
}
