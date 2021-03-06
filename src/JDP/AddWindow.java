package JDP;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Calendar;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 * Class used to add Task data to the ToDoList
 * @author Charles Henry
 *
 */
public class AddWindow extends JFrame implements ActionListener{
	
	private static JLabel taskName;
	private static JLabel startDate;
	private static JLabel endDate;
	private static JLabel priority;
	private static JLabel percComp;
	private static JLabel category;
	private static JLabel note;
	private static JTextField taskNameDisplay;
	private static JPanel startDatePanel;
	private static JPanel endDatePanel;
	private static JComboBox priorityCombo;
	private static JTextField percCompDisplay;
	private static JComboBox categoryCombo;
	private static JTextField noteDisplay;
	private static JPanel mainPanel;
	private static JPanel lowerPanel;
	private static JButton add;
	private static JButton cancel;
	
	private static JComboBox dayCombo;
	private static JComboBox monthCombo;
	private static JComboBox yearCombo;
	private static JComboBox endDayCombo;
	private static JComboBox endMonthCombo;
	private static JComboBox endYearCombo;
	
	private static Calendar cal;
	
	private static String[] priorityList = {"Low", "Medium", "High", "Other"};
	private static String[] categoryList = {"Personal", "Social", "Business", "School", "Other"};
	private static String[] day = {"1","2","3","4","5","6","7","8","9","10","11","12","13","14","15","16","17","18","19","20","21","22","23","24","25","26","27","28","29","30","31"};
	private static String[] month = {"1","2","3","4","5","6","7","8","9","10","11","12"};
	private static String[] year;
	/**
	 * Method used to create the Add window
	 */
	public AddWindow()
	{	
		int tYear;
		
		Dimension dimen = Toolkit.getDefaultToolkit().getScreenSize();
		this.setLayout(new BorderLayout());
		this.setTitle("Add Task");
		this.setSize(350,220);
		this.setResizable(false);
		this.setLocation((int)dimen.getWidth()/2 - this.getWidth()/2, (int)dimen.getHeight()/2 - this.getHeight()/2);
		
		cal = Calendar.getInstance();
		year = new String[21];
		for(int i=0; i < 21; i++){
			tYear = cal.get(Calendar.YEAR);
			year[i] = Integer.toString(tYear - 10 + i);
		}
		
		taskName = new JLabel("Task Name: ");
		startDate = new JLabel("Start Date: ");
		endDate = new JLabel("End Date: ");
		priority = new JLabel("Priority: ");
		percComp = new JLabel("Percent Complete: ");
		category = new JLabel("Category: ");
		note = new JLabel("Note: ");
		taskNameDisplay  = new JTextField("");
		startDatePanel 	 = new JPanel(new GridLayout(1,3));
		dayCombo = new JComboBox(day);
		monthCombo = new JComboBox(month);
		yearCombo = new JComboBox(year);
		startDatePanel.add(yearCombo);
		startDatePanel.add(monthCombo);
		startDatePanel.add(dayCombo);
		
		endDatePanel = new JPanel(new GridLayout(1,3));
		endDayCombo = new JComboBox(day);
		endMonthCombo = new JComboBox(month);
		endYearCombo = new JComboBox(year);
		endDatePanel.add(endYearCombo);
		endDatePanel.add(endMonthCombo);
		endDatePanel.add(endDayCombo);
		
		priorityCombo 	 = new JComboBox(priorityList);
		percCompDisplay  = new JTextField("");
		categoryCombo  	 = new JComboBox(categoryList);
		noteDisplay  	 = new JTextField("");
		
		mainPanel = new JPanel(new GridLayout(7, 2));				//Create a panel with a grid layout
		mainPanel.add(taskName);		mainPanel.add(taskNameDisplay);
		mainPanel.add(startDate);		mainPanel.add(startDatePanel);
		mainPanel.add(endDate);			mainPanel.add(endDatePanel);
		mainPanel.add(priority);		mainPanel.add(priorityCombo);
		mainPanel.add(percComp);		mainPanel.add(percCompDisplay);
		mainPanel.add(category);		mainPanel.add(categoryCombo);
		mainPanel.add(note);			mainPanel.add(noteDisplay);
		this.getContentPane().add(BorderLayout.CENTER, mainPanel);	//Add the panel to the CENTER of the BorderLayout
		
		add = new JButton("Add");
		cancel = new JButton("Cancel");
		add.addActionListener(this);
		cancel.addActionListener(this);

		
		lowerPanel = new JPanel(new FlowLayout());
		lowerPanel.add(add);
		lowerPanel.add(cancel);
		this.getContentPane().add(BorderLayout.SOUTH, lowerPanel);	//Add the panel to the SOUTH of the BorderLayout
		/**
		 * Method used to clear the text fields when the window is closed
		 */
		this.addWindowListener(new WindowAdapter(){
			public void windowClosing(WindowEvent we)
		    {
		        clearText();
		    }
		});
		clearText();
		validate();
	}
	
