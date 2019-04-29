package OldTestView;

import java.awt.*;
import javax.swing.*;

import View.JIFrame_a_InicioProgramaListParticipantes;
import View.JIFrame_b_AltaParticipante;
import View.JIFrame_c_ViewParticipanteListPerformances;
import View.JIFrame_d_AltaPerformance;
import View.JPanelImageBg;

import java.awt.event.*;
import java.beans.*;
import java.io.FileNotFoundException;
import java.io.IOException;

public class JFrameContenedorPrincipal extends JFrame {

	private static final long serialVersionUID = 1L;
	private JIFrame_a_InicioProgramaListParticipantes ventana_a;
	private JIFrame_b_AltaParticipante ventana_b;
	private JIFrame_c_ViewParticipanteListPerformances ventana_c;
	private JIFrame_d_AltaPerformance ventana_d;
	JPanel contentPane;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					JFrameContenedorPrincipal frame = new JFrameContenedorPrincipal();
					frame.setVisible(true);
					frame.setLocationRelativeTo(null);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public JFrameContenedorPrincipal() {
		// this.setLocationRelativeTo(null);
		try {
			setDefaultLookAndFeelDecorated(true);
			JDialog.setDefaultLookAndFeelDecorated(true);
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Exception e) {

		}

		setIconImage(Toolkit.getDefaultToolkit()
				.getImage(JFrameContenedorPrincipal.class.getResource("/imagenes/Diapositiva1.PNG")));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 800, 600);

		JMenuBar Concurso = new JMenuBar();
		setJMenuBar(Concurso);

		JMenu mnInicio = new JMenu("Inicio");
		Concurso.add(mnInicio);
		getContentPane().setLayout(new CardLayout(0, 0));

		// Crear y Agregar el Panel
		contentPane = new JPanel();
		contentPane.setBackground(new java.awt.Color(2, 49, 75));

		this.getContentPane().add(contentPane, "name_154833586437864");
		contentPane.setLayout(null);

		JPanelImageBg panelImageBg = new JPanelImageBg();
		panelImageBg.setBounds(0, 0, 784, 440);
		panelImageBg.setBorder(null);
		panelImageBg.setBackground(new Color(2, 49, 75));
		contentPane.add(panelImageBg);
		panelImageBg.setLayout(new CardLayout(0, 0));

		this.setTitle("Talento Uruguayo - SPT (Sistema de Participantes de TalentoUruguayo)");

		JMenuItem mntmPantallaPrincipalParticipantes = new JMenuItem("Pantalla Principal - Participantes");
		mntmPantallaPrincipalParticipantes.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				panelImageBg.setVisible(false);

				cerrarTodoSiEstaInstanciadoYVisible();

				if (ventana_a == null) {
					try {
						ventana_a = new JIFrame_a_InicioProgramaListParticipantes();
						ventana_a.setLocation(0, 0);
						contentPane.add(ventana_a);
						ventana_a.show();
					} catch (PropertyVetoException e) {
						e.printStackTrace();
					} catch (FileNotFoundException e) {
						e.printStackTrace();
					} catch (IOException e) {
						e.printStackTrace();
					} catch (Exception e) {
						e.printStackTrace();
					}

				} else {
					ventana_a.show();
				}

				// if (v2 == null || v1.isClosed()) {
				//
				// v2 = new JIFrameAltaParticipante();
				// v2.setLocation(10, 10);
				//
				// contentPane.add(v2);
				// v2.show();
				//
				// } else {
				//
				// v1.show();
				// }

			}
		});
		mnInicio.add(mntmPantallaPrincipalParticipantes);

		JMenuItem mntmTestb = new JMenuItem("TestB");
		mntmTestb.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				panelImageBg.setVisible(false);

				cerrarTodoSiEstaInstanciadoYVisible();

				if (ventana_b == null) {
					ventana_b = new JIFrame_b_AltaParticipante();
					ventana_b.setLocation(0, 0);
					contentPane.add(ventana_b);
					ventana_b.show();

				} else {
					ventana_b.show();
				}

				// if (v2 == null || v1.isClosed()) {
				//
				// v2 = new JIFrameAltaParticipante();
				// v2.setLocation(10, 10);
				//
				// contentPane.add(v2);
				// v2.show();
				//
				// } else {
				//
				// v1.show();
				// }

			}
		});
		mnInicio.add(mntmTestb);

		JMenuItem mntmTestc = new JMenuItem("TestC");
		mntmTestc.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				panelImageBg.setVisible(false);

				cerrarTodoSiEstaInstanciadoYVisible();

				if (ventana_c == null) {
					try {
						ventana_c = new JIFrame_c_ViewParticipanteListPerformances("El ZORRO N");
						ventana_c.setLocation(0, 0);
						contentPane.add(ventana_c);
						ventana_c.show();
					} catch (PropertyVetoException e) {
						e.printStackTrace();
					} catch (Exception e) {
						e.printStackTrace();
					}

				} else {
					ventana_c.show();
				}

			}
		});
		mnInicio.add(mntmTestc);

		JMenuItem mntmTestd = new JMenuItem("TestD");
		mntmTestd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				panelImageBg.setVisible(false);

				cerrarTodoSiEstaInstanciadoYVisible();

				if (ventana_d == null) {
					try {
						ventana_d = new JIFrame_d_AltaPerformance("El ZORRO N");
						ventana_d.setLocation(0, 0);
						contentPane.add(ventana_d);
						ventana_d.show();
					} catch (PropertyVetoException e) {
						e.printStackTrace();
					}

				} else {
					ventana_d.show();
				}

			}
		});
		mnInicio.add(mntmTestd);

		JSeparator separator = new JSeparator();
		// separator.setBackground(new java.awt.Color(2, 49, 75));
		// separator.setForeground(new java.awt.Color(2, 49, 75));
		mnInicio.add(separator); // Una rayita separadora.
		JMenuItem mntmCloseAll = new JMenuItem("Volver a la pantalla de carga");
		mntmCloseAll.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				cerrarTodoSiEstaInstanciadoYVisible();
				panelImageBg.setVisible(true);

			}
		});
		mnInicio.add(mntmCloseAll);

	}

	public void imgSetVisible() {
		// lblNewLabel.setVisible(true);

	}

	public void cerrarTodoSiEstaInstanciadoYVisible() {

		if (ventana_a != null && ventana_a.isVisible()) {
			ventana_a.dispose();// .setVisible(false);
			ventana_a = null;
		}
		if (ventana_b != null && ventana_b.isVisible()) {
			ventana_b.dispose();// setVisible(false);
			ventana_b = null;
		}
		if (ventana_c != null && ventana_c.isVisible()) {
			ventana_c.dispose();// setVisible(false);
			ventana_c = null;
		}
		if (ventana_d != null && ventana_d.isVisible()) {
			ventana_d.dispose();// setVisible(false);
			ventana_d = null;
		}
	}

}
