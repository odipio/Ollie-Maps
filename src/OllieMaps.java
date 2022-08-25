import java.awt.BasicStroke;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JTextField;

/*
 * Name: Oliver DiPuccio
 * Assignment: Project04 - GUI Class
 * Due Date: 12/5/2021
 */

public class OllieMaps extends JPanel {
	
	private static final long serialVersionUID = 1L;

	public String to;
	public String from;
	int index1 = -1;
	int index2 = -1;
	boolean isTrue = false;
	
	Graph graph;
	JFrame window;
	JPanel panel;
	JPanel panel2;
	JPanel panel3;
	JPanel panel4;
	JPanel panel5;
	JLabel titleLabel;
	JLabel byLabel;
	JLabel startLabel;
	JLabel endLabel;
	JLabel dash1;
	JLabel dash2;
	JTextField field;
	JButton goButton;
	JButton toggleUnits;
	JButton toggleAddress;
	JList<String> list1;
	JList<String> list2;
	Path p1;
	Path p2;
	Path p3;

	public OllieMaps() {
		graph = new Graph("MapInformation.txt");
		
		int i = 0;
		String arr[] = new String[Graph.vertexMap.values().size()];
		for(Vertex v : Graph.vertexMap.values()) {
			arr[i] = v.address; i++;
		}
	
		window = new JFrame("Ollie Maps");
		panel = new JPanel();
		panel2 = new JPanel();
		panel3 = new JPanel();
		panel4 = new JPanel();
		panel5 = new JPanel();
		titleLabel = new JLabel("Ollie Maps");
		byLabel = new JLabel("Developed by: Oliver DiPuccio");
		startLabel = new JLabel("Select Start Location:");
		endLabel = new JLabel("Select End Location:");
		dash1 = new JLabel("--------------");
		dash2 = new JLabel("--------------");
		field = new JTextField(50);
		goButton = new JButton("Go!");
		toggleUnits = new JButton("Toggle Units (miles/minutes)");
		toggleAddress = new JButton("Toggle symbol/address");
		titleLabel.setHorizontalAlignment(JLabel.CENTER);
		titleLabel.setFont(new Font("Times New Roman", Font.PLAIN, 23));
		byLabel.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		panel2.setLayout(new GridBagLayout());
		panel3.setLayout(new GridBagLayout());
		panel5.setLayout(new GridBagLayout());
		list1 = new JList<String>(arr);
		list2 = new JList<String>(arr);
		
		GridBagConstraints c1 = new GridBagConstraints();
		GridBagConstraints c2 = new GridBagConstraints();
		GridBagConstraints c3 = new GridBagConstraints();
		
		panel.add(titleLabel, BorderLayout.NORTH);
		
		c1.gridx = 0;
		c1.gridy = 0;
		panel2.add(startLabel, c1);
		c1.gridy++;
		panel2.add(dash1, c1);
		c1.gridy++;
		panel2.add(list1, c1);
		
		c2.gridx = 0;
		c2.gridy = 0;
		panel3.add(endLabel, c2);
		c2.gridy++;
		panel3.add(dash2, c2);
		c2.gridy++;
		panel3.add(list2, c2);
		
		c3.gridwidth = 2;
		c3.gridx = 0;
		c3.gridy = 0;
		panel5.add(field, c3);
		c3.gridy++;
		panel5.add(goButton, c3);
		c3.gridy++;
		panel5.add(toggleAddress, c3);
		c3.gridy++;
		panel5.add(toggleUnits, c3);
		c3.gridy++;
		panel5.add(byLabel, c3);
		
		window.add(panel, BorderLayout.NORTH);
		window.add(panel2, BorderLayout.WEST);
		window.add(panel3, BorderLayout.EAST);
		window.add(panel4, BorderLayout.CENTER);
		window.add(panel5, BorderLayout.SOUTH);
		
		window.setBounds(50, 50, 500, 500);
		window.setAlwaysOnTop(true);
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setResizable(false);
		window.getContentPane().add(this);
		window.setVisible(true);
		window.setSize(680, 600);
		field.setEditable(false);
		field.setHorizontalAlignment(JTextField.CENTER);
		
		//============================================================ Events
		goButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				p1 = Dijkstra.findShortestPath(graph, from, to);
				field.setText(p1.toString());
				isTrue = true;
				repaint();
			}
		}); 
		
		toggleUnits.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Graph.useDistCost = !Graph.useDistCost;
				p2 = Dijkstra.findShortestPath(graph, from, to);
				field.setText(p2.toString());
				isTrue = true;
				repaint();
			}
		});
		
		toggleAddress.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String print = "";
				p3 = Dijkstra.findShortestPath(graph, from, to);
				for(String s : Graph.vertexMap.keySet()) {
					for(int i = 0; i < p3.path.length() + 1; i++) {
						if(Graph.vertexMap.get(s).symbol.equals(p3.toString().substring(i, i + 1))) {
							print += Graph.vertexMap.get(s).address + " ";
						}
					}
				}
				field.setText(print + "| Cost: " + p3.cost + (Graph.useDistCost ? " miles" : " minutes" ));
			}
		});
		
		//============================================================ Mouse Pressed
		list1.addMouseListener(new MouseListener() {

			@Override
			public void mousePressed(MouseEvent e) {
			}

			@Override
			public void mouseReleased(MouseEvent e) {
			}

			@Override
			public void mouseExited(MouseEvent e) {
			}

			@Override
			public void mouseEntered(MouseEvent e) {
			}

			@Override
			public void mouseClicked(MouseEvent e) {
				 @SuppressWarnings("unchecked")
				 JList<String> leftList = (JList<String>) e.getSource();
				 index1 = leftList.locationToIndex(e.getPoint());
				 from = graph.getVertex(leftList.getSelectedValue()).symbol;
				 repaint();
			}
		});
		
		list2.addMouseListener(new MouseListener() {

			@Override
			public void mousePressed(MouseEvent e) {
			}

			@Override
			public void mouseReleased(MouseEvent e) {
			}

			@Override
			public void mouseExited(MouseEvent e) {
			}

			@Override
			public void mouseEntered(MouseEvent e) {
			}

			@Override
			public void mouseClicked(MouseEvent e) {
				 @SuppressWarnings("unchecked")
				 JList<String> rightList = (JList<String>) e.getSource();
				 index2 = rightList.locationToIndex(e.getPoint());
				 to = graph.getVertex(rightList.getSelectedValue()).symbol;
				 repaint();
			}
		});
	}

	//============================================================ Drawing
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		drawEdges(g);
		drawPathEdges(g);
		drawVertices(g);
		drawLetters(g);
		drawToVertex(g);
		drawFromVertex(g);
		
		isTrue = false;
	}

	public void drawEdges(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
		g2.setStroke(new BasicStroke(2));
		for(int i = 0; i < graph.map.values().size(); i++) {
			g2.drawLine(
					graph.map.get(graph.justEdges.get(i))[0],
					graph.map.get(graph.justEdges.get(i))[1],
					graph.map.get(graph.justEdges.get(i))[2],
					graph.map.get(graph.justEdges.get(i))[3]
					);
		}
	}
	
	public void drawPathEdges(Graphics g) {
		if(isTrue) {
			Graphics2D g2 = (Graphics2D) g;
			g2.setStroke(new BasicStroke(5));
			g2.setColor(Color.BLUE);
			for(String s : graph.map.keySet()) {
				for(int i = 0; i < p1.path.length() - 1; i++) {
					if(p1.path.substring(i, i + 2).equals(s)) {
						g2.drawLine(
								graph.map.get(s)[0],
								graph.map.get(s)[1],
								graph.map.get(s)[2],
								graph.map.get(s)[3]
							);
					}
				}
			}
		}
	}
	
	public void drawVertices(Graphics g) {
		g.setColor(Color.BLACK);
		g.fillOval(25, 20, 30, 30);	
		g.fillOval(100, 10, 30, 30);
		g.fillOval(150, 30, 30, 30);
		g.fillOval(300, 25, 30, 30);
		g.fillOval(375, 5, 30, 30);
		g.fillOval(65, 90, 30, 30);
		g.fillOval(160, 110, 30, 30);
		g.fillOval(260, 120, 30, 30);
		g.fillOval(340, 140, 30, 30);
		g.fillOval(5, 160, 30, 30);
		g.fillOval(125, 180, 30, 30);
		g.fillOval(260, 170, 30, 30);
		g.fillOval(20, 220, 30, 30);
		g.fillOval(150, 240, 30, 30);
		g.fillOval(250, 255, 30, 30);
		g.fillOval(330, 220, 30, 30);
		g.fillOval(60, 325, 30, 30);
		g.fillOval(120, 350, 30, 30);
		g.fillOval(180, 370, 30, 30);
		g.fillOval(330, 335, 30, 30);
	}
	
	public void drawLetters(Graphics g) {
		g.setColor(Color.WHITE);
		g.drawString("H", 36, 40);
		g.drawString("K", 111, 30);
		g.drawString("Q", 161, 50);
		g.drawString("E", 311, 45);
		g.drawString("I", 388, 25);
		g.drawString("B", 76, 110);
		g.drawString("G", 171, 130);
		g.drawString("A", 271, 140);
		g.drawString("O", 351, 160);
		g.drawString("L", 16, 180);
		g.drawString("C", 136, 200);
		g.drawString("P", 271, 190);
		g.drawString("R", 31, 240);
		g.drawString("F", 161, 260);
		g.drawString("D", 261, 275);
		g.drawString("T", 341, 240);
		g.drawString("J", 74, 345);
		g.drawString("M", 131, 370);
		g.drawString("N", 191, 390);
		g.drawString("S", 341, 355);
	}
	
	public void drawToVertex(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
		g2.setStroke(new BasicStroke(5));
		g.setColor(Color.GREEN);
		if(index1 == 0) g2.drawOval(260, 120, 30, 30);
		if(index1 == 1) g2.drawOval(65, 90, 30, 30);
		if(index1 == 2) g2.drawOval(125, 180, 30, 30);
		if(index1 == 3) g2.drawOval(250, 255, 30, 30);
		if(index1 == 4) g2.drawOval(300, 25, 30, 30);
		if(index1 == 5) g2.drawOval(150, 240, 30, 30);
		if(index1 == 6) g2.drawOval(160, 110, 30, 30);
		if(index1 == 7) g2.drawOval(25, 20, 30, 30);
		if(index1 == 8) g2.drawOval(375, 5, 30, 30);
		if(index1 == 9) g2.drawOval(60, 325, 30, 30);
		if(index1 == 10) g2.drawOval(100, 10, 30, 30);
		if(index1 == 11) g2.drawOval(5, 160, 30, 30);
		if(index1 == 12) g2.drawOval(120, 350, 30, 30);
		if(index1 == 13) g2.drawOval(180, 370, 30, 30);
		if(index1 == 14) g2.drawOval(340, 140, 30, 30);
		if(index1 == 15) g2.drawOval(260, 170, 30, 30);
		if(index1 == 16) g2.drawOval(150, 30, 30, 30);
		if(index1 == 17) g2.drawOval(20, 220, 30, 30);
		if(index1 == 18) g2.drawOval(330, 335, 30, 30);
		if(index1 == 19) g2.drawOval(330, 220, 30, 30);
	}
	
	public void drawFromVertex(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
		g2.setStroke(new BasicStroke(5));
		g.setColor(Color.RED);
		if(index2 == 0) g2.drawOval(260, 120, 30, 30);
		if(index2 == 1) g2.drawOval(65, 90, 30, 30);
		if(index2 == 2) g2.drawOval(125, 180, 30, 30);
		if(index2 == 3) g2.drawOval(250, 255, 30, 30);
		if(index2 == 4) g2.drawOval(300, 25, 30, 30);
		if(index2 == 5) g2.drawOval(150, 240, 30, 30);
		if(index2 == 6) g2.drawOval(160, 110, 30, 30);
		if(index2 == 7) g2.drawOval(25, 20, 30, 30);
		if(index2 == 8) g2.drawOval(375, 5, 30, 30);
		if(index2 == 9) g2.drawOval(60, 325, 30, 30);
		if(index2 == 10) g2.drawOval(100, 10, 30, 30);
		if(index2 == 11) g2.drawOval(5, 160, 30, 30);
		if(index2 == 12) g2.drawOval(120, 350, 30, 30);
		if(index2 == 13) g2.drawOval(180, 370, 30, 30);
		if(index2 == 14) g2.drawOval(340, 140, 30, 30);
		if(index2 == 15) g2.drawOval(260, 170, 30, 30);
		if(index2 == 16) g2.drawOval(150, 30, 30, 30);
		if(index2 == 17) g2.drawOval(20, 220, 30, 30);
		if(index2 == 18) g2.drawOval(330, 335, 30, 30);
		if(index2 == 19) g2.drawOval(330, 220, 30, 30);
	}
	
	//======================================================
	public static void main(String[] args) {
		Graph.returnAddress = false;
		new OllieMaps();
	}
	//======================================================
}

