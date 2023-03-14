package poolControllerUI;
import javax.swing.*;  
import java.awt.event.*; 
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.Timer;
import javax.swing.border.Border;

import poolControllerPD.*;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.BorderFactory;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.ListSelectionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDateTime;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
@SuppressWarnings({ "unused", "serial" })

public class LightsOpSchedPanel extends JPanel
{
	private LocalDateTime init, term;
	private boolean downTime;
	private JTextField textField1, textField2;
	
	// Constructor
	public LightsOpSchedPanel(JFrame currentFrame, Pool pool)
	{
		this.setLayout(null);
		this.setBackground(Color.BLACK);	
		
		checkBox(pool, currentFrame);  
		scheduleOpLabel(pool, currentFrame);
		enterInitiatorLabel(pool, currentFrame);
		enterTerminatorLabel(pool, currentFrame);
		acceptInitiator(pool, currentFrame);
		acceptTerminator(pool, currentFrame);
		back(pool, currentFrame);
		enter(pool, currentFrame);
	}
	// Title of the panel
	public void scheduleOpLabel(Pool pool, JFrame currentFrame)
	{
		Border border = BorderFactory.createLineBorder(Color.WHITE, 5);
	   	JLabel lblNewLabel2 = new JLabel("Operation Scheduling");
		lblNewLabel2.setFont(new Font("Courier", Font.BOLD, 36));
		lblNewLabel2.setForeground(Color.WHITE);
		lblNewLabel2.setBorder(border);
		lblNewLabel2.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel2.setBounds(250, 50, 500, 50);
		this.add(lblNewLabel2);
	}
	// Tells the user to enter a start time to schedule an operation
	public void enterInitiatorLabel(Pool pool, JFrame currentFrame)
	{
		String lblStr = new String("Enter Start Time: dd/mm/yyyy hh:mm");
		JLabel lblNewLabel3 = new JLabel(lblStr);
		lblNewLabel3.setFont(new Font("Courier", Font.BOLD, 26));
		lblNewLabel3.setForeground(Color.white);
		lblNewLabel3.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel3.setBounds(50, 125, 550, 50);
		add(lblNewLabel3);
	}
	// Tells the user to enter an end time to schedule an operation
	public void enterTerminatorLabel(Pool pool, JFrame currentFrame)
	{
		String lblStr = new String("Enter End Time: dd/mm/yyyy hh:mm");
		JLabel lblNewLabel3 = new JLabel(lblStr);
		lblNewLabel3.setFont(new Font("Courier", Font.BOLD, 26));
		lblNewLabel3.setForeground(Color.white);
		lblNewLabel3.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel3.setBounds(50, 275, 550, 50);
		add(lblNewLabel3);
	}
	// Accepts the initiator date text
	public void acceptInitiator(Pool pool, JFrame currentFrame)
	{
		textField1 = new JTextField(20);
		textField1.setBounds(50, 200, 400, 50);
		add(textField1);
	}
	// Accepts the terminator date text
	public void acceptTerminator(Pool pool, JFrame currentFrame)
	{
		textField2 = new JTextField(20);
		textField2.setBounds(50, 350, 400, 50);
		add(textField2);
	}
	// Determines if it needs to schedule a run time or a down time
	public void checkBox(Pool pool, JFrame currentFrame)
	{
		JCheckBox c1 = new JCheckBox("Down Time?", false);
		c1.setBounds(700, 200, 200, 50);
		add(c1);
		c1.addItemListener(new ItemListener() 
		{    
            public void itemStateChanged(ItemEvent e) 
            {                 
            	if(e.getStateChange() == 1)
            	{
            		pool.getLights().setDT(true);
            		
            	}
            	else
            	{
            		pool.getLights().setDT(false);
            	}
            }    
         });  
	}
	// Returns user to mod screen
	public void back(Pool pool, JFrame currentFrame)
	{
		JButton btnNewButton_2 = new JButton("Back");
		btnNewButton_2.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e) 
			{ 
				pool.setPanelIndex(6);
				currentFrame.getContentPane().removeAll();
				currentFrame.getContentPane().add(new LightsModPanel(currentFrame, pool));
				currentFrame.getContentPane().revalidate();
			}
		});
		btnNewButton_2.setHorizontalAlignment(SwingConstants.CENTER);
		btnNewButton_2.setBounds(50, 650, 200, 50);
		btnNewButton_2.setEnabled(true);
		add(btnNewButton_2);	
	}
	// Confirms user date entry
	public void enter(Pool pool, JFrame currentFrame)
	{
		JButton btnNewButton_2 = new JButton("Confirm Operation");
		btnNewButton_2.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e) 
			{ 
				pool.getLights().setOP(true);
				pool.getLights().setInitiate(pool.getLights().stringToLocalDateTime(textField1.getText()));
				pool.getLights().setTerminate(pool.getLights().stringToLocalDateTime(textField2.getText()));
				pool.setPanelIndex(6);
				currentFrame.getContentPane().removeAll();
				currentFrame.getContentPane().add(new LightsModPanel(currentFrame, pool));
				currentFrame.getContentPane().revalidate();
			}
		});
		btnNewButton_2.setHorizontalAlignment(SwingConstants.CENTER);
		btnNewButton_2.setBounds(700, 350, 200, 50);
		btnNewButton_2.setEnabled(true);
		add(btnNewButton_2);	
	}
}
