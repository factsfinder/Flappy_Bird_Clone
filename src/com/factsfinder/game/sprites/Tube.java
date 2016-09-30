package com.factsfinder.game.sprites;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;

import com.badlogic.gdx.math.Rectangle;
import java.util.Random;


/**
 * Created by phani on 9/28/2016.
 */

public class Tube {
    public static int TubeWidth = 0;
    private static final int TUBE_RANGE = 130; //y-axis position : random range for the Tube
    private static final int TUBE_GAP = 100; // gap between the top and bottom tubes
    private static final int LOWEST_OPENING = 130; //the lowest opening the top tube can get from the bottom
    private Texture TopTube;
    private Texture BotTube;
    private Vector2 PosTopTube;
    private Vector2 PosBotTube;
    private Rectangle rectTop, rectBot; //invisible rectangles around the top tube and the bottom tube ; Used for Collision Detection

    private Random rand;

    public Tube(float x){
        rand = new Random();
        TopTube = new Texture("C:\\Users\\phani\\Desktop\\flappy-bird-java\\Code\\android\\assets\\toptube.png");
        BotTube = new Texture("C:\\Users\\phani\\Desktop\\flappy-bird-java\\Code\\android\\assets\\bottomtube.png");
        TubeWidth = TopTube.getWidth();
        PosTopTube = new Vector2(x, rand.nextInt(TUBE_RANGE) + TUBE_GAP + LOWEST_OPENING);
        PosBotTube = new Vector2(x, PosTopTube.y - BotTube.getHeight() - TUBE_GAP);
        rectTop = new Rectangle((int)(PosTopTube.x), (int)(PosTopTube.y), TopTube.getWidth(), TopTube.getHeight());
        rectBot = new Rectangle((int)PosBotTube.x, (int)PosBotTube.y, BotTube.getWidth(), BotTube.getHeight());
    }

    public Texture getTopTube() {
        return TopTube;
    }

    public Texture getBotTube() {
        return BotTube;
    }

    public Vector2 getPosTopTube() {
        return PosTopTube;
    }

    public Vector2 getPosBotTube() {
        return PosBotTube;
    }

    public void repositionTube(float x){
        PosTopTube.set(x, rand.nextInt(TUBE_RANGE) + TUBE_GAP + LOWEST_OPENING);
        PosBotTube.set(x, PosTopTube.y - BotTube.getHeight() - TUBE_GAP);
        rectTop.setPosition(PosTopTube.x, PosTopTube.y);
        rectBot.setPosition(PosBotTube.x, PosBotTube.y);
    }

    public boolean collision(Rectangle player){
        return player.overlaps(rectTop) || player.overlaps(rectBot);
    }

    public Rectangle getRectTop() {
        return rectTop;
    }

    public void dispose(){
        TopTube.dispose();
        BotTube.dispose();
    }
}
