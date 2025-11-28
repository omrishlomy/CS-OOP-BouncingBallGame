package bricker.brick_strategies;

import bricker.main.BrickerGameManager;
import bricker.gameobjects.Brick;
import danogl.GameObject;
import danogl.collisions.GameObjectCollection;
import danogl.collisions.Layer;

/**
 * class of basic collision of the game - removing the brick from the screen without further functions
 */
public class BasicCollisionStrategy implements CollisionStrategy {
 private final BrickerGameManager gameManager;

 /**
  * Strategy constructor
  * @param gameManager
  */
	public BasicCollisionStrategy(BrickerGameManager gameManager) {
	 this.gameManager = gameManager;
	}

 /**
  * Collision strategy - removes the brick from the game using the Bricker game manager
  * @param thisObj
  * @param otherObj
  */
    @Override
    public void onCollision(GameObject thisObj, GameObject otherObj) {
        // remove the brick from the game object collection
        gameManager.removeGameObject(thisObj);
        // set the bricks array to be null in the current brick position
         Brick thisBrick = (Brick) thisObj;
		 //check if the brick was already removed from the game
		 Brick gridBrick = gameManager.getBrick(thisBrick.getRowInGrid(), thisBrick.getColInGrid());
		 if (gridBrick == null){
		  return;
		 }
         gameManager.setBrick(thisBrick.getRowInGrid(), thisBrick.getColInGrid(), null);
         gameManager.setActiveBricks(gameManager.getActiveBricks() - 1);
    }
}
