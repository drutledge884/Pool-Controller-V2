package poolControllerUI;
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
import javax.swing.event.ListSelectionListener;
import javax.swing.event.ListSelectionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDateTime;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
@SuppressWarnings({ "unused", "serial" })

public class ChemicalModPanel extends JPanel 
{
	private JButton btnNewButton_1;
	private JButton btnNewButton_2;
	private Timer timer5;
	
	// Auto refreshes the screen allowing timers to work and parameters to be updated real time
	public ChemicalModPanel(JFrame currentFrame, Pool pool) 
	{
		setLayout(null);
		this.setBackground(Color.BLACK);

		timer5 = new Timer(0, new ActionListener()
		{
		    public void actionPerformed(ActionEvent x) 
		    {
		    	if(pool.getPanelIndex() == 4)
		    	{
		    		timer5.setDelay(50);
		    		currentFrame.getContentPane().removeAll();
		    		currentFrame.getContentPane().add(new ChemicalModPanel(currentFrame, pool));
		    		currentFrame.getContentPane().revalidate();
		    	}
		    	else if(pool.getPanelIndex() == 9)
		    	{
		    		timer5.stop();
		    		currentFrame.getContentPane().removeAll();
		    		currentFrame.getContentPane().add(new ChemicalOpSchedPanel(currentFrame, pool));
		    		currentFrame.getContentPane().revalidate();
		    	}
		}});
		timer5.setDelay(1000000000);
		timer5.start();
		
		displayPH(pool);
		displayStoredPH(pool);
	   	scheduleOpLabel(pool, currentFrame);
	   	toOpSched(pool, currentFrame);		
	   	pool.getChemical().scheduleCheck();
	}
	// Shows the current water pH
	public void displayPH(Pool pool)
	{
		JLabel tempLabel;
		Border border;
		tempLabel = new JLabel("Current pH: " + pool.getChemical().getpH());
		if(pool.getChemical().getpH() > 7 && pool.getChemical().getpH() < 7.6)
		{
			border = BorderFactory.createLineBorder(Color.GREEN, 5);
		}
		else
		{
			border = BorderFactory.createLineBorder(Color.RED, 5);
		}
		tempLabel.setBorder(border);
		tempLabel.setFont(new Font("Courier", Font.BOLD, 18));
		tempLabel.setForeground(Color.white);
		tempLabel.setHorizontalAlignment(SwingConstants.CENTER);
		tempLabel.setBounds(350, 50, 300, 50);
		add(tempLabel);
	}
	// Shows the current air temperature
	public void displayStoredPH(Pool pool)
	{
		JLabel tempLabel;
		Border border;
		if(pool.getChemical().dateMatch(LocalDateTime.now(), pool.getChemical().getStoredDate()))
		{
			tempLabel = new JLabel("At " + pool.getChemical().getStoredDate().toString() + " the pH was " + pool.getChemical().getStoredPH());
			if(pool.getChemical().getStoredPH() > 7 && pool.getChemical().getStoredPH() < 7.6)
			{
				border = BorderFactory.createLineBorder(Color.GREEN, 5);
			}
			else
			{
				border = BorderFactory.createLineBorder(Color.RED, 5);
			}
		}
		else
		{
			tempLabel = new JLabel("No pending pH checks...");
			border = BorderFactory.createLineBorder(Color.BLUE, 5); 			
		}
		tempLabel.setBorder(border);
		tempLabel.setFont(new Font("Courier", Font.BOLD, 18));
		tempLabel.setForeground(Color.white);
		tempLabel.setHorizontalAlignment(SwingConstants.CENTER);
		tempLabel.setBounds(250, 150, 500, 50);
		add(tempLabel);
	}
	// Displays the "Schedule Operation" label
	public void scheduleOpLabel(Pool pool, JFrame currentFrame)
	{
		Border border = BorderFactory.createLineBorder(Color.WHITE, 5);
	   	JLabel lblNewLabel2 = new JLabel("Operation Scheduling");
		lblNewLabel2.setFont(new Font("Courier", Font.BOLD, 36));
		lblNewLabel2.setForeground(Color.WHITE);
		lblNewLabel2.setBorder(border);
		lblNewLabel2.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel2.setBounds(250, 500, 500, 50);
		add(lblNewLabel2);
	}
	// Opens Operation Schedulings
	public void toOpSched(Pool pool, JFrame currentFrame)
	{
		btnNewButton_1 = new JButton("Schedule an Operation");
		btnNewButton_1.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e) 
			{
				timer5.stop();
				pool.setPanelIndex(9);
				currentFrame.getContentPane().removeAll();
				currentFrame.getContentPane().add(new ChemicalOpSchedPanel(currentFrame, pool));
				currentFrame.getContentPane().revalidate();
			}
		});
		btnNewButton_1.setHorizontalAlignment(SwingConstants.CENTER);
		btnNewButton_1.setBounds(400, 600, 200, 50);
		btnNewButton_1.setEnabled(true);
		add(btnNewButton_1);
	}
}