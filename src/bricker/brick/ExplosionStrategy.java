package bricker.brick;

import danogl.GameObject;
import danogl.collisions.GameObjectCollection;
import danogl.gui.Sound;

// package protected - not part of API
class ExplosionStrategy implements CollisionStrategy{
    private final Sound explosionSound;
    private final GameObjectCollection gameObjectCollection;
    private final Brick[][] bricks;


    // constructor receives explosion sound, the game objects and a list of the bricks
    public ExplosionStrategy(Sound sound, GameObjectCollection gameObjectCollection, Brick[][] bricks) {
        this.explosionSound = sound;
        this.gameObjectCollection = gameObjectCollection;
        this.bricks = bricks;
    }

    @Override
    public void onCollision(GameObject thisObj, GameObject otherObj) {
        // play the sound
        explosionSound.play();

        // explode neighbors
        Brick currBrick = (Brick) thisObj;
        float currRow = currBrick.getRow();
        float currCol = currBrick.getCol();
        float [][] neighbors = {{currRow-1, currCol}, {}};


    }
}
