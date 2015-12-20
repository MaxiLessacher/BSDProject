package pkgController;

import java.sql.SQLException;

import application.BooleanConverter;
import application.IntegerConverter;
import application.Mitglied;
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

public class MitgliederController {
	@FXML
	private Button btnCreate;
	@FXML
	private Button btnDelete;
	@FXML
	private Button btnCancel;
	@FXML
	private Button btnLoad;
	@FXML
    private TableColumn<Mitglied, Integer> cellMitglieds_ID;
	@FXML
    private TableColumn<Mitglied, String> cellName;
	@FXML
    private TableColumn<Mitglied, Integer> cell_HH_Vorstand;
	@FXML
    private TableColumn<Mitglied, Integer> cell_HH_ID;
	@FXML
	private TableView<Mitglied> tableviewData;
	private int counter = -1;


	private Database db = new Database();

	@FXML
    void onEdit_cellMitglieds_ID(CellEditEvent<Mitglied, Integer> event) {
    	try
    	{
    		Mitglied temp = db.getVecMitglied().get(event.getTablePosition().getRow());
    		Mitglied oldMitglied = new Mitglied(temp.getMitglieds_id(), temp.getName(), temp.isHH_Vorstand(), temp.getHH_ID());
    		event.getTableView().getItems().get(event.getTablePosition().getRow()).setMitglieds_id(event.getNewValue());
    		db.updateMitglied(oldMitglied,event.getTableView().getItems().get(event.getTablePosition().getRow()));
    	}
    	catch(Exception e)
    	{
    		e.printStackTrace();
    	}
    }

	@FXML
    void onEdit_cellName(CellEditEvent<Mitglied, String> event) {
    	try
    	{
    		Mitglied temp = db.getVecMitglied().get(event.getTablePosition().getRow());
    		Mitglied oldMitglied = new Mitglied(temp.getMitglieds_id(), temp.getName(), temp.isHH_Vorstand(), temp.getHH_ID());
    		event.getTableView().getItems().get(event.getTablePosition().getRow()).setName(event.getNewValue());
    		db.updateMitglied(oldMitglied,event.getTableView().getItems().get(event.getTablePosition().getRow()));
    	}
    	catch(Exception e)
    	{
    		e.printStackTrace();
    	}
    }

	@FXML
    void onEdit_cell_HH_Vorstand(CellEditEvent<Mitglied, Boolean> event) {
    	try
    	{
    		Mitglied temp = db.getVecMitglied().get(event.getTablePosition().getRow());
    		Mitglied oldMitglied = new Mitglied(temp.getMitglieds_id(), temp.getName(), temp.isHH_Vorstand(), temp.getHH_ID());
    		event.getTableView().getItems().get(event.getTablePosition().getRow()).setHH_Vorstand(event.getNewValue());
    		db.updateMitglied(oldMitglied,event.getTableView().getItems().get(event.getTablePosition().getRow()));
    	}
    	catch(Exception e)
    	{
    		e.printStackTrace();
    	}
    }

	@FXML
    void onEdit_cell_HH_ID(CellEditEvent<Mitglied, Integer> event) {
    	try
    	{
    		Mitglied temp = db.getVecMitglied().get(event.getTablePosition().getRow());
    		Mitglied oldMitglied = new Mitglied(temp.getMitglieds_id(), temp.getName(), temp.isHH_Vorstand(), temp.getHH_ID());
    		event.getTableView().getItems().get(event.getTablePosition().getRow()).setHH_ID(event.getNewValue());
    		db.updateMitglied(oldMitglied,event.getTableView().getItems().get(event.getTablePosition().getRow()));
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
			Mitglied newMitglied = new Mitglied(-1, "noname", false, counter);
			counter--;
	    	this.tableviewData.getItems().add(newMitglied);
	    	this.tableviewData.getSelectionModel().selectLast();
	    	this.tableviewData.scrollTo(this.tableviewData.getItems().size());
	    	db.insertMitglied(newMitglied);
    	}
    	catch(Exception e)
    	{
    		e.printStackTrace();
    	}
	}
	@FXML
	void onAction_btnDelete(Event ev) {
		try {
			Mitglied temp = this.tableviewData.getSelectionModel().getSelectedItem();
			this.tableviewData.getSelectionModel().clearSelection();
			db.deleteMitglied(temp);
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
			db.loadMitgliederFromDb();
			ObservableList<Mitglied> tmpList = FXCollections.observableArrayList(db.getVecMitglied());

			this.cellMitglieds_ID.setCellValueFactory
	    	(
	    		new PropertyValueFactory<Mitglied, Integer>("mitglieds_id")
	    	);
	    	this.cellName.setCellValueFactory
	    	(
	    		new PropertyValueFactory<Mitglied, String>("name")
	    	);
	    	this.cell_HH_Vorstand.setCellValueFactory
	    	(
	    		new PropertyValueFactory<Mitglied, Integer>("isHH_Vorstand")
	    	);
	    	this.cell_HH_ID.setCellValueFactory
	    	(
	    		new PropertyValueFactory<Mitglied, Integer>("HH_ID")
	    	);

	    	this.tableviewData.setItems(tmpList);

	    	this.cellMitglieds_ID.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerConverter()));
	    	this.cellName.setCellFactory(TextFieldTableCell.forTableColumn());
	    	this.cell_HH_Vorstand.setCellFactory(TextFieldTableCell.forTableColumn(new BooleanConverter()));
	    	this.cell_HH_ID.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerConverter()));
		} catch (SQLException e) {
			System.out.println("an error occured: " + e.getMessage());
			e.printStackTrace();
		}
    }
}
