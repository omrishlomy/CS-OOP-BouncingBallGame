package bricker.gameobjects;

import bricker.BrickerGameManager;
import danogl.GameObject;
import danogl.collisions.Collision;
import danogl.gui.Sound;
import danogl.gui.rendering.Renderable;
import danogl.util.Vector2;

import java.util.Random;

/**
 * class for puck balls
 */
 public class Puck extends Ball{
 private static final float RATIO = 0.75f;
 private BrickerGameManager gameManager;

 /**
  * Constructor
  * @param topLeftCorner - location for the puck ball to be presented in Vector2 pixels
  * @param dimensions - Vector2 dimention size
  * @param renderable - Puck image to rend
  * @param collisionSound - sound of Puck collision
  * @param gameManager - Game manager object
  */
 public Puck(Vector2 topLeftCorner, Vector2 dimensions, Renderable renderable, Sound collisionSound,
             BrickerGameManager gameManager) {
  super(topLeftCorner,
		  new Vector2(dimensions.x() * RATIO,dimensions.y() * RATIO),
		  renderable,
		  collisionSound);
  this.gameManager = gameManager;
  this.initVelocity();
 }

 /**
  * call the Ball collision method, leave the game if pass the bottom paddle
  * @param other - Game object the ball collided with
  * @param collision - holds the data of the collision like norm
  */
     @Override
     public void onCollisionEnter(GameObject other, Collision collision) {
         super.onCollisionEnter(other, collision);
         // if it passes the bottom wall, remove it from the play
         if (other.getTag().equals("BottomWall")) {
             gameManager.removeGameObject(this);
         }
     }
 }
