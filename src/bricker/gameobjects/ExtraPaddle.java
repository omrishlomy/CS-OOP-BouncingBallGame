package bricker.gameobjects;

import bricker.brick_strategies.AddPaddleStrategy;
import bricker.brick_strategies.CollisionStrategy;
import danogl.GameObject;
import danogl.collisions.Collision;
import danogl.gui.UserInputListener;
import danogl.gui.rendering.Renderable;
import danogl.util.Vector2;

// package private - not part of the API : HAVE TO BE PUBLIC FOR BEING CALLED FROM THE STRATEGY PACKAGE
public class ExtraPaddle extends Paddle {
 	private static int extraPaddleNum=0;
    int numCollisionsBeforeDisappear;
	CollisionStrategy collisionStrategy;
    // indicatinf if the paddle is active, if it's false we need to remove it from the game objects and not
    // update it.
    boolean active = true;

    // constructor - added a number of collision before disapearing parameter (4 in the instructions)
    public ExtraPaddle(Vector2 topLeftCorner, Vector2 dimensions, Renderable renderable,
                       UserInputListener userInputListener, int numCollisionsBeforeDisapear,CollisionStrategy collisionStrategy) {
        super(topLeftCorner, dimensions, renderable, userInputListener);
        this.numCollisionsBeforeDisappear = numCollisionsBeforeDisapear;
		this.collisionStrategy = collisionStrategy;
		extraPaddleNum++;
    }

    @Override
    public void onCollisionEnter(GameObject other, Collision collision) {
        this.numCollisionsBeforeDisappear--;
        if (numCollisionsBeforeDisappear == 0){
            active = false;
            return;
        }
        super.onCollisionEnter(other, collision);
    }
	public void update(float deltaTime)
  {
    super.update(deltaTime);
	if(!active){
	 extraPaddleNum--;
	 collisionStrategy.onCollision(this,this);
	}
  }
    public boolean isActive() {
        return active;
    }
	public static int getPaddlesNum(){
	 return extraPaddleNum;
	}
}
