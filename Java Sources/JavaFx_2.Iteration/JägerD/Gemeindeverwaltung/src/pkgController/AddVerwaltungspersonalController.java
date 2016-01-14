package pkgController;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import pkgClasses.Verwaltungspersonal;
import pkgDatabase.Database;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

public class AddVerwaltungspersonalController implements Initializable {
	@FXML
	private TextField txtPersonalID;
	@FXML
	private TextField txtName;
	@FXML
	private TextField txtAbteilung;
	@FXML
	private Label lblInfo;
	@FXML
	private Button btnCancel;
	@FXML
	private Button btnOK;
	private Database db = Database.getInstance();
	
	@FXML
	void onAction_btnOK(Event event) throws SQLException {
		Verwaltungspersonal vw = new Verwaltungspersonal(Integer.parseInt(txtPersonalID.getText()), txtName.getText(), txtAbteilung.getText());
		if (!db.vwExists(vw)) {
			db.insertVerwaltungspersonal(vw);
			Stage stage = (Stage) btnOK.getScene().getWindow();
			stage.fireEvent(new WindowEvent(stage, WindowEvent.WINDOW_CLOSE_REQUEST));
		} else {
			lblInfo.setText("error: Verwaltungspersonal already exists");
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
			db.loadVerwaltungspersonalFromDb();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
