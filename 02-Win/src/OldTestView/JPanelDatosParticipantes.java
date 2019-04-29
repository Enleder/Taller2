package OldTestView;

import javax.swing.JPanel;
import java.awt.GridLayout;
import javax.swing.border.TitledBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.Font;
import javax.swing.UIManager;
import java.awt.Color;

public class JPanelDatosParticipantes extends JPanel {

	private static final long serialVersionUID = 1L;
	private JTextField txtNombreArtistico;
	private JTextField txtNombreApellido;
	private JTextField txtEdad;
	private JTextField txtEspecialidadArtistica;
	private JButton btnGuardarParticipante;
	private JButton btnSalir;

	public JPanelDatosParticipantes() {
		setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Datos del Participante",
				TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		setLayout(new GridLayout(5, 2, 5, 5));

		JLabel lblNombreArtistico = new JLabel("Nombre Artistico");
		lblNombreArtistico.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNombreArtistico.setLabelFor(txtNombreArtistico);
		add(lblNombreArtistico);

		txtNombreArtistico = new JTextField();
		txtNombreArtistico.setToolTipText("Ingrese Nombre Artistico");
		add(txtNombreArtistico);
		txtNombreArtistico.setColumns(10);

		JLabel lblNombreYApellido = new JLabel("Nombre y Apellido");
		lblNombreYApellido.setFont(new Font("Tahoma", Font.BOLD, 11));
		add(lblNombreYApellido);

		txtNombreApellido = new JTextField();
		lblNombreYApellido.setLabelFor(txtNombreApellido);
		txtNombreApellido.setToolTipText("Ingrese Nombre Artistico");
		txtNombreApellido.setColumns(10);
		add(txtNombreApellido);

		JLabel lblEdad = new JLabel("Edad");
		lblEdad.setFont(new Font("Tahoma", Font.BOLD, 11));
		add(lblEdad);

		txtEdad = new JTextField();
		lblEdad.setLabelFor(txtEdad);
		txtEdad.setToolTipText("Ingrese Nombre Artistico");
		txtEdad.setColumns(10);
		add(txtEdad);

		JLabel lblEspecialidadartistica = new JLabel("EspecialidadArtistica");
		lblEspecialidadartistica.setFont(new Font("Tahoma", Font.BOLD, 11));
		add(lblEspecialidadartistica);

		txtEspecialidadArtistica = new JTextField();
		lblEspecialidadartistica.setLabelFor(txtEspecialidadArtistica);
		txtEspecialidadArtistica.setToolTipText("Ingrese Nombre Artistico");
		txtEspecialidadArtistica.setColumns(10);
		add(txtEspecialidadArtistica);

		btnGuardarParticipante = new JButton("Guardar Participante");
		btnGuardarParticipante.setFont(new Font("Tahoma", Font.BOLD, 11));
		add(btnGuardarParticipante);

		btnSalir = new JButton("Salir");
		btnSalir.setFont(new Font("Tahoma", Font.BOLD, 11));
		add(btnSalir);

	}

	public String getTxtNombreArtistico() {
		return txtNombreArtistico.getText();
	}

	public void setTxtNombreArtistico(String txtNombreArtistico) {
		this.txtNombreArtistico.setText(txtNombreArtistico);
	}

	public JButton getBtnGuardarParticipante() {
		return btnGuardarParticipante;
	}

	public JButton getBtnSalir() {
		return btnSalir;
	}
}
