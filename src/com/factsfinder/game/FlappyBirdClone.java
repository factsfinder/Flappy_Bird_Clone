package com.factsfinder.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.factsfinder.game.states.GameStateManager;
import com.factsfinder.game.states.MenuState;


public class FlappyBirdClone extends ApplicationAdapter {
	public static final int WIDTH = 500;
	public static final int HEIGHT = 800;
	public static final String TITLE = "Flappy Bird CLone";
	public GameStateManager gsm;
	public SpriteBatch batch;
	private Music musicBG; //background music
	
	@Override
	public void create () {
		batch = new SpriteBatch();
		gsm = new GameStateManager();
		musicBG = Gdx.audio.newMusic(Gdx.files.internal("C:\\Users\\phani\\Desktop\\flappy-bird-java\\Code\\android\\assets\\background-musci.mp3"));
		musicBG.setLooping(true);
		musicBG.setVolume(0.1f);
		musicBG.play();
		gsm.push(new MenuState(gsm));
	}

	@Override
	public void render () {
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		gsm.update(Gdx.graphics.getDeltaTime());
		gsm.render(batch);
	}
	
	@Override
	public void dispose () {
		super.dispose();
		musicBG.dispose();
	}
}
