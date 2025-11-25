package bricker.brick_strategies;
import danogl.GameObject;
import danogl.collisions.Collision;
import danogl.gui.rendering.Renderable;
import danogl.util.Vector2;

public class Brick extends GameObject {
     private final float width;
     private final float height;
     private final bricker.brick.CollisionStrategy collisionStrategy;
     private final float row;
     private final float col;
     private final int rowInGrid;
     private final int colInGrid;
     //constructor with Collision strategy
     public Brick(Vector2 topLeftCorner, Vector2 dimensions, Renderable renderable ,
                  bricker.brick.CollisionStrategy collisionStrategy, int rowInGrid, int colInGrid) {
      super(topLeftCorner,dimensions,renderable);
      this.row = topLeftCorner.y();
      this.col = topLeftCorner.x();
      this.collisionStrategy = collisionStrategy;
      this.rowInGrid = rowInGrid;
      this.colInGrid = colInGrid;
      this.width = dimensions.x();
      this.height = dimensions.y();
     }
     @Override
     public void onCollisionEnter(GameObject other, Collision collision) {
      collisionStrategy.onCollision(this, other);
     }



     public float getCol() {
      return col;
     }

     public float getRow() {
      return row;
     }

    public int getRowInGrid() {
        return rowInGrid;
    }

    public int getColInGrid() {
        return colInGrid;
    }

    public float getWidth() {
        return width;
    }

    public float getHeight() {
        return height;
    }
}

