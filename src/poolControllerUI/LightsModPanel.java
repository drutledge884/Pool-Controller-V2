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

public class LightsModPanel extends JPanel 
{
	private JButton btnNewButton_1;
	private JButton btnNewButton_2;
	private Timer timer3;
	
	// Auto refreshes the screen allowing timers to work and parameters to be updated real time
	public LightsModPanel(JFrame currentFrame, Pool pool) 
	{
		setLayout(null);
		this.setBackground(Color.BLACK);

		timer3 = new Timer(0, new ActionListener()
		{
		    public void actionPerformed(ActionEvent x) 
		    {
		    	if(pool.getLights().getDT() == true)
		    	{
		    		pool.getLights().scheduleDownTime();
		    	}
		    	if(pool.getLights().getDT() == false)
		    	{
		    		pool.getLights().scheduleRunTime();
		    	}
		    		
		    	if(pool.getPanelIndex() == 6)
		    	{
		    		timer3.setDelay(50);
		    		currentFrame.getContentPane().removeAll();
		    		currentFrame.getContentPane().add(new LightsModPanel(currentFrame, pool));
		    		currentFrame.getContentPane().revalidate();
		    	}
		    	else if(pool.getPanelIndex() == 11)
		    	{
		    		timer3.stop();
		    		currentFrame.getContentPane().removeAll();
		    		currentFrame.getContentPane().add(new LightsOpSchedPanel(currentFrame, pool));
		    		currentFrame.getContentPane().revalidate();
		    	}
		}});
		timer3.setDelay(1000000000);
		timer3.start();
		
	   	displayLightsStatus(pool);
	   	activateLights(pool, currentFrame);
	   	deactivateLights(pool, currentFrame);
	   	displayRunTime(pool, currentFrame);
	   	displayDownTime(pool);
	   	scheduleOpLabel(pool, currentFrame);
	   	toOpSched(pool, currentFrame);		
	}
	// Turns the lights on
	public void activateLights(Pool pool, JFrame currentFrame)
	{
		btnNewButton_1 = new JButton("Activate Lights");
		btnNewButton_1.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e) 
			{
				pool.getLights().setActive(true);
				currentFrame.getContentPane().removeAll();
				currentFrame.getContentPane().add(new LightsModPanel(currentFrame, pool));
				currentFrame.getContentPane().revalidate();
			}
		});
		btnNewButton_1.setHorizontalAlignment(SwingConstants.CENTER);
		btnNewButton_1.setBounds(50, 150, 400, 50);
		btnNewButton_1.setEnabled(true);
		add(btnNewButton_1);
	}
	// Turns the lights off
	public void deactivateLights(Pool pool, JFrame currentFrame)
	{
		btnNewButton_2 = new JButton("Deactivate Lights");
		btnNewButton_2.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e) 
			{ 
				pool.getLights().setActive(false);
				currentFrame.getContentPane().removeAll();
				currentFrame.getContentPane().add(new LightsModPanel(currentFrame, pool));
				currentFrame.getContentPane().revalidate();
			}
		});
		btnNewButton_2.setHorizontalAlignment(SwingConstants.CENTER);
		btnNewButton_2.setBounds(50, 250, 400, 50);
		btnNewButton_2.setEnabled(true);
		add(btnNewButton_2);	
	}
	// Shows how long the lights have been on
	public void displayRunTime(Pool pool, JFrame currentFrame)
	{
		String lblStr = new String("Run Time: " + pool.getLights().runTimeToString());
		JLabel lblNewLabel3 = new JLabel(lblStr);
		lblNewLabel3.setFont(new Font("Courier", Font.BOLD, 26));
		lblNewLabel3.setForeground(Color.white);
		lblNewLabel3.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel3.setBounds(500, 150, 400, 50);
		Border border2 = BorderFactory.createLineBorder(Color.BLUE, 5);
		lblNewLabel3.setBorder(border2);
		add(lblNewLabel3);
	}
	// Shows how long the lights have been off
	public void displayDownTime(Pool pool)
	{
		String lblStr2 = new String("Down Time: " + pool.getLights().downTimeToString());
		JLabel lblNewLabel4 = new JLabel(lblStr2);
		lblNewLabel4.setFont(new Font("Courier", Font.BOLD, 26));
		lblNewLabel4.setForeground(Color.white);
		lblNewLabel4.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel4.setBounds(500, 250, 400, 50);
		Border border3 = BorderFactory.createLineBorder(Color.BLUE, 5);
		lblNewLabel4.setBorder(border3);
		add(lblNewLabel4);
	}
	// Display if the lights are on or not
	public void displayLightsStatus(Pool pool)
	{
		JLabel lblNewLabel2;
		if(pool.getLights().getActive())
		{
			lblNewLabel2 = new JLabel("Lights Status: On");
			Border border = BorderFactory.createLineBorder(Color.GREEN, 5);
			lblNewLabel2.setBorder(border);
		}
		else
		{
			lblNewLabel2 = new JLabel("Lights Status: Off");
			Border border = BorderFactory.createLineBorder(Color.RED, 5);
			lblNewLabel2.setBorder(border);
		}
			
		lblNewLabel2.setFont(new Font("Courier", Font.BOLD, 26));
		lblNewLabel2.setForeground(Color.white);
		lblNewLabel2.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel2.setBounds(300, 50, 400, 50);
		add(lblNewLabel2);
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
		lblNewLabel2.setBounds(250, 400, 500, 50);
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
				timer3.stop();
				pool.setPanelIndex(11);
				currentFrame.getContentPane().removeAll();
				currentFrame.getContentPane().add(new LightsOpSchedPanel(currentFrame, pool));
				currentFrame.getContentPane().revalidate();
			}
		});
		btnNewButton_1.setHorizontalAlignment(SwingConstants.CENTER);
		btnNewButton_1.setBounds(400, 500, 200, 50);
		btnNewButton_1.setEnabled(true);
		add(btnNewButton_1);
	}
}