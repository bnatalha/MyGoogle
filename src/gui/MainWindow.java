package gui;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

public class MainWindow{
	
	private JFrame frame;
	private JTextField searchField;
	private JButton searchBtn;
	private JTextArea resultsArea;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainWindow frame = new MainWindow();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public MainWindow() {
		
		frame = new JFrame("MyGoogle");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setBounds(100, 100, 450, 300);
		
		//Makes the Menu bar
		makeMenuBar(frame);
		fillContentPane(frame);
		
		frame.pack();
		frame.setVisible(true);
		
	}
	
	public void makeMenuBar(JFrame frame) {
		JMenuBar menuBar = new JMenuBar();
		frame.setJMenuBar(menuBar);
		
		JMenu menu = new JMenu("File");
		menuBar.add(menu);
		
		JMenuItem item = new JMenuItem("Add");
		item.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) { /*openFile();*/ }
		});
		menu.add(item);
		
		item = new JMenuItem("Remove");
		item.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) { /*openFile();*/ }
		});
		menu.add(item);
		
		item = new JMenuItem("List");
		item.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) { /*openFile();*/ }
		});
		menu.add(item);
		
		menu = new JMenu("Help");
		menuBar.add(menu);
		
		item = new JMenuItem("About");
		item.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) { /*openFile();*/ }
		});
		menu.add(item);
	}
	
	public void fillContentPane(JFrame frame) {
		
		Container contentpane = frame.getContentPane();
		contentpane.setLayout(new BorderLayout());
		
		JPanel northPanel = new JPanel();
		JPanel panel = new JPanel();
		
		searchField = new JTextField("Type your search");
		searchField.setColumns(40);
		searchField.addFocusListener(new FocusListener(){
			
			@Override
            public void focusLost(FocusEvent e) {
                if(searchField.getText().isEmpty()) {
                	searchField.setFont(new Font("Arial", Font.ITALIC,12));
                    searchField.setText("Type your search here");
                }
            }

            @Override
            public void focusGained(FocusEvent e) {
            	searchField.setFont(new Font("Arial", Font.BOLD,12));
                searchField.setText("");
            }
		});
		panel.add(searchField);
		
		searchBtn = new JButton("Search");
		searchBtn.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {  }
		});
		panel.add(searchBtn);
		northPanel.add(panel);
		contentpane.add(northPanel,BorderLayout.NORTH);
		
		resultsArea = new JTextArea("Results from search", 10, 40);
		resultsArea.setFont(new Font("Arial", Font.ITALIC,12));
		resultsArea.setEditable(false);
		contentpane.add(resultsArea,BorderLayout.CENTER);
	}
}
