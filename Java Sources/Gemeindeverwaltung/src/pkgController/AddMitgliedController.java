package pkgController;

import java.net.URL;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.ResourceBundle;
import java.util.Vector;

import pkgClasses.Haushalt;
import pkgClasses.Mitglied;
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

public class AddMitgliedController implements Initializable {
	@FXML
	private TextField txtMitglieds_ID;
	@FXML
	private TextField txtName;
	@FXML
	private CheckBox chbox_HH_Vorstand;
	@FXML
	private ComboBox<Integer> cbox_HH_ID;
	@FXML
	private Label lblInfo;
	@FXML
	private Button btnCancel;
	@FXML
	private Button btnOK;
	private Database db = Database.getInstance();
	
	@FXML
	void onAction_btnOK(Event event) throws SQLException {
		Mitglied mitglied = new Mitglied(Integer.parseInt(txtMitglieds_ID.getText()), txtName.getText(), chbox_HH_Vorstand.isSelected(), cbox_HH_ID.getSelectionModel().getSelectedItem());
		if (!db.memberExists(mitglied)) {
			db.insertMitglied(mitglied);
			Stage stage = (Stage) btnOK.getScene().getWindow();
			stage.fireEvent(new WindowEvent(stage, WindowEvent.WINDOW_CLOSE_REQUEST));
		} else {
			lblInfo.setText("error: Mitglied already exists");
		}
	}
	
	@FXML
	void onAction_btnCancel(Event event) {
    	Stage stage = (Stage) btnCancel.getScene().getWindow();
        stage.close();
	}
	
	public void fillCboxHH_ID() {
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
			db.loadMitgliederFromDb();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		fillCboxHH_ID();
	}
}
