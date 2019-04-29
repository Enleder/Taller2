package View.ComponentesPanel;

import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import Controller.ControllerParticipanteListPerformances;
import Exceptions.ParticipantesExceptions;
import Exceptions.PerformancesExceptions;
import Logic.Mensajes;
import ValueObjects.VOPerformance;
import java.awt.BorderLayout;
import javax.swing.JScrollPane;
import javax.swing.JOptionPane;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;
import java.awt.Font;

public class JPanelGrillaPerformancesR4 extends JPanel {

	private static final long serialVersionUID = 1L;

	public JTable gridPerformances;

	private static Object[][] datosFilas = {};

	private final static String nombresColumnas[] = { "Numero", "Descripcion", "Puntaje Jurado 1", "Puntaje Jurado 2",
			"Puntaje Jurado 3" };

	private String idParticipanteLocal;

	private TableModel modelo;

	public JPanelGrillaPerformancesR4(String nombreArtistico) throws Exception {

		idParticipanteLocal = nombreArtistico;
		setLayout(new BorderLayout(5, 5));
		cargarGrillaPerformances();
		// creamos el modelo con los datos
		TableModel modelo = new DefaultTableModel(datosFilas, nombresColumnas) {
			private static final long serialVersionUID = 1L;

			public Class<?> getColumnClass(int column) {
				return getValueAt(0, column).getClass();
			}
		};
 
		// creamos la Table basados en el modelo de datos que hemos creado
		JTable table = new JTable(modelo);
		table.setFont(new Font("Tahoma", Font.PLAIN, 14));
		table.setEnabled(false);
		// table.addMouseListener(new MouseAdapter() {
		// @Override
		// public void mouseClicked(MouseEvent e) {
		//
		// if (table.getSelectedRow() != -1) {
		//
		// JOptionPane.showMessageDialog(null, table.getValueAt(table.getSelectedRow(),
		// 1), "Alerta",
		// JOptionPane.ERROR_MESSAGE);
		//
		// }
		//
		// }
		// });

		// ordenacion de filas (por defecto, al ser tipos primitivos)
		TableRowSorter<TableModel> sorter = new TableRowSorter<TableModel>(modelo);
		table.setRowSorter(sorter);

		// creamos un scroll y le añadomos la tabla
		JScrollPane scrollPane = new JScrollPane(table);

		// // contenedor principal
		// JFrame frame = new JFrame("Tabla con filtros");
		// // le decimos que cierre
		// frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		// // añadimos la tabla
		// frame.add(scrollPane);
		// // tamaño
		// frame.setSize(500, 150);
		// // mostramos
		// frame.setVisible(true);
		this.add(scrollPane);
		this.setSize(200, 150);
		this.setVisible(true);

	}

	private void cargarGrillaPerformances() throws Exception {

		try {
			ControllerParticipanteListPerformances controllerlistarPerformances = new ControllerParticipanteListPerformances();
			List<VOPerformance> lista = new ArrayList<VOPerformance>();
			lista = controllerlistarPerformances.ListarTodosLasPerformances(idParticipanteLocal);
			if (lista.size() > 0) {

				datosFilas = new Object[lista.size()][5];

				for (int i = 0; i < lista.size(); i++) {

					datosFilas[i][0] = String.valueOf(lista.get(i).getNumero());
					datosFilas[i][1] = String.valueOf(lista.get(i).getDescripcion());
					datosFilas[i][2] = String.valueOf(lista.get(i).getPuntajeJuez1());
					datosFilas[i][3] = String.valueOf(lista.get(i).getPuntajeJuez2());
					datosFilas[i][4] = String.valueOf(lista.get(i).getPuntajeJuez3());
				}

				// creamos el modelo con los datos
				modelo = new DefaultTableModel(datosFilas, nombresColumnas) {
					private static final long serialVersionUID = 1L;

					public Class<?> getColumnClass(int column) {
						return getValueAt(0, column).getClass();
					}
				};
			} else {
				// JOptionPane.showMessageDialog(null, Mensajes.M_PERFORMANCE_NOHAYNINGUNA,
				// "Lista de Performances",
				// JOptionPane.INFORMATION_MESSAGE);
			}

		} catch (RemoteException e) {
			JOptionPane.showMessageDialog(null, Mensajes.M_REMOTE_ERROR, "Alerta", JOptionPane.ERROR_MESSAGE);

		} catch (PerformancesExceptions e) {
			JOptionPane.showMessageDialog(null, Mensajes.M_PERFORMANCE_ERROR, "Alerta", JOptionPane.ERROR_MESSAGE);

		} catch (ParticipantesExceptions e) {
			JOptionPane.showMessageDialog(null, Mensajes.M_PARTICIPANTE_ERROR, "Alerta", JOptionPane.ERROR_MESSAGE);

		} catch (InterruptedException e) {
			JOptionPane.showMessageDialog(null, Mensajes.M_DESCONOCIDO_ERROR, "Alerta", JOptionPane.ERROR_MESSAGE);

		}

	}
}
