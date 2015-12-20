package pkgController;

import java.sql.SQLException;

import application.IntegerConverter;
import application.Ort;
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

public class OrtsController {
	@FXML
	private Button btnCreate;
	@FXML
	private Button btnDelete;
	@FXML
	private Button btnCancel;
	@FXML
	private Button btnLoad;
	@FXML
    private TableColumn<Ort, String> cellOrt;
	@FXML
    private TableColumn<Ort, Integer> cellPLZ;
	@FXML
	private TableView<Ort> tableviewData;
	private int counter=-1;


	private Database db = new Database();

	@FXML
	void onAction_btnCreate(Event ev) {
		try
    	{
			Ort newOrt = new Ort(counter,"neuer Ort");
			counter--;
	    	this.tableviewData.getItems().add(newOrt);
	    	this.tableviewData.getSelectionModel().selectLast();
	    	this.tableviewData.scrollTo(this.tableviewData.getItems().size());
	    	db.insertOrt(newOrt);
    	}
    	catch(Exception e)
    	{
    		e.printStackTrace();
    	}
	}
	@FXML
	void onAction_btnDelete(Event ev) {
		try {
			Ort temp = this.tableviewData.getSelectionModel().getSelectedItem();
			this.tableviewData.getSelectionModel().clearSelection();
			db.deleteOrt(temp);
			this.updateTable();
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

	private void updateTable() {
		ObservableList<Ort> tmpList = FXCollections.observableArrayList(db.getVecOrt());
		this.tableviewData.setItems(tmpList);

	}

	@FXML
    void onAction_btnLoad(Event event) {
    	try {
			db.loadOrtFromDb();
			ObservableList<Ort> tmpList = FXCollections.observableArrayList(db.getVecOrt());

			this.cellOrt.setCellValueFactory
	    	(
	    		new PropertyValueFactory<Ort, String>("ort")
	    	);
	    	this.cellPLZ.setCellValueFactory
	    	(
	    		new PropertyValueFactory<Ort, Integer>("plz")
	    	);

	    	this.tableviewData.setItems(tmpList);

	    	this.cellOrt.setCellFactory(TextFieldTableCell.forTableColumn());
	    	this.cellPLZ.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerConverter()));
		} catch (SQLException e) {
			System.out.println("an error occured: " + e.getMessage());
			e.printStackTrace();
		}
    }

	@FXML
    void onEdit_cellOrt(CellEditEvent<Ort, String> event) {
    	try
    	{
    		Ort temp =db.getVecOrt().get(event.getTablePosition().getRow());
    		Ort oldOrt = new Ort (temp.getPlz(), temp.getOrt());
    		event.getTableView().getItems().get(event.getTablePosition().getRow()).setOrt(event.getNewValue());
    		db.updateOrt(oldOrt,event.getTableView().getItems().get(event.getTablePosition().getRow()));
    	}
    	catch(Exception e)
    	{
    		e.printStackTrace();

    	}

    }

    @FXML
    void onEdit_cellPLZ(CellEditEvent<Ort, Integer> event) {

    	try
    	{
    		Ort temp =db.getVecOrt().get(event.getTablePosition().getRow());
    		Ort oldOrt = new Ort (temp.getPlz(), temp.getOrt());
    		event.getTableView().getItems().get(event.getTablePosition().getRow()).setPlz(event.getNewValue());
    		db.updateOrt(oldOrt, event.getTableView().getItems().get(event.getTablePosition().getRow()));
    	}
    	catch(Exception e)
    	{
    		e.printStackTrace();

    	}

    }
}
