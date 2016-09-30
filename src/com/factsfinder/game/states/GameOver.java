package com.factsfinder.game.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.factsfinder.game.FlappyBirdClone;

/**
 * Created by phani on 9/29/2016.
 */

public class GameOver extends State {
    private Texture background;
    private Texture StartBtn;
    private String gameOverText = "Game Over";
    private BitmapFont scoreText;
    private PlayState play;

    public GameOver(GameStateManager gsm){
        super(gsm);
        play = new PlayState(gsm);
        scoreText = new BitmapFont();
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
        sb.draw(StartBtn, (FlappyBirdClone.WIDTH/2 - StartBtn.getWidth()), (FlappyBirdClone.HEIGHT/2 + StartBtn.getHeight()));
        sb.draw(background, 0,0, FlappyBirdClone.WIDTH, FlappyBirdClone.HEIGHT);
        scoreText.draw(sb, gameOverText + "\n"+"Your Score: " + play.getScore(), 250, FlappyBirdClone.HEIGHT/2);
        sb.end();
    }

    @Override
    public void dispose() {
        scoreText.dispose();
        background.dispose();
        StartBtn.dispose();
        System.out.println("Game Over State Disposed");
    }
}
