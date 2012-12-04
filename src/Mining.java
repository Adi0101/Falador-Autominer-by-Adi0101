import org.powerbot.core.script.job.Task;
import org.powerbot.core.script.job.state.Node;
import org.powerbot.game.api.methods.Walking;
import org.powerbot.game.api.methods.Widgets;
import org.powerbot.game.api.methods.interactive.Players;
import org.powerbot.game.api.methods.node.SceneEntities;
import org.powerbot.game.api.methods.tab.Inventory;
import org.powerbot.game.api.methods.widget.Camera;
import org.powerbot.game.api.wrappers.Area;
import org.powerbot.game.api.wrappers.Tile;
import org.powerbot.game.api.wrappers.node.SceneObject;

public class Mining extends Node{
	
		@Override
		public boolean activate() {
			final SceneObject rock = SceneEntities.getNearest(FaladorAutominer.MineOre);
			return (!Inventory.isFull() && Players.getLocal().isIdle() && !Widgets.get(762).validate() && !Widgets.get(11).validate() && rock != null && !FaladorAutominer.area.contains(Players.getLocal().getLocation()));
		}
		@Override
		public void execute() {
		final SceneObject rock = SceneEntities.getNearest(FaladorAutominer.MineOre);
		if(rock != null && rock.isOnScreen() && Players.getLocal().isIdle()){
			rock.interact("Mine");
			Task.sleep(1700,2000);
		}else if (rock !=null && !rock.isOnScreen() ){
			Camera.turnTo(rock);
			Walking.walk(rock);
			Task.sleep(1000, 1500);		}
		
       }
}