package pkgController;

import java.net.URL;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.ResourceBundle;
import java.util.Vector;

import pkgClasses.Adresse;
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

public class AddAdresseController implements Initializable {
	@FXML
	private ComboBox<String> cboxStrasse;
	@FXML
	private ComboBox<Integer> cboxPLZ;
	@FXML
	private TextField txtHausnummer;
	@FXML
	private Label lblInfo;
	@FXML
	private Button btnCancel;
	@FXML
	private Button btnOK;
	private Database db = Database.getInstance();
	
	@FXML
	void onAction_btnOK(Event event) throws SQLException {
		Adresse adresse = new Adresse(cboxPLZ.getSelectionModel().getSelectedItem(), cboxStrasse.getSelectionModel().getSelectedItem(), Integer.parseInt(txtHausnummer.getText()));
		if (!db.addressExists(adresse)) {
			db.insertAdresse(adresse);
			Stage stage = (Stage) btnOK.getScene().getWindow();
			stage.fireEvent(new WindowEvent(stage, WindowEvent.WINDOW_CLOSE_REQUEST));
		} else {
			lblInfo.setText("error: Adresse already exists");
		}
	}
	
	@FXML
	void onAction_btnCancel(Event event) {
    	Stage stage = (Stage) btnCancel.getScene().getWindow();
        stage.close();
	}
	
	@FXML
	void onAction_cboxPLZ(Event event) {
		fillCboxStrasse();
		cboxStrasse.setDisable(false);
	}
	
	public void fillCboxPLZ() {
		Vector<Integer> vecPLZ = new Vector<Integer>();
		Iterator<Ort> itrOrte = db.getVecOrt().iterator();
		while (itrOrte.hasNext()) {
			Ort tempOrt = itrOrte.next();
			vecPLZ.add(tempOrt.getPlz());
		}
		ObservableList<Integer> tmpList = FXCollections.observableArrayList(vecPLZ);
		cboxPLZ.setItems(tmpList);
	}
	
	public void fillCboxStrasse() {
		Vector<String> vecStrasse = new Vector<String>();
		Iterator<Strasse> itrStrasse = db.getVecStrasse().iterator();
		while (itrStrasse.hasNext()) {
			Strasse tempStrasse = itrStrasse.next();
			if (tempStrasse.getPlz() == cboxPLZ.getSelectionModel().getSelectedItem()) {
				vecStrasse.add(tempStrasse.getStrasse());
			}
		}
		ObservableList<String> tmpList = FXCollections.observableArrayList(vecStrasse);
		cboxStrasse.setItems(tmpList);
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		try {
			db.loadOrtFromDb();
			db.loadStrasseFromDb();
			db.loadAdressenFromDb();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		fillCboxPLZ();
		cboxStrasse.setDisable(true);
	}
}
