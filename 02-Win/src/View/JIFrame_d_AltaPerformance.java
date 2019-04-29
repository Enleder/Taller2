package View;

import javax.swing.*;
import javax.swing.border.*;
import javax.swing.text.*;
import java.awt.*;
import java.awt.event.*;
import java.beans.*;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.NumberFormat;
import java.util.Base64;
import java.util.List;
import Controller.*;
import ValueObjects.*;
import View.FotografiaTools.*;
import Exceptions.*;
import Logic.Mensajes;
import Logic.SystemProperties;

public class JIFrame_d_AltaPerformance extends JInternalFrame {

	private static final long serialVersionUID = 1L;
	protected static final Component JIFrame_b_AltaParticipante = null;
	private ControllerAltaPerformance miControler = null;
	private JTextField textField_NumeroPerformance;
	private JTextField textField_NombreArtistico;
	private JTextField textField_NombreYApellido;
	private JTextField textField_Edad;
	private JTextField textField_EspecialidadArtistica;
	private JTextField textField_PuntajePerformances;
	private JTextArea textArea_Descripcion;
	private JButton btnConfirmarRegistro;
	private JButton btnCacnelarYVolver;
	private JPanel panel_DatosBasicosParticipante;
	private JPanel panel_DatosBasicosPartCampos;
	private JLabel label_NombreArtistico;
	private JLabel label_NombreYApellido;
	private JLabel label_Edad;
	private JLabel label_EspecialidadArtistica;
	private JLabel label_PuntajePerformances;
	private MiCanvasSmallPhoto miCanvasSmall;
	private JPanel PanelNewMainData_GridStyle;
	private JLabel lblNumeroDePerformance;
	private JLabel lblDescripcion;
	private JLabel lblPuntajeDeJurado_1;
	private JLabel lblPuntajeDeJurado_2;
	private JLabel lblPuntajeDeJurado_3;
	private JScrollPane scrollPaneDescription;
	private JFormattedTextField formattedTextField_PuntajeDeJurado_1;
	private JFormattedTextField formattedTextField_PuntajeDeJurado_2;
	private JFormattedTextField formattedTextField_PuntajeDeJurado_3;

	private static SystemProperties properties;
	private static String idParticipante;
	private byte[] imagen;
	private int numero;
	private String descripcion;
	private int puntajeJuez1 = 0;
	private int puntajeJuez2 = 0;
	private int puntajeJuez3 = 0;
	private String idParaAgregarlePerf;
	VOPerformance voNuevaPer = null;
	private NumberFormat formato;
	private NumberFormatter formatear;
	private List<VOPerformance> lista;
	int cantidadPerformances;
	VOParticipanteDetallado VOParticipanteDetallado = null;
	
	private int cantidadDePerformancesMaximas;
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					JIFrame_d_AltaPerformance frame = new JIFrame_d_AltaPerformance(idParticipante);

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
	 * @throws PropertyVetoException
	 */
	public JIFrame_d_AltaPerformance(String idParticipante) throws PropertyVetoException {

		idParaAgregarlePerf = idParticipante;
		try {
			properties = new SystemProperties();
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
				
		//// FORMATO PARA LOS NUMERICOS
		formato = NumberFormat.getInstance();
		formatear = new NumberFormatter(formato);
		formatear.setValueClass(Integer.class);
		formatear.setMinimum(1); // valor mínimo
		formatear.setMaximum(100); // valor máximo
		formatear.setAllowsInvalid(false);
		// Si quieres comprobar que sea válido, cada vez que se pulse una tecla
		formatear.setCommitsOnValidEdit(true);
		//// FIN FORMATO PARA LOS NUMERICOS

		getContentPane().setEnabled(false);
		setFrameIcon(new ImageIcon(JIFrame_d_AltaPerformance.class.getResource("/imagenes/Diapositiva1.PNG")));

		setResizable(true);
		setMaximizable(true);
		setTitle("Registrar una nueva Performance");
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setBounds(100, 100, 780, 535);
		getContentPane().setLayout(null);

		btnConfirmarRegistro = new JButton("Confirmar registro");
		btnConfirmarRegistro.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				try {
					cargarParticipante();
					if (VOParticipanteDetallado.isTienePerformancePendiente()) {
						// isTienePerformancePendiente() == true;
						btnConfirmarRegistro.setEnabled(true);
						btnConfirmarRegistro.setForeground(Color.BLUE);
						btnConfirmarRegistro.setBackground(Color.BLUE);

						CargarPerformance();

					} else {
						// isTienePerformancePendiente() == false;
						btnConfirmarRegistro.setEnabled(true);
						btnConfirmarRegistro.setForeground(Color.GRAY);
						btnConfirmarRegistro.setBackground(Color.GRAY);
						JOptionPane.showMessageDialog(null, Mensajes.M_ErrorNoSeLePuedeAgregarPerformance, "Error",
								JOptionPane.ERROR_MESSAGE);
					}
					limpiarPantalla();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		btnConfirmarRegistro.setBounds(552, 450, 188, 33);
		btnConfirmarRegistro.setForeground(Color.BLUE);
		btnConfirmarRegistro.setBackground(Color.BLUE);
		getContentPane().add(btnConfirmarRegistro);

		btnCacnelarYVolver = new JButton("<-- Cancelar y volver");
		btnCacnelarYVolver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				// CERAR ESTE PANEL Y VOLVER AL MISMO C

				try {
					JIFrame_c_ViewParticipanteListPerformances ventana_c;
					ventana_c = new JIFrame_c_ViewParticipanteListPerformances(idParaAgregarlePerf);
					ventana_c.setLocation(0, 0);
					// frmContenedorPrincipal.escritorio.add(v2);
					SPT_TalentoUruguayo.desktopPane.removeAll();
					SPT_TalentoUruguayo.desktopPane.add(ventana_c);
					ventana_c.show();
					setVisible(false);
				} catch (Exception e) {
					e.printStackTrace();
				}

			}
		});

