package OldTestView;

import java.awt.EventQueue;

import javax.swing.JInternalFrame;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import Controller.ControllerAltaParticipante;
import Controller.ControllerInicioProgramaListParticipantes;
import Exceptions.ParticipantesExceptions;
import Logic.Mensajes;
import Logic.TipoParticipante;
import ValueObjects.VOExtranjero;
import ValueObjects.VONacional;
import ValueObjects.VOParticipante;
import ValueObjects.VOParticipanteDetallado;
import java.awt.event.ActionListener;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;
import java.awt.event.ActionEvent;
import javax.swing.JComboBox;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.border.CompoundBorder;

public class JIFrameAltaParticipante extends JInternalFrame implements ActionListener {

	private static final long serialVersionUID = 1L;
	private JTextField txtNombreArtistico;
	private JTextField txtNombreApellido;
	private JTextField txtEdad;
	private JTextField txtEspacialidadArtistica;

	private JComboBox<Object> cboTipoParticipante;
	private JTextField txtDepartamento;
	private JTextField txtLocallidad;
	private JTextField txtPaisOrigen;
	private JTextField txtAniosUruguay;
	private JTable table;
	private String tipo;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					JIFrameAltaParticipante frame = new JIFrameAltaParticipante();
					frame.setVisible(true);

				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	private void limpiarPantalla() {

		txtNombreArtistico.setText("");
		txtNombreApellido.setText("");
		txtEdad.setText("");
		txtEspacialidadArtistica.setText("");
		txtAniosUruguay.setText("");
		txtPaisOrigen.setText("");
		txtDepartamento.setText("");
		txtLocallidad.setText("");
		txtAniosUruguay.setEnabled(false);
		txtPaisOrigen.setEnabled(false);
	}

