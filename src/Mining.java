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
	final Area area = new Area(new Tile[] {		
			new Tile(3009, 3355, 0), new Tile(3010, 3355, 0),
			new Tile(3011, 3355, 0),new Tile(3012, 3355, 0),
			new Tile(3013, 3355, 0), new Tile(3014, 3355, 0),
			new Tile(3015, 3355, 0),new Tile(3016, 3355, 0),
			new Tile(3017, 3355, 0),new Tile(3018, 3355, 0),
			new Tile(3009, 3356, 0), new Tile(3010, 3356, 0),
			new Tile(3011, 3356, 0),new Tile(3012, 3356, 0),
			new Tile(3013, 3356, 0), new Tile(3014, 3356, 0),
			new Tile(3015, 3356, 0),new Tile(3016, 3356, 0),
			new Tile(3017, 3356, 0),new Tile(3018, 3356, 0),
			new Tile(3009, 3357, 0), new Tile(3010, 3357, 0),
			new Tile(3011, 3357, 0),new Tile(3012, 3357, 0),
			new Tile(3013, 3357, 0), new Tile(3014, 3357, 0),
			new Tile(3015, 3357, 0),new Tile(3016, 3357, 0),
			new Tile(3017, 3357, 0),new Tile(3018, 3357, 0),				
			new Tile(3009, 3358, 0), new Tile(3010, 3358, 0),
			new Tile(3011, 3358, 0),new Tile(3012, 3358, 0),
			new Tile(3013, 3358, 0), new Tile(3014, 3358, 0),
			new Tile(3015, 3358, 0),new Tile(3016, 3358, 0),
			new Tile(3017, 3358, 0),new Tile(3018, 3358, 0),				
			new Tile(3009, 3359, 0), new Tile(3010, 3359, 0),
			new Tile(3011, 3359, 0),new Tile(3012, 3359, 0),
			new Tile(3013, 3359, 0), new Tile(3014, 3359, 0),
			new Tile(3015, 3359, 0),new Tile(3016, 3359, 0),
			new Tile(3017, 3359, 0),new Tile(3018, 3359, 0),				
			new Tile(3009, 3360, 0), new Tile(3010, 3360, 0),
			new Tile(3011, 3360, 0),new Tile(3012, 3360, 0),
			new Tile(3013, 3360, 0), new Tile(3014, 3360, 0),
			new Tile(3015, 3360, 0),new Tile(3016, 3360, 0),
			new Tile(3017, 3360, 0),new Tile(3018, 3360, 0),	
			new Tile(3009, 3361, 0), new Tile(3010, 3361, 0),
			new Tile(3011, 3361, 0),new Tile(3012, 3361, 0),
			new Tile(3013, 3361, 0), new Tile(3014, 3361, 0),
			new Tile(3015, 3361, 0),new Tile(3016, 3361, 0),
			new Tile(3017, 3361, 0),new Tile(3018, 3361, 0),
	});
		@Override
		public boolean activate() {
			final SceneObject rock = SceneEntities.getNearest(FaladorAutominer.MineOre);
			return (!Inventory.isFull() && Players.getLocal().isIdle() && !Widgets.get(762).validate() && !Widgets.get(11).validate() && rock != null && !area.contains(Players.getLocal().getLocation()));
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