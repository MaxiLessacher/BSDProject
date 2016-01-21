package pkgController;

import java.net.URL;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.ResourceBundle;
import java.util.Vector;

import pkgClasses.Ort;
import pkgClasses.Strasse;
import pkgDatabase.Database;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

public class AddStrasseController implements Initializable {
	@FXML
	private TextField txtStrasse;
	@FXML
	private ComboBox<Integer> cboxPLZ;
	@FXML
	private Label lblInfo;
	@FXML
	private Button btnCancel;
	@FXML
	private Button btnOK;
	private Database db = Database.getInstance();
	
	@FXML
	void onAction_btnOK(Event event) throws SQLException {
		Strasse strasse = new Strasse(txtStrasse.getText(), cboxPLZ.getSelectionModel().getSelectedItem());
		if (!db.streetExists(strasse)) {
			db.insertStrasse(strasse);
			Stage stage = (Stage) btnOK.getScene().getWindow();
			stage.fireEvent(new WindowEvent(stage, WindowEvent.WINDOW_CLOSE_REQUEST));
		} else {
			lblInfo.setText("error: Strasse already exists");
		}
	}
	
	@FXML
	void onAction_btnCancel(Event event) {
    	Stage stage = (Stage) btnCancel.getScene().getWindow();
        stage.close();
	}
	
	public void fillCbox() {
		Vector<Integer> vecPLZ = new Vector<Integer>();
		Iterator<Ort> itrOrte = db.getVecOrt().iterator();
		while (itrOrte.hasNext()) {
			Ort tempOrt = itrOrte.next();
			vecPLZ.add(tempOrt.getPlz());
		}
		ObservableList<Integer> tmpList = FXCollections.observableArrayList(vecPLZ);
		cboxPLZ.setItems(tmpList);
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		try {
			db.loadOrtFromDb();
			db.loadStrasseFromDb();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		fillCbox();
	}
}
