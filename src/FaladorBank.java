import org.powerbot.core.script.job.Task;
import org.powerbot.core.script.job.state.Node;
import org.powerbot.game.api.methods.Widgets;
import org.powerbot.game.api.methods.interactive.Players;
import org.powerbot.game.api.methods.node.SceneEntities;
import org.powerbot.game.api.methods.tab.Inventory;
import org.powerbot.game.api.methods.widget.Bank;
import org.powerbot.game.api.wrappers.Area;
import org.powerbot.game.api.wrappers.Tile;
import org.powerbot.game.api.wrappers.node.SceneObject;


public class FaladorBank extends Node{

	
		@Override
		public boolean activate() {
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
			return (Inventory.isFull() && Players.getLocal().isIdle() && area.contains(Players.getLocal().getLocation()));
		}
		@Override
		public void execute() {
			SceneObject bankBooth = SceneEntities.getNearest(11758);
			
			if(Inventory.isFull() && bankBooth != null && bankBooth.isOnScreen() && !Widgets.get(762).validate()){
				bankBooth.interact("Bank", "Bank booth");
				Task.sleep(1500, 2000);
			}else if (Inventory.isFull() && Widgets.get(762).validate()){
				Bank.depositInventory();
				FaladorAutominer.oresbankedordropped+=28;
				Task.sleep(500,800);
			}if (!Inventory.isFull() && Widgets.get(762).validate()){
				Bank.close();
			}

       }
}