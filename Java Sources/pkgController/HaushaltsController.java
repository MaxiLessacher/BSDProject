package pkgController;

import java.sql.SQLException;

import application.BooleanConverter;
import application.Haushalt;
import application.IntegerConverter;
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

public class HaushaltsController {
	@FXML
	private Button btnCreate;
	@FXML
	private Button btnDelete;
	@FXML
	private Button btnCancel;
	@FXML
	private Button btnLoad;

	private Database db = new Database();
	@FXML
	private TableView<Haushalt> tableviewData;
	@FXML
    private TableColumn<Haushalt, String> cellStrasse;
	@FXML
    private TableColumn<Haushalt, Integer> cellPLZ;
	@FXML
    private TableColumn<Haushalt, Integer> cellHausnummer;
	@FXML
    private TableColumn<Haushalt, Integer> cell_HH_ID;
	@FXML
    private TableColumn<Haushalt, Integer> cellTuerNr;
	@FXML
    private TableColumn<Haushalt, Integer> cellWohnflaeche;
	@FXML
    private TableColumn<Haushalt, Integer> cellLandwirtschaft;
	@FXML
    private TableColumn<Haushalt, Integer> cellGarten;
	private int counter = -1;

	@FXML
    void onEdit_cellStrasse(CellEditEvent<Haushalt, String> event) {
    	try
    	{
    		Haushalt temp = db.getVecHaushalt().get(event.getTablePosition().getRow());
    		Haushalt oldHaushalt = new Haushalt(temp.getHH_ID(), temp.getStrasse(), temp.getPlz(), temp.getHausnummer(), temp.getTuernummer(), temp.getWohnflaeche(), temp.isLandwirtschaft(), temp.isGarten());
    		event.getTableView().getItems().get(event.getTablePosition().getRow()).setStrasse(event.getNewValue());
    		db.updateHaushalt(oldHaushalt,event.getTableView().getItems().get(event.getTablePosition().getRow()));
    	}
    	catch(Exception e)
    	{
    		e.printStackTrace();

    	}

    }

	@FXML
    void onEdit_cellPLZ(CellEditEvent<Haushalt, Integer> event) {
    	try
    	{
    		Haushalt temp = db.getVecHaushalt().get(event.getTablePosition().getRow());
    		Haushalt oldHaushalt = new Haushalt(temp.getHH_ID(), temp.getStrasse(), temp.getPlz(), temp.getHausnummer(), temp.getTuernummer(), temp.getWohnflaeche(), temp.isLandwirtschaft(), temp.isGarten());
    		event.getTableView().getItems().get(event.getTablePosition().getRow()).setPlz(event.getNewValue());
    		db.updateHaushalt(oldHaushalt,event.getTableView().getItems().get(event.getTablePosition().getRow()));
    	}
    	catch(Exception e)
    	{
    		e.printStackTrace();

    	}

    }

	@FXML
    void onEdit_cellHausnummer(CellEditEvent<Haushalt, Integer> event) {
    	try
    	{
    		Haushalt temp = db.getVecHaushalt().get(event.getTablePosition().getRow());
    		Haushalt oldHaushalt = new Haushalt(temp.getHH_ID(), temp.getStrasse(), temp.getPlz(), temp.getHausnummer(), temp.getTuernummer(), temp.getWohnflaeche(), temp.isLandwirtschaft(), temp.isGarten());
    		event.getTableView().getItems().get(event.getTablePosition().getRow()).setHausnummer(event.getNewValue());
    		db.updateHaushalt(oldHaushalt,event.getTableView().getItems().get(event.getTablePosition().getRow()));
    	}
    	catch(Exception e)
    	{
    		e.printStackTrace();

    	}

    }

	@FXML
    void onEdit_cellTuerNr(CellEditEvent<Haushalt, Integer> event) {
    	try
    	{
    		Haushalt temp = db.getVecHaushalt().get(event.getTablePosition().getRow());
    		Haushalt oldHaushalt = new Haushalt(temp.getHH_ID(), temp.getStrasse(), temp.getPlz(), temp.getHausnummer(), temp.getTuernummer(), temp.getWohnflaeche(), temp.isLandwirtschaft(), temp.isGarten());
    		event.getTableView().getItems().get(event.getTablePosition().getRow()).setTuernummer(event.getNewValue());
    		db.updateHaushalt(oldHaushalt,event.getTableView().getItems().get(event.getTablePosition().getRow()));
    	}
    	catch(Exception e)
    	{
    		e.printStackTrace();

    	}

    }

	@FXML
    void onEdit_cell_HH_ID(CellEditEvent<Haushalt, Integer> event) {
    	try
    	{
    		Haushalt temp = db.getVecHaushalt().get(event.getTablePosition().getRow());
    		Haushalt oldHaushalt = new Haushalt(temp.getHH_ID(), temp.getStrasse(), temp.getPlz(), temp.getHausnummer(), temp.getTuernummer(), temp.getWohnflaeche(), temp.isLandwirtschaft(), temp.isGarten());
    		event.getTableView().getItems().get(event.getTablePosition().getRow()).setHH_ID(event.getNewValue());
    		db.updateHaushalt(oldHaushalt,event.getTableView().getItems().get(event.getTablePosition().getRow()));
    	}
    	catch(Exception e)
    	{
    		e.printStackTrace();

    	}

    }

