package pkgController;

import application.Adresse;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.TableColumn.CellEditEvent;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.stage.Stage;
import pkgDatabase.Database;

public class AdressenController {
	Database db = null;
	@FXML
	private Button btnCreate;
	@FXML
	private Button btnDelete;
	@FXML
	private Button btnCancel;
	@FXML
    private TableColumn<Adresse, String> cellStrasse;
	@FXML
    private TableColumn<Adresse, Integer> cellPLZ;
	@FXML
    private TableColumn<Adresse, Integer> cellHausnummer;
    @FXML
    private TableView<Adresse> tableviewData;

	@FXML
	void onAction_btnCreate(Event ev) {

	}
	@FXML
	void onAction_btnDelete(Event ev) {

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
    		event.getTableView().getItems().get(event.getTablePosition().getRow()).setStrasse(event.getNewValue());
    	}
    	catch(Exception e)
    	{
    		System.out.println(e.getMessage());
    	}

    }

    @FXML
    void onEdit_cellPLZ(CellEditEvent<Adresse, Integer> event) {
    	try
    	{
    		event.getTableView().getItems().get(event.getTablePosition().getRow()).setPlz(event.getNewValue());
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
    		event.getTableView().getItems().get(event.getTablePosition().getRow()).setHausnummer(event.getNewValue());
    	}
    	catch(Exception e)
    	{
    		System.out.println(e.getMessage());
    	}

    }

    @FXML
    void bttn_drink_load_click(Event event) {
    	ObservableList<Adresse> tmpList = FXCollections.observableArrayList(db.getVecAdresse());

    	this.cellStrasse.setCellValueFactory
    	(
    		new PropertyValueFactory<Adresse, String>("strasse")
    	);
    	this.cellPLZ.setCellValueFactory
    	(
    		new PropertyValueFactory<Adresse, Integer>("PLZ")
    	);
    	this.cellHausnummer.setCellValueFactory
    	(
    		new PropertyValueFactory<Adresse, Integer>("Hausnummer")
    	);

    	this.tableviewData.setItems(tmpList);

    	this.cellStrasse.setCellFactory(TextFieldTableCell.forTableColumn());
    	this.cellPLZ.setCellFactory(TextFieldTableCell.forTableColumn());
    	this.cellHausnummer.setCellFactory(TextFieldTableCell.forTableColumn());
    }

    @FXML
    void bttn_drink_add_click(Event event) {
    	try
    	{
    		Adresse tmpAdresse = new Adresse(0000, "", 0);
	    	this.tableviewData.getItems().add(tmpAdresse);
	    	this.tableviewData.getSelectionModel().selectLast();
	    	this.tableviewData.scrollTo(this.tableviewData.getItems().size());
	    	db.addAdresse(tmpAdresse);
	    	System.out.println("New Empty Drink Added!!!");
    	}
    	catch(Exception e)
    	{
    		System.out.println(e.getMessage());
    	}

    }

    @FXML
    void bttn_drink_remove_click(Event event) {
    	try
    	{
	    	Adresse rem = this.tableviewData.getSelectionModel().getSelectedItem();
	    	this.tableviewData.getItems().remove(rem);
	    	db.removeAdresse(rem);
    	}
    	catch (Exception e)
    	{
    		System.out.println(e.getMessage());
    	}


    }

    public void setDatabase(Database db) {
    	this.db = db;
    }

}
