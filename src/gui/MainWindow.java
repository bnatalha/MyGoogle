package gui;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class MainWindow{
	
	private JFrame frame;
	private JFrame listFrame;
	private JTextField searchField;
	private JButton searchBtn;
	private JTextArea resultsArea;
	private JFileChooser fc;
	private DefaultListModel filesL;
	private JList<String>filesList;
	private Filter filter;

	/**
	 * Launch the application.tet
	 */
	public static void main(String[] args ) {
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
		
		//Create the Menu bar
		makeMenuBar(frame);
		fillContentPane(frame);
		
		File dir = new File(System.getProperty("user.dir") + "/db");
		String[] files = dir.list();
		
		filesL = new DefaultListModel();
		for(int i=0;i<files.length;i++) {
			filesL.addElement(files[i]);
		}
		
		//create the file list window
		listWindow();
		
		//Pack all components and set the window visible
		frame.pack();
		frame.setVisible(true);
		
		//initialize filter object
		filter = new Filter();
	}
	/**
	 * Function for createing the menu bar in the window
	 * @param frame frame where the menu bar will be created
	 */
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
			public void actionPerformed(ActionEvent e) { 
				JOptionPane.showMessageDialog(null,"This program is used to search the ocurrences\n"
						+ "of one or more specific keywords on the txt files in the folder database\n"
						+ "and returns all the occurrences of the keyword(s)"); 
			}
		});
		//add the item to the menu Help
		menu.add(item);
	}
	
	/**
	 * Function to fill the frame
	 * @param frame frame that will be filled
	 */
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
		searchField.setColumns(30);
		
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
			public void actionPerformed(ActionEvent e) {  
				String searchStr = searchField.getText();
				ArrayList<String> bledWords = filter.getBlacklistedWords(searchStr); 
				if(!bledWords.isEmpty()) {
					JOptionPane.showMessageDialog(null,"Uma ou mais palavras invalidas: "
							+ "\n" + bledWords); 
				}
				resultsArea.setText(null);
				
			}
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
					addToDb(selectedFile);
					
	            }
			}
		});
		
		//adds the button to the generic panel
		panel.add(addBtn);
		
		//Initializes the "List Files" button
		//which will show a new window showing all files in the dabatase
		//this new window can be used to remove files from the database
		JButton listBtn = new JButton("List/Remove Files...");
		listBtn.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				listFrame.pack();
				listFrame.setVisible(true);
				frame.setEnabled(false);
			}
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
	/**
	 * 
	 * Function responsible for adding a selected file to the folder db
	 * 
	 * @param  selectedFile file that will be added
	 * */
	public void addToDb(File selectedFile) {
		
        String filePath = selectedFile.getAbsolutePath();
        InputStream inStream = null;
        OutputStream outStream = null;
        try{
        	//source of the file
            File source =new File(filePath);
            //destination of the file(creates a file with same name on the db folder)
            File dest =new File(System.getProperty("user.dir") + "/db",selectedFile.getName());
            inStream = new FileInputStream(source);
            outStream = new FileOutputStream(dest);
            
            //copy the content of the source file to the copy file
            byte[] buffer = new byte[1024];
            int length;
            while ((length = inStream.read(buffer)) > 0){
                outStream.write(buffer, 0, length);
            }
            
            //close the output and the input
            if (inStream != null)inStream.close();
            if (outStream != null)outStream.close();
            
            //add the name of the file to the file list
            filesL.addElement(selectedFile.getName());
            JOptionPane.showMessageDialog(null,"File added successfully");
        	
        	}catch(IOException e1){
        		JOptionPane.showMessageDialog(null,"File couldn't be added ");
        }
	}
	
	public void listWindow() {
		//Initialize the list window frame
		listFrame = new JFrame("File list");
		listFrame.setBounds(150, 150, 450, 300);
		
		//initialize content pane so it can be filled
		Container contentpane = listFrame.getContentPane();
		
		//set the layout as borderlayout
		contentpane.setLayout(new BorderLayout());
		
		//create a generic panel
		JPanel panel = new JPanel();
		
		//create a JList that receives the names of the files, in the db directory, to show
		filesList = new JList<String>(filesL);
		contentpane.add(filesList,BorderLayout.CENTER);
		
		//create the Remove button and add it to the generic panel
		JButton rmvBtn = new JButton("Remove file...");
		panel.add(rmvBtn);
		rmvBtn.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				int response = JOptionPane.showConfirmDialog(null, "Do you want to continue?", "Confirm",
						JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
				if (response == JOptionPane.YES_OPTION) {
					int index = filesList.getSelectedIndex();
					String name = (String) filesL.getElementAt(index); 
					removeFile(System.getProperty("user.dir") + "/db/" + name);
					filesL.removeElementAt(index);
				 }
			}
		});
		
		//add the panel to the south location of the window
		contentpane.add(panel,BorderLayout.SOUTH);
		
		//re-enable the main window
		listFrame.addWindowListener( new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent we) {
                frame.setEnabled(true);
            }
        } );
		
		//pack and make the window invisible
		listFrame.pack();
	}
	
	public void removeFile(String path){
		//create a file object with the path to the selected file and delete it
		File file = new File(path);
		file.delete();
	}
}
