package pkgController;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Date;

import application.DateConverter;
import application.IntegerConverter;
import application.Wasserstandsmeldung;
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

public class WasserstandsmeldungsController {
	@FXML
	private Button btnCreate;
	@FXML
	private Button btnDelete;
	@FXML
	private Button btnCancel;
	@FXML
	private Button btnLoad;
	@FXML
    private TableColumn<Wasserstandsmeldung, Date> cellDatum;
	@FXML
    private TableColumn<Wasserstandsmeldung, Integer> cellZaehlerNr;
	@FXML
    private TableColumn<Wasserstandsmeldung, Integer> cell_neuZaehlerstand;
	@FXML
	private TableView<Wasserstandsmeldung> tableviewData;


	private Database db = new Database();
	private int counter = -1;

	@FXML
    void onEdit_cellDatum(CellEditEvent<Wasserstandsmeldung, LocalDate> event) {
    	try
    	{
    		Wasserstandsmeldung temp = db.getVecWasserstandsmeldung().get(event.getTablePosition().getRow());
    		Wasserstandsmeldung oldWasserstandsmeldung = new Wasserstandsmeldung(temp.getDate(), temp.getZaehler_nr(), temp.getNeuZaehlerstand());
    		event.getTableView().getItems().get(event.getTablePosition().getRow()).setDate(event.getNewValue());
    		db.updateWasserstandsmeldung(oldWasserstandsmeldung,event.getTableView().getItems().get(event.getTablePosition().getRow()));
    	}
    	catch(Exception e)
    	{
    		e.printStackTrace();
    	}
    }

	@FXML
    void onEdit_cellZaehlerNr(CellEditEvent<Wasserstandsmeldung, Integer> event) {
    	try
    	{
    		Wasserstandsmeldung temp = db.getVecWasserstandsmeldung().get(event.getTablePosition().getRow());
    		Wasserstandsmeldung oldWasserstandsmeldung = new Wasserstandsmeldung(temp.getDate(), temp.getZaehler_nr(), temp.getNeuZaehlerstand());
    		event.getTableView().getItems().get(event.getTablePosition().getRow()).setZaehler_nr(event.getNewValue());
    		db.updateWasserstandsmeldung(oldWasserstandsmeldung,event.getTableView().getItems().get(event.getTablePosition().getRow()));
    	}
    	catch(Exception e)
    	{
    		e.printStackTrace();
    	}
    }

	@FXML
    void onEdit_cell_neuZaehlerstand(CellEditEvent<Wasserstandsmeldung, Integer> event) {
    	try
    	{
    		Wasserstandsmeldung temp = db.getVecWasserstandsmeldung().get(event.getTablePosition().getRow());
    		Wasserstandsmeldung oldWasserstandsmeldung = new Wasserstandsmeldung(temp.getDate(), temp.getZaehler_nr(), temp.getNeuZaehlerstand());
    		event.getTableView().getItems().get(event.getTablePosition().getRow()).setNeuZaehlerstand(event.getNewValue());
    		db.updateWasserstandsmeldung(oldWasserstandsmeldung,event.getTableView().getItems().get(event.getTablePosition().getRow()));
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
			Wasserstandsmeldung newWasserstandsmeldung = new Wasserstandsmeldung(LocalDate.now(), counter, 0);
			counter --;
	    	this.tableviewData.getItems().add(newWasserstandsmeldung);
	    	this.tableviewData.getSelectionModel().selectLast();
	    	this.tableviewData.scrollTo(this.tableviewData.getItems().size());
	    	db.insertWasserstandsmeldung(newWasserstandsmeldung);
    	}
    	catch(Exception e)
    	{
    		e.printStackTrace();
    	}
	}
	@FXML
	void onAction_btnDelete(Event ev) {
		try {
			Wasserstandsmeldung temp = this.tableviewData.getSelectionModel().getSelectedItem();
			this.tableviewData.getSelectionModel().clearSelection();
			db.deleteWasserstandsmeldung(temp);
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
			db.loadWasserstandsmeldungFromDb();
			ObservableList<Wasserstandsmeldung> tmpList = FXCollections.observableArrayList(db.getVecWasserstandsmeldung());

			this.cellDatum.setCellValueFactory
	    	(
	    		new PropertyValueFactory<Wasserstandsmeldung, Date>("datum")
	    	);
			this.cellZaehlerNr.setCellValueFactory
	    	(
	    		new PropertyValueFactory<Wasserstandsmeldung, Integer>("zaehlerNr")
	    	);
			this.cell_neuZaehlerstand.setCellValueFactory
	    	(
	    		new PropertyValueFactory<Wasserstandsmeldung, Integer>("neuZaehlerstand")
	    	);

	    	this.tableviewData.setItems(tmpList);

	    	this.cellDatum.setCellFactory(TextFieldTableCell.forTableColumn(new DateConverter()));
	    	this.cellZaehlerNr.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerConverter()));
	    	this.cell_neuZaehlerstand.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerConverter()));
		} catch (SQLException e) {
			System.out.println("an error occured: " + e.getMessage());
			e.printStackTrace();
		}
    }
}
