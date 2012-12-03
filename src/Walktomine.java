import org.powerbot.core.script.job.Task;
import org.powerbot.core.script.job.state.Node;
import org.powerbot.game.api.methods.Calculations;
import org.powerbot.game.api.methods.Walking;
import org.powerbot.game.api.methods.node.SceneEntities;
import org.powerbot.game.api.methods.tab.Inventory;
import org.powerbot.game.api.util.Filter;
import org.powerbot.game.api.wrappers.Area;
import org.powerbot.game.api.wrappers.Tile;
import org.powerbot.game.api.wrappers.node.SceneObject;

public class Walktomine extends Node{
	private final Tile DOOR_TILE = new Tile(3061, 3374, 0);

	
		@Override
		public boolean activate() {
			SceneObject downstairs = SceneEntities.getNearest(new Filter<SceneObject>(){
				public boolean accept(SceneObject arg0){
					return arg0.getId() == 30944;
				}
			});
			return (Inventory.getCount() == 0 && downstairs != null);
		}
		@Override
		public void execute() {
			SceneObject minedoor = SceneEntities.getNearest(new Filter<SceneObject>(){
				public boolean accept(SceneObject arg0){
					return arg0.getId() == 11714 && Calculations.distance(arg0, DOOR_TILE) <= 2;
				}
			});
			SceneObject downstairs = SceneEntities.getNearest(new Filter<SceneObject>(){
				public boolean accept(SceneObject arg0){
					return arg0.getId() == 30944;
				}
			});
			SceneObject bankBooth = SceneEntities.getNearest(11758);
			if(downstairs != null && !downstairs.isOnScreen()){
				Walking.walk(downstairs);
				Task.sleep(1500, 2000);
			}else if (minedoor != null && minedoor.isOnScreen() && downstairs != null && downstairs.isOnScreen()){
				minedoor.interact("Open", "Door");
				Task.sleep(1000,1400);
			}if (minedoor == null && downstairs != null && downstairs.isOnScreen()){
				downstairs.interact("Climb-down", "Stairs");
				Task.sleep(1700,2000);
				}

       }
}