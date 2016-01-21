package pkgController;

import java.sql.SQLException;

import application.Adresse;
import application.IntegerConverter;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.control.TableColumn.CellEditEvent;
import javafx.stage.Stage;
import pkgDatabase.Database;

public class AdressenController {
	Database db = new Database();
	@FXML
	private Button btnCreate;
	@FXML
	private Button btnDelete;
	@FXML
	private Button btnCancel;
	@FXML
	private Button btnLoad;
	@FXML
    private TableColumn<Adresse, String> cellStrasse;
	@FXML
    private TableColumn<Adresse, Integer> cellPLZ;
	@FXML
    private TableColumn<Adresse, Integer> cellHausnummer;
    @FXML
    private TableView<Adresse> tableviewData;
    private int counter = -1;

    @FXML
	void onAction_btnCreate(Event ev) {
		try
    	{
			Adresse newAdresse = new Adresse(10000,"neuer Ort", counter);
			counter --;
	    	this.tableviewData.getItems().add(newAdresse);
	    	this.tableviewData.getSelectionModel().selectLast();
	    	this.tableviewData.scrollTo(this.tableviewData.getItems().size());
	    	db.insertAdresse(newAdresse);
    	}
    	catch(Exception e)
    	{
    		e.printStackTrace();
    	}
	}
	@FXML
	void onAction_btnDelete(Event ev) {
		try {
			Adresse temp = this.tableviewData.getSelectionModel().getSelectedItem();
			this.tableviewData.getSelectionModel().clearSelection();
			db.deleteAdresse(temp);
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
    void onEdit_cellStrasse(CellEditEvent<Adresse, String> event) {
    	try
    	{
    		Adresse temp = db.getVecAdresse().get(event.getTablePosition().getRow());
    		Adresse oldAdresse = new Adresse (temp.getPlz(), temp.getStrasse(), temp.getHausnummer());
    		event.getTableView().getItems().get(event.getTablePosition().getRow()).setStrasse(event.getNewValue());
    		db.updateAdresse(oldAdresse,event.getTableView().getItems().get(event.getTablePosition().getRow()));
    	}
    	catch(Exception e)
    	{
    		e.printStackTrace();

    	}

    }

    @FXML
    void onEdit_cellPLZ(CellEditEvent<Adresse, Integer> event) {
    	try
    	{
    		Adresse temp = db.getVecAdresse().get(event.getTablePosition().getRow());
    		Adresse oldAdresse = new Adresse (temp.getPlz(), temp.getStrasse(), temp.getHausnummer());
    		event.getTableView().getItems().get(event.getTablePosition().getRow()).setPlz(event.getNewValue());
    		db.updateAdresse(oldAdresse,event.getTableView().getItems().get(event.getTablePosition().getRow()));
    	}
    	catch(Exception e)
    	{
    		System.out.println(e.getMessage());
    	}

    }

    @FXML
    void onEdit_cellHausnummer(CellEditEvent<Adresse, Integer> event) {
    	try
    	{
    		Adresse temp = db.getVecAdresse().get(event.getTablePosition().getRow());
    		Adresse oldAdresse = new Adresse (temp.getPlz(), temp.getStrasse(), temp.getHausnummer());
    		event.getTableView().getItems().get(event.getTablePosition().getRow()).setHausnummer(event.getNewValue());
    		db.updateAdresse(oldAdresse,event.getTableView().getItems().get(event.getTablePosition().getRow()));
    	}
    	catch(Exception e)
    	{
    		System.out.println(e.getMessage());
    	}

    }

    @FXML
    void onAction_btnLoad(Event event) {
    	try {
			db.loadAdressenFromDb();
			ObservableList<Adresse> tmpList = FXCollections.observableArrayList(db.getVecAdresse());

	    	this.cellStrasse.setCellValueFactory
	    	(
	    		new PropertyValueFactory<Adresse, String>("strasse")
	    	);
	    	this.cellPLZ.setCellValueFactory
	    	(
	    		new PropertyValueFactory<Adresse, Integer>("plz")
	    	);
	    	this.cellHausnummer.setCellValueFactory
	    	(
	    		new PropertyValueFactory<Adresse, Integer>("hausnummer")
	    	);

	    	this.tableviewData.setItems(tmpList);

	    	this.cellStrasse.setCellFactory(TextFieldTableCell.forTableColumn());
	    	this.cellPLZ.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerConverter()));
	    	this.cellHausnummer.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerConverter()));
		} catch (SQLException e) {
			System.out.println("an error occured: " + e.getMessage());
			e.printStackTrace();
		}
    }

    public void setDatabase(Database db) {
    	this.db = db;
    }

}