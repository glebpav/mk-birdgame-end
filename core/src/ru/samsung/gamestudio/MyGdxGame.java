package ru.samsung.gamestudio;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import ru.samsung.gamestudio.screens.ScreenGame;

public class MyGdxGame extends Game {

	public SpriteBatch batch;
	public OrthographicCamera camera;

	public static final int SCR_WIDTH = 1280;
	public static final int SCR_HEIGHT = 720;

	public ScreenGame screenGame;

	@Override
	public void create() {
		batch = new SpriteBatch();
		camera = new OrthographicCamera();
		camera.setToOrtho(false, SCR_WIDTH, SCR_HEIGHT);

		screenGame = new ScreenGame(this);
		setScreen(screenGame);
	}

	@Override
	public void dispose() {
		batch.dispose();
	}
}
