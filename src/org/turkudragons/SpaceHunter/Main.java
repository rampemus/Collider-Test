package org.turkudragons.SpaceHunter;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

public class Main extends StateBasedGame {

	private Player oldPlayer;
	
	public Main(String name) {
		super(name);
	}

	public static void main(String[] args) {

		AppGameContainer appgc;
		Weapon.createWeapons();
		try {
			Sounds.initiateSounds();
		} catch (SlickException e) {
			e.printStackTrace();
		}
		
		try {
			appgc = new AppGameContainer(new Main("Space Hunter"));
			appgc.setDisplayMode(800, 600, false);
			appgc.setAlwaysRender(true);
			appgc.start();
		} catch (SlickException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void initStatesList(GameContainer gc) throws SlickException {
		addState(new Menu()); //id = 0
		//addState(new Play()); //id = 
		addState(new EnemyGame()); //id = 1
		addState(new MapGame()); //id = 2
		addState(new MapGame2()); //id = 3
		addState(new BossMap()); //id = 4
		addState(new GameOver()); //id = 5
		addState(new Story());	//id = 6
		addState(new Credits());//id = 7
		
		this.getState(0).init(gc, this);
		//this.getState(Play.id).init(gc, this);
		//this.getState(EnemyGame.id).init(gc, this);
		//this.getState(MapGame.id).init(gc, this);
		//this.getState(MapGame2.id).init(gc, this);
		//this.getState(BossMap.id).init(gc, this);
		//this.getState(GameOver.id).init(gc, this);
		//this.getState(Story.id).init(gc, this);
	}
	
	public Player getOldPlayer() {
		return oldPlayer;
	}
	public void setOldPlayer(Player oldPlayer) {
		this.oldPlayer = oldPlayer;
	}

}
