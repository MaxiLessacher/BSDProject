package pkgController;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

import pkgClasses.Ort;
import pkgDatabase.Database;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

public class AddOrtController implements Initializable {
	@FXML
	private TextField txtOrt;
	@FXML
	private TextField txtPLZ;
	@FXML
	private Label lblInfo;
	@FXML
	private Button btnCancel;
	@FXML
	private Button btnOK;
	private Database db = Database.getInstance();
	
	@FXML
	void onAction_btnOK(Event event) throws SQLException {
		Ort ort = new Ort(Integer.parseInt(txtPLZ.getText()), txtOrt.getText());
		if (!db.ortExists(ort)) {
			db.insertOrt(ort);
			Stage stage = (Stage) btnOK.getScene().getWindow();
			stage.fireEvent(new WindowEvent(stage, WindowEvent.WINDOW_CLOSE_REQUEST));
		} else {
			lblInfo.setText("error: Ort already exists");
		}
	}
	
	@FXML
	void onAction_btnCancel(Event event) {
    	Stage stage = (Stage) btnCancel.getScene().getWindow();
        stage.close();
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		try {
			db.loadOrtFromDb();
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
