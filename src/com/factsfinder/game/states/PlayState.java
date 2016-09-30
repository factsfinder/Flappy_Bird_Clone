package com.factsfinder.game.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.factsfinder.game.sprites.Bird;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.factsfinder.game.FlappyBirdClone;
import com.factsfinder.game.sprites.Tube;

// The main state of the game where the game play actually takes place.

public class PlayState extends State {

    private Bird bird;
    private Texture bg; //background for the game;
    private static final int TUBE_SPACING = 100;// space between the tubes
    private static final int TUBE_COUNT = 4;

    private Texture ground;
    private Vector2 groundPos1, groundPos2;
    private static final int GROUND_OFFSET = -60;

    private int score = 0;
    private String scoreName = "score: 0";
    private BitmapFont playScore;
    private Array<Tube> Tubes; //dynamic array of tubes


    public PlayState(GameStateManager gsm) {
        super(gsm);
        bird = new Bird(10,200);
        playScore = new BitmapFont();
        playScore.setColor(1,0,0,0);
        bg = new Texture("C:\\Users\\phani\\Desktop\\flappy-bird-java\\Code\\android\\assets\\background.png");
        ground = new Texture("C:\\Users\\phani\\Desktop\\flappy-bird-java\\Code\\android\\assets\\ground.png");
        groundPos1 = new Vector2(cam.position.x - cam.viewportWidth/2, GROUND_OFFSET);
        groundPos2 = new Vector2((cam.position.x - cam.viewportWidth/2) + ground.getWidth(), GROUND_OFFSET);

        cam.setToOrtho(false, FlappyBirdClone.WIDTH/2, FlappyBirdClone.HEIGHT/2);

        Tubes = new Array<Tube>();
        for(int i =1; i <= TUBE_COUNT; i++){
            Tubes.add(new Tube(i * (TUBE_SPACING + Tube.TubeWidth)));
        }


    }

    protected void handleInput() {
        if(Gdx.input.justTouched()){
            bird.jump();
        }
    }

    public void update(float dt) {
        handleInput();
        updateScore();
        updateGround();
        cam.position.x = bird.getPosition().x +80;
        bird.update(dt);
        for(int i=0; i<Tubes.size; i++){
            Tube tube = Tubes.get(i);
            if(cam.position.x - cam.viewportWidth/2 > tube.getPosTopTube().x + tube.getTopTube().getWidth()){
                tube.repositionTube(tube.getPosBotTube().x + (TUBE_SPACING + Tube.TubeWidth)* TUBE_COUNT);
            }
            if(tube.collision(bird.getBirdRect())){
                gsm.set(new GameOver(gsm));
            }

        }

        if(bird.getPosition().y <= ground.getHeight() + GROUND_OFFSET) {
            gsm.set(new GameOver(gsm));
        }
        cam.update();

    }

    public void render(SpriteBatch sb) {
        sb.setProjectionMatrix(cam.combined);
        sb.begin();
        sb.draw(bg, cam.position.x - cam.viewportWidth/2 , 0);
        sb.draw(bird.getBird(), bird.getPosition().x, bird.getPosition().y);
        for(Tube tube: Tubes){
            sb.draw(tube.getTopTube(), tube.getPosTopTube().x, tube.getPosTopTube().y);
            sb.draw(tube.getBotTube(), tube.getPosBotTube().x, tube.getPosBotTube().y);
        }
        sb.draw(ground, groundPos1.x, groundPos1.y);
        sb.draw(ground, groundPos2.x, groundPos2.y);
        playScore.draw(sb, scoreName, FlappyBirdClone.WIDTH/2, FlappyBirdClone.HEIGHT/2);
        sb.end();
    }

    public void updateGround(){
        if(cam.position.x - cam.viewportWidth/2 > groundPos1.x + ground.getWidth()){
            groundPos1.add(ground.getWidth()*2, 0);
        }

        if(cam.position.x - cam.viewportWidth/2 > groundPos2.x + ground.getWidth()){
            groundPos2.add(ground.getWidth()*2, 0);
        }
    }

    public void updateScore(){
        for(Tube tube: Tubes){
            if(!tube.collision(bird.getBirdRect()) && !bird.getBirdRect().overlaps(tube.getRectTop()) ){
                score++;
            }
            scoreName = "Score: " + score;
            System.out.println("your score " + score);
        }
    }


    public int getScore(){
        return score;
    }

    public void dispose() {
        bird.dispose();
        bg.dispose();
        playScore.dispose();
        for(Tube tube: Tubes){
            tube.dispose();
        }
        System.out.println("PlayState Disposed");
    }
}
