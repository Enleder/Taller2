package View;

import javax.swing.JInternalFrame;
import javax.swing.JPanel;
import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.Component;
import javax.swing.UIManager;
import javax.swing.border.TitledBorder;
import Controller.ControllerParticipanteListPerformances;
import Logic.*;
import ValueObjects.VOExtranjero;
import ValueObjects.VONacional;
import ValueObjects.VOParticipanteDetallado;
import ValueObjects.VOPerformance;
import View.ComponentesPanel.JPanelGrillaPerformancesNoHayNinguna;
import View.ComponentesPanel.JPanelGrillaPerformancesR4;
import View.FotografiaTools.MiCanvas;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import java.awt.GridLayout;
import java.awt.Font;
import javax.swing.JTextField;

import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

public class JIFrame_c_ViewParticipanteListPerformances extends JInternalFrame {

	private static final long serialVersionUID = 1L;
	protected static final Component JIFrame_c_ViewParticipanteListPerformances = null;

	// private JFrame frmVentanaFoto;

	private JTextField textField_NombreArtistico;
	private JTextField textField_NombreYApellido;
	private JTextField textField_Edad;
	private JTextField textField_EspecialidadArtistica;
	private JTextField textField_DatoNacionalidad_01;
	private JTextField textField_DatoNacionalidad_02;

	private MiCanvas canvasForProfilePhoto;
	private JTextField textField_PuntajeTotalDePerformances;
	private JTextField textField_TienePerformancesPendientes;
	private JTextField textField_EsFinalista;
	private JTextField textField_PuntajeTotalDelPublico;
	private JTextField textField_EsGanador;
	private JButton btnAgregarPerformance;

	private JPanelGrillaPerformancesR4 panel_JPanelGrillaPerformancesR4;
	private JPanel PanelListaDePerformances;
	private String idParticipante;
	private static String idParticipanteDetalle;
	private VOParticipanteDetallado VOParticipanteDetallado;

	private JLabel label_DatoNacionalidad_01;
	private JLabel label_DatoNacionalidad_02;
	private byte[] imagen;

	private List<VOPerformance> lista;
	
