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

			return (Inventory.isFull() && Players.getLocal().isIdle() && FaladorAutominer.area.contains(Players.getLocal().getLocation()));
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