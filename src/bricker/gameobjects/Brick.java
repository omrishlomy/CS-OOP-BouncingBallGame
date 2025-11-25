package bricker.gameobjects;
import danogl.GameObject;
import danogl.collisions.Collision;
import danogl.gui.rendering.Renderable;
import danogl.util.Vector2;

public class Brick extends GameObject {
 private static final int HEIGHT = 15;
 private float width;
 private final CollisionStrategy collisionStrategy;
 private final float row;
 private final float col;
 //constructor with Collision strategy
 public Brick(Vector2 topLeftCorner, Vector2 dimensions, Renderable renderable ,CollisionStrategy collisionStrategy) {
  super(topLeftCorner,dimensions,renderable);
  this.row = topLeftCorner.y();
  this.col = topLeftCorner.x();
  this.collisionStrategy = collisionStrategy;
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
}
