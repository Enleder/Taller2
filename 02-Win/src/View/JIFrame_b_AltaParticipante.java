package View;

import javax.swing.JInternalFrame;
import javax.swing.JPanel;
import java.awt.EventQueue;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.NumberFormat;
import javax.swing.JFrame;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.Component;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.border.TitledBorder;
import javax.swing.text.MaskFormatter;
import javax.swing.text.NumberFormatter;
import java.util.Base64;
import Controller.ControllerAltaParticipante;
import Exceptions.ParticipantesExceptions;
import Logic.Mensajes;
import Logic.TipoParticipante;
import ValueObjects.VOExtranjero;
import ValueObjects.VONacional;
import ValueObjects.VOParticipanteDetallado;
import View.FotografiaTools.ImagenConvertidor;
import View.FotografiaTools.MiCanvas;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import java.awt.GridLayout;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.SwingConstants;
import javax.swing.JFormattedTextField;
import javax.swing.*;

public class JIFrame_b_AltaParticipante extends JInternalFrame {

	private static final long serialVersionUID = 1L;
	protected static final Component JIFrame_b_AltaParticipante = null;
	private JFrame frmVentanaFoto;
	private JTextField textField_NombreArtistico;
	private JTextField textField_NombreYApellido;
	private JTextField textField_EspecialidadArtistica;
	private JTextField textField_Localidad;
	private MiCanvas canvasForProfilePhoto;
	private JTextField textField_PaisDeOrigen;
	private JComboBox<Object> comboBox_Departamento;
	private JComboBox<Object> comboBox_TipoDeNacionalidad;
	private MaskFormatter mascara;
	private JFormattedTextField formattedTextField_Edad;
	private JFormattedTextField formattedTextField_CantidadAnios;
	private byte[] imagen;
	private int idTipo;
	private int cantidadAniosEnUy = 0;
	private int edad = 0;
	private String departamentoNombre;
	private JIFrame_a_InicioProgramaListParticipantes ventana_a;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					JIFrame_b_AltaParticipante frame = new JIFrame_b_AltaParticipante();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public JIFrame_b_AltaParticipante() {

		NumberFormat formato = NumberFormat.getInstance();
		NumberFormatter formatear = new NumberFormatter(formato);
		formatear.setValueClass(Integer.class);
		formatear.setMinimum(1);
		formatear.setMaximum(Integer.MAX_VALUE);
		formatear.setAllowsInvalid(false);
		formatear.setCommitsOnValidEdit(true);
		getContentPane().setEnabled(false);
		setFrameIcon(new ImageIcon(JIFrame_b_AltaParticipante.class.getResource("/imagenes/Diapositiva1.PNG")));
		setResizable(true);
		setMaximizable(true);
		setTitle("Registrar un nuevo Participante");
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setBounds(100, 100, 780, 535);
		getContentPane().setLayout(null);

		JPanel PanelAllData_FreeStyle = new JPanel();
		PanelAllData_FreeStyle.setBorder(
				new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Ingresa los datos del nuevo participante",
						TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		PanelAllData_FreeStyle.setBounds(20, 20, 722, 415);
		getContentPane().add(PanelAllData_FreeStyle);
		PanelAllData_FreeStyle.setLayout(null);

		JPanel PanelMainData_GridStyle = new JPanel();
		PanelMainData_GridStyle.setBorder(null);
		PanelMainData_GridStyle.setBounds(10, 34, 531, 190);

		PanelAllData_FreeStyle.add(PanelMainData_GridStyle);
		PanelMainData_GridStyle.setLayout(new GridLayout(5, 2, 5, 5));

		JLabel label_NombreArtistico = new JLabel("Nombre Artistico");
		label_NombreArtistico.setFont(new Font("Tahoma", Font.BOLD, 11));
		PanelMainData_GridStyle.add(label_NombreArtistico);

		textField_NombreArtistico = new JTextField();

		textField_NombreArtistico.setToolTipText("Ingrese Nombre Artistico");
		textField_NombreArtistico.setColumns(10);
		PanelMainData_GridStyle.add(textField_NombreArtistico);

		JLabel label_NombreYApellido = new JLabel("Nombre y Apellido");
		label_NombreYApellido.setFont(new Font("Tahoma", Font.BOLD, 11));
		PanelMainData_GridStyle.add(label_NombreYApellido);

		textField_NombreYApellido = new JTextField();
		textField_NombreYApellido.setToolTipText("Ingrese Nombre y Apellido");
		textField_NombreYApellido.setColumns(10);
		PanelMainData_GridStyle.add(textField_NombreYApellido);

		JLabel label_Edad = new JLabel("Edad");
		label_Edad.setFont(new Font("Tahoma", Font.BOLD, 11));
		PanelMainData_GridStyle.add(label_Edad);

		formattedTextField_Edad = new JFormattedTextField(formatear);
//		formattedTextField_Edad.setValue();
		PanelMainData_GridStyle.add(formattedTextField_Edad);

		JLabel label_EspecialidadArtistica = new JLabel("EspecialidadArtistica");
		label_EspecialidadArtistica.setFont(new Font("Tahoma", Font.BOLD, 11));
		PanelMainData_GridStyle.add(label_EspecialidadArtistica);

		textField_EspecialidadArtistica = new JTextField();
		textField_EspecialidadArtistica.setToolTipText("Ingrese Especialidad Artistica");
		textField_EspecialidadArtistica.setColumns(10);
		PanelMainData_GridStyle.add(textField_EspecialidadArtistica);

		JLabel label_Fotografia = new JLabel("Fotografia");
		label_Fotografia.setFont(new Font("Tahoma", Font.BOLD, 11));
		PanelMainData_GridStyle.add(label_Fotografia);

		JButton btn_SubirFotografia = new JButton("Subir Foto");
		btn_SubirFotografia.setToolTipText("");

		btn_SubirFotografia.setForeground(Color.BLACK);
		btn_SubirFotografia.setBackground(Color.BLACK);

		btn_SubirFotografia.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				try {

					UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");// UIManager.getSystemLookAndFeelClassName());//

					JFileChooser fc = new JFileChooser();
					fc.showOpenDialog(JIFrame_b_AltaParticipante);
					File file = fc.getSelectedFile();

					if (file != null) {
						String nombreArchivo = file.getName();
						int index = nombreArchivo.indexOf(".");
						String extension = nombreArchivo.substring(index + 1, nombreArchivo.length());
						if (extension.equalsIgnoreCase("jpg")) {
							System.out.println("file.getAbsolutePath():   " + file.getAbsolutePath());
							imagen = ImagenConvertidor.convertirImagen(file.getAbsolutePath());
							canvasForProfilePhoto.setImagen(imagen);
							canvasForProfilePhoto.repaint();
						}
					}
				} catch (ClassNotFoundException e1) {
					e1.printStackTrace();
				} catch (InstantiationException e1) {
					e1.printStackTrace();
				} catch (IllegalAccessException e1) {
					e1.printStackTrace();
				} catch (UnsupportedLookAndFeelException e1) {
					e1.printStackTrace();
				}
			}
		});
		PanelMainData_GridStyle.add(btn_SubirFotografia);

		JPanel PanelByTipoDeParticipante = new JPanel();
		PanelByTipoDeParticipante.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"),
				"Datos respecto a la nacionalidad", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		PanelByTipoDeParticipante.setBounds(10, 235, 531, 169);
		PanelAllData_FreeStyle.add(PanelByTipoDeParticipante);
		PanelByTipoDeParticipante.setLayout(new GridLayout(5, 1, 5, 5));

		JLabel label_TipoDeNacionalidad = new JLabel("Tipo de Nacionalidad");
		PanelByTipoDeParticipante.add(label_TipoDeNacionalidad);
		label_TipoDeNacionalidad.setFont(new Font("Tahoma", Font.BOLD, 11));

		String[] tipos = { "NACIONAL", "EXTRANJERO" };
		JComboBox<Object> comboBox_TipoDeNacionalidad = new JComboBox<Object>(tipos);
		comboBox_TipoDeNacionalidad.setSelectedIndex(0);
		PanelByTipoDeParticipante.add(comboBox_TipoDeNacionalidad);

		JLabel label_Departamento = new JLabel("Departamento");
		label_Departamento.setForeground(Color.BLACK);
		label_Departamento.setFont(new Font("Tahoma", Font.BOLD, 11));
		PanelByTipoDeParticipante.add(label_Departamento);

		JComboBox<Object> comboBox_Departamento = new JComboBox<Object>(new Object[] {});

		comboBox_Departamento.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				departamentoNombre = comboBox_Departamento.getSelectedItem().toString();
			}
		});
		comboBox_Departamento.setModel(
				new DefaultComboBoxModel(new String[] { "ARTIGAS", "CANELONES", "CERRO LARGO", "COLONIA", "DURAZNO",
						"FLORES", "FLORIDA", "LAVALEJA", "MALDONADO", "MONTEVIDEO ", "PAYSAND\u00DA", "R\u00CDO NEGRO",
						"RIVERA", "ROCHA", "SALTO", "SAN JOS\u00C9", "SORIANO", "TACUAREMB\u00D3", "TREINTA Y TRES" }));

		comboBox_Departamento.setSelectedIndex(0);
		PanelByTipoDeParticipante.add(comboBox_Departamento);

		JLabel label_Localidad = new JLabel("Localidad");
		label_Localidad.setForeground(Color.BLACK);
		label_Localidad.setFont(new Font("Tahoma", Font.BOLD, 11));
		PanelByTipoDeParticipante.add(label_Localidad);

		textField_Localidad = new JTextField();
		textField_Localidad.setToolTipText("Ingrese Localidad");
		textField_Localidad.setColumns(10);
		PanelByTipoDeParticipante.add(textField_Localidad);

		JLabel label_PaisDeOrigen = new JLabel("Pais de Origen");
		label_PaisDeOrigen.setForeground(Color.LIGHT_GRAY);
		label_PaisDeOrigen.setFont(new Font("Tahoma", Font.BOLD, 11));
		PanelByTipoDeParticipante.add(label_PaisDeOrigen);

		textField_PaisDeOrigen = new JTextField();
		textField_PaisDeOrigen.setToolTipText("Ingrese Pais de Origen");
		textField_PaisDeOrigen.setColumns(10);
		PanelByTipoDeParticipante.add(textField_PaisDeOrigen);

		JLabel label_CantidadAnios = new JLabel("Cantidad de a\u00F1os en Uruguay");
		label_CantidadAnios.setForeground(Color.LIGHT_GRAY);
		label_CantidadAnios.setFont(new Font("Tahoma", Font.BOLD, 11));
		PanelByTipoDeParticipante.add(label_CantidadAnios);

		formattedTextField_CantidadAnios = new JFormattedTextField(formatear);
