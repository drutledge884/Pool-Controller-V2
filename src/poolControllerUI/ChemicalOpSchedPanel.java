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

public class ChemicalOpSchedPanel extends JPanel
{
	private LocalDateTime init, term;
	private boolean downTime;
	private JTextField textField1, textField2, textField3;
	
	// Constructor
	public ChemicalOpSchedPanel(JFrame currentFrame, Pool pool)
	{
		this.setLayout(null);
		this.setBackground(Color.BLACK);	
		
		scheduleOpLabel(pool, currentFrame);
		enterInitiatorLabel(pool, currentFrame);
		acceptInitiator(pool, currentFrame);
		back(pool, currentFrame);
		enter(pool, currentFrame);
	}
	// Title of the panel
	public void scheduleOpLabel(Pool pool, JFrame currentFrame)
	{
		Border border = BorderFactory.createLineBorder(Color.RED, 5);
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
		String lblStr = new String("Enter Check Time: dd/mm/yyyy hh:mm");
		JLabel lblNewLabel3 = new JLabel(lblStr);
		lblNewLabel3.setFont(new Font("Courier", Font.BOLD, 26));
		lblNewLabel3.setForeground(Color.white);
		lblNewLabel3.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel3.setBounds(50, 125, 550, 50);
		add(lblNewLabel3);
	}
	// Accepts the initiator date text
	public void acceptInitiator(Pool pool, JFrame currentFrame)
	{
		textField1 = new JTextField(20);
		textField1.setBounds(50, 200, 400, 50);
		Border border = BorderFactory.createLineBorder(Color.RED, 5);
		textField1.setBorder(border);
		add(textField1);
	}
	// Returns user to mod screen
	public void back(Pool pool, JFrame currentFrame)
	{
		JButton btnNewButton_2 = new JButton("Back");
		btnNewButton_2.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e) 
			{ 
				pool.setPanelIndex(4);
				currentFrame.getContentPane().removeAll();
				currentFrame.getContentPane().add(new ChemicalModPanel(currentFrame, pool));
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
				pool.getChemical().setOP(true);
				pool.getChemical().setStoredDate(pool.getChemical().stringToLocalDateTime(textField1.getText()));
				pool.setPanelIndex(4);
				currentFrame.getContentPane().removeAll();
				currentFrame.getContentPane().add(new ChemicalModPanel(currentFrame, pool));
				currentFrame.getContentPane().revalidate();
			}
		});
		btnNewButton_2.setHorizontalAlignment(SwingConstants.CENTER);
		btnNewButton_2.setBounds(700, 350, 200, 50);
		btnNewButton_2.setEnabled(true);
		btnNewButton_2.setBackground(Color.RED);
		btnNewButton_2.setForeground(Color.WHITE);
		btnNewButton_2.setFont(new Font("Courier", Font.BOLD, 16));
		add(btnNewButton_2);	
	}
}
