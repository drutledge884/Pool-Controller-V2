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

public class FillPumpModPanel extends JPanel 
{
	private JButton btnNewButton_1;
	private JButton btnNewButton_2;
	private Timer timer1;
	
	// Auto refreshes the screen allowing timers to work and parameters to be updated real time
	public FillPumpModPanel(JFrame currentFrame, Pool pool) 
	{
		setLayout(null);
		this.setBackground(Color.BLACK);
		
		timer1 = new Timer(0, new ActionListener()
		{
		    public void actionPerformed(ActionEvent y) 
		    {
		    	if(pool.getFillPump().getDT() == true)
		    	{
		    		pool.getFillPump().scheduleDownTime();
		    	}
		    	if(pool.getFillPump().getDT() == false)
		    	{
		    		pool.getFillPump().scheduleRunTime();
		    	}
		    		
		    	if(pool.getPanelIndex() == 2)
		    	{
		    		timer1.setDelay(50);
		    		currentFrame.getContentPane().removeAll();
		    		currentFrame.getContentPane().add(new FillPumpModPanel(currentFrame, pool));
		    		currentFrame.getContentPane().revalidate();
		    	}
		    	else if(pool.getPanelIndex() == 7)
		    	{
		    		timer1.stop();
		    		currentFrame.getContentPane().removeAll();
		    		currentFrame.getContentPane().add(new FillPumpOpSchedPanel(currentFrame, pool));
		    		currentFrame.getContentPane().revalidate();
		    	}
		}});
		timer1.setDelay(1000000000);
		timer1.start();
		
	   	atCapacityCheck(pool);
	   	displayFillPumpStatus(pool);
	   	activateFillPump(pool, currentFrame);
	   	deactivateFillPump(pool, currentFrame);
	   	displayRunTime(pool, currentFrame);
	   	displayDownTime(pool);
	   	scheduleOpLabel(pool, currentFrame);
	   	toOpSched(pool, currentFrame);		
	}
	// Turns the fill pump on
	public void activateFillPump(Pool pool, JFrame currentFrame)
	{
		btnNewButton_1 = new JButton("Activate Fill Pump");
		btnNewButton_1.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e) 
			{
				pool.getFillPump().setActive(true);
				currentFrame.getContentPane().removeAll();
				currentFrame.getContentPane().add(new FillPumpModPanel(currentFrame, pool));
				currentFrame.getContentPane().revalidate();
			}
		});
		btnNewButton_1.setHorizontalAlignment(SwingConstants.CENTER);
		btnNewButton_1.setBounds(50, 150, 400, 50);
		btnNewButton_1.setEnabled(true);
		this.add(btnNewButton_1);
	}
	// Turns the fill pump off
	public void deactivateFillPump(Pool pool, JFrame currentFrame)
	{
		btnNewButton_2 = new JButton("Deactivate Fill Pump");
		btnNewButton_2.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e) 
			{ 
				pool.getFillPump().setActive(false);
				currentFrame.getContentPane().removeAll();
				currentFrame.getContentPane().add(new FillPumpModPanel(currentFrame, pool));
				currentFrame.getContentPane().revalidate();
			}
		});
		btnNewButton_2.setHorizontalAlignment(SwingConstants.CENTER);
		btnNewButton_2.setBounds(50, 250, 400, 50);
		btnNewButton_2.setEnabled(true);
		this.add(btnNewButton_2);	
	}
	// Shows how long the fill pump has been on
	public void displayRunTime(Pool pool, JFrame currentFrame)
	{
		String lblStr = new String("Run Time: " + pool.getFillPump().runTimeToString());
		JLabel lblNewLabel3 = new JLabel(lblStr);
		lblNewLabel3.setFont(new Font("Courier", Font.BOLD, 26));
		lblNewLabel3.setForeground(Color.white);
		lblNewLabel3.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel3.setBounds(500, 150, 400, 50);
		Border border2 = BorderFactory.createLineBorder(Color.BLUE, 5);
		lblNewLabel3.setBorder(border2);
		add(lblNewLabel3);
	}
	// Shows how long the fill pump has been off
	public void displayDownTime(Pool pool)
	{
		String lblStr2 = new String("Down Time: " + pool.getFillPump().downTimeToString());
		JLabel lblNewLabel4 = new JLabel(lblStr2);
		lblNewLabel4.setFont(new Font("Courier", Font.BOLD, 26));
		lblNewLabel4.setForeground(Color.white);
		lblNewLabel4.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel4.setBounds(500, 250, 400, 50);
		Border border3 = BorderFactory.createLineBorder(Color.BLUE, 5);
		lblNewLabel4.setBorder(border3);
		add(lblNewLabel4);
	}
	// Display if the fill pump is on or not
	public void displayFillPumpStatus(Pool pool)
	{
		JLabel lblNewLabel2;
		if(pool.getFillPump().getActive())
		{
			lblNewLabel2 = new JLabel("Fill Pump Status: On");
			Border border = BorderFactory.createLineBorder(Color.GREEN, 5);
			lblNewLabel2.setBorder(border);
		}
			
		else
		{
			lblNewLabel2 = new JLabel("Fill Pump Status: Off");
			Border border = BorderFactory.createLineBorder(Color.RED, 5);
			lblNewLabel2.setBorder(border);
		}
			
		lblNewLabel2.setFont(new Font("Courier", Font.BOLD, 26));
		lblNewLabel2.setForeground(Color.white);
		lblNewLabel2.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel2.setBounds(500, 50, 400, 50);
		add(lblNewLabel2);
	}
	// Checks if the water level is at capacity
	public void atCapacityCheck(Pool pool)
	{
		JLabel lblNewLabel;
		if(pool.getFillPump().getAtCapacity()) 
		{
			lblNewLabel = new JLabel("At Capacity");
			Border border = BorderFactory.createLineBorder(Color.GREEN, 5);
			lblNewLabel.setBorder(border);
		}
			
		else
		{
			lblNewLabel = new JLabel("Below Capacity");
			Border border = BorderFactory.createLineBorder(Color.RED, 5);
			lblNewLabel.setBorder(border);
		}
		
		lblNewLabel.setFont(new Font("Courier", Font.BOLD, 26));
		lblNewLabel.setForeground(Color.white);
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(50, 50, 400, 50);
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
				timer1.stop();
				pool.setPanelIndex(7);
				currentFrame.getContentPane().removeAll();
				currentFrame.getContentPane().add(new FillPumpOpSchedPanel(currentFrame, pool));
				currentFrame.getContentPane().revalidate();
			}
		});
		btnNewButton_1.setHorizontalAlignment(SwingConstants.CENTER);
		btnNewButton_1.setBounds(400, 500, 200, 50);
		btnNewButton_1.setEnabled(true);
		add(btnNewButton_1);
	}
}