//		formattedTextField_CantidadAnios.setValue(1);
		formattedTextField_CantidadAnios.setToolTipText("Ingrese Cantidad de A\u00F1os en Uruguay");
		PanelByTipoDeParticipante.add(formattedTextField_CantidadAnios);

		canvasForProfilePhoto = new MiCanvas();
		canvasForProfilePhoto.setBackground(Color.LIGHT_GRAY);
		canvasForProfilePhoto.setBounds(553, 34, 146, 190);
		PanelAllData_FreeStyle.add(canvasForProfilePhoto);

		imagen = ImagenConvertidor.convertirImagen("src\\imagenes\\imgFotoSize.jpg");
		canvasForProfilePhoto.setImagen(imagen);

		JLabel lblOnlyJPGimg = new JLabel("*Unicamente se permite .jpg");
		lblOnlyJPGimg.setHorizontalAlignment(SwingConstants.CENTER);
		lblOnlyJPGimg.setFont(new Font("Tahoma", Font.ITALIC, 11));
		lblOnlyJPGimg.setBounds(551, 235, 146, 14);
		PanelAllData_FreeStyle.add(lblOnlyJPGimg);
		canvasForProfilePhoto.repaint();

		textField_PaisDeOrigen.setEnabled(false);
		formattedTextField_CantidadAnios.setEnabled(false);

		JButton btnConfirmarRegistro = new JButton("Confirmar registro");

		btnConfirmarRegistro.setBounds(552, 450, 188, 33);
		btnConfirmarRegistro.setForeground(Color.BLUE);
		btnConfirmarRegistro.setBackground(Color.BLUE);
		getContentPane().add(btnConfirmarRegistro);

		JButton btnCacnelarYVolver = new JButton("<-- Cancelar y volver");
		btnCacnelarYVolver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					ventana_a = new JIFrame_a_InicioProgramaListParticipantes();
					ventana_a.setLocation(1, 1);
					SPT_TalentoUruguayo.desktopPane.removeAll();
					SPT_TalentoUruguayo.desktopPane.add(ventana_a);

					ventana_a.show();
				} catch (Exception e) {

					e.printStackTrace();
				}
			}
		});
		btnCacnelarYVolver.setForeground(Color.GRAY);
		btnCacnelarYVolver.setBackground(Color.LIGHT_GRAY);
		btnCacnelarYVolver.setBounds(337, 450, 188, 33);
		getContentPane().add(btnCacnelarYVolver);

		comboBox_TipoDeNacionalidad.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				idTipo = comboBox_TipoDeNacionalidad.getSelectedIndex();
				if (comboBox_TipoDeNacionalidad.getSelectedIndex() == 0) {

					label_Departamento.setForeground(Color.BLACK);
					comboBox_Departamento.setEnabled(true);

					label_Localidad.setForeground(Color.BLACK);
					textField_Localidad.setEnabled(true);

					label_PaisDeOrigen.setForeground(Color.LIGHT_GRAY);
					textField_PaisDeOrigen.setEnabled(false);

					label_CantidadAnios.setForeground(Color.LIGHT_GRAY);
					formattedTextField_CantidadAnios.setEnabled(false);

				} else {
					label_Departamento.setForeground(Color.LIGHT_GRAY);
					comboBox_Departamento.setEnabled(false);

					label_Localidad.setForeground(Color.LIGHT_GRAY);
					textField_Localidad.setEnabled(false);

					label_PaisDeOrigen.setForeground(Color.BLACK);
					textField_PaisDeOrigen.setEnabled(true);

					label_CantidadAnios.setForeground(Color.BLACK);
					formattedTextField_CantidadAnios.setEnabled(true);

				}

			}
		});

		btnConfirmarRegistro.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				try {
					DarAltaParticipante();

				} catch (FileNotFoundException e) {
					JOptionPane.showMessageDialog(null, Mensajes.M_PARTICIPANTE_ERROR, "Alerta",
							JOptionPane.ERROR_MESSAGE);
				} catch (ParticipantesExceptions e) {
					JOptionPane.showMessageDialog(null, Mensajes.M_PARTICIPANTE_ERROR, "Alerta",
							JOptionPane.ERROR_MESSAGE);
				} catch (InterruptedException e) {
					JOptionPane.showMessageDialog(null, Mensajes.M_PARTICIPANTE_ERROR, "Alerta",
							JOptionPane.ERROR_MESSAGE);
				} catch (IOException e) {
					JOptionPane.showMessageDialog(null, Mensajes.M_PARTICIPANTE_ERROR, "Alerta",
							JOptionPane.ERROR_MESSAGE);
				}

				limpiarPantalla();
			}
		});

	}

	private void limpiarPantalla() {

		imagen = ImagenConvertidor.convertirImagen("src\\imagenes\\imgFotoSize.jpg");
		canvasForProfilePhoto.setImagen(imagen);
		textField_NombreArtistico.setText("");
		textField_NombreYApellido.setText("");
		textField_EspecialidadArtistica.setText("");
		textField_PaisDeOrigen.setText("");
		departamentoNombre = "";
		textField_Localidad.setText("");
		formattedTextField_Edad.setValue(null);
		formattedTextField_CantidadAnios.setValue(null);
		cantidadAniosEnUy = 0;
		edad = 0;

	}

	private void DarAltaParticipante()
			throws FileNotFoundException, ParticipantesExceptions, InterruptedException, IOException {

		ControllerAltaParticipante con = new ControllerAltaParticipante();
		String nombreArtistico = textField_NombreArtistico.getText();
		String nombreApellido = textField_NombreYApellido.getText();
		String especialidadArtistica = textField_EspecialidadArtistica.getText();
		String foto = Base64.getEncoder().encodeToString(imagen);

		try {

			edad = (int) formattedTextField_Edad.getValue();

		} catch (NullPointerException e2) {

			JOptionPane.showMessageDialog(null, "ingrese un numero", "error", JOptionPane.ERROR_MESSAGE);

		}

		TipoParticipante tipoDeParticipante;

		VOParticipanteDetallado vo;
		try {
			if (idTipo == 1) {

				tipoDeParticipante = TipoParticipante.EXTRANJERO;

				String paisOrigen = textField_PaisDeOrigen.getText();

				try {

					cantidadAniosEnUy = (int) formattedTextField_CantidadAnios.getValue();

				} catch (NullPointerException e2) {

					JOptionPane.showMessageDialog(null, "ingrese un numero para cantidad de a�os", "error",
							JOptionPane.ERROR_MESSAGE);

				}

				vo = new VOExtranjero(nombreArtistico, nombreApellido, foto, edad, especialidadArtistica, paisOrigen,
						cantidadAniosEnUy);

				con.R1AltaParticipante(vo);

				JOptionPane.showMessageDialog(null, Mensajes.M_PARTICIPANTES_EXITO, "Alerta",
						JOptionPane.INFORMATION_MESSAGE);

			} else {

				tipoDeParticipante = TipoParticipante.NACIONAL;
				String localidad = textField_Localidad.getText();

				vo = new VONacional(nombreArtistico, nombreApellido, foto, edad, especialidadArtistica,
						departamentoNombre, localidad);

				con.R1AltaParticipante(vo);

				JOptionPane.showMessageDialog(null, Mensajes.M_PARTICIPANTES_EXITO, "Alerta",
						JOptionPane.INFORMATION_MESSAGE);

			}

			limpiarPantalla();

		} catch (ParticipantesExceptions e) {
			JOptionPane.showMessageDialog(null, Mensajes.M_PARTICIPANTE_ERROR, "Alerta", JOptionPane.ERROR_MESSAGE);

		}

	}

	public boolean isClosed() {
		return false;
	}

	public JFrame getFrmVentanaFoto() {
		return frmVentanaFoto;
	}

	public void setFrmVentanaFoto(JFrame frmVentanaFoto) {
		this.frmVentanaFoto = frmVentanaFoto;
	}

	public JTextField getTextField_NombreArtistico() {
		return textField_NombreArtistico;
	}

	public void setTextField_NombreArtistico(JTextField textField_NombreArtistico) {
		this.textField_NombreArtistico = textField_NombreArtistico;
	}

	public JTextField getTextField_NombreYApellido() {
		return textField_NombreYApellido;
	}

	public void setTextField_NombreYApellido(JTextField textField_NombreYApellido) {
		this.textField_NombreYApellido = textField_NombreYApellido;
	}

	public JTextField getTextField_EspecialidadArtistica() {
		return textField_EspecialidadArtistica;
	}

	public void setTextField_EspecialidadArtistica(JTextField textField_EspecialidadArtistica) {
		this.textField_EspecialidadArtistica = textField_EspecialidadArtistica;
	}

	public JTextField getTextField_Localidad() {
		return textField_Localidad;
	}

	public void setTextField_Localidad(JTextField textField_Localidad) {
		this.textField_Localidad = textField_Localidad;
	}

	public MiCanvas getCanvasForProfilePhoto() {
		return canvasForProfilePhoto;
	}

	public void setCanvasForProfilePhoto(MiCanvas canvasForProfilePhoto) {
		this.canvasForProfilePhoto = canvasForProfilePhoto;
	}

	public JTextField getTextField_PaisDeOrigen() {
		return textField_PaisDeOrigen;
	}

	public void setTextField_PaisDeOrigen(JTextField textField_PaisDeOrigen) {
		this.textField_PaisDeOrigen = textField_PaisDeOrigen;
	}

	public JComboBox<Object> getComboBox_Departamento() {
		return comboBox_Departamento;
	}

	public void setComboBox_Departamento(JComboBox<Object> comboBox_Departamento) {
		this.comboBox_Departamento = comboBox_Departamento;
	}

	public JComboBox<Object> getComboBox_TipoDeNacionalidad() {
		return comboBox_TipoDeNacionalidad;
	}

	public void setComboBox_TipoDeNacionalidad(JComboBox<Object> comboBox_TipoDeNacionalidad) {
		this.comboBox_TipoDeNacionalidad = comboBox_TipoDeNacionalidad;
	}

	public MaskFormatter getMascara() {
		return mascara;
	}

	public void setMascara(MaskFormatter mascara) {
		this.mascara = mascara;
	}

	public JFormattedTextField getFormattedTextField_Edad() {
		return formattedTextField_Edad;
	}

	public void setFormattedTextField_Edad(JFormattedTextField formattedTextField_Edad) {
		this.formattedTextField_Edad = formattedTextField_Edad;
	}

	public JFormattedTextField getFormattedTextField_CantidadAnios() {
		return formattedTextField_CantidadAnios;
	}

	public void setFormattedTextField_CantidadAnios(JFormattedTextField formattedTextField_CantidadAnios) {
		this.formattedTextField_CantidadAnios = formattedTextField_CantidadAnios;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public static Component getJiframeBAltaparticipante() {
		return JIFrame_b_AltaParticipante;
	}

}
