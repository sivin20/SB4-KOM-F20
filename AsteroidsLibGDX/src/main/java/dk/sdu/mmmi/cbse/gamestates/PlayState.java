package dk.sdu.mmmi.cbse.gamestates;

import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import dk.sdu.mmmi.cbse.entities.Bullet;
import dk.sdu.mmmi.cbse.entities.Enemy;
import dk.sdu.mmmi.cbse.entities.EnemyBullet;
import dk.sdu.mmmi.cbse.entities.Player;
import dk.sdu.mmmi.cbse.managers.GameKeys;
import dk.sdu.mmmi.cbse.managers.GameStateManager;

import java.util.ArrayList;
import java.util.Random;

public class PlayState extends GameState {
	
	private ShapeRenderer sr;
	private Random r = new Random();
	private Player player;
	private Enemy enemy;
	private ArrayList<Bullet> bullets;
	private ArrayList<EnemyBullet> enemyBullets;
	
	public PlayState(GameStateManager gsm) {
		super(gsm);
	}
	
	public void init() {
		
		sr = new ShapeRenderer();

		bullets = new ArrayList<Bullet>();
		enemyBullets = new ArrayList<EnemyBullet>();

		player = new Player(bullets);

		enemy = new Enemy(enemyBullets);
		
	}
	
	public void update(float dt) {

		//get user input
		handleInput();

		//update player
		player.update(dt);

		// update player bullets
		for(int i = 0; i < bullets.size(); i++) {
			bullets.get(i).update(dt);
			if(bullets.get(i).shouldRemove()) {
				bullets.remove(i);
				i--;
			}
		}

		//update enemy
		enemy.shoot();
		enemy.setUp(r.nextBoolean());
		enemy.setLeft(r.nextBoolean());
		enemy.setRight(r.nextBoolean());
		enemy.update(dt);

		// update enemy bullets
		for(int i = 0; i < enemyBullets.size(); i++) {
			enemyBullets.get(i).update(dt);
			if(enemyBullets.get(i).shouldRemove()) {
				enemyBullets.remove(i);
				i--;
			}
		}
	}
	
	public void draw() {

		//draw player
		player.draw(sr);

		//draw bullets
		for(int i = 0; i < bullets.size(); i++) {
			bullets.get(i).draw(sr);
		}

		//draw enemy
		enemy.draw(sr);

		//draw enemy bullets
		for(int i = 0; i < enemyBullets.size(); i++) {
			enemyBullets.get(i).draw(sr);
		}
	}
	
	public void handleInput() {
		player.setLeft(GameKeys.isDown(GameKeys.LEFT));
		player.setRight(GameKeys.isDown(GameKeys.RIGHT));
		player.setUp(GameKeys.isDown(GameKeys.UP));
		if(GameKeys.isPressed(GameKeys.SPACE)) {
			player.shoot();
		}
	}
	
	public void dispose() {}
	
}









