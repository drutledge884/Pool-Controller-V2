package poolControllerUI;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.Timer;
import javax.swing.border.EmptyBorder;
import poolControllerPD.Pool;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Image;
@SuppressWarnings({ "unused", "serial" })

public class PoolFrame extends JFrame 
{
	private JPanel contentPanel;
	private JFrame currentFrame;

	/**
	 * Launch the Pool
	 */
	public static void open(Pool pool) 
	{
		EventQueue.invokeLater(new Runnable() 
		{
			public void run() 
			{
				try 
				{
					PoolFrame frame = new PoolFrame(pool);
					frame.setVisible(true);
				} 
				catch (Exception e) 
				{
					e.printStackTrace();
				}
			}
		});
	}
	
	public PoolFrame(Pool pool) 
	{
		currentFrame = this;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(360, 50, 1000, 800);
		//creates a menu bar
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		//maintenance drop down menu
		JMenu mnNewMenu = new JMenu("Pool Maintainance");
		menuBar.add(mnNewMenu);
		//filter edit
		JMenuItem mntmNewMenuItem = new JMenuItem("Chemical Controls");
		mntmNewMenuItem.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent a)
			{
				pool.setPanelIndex(4);
				getContentPane().removeAll();
				getContentPane().add(new ChemicalModPanel(currentFrame, pool));
				getContentPane().revalidate();
			}
		});
		mnNewMenu.add(mntmNewMenuItem);
		//pump edit
		JMenuItem mntmNewMenuItem2 = new JMenuItem("Fill Pump Controls");
		mntmNewMenuItem2.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent b) 
			{
				pool.setPanelIndex(2);
				getContentPane().removeAll();
				getContentPane().add(new FillPumpModPanel(currentFrame, pool));
				getContentPane().revalidate();
			}
		});
		mnNewMenu.add(mntmNewMenuItem2);
		//lights edit
		JMenuItem mntmNewMenuItem3 = new JMenuItem("Light Controls");
		mntmNewMenuItem3.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent c) 
			{
				pool.setPanelIndex(6);
				getContentPane().removeAll();
				getContentPane().add(new LightsModPanel(currentFrame, pool));
				getContentPane().revalidate();
			}
		});
		mnNewMenu.add(mntmNewMenuItem3);
		//heater edit
		JMenuItem mntmNewMenuItem4 = new JMenuItem("Heater Controls");
		mntmNewMenuItem4.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent d) 
			{
				pool.setPanelIndex(5);
				getContentPane().removeAll();
				getContentPane().add(new HeatingModPanel(currentFrame, pool));
				getContentPane().revalidate();
			}
		});
		mnNewMenu.add(mntmNewMenuItem4);
		//pump edit
		JMenuItem mntmNewMenuItem5 = new JMenuItem("Filter Pump Controls");
		mntmNewMenuItem5.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				pool.setPanelIndex(3);
				getContentPane().removeAll();
				getContentPane().add(new FilterPumpModPanel(currentFrame, pool));
				getContentPane().revalidate();
			}
		});
		mnNewMenu.add(mntmNewMenuItem5);		
		//create j panel and add a Pool Home		
		contentPanel = new JPanel();
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPanel.setLayout(new BorderLayout(0, 0));
		
		setContentPane(contentPanel);
		pool.setPanelIndex(1);
		getContentPane().removeAll();
		getContentPane().add(new HomePanel(pool));
		getContentPane().revalidate();
	}
}