	public static void main(String[] args) {

		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					JIFrame_c_ViewParticipanteListPerformances frame = new JIFrame_c_ViewParticipanteListPerformances(
							idParticipanteDetalle);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the panel.
	 * 
	 * @throws Exception
	 */
	public JIFrame_c_ViewParticipanteListPerformances(String idParticipanteDetalle) throws Exception {
		idParticipante = idParticipanteDetalle;
		// System.out.println("ADNETRO DE C: "+idParticipante);
		getContentPane().setEnabled(false);
		setFrameIcon(new ImageIcon(
				JIFrame_c_ViewParticipanteListPerformances.class.getResource("/imagenes/Diapositiva1.PNG")));

		// setFrameIcon(new
		// ImageIcon(JIFrame_b_AltaParticipante.class.getResource("D:\\Google
		// Drive\\UDE\\13-Taller2\\Proyecto\\Taller2\\Documentacion\\Defensa\\Recursos
		// Imagenes\\Logo\\star.PNG")));

		setResizable(true);
		setMaximizable(true);
		setTitle("Datos detallados de Participante");
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setBounds(100, 100, 780, 535);
		getContentPane().setLayout(null);

		JPanel PanelAllData_FreeStyle = new JPanel();
		PanelAllData_FreeStyle.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"),
				"Datos del Participante", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		PanelAllData_FreeStyle.setBounds(20, 15, 722, 424);
		getContentPane().add(PanelAllData_FreeStyle);
		PanelAllData_FreeStyle.setLayout(null);

		JPanel PanelMainData_GridStyle = new JPanel();
		PanelMainData_GridStyle.setBorder(null);
		PanelMainData_GridStyle.setBounds(184, 20, 528, 263);
		// panel.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"),
		// "Ingresa los datos del nuevo participante", TitledBorder.LEADING,
		// TitledBorder.TOP, null, new Color(0, 0, 0)));
		PanelAllData_FreeStyle.add(PanelMainData_GridStyle);
		PanelMainData_GridStyle.setLayout(new GridLayout(11, 2, 5, 5));

		JLabel label_NombreArtistico = new JLabel("Nombre Artistico");
		label_NombreArtistico.setFont(new Font("Tahoma", Font.BOLD, 11));
		PanelMainData_GridStyle.add(label_NombreArtistico);

		textField_NombreArtistico = new JTextField();
		textField_NombreArtistico.setEditable(false);
		textField_NombreArtistico.setToolTipText("");
		textField_NombreArtistico.setColumns(10);
		PanelMainData_GridStyle.add(textField_NombreArtistico);

		JLabel label_NombreYApellido = new JLabel("Nombre y Apellido");
		label_NombreYApellido.setFont(new Font("Tahoma", Font.BOLD, 11));
		PanelMainData_GridStyle.add(label_NombreYApellido);

		textField_NombreYApellido = new JTextField();
		textField_NombreYApellido.setEditable(false);
		textField_NombreYApellido.setToolTipText("");
		textField_NombreYApellido.setColumns(10);
		PanelMainData_GridStyle.add(textField_NombreYApellido);

		JLabel label_Edad = new JLabel("Edad");
		label_Edad.setFont(new Font("Tahoma", Font.BOLD, 11));
		PanelMainData_GridStyle.add(label_Edad);

		textField_Edad = new JTextField();
		textField_Edad.setEditable(false);
		textField_Edad.setToolTipText("");
		textField_Edad.setColumns(10);
		PanelMainData_GridStyle.add(textField_Edad);

		JLabel label_EspecialidadArtistica = new JLabel("EspecialidadArtistica");
		label_EspecialidadArtistica.setFont(new Font("Tahoma", Font.BOLD, 11));
		PanelMainData_GridStyle.add(label_EspecialidadArtistica);

		textField_EspecialidadArtistica = new JTextField();
		textField_EspecialidadArtistica.setEditable(false);
		textField_EspecialidadArtistica.setToolTipText("");
		textField_EspecialidadArtistica.setColumns(10);
		PanelMainData_GridStyle.add(textField_EspecialidadArtistica);

		label_DatoNacionalidad_01 = new JLabel("Departamento");
		PanelMainData_GridStyle.add(label_DatoNacionalidad_01);
		label_DatoNacionalidad_01.setFont(new Font("Tahoma", Font.BOLD, 11));

		textField_DatoNacionalidad_01 = new JTextField();
		textField_DatoNacionalidad_01.setEditable(false);
		PanelMainData_GridStyle.add(textField_DatoNacionalidad_01);
		textField_DatoNacionalidad_01.setToolTipText("");
		textField_DatoNacionalidad_01.setColumns(10);

		label_DatoNacionalidad_02 = new JLabel("Localidad");
		PanelMainData_GridStyle.add(label_DatoNacionalidad_02);
		label_DatoNacionalidad_02.setFont(new Font("Tahoma", Font.BOLD, 11));

		textField_DatoNacionalidad_02 = new JTextField();
		textField_DatoNacionalidad_02.setEditable(false);
		PanelMainData_GridStyle.add(textField_DatoNacionalidad_02);
		textField_DatoNacionalidad_02.setToolTipText("");
		textField_DatoNacionalidad_02.setColumns(10);

		JLabel label_PuntajeTotalDePerformances = new JLabel("Puntaje total de Performances realizadas");
		label_PuntajeTotalDePerformances.setFont(new Font("Tahoma", Font.BOLD, 11));
		PanelMainData_GridStyle.add(label_PuntajeTotalDePerformances);

		textField_PuntajeTotalDePerformances = new JTextField();
		textField_PuntajeTotalDePerformances.setToolTipText("");
		textField_PuntajeTotalDePerformances.setEditable(false);
		textField_PuntajeTotalDePerformances.setColumns(10);
		PanelMainData_GridStyle.add(textField_PuntajeTotalDePerformances);

		JLabel label_TienePerformancesPendientes = new JLabel("Tiene Performances pendientes?");
		label_TienePerformancesPendientes.setFont(new Font("Tahoma", Font.BOLD, 11));
		PanelMainData_GridStyle.add(label_TienePerformancesPendientes);

		textField_TienePerformancesPendientes = new JTextField();
		textField_TienePerformancesPendientes.setToolTipText("");
		textField_TienePerformancesPendientes.setEditable(false);
		textField_TienePerformancesPendientes.setColumns(10);
		PanelMainData_GridStyle.add(textField_TienePerformancesPendientes);

		JLabel label_EsFinalista = new JLabel("Es Finalista?");
		label_EsFinalista.setFont(new Font("Tahoma", Font.BOLD, 11));
		PanelMainData_GridStyle.add(label_EsFinalista);

		textField_EsFinalista = new JTextField();
		textField_EsFinalista.setToolTipText("");
		textField_EsFinalista.setEditable(false);
		textField_EsFinalista.setColumns(10);
		PanelMainData_GridStyle.add(textField_EsFinalista);

		JLabel label_PuntajeTotalDelPublico = new JLabel("Puntaje total de Votacion del Publico");
		label_PuntajeTotalDelPublico.setFont(new Font("Tahoma", Font.BOLD, 11));
		PanelMainData_GridStyle.add(label_PuntajeTotalDelPublico);

		textField_PuntajeTotalDelPublico = new JTextField();
		textField_PuntajeTotalDelPublico.setToolTipText("");
		textField_PuntajeTotalDelPublico.setEditable(false);
		textField_PuntajeTotalDelPublico.setColumns(10);
		PanelMainData_GridStyle.add(textField_PuntajeTotalDelPublico);

		JLabel label_EsGanador = new JLabel("Es Ganador?");
		label_EsGanador.setFont(new Font("Tahoma", Font.BOLD, 11));
		PanelMainData_GridStyle.add(label_EsGanador);

		textField_EsGanador = new JTextField();
		textField_EsGanador.setToolTipText("");
		textField_EsGanador.setEditable(false);
		textField_EsGanador.setColumns(10);
		PanelMainData_GridStyle.add(textField_EsGanador);

		// String NombreArtisticoPasar = textField_NombreArtistico.getText(); //

		PanelListaDePerformances = new JPanel();
		PanelListaDePerformances.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"),
				"Listado de sus Performances", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		PanelListaDePerformances.setBounds(20, 289, 692, 124);
		PanelAllData_FreeStyle.add(PanelListaDePerformances);
		PanelListaDePerformances.setLayout(new GridLayout(0, 1, 0, 0));
		panel_JPanelGrillaPerformancesR4 = new JPanelGrillaPerformancesR4(idParticipante);
		PanelListaDePerformances.add(panel_JPanelGrillaPerformancesR4);

		// JPanelGrillaParticipantesR2 PanelListaDePerformances = new
		// JPanelGrillaPerformancesR4();
		// PanelListaDePerformances.setBorder(new
		// TitledBorder(UIManager.getBorder("TitledBorder.border"), "Listado de sus
		// Performances", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0,
		// 0)));
		// PanelListaDePerformances.setBounds(10, 214, 655, 135);
		// PanelAllData_FreeStyle.add(PanelListaDePerformances);
		// PanelListaDePerformances.setLayout(new GridLayout(1, 0, 0, 0));
		////
		//
		//
		// panelGrillaParticipantesSimpleR2.setBounds(10, 60, 664, 168);
		// getContentPane().add(panelGrillaParticipantesSimpleR2);
		//

		// JLabel lblSubirFoto = new JLabel("");
		// lblSubirFoto.setHorizontalAlignment(SwingConstants.CENTER);
		// lblSubirFoto.setIcon(new
		// ImageIcon(JIFrame_b_AltaParticipante.class.getResource("/imagenes/uploadPhoto.png")));
		// lblSubirFoto.setBounds(505, 21, 146, 190);
		// PanelAllData_FreeStyle.add(lblSubirFoto);

		canvasForProfilePhoto = new MiCanvas();
		canvasForProfilePhoto.setBackground(Color.LIGHT_GRAY);
		canvasForProfilePhoto.setBounds(20, 20, 146, 190);
		PanelAllData_FreeStyle.add(canvasForProfilePhoto);

		// byte[] imagen =
		// ImagenConvertidor.convertirImagen("src\\imagenes\\imgFotoSize.jpg");//
		// C:\\Users\\joase\\Desktop\\star\\bitmap23.jpg");
		// canvasForProfilePhoto.setImagen(imagen);
		// canvasForProfilePhoto.repaint();

		JButton btnVolver = new JButton("<-- Volver");
		btnVolver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					JIFrame_a_InicioProgramaListParticipantes ventana_a = new JIFrame_a_InicioProgramaListParticipantes();
					ventana_a.setLocation(1, 1);
					SPT_TalentoUruguayo.desktopPane.removeAll();
					SPT_TalentoUruguayo.desktopPane.add(ventana_a);
					// contentPane.add(ventana_a);
					ventana_a.show();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		btnVolver.setForeground(Color.GRAY);
		btnVolver.setBackground(Color.LIGHT_GRAY);
		btnVolver.setBounds(335, 450, 188, 33);
		getContentPane().add(btnVolver);
		// canvas.setVisible(false);

		btnAgregarPerformance = new JButton("Agregar nuava Performance");
		btnAgregarPerformance.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					if (VOParticipanteDetallado.isTienePerformancePendiente()) {
						// tienePerformancesPendiente = "SI";
						btnAgregarPerformance.setEnabled(true);
						btnAgregarPerformance.setForeground(Color.BLUE);
						btnAgregarPerformance.setBackground(Color.BLUE);

						JIFrame_d_AltaPerformance ventana_d = new JIFrame_d_AltaPerformance(
								VOParticipanteDetallado.getNombreArtistico());
						ventana_d.setLocation(1, 1);
						SPT_TalentoUruguayo.desktopPane.removeAll();
						SPT_TalentoUruguayo.desktopPane.add(ventana_d);
						ventana_d.show();
					} else {
						// tienePerformancesPendiente = "NO";
						btnAgregarPerformance.setEnabled(true);
						btnAgregarPerformance.setForeground(Color.GRAY);
						btnAgregarPerformance.setBackground(Color.GRAY);
						JOptionPane.showMessageDialog(null, Mensajes.M_ErrorNoSeLePuedeAgregarPerformance, "Error",
								JOptionPane.ERROR_MESSAGE);
					}

				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});