	private void DarAltaParticipante() {

		ControllerAltaParticipante con = new ControllerAltaParticipante();
		String nombreArtistico = txtNombreArtistico.getText();
		String nombreApellido = txtNombreApellido.getText();
		int edad = Integer.parseInt(txtEdad.getText());
		String especialidadArtistica = txtEspacialidadArtistica.getText();

		String foto = "foto1";
		int puntajeTodasPerformances = 0;
		boolean esFinalista = false;
		boolean tienePerformancePendiente = false;
		int puntajeTotalPublico = 0;
		boolean esGanador = false;
		TipoParticipante tipoDeParticipante;

		VOParticipanteDetallado vo;
		if (tipo.equals("EXTRANJERO")) {
			tipoDeParticipante = TipoParticipante.EXTRANJERO;
			try {
				String paisOrigen = txtPaisOrigen.getText();
				int cantidadAniosEnUy = Integer.parseInt(txtAniosUruguay.getText());

				vo = new VOExtranjero(nombreArtistico, nombreApellido, foto, edad, especialidadArtistica,
						puntajeTodasPerformances, tienePerformancePendiente, esFinalista, puntajeTotalPublico,
						esGanador, paisOrigen, cantidadAniosEnUy);

				con.R1AltaParticipante(vo);

				JOptionPane.showMessageDialog(null, Mensajes.M_PARTICIPANTES_EXITO, "Alerta",
						JOptionPane.INFORMATION_MESSAGE);
				limpiarPantalla();

			} catch (Exception ex) {
				JOptionPane.showMessageDialog(null, ex.getMessage(), "Alerta", JOptionPane.ERROR_MESSAGE);
			}

		} else {

			tipoDeParticipante = TipoParticipante.NACIONAL;
			String departamento = txtDepartamento.getText();
			String localidad = txtLocallidad.getText();
			try {
				vo = new VONacional(nombreArtistico, nombreApellido, foto, edad, especialidadArtistica, departamento,
						localidad);

				con.R1AltaParticipante(vo);

				JOptionPane.showMessageDialog(null, Mensajes.M_PARTICIPANTES_EXITO, "Alerta",
						JOptionPane.INFORMATION_MESSAGE);
				limpiarPantalla();

			} catch (Exception ex) {
				JOptionPane.showMessageDialog(null, ex.getMessage(), "Alerta", JOptionPane.ERROR_MESSAGE);
			}
		}

		try {
			ListarParticipantes();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e.getMessage(), "Alerta", JOptionPane.ERROR_MESSAGE);

		}

	}

	private void ListarParticipantes() throws RemoteException, ParticipantesExceptions, InterruptedException {

		ControllerInicioProgramaListParticipantes controllerlistar = new ControllerInicioProgramaListParticipantes();
		List<VOParticipante> lista = new ArrayList<VOParticipante>();
		lista = controllerlistar.ListarTodosLosParticipantes();
		String[][] matriz = new String[lista.size()][3];

		for (int i = 0; i < lista.size(); i++) {

			matriz[i][0] = String.valueOf(lista.get(i).getEdad());
			matriz[i][1] = String.valueOf(lista.get(i).getEspecialidadArtistica());
			matriz[i][2] = String.valueOf(lista.get(i).getNombreArtistico());

		}

		table.setModel(new DefaultTableModel(matriz, new String[] { "Edad", "Especialidad", "Nombre Artistico" }));
		table.getColumnModel().getColumn(2).setPreferredWidth(252);

	}

	public JIFrameAltaParticipante() {
		setClosable(true);
		setBounds(100, 100, 699, 467);
		getContentPane().setLayout(null);

		JButton btnAltaParticipante = new JButton("Alta Participante");
		btnAltaParticipante.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				DarAltaParticipante();

			}

			private JFramePrincipal JFramePrincipal() {
				return null;
			}
		});

		btnAltaParticipante.setBounds(28, 288, 148, 30);
		getContentPane().add(btnAltaParticipante);

		JLabel lblNewLabel = new JLabel("Nombre Artistico");
		lblNewLabel.setBounds(100, 17, 130, 14);
		getContentPane().add(lblNewLabel);

		txtNombreArtistico = new JTextField();
		txtNombreArtistico.setBounds(268, 11, 263, 20);
		getContentPane().add(txtNombreArtistico);
		txtNombreArtistico.setColumns(10);

		JLabel lblNombreYApellido = new JLabel("Nombre y Apellido");
		lblNombreYApellido.setBounds(100, 48, 130, 14);
		getContentPane().add(lblNombreYApellido);

		txtNombreApellido = new JTextField();
		txtNombreApellido.setColumns(10);
		txtNombreApellido.setBounds(268, 42, 263, 20);
		getContentPane().add(txtNombreApellido);

		JLabel lblEdad = new JLabel("Edad");
		lblEdad.setBounds(100, 79, 46, 14);
		getContentPane().add(lblEdad);

		txtEdad = new JTextField();
		txtEdad.setColumns(10);
		txtEdad.setBounds(268, 73, 263, 20);
		getContentPane().add(txtEdad);

		JLabel lblEspecialidadArtistica = new JLabel("Especialidad Artistica");
		lblEspecialidadArtistica.setBounds(97, 107, 151, 14);
		getContentPane().add(lblEspecialidadArtistica);

		txtEspacialidadArtistica = new JTextField();
		txtEspacialidadArtistica.setColumns(10);
		txtEspacialidadArtistica.setBounds(268, 104, 263, 20);
		getContentPane().add(txtEspacialidadArtistica);

		JLabel label = new JLabel("Tipo Participane");
		label.setBounds(100, 135, 151, 14);
		getContentPane().add(label);

		JPanel panelNacioanal = new JPanel();
		panelNacioanal.setBounds(28, 228, 613, 63);
		// panelNacioanal.setVisible(true);
		panelNacioanal.setLayout(null);
		getContentPane().add(panelNacioanal);

		JLabel lblTipoParticipane = new JLabel("Departamento");
		lblTipoParticipane.setBounds(66, 11, 134, 14);
		panelNacioanal.add(lblTipoParticipane);

		txtDepartamento = new JTextField();
		txtDepartamento.setColumns(10);
		txtDepartamento.setBounds(247, 8, 263, 20);
		panelNacioanal.add(txtDepartamento);

		JLabel lblLocalidad = new JLabel("Localidad");
		lblLocalidad.setBounds(66, 39, 134, 14);
		panelNacioanal.add(lblLocalidad);

		txtLocallidad = new JTextField();
		txtLocallidad.setColumns(10);
		txtLocallidad.setBounds(247, 36, 263, 20);
		panelNacioanal.add(txtLocallidad);

		JPanel panelExtranjero = new JPanel();
		panelExtranjero.setLayout(null);
		panelExtranjero.setBounds(28, 163, 613, 63);
		// panelExtranjero.setVisible(false);
		getContentPane().add(panelExtranjero);

		JLabel lblPaisDeOrigen = new JLabel("Pais de Origen");
		lblPaisDeOrigen.setBounds(66, 11, 134, 14);
		panelExtranjero.add(lblPaisDeOrigen);

		txtPaisOrigen = new JTextField();
		txtPaisOrigen.setColumns(10);
		txtPaisOrigen.setBounds(247, 8, 263, 20);
		txtPaisOrigen.setEnabled(false);
		panelExtranjero.add(txtPaisOrigen);

		JLabel lblAosQueViven = new JLabel("A\u00F1os que Viven en Uruguay");
		lblAosQueViven.setBounds(66, 39, 160, 14);
		panelExtranjero.add(lblAosQueViven);

		txtAniosUruguay = new JTextField();
		txtAniosUruguay.setColumns(10);
		txtAniosUruguay.setBounds(247, 36, 263, 20);
		txtAniosUruguay.setEnabled(false);
		panelExtranjero.add(txtAniosUruguay);

		tipo = "NACIONAL";

		String[] tipos = { "NACIONAL", "EXTRANJERO" };

		JComboBox<Object> cboTipoParticipante = new JComboBox<Object>(tipos);
		cboTipoParticipante.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {

				tipo = cboTipoParticipante.getSelectedItem().toString();

				if (e.getSource() == cboTipoParticipante) {

					try {
						if (tipo.equals("EXTRANJERO")) {

							txtLocallidad.setEnabled(false);
							txtDepartamento.setEnabled(false);
							txtPaisOrigen.setEnabled(true);
							txtAniosUruguay.setEnabled(true);

						} else {
							txtLocallidad.setEnabled(true);
							txtDepartamento.setEnabled(true);
							txtPaisOrigen.setEnabled(false);
							txtAniosUruguay.setEnabled(false);
						}
					} catch (Exception ex) {
						JOptionPane.showMessageDialog(null, ex.getMessage(), "Alerta", JOptionPane.ERROR_MESSAGE);
					}

				}

			}
		});

		cboTipoParticipante.setBounds(271, 132, 263, 20);
		cboTipoParticipante.addActionListener(this);
		getContentPane().add(cboTipoParticipante);

		table = new JTable();
		table.setBorder(new CompoundBorder());
		table.setBounds(28, 321, 631, 105);
		getContentPane().add(table);

		try {
			ListarParticipantes();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e.getMessage(), "Alerta", JOptionPane.ERROR_MESSAGE);

		}

	}

	@Override
	public void actionPerformed(ActionEvent e) {

	}
}
