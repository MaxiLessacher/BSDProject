package pkgController;

import java.sql.SQLException;

import application.BooleanConverter;
import application.IntegerConverter;
import application.Wasserzaehler;
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

public class WasserzaehlerController {
	@FXML
	private Button btnCreate;
	@FXML
	private Button btnDelete;
	@FXML
	private Button btnCancel;
	@FXML
	private Button btnLoad;
	@FXML
    private TableColumn<Wasserzaehler, Integer> cell_HH_ID;
	@FXML
    private TableColumn<Wasserzaehler, Integer> cellZaehlerNr;
	@FXML
    private TableColumn<Wasserzaehler, Integer> cellStandort_X;
	@FXML
    private TableColumn<Wasserzaehler, Integer> cellStandort_Y;
	@FXML
    private TableColumn<Wasserzaehler, Integer> cellHauptzaehler;
	@FXML
    private TableColumn<Wasserzaehler, Integer> cellZaehlerstand;
	@FXML
	private TableView<Wasserzaehler> tableviewData;

	private int counter = -1;

	private Database db = new Database();

	@FXML
    void onEdit_cell_HH_ID(CellEditEvent<Wasserzaehler, Integer> event) {
    	try
    	{
    		Wasserzaehler temp = db.getVecWasserzaehler().get(event.getTablePosition().getRow());
    		Wasserzaehler oldWasserzaehler = new Wasserzaehler(temp.getZaehler_nr(), temp.getHH_ID(), temp.getZaehlerstand(), temp.isHauptzaehler(), temp.getStandort_x(), temp.getStandort_y());
    		event.getTableView().getItems().get(event.getTablePosition().getRow()).setZaehler_nr(event.getNewValue());
    		db.updateWasserzaehler(oldWasserzaehler,event.getTableView().getItems().get(event.getTablePosition().getRow()));
    	}
    	catch(Exception e)
    	{
    		e.printStackTrace();
    	}
    }

	@FXML
    void onEdit_cellZaehlerNr(CellEditEvent<Wasserzaehler, Integer> event) {
    	try
    	{
    		Wasserzaehler temp = db.getVecWasserzaehler().get(event.getTablePosition().getRow());
    		Wasserzaehler oldWasserzaehler = new Wasserzaehler(temp.getZaehler_nr(), temp.getHH_ID(), temp.getZaehlerstand(), temp.isHauptzaehler(), temp.getStandort_x(), temp.getStandort_y());
    		event.getTableView().getItems().get(event.getTablePosition().getRow()).setHH_ID(event.getNewValue());
    		db.updateWasserzaehler(oldWasserzaehler,event.getTableView().getItems().get(event.getTablePosition().getRow()));
    	}
    	catch(Exception e)
    	{
    		e.printStackTrace();
    	}
    }

	@FXML
    void onEdit_cellZaehlerstand(CellEditEvent<Wasserzaehler, Integer> event) {
    	try
    	{
    		Wasserzaehler temp = db.getVecWasserzaehler().get(event.getTablePosition().getRow());
    		Wasserzaehler oldWasserzaehler = new Wasserzaehler(temp.getZaehler_nr(), temp.getHH_ID(), temp.getZaehlerstand(), temp.isHauptzaehler(), temp.getStandort_x(), temp.getStandort_y());
    		event.getTableView().getItems().get(event.getTablePosition().getRow()).setZaehlerstand(event.getNewValue());
    		db.updateWasserzaehler(oldWasserzaehler,event.getTableView().getItems().get(event.getTablePosition().getRow()));
    	}
    	catch(Exception e)
    	{
    		e.printStackTrace();
    	}
    }

	@FXML
    void onEdit_cellHauptzaehler(CellEditEvent<Wasserzaehler, Boolean> event) {
    	try
    	{
    		Wasserzaehler temp = db.getVecWasserzaehler().get(event.getTablePosition().getRow());
    		Wasserzaehler oldWasserzaehler = new Wasserzaehler(temp.getZaehler_nr(), temp.getHH_ID(), temp.getZaehlerstand(), temp.isHauptzaehler(), temp.getStandort_x(), temp.getStandort_y());
    		event.getTableView().getItems().get(event.getTablePosition().getRow()).setHauptzaehler(event.getNewValue());
    		db.updateWasserzaehler(oldWasserzaehler,event.getTableView().getItems().get(event.getTablePosition().getRow()));
    	}
    	catch(Exception e)
    	{
    		e.printStackTrace();
    	}
    }

