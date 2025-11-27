package bricker.gameobjects;

import bricker.BrickerGameManager;
import danogl.GameObject;
import danogl.collisions.Collision;
import danogl.gui.Sound;
import danogl.gui.rendering.Renderable;
import danogl.util.Vector2;

import java.util.Random;

 public class Puck extends Ball{
 private static final float RATIO = 0.75f;
 private BrickerGameManager gameManager;
 public Puck(Vector2 topLeftCorner, Vector2 dimensions, Renderable renderable, Sound collisionSound,
             BrickerGameManager gameManager) {
  super(topLeftCorner,
		  new Vector2(dimensions.x() * RATIO,dimensions.y() * RATIO),
		  renderable,
		  collisionSound);
  this.gameManager = gameManager;
  this.initVelocity();
 }

     @Override
     public void onCollisionEnter(GameObject other, Collision collision) {
         super.onCollisionEnter(other, collision);
         // if it passes the bottom wall, remove it from the play
         if (other.getTag().equals("BottomWall")) {
             gameManager.removeGameObject(this);
         }
     }
 }
