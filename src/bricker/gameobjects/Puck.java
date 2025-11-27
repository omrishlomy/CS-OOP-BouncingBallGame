package bricker.gameobjects;

import danogl.gui.Sound;
import danogl.gui.rendering.Renderable;
import danogl.util.Vector2;

import java.util.Random;

 public class Puck extends Ball{
 private static final float RATIO = 0.75f;
 private static final float BALL_SPEED = 100;
// public void initVelocity(){
//  Random rand = new Random();
//  double angle = rand.nextDouble() * Math.PI;
//  float velocityX = (float) Math.cos(angle) * BALL_SPEED;
//  float velocityY = (float) Math.sin(angle) * BALL_SPEED;
//  Vector2 velocity = new Vector2(velocityX, velocityY);
//  super.setVelocity(velocity);
// }
 public Puck(Vector2 topLeftCorner, Vector2 dimensions, Renderable renderable, Sound collisionSound) {
  super(topLeftCorner,
		  new Vector2(dimensions.x() * RATIO,dimensions.y() * RATIO),
		  renderable,
		  collisionSound);
  this.initVelocity();
 }

}
