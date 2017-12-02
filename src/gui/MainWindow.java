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
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.filechooser.FileNameExtensionFilter;

public class MainWindow{
	
	private JFrame frame;
	private JTextField searchField;
	private JButton searchBtn;
	private JTextArea resultsArea;
	private JFileChooser fc;
	private InputStream inStream = null;
    private OutputStream outStream = null;

	/**
	 * Launch the application.tet
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
		//Creates the main frame and set its specifications
		frame = new JFrame("MyGoogle");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setBounds(100, 100, 450, 300);
		
		//Makes the Menu bar
		makeMenuBar(frame);
		fillContentPane(frame);
		
		//Pack all components and set the window visible
		frame.pack();
		frame.setVisible(true);
		
	}
	
	public void makeMenuBar(JFrame frame) {
		//Initializes the top menu bar and set it to the main frame
		JMenuBar menuBar = new JMenuBar();
		frame.setJMenuBar(menuBar);
		
		//Creates the menu Help
		JMenu menu = new JMenu("Help");
		menuBar.add(menu);
		
		//Creates the item About, which will contain information about the system and how use it
		JMenuItem item = new JMenuItem("About");
		item.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) { /*openFile();*/ }
		});
		//add the item to the menu Help
		menu.add(item);
	}
	
	public void fillContentPane(JFrame frame) {
		
		//Creates a object container that will be used to add components to the main window
		//and sets its Layout as BorderLayut
		Container contentpane = frame.getContentPane();
		contentpane.setLayout(new BorderLayout());
		
		//Creates a panel that will be set to the north position of the main window
		JPanel northPanel = new JPanel();
		northPanel.setLayout(new GridLayout(2,1));
		
		//Creates a generic panel that will be used multiple times to add components to contentpane
		JPanel panel = new JPanel();
		
		//Creates the text field where the user will type the word they want to search
		searchField = new JTextField("Type your search");
		searchField.setColumns(40);
		
		//Sets a focus listener to the text field to show the "Type your search here" message, 
		// which will disappear when the user clicks on the text field, 
		// and reappear if the is no text and the user unfocus the text field
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
		
		//adds the search field to the generic panel
		panel.add(searchField);
		
		//Initializes the "Search" button
		//once pressed will capture the text on the text field and start the search
		searchBtn = new JButton("Search");
		searchBtn.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {  }
		});
		
		
		
		//add the search button to the generic panel
		panel.add(searchBtn);
		
		//add the generic panel to the north panel
		northPanel.add(panel);
		
		//resets the generic panel so it can be used again
		panel = new JPanel();
		
		//Initiazes the "Add File" button
		//which will be used to add new files to the database
		JButton addBtn = new JButton("Add a File...");
		
		//sets a action listener to the button
		//eclipse-workspace/MyGoogle/db
		addBtn.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {  
				fc = new JFileChooser();
				int returnVal = fc.showOpenDialog(frame);
				if (returnVal == JFileChooser.APPROVE_OPTION) {
					File selectedFile = fc.getSelectedFile();
	                String filePath = selectedFile.getAbsolutePath();
	                InputStream inStream = null;
	                OutputStream outStream = null;
	                try{
	                    File source =new File(filePath);
	                    File dest =new File(System.getProperty("user.dir") + "/db",selectedFile.getName());
	                    inStream = new FileInputStream(source);
	                    outStream = new FileOutputStream(dest);

	                    byte[] buffer = new byte[1024];

	                    int length;
	                    while ((length = inStream.read(buffer)) > 0){
	                        outStream.write(buffer, 0, length);
	                    }

	                    if (inStream != null)inStream.close();
	                    if (outStream != null)outStream.close();
	                    
	                    JOptionPane.showMessageDialog(null,"File added successfully");
	                	
	                	}catch(IOException e1){
	                		JOptionPane.showMessageDialog(null,"File couldn't be added ");
	                }
	            }
			}
		});
		
		//adds the button to the generic panel
		panel.add(addBtn);
		
		//Initializes the "List Files" button
		//which will show a new window showing all files in the dabatase
		//this new window can be used to remove files from the database
		JButton listBtn = new JButton("List/Remove Files...");
		addBtn.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {  }
		});
		
		//add the button to the generic panel
		panel.add(listBtn);
		
		//add the generic panel to the north panel
		northPanel.add(panel);
		
		//add the north panel to the north position on the main frame
		contentpane.add(northPanel,BorderLayout.NORTH);
		
		//initializes the a text area that will show the results from the search
		resultsArea = new JTextArea("Results from search", 10, 40);
		resultsArea.setFont(new Font("Arial", Font.ITALIC,12));
		
		//makes it so the text area cant be written on, edited
		resultsArea.setEditable(false);
		
		//add the text area to the center position on the main frame
		contentpane.add(resultsArea,BorderLayout.CENTER);
	}
}
