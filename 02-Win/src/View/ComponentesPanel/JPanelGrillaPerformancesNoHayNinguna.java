package View.ComponentesPanel;

import javax.swing.*;
import java.awt.*;

public class JPanelGrillaPerformancesNoHayNinguna extends JPanel {

	private static final long serialVersionUID = 1L;

	public JPanelGrillaPerformancesNoHayNinguna() {

		this.setSize(464, 191);
		setLayout(new GridLayout(0, 1, 0, 0));

		JLabel lblNewLabel = new JLabel("NO HAY PERFORMANCES PARA MOSTRAR");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		add(lblNewLabel);
		this.setVisible(true);
	}

}