	@FXML
    void onEdit_cellStandort_X(CellEditEvent<Wasserzaehler, Integer> event) {
    	try
    	{
    		Wasserzaehler temp = db.getVecWasserzaehler().get(event.getTablePosition().getRow());
    		Wasserzaehler oldWasserzaehler = new Wasserzaehler(temp.getZaehler_nr(), temp.getHH_ID(), temp.getZaehlerstand(), temp.isHauptzaehler(), temp.getStandort_x(), temp.getStandort_y());
    		event.getTableView().getItems().get(event.getTablePosition().getRow()).setStandort_x(event.getNewValue());
    		db.updateWasserzaehler(oldWasserzaehler,event.getTableView().getItems().get(event.getTablePosition().getRow()));
    	}
    	catch(Exception e)
    	{
    		e.printStackTrace();
    	}
    }

	@FXML
    void onEdit_cellStandort_Y(CellEditEvent<Wasserzaehler, Integer> event) {
    	try
    	{
    		Wasserzaehler temp = db.getVecWasserzaehler().get(event.getTablePosition().getRow());
    		Wasserzaehler oldWasserzaehler = new Wasserzaehler(temp.getZaehler_nr(), temp.getHH_ID(), temp.getZaehlerstand(), temp.isHauptzaehler(), temp.getStandort_x(), temp.getStandort_y());
    		event.getTableView().getItems().get(event.getTablePosition().getRow()).setStandort_y(event.getNewValue());
    		db.updateWasserzaehler(oldWasserzaehler,event.getTableView().getItems().get(event.getTablePosition().getRow()));
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
			Wasserzaehler newWasserzaehler = new Wasserzaehler(counter, 1, 0, false, 0, 0);
			counter --;
	    	this.tableviewData.getItems().add(newWasserzaehler);
	    	this.tableviewData.getSelectionModel().selectLast();
	    	this.tableviewData.scrollTo(this.tableviewData.getItems().size());
	    	db.insertWasserzaehler(newWasserzaehler);
    	}
    	catch(Exception e)
    	{
    		e.printStackTrace();
    	}
	}
	@FXML
	void onAction_btnDelete(Event ev) {
		try {
			Wasserzaehler temp = this.tableviewData.getSelectionModel().getSelectedItem();
			this.tableviewData.getSelectionModel().clearSelection();
			db.deleteWasserzaehler(temp);
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
			db.loadWasserzaehlerFromDb();
			ObservableList<Wasserzaehler> tmpList = FXCollections.observableArrayList(db.getVecWasserzaehler());

			this.cellZaehlerNr.setCellValueFactory
	    	(
	    		new PropertyValueFactory<Wasserzaehler, Integer>("zaehlerNr")
	    	);
			this.cell_HH_ID.setCellValueFactory
	    	(
	    		new PropertyValueFactory<Wasserzaehler, Integer>("hh_id")
	    	);
			this.cellStandort_X.setCellValueFactory
	    	(
	    		new PropertyValueFactory<Wasserzaehler, Integer>("standort_x")
	    	);
			this.cellStandort_Y.setCellValueFactory
	    	(
	    		new PropertyValueFactory<Wasserzaehler, Integer>("standort_y")
	    	);
			this.cellHauptzaehler.setCellValueFactory
	    	(
	    		new PropertyValueFactory<Wasserzaehler, Integer>("isHauptzaehler")
	    	);
			this.cellZaehlerstand.setCellValueFactory
	    	(
	    		new PropertyValueFactory<Wasserzaehler, Integer>("zaehlerstand")
	    	);


	    	this.tableviewData.setItems(tmpList);

	    	this.cellZaehlerNr.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerConverter()));
	    	this.cellHauptzaehler.setCellFactory(TextFieldTableCell.forTableColumn(new BooleanConverter()));
	    	this.cellStandort_X.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerConverter()));
	    	this.cellStandort_Y.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerConverter()));
	    	this.cell_HH_ID.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerConverter()));
	    	this.cellZaehlerstand.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerConverter()));
		} catch (SQLException e) {
			System.out.println("an error occured: " + e.getMessage());
			e.printStackTrace();
		}
    }
}