	@FXML
    void onEdit_cellWohnflaeche(CellEditEvent<Haushalt, Integer> event) {
    	try
    	{
    		Haushalt temp = db.getVecHaushalt().get(event.getTablePosition().getRow());
    		Haushalt oldHaushalt = new Haushalt(temp.getHH_ID(), temp.getStrasse(), temp.getPlz(), temp.getHausnummer(), temp.getTuernummer(), temp.getWohnflaeche(), temp.isLandwirtschaft(), temp.isGarten());
    		event.getTableView().getItems().get(event.getTablePosition().getRow()).setWohnflaeche(event.getNewValue());
    		db.updateHaushalt(oldHaushalt,event.getTableView().getItems().get(event.getTablePosition().getRow()));
    	}
    	catch(Exception e)
    	{
    		e.printStackTrace();

    	}

    }

	@FXML
    void onEdit_cellGarten(CellEditEvent<Haushalt, Boolean> event) {
    	try
    	{
    		Haushalt temp = db.getVecHaushalt().get(event.getTablePosition().getRow());
    		Haushalt oldHaushalt = new Haushalt(temp.getHH_ID(), temp.getStrasse(), temp.getPlz(), temp.getHausnummer(), temp.getTuernummer(), temp.getWohnflaeche(), temp.isLandwirtschaft(), temp.isGarten());
    		event.getTableView().getItems().get(event.getTablePosition().getRow()).setGarten(event.getNewValue());
    		db.updateHaushalt(oldHaushalt,event.getTableView().getItems().get(event.getTablePosition().getRow()));
    	}
    	catch(Exception e)
    	{
    		e.printStackTrace();

    	}

    }

	@FXML
    void onEdit_cellLandwirtschaft(CellEditEvent<Haushalt, Boolean> event) {
    	try
    	{
    		Haushalt temp = db.getVecHaushalt().get(event.getTablePosition().getRow());
    		Haushalt oldHaushalt = new Haushalt(temp.getHH_ID(), temp.getStrasse(), temp.getPlz(), temp.getHausnummer(), temp.getTuernummer(), temp.getWohnflaeche(), temp.isLandwirtschaft(), temp.isGarten());
    		event.getTableView().getItems().get(event.getTablePosition().getRow()).setLandwirtschaft(event.getNewValue());
    		db.updateHaushalt(oldHaushalt,event.getTableView().getItems().get(event.getTablePosition().getRow()));
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
			Haushalt newHaushalt = new Haushalt(counter, "street", 99999, 0, 0, 0, false, false);
			counter--;
	    	this.tableviewData.getItems().add(newHaushalt);
	    	this.tableviewData.getSelectionModel().selectLast();
	    	this.tableviewData.scrollTo(this.tableviewData.getItems().size());
	    	db.insertHaushalt(newHaushalt);
    	}
    	catch(Exception e)
    	{
    		e.printStackTrace();
    	}
	}
	@FXML
	void onAction_btnDelete(Event ev) {
		try {
			Haushalt temp = this.tableviewData.getSelectionModel().getSelectedItem();
			this.tableviewData.getSelectionModel().clearSelection();
			db.deleteHaushalt(temp);
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
			db.loadHaushalteFromDb();
			ObservableList<Haushalt> tmpList = FXCollections.observableArrayList(db.getVecHaushalt());

			this.cellStrasse.setCellValueFactory
	    	(
	    		new PropertyValueFactory<Haushalt, String>("strasse")
	    	);
	    	this.cellPLZ.setCellValueFactory
	    	(
	    		new PropertyValueFactory<Haushalt, Integer>("plz")
	    	);
	    	this.cellHausnummer.setCellValueFactory
	    	(
	    		new PropertyValueFactory<Haushalt, Integer>("hausnummer")
	    	);
	    	this.cell_HH_ID.setCellValueFactory
	    	(
	    		new PropertyValueFactory<Haushalt, Integer>("HH_ID")
	    	);
	    	this.cellTuerNr.setCellValueFactory
	    	(
	    		new PropertyValueFactory<Haushalt, Integer>("tuernummer")
	    	);
	    	this.cellWohnflaeche.setCellValueFactory
	    	(
	    		new PropertyValueFactory<Haushalt, Integer>("wohnflaeche")
	    	);
	    	this.cellLandwirtschaft.setCellValueFactory
	    	(
	    		new PropertyValueFactory<Haushalt, Integer>("isLandwirtschaft")
	    	);
	    	this.cellGarten.setCellValueFactory
	    	(
	    		new PropertyValueFactory<Haushalt, Integer>("isGarten")
	    	);

	    	this.tableviewData.setItems(tmpList);

	    	this.cellStrasse.setCellFactory(TextFieldTableCell.forTableColumn());
	    	this.cellPLZ.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerConverter()));
	    	this.cellHausnummer.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerConverter()));
	    	this.cell_HH_ID.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerConverter()));
	    	this.cellTuerNr.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerConverter()));
	    	this.cellWohnflaeche.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerConverter()));
	    	this.cellLandwirtschaft.setCellFactory(TextFieldTableCell.forTableColumn(new BooleanConverter()));
	    	this.cellGarten.setCellFactory(TextFieldTableCell.forTableColumn(new BooleanConverter()));
		} catch (SQLException e) {
			System.out.println("an error occured: " + e.getMessage());
			e.printStackTrace();
		}
    }
}
