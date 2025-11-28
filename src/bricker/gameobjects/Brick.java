package bricker.gameobjects;
import danogl.GameObject;
import bricker.brick_strategies.CollisionStrategy;
import danogl.collisions.Collision;
import danogl.gui.rendering.Renderable;
import danogl.util.Vector2;

/**
 * class for brick object
 */
public class Brick extends GameObject {
     private final float width;
     private final float height;
     private final CollisionStrategy collisionStrategy;
     private final float row;
     private final float col;
     private final int rowInGrid;
     private final int colInGrid;

 /**
  * Constructor
  * @param topLeftCorner - location to add the object in pixels
  * @param dimensions - Vector2 dimentions of the brick
  * @param renderable - brick image to rend on the screen
  * @param collisionStrategy - collision strategy to apply
  * @param rowInGrid - row number in the bricks grid
  * @param colInGrid - column number in the bricks grid
  */
     public Brick(Vector2 topLeftCorner, Vector2 dimensions, Renderable renderable ,
                  CollisionStrategy collisionStrategy, int rowInGrid, int colInGrid) {
      super(topLeftCorner,dimensions,renderable);
      this.row = topLeftCorner.y();
      this.col = topLeftCorner.x();
      this.collisionStrategy = collisionStrategy;
      this.rowInGrid = rowInGrid;
      this.colInGrid = colInGrid;
      this.width = dimensions.x();
      this.height = dimensions.y();
     }

 /**
  * calls the collision strategy of the brick
  * @param other
  * @param collision
  */
     @Override
     public void onCollisionEnter(GameObject other, Collision collision) {
      collisionStrategy.onCollision(this, other);
     }

 /**
  * getter for row number in the bricks grid
   * @return
  */
    public int getRowInGrid() {
        return rowInGrid;
    }

 /**
  * getter for the column number in the brick grid
  * @return
  */
    public int getColInGrid() {
        return colInGrid;
    }

 /**
  * method for activating a collision
  * @param other
  */
    public void explode(GameObject other) {
         collisionStrategy.onCollision(this, other);
    }
}

