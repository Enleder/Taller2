package View;

import javax.swing.JInternalFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.Color;
import Controller.ControllerInicioProgramaListParticipantes;
import Logic.Mensajes;
import Logic.SystemProperties;
import View.ComponentesPanel.JPanelGrillaParticipantesNoHayNinguno;
import View.ComponentesPanel.JPanelGrillaParticipantesR2;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import java.awt.GridLayout;

public class JIFrame_a_InicioProgramaListParticipantes extends JInternalFrame {

	private static final long serialVersionUID = 1L;
	private JPanelGrillaParticipantesR2 panelGrillaParticipantesSimpleR2;
	private JPanel panelContenedorCasos;
	private JPanelGrillaParticipantesNoHayNinguno PanelParticipantesNoHayNinguno;
	private ControllerInicioProgramaListParticipantes controllerlistar;
	private JButton btnRegistrarParticipante;
	private JButton btnCierreDefFinalistasAbrirVot;
	private JButton btnCierreVotObtGanador;
	private JButton btnRefreshGrid;
	private boolean tieneQEjecutarOImprimirMsg;
	private static SystemProperties properties;
	private int cantidadDeParticipantesNecesarios;
	static JIFrame_a_InicioProgramaListParticipantes frame;
	private boolean puedeEjecutarAgregar;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					frame = new JIFrame_a_InicioProgramaListParticipantes();

