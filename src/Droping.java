import org.powerbot.core.script.job.Task;
import org.powerbot.core.script.job.state.Node;
import org.powerbot.game.api.methods.interactive.Players;
import org.powerbot.game.api.methods.node.SceneEntities;
import org.powerbot.game.api.methods.tab.Inventory;
import org.powerbot.game.api.wrappers.node.Item;
import org.powerbot.game.api.wrappers.node.SceneObject;


public class Droping extends Node{

	@Override
	public boolean activate() {
		final SceneObject rock = SceneEntities.getNearest(FaladorAutominer.MineOre);
		return (Inventory.isFull() && Players.getLocal().isIdle() && rock != null);
	}

	@Override
	public void execute() {
		for(final Item i : Inventory.getItems()){
			if(i.getId() == FaladorAutominer.oredrop){
				Inventory.getItem(FaladorAutominer.oredrop).getWidgetChild().interact("Drop");
				FaladorAutominer.oresbankedordropped++;
			Task.sleep(900, 1000);
		}
		}
			
			

		}
}
	

