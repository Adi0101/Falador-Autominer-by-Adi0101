import org.powerbot.core.script.job.Task;
import org.powerbot.core.script.job.state.Node;
import org.powerbot.game.api.methods.interactive.Players;
import org.powerbot.game.api.methods.node.SceneEntities;
import org.powerbot.game.api.methods.tab.Inventory;
import org.powerbot.game.api.methods.widget.DepositBox;
import org.powerbot.game.api.wrappers.node.SceneObject;


public class EntranceDeposit extends Node{

	@Override
	public boolean activate() {
		SceneObject DungBox = SceneEntities.getNearest(25937);
			
		
		return (DungBox != null);
	}

	@Override
	public void execute() {
		SceneObject DungDoor = SceneEntities.getNearest(52864);
		if(!DepositBox.isOpen() && Players.getLocal().isIdle()){
		DepositBox.open();
		Task.sleep(1000,1500);
		}
		if(DepositBox.isOpen()){
		DepositBox.depositInventory();
		DepositBox.close();
		FaladorAutominer.oresbankedordropped+=28;
		Task.sleep(1000,1500);
		
		}if(!DepositBox.isOpen() && !Inventory.isFull()){
			DungDoor.interact("Exit");
			Task.sleep(1000, 1500);
		}
		
	}

}
