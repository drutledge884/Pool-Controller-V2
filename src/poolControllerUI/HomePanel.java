package poolControllerUI;

import poolControllerPD.*;
import javax.swing.JPanel;
import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.border.Border;
import java.awt.*;
import poolControllerUI.*;
import java.awt.Color;
import java.awt.Font;
@SuppressWarnings({ "unused", "serial" })

public class HomePanel extends JPanel 
{
	/**
	 * Create the panel.
	 */
	public HomePanel(Pool pool) 
	{
		setLayout(null);
		this.setBackground(Color.BLACK);
		JLabel lblNewLabel = new JLabel("Swimming Pool Main Control Hub V2");
		Border border = BorderFactory.createLineBorder(Color.RED, 5);
		lblNewLabel.setForeground(Color.white);
		lblNewLabel.setBorder(border);
		lblNewLabel.setFont(new Font("Courier", Font.BOLD, 36));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setVerticalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(80, 300, 800, 85);
		add(lblNewLabel);
	}
}