package com.factsfinder.game.states;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector3;


/* A State class will refer to every state of the game. For example, MenuState, PlayState etc..; So basically
 every other game state will contain all the methods defined in this abstract class. */

public abstract class State {

    protected OrthographicCamera cam;
    protected Vector3 mouse;
    protected GameStateManager gsm;

    //Constructor
    public State(GameStateManager gsm){
        this.gsm = gsm;
        cam  = new OrthographicCamera();
        mouse = new Vector3();
    }

    //Every State will contain the below mentioned methods.
    protected abstract void handleInput();
    public abstract void update(float dt);
    public abstract void render(SpriteBatch sb);
    public abstract void dispose();

}
