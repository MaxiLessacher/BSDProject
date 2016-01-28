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
import pkgClasses.Verwaltungspersonal;
import pkgConverter.IntegerConverter;
import pkgDatabase.Database;

public class VerwaltungspersonalController implements Initializable {
	@FXML
	private Button btnCreate;
	@FXML
	private Button btnDelete;
	@FXML
	private Button btnCancel;
	@FXML
	private Button btnLoad;
	@FXML
    private TableColumn<Verwaltungspersonal, Integer> cellPersonal_ID;
	@FXML
    private TableColumn<Verwaltungspersonal, String> cellName;
	@FXML
    private TableColumn<Verwaltungspersonal, String> cellAbteilung;
	@FXML
	private TableView<Verwaltungspersonal> tableviewData;


	private Database db = Database.getInstance();

	@FXML
    void onEdit_cellPersonal_ID(CellEditEvent<Verwaltungspersonal, Integer> event) {
    	try
    	{
    		Verwaltungspersonal temp = db.getVecVerwaltungspersonal().get(event.getTablePosition().getRow());
    		Verwaltungspersonal oldVerwaltungspersonal = new Verwaltungspersonal(temp.getPersonal_id(), temp.getName(), temp.getAbteilung());
    		event.getTableView().getItems().get(event.getTablePosition().getRow()).setPersonal_id(event.getNewValue());
    		db.updateVerwaltungspersonal(oldVerwaltungspersonal,event.getTableView().getItems().get(event.getTablePosition().getRow()));
    	}
    	catch(Exception e)
    	{
    		e.printStackTrace();
    	}
    }

	@FXML
    void onEdit_cellName(CellEditEvent<Verwaltungspersonal, String> event) {
    	try
    	{
    		Verwaltungspersonal temp = db.getVecVerwaltungspersonal().get(event.getTablePosition().getRow());
    		Verwaltungspersonal oldVerwaltungspersonal = new Verwaltungspersonal(temp.getPersonal_id(), temp.getName(), temp.getAbteilung());
    		event.getTableView().getItems().get(event.getTablePosition().getRow()).setName(event.getNewValue());
    		db.updateVerwaltungspersonal(oldVerwaltungspersonal,event.getTableView().getItems().get(event.getTablePosition().getRow()));
    	}
    	catch(Exception e)
    	{
    		e.printStackTrace();
    	}
    }

	@FXML
    void onEdit_cellAbteilung(CellEditEvent<Verwaltungspersonal, String> event) {
    	try
    	{
    		Verwaltungspersonal temp = db.getVecVerwaltungspersonal().get(event.getTablePosition().getRow());
    		Verwaltungspersonal oldVerwaltungspersonal = new Verwaltungspersonal(temp.getPersonal_id(), temp.getName(), temp.getAbteilung());
    		event.getTableView().getItems().get(event.getTablePosition().getRow()).setAbteilung(event.getNewValue());
    		db.updateVerwaltungspersonal(oldVerwaltungspersonal,event.getTableView().getItems().get(event.getTablePosition().getRow()));
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
    		Parent rootframe = FXMLLoader.load(getClass().getResource("/resources/addVerwaltungspersonal.fxml"));
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
				Verwaltungspersonal temp = this.tableviewData.getSelectionModel().getSelectedItem();
				this.tableviewData.getSelectionModel().clearSelection();
				db.deleteVerwaltungspersonal(temp);
				this.updateTable();
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
		db.loadVerwaltungspersonalFromDb();
		ObservableList<Verwaltungspersonal> tmpList = FXCollections.observableArrayList(db.getVecVerwaltungspersonal());
		this.tableviewData.setItems(tmpList);
	}

	@FXML
    void onAction_btnLoad(Event event) {
    	try {
			db.loadVerwaltungspersonalFromDb();
			ObservableList<Verwaltungspersonal> tmpList = FXCollections.observableArrayList(db.getVecVerwaltungspersonal());
	    	this.tableviewData.setItems(tmpList);
			
		} catch (SQLException e) {
			System.out.println("an error occured: " + e.getMessage());
			e.printStackTrace();
		}
    }

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		this.cellPersonal_ID.setCellValueFactory
    	(
    		new PropertyValueFactory<Verwaltungspersonal, Integer>("personal_id")
    	);
		this.cellName.setCellValueFactory
    	(
    		new PropertyValueFactory<Verwaltungspersonal, String>("name")
    	);
		this.cellAbteilung.setCellValueFactory
    	(
    		new PropertyValueFactory<Verwaltungspersonal, String>("abteilung")
    	);
    	this.cellPersonal_ID.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerConverter()));
    	this.cellName.setCellFactory(TextFieldTableCell.forTableColumn());
    	this.cellAbteilung.setCellFactory(TextFieldTableCell.forTableColumn());
	}
}
