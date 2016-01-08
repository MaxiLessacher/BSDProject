package pkgController;

import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.ResourceBundle;
import java.util.Vector;

import pkgClasses.Wasserstandsmeldung;
import pkgDatabase.Database;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

public class Controller implements Initializable {
	@FXML
	private ComboBox<Integer> cboxZaehlerNr;
	@FXML
	private DatePicker datePicker;
	@FXML
	private TextField txtNeuZaehlerstand;
	@FXML
	private Label lblInfo;
	@FXML
	private Button btnMelden;
	private Database db = Database.getInstance();
	
	@FXML
	void onAction_btnMelden(Event event) throws SQLException {
		Wasserstandsmeldung wm = new Wasserstandsmeldung(Date.from(datePicker.getValue().atStartOfDay().atZone(ZoneId.systemDefault()).toInstant()), cboxZaehlerNr.getSelectionModel().getSelectedItem(), Integer.parseInt(txtNeuZaehlerstand.getText()));
		if (!db.wmExists(wm)) {
			db.insertWasserstandsmeldung(wm);
			Stage stage = (Stage) btnMelden.getScene().getWindow();
			stage.fireEvent(new WindowEvent(stage, WindowEvent.WINDOW_CLOSE_REQUEST));
		} else {
			lblInfo.setText("error: Wasserstandsmeldung already exists");
		}
	}
	
	@FXML
	void onAction_datePicker(Event event) {
		if (datePicker.getValue().isAfter(LocalDate.now())) {
			lblInfo.setText("you have to choose a date that is not in the future");
			btnMelden.setDisable(true);
		}
		else {
			lblInfo.setText("");
			btnMelden.setDisable(false);
		}
	}
	
	public void fillCboxZaehlerNr() {
		Vector<Integer> vecZaehlerNr = db.getVecWasserzaehler();
		ObservableList<Integer> tmpList = FXCollections.observableArrayList(vecZaehlerNr);
		cboxZaehlerNr.setItems(tmpList);
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		try {
			db.loadWasserstandsmeldungFromDb();
			db.loadWasserzaehlerFromDb();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		fillCboxZaehlerNr();
		datePicker.setValue(LocalDate.now());
	}
}
