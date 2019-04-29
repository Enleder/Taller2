package OldTestView;

import java.awt.EventQueue;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.awt.event.ActionEvent;
import java.awt.Toolkit;

public class JFramePrincipal extends JFrame {

	private static final long serialVersionUID = 1L;
	private JIFrameTestRMI v1;
	private JIFrameAltaParticipante v2;
	public static JPanel contentPane = new JPanel();
	public testSoloNumeros panelSoloNumerosTest;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					JFramePrincipal frame = new JFramePrincipal();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public JFramePrincipal() {

		try {
			setDefaultLookAndFeelDecorated(true);
			JDialog.setDefaultLookAndFeelDecorated(true);
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Exception e) {
		}

		setIconImage(
				Toolkit.getDefaultToolkit().getImage(JFramePrincipal.class.getResource("/imagenes/Diapositiva1.PNG")));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 914, 594);

		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);

		JMenu mnInicio = new JMenu("Inicio");
		menuBar.add(mnInicio);

		// Crear y Agregar el Panel

		contentPane.setBackground(new java.awt.Color(2, 49, 75));
		contentPane.setLayout(null);

		this.getContentPane().add(contentPane);

		this.setTitle("Talento Uruguayo - SPT (Sistema de Participantes de TalentoUruguayo)");

		JMenuItem mntmMantenerParticipantes = new JMenuItem("TEST RMI");
		mntmMantenerParticipantes.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				if (v1 == null || v1.isClosed()) {

					v1 = new JIFrameTestRMI();
					v1.setLocation(10, 10);

					contentPane.add(v1);
					v1.show();

				} else {

					v1.show();
				}
				// lblNewLabel.setVisible(false);

			}
		});

		mnInicio.add(mntmMantenerParticipantes);

		JMenuItem mntmPantallaPrincipalParticipantes = new JMenuItem("Pantalla Principal - Participantes");
		mntmPantallaPrincipalParticipantes.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		mnInicio.add(mntmPantallaPrincipalParticipantes);

		JMenuItem mntmRegistrarParticipante = new JMenuItem("Registrar un Participante");
		mntmRegistrarParticipante.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				if (v2 == null || v1.isClosed()) {

					v2 = new JIFrameAltaParticipante();
					v2.setLocation(10, 10);

					contentPane.add(v2);
					v2.show();

				} else {

					v1.show();
				}

			}
		});
		mnInicio.add(mntmRegistrarParticipante);

		JMenuItem mntmPrueba = new JMenuItem("prueba");
		mnInicio.add(mntmPrueba);

		JMenuItem testSoloNumerosMENU = new JMenuItem("testSoloNumeros");
		testSoloNumerosMENU.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				if (panelSoloNumerosTest == null || panelSoloNumerosTest.isClosed()) {

					try {
						panelSoloNumerosTest = new testSoloNumeros();
					} catch (ParseException e1) {
						e1.printStackTrace();
					}
					panelSoloNumerosTest.setLocation(10, 10);

					contentPane.add(panelSoloNumerosTest);
					panelSoloNumerosTest.show();

				} else {

					panelSoloNumerosTest.show();
				}

			}
		});

		mnInicio.add(testSoloNumerosMENU);

	}

	public void imgSetVisible() {
		// lblNewLabel.setVisible(true);

	}
}
