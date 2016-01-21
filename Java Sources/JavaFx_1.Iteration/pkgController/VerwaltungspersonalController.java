package pkgController;

import java.sql.SQLException;

import application.IntegerConverter;
import application.Verwaltungspersonal;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TableColumn.CellEditEvent;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.stage.Stage;
import pkgDatabase.Database;

public class VerwaltungspersonalController {
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


	private Database db = new Database();
	private int counter = -1;

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
			Verwaltungspersonal newVw = new Verwaltungspersonal(counter,"", "");
			counter --;
	    	this.tableviewData.getItems().add(newVw);
	    	this.tableviewData.getSelectionModel().selectLast();
	    	this.tableviewData.scrollTo(this.tableviewData.getItems().size());
	    	db.insertVerwaltungspersonal(newVw);
    	}
    	catch(Exception e)
    	{
    		e.printStackTrace();
    	}
	}
	@FXML
	void onAction_btnDelete(Event ev) {
		try {
			Verwaltungspersonal temp = this.tableviewData.getSelectionModel().getSelectedItem();
			this.tableviewData.getSelectionModel().clearSelection();
			db.deleteVerwaltungspersonal(temp);
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

	@FXML
    void onAction_btnLoad(Event event) {
    	try {
			db.loadVerwaltungspersonalFromDb();
			ObservableList<Verwaltungspersonal> tmpList = FXCollections.observableArrayList(db.getVecVerwaltungspersonal());

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

	    	this.tableviewData.setItems(tmpList);

	    	this.cellPersonal_ID.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerConverter()));
	    	this.cellName.setCellFactory(TextFieldTableCell.forTableColumn());
	    	this.cellAbteilung.setCellFactory(TextFieldTableCell.forTableColumn());
		} catch (SQLException e) {
			System.out.println("an error occured: " + e.getMessage());
			e.printStackTrace();
		}
    }
}