	/**
	 * Method used to reset the window to its default state, empty text fields etc
	 */
	public void clearText()
	{
		taskNameDisplay.setText("");
		
		dayCombo.setSelectedItem(Integer.toString(cal.get(Calendar.DATE)));
		monthCombo.setSelectedItem(Integer.toString(cal.get(Calendar.MONTH) + 1));
		yearCombo.setSelectedItem(Integer.toString(cal.get(Calendar.YEAR)));

		endDayCombo.setSelectedItem(Integer.toString(cal.get(Calendar.DATE)));
		endMonthCombo.setSelectedItem(Integer.toString(cal.get(Calendar.MONTH) + 1));
		endYearCombo.setSelectedItem(Integer.toString(cal.get(Calendar.YEAR)));
		
		priorityCombo.setSelectedItem("Other");
		percCompDisplay.setText("");
		categoryCombo.setSelectedItem("Personal");
		noteDisplay.setText("");
	}
	/**
	 * Method used to track actions performed
	 */
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == add)
		{
			try
			{
				if(taskNameDisplay.getText().equals("") || percCompDisplay.getText().equals("") || noteDisplay.getText().equals(""))
				{
					JOptionPane.showMessageDialog(null, "Please fill in all of the fields.");
				}
				else if(taskNameDisplay.getText().equals("") || Integer.parseInt(percCompDisplay.getText()) < 0 || Integer.parseInt(percCompDisplay.getText()) > 100)
				{
					JOptionPane.showMessageDialog(null, "Please fill in a number from '0' to '100' in the percentage field.");
				}
				else if(!(Integer.parseInt(percCompDisplay.getText()) >= 0 || Integer.parseInt(percCompDisplay.getText()) <= 100))
				{
					JOptionPane.showMessageDialog(null, "Please fill in a number from '0' to '100' in the percentage field.");
				}
				else if(Integer.parseInt(percCompDisplay.getText()) >= 0 || Integer.parseInt(percCompDisplay.getText()) <= 100)
				{
					ToDoList.myTasks.add(new Task(taskNameDisplay.getText(),
							new myDate(Integer.parseInt((String) dayCombo.getSelectedItem()), Integer.parseInt((String) monthCombo.getSelectedItem()), Integer.parseInt((String) yearCombo.getSelectedItem())),
							new myDate(Integer.parseInt((String) endDayCombo.getSelectedItem()), Integer.parseInt((String) endMonthCombo.getSelectedItem()), Integer.parseInt((String) endYearCombo.getSelectedItem())),
							(String) priorityCombo.getSelectedItem(), Integer.parseInt(percCompDisplay.getText()), (String) categoryCombo.getSelectedItem(), noteDisplay.getText()));
					ToDoList.listModel.addElement(ToDoList.myTasks.get(ToDoList.myTasks.size()-1).getName());
					TaskPanel.listModel.addElement(ToDoList.myTasks.get(ToDoList.myTasks.size()-1).getName());
					clearText();
					ToDoList.add1.setVisible(false);
					ToDoList.exported = false;
					JOptionPane.showMessageDialog(null, "Task Added.");
				}
			}
			catch(NumberFormatException n)
			{
				JOptionPane.showMessageDialog(null, "Please fill in a number from '0' to '100' in the percentage field.");
			}
		}
		if(e.getSource() == cancel)
		{
			clearText();
			ToDoList.add1.setVisible(false);
		}
	}
}