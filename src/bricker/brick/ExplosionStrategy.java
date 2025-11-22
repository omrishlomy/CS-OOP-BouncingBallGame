package bricker.brick;

import danogl.GameObject;
import danogl.gui.Sound;

// package protected - not part of API
class ExplosionStrategy implements CollisionStrategy{
    private Sound explosionSound;

    // constructor receives explosion sound.
    public ExplosionStrategy(Sound sound) {
        this.explosionSound = sound;
    }

    @Override
    public void onCollision(GameObject thisObj, GameObject otherObj) {
        // play the sound
        explosionSound.play();
    }
}
