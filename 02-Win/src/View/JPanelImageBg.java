package View;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.SwingConstants;
import java.awt.FlowLayout;

public class JPanelImageBg extends JPanel {

	/**
	 * Create the panel.
	 */
	public JPanelImageBg() {
		setBorder(null);
		setBackground(new java.awt.Color(2, 49, 75));
		setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));

		JLabel lblImageBG = new JLabel("");
		lblImageBG.setVerticalAlignment(SwingConstants.TOP);
		lblImageBG.setBackground(new java.awt.Color(2, 49, 75));
		lblImageBG.setHorizontalAlignment(SwingConstants.CENTER);
		lblImageBG.setIcon(new ImageIcon(JPanelImageBg.class.getResource("/imagenes/logoPrincipalConDevs.png")));
		add(lblImageBG);

	}

}
