import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.powerbot.core.event.listeners.PaintListener;
import org.powerbot.core.script.ActiveScript;
import org.powerbot.core.script.job.Job;
import org.powerbot.core.script.job.state.Node;
import org.powerbot.core.script.job.state.Tree;
import org.powerbot.game.api.Manifest;
import org.powerbot.game.api.methods.Calculations;
import org.powerbot.game.api.methods.Game;
import org.powerbot.game.api.methods.input.Mouse;
import org.powerbot.game.api.util.Filter;
import org.powerbot.game.api.util.Random;
import org.powerbot.game.api.util.Timer;
import org.powerbot.game.api.wrappers.Tile;
import org.powerbot.game.api.wrappers.node.SceneObject;



import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

@Manifest(authors = { "Adi0101" }, name = "Autominer", version = 1 )
public class FaladorAutominer extends ActiveScript implements PaintListener {

private final List<Node> jobsCollection = Collections.synchronizedList(new ArrayList<Node>());
private Tree jobContainer = null;

//Paint stuff
public final Timer timer = new Timer(0);
public static int oresbankedordropped=0;

private final Color color1 = new Color(102, 102, 102, 200);
private final Color color2 = new Color(0, 0, 0);
private final Color color3 = new Color(255, 255, 255);
private final Color color4 = new Color(51, 255, 51);

private final BasicStroke stroke1 = new BasicStroke(1);

private final Font font1 = new Font("Blackoak Std", 1, 15);
private final Font font2 = new Font("Adobe Arabic", 1, 10);
private final Font font3 = new Font("Arial", 1, 10);
private boolean guiWait = true;
gui g = new gui();

//ore Filters
public static Filter<SceneObject> MineOre;

private final static Tile IRON_TILE = new Tile(3037, 9775, 0);
private final static Tile COAL_TILE = new Tile(3037, 9762, 0);
private final static Tile ADAMANT_TILE = new Tile(3041, 9773, 0);
private final static Tile MITHRIL_TILE = new Tile(3036, 9772, 0);

private final static int ironoredrop=440;
private final static int mithriloredrop=447;
private final static int coaloredrop=447;
    
public static int oredrop;
public static Filter<SceneObject> IronFilter = new Filter<SceneObject>(){
	@Override
	public boolean accept(SceneObject arg0) {

	return (Calculations.distance(arg0, IRON_TILE) <= 3) && (arg0.getId() == 5773||arg0.getId() == 5775||arg0.getId() == 5774);


	}
};
public static Filter<SceneObject> CoalFilter = new Filter<SceneObject>(){
	@Override
	public boolean accept(SceneObject arg0) {

	return (Calculations.distance(arg0, COAL_TILE) <= 5) && (arg0.getId() == 5770||arg0.getId() == 5771);


	}
};
public static Filter<SceneObject> MithrilFilter = new Filter<SceneObject>(){
	@Override
	public boolean accept(SceneObject arg0) {

	return (Calculations.distance(arg0, MITHRIL_TILE) <= 4) && (arg0.getId() == 5784||arg0.getId() == 5786);


	}
};
public static Filter<SceneObject> AdamantFilter = new Filter<SceneObject>(){
	@Override
	public boolean accept(SceneObject arg0) {

	return (Calculations.distance(arg0, ADAMANT_TILE) <= 3) && (arg0.getId() == 5782||arg0.getId() == 5783);


	}
};







        public synchronized final void provide(final Node... jobs) {
                for (final Node job : jobs) {
                        if(!jobsCollection.contains(job)) {
                                jobsCollection.add(job);
                        }
                }
                jobContainer = new Tree(jobsCollection.toArray(new Node[jobsCollection.size()]));
        }

        public synchronized final void revoke(final Node... jobs) {
                for (final Node job : jobs) {
                        if(jobsCollection.contains(job)) {
                                jobsCollection.remove(job);
                        }
                }
                jobContainer = new Tree(jobsCollection.toArray(new Node[jobsCollection.size()]));
        }

        public final void submit(final Job... jobs) {
                for (final Job job : jobs) {
                        getContainer().submit(job);
                }
        }

        @Override
        public void onStart() {
        	g.setVisible(true);
        	while(guiWait == true) sleep(500);
        	provide(new Mining());

			
			
        }

        @Override
        public int loop() {

                if (jobContainer != null) {
                        final Node job = jobContainer.state();
                        if (job != null) {
                                jobContainer.set(job);
                                getContainer().submit(job);
                                job.join();
                        }
                }
                return Random.nextInt(10, 50);
        }
        public void onRepaint(Graphics g1) {
        	
        	g1.setColor(Color.CYAN);
        	g1.drawLine(Mouse.getX() - 5,Mouse.getY() -5, Mouse.getX() + 5, Mouse.getY() + 5);
        	g1.drawLine(Mouse.getX() - 5, Mouse.getY() + 5, Mouse.getX() + 5, Mouse.getY() - 5);
        	
        	if (Game.getClientState()  == 11) {
            Graphics2D g = (Graphics2D)g1;
            g.setColor(color1);
            g.fillRect(9, 333+63, 486, 110);
            g.setColor(color2);
            g.setStroke(stroke1);
            g.drawRect(9, 333+63, 486, 110);
            g.setFont(font1);
            g.setColor(color3);
            g.drawString("Adi0101's Autominer!", 55, 410);
            g.setFont(font2);
            g.setColor(color4);
            g.drawString("Time Running: " +timer.toElapsedString(), 19, 440);
            g.drawString("Ores Banked/Dropped: " +oresbankedordropped, 19, 461);
            g.setFont(font3);
            g.setColor(color3);
            g.drawString("Script by adi0101", 408, 502);
            }
        }
        class gui extends JFrame {
        	public gui() {
        		initComponents();
        	}

        	private void button1ActionPerformed(ActionEvent e) {
        		        	    String chosen = Mine.getSelectedItem().toString();
        		        	    String chosen2 = Bankdestination.getSelectedItem().toString();
                        		if(chosen.equals("Iron")){
                        			MineOre = IronFilter;
                        			oredrop = ironoredrop;
                        			
                        		}else if(chosen.equals("Coal")){
                        			MineOre = CoalFilter;
                        			oredrop = coaloredrop;

                        		}else if(chosen.equals("Mithril")){
                        			MineOre = MithrilFilter;
                        			oredrop = mithriloredrop;
                        		}else{
                        			MineOre = AdamantFilter;

                        		}
                        		
                        		if(chosen2.equals("Falador East")){
                					provide(new Walktobank());
                					provide(new FaladorBank());
                					provide(new Walktomine());
                					revoke(new Droping());
                					revoke(new MystEntrance());
                					}else if(chosen2.equals("Drop")){
                            			revoke(new Walktobank());
                            			revoke(new FaladorBank());
                            			revoke(new Walktomine());
                            			revoke(new MystEntrance());
                            			provide(new Droping());
                					} else{
                						revoke(new Walktobank());
                            			revoke(new FaladorBank());
                            			revoke(new Walktomine());
                            			revoke(new Droping());
                						provide(new MystEntrance());
                						provide(new EntranceDeposit());
                						
                					}
                						
                					
                					
                        		
                        		guiWait = false;
                        		this.dispose();
                        		
                        	}

        	private void initComponents() {
        		label1 = new JLabel();
        		label2 = new JLabel();
        		Mine = new JComboBox<>();
        		button1 = new JButton();
        		label3 = new JLabel();
        		Bankdestination = new JComboBox<>();

        		setFont(new Font("BankGothic Md BT", Font.PLAIN, 18));
        		Container contentPane = getContentPane();

        		label1.setText("Adi's Falador Autominer V1");
        		label1.setFont(new Font("BankGothic Md BT", Font.PLAIN, 24));

        		label2.setText("Mine:");
        		label2.setFont(new Font("Tahoma", Font.PLAIN, 16));

        		Mine.setModel(new DefaultComboBoxModel<>(new String[] {
        			"Iron",
        			"Coal",
        			"Mithril",
        			"Adamant"
        		}));

        		button1.setText("Start");
        		button1.addActionListener(new ActionListener() {
        			@Override
        			public void actionPerformed(ActionEvent e) {
        				button1ActionPerformed(e);
        			}
        		});

        		label3.setText("Banking?:");
        		label3.setFont(new Font("Tahoma", Font.PLAIN, 16));

        		Bankdestination.setModel(new DefaultComboBoxModel<>(new String[] {
        			"Falador East",
        			"Dungeoneering entrance",
        			"Drop"
        		}));

        		GroupLayout contentPaneLayout = new GroupLayout(contentPane);
        		contentPane.setLayout(contentPaneLayout);
        		contentPaneLayout.setHorizontalGroup(
        			contentPaneLayout.createParallelGroup()
        				.addGroup(contentPaneLayout.createSequentialGroup()
        					.addGroup(contentPaneLayout.createParallelGroup()
        						.addGroup(GroupLayout.Alignment.TRAILING, contentPaneLayout.createSequentialGroup()
        							.addGap(0, 372, Short.MAX_VALUE)
        							.addComponent(button1))
        						.addGroup(contentPaneLayout.createSequentialGroup()
        							.addGroup(contentPaneLayout.createParallelGroup()
        								.addGroup(contentPaneLayout.createSequentialGroup()
        									.addContainerGap()
        									.addGroup(contentPaneLayout.createParallelGroup()
        										.addComponent(label2, GroupLayout.PREFERRED_SIZE, 55, GroupLayout.PREFERRED_SIZE)
        										.addComponent(label3))
        									.addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
        									.addGroup(contentPaneLayout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
        										.addComponent(Bankdestination)
        										.addComponent(Mine)))
        								.addGroup(contentPaneLayout.createSequentialGroup()
        									.addGap(29, 29, 29)
        									.addComponent(label1)))
        							.addGap(0, 24, Short.MAX_VALUE)))
        					.addContainerGap())
        		);
        		contentPaneLayout.setVerticalGroup(
        			contentPaneLayout.createParallelGroup()
        				.addGroup(contentPaneLayout.createSequentialGroup()
        					.addContainerGap()
        					.addComponent(label1)
        					.addGap(24, 24, 24)
        					.addGroup(contentPaneLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
        						.addComponent(label2)
        						.addComponent(Mine, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
        					.addGap(11, 11, 11)
        					.addGroup(contentPaneLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
        						.addComponent(label3)
        						.addComponent(Bankdestination, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
        					.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 84, Short.MAX_VALUE)
        					.addComponent(button1)
        					.addContainerGap())
        		);
        		pack();
        		setLocationRelativeTo(getOwner());
        	}

        	private JLabel label1;
        	private JLabel label2;
        	private JComboBox<String> Mine;
        	private JButton button1;
        	private JLabel label3;
        	private JComboBox<String> Bankdestination;
        }
}