					frame.setVisible(true);

				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public JIFrame_a_InicioProgramaListParticipantes() throws Exception {
		// v_JFrameContenedorPrincipal = new JFrameContenedorPrincipal();

		properties = new SystemProperties();
		getContentPane().setEnabled(false);
		setFrameIcon(new ImageIcon(
				JIFrame_a_InicioProgramaListParticipantes.class.getResource("/imagenes/Diapositiva1.PNG")));
		setResizable(true);
		setMaximizable(true);
		setTitle("Pantalla Principal - Participantes");
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setBounds(100, 100, 780, 535);
		getContentPane().setLayout(null);

		btnRegistrarParticipante = new JButton("Registrar un nuevo Participante");
		btnRegistrarParticipante.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tieneQEjecutarOImprimirMsg = true;
				puedeEjecutarAgregar = false;
				validar_y_o_ejecutar_btnRegistrarParticipante(tieneQEjecutarOImprimirMsg);
				///////////////////////////// VOMER DENTRO DE LA VALIDACION
				if (puedeEjecutarAgregar) {
					JIFrame_b_AltaParticipante ventana_b = new JIFrame_b_AltaParticipante();
					ventana_b.setLocation(0, 0);
					// frmContenedorPrincipal.escritorio.add(v2);
					SPT_TalentoUruguayo.desktopPane.removeAll();
					SPT_TalentoUruguayo.desktopPane.add(ventana_b);
					ventana_b.show();
					setVisible(false);
				}

			}
		});
		btnRegistrarParticipante.setBounds(538, 80, 200, 33);
		tieneQEjecutarOImprimirMsg = false;
		validar_y_o_ejecutar_btnRegistrarParticipante(tieneQEjecutarOImprimirMsg);
		btnRegistrarParticipante.setEnabled(true);
		getContentPane().add(btnRegistrarParticipante);

		btnCierreDefFinalistasAbrirVot = new JButton(
				"Cierre de Performances, def. de Finalistas - Abrir votacion del publico");
		btnCierreDefFinalistasAbrirVot.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				tieneQEjecutarOImprimirMsg = true;
				validar_y_o_ejecutar_btnCierreDefFinalistasAbrirVot(tieneQEjecutarOImprimirMsg);
			}

		});
		tieneQEjecutarOImprimirMsg = false;
		validar_y_o_ejecutar_btnCierreDefFinalistasAbrirVot(tieneQEjecutarOImprimirMsg);

		btnCierreDefFinalistasAbrirVot.setEnabled(true);
		btnCierreDefFinalistasAbrirVot.setBounds(20, 445, 380, 33);
		getContentPane().add(btnCierreDefFinalistasAbrirVot);

		btnCierreVotObtGanador = new JButton("Cerrar votacion del publico - Obtener ganador");
		btnCierreVotObtGanador.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				tieneQEjecutarOImprimirMsg = true;
				validar_y_o_ejecutar_btnCierreVotObtGanador(tieneQEjecutarOImprimirMsg);
			}
		});
		btnCierreVotObtGanador.setEnabled(true);

		btnCierreVotObtGanador.setBounds(420, 445, 320, 33);
		tieneQEjecutarOImprimirMsg = false;
		validar_y_o_ejecutar_btnCierreVotObtGanador(tieneQEjecutarOImprimirMsg);
		getContentPane().add(btnCierreVotObtGanador);

		JLabel lblLogoHeader = new JLabel("");
		lblLogoHeader.setIcon(new ImageIcon(
				JIFrame_a_InicioProgramaListParticipantes.class.getResource("/imagenes/headerNoBG_SPT.png")));
		lblLogoHeader.setBounds(254, 11, 220, 70);
		getContentPane().add(lblLogoHeader);

		panelContenedorCasos = new JPanel();
		panelContenedorCasos.setBounds(20, 120, 718, 307);
		getContentPane().add(panelContenedorCasos);
		panelContenedorCasos.setLayout(new GridLayout(0, 1, 0, 0));

		panelGrillaParticipantesSimpleR2 = new JPanelGrillaParticipantesR2();
		panelContenedorCasos.add(panelGrillaParticipantesSimpleR2);

		btnRefreshGrid = new JButton("Refrescar lista");
		btnRefreshGrid.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent arg0) {

				// manageImageRefresh();

				try {
					// mostrar gif
					// sleep
					// ocultar gif

					cantidadDeParticipantesNecesarios = Integer.parseInt(properties.getCantidadFinalistas());

					controllerlistar = new ControllerInicioProgramaListParticipantes();
					int cantidadParticipantes = 0;
					cantidadParticipantes = controllerlistar.CuantosParticipantesHay();
					if (cantidadParticipantes <= cantidadDeParticipantesNecesarios) {
						panelGrillaParticipantesSimpleR2.setVisible(false);
						PanelParticipantesNoHayNinguno.setVisible(true);
						panelContenedorCasos.remove(panelGrillaParticipantesSimpleR2);
						panelContenedorCasos.add(PanelParticipantesNoHayNinguno);
						JOptionPane.showMessageDialog(null, Mensajes.M_NoHaySuficientesParticipantesRegistrados,
								"Información", JOptionPane.INFORMATION_MESSAGE);
					} else {
						PanelParticipantesNoHayNinguno.setVisible(false);
						panelContenedorCasos.remove(PanelParticipantesNoHayNinguno);

						panelContenedorCasos.remove(panelGrillaParticipantesSimpleR2);
						panelGrillaParticipantesSimpleR2 = null;
						panelGrillaParticipantesSimpleR2 = new JPanelGrillaParticipantesR2();
						panelContenedorCasos.add(panelGrillaParticipantesSimpleR2);
						setBounds(0, 0, 784, 540);// setBounds(100, 100, 780, 535);
					}

				} catch (Exception e) {
					JOptionPane.showMessageDialog(null, Mensajes.M_ErrorAlIntentarConectarServer, "Error",
							JOptionPane.ERROR_MESSAGE);
				}

				//
				// panelContenedorCasos.remove(panelGrillaParticipantesSimpleR2);
				// panelGrillaParticipantesSimpleR2 = null;
				// panelGrillaParticipantesSimpleR2 = new JPanelGrillaParticipantesR2();
				// panelContenedorCasos.add(panelGrillaParticipantesSimpleR2);
				// setBounds(0, 0, 780, 535);

			}

		});
		btnRefreshGrid.setBounds(22, 85, 147, 23);
		getContentPane().add(btnRefreshGrid);

		// lblRefreshImage = new JLabel("");
		// lblRefreshImage.setIcon(new ImageIcon(
		// JIFrame_a_InicioProgramaListParticipantes.class.getResource("/imagenes/headerNoBG_SPT.png")));

		// lblRefreshImage.setIcon(new ImageIcon(
		// JIFrame_a_InicioProgramaListParticipantes.class.getResource("/imagenes/refresh23x23_speed20.gif")));
		// lblRefreshImage.setHorizontalAlignment(SwingConstants.CENTER);
		// lblRefreshImage.setBounds(179, 85, 23, 23);
		// getContentPane().add(lblRefreshImage);
		// lblRefreshImage.setVisible(false);

		// lblRefreshImage2 = new JLabel("");
		// lblRefreshImage2.setIcon(new ImageIcon(
		// JIFrame_a_InicioProgramaListParticipantes.class.getResource("/imagenes/refresh23x23_speed20.gif")));
		// lblRefreshImage2.setHorizontalAlignment(SwingConstants.CENTER);
		// lblRefreshImage2.setBounds(179, 85, 23, 23);
		// getContentPane().add(lblRefreshImage2);
		// lblRefreshImage2.setVisible(false);

		PanelParticipantesNoHayNinguno = new JPanelGrillaParticipantesNoHayNinguno();

		try {
			controllerlistar = new ControllerInicioProgramaListParticipantes();
			int cantidadParticipantes = 0;
			try {
				cantidadParticipantes = controllerlistar.CuantosParticipantesHay();
			} catch (Exception e) {
				JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
			}

			if (cantidadParticipantes == 0) {
				panelGrillaParticipantesSimpleR2.setVisible(false);
				PanelParticipantesNoHayNinguno.setVisible(true);
				panelContenedorCasos.remove(panelGrillaParticipantesSimpleR2);
				panelContenedorCasos.add(PanelParticipantesNoHayNinguno);

			} else {
				panelGrillaParticipantesSimpleR2.setVisible(true);
				PanelParticipantesNoHayNinguno.setVisible(false);

				panelContenedorCasos.remove(PanelParticipantesNoHayNinguno);
				panelContenedorCasos.add(panelGrillaParticipantesSimpleR2);
			}

		} catch (Exception e1) {
			JOptionPane.showMessageDialog(null, e1.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
			e1.printStackTrace();
		}

	}

	public boolean isClosed() {
		return false;
	}

	private void validar_y_o_ejecutar_btnRegistrarParticipante(boolean tieneQImprimirMsg) {
		try {
			controllerlistar = new ControllerInicioProgramaListParticipantes();
			boolean EstadoVotacion = controllerlistar.getEstadoVotacion();
			boolean HayGanador = controllerlistar.getHayGanador();
			
			if (EstadoVotacion == true || HayGanador == true) {
				// no se puede agregar participante
				btnRegistrarParticipante.setForeground(Color.GRAY);
				btnRegistrarParticipante.setBackground(Color.GRAY);
				if (tieneQEjecutarOImprimirMsg) {
					JOptionPane.showMessageDialog(null, Mensajes.M_VotacionAbiertaOHayGanador, "Error",
							JOptionPane.ERROR_MESSAGE);
				}

			} else {
				btnRegistrarParticipante.setForeground(Color.BLUE);
				btnRegistrarParticipante.setBackground(Color.BLUE);
				/////////////////////////////////////////////////////////////////////////////// LLAMAR
				/////////////////////////////////////////////////////////////////////////////// AL
				/////////////////////////////////////////////////////////////////////////////// B
				// ventana_b = new JIFrame_b_AltaParticipante();
				// ventana_b.setLocation(0, 0);
				// frame.dispose();//add(ventana_b);
				// ventana_b.show();
				// ((JFrameContenedorPrincipal)
				// v_JFrameContenedorPrincipal).cerrarTodoSiEstaInstanciadoYVisible();
				puedeEjecutarAgregar = true;
			}
		} catch (Exception eq) {
		}
	}

	private void validar_y_o_ejecutar_btnCierreDefFinalistasAbrirVot(boolean tieneQImprimirMsg) {
		cantidadDeParticipantesNecesarios = Integer.parseInt(properties.getCantidadFinalistas());

		try {
			controllerlistar = new ControllerInicioProgramaListParticipantes();

			int cantidadParticipantes = 0;
			cantidadParticipantes = controllerlistar.CuantosParticipantesHay();
			if (cantidadParticipantes <= cantidadDeParticipantesNecesarios) {
				btnCierreDefFinalistasAbrirVot.setForeground(Color.GRAY);
				btnCierreDefFinalistasAbrirVot.setBackground(Color.GRAY);
				if (tieneQEjecutarOImprimirMsg) {
					JOptionPane.showMessageDialog(null, Mensajes.M_NoHaySuficientesParticipantesRegistrados, "Error",
							JOptionPane.ERROR_MESSAGE);
				}
			} else {

				boolean EstadoVotacion = controllerlistar.getEstadoVotacion();
				boolean HayGanador = controllerlistar.getHayGanador();
				boolean QuedaAlgunaPerformance = controllerlistar.getQuedaAlgunaPerformance();
				if (QuedaAlgunaPerformance == true) {
					// no se puede cerrarPerformances
					btnCierreDefFinalistasAbrirVot.setForeground(Color.GRAY);
					btnCierreDefFinalistasAbrirVot.setBackground(Color.GRAY);
					if (tieneQEjecutarOImprimirMsg) {
						JOptionPane.showMessageDialog(null, Mensajes.M_AunQuedanPerformances, "Error",
								JOptionPane.ERROR_MESSAGE);
					}
				} else {
					if (EstadoVotacion == true || HayGanador == true) {
						// public static final String M_VotacionAbiertaOHayGanadorR5 = "No se puede
						// ejecutar esta opcion ya que la etapa realizacion de performances ya fue
						// cerrada (la votacion del publico esta abierta o ya hay un ganador)";
						// no se puede cerrarPerformances
						btnCierreDefFinalistasAbrirVot.setForeground(Color.GRAY);
						btnCierreDefFinalistasAbrirVot.setBackground(Color.GRAY);
						if (tieneQEjecutarOImprimirMsg) {
							JOptionPane.showMessageDialog(null, Mensajes.M_VotacionAbiertaOHayGanadorR5, "Error",
									JOptionPane.ERROR_MESSAGE);
						}
					} else {
						btnCierreDefFinalistasAbrirVot.setForeground(Color.BLUE);
						btnCierreDefFinalistasAbrirVot.setBackground(Color.BLUE);
						if (tieneQEjecutarOImprimirMsg) {
							controllerlistar.R5CierrePerfsYDefinicionFinalistas();
							JOptionPane.showMessageDialog(null, Mensajes.M_ProcedimientoCorrectoR5,
									"Procedimiento Correcto", JOptionPane.INFORMATION_MESSAGE);
							btnRegistrarParticipante.setForeground(Color.GRAY);
							btnRegistrarParticipante.setBackground(Color.GRAY);							
							btnCierreDefFinalistasAbrirVot.setForeground(Color.GRAY);
							btnCierreDefFinalistasAbrirVot.setBackground(Color.GRAY);
							btnCierreVotObtGanador.setForeground(Color.BLUE);
							btnCierreVotObtGanador.setBackground(Color.BLUE);
						}
					}
				}
			}
		} catch (Exception e) {
		}
	}

	private void validar_y_o_ejecutar_btnCierreVotObtGanador(boolean tieneQImprimirMsg) {
		try {
			controllerlistar = new ControllerInicioProgramaListParticipantes();
			boolean EstadoVotacion = controllerlistar.getEstadoVotacion();
			if (EstadoVotacion == false) {
				// no se puede cerrar votacion
				btnCierreVotObtGanador.setForeground(Color.GRAY);
				btnCierreVotObtGanador.setBackground(Color.GRAY);
				if (tieneQEjecutarOImprimirMsg) {
					JOptionPane.showMessageDialog(null, Mensajes.M_VotacionNoAbierta, "Error",
							JOptionPane.ERROR_MESSAGE);
				}
			} else {

				btnCierreVotObtGanador.setForeground(Color.BLUE);
				btnCierreVotObtGanador.setBackground(Color.BLUE);
				if (tieneQEjecutarOImprimirMsg) {
					controllerlistar.R7CierreVotacionPublico();
					JOptionPane.showMessageDialog(null, Mensajes.M_ProcedimientoCorrectoR7, "Procedimiento Correcto",
							JOptionPane.INFORMATION_MESSAGE);
					btnCierreVotObtGanador.setForeground(Color.GRAY);
					btnCierreVotObtGanador.setBackground(Color.GRAY);
				}
			}
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e.getMessage(), "ERROR :: ", JOptionPane.INFORMATION_MESSAGE);
		}

	}

	// private void manageImageRefresh() {
	// Auto-generated method stub
	// getContentPane().add(lblRefreshImage);
	// lblRefreshImage.setIcon(new ImageIcon(
	// JIFrame_a_InicioProgramaListParticipantes.class.getResource("/imagenes/refresh23x23_speed20.gif")));
	// panelContenedorCasos.setVisible(false);
	// try {
	//
	// TimeUnit.SECONDS.sleep(13);
	// } catch (InterruptedException e1) {
	// // Auto-generated catch block
	// e1.printStackTrace();
	// }
	//
	// lblRefreshImage.setIcon(new ImageIcon(
	// JIFrame_a_InicioProgramaListParticipantes.class.getResource("/imagenes/headerNoBG_SPT.png")));
	// panelContenedorCasos.setVisible(true);
	// lblRefreshImage.setVisible(false);

	// lblRefreshImage2.setVisible(false);

	// lblRefreshImage.setVisible(true);

	// try {
	// TimeUnit.SECONDS.sleep(13);
	// } catch (InterruptedException e2) {
	// // Auto-generated catch block
	// e2.printStackTrace();
	// // JOptionPane.showMessageDialog(null, e2.getMessage(), "Error",
	// // JOptionPane.ERROR_MESSAGE);
	// }
	// lblRefreshImage.setVisible(false);
	//
	//
	// lblRefreshImage.setVisible(false);
	//
	// try
	// {
	// // Thread.sleep(9999);
	//
	// getContentPane().remove(lblRefreshImage);
	// // lblRefreshImage.setVisible(false);
	// }
	// catch(InterruptedException ex)
	// {
	// Thread.currentThread().interrupt();
	// }
	//
	// try {
	// TimeUnit.SECONDS.sleep(5);
	// //getContentPane().remove(lblRefreshImage);
	// } catch (InterruptedException e2) {
	// // Auto-generated catch block
	// e2.printStackTrace();
	//// JOptionPane.showMessageDialog(null, e2.getMessage(), "Error",
	//// JOptionPane.ERROR_MESSAGE);
	// }

	// }
}
