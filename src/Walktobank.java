import org.powerbot.core.script.job.Task;
import org.powerbot.core.script.job.state.Node;
import org.powerbot.game.api.methods.Calculations;
import org.powerbot.game.api.methods.Walking;
import org.powerbot.game.api.methods.interactive.Players;
import org.powerbot.game.api.methods.node.SceneEntities;
import org.powerbot.game.api.methods.tab.Inventory;
import org.powerbot.game.api.util.Filter;
import org.powerbot.game.api.wrappers.Area;
import org.powerbot.game.api.wrappers.Tile;
import org.powerbot.game.api.wrappers.node.SceneObject;

public class Walktobank extends Node{
	private final Tile DOOR_TILE = new Tile(3061, 3374, 0);
		@Override
		public boolean activate() {
			
			return (Inventory.isFull() && !FaladorAutominer.area.contains(Players.getLocal().getLocation()));
		}
		@Override
		public void execute() {
			SceneObject stairs = SceneEntities.getNearest(new Filter<SceneObject>(){
				public boolean accept(SceneObject arg0) {
				return arg0.getId() == 30943;
				}

				});
				SceneObject minedoor = SceneEntities.getNearest(new Filter<SceneObject>(){
					public boolean accept(SceneObject arg0){
						return arg0.getId() == 11714 && Calculations.distance(arg0, DOOR_TILE) <= 2;
					}
				});
				
				SceneObject bankBooth = SceneEntities.getNearest(11758);

			if(stairs != null && stairs.isOnScreen() && minedoor == null){
				stairs.interact("Climb-up", "Stairs");
				Task.sleep(1030, 1400);
				
			}
		    else if(stairs != null && !stairs.isOnScreen() && minedoor == null){ 	
			Walking.walk(stairs);
			Task.sleep(1300,1900);
			}
			
		    else if(minedoor != null && minedoor.isOnScreen() && stairs == null){
				minedoor.interact("Open", "Door");
				Task.sleep(1000,1400);
		    }
		    if(stairs == null && minedoor == null && bankBooth != null && !bankBooth.isOnScreen()){
		    	Walking.walk(bankBooth);
		    	Task.sleep(1500, 2000);
		    }
       }
}