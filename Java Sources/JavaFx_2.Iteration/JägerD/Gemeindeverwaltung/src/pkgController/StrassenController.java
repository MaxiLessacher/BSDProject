package pkgController;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TableColumn.CellEditEvent;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import pkgClasses.Strasse;
import pkgConverter.IntegerConverter;
import pkgDatabase.Database;

public class StrassenController implements Initializable {
	@FXML
	private Button btnCreate;
	@FXML
	private Button btnDelete;
	@FXML
	private Button btnCancel;
	@FXML
	private Button btnLoad;
	@FXML
    private TableColumn<Strasse, String> cellStrasse;
	@FXML
    private TableColumn<Strasse, Integer> cellPLZ;
	@FXML
	private TableView<Strasse> tableviewData;


	private Database db = Database.getInstance();

	@FXML
    void onEdit_cellStrasse(CellEditEvent<Strasse, String> event) {
    	try
    	{
    		Strasse temp = db.getVecStrasse().get(event.getTablePosition().getRow());
    		Strasse oldStrasse = new Strasse(temp.getStrasse(), temp.getPlz());
    		event.getTableView().getItems().get(event.getTablePosition().getRow()).setStrasse(event.getNewValue());
    		db.updateStrasse(oldStrasse,event.getTableView().getItems().get(event.getTablePosition().getRow()));
    	}
    	catch(Exception e)
    	{
    		e.printStackTrace();
    	}
    }

	@FXML
    void onEdit_cellPLZ(CellEditEvent<Strasse, Integer> event) {
    	try
    	{
    		Strasse temp = db.getVecStrasse().get(event.getTablePosition().getRow());
    		Strasse oldStrasse = new Strasse(temp.getStrasse(), temp.getPlz());
    		event.getTableView().getItems().get(event.getTablePosition().getRow()).setPlz(event.getNewValue());
    		db.updateStrasse(oldStrasse,event.getTableView().getItems().get(event.getTablePosition().getRow()));
    	}
    	catch(Exception e)
    	{
    		e.printStackTrace();
    	}
    }

	@FXML
	void onAction_btnCreate(Event ev) {
		try
    	{
			Stage stage = new Stage();
    		Parent rootframe = FXMLLoader.load(getClass().getResource("/resources/addStrasse.fxml"));
    		Scene scene = new Scene(rootframe);

    		stage.setScene(scene);
    		stage.setTitle("Insert Strasse");
    		stage.initModality(Modality.APPLICATION_MODAL);
    		stage.setOnCloseRequest(new EventHandler<WindowEvent>() {
				@Override
				public void handle(WindowEvent event) {
					try {
						updateTable();
					} catch (SQLException e) {
						e.printStackTrace();
					}
				}
			});
    		stage.show();
    	}
    	catch(Exception e)
    	{
    		e.printStackTrace();
    	}
	}
	@FXML
	void onAction_btnDelete(Event ev) {
		try {
			if (this.tableviewData.getSelectionModel().getSelectedItem() != null) {
				Strasse temp = this.tableviewData.getSelectionModel().getSelectedItem();
				this.tableviewData.getSelectionModel().clearSelection();
				db.deleteStrasse(temp);
				updateTable();
			}
			else {
				System.out.println("you have to select an element");
			}
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
	@FXML
	void onAction_btnCancel(Event ev) {
    	Stage stage = (Stage) btnCancel.getScene().getWindow();
        stage.close();
	}
	
	private void updateTable() throws SQLException {
		db.loadStrasseFromDb();
		ObservableList<Strasse> tmpList = FXCollections.observableArrayList(db.getVecStrasse());
		this.tableviewData.setItems(tmpList);
	}

	@FXML
    void onAction_btnLoad(Event event) {
    	try {
			db.loadStrasseFromDb();
			ObservableList<Strasse> tmpList = FXCollections.observableArrayList(db.getVecStrasse());
			this.tableviewData.setItems(tmpList);	
    	} catch (SQLException e) {
			System.out.println("an error occured: " + e.getMessage());
			e.printStackTrace();
		}
    }

	@Override
	public void initialize(URL location, ResourceBundle resources) {

		this.cellStrasse.setCellValueFactory
    	(
    		new PropertyValueFactory<Strasse, String>("strasse")
    	);
    	this.cellPLZ.setCellValueFactory
    	(
    		new PropertyValueFactory<Strasse, Integer>("plz")
    	);
    	this.cellStrasse.setCellFactory(TextFieldTableCell.forTableColumn());
    	this.cellPLZ.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerConverter()));
	}
}
