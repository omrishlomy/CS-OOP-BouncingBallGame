package bricker.gameobjects;

import bricker.brick_strategies.AddPaddleStrategy;
import bricker.brick_strategies.CollisionStrategy;
import danogl.GameObject;
import danogl.collisions.Collision;
import danogl.gui.UserInputListener;
import danogl.gui.rendering.Renderable;
import danogl.util.Vector2;

/**
 * Extra paddle class, supporting the add Paddle strategy
 * @author Omri
 * @see bricker.gameobjects.Paddle
 */
public class ExtraPaddle extends Paddle {
 	private static int extraPaddleNum=0;
    int numCollisionsBeforeDisappear;
	CollisionStrategy collisionStrategy;
    // indicatinf if the paddle is active, if it's false we need to remove it from the game objects and not
    // update it.
    boolean active = true;

 /**
  * constructor - added a number of collision before disappearing parameter (4 in the instructions)
  * @param topLeftCorner - location in  pixels of the extra Paddle
  * @param dimensions - Vector2 dimensions of the Paddle
  * @param renderable - Paddle image to rend
  * @param userInputListener - input listener for moving the Paddle
  * @param numCollisionsBeforeDisapear - number of hits the Paddle will recencies before disappearing
  * @param collisionStrategy - collision strategy to apply (Basic in default)
  */
    public ExtraPaddle(Vector2 topLeftCorner, Vector2 dimensions, Renderable renderable,
                       UserInputListener userInputListener, int numCollisionsBeforeDisapear,
                       CollisionStrategy collisionStrategy) {
        super(topLeftCorner, dimensions, renderable, userInputListener);
        this.numCollisionsBeforeDisappear = numCollisionsBeforeDisapear;
		this.collisionStrategy = collisionStrategy;
		extraPaddleNum++;
    }

 /**
  * collision method checks if the Paddle reached to the maximum hits and call the Paddle collision strategy
  * @param other
  * @param collision
  */
    @Override
    public void onCollisionEnter(GameObject other, Collision collision) {
        this.numCollisionsBeforeDisappear--;
        if (numCollisionsBeforeDisappear == 0){
            active = false;
            return;
        }
        super.onCollisionEnter(other, collision);
    }

 /**
  * update method checks if the Padlle is not active, than remove it form the game
  * @param deltaTime - time for updating the frame
  */
	public void update(float deltaTime)
  {
    super.update(deltaTime);
	if(!active){
	 extraPaddleNum--;
	 collisionStrategy.onCollision(this,this);
	}
  }

 /**
  * return thee number of Extra Paddles in the game
  * @return
  */
	public static int getPaddlesNum(){
	 return extraPaddleNum;
	}
}