		btnCacnelarYVolver.setForeground(Color.GRAY);
		btnCacnelarYVolver.setBackground(Color.LIGHT_GRAY);
		btnCacnelarYVolver.setBounds(337, 450, 188, 33);
		getContentPane().add(btnCacnelarYVolver);

		panel_DatosBasicosParticipante = new JPanel();
		panel_DatosBasicosParticipante.setLayout(null);
		panel_DatosBasicosParticipante.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"),
				"Datos basicos del Participante al cual le agregara Performance", TitledBorder.LEFT, TitledBorder.TOP,
				null, new Color(0, 0, 0)));
		panel_DatosBasicosParticipante.setBounds(20, 20, 722, 171);
		getContentPane().add(panel_DatosBasicosParticipante);

		panel_DatosBasicosPartCampos = new JPanel();
		panel_DatosBasicosPartCampos.setBorder(null);
		panel_DatosBasicosPartCampos.setBounds(147, 30, 553, 131);
		panel_DatosBasicosParticipante.add(panel_DatosBasicosPartCampos);
		panel_DatosBasicosPartCampos.setLayout(new GridLayout(5, 2, 5, 5));

		label_NombreArtistico = new JLabel("Nombre Artistico");
		label_NombreArtistico.setFont(new Font("Tahoma", Font.BOLD, 11));
		panel_DatosBasicosPartCampos.add(label_NombreArtistico);

		textField_NombreArtistico = new JTextField();
		textField_NombreArtistico.setToolTipText("");
		textField_NombreArtistico.setEditable(false);
		textField_NombreArtistico.setColumns(10);
		panel_DatosBasicosPartCampos.add(textField_NombreArtistico);

		label_NombreYApellido = new JLabel("Nombre y Apellido");
		label_NombreYApellido.setFont(new Font("Tahoma", Font.BOLD, 11));
		panel_DatosBasicosPartCampos.add(label_NombreYApellido);

		textField_NombreYApellido = new JTextField();
		textField_NombreYApellido.setToolTipText("");
		textField_NombreYApellido.setEditable(false);
		textField_NombreYApellido.setColumns(10);
		panel_DatosBasicosPartCampos.add(textField_NombreYApellido);

		label_Edad = new JLabel("Edad");
		label_Edad.setFont(new Font("Tahoma", Font.BOLD, 11));
		panel_DatosBasicosPartCampos.add(label_Edad);

		textField_Edad = new JTextField();
		textField_Edad.setToolTipText("");
		textField_Edad.setEditable(false);
		textField_Edad.setColumns(10);
		panel_DatosBasicosPartCampos.add(textField_Edad);

		label_EspecialidadArtistica = new JLabel("EspecialidadArtistica");
		label_EspecialidadArtistica.setFont(new Font("Tahoma", Font.BOLD, 11));
		panel_DatosBasicosPartCampos.add(label_EspecialidadArtistica);

		textField_EspecialidadArtistica = new JTextField();
		textField_EspecialidadArtistica.setToolTipText("");
		textField_EspecialidadArtistica.setEditable(false);
		textField_EspecialidadArtistica.setColumns(10);
		panel_DatosBasicosPartCampos.add(textField_EspecialidadArtistica);

		label_PuntajePerformances = new JLabel("Puntaje total de Performances realizadas");
		label_PuntajePerformances.setFont(new Font("Tahoma", Font.BOLD, 11));
		panel_DatosBasicosPartCampos.add(label_PuntajePerformances);

		textField_PuntajePerformances = new JTextField();
		textField_PuntajePerformances.setToolTipText("");
		textField_PuntajePerformances.setEditable(false);
		textField_PuntajePerformances.setColumns(10);
		panel_DatosBasicosPartCampos.add(textField_PuntajePerformances);

		miCanvasSmall = new MiCanvasSmallPhoto();
		miCanvasSmall.setBackground(Color.LIGHT_GRAY);
		miCanvasSmall.setBounds(32, 30, 101, 131);

		imagen = ImagenConvertidor.convertirImagen("src\\imagenes\\imgFotoSize.jpg");// C:\\Users\\joase\\Desktop\\star\\bitmap23.jpg");
		miCanvasSmall.setImagen(imagen);
		miCanvasSmall.repaint();

		panel_DatosBasicosParticipante.add(miCanvasSmall);

		try {
			PanelNewMainData_GridStyle = new JPanel();
			PanelNewMainData_GridStyle.setBounds(20, 198, 720, 241);
			getContentPane().add(PanelNewMainData_GridStyle);
			PanelNewMainData_GridStyle.setBorder(new TitledBorder(null, "Ingrese los datos de la nueva Performance",
					TitledBorder.LEADING, TitledBorder.TOP, null, null));
			PanelNewMainData_GridStyle.setLayout(null);

			lblNumeroDePerformance = new JLabel("Numero de Performance");
			lblNumeroDePerformance.setBounds(32, 19, 200, 26);
			lblNumeroDePerformance.setFont(new Font("Tahoma", Font.BOLD, 11));
			PanelNewMainData_GridStyle.add(lblNumeroDePerformance);

			textField_NumeroPerformance = new JTextField("");
			textField_NumeroPerformance.setBounds(242, 19, 330, 26);
			textField_NumeroPerformance.setEditable(false);
			textField_NumeroPerformance.setFont(new Font("Tahoma", Font.PLAIN, 18));

			textField_NumeroPerformance.setToolTipText("Ingrese Nombre Artistico");
			textField_NumeroPerformance.setColumns(10);
			PanelNewMainData_GridStyle.add(textField_NumeroPerformance);

			lblDescripcion = new JLabel("Descripcion");
			lblDescripcion.setBounds(32, 56, 200, 26);
			lblDescripcion.setFont(new Font("Tahoma", Font.BOLD, 11));
			PanelNewMainData_GridStyle.add(lblDescripcion);

			lblPuntajeDeJurado_1 = new JLabel("Puntaje de Jurado 1");
			lblPuntajeDeJurado_1.setBounds(32, 138, 200, 26);
			lblPuntajeDeJurado_1.setFont(new Font("Tahoma", Font.BOLD, 11));
			PanelNewMainData_GridStyle.add(lblPuntajeDeJurado_1);

			lblPuntajeDeJurado_2 = new JLabel("Puntaje de Jurado 2");
			lblPuntajeDeJurado_2.setBounds(32, 172, 200, 26);
			lblPuntajeDeJurado_2.setFont(new Font("Tahoma", Font.BOLD, 11));
			PanelNewMainData_GridStyle.add(lblPuntajeDeJurado_2);

			lblPuntajeDeJurado_3 = new JLabel("Puntaje de Jurado 3");
			lblPuntajeDeJurado_3.setBounds(32, 205, 200, 26);
			lblPuntajeDeJurado_3.setFont(new Font("Tahoma", Font.BOLD, 11));
			PanelNewMainData_GridStyle.add(lblPuntajeDeJurado_3);
			// textArea.setBounds(232, 55, 330, 71);
			// PanelMainData_GridStyle.add(textArea);

			textArea_Descripcion = new JTextArea();
			textArea_Descripcion.setFont(new Font("Tahoma", Font.PLAIN, 13));
			// textArea.setBounds(232, 55, 330, 71);
			// PanelMainData_GridStyle.add(textArea);
			textArea_Descripcion.setLineWrap(true);

			scrollPaneDescription = new JScrollPane(textArea_Descripcion);
			scrollPaneDescription.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
			scrollPaneDescription.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
			scrollPaneDescription.setBounds(242, 56, 330, 71);
			PanelNewMainData_GridStyle.add(scrollPaneDescription);

			formattedTextField_PuntajeDeJurado_1 = new JFormattedTextField(formatear);
			formattedTextField_PuntajeDeJurado_1.setBounds(242, 138, 166, 26);
			PanelNewMainData_GridStyle.add(formattedTextField_PuntajeDeJurado_1);

			formattedTextField_PuntajeDeJurado_2 = new JFormattedTextField(formatear);
			formattedTextField_PuntajeDeJurado_2.setBounds(242, 172, 166, 26);
			PanelNewMainData_GridStyle.add(formattedTextField_PuntajeDeJurado_2);

			formattedTextField_PuntajeDeJurado_3 = new JFormattedTextField(formatear);
			formattedTextField_PuntajeDeJurado_3.setBounds(242, 205, 166, 26);
			PanelNewMainData_GridStyle.add(formattedTextField_PuntajeDeJurado_3);

			cargarParticipante();

		} catch (Exception e) {
			// ...
		}

	}

	public boolean isClosed() {
		return false;
	}

	public void CargarPerformance() {

		try {
			numero = Performances().size();
		} catch (Exception e) {
			e.printStackTrace();
		}
		numero++;
		idParaAgregarlePerf = textField_NombreArtistico.getText();
		descripcion = textArea_Descripcion.getText();
		puntajeJuez1 = Integer.parseInt(formattedTextField_PuntajeDeJurado_1.getText());
		puntajeJuez2 = Integer.parseInt(formattedTextField_PuntajeDeJurado_2.getText());
		puntajeJuez3 = Integer.parseInt(formattedTextField_PuntajeDeJurado_3.getText());

		voNuevaPer = new VOPerformance(numero, descripcion, puntajeJuez1, puntajeJuez2, puntajeJuez3);
		miControler = new ControllerAltaPerformance();
		try {
			miControler.AltaPerformance(idParaAgregarlePerf, voNuevaPer);
			JOptionPane.showMessageDialog(null, Mensajes.M_ProcedimientoCorrectoR6, "Procedimiento Correcto",
					JOptionPane.INFORMATION_MESSAGE);
		} catch (Exception e) {
			// : handle exception
		}
	}

	public void cargarParticipante() throws ParticipantesExceptions {

		ControllerAltaPerformance controllerParticipanteDetallado = new ControllerAltaPerformance();
		// VOParticipanteDetallado = null;
		try {
			VOParticipanteDetallado = controllerParticipanteDetallado.ListarParticipanteDetallado(idParaAgregarlePerf);
		} catch (ParticipantesExceptions e) {
			JOptionPane.showMessageDialog(null, Mensajes.M_PARTICIPANTE_ERROR, "Alerta", JOptionPane.ERROR_MESSAGE);
		} catch (Exception e) {
			e.printStackTrace();
		}
		textField_NombreArtistico.setText(VOParticipanteDetallado.getNombreArtistico());
		textField_NombreYApellido.setText(VOParticipanteDetallado.getNombreYApellido());
		textField_Edad.setText(String.valueOf(VOParticipanteDetallado.getEdad()));
		textField_EspecialidadArtistica.setText(VOParticipanteDetallado.getEspecialidadArtistica());
		textField_PuntajePerformances.setText(String.valueOf(VOParticipanteDetallado.getPuntajeTodasLasPerformances()));

		imagen = Base64.getDecoder().decode(VOParticipanteDetallado.getFoto());
		miCanvasSmall.setImagen(imagen);
		miCanvasSmall.repaint();

		try {
			cantidadPerformances = Performances().size();
		} catch (Exception e) {
			e.printStackTrace();
		}
		textField_NumeroPerformance.setText(String.valueOf(cantidadPerformances + 1));
	}

	private List<VOPerformance> Performances() throws Exception {

		try {
			ControllerAltaPerformance controllerlistarPerformances = new ControllerAltaPerformance();
			lista = controllerlistarPerformances.ListarTodosLasPerformances(idParaAgregarlePerf);

		} catch (Exception e) {

			throw new Exception(e.getMessage());

		}
		return lista;
	}

	private void limpiarPantalla() {

		// CASO EN EL CUAL SE MODIFIQUE DE BACK-END.
		try {
			cantidadPerformances = Performances().size();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		cantidadDePerformancesMaximas = Integer.parseInt(properties.getCantidadPerformances());
		
		textArea_Descripcion.setText("");
		formattedTextField_PuntajeDeJurado_1.setValue(null);
		formattedTextField_PuntajeDeJurado_2.setValue(null);
		formattedTextField_PuntajeDeJurado_3.setValue(null);

		if (cantidadPerformances >= cantidadDePerformancesMaximas) {
			textField_NumeroPerformance.setText("Maximo alcanzado");
		} else {
			textField_NumeroPerformance.setText(String.valueOf(cantidadPerformances + 1));
		}
	}
}
