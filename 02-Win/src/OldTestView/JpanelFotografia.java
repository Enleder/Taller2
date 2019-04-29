package OldTestView;

import javax.swing.JPanel;
import java.awt.Canvas;
import java.awt.Button;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.FormSpecs;
import com.jgoodies.forms.layout.RowSpec;

public class JpanelFotografia extends JPanel {

	/**
	 * Create the panel.
	 */
	public JpanelFotografia() {
		setLayout(new FormLayout(new ColumnSpec[] {
				FormSpecs.UNRELATED_GAP_COLSPEC,
				ColumnSpec.decode("178px"),
				ColumnSpec.decode("90px"),
				ColumnSpec.decode("162px"),},
			new RowSpec[] {
				RowSpec.decode("58px"),
				RowSpec.decode("164px"),}));
		
		Button btnCargarImagen = new Button("Cargar Imagen");
		add(btnCargarImagen, "2, 2, fill, center");
		
		Canvas canvas = new Canvas();
		add(canvas, "4, 2");

	}
}
