package bricker.brick_strategies;
import bricker.gameobjects.Puck;
import danogl.GameManager;
import danogl.GameObject;
import danogl.collisions.GameObjectCollection;
import danogl.gui.ImageReader;
import danogl.gui.SoundReader;
import danogl.gui.rendering.Renderable;
import danogl.util.Vector2;
import danogl.gui.*;

public class PuckStrategy implements  CollisionStrategy {
 private final GameObjectCollection gameObjectCollection;
 private final ImageReader imageReader;
 private final SoundReader soundReader;
 private float row;
 private float col;
 public PuckStrategy(GameObjectCollection gameObjectCollection,ImageReader imageReader,SoundReader soundReader) {
  this.gameObjectCollection = gameObjectCollection;
  this.imageReader = imageReader;
  this.soundReader = soundReader;
 }
 @Override
 public void onCollision(GameObject thisObj, GameObject otherObj) {
  Vector2 startLocation = new Vector2(row,col); //should be the center of the collided brick
  Renderable puckImage = imageReader.readImage("assets/mockBall.png", true);
  Sound puckSound = soundReader.readSound("assets/blop.wav");
  Puck firstPuck = new Puck(startLocation,new Vector2(50,50),puckImage,puckSound); //50 is the ball size, need to replace with constants
  firstPuck.initVelocity();
  Puck secondPuck = new Puck(startLocation,new Vector2(50,50),puckImage,puckSound);//50 is the ball size, need to replace with constants
  secondPuck.initVelocity();
  gameObjectCollection.removeGameObject(thisObj);
  gameObjectCollection.addGameObject(firstPuck);
  gameObjectCollection.addGameObject(secondPuck);
 }
}
