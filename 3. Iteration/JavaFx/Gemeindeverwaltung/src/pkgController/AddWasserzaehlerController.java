package pkgController;

import java.net.URL;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.ResourceBundle;
import java.util.Vector;

import pkgClasses.Haushalt;
import pkgClasses.Wasserzaehler;
import pkgDatabase.Database;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

public class AddWasserzaehlerController implements Initializable {
	@FXML
	private ComboBox<Integer> cbox_HH_ID;
	@FXML
	private TextField txtZaehlerNr;
	@FXML
	private TextField txtZaehlerstand;
	@FXML
	private CheckBox chboxHauptzaehler;
	@FXML
	private TextField txtX;
	@FXML
	private TextField txtY;
	@FXML
	private Label lblInfo;
	@FXML
	private Button btnCancel;
	@FXML
	private Button btnOK;
	private Database db = Database.getInstance();
	
	@FXML
	void onAction_btnOK(Event event) throws SQLException {
		Wasserzaehler wz = new Wasserzaehler(Integer.parseInt(txtZaehlerNr.getText()), cbox_HH_ID.getSelectionModel().getSelectedItem(), Integer.parseInt(txtZaehlerstand.getText()), chboxHauptzaehler.isSelected(), Integer.parseInt(txtX.getText()), Integer.parseInt(txtY.getText()));
		if (!db.wasserzaehlerExists(wz)) {
			db.insertWasserzaehler(wz);
			Stage stage = (Stage) btnOK.getScene().getWindow();
			stage.fireEvent(new WindowEvent(stage, WindowEvent.WINDOW_CLOSE_REQUEST));
		} else {
			lblInfo.setText("error: Haushalt already exists");
		}
	}
	
	@FXML
	void onAction_btnCancel(Event event) {
    	Stage stage = (Stage) btnCancel.getScene().getWindow();
        stage.close();
	}
	
	public void fillCbox_HH_ID() {
		Vector<Integer> vecHHID = new Vector<Integer>();
		Iterator<Haushalt> itrHaushalt = db.getVecHaushalt().iterator();
		while (itrHaushalt.hasNext()) {
			Haushalt tempHaushalt = itrHaushalt.next();
			vecHHID.add(tempHaushalt.getHH_ID());
		}
		ObservableList<Integer> tmpList = FXCollections.observableArrayList(vecHHID);
		cbox_HH_ID.setItems(tmpList);
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		try {
			db.loadHaushalteFromDb();
			db.loadWasserzaehlerFromDb();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		fillCbox_HH_ID();
	}
}
