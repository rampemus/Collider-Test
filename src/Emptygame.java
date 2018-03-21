import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

public class Emptygame extends BasicGameState {
	
	public static int id = 1;
	private Player player;
	private String deltaNumber = "0";

	@Override
	public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
		player = new Player(350,300);
	}

	@Override
	public void render(GameContainer gc, StateBasedGame stg, Graphics g) throws SlickException {
		player.display(g);
		g.drawString(deltaNumber,100,100);
	}

	@Override
	public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException {
		player.update(delta);
		player.updateInput(gc,delta);
		
		deltaNumber = "Delta: " + delta;
	}

	@Override
	public int getID() {
		// TODO Auto-generated method stub
		return Emptygame.id;
	}
	
}