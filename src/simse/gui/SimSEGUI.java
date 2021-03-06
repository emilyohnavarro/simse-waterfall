/* File generated by: simse.codegenerator.guigenerator.GUIGenerator */
package simse.gui;

import simse.SimSE;
import simse.state.*;
import simse.logic.*;
import simse.engine.*;
import simse.explanatorytool.Branch;
import simse.explanatorytool.ExplanatoryTool;
import simse.explanatorytool.MultipleTimelinesBrowser;

import java.awt.event.*;
import java.awt.*;
import java.awt.Dimension;
import java.awt.Color;

import java.util.ArrayList;

import javax.swing.*;

public class SimSEGUI extends JFrame implements ActionListener {
	private TabPanel tabPanel;
	private AttributePanel attribPanel;
	private ActionPanel actionPanel;

	// Analyze menu:
	private JMenuBar menuBar; // menu bar at top of window
	private JMenu analyzeMenu; // analyze menu
	private JMenuItem analyzeSimItem; // menu item in "Analyze" menu

	private State state;
	private Logic logic;
	private Engine engine;
	private World world;
	private ExplanatoryTool expTool;
	private static MultipleTimelinesBrowser timelinesBrowser;
	private Branch branch; // branch associated with this particular game

	public SimSEGUI(Engine e, State s, Logic l, Branch branch,
			MultipleTimelinesBrowser browser) {
		this.branch = branch;
		timelinesBrowser = browser;
		reset(e, s, l);
	}

	public void reset(Engine e, State s, Logic l) {
		state = s;
		logic = l;
		engine = e;

		expTool = new ExplanatoryTool(this, state.getLogger().getLog(), branch,
				timelinesBrowser);

		attribPanel = new AttributePanel(this, state, engine);
		tabPanel = new TabPanel(this, state, logic, attribPanel);
		actionPanel = new ActionPanel(this, state, logic);

		// Set window title:
		String title = "SimSE";
		if (branch.getName() != null) {
			title = title.concat(" - " + branch.getName());
		}
		setTitle(title);

		menuBar = new JMenuBar();
		// Analyze menu:
		analyzeMenu = new JMenu("Analyze"); // "Analyze" menu
		analyzeSimItem = new JMenuItem("Analyze Simulation");
		analyzeMenu.add(analyzeSimItem);
		analyzeSimItem.addActionListener(this);
		menuBar.add(analyzeMenu);

		// Add menu bar to this frame:
		this.setJMenuBar(menuBar);

		// Create main panel:
		JPanel mainPane = new JPanel(new BorderLayout());
		mainPane.setPreferredSize(new Dimension(1024, 710));

		mainPane.add(tabPanel, BorderLayout.NORTH);
		mainPane.add(attribPanel, BorderLayout.SOUTH);
		world = new World(state, logic, this);
		mainPane.add(world, BorderLayout.CENTER);
		mainPane.add(actionPanel, BorderLayout.EAST);

		// Set main window frame properties:
		mainPane.setBackground(Color.white);
		addWindowListener(new ExitListener());
		setContentPane(mainPane);
		setVisible(true);
		setSize(getLayout().preferredLayoutSize(this));
		// Make it show up in the center of the screen:
		setLocationRelativeTo(null);
		validate();
		repaint();
	}

	public void actionPerformed(ActionEvent evt) {
		Object source = evt.getSource(); // get which component the action came
											// from
		if (source == analyzeSimItem) {
			if (expTool.getState() == Frame.ICONIFIED) {
				expTool.setState(Frame.NORMAL);
			}
			expTool.setVisible(true);
		}
	}

	public Engine getEngine() {
		return engine;
	}

	public State getSimSEState() {
		return state;
	}

	public World getWorld() {
		return world;
	}

	public AttributePanel getAttributePanel() {
		return attribPanel;
	}

	public TabPanel getTabPanel() {
		return tabPanel;
	}

	// forces gui to update, used when the game ends
	public void forceGUIUpdate() {
		tabPanel.setGUIChanged();
		attribPanel.setGUIChanged();
		update();
	}

	// Update the GUI to reflect the current state:
	public void update() {
		tabPanel.update();
		attribPanel.update();
		world.update();
		actionPanel.update();
		expTool.update();
		branch.update(state);
	}

	public void close() {
		branch.setClosed();
		if (!timelinesBrowser.isVisible() && SimSE.getNumOpenBranches() == 0) {
			System.exit(0);
		}
		timelinesBrowser.update();
	}

	public class ExitListener extends WindowAdapter {
		public void windowClosing(WindowEvent event) {
			close();
		}
	}
}