		btnAgregarPerformance.setBounds(554, 450, 188, 33);
		getContentPane().add(btnAgregarPerformance);
		btnAgregarPerformance.setForeground(Color.BLUE);
		btnAgregarPerformance.setBackground(Color.BLUE);

		cargarParticipante();
	}

	public boolean isClosed() {
		return false;
	}

	public void cargarParticipante() throws Exception {

		//CONTROLLER PARTICIPANTE DETALLADO
		ControllerParticipanteListPerformances controllerParticipanteDetallado = new ControllerParticipanteListPerformances();
		VOParticipanteDetallado = controllerParticipanteDetallado.ListarParticipanteDetallado(idParticipante);

		//CONTROLLER LISTA DE PERFORMANCES DE UN PARTICIPANTE POR ID
		ControllerParticipanteListPerformances controllerlistarPerformances = new ControllerParticipanteListPerformances();
		lista = new ArrayList<VOPerformance>();
		lista = controllerlistarPerformances.ListarTodosLasPerformances(idParticipante);
		
		textField_NombreArtistico.setText(VOParticipanteDetallado.getNombreArtistico());
		textField_NombreYApellido.setText(VOParticipanteDetallado.getNombreYApellido());
		textField_Edad.setText(String.valueOf(VOParticipanteDetallado.getEdad()));
		textField_EspecialidadArtistica.setText(VOParticipanteDetallado.getEspecialidadArtistica());

		imagen = Base64.getDecoder().decode(VOParticipanteDetallado.getFoto());
		canvasForProfilePhoto.setImagen(imagen);
		canvasForProfilePhoto.repaint();

		if (VOParticipanteDetallado.getTipoDeParticipante() == TipoParticipante.NACIONAL) {

			String departamento = ((VONacional) VOParticipanteDetallado).getDepartamento();
			String localidad = ((VONacional) VOParticipanteDetallado).getLocalidad();

			textField_DatoNacionalidad_01.setText(departamento);
			textField_DatoNacionalidad_02.setText(localidad);

		} else {

			String PaisOrigen = ((VOExtranjero) VOParticipanteDetallado).getPaisOrigen();
			int CantAniosUY = ((VOExtranjero) VOParticipanteDetallado).getCantidadAniosEnUy();

			label_DatoNacionalidad_01.setText("Pais de Origen");
			label_DatoNacionalidad_02.setText("Años en el País");

			textField_DatoNacionalidad_01.setText(PaisOrigen);
			textField_DatoNacionalidad_02.setText(String.valueOf(CantAniosUY));
		}

		textField_PuntajeTotalDePerformances
				.setText(String.valueOf(VOParticipanteDetallado.getPuntajeTodasLasPerformances()));

		String tienePerformancesPendiente;
		if (VOParticipanteDetallado.isTienePerformancePendiente() == true) {
			tienePerformancesPendiente = "SI";
			btnAgregarPerformance.setEnabled(true);
			btnAgregarPerformance.setForeground(Color.BLUE);
			btnAgregarPerformance.setBackground(Color.BLUE);
		} else {
			tienePerformancesPendiente = "NO";
			btnAgregarPerformance.setEnabled(true);
			btnAgregarPerformance.setForeground(Color.GRAY);
			btnAgregarPerformance.setBackground(Color.GRAY);
		}
		textField_TienePerformancesPendientes.setText(tienePerformancesPendiente);
		String esFinalista;
		if (VOParticipanteDetallado.isEsFinalista() == true) {
			esFinalista = "SI";
		} else {
			esFinalista = "NO";
		}

		textField_EsFinalista.setText(esFinalista);
		textField_PuntajeTotalDelPublico.setText(String.valueOf(VOParticipanteDetallado.getPuntajeTotalPublico()));
		String esGanador;
		if (VOParticipanteDetallado.isEsGanador() == true) {
			esGanador = "SI";
		} else {
			esGanador = "NO";
		}

		textField_EsGanador.setText(esGanador);

		JPanelGrillaPerformancesNoHayNinguna Panel_JPanelNoHayNinguno = new JPanelGrillaPerformancesNoHayNinguna();
		
		if (lista.size() > 0) {
			panel_JPanelGrillaPerformancesR4.setVisible(true);
			Panel_JPanelNoHayNinguno.setVisible(false);
			PanelListaDePerformances.remove(Panel_JPanelNoHayNinguno);
			PanelListaDePerformances.add(panel_JPanelGrillaPerformancesR4);

		} else {
			panel_JPanelGrillaPerformancesR4.setVisible(false);
			Panel_JPanelNoHayNinguno.setVisible(true);
			PanelListaDePerformances.remove(panel_JPanelGrillaPerformancesR4);
			PanelListaDePerformances.add(Panel_JPanelNoHayNinguno);
		}

	}

}
