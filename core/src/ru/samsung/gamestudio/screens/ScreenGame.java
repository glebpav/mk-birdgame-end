package ru.samsung.gamestudio.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.utils.ScreenUtils;
import ru.samsung.gamestudio.MyGdxGame;
import ru.samsung.gamestudio.characters.Bird;
import ru.samsung.gamestudio.characters.Tube;

import static ru.samsung.gamestudio.MyGdxGame.SCR_HEIGHT;
import static ru.samsung.gamestudio.MyGdxGame.SCR_WIDTH;

public class ScreenGame implements Screen {

    MyGdxGame myGdxGame;

    Bird bird;
    Texture backgroundTexture;

    int tubeCount = 3;
    Tube[] tubes;

    int gamePoints;
    boolean isGameOver;

    public ScreenGame(MyGdxGame myGdxGame) {
        this.myGdxGame = myGdxGame;

        initTubes();
        backgroundTexture = new Texture("backgrounds/game_bg.png");
        bird = new Bird(20, SCR_HEIGHT / 2, 10, 250, 200);
    }


    @Override
    public void show() {
        gamePoints = 0;
        isGameOver = false;
        initTubes();
    }

    @Override
    public void render(float delta) {

        if (isGameOver) {
            System.out.println("Game Over");
            Gdx.app.exit();
        }

        if (Gdx.input.justTouched()) {
            bird.onClick();
        }
        bird.fly();

        for (Tube tube : tubes) {
            tube.move();
            if (tube.isHit(bird)) {
                isGameOver = true;
                System.out.println("hit");
            }
        }

        ScreenUtils.clear(1, 0, 0, 1);
        myGdxGame.camera.update();
        myGdxGame.batch.setProjectionMatrix(myGdxGame.camera.combined);
        myGdxGame.batch.begin();

        myGdxGame.batch.draw(backgroundTexture, 0, 0, SCR_WIDTH, SCR_HEIGHT);
        bird.draw(myGdxGame.batch);
        for (Tube tube : tubes) {
            tube.draw(myGdxGame.batch);
        }

        myGdxGame.batch.end();
    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {
        backgroundTexture.dispose();
        for (int i = 0; i < tubeCount; i++) {
            tubes[i].dispose();
        }
    }

    void initTubes() {
        tubes = new Tube[tubeCount];
        for (int i = 0; i < tubeCount; i++) {
            tubes[i] = new Tube(tubeCount, i);
        }
    }

}
