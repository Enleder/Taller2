package View.ComponentesPanel;

import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;

public class JPanelGrillaParticipantesNoHayNinguno extends JPanel {

	private static final long serialVersionUID = 1L;

	public JPanelGrillaParticipantesNoHayNinguno() {
		setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Listado simple de Participantes",
				TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));

		this.setSize(464, 191);
		setLayout(new GridLayout(0, 1, 0, 0));

		JLabel lblNewLabel = new JLabel("NO HAY PARTICIPANTES REGISTRADOS PARA MOSTRAR");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		add(lblNewLabel);
		this.setVisible(true);
	}

}
