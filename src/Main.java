import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

public class Main extends StateBasedGame {

	public Main(String name) {
		super(name);
	}

	public static void main(String[] args) {

		AppGameContainer appgc;
		Weapon.createWeapons();
		
		try {
			appgc = new AppGameContainer(new Main("Collider-luokan testaukseen"));
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
		//addState(new Play()); //id = 1
		//addState(new Story()); //id = 2
		addState(new EnemyGame()); //id = 3
		addState(new MapGame()); //id = 4
		addState(new MapGame2()); //id = 5
		
		this.getState(Menu.id).init(gc, this);
		//this.getState(Play.id).init(gc, this);
		//this.getState(Story.id).init(gc, this);
		this.getState(EnemyGame.id).init(gc, this);
		this.getState(MapGame.id).init(gc, this);
		this.getState(MapGame2.id).init(gc, this);
	}

}
