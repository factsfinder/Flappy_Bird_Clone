package com.factsfinder.game.sprites;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.Array;

/**
 * Created by phani on 9/29/2016
 */

public class Animation {
    private Array<TextureRegion> frames; //An array list where all of our frames will be stored
    private float maxFrameTime; // Time interval of a frame before switching to the next frame
    private float currentFrameTime; //Time of the current frame
    private int frameCount; //No of frames
    private int frame; //current frame

    public Animation(TextureRegion region, int frameCount, float cycleTime ) {
        //Region is all of the frames combined into one image
        //frameCount is number of frames in the TextureRegion's region
        //cycleTime is the time taken to cycle through the entire animation

        frames = new Array<TextureRegion>();
        int frameWidth = region.getRegionWidth()/frameCount; //width of a single frame
        for(int i=0; i<frameCount; i++){
            frames.add(new TextureRegion(region, i*frameWidth, 0, frameWidth, region.getRegionHeight()));
        }

        this.frameCount = frameCount;
        maxFrameTime = cycleTime/frameCount;
        frame = 0;
    }

    public void update(float dt){
        currentFrameTime += dt;
        if(currentFrameTime > maxFrameTime){
            frame++;
            currentFrameTime = 0;
        }
        if(frame >= frameCount){
            frame = 0;
        }
    }

    public TextureRegion getFrame(){
        return frames.get(frame);
    }
}
