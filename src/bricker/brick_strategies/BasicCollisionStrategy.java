package bricker.brick_strategies;

import bricker.BrickerGameManager;
import bricker.gameobjects.Brick;
import danogl.GameObject;
import danogl.collisions.GameObjectCollection;
import danogl.collisions.Layer;

public class BasicCollisionStrategy implements CollisionStrategy {
 private final BrickerGameManager gameManager;
	public BasicCollisionStrategy(BrickerGameManager gameManager) {
	 this.gameManager = gameManager;
	}
    @Override
    public void onCollision(GameObject thisObj, GameObject otherObj) {
        // remove the brick from the game object collection
	 if(thisObj.getTag().equals("Brick")) {
	  gameManager.removeGameObject(thisObj, Layer.STATIC_OBJECTS);
	 }
	 else{
	  gameManager.removeGameObject(thisObj, Layer.DEFAULT);
	 }
        // set the bricks array to be null in the current brick position
         if(thisObj.getTag().equals("Brick")) {
		  Brick thisBrick = (Brick) thisObj;
         gameManager.setBrick(thisBrick.getRowInGrid(), thisBrick.getColInGrid(), null);
		 }
    }
}
