package bricker.brick_strategies;
import bricker.gameobjects.Puck;
import danogl.GameObject;
import danogl.collisions.GameObjectCollection;
import danogl.gui.ImageReader;
import danogl.gui.rendering.Renderable;
import danogl.util.Vector2;

public class PuckStrategy implements  CollisionStrategy {
 private final GameObjectCollection gameObjectCollection;
 private final ImageReader imageReader;
 private float row;
 private float col;
 public PuckStrategy(GameObjectCollection gameObjectCollection, ImageReader imageReader) {
  this.gameObjectCollection = gameObjectCollection;
  this.imageReader = imageReader;
 }
 @Override
 public void onCollision(GameObject thisObj, GameObject otherObj) {
  Vector2 startLocation = new Vector2(row,col); //should be the center of the collided brick
  Renderable puckImage = imageReader.readImage("assets/mockBall.png", true);
  Puck firstPuck = new Puck(startLocation,new Vector2(50,50),puckImage,puckSound) //need to figure how to pass/use the ball sound here
 }
}
