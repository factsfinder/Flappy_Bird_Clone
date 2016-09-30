package com.factsfinder.game.sprites;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;

/**
 * Created by phani on 9/28/2016.
 */

public class Bird {
    private static final int GRAVITY = -15;
    private static final int FLY = 100; //bird's flying position update
    private Texture texture;
    private Vector3 position;
    private Vector3 velocity;
    private Rectangle birdRect; //Invisible rectangle around the bird; used for collision detection
    private Sound flap;
    private Animation birdAnimation;


    public Bird(int x, int y){
        position = new Vector3(x,y,0);
        velocity = new Vector3(0,0,0);
        texture = new Texture("C:\\Users\\phani\\Desktop\\flappy-bird-java\\Code\\android\\assets\\birdanimation.png");
        birdAnimation = new Animation(new TextureRegion(texture), 3, 0.5f);
        birdRect = new Rectangle(x, y, texture.getWidth()/3, texture.getHeight());
        flap = Gdx.audio.newSound(Gdx.files.internal("C:\\Users\\phani\\Desktop\\flappy-bird-java\\Code\\android\\assets\\flap.ogg"));
    }

    public void update(float dt){
        birdAnimation.update(dt);
        if(position.y > 0){
            velocity.add(0, GRAVITY, 0);
        }
        velocity.scl(dt);
        position.add(FLY * dt ,velocity.y, 0);
        velocity.scl(1/dt);
        birdRect.setPosition(position.x, position.y);
    }

    public void jump(){
        velocity.y = 300;
        flap.play(1f);
    }

    public TextureRegion getBird() {
        return birdAnimation.getFrame();
    }

    public Vector3 getPosition() {
        return position;
    }
    public Rectangle getBirdRect(){
        return birdRect;
    }
    public void dispose(){
        texture.dispose();
        flap.dispose();
    }
}
