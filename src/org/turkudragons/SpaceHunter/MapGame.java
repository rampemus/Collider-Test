package org.turkudragons.SpaceHunter;

import java.util.ArrayList;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.Music;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

public class MapGame extends BasicGameState {
	
	public static int id = 2;
	private Player player;
	//private String deltaNumber = "0";
	private Input input;
	private boolean isTile;
	public Music backgroundMusic;
	private boolean initialized = false;
	private ArrayList<Object> oList;
	private Map m;

	@Override
	public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
		if(!initialized) {
			oList = new ArrayList<Object>();
			m = new Map();
			//player and enemies
			player = new Player(414,400);
			oList.add(player);
			oList.add(new Flame_Tank(640,576));
			oList.add(new Flame_Tank(3200,1600));
			oList.add(new Flame_Tank(3500,1600));
			oList.add(new Flame_Tank(2800,1600));
			oList.add(new Flame_Tank(576,700));
			
			oList.add(new Flame_Tank(3200,1400));
			oList.add(new Flame_Tank(3500,1000));
			
			oList.add(new Dragonling_Drone(1900,1600));
			oList.add(new Dragonling_Drone(1700,1600));
			oList.add(new Dragonling_Drone(1200,1600));
			oList.add(new Dragonling_Drone(1500,1600));
			oList.add(new Dragonling_Drone(2000,1600));
			
			oList.add(new Alien(3000,600));
			oList.add(new Alien(960,500));
		    oList.add(new Dragonling_Drone(1200,500));
		    oList.add(new Dragonling_Drone(1100,500));
		    oList.add(new Dragonling_Drone(1300,500));
		    input = gc.getInput();
		  //map for the state
			m.add(4,1,5,6);
			
			m.add(2,1,6,2);
			m.add(2,1,10,2);
			m.add(2,1,8,2);
			
			m.add(4,1,5,11);
			m.add(4,1,5,16);
			m.add(4,1,5,21);
			m.add(2,1,6,6);
			m.add(2,1,8,6);
			m.add(2,1,6,14);
			m.add(2,1,8,14);
			m.add(4,1,13,6);
			m.add(4,1,13,11);
			m.add(4,1,13,16);
			m.add(2,1,10,10);
			m.add(2,1,8,10);
			m.add(2,1,5,22);
			m.add(1,1,8,22);
			m.add(4,1,18,26);
			m.add(1,1,19,26);
			m.add(1,1,29,26);
	    	m.add(4,1,39,26);
	    	m.add(3,1,21,24);
	    	m.add(3,1,30,24);
	    	m.add(2,1,37,21);
	    	m.add(1,1,14,8);
	    	m.add(2,1,24,8);
	    	m.add(4,1,42,23);
	    	m.add(4,1,42,19);
	    	m.add(1,1,40,26);
	    	m.add(1,1,50,26);
	    	m.add(1,1,42,23);
	    	m.add(2,1,51,23);
	    	m.add(4,1,59,26);
	    	m.add(4,1,59,21);
	    	m.add(2,1,42,20);
	    	m.add(3,1,47,16);
	    	m.add(1,1,49,16);
	    	m.add(1,1,49,17);
	    	m.add(4,1,59,16);
	    	m.add(4,1,59,12);
	    	m.add(2,1,51,13);
	    	m.add(2,1,45,10);
	    	m.add(2,1,42,10);
	    	m.add(2,1,35,8);
	    	m.add(2,1,30,6);
	    	
			//For The Win 16 by Håkan Eriksson, downloaded from epidemicsound.com
			backgroundMusic = new Music("res/Music.ogg");
			
			initialized = true;
		}
	}

	@Override
	public void render(GameContainer gc, StateBasedGame stg, Graphics g) throws SlickException {
		g.translate(-(int)(player.getX()-400),-(int)(player.getY()-300));
		m.display();
		//player.display(g);
		for (Object o : oList) {
			if ( o instanceof Visible) {
				((Visible)o).display(g);
			}
		}
		g.drawString("isTile:" + isTile,100,100);
	}

	@Override
	public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException {
		if(initialized) {
			backgroundMusic.loop(1.0f,0.1f);
			initialized = false;
		}
		player.updateInput(gc, m,delta, oList);
		//player.update(oList,m,delta);
		for (int i = oList.size()-1; i >= 0; i--) {
			Object o = oList.get(i);
			if ( o instanceof Active) {
				((Active)o).update(oList, m, delta);
			}
		}
		if(oList.size()<2) {
			((Main)sbg).setOldPlayer(player);
			sbg.getState(MapGame2.id).init(gc, sbg);
			sbg.enterState(MapGame2.id);
		}
		if (player.getHP() <= 0) {
			backgroundMusic.stop();
			((Main) sbg).setOldPlayer(null);
			sbg.getState(GameOver.id).init(gc, sbg);
			sbg.enterState(GameOver.id);
		}
		if(input.isKeyPressed(Input.KEY_ESCAPE)) {
			backgroundMusic.stop();
			((Main) sbg).setOldPlayer(null);
			sbg.getState(Menu.id).init(gc, sbg);
			sbg.enterState(Menu.id);
		}
		
		isTile = m.isTile(input.getMouseX(), input.getMouseY());
		
		//deltaNumber = "Delta: " + delta;
		// god-mode
		if (input.isKeyDown(Input.KEY_F)) {
			oList.add(new Item((int)((Collider)oList.get(0)).getX()+128,(int)((Collider)oList.get(0)).getY(),Collect.randomItem()));
		}
		if (input.isKeyDown(Input.KEY_G) && oList.size() > 1) {
			oList.remove(1);
		}
	}

	@Override
	public int getID() {
		// TODO Auto-generated method stub
		return MapGame.id;
	}
	
}
