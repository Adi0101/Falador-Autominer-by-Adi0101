import org.powerbot.core.script.job.Task;
import org.powerbot.core.script.job.state.Node;
import org.powerbot.game.api.methods.Walking;
import org.powerbot.game.api.methods.node.SceneEntities;
import org.powerbot.game.api.methods.tab.Inventory;
import org.powerbot.game.api.methods.widget.Camera;
import org.powerbot.game.api.wrappers.node.SceneObject;


public class MystEntrance extends Node {

	@Override
	public boolean activate() {
		final SceneObject Dungdoor = SceneEntities.getNearest(52855);
		return (Inventory.isFull()&& Dungdoor != null);
	}

	@Override
	public void execute() {
		final SceneObject Dungdoor = SceneEntities.getNearest(52855);
		if(Dungdoor != null && Dungdoor.isOnScreen()){
			Dungdoor.interact("Enter","Mysterious entrance");
			Task.sleep(1500,2000);
		}else if (Dungdoor != null && !Dungdoor.isOnScreen()){
			Camera.turnTo(Dungdoor);
			Walking.walk(Dungdoor);
		}
	}

}
