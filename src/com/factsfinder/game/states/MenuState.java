package com.factsfinder.game.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.factsfinder.game.FlappyBirdClone;

// A game state where a menu is shown before and after the game.

public class MenuState extends State {

    private Texture background;
    private Texture StartBtn;

    public MenuState(GameStateManager gsm){
        super(gsm);
        background = new Texture("C:\\Users\\phani\\Desktop\\flappy-bird-java\\Code\\android\\assets\\background.png");
        StartBtn = new Texture("C:\\Users\\phani\\Desktop\\flappy-bird-java\\Code\\android\\assets\\startBtn.png");
    }


    @Override
    protected void handleInput() {
        if(Gdx.input.justTouched()){
            gsm.set(new PlayState(gsm));
        }
    }

    @Override
    public void update(float dt) {
        handleInput();
    }

    @Override
    public void render(SpriteBatch sb) {
        sb.begin();
        sb.draw(background, 0,0, FlappyBirdClone.WIDTH, FlappyBirdClone.HEIGHT);
        sb.draw(StartBtn, (FlappyBirdClone.WIDTH/2)-(StartBtn.getWidth()/2), FlappyBirdClone.HEIGHT/2);
        sb.end();
    }

    @Override
    public void dispose() {
        background.dispose();
        StartBtn.dispose();
        System.out.println("Menu State Disposed");
    }
}
