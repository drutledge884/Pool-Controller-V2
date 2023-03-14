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

public class HeatingModPanel extends JPanel 
{
	private JButton btnNewButton_1;
	private JButton btnNewButton_2;
	private Timer timer4;
	
	// Auto refreshes the screen allowing timers to work and parameters to be updated real time
	public HeatingModPanel(JFrame currentFrame, Pool pool) 
	{
		setLayout(null);
		this.setBackground(Color.BLACK);

		timer4 = new Timer(0, new ActionListener()
		{
		    public void actionPerformed(ActionEvent x) 
		    {
		    	if(pool.getHeating().getDT() == true)
		    	{
		    		pool.getHeating().scheduleDownTime();
		    	}
		    	if(pool.getHeating().getDT() == false)
		    	{
		    		pool.getHeating().scheduleRunTime();
		    	}
		    		
		    	if(pool.getPanelIndex() == 5)
		    	{
		    		timer4.setDelay(50);
		    		currentFrame.getContentPane().removeAll();
		    		currentFrame.getContentPane().add(new HeatingModPanel(currentFrame, pool));
		    		currentFrame.getContentPane().revalidate();
		    	}
		    	else if(pool.getPanelIndex() == 10)
		    	{
		    		timer4.stop();
		    		currentFrame.getContentPane().removeAll();
		    		currentFrame.getContentPane().add(new HeatingOpSchedPanel(currentFrame, pool));
		    		currentFrame.getContentPane().revalidate();
		    	}
		}});
		timer4.setDelay(1000000000);
		timer4.start();
		
	   	displayHeatingStatus(pool);
	   	activateHeating(pool, currentFrame);
	   	deactivateHeating(pool, currentFrame);
	   	displayRunTime(pool, currentFrame);
	   	displayDownTime(pool);
	   	scheduleOpLabel(pool, currentFrame);
	   	toOpSched(pool, currentFrame);		
	   	atTargetTempCheck(pool);
	   	displayCurrentWaterTemp(pool);
	   	displayCurrentAirTemp(pool);
	   	displayTargetAirTemp(pool);
	   	pool.getHeating().autoMaintain();
	}
	// Turns the heater on
	public void activateHeating(Pool pool, JFrame currentFrame)
	{
		btnNewButton_1 = new JButton("Activate Heater");
		btnNewButton_1.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e) 
			{
				pool.getHeating().setActive(true);
				currentFrame.getContentPane().removeAll();
				currentFrame.getContentPane().add(new HeatingModPanel(currentFrame, pool));
				currentFrame.getContentPane().revalidate();
			}
		});
		btnNewButton_1.setHorizontalAlignment(SwingConstants.CENTER);
		btnNewButton_1.setBounds(50, 250, 400, 50);
		btnNewButton_1.setEnabled(true);
		add(btnNewButton_1);
	}
	// Turns the heater off
	public void deactivateHeating(Pool pool, JFrame currentFrame)
	{
		btnNewButton_2 = new JButton("Deactivate Heater");
		btnNewButton_2.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e) 
			{ 
				pool.getHeating().setActive(false);
				currentFrame.getContentPane().removeAll();
				currentFrame.getContentPane().add(new HeatingModPanel(currentFrame, pool));
				currentFrame.getContentPane().revalidate();
			}
		});
		btnNewButton_2.setHorizontalAlignment(SwingConstants.CENTER);
		btnNewButton_2.setBounds(50, 350, 400, 50);
		btnNewButton_2.setEnabled(true);
		add(btnNewButton_2);	
	}
	// Shows how long the heater has been on
	public void displayRunTime(Pool pool, JFrame currentFrame)
	{
		String lblStr = new String("Run Time: " + pool.getHeating().runTimeToString());
		JLabel lblNewLabel3 = new JLabel(lblStr);
		lblNewLabel3.setFont(new Font("Courier", Font.BOLD, 26));
		lblNewLabel3.setForeground(Color.white);
		lblNewLabel3.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel3.setBounds(500, 250, 400, 50);
		Border border2 = BorderFactory.createLineBorder(Color.BLUE, 5);
		lblNewLabel3.setBorder(border2);
		add(lblNewLabel3);
	}
	// Shows how long the heater has been off
	public void displayDownTime(Pool pool)
	{
		String lblStr2 = new String("Down Time: " + pool.getHeating().downTimeToString());
		JLabel lblNewLabel4 = new JLabel(lblStr2);
		lblNewLabel4.setFont(new Font("Courier", Font.BOLD, 26));
		lblNewLabel4.setForeground(Color.white);
		lblNewLabel4.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel4.setBounds(500, 350, 400, 50);
		Border border3 = BorderFactory.createLineBorder(Color.BLUE, 5);
		lblNewLabel4.setBorder(border3);
		add(lblNewLabel4);
	}
	// Shows the current water temperature
	public void displayCurrentWaterTemp(Pool pool)
	{
		JLabel tempLabel;
		tempLabel = new JLabel("Water Temp: " + pool.getHeating().getMeasuredWaterTemp() + " deg");
		Border border = BorderFactory.createLineBorder(Color.BLUE, 5);
		tempLabel.setBorder(border);
		tempLabel.setFont(new Font("Courier", Font.BOLD, 18));
		tempLabel.setForeground(Color.white);
		tempLabel.setHorizontalAlignment(SwingConstants.CENTER);
		tempLabel.setBounds(50, 50, 250, 50);
		add(tempLabel);
	}
	// Shows the current air temperature
	public void displayCurrentAirTemp(Pool pool)
	{
		JLabel tempLabel;
		tempLabel = new JLabel("Air Temp: " + pool.getHeating().getMeasuredAirTemp() + " deg");
		Border border = BorderFactory.createLineBorder(Color.CYAN, 5);
		tempLabel.setBorder(border);
		tempLabel.setFont(new Font("Courier", Font.BOLD, 18));
		tempLabel.setForeground(Color.white);
		tempLabel.setHorizontalAlignment(SwingConstants.CENTER);
		tempLabel.setBounds(350, 50, 250, 50);
		add(tempLabel);
	}
	// Shows the target air temperature
	public void displayTargetAirTemp(Pool pool)
	{
		JLabel tempLabel;
		tempLabel = new JLabel("Target: " + pool.getHeating().getTargetWaterTemp() + " deg");
		Border border = BorderFactory.createLineBorder(Color.ORANGE, 5);
		tempLabel.setBorder(border);
		tempLabel.setFont(new Font("Courier", Font.BOLD, 18));
		tempLabel.setForeground(Color.white);
		tempLabel.setHorizontalAlignment(SwingConstants.CENTER);
		tempLabel.setBounds(650, 50, 250, 50);
		add(tempLabel);
	}
	// Display if the heater is on or not
	public void displayHeatingStatus(Pool pool)
	{
		JLabel lblNewLabel2;
		if(pool.getHeating().getActive())
		{
			lblNewLabel2 = new JLabel("Heater Status: On");
			Border border = BorderFactory.createLineBorder(Color.GREEN, 5);
			lblNewLabel2.setBorder(border);
		}
		else
		{
			lblNewLabel2 = new JLabel("Heater Status: Off");
			Border border = BorderFactory.createLineBorder(Color.RED, 5);
			lblNewLabel2.setBorder(border);
		}
			
		lblNewLabel2.setFont(new Font("Courier", Font.BOLD, 26));
		lblNewLabel2.setForeground(Color.white);
		lblNewLabel2.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel2.setBounds(500, 150, 400, 50);
		add(lblNewLabel2);
	}
	// Checks if the water temperature is at the target temp
	public void atTargetTempCheck(Pool pool)
	{
		JLabel lblNewLabel;
		if(pool.getHeating().getAtTargetTemp()) 
		{
			lblNewLabel = new JLabel("At Target Temp");
			Border border = BorderFactory.createLineBorder(Color.GREEN, 5);
			lblNewLabel.setBorder(border);
		}
			
		else
		{
			lblNewLabel = new JLabel("Below Target Temp");
			Border border = BorderFactory.createLineBorder(Color.RED, 5);
			lblNewLabel.setBorder(border);
		}
		
		lblNewLabel.setFont(new Font("Courier", Font.BOLD, 26));
		lblNewLabel.setForeground(Color.white);
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(50, 150, 400, 50);
		add(lblNewLabel);
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
				timer4.stop();
				pool.setPanelIndex(10);
				currentFrame.getContentPane().removeAll();
				currentFrame.getContentPane().add(new HeatingOpSchedPanel(currentFrame, pool));
				currentFrame.getContentPane().revalidate();
			}
		});
		btnNewButton_1.setHorizontalAlignment(SwingConstants.CENTER);
		btnNewButton_1.setBounds(400, 600, 200, 50);
		btnNewButton_1.setEnabled(true);
		add(btnNewButton_1);
	}
}