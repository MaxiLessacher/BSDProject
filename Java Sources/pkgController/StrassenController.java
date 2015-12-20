package pkgController;

import java.sql.SQLException;

import application.IntegerConverter;
import application.Strasse;
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

public class StrassenController {
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
	private int counter = -1;


	private Database db = new Database();

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
			String street = "nonamestreet" + counter;
			Strasse newStrasse = new Strasse(street, 10000);
			counter--;
	    	this.tableviewData.getItems().add(newStrasse);
	    	this.tableviewData.getSelectionModel().selectLast();
	    	this.tableviewData.scrollTo(this.tableviewData.getItems().size());
	    	db.insertStrasse(newStrasse);
    	}
    	catch(Exception e)
    	{
    		e.printStackTrace();
    	}
	}
	@FXML
	void onAction_btnDelete(Event ev) {
		try {
			Strasse temp = this.tableviewData.getSelectionModel().getSelectedItem();
			this.tableviewData.getSelectionModel().clearSelection();
			db.deleteStrasse(temp);
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
			db.loadStrasseFromDb();
			ObservableList<Strasse> tmpList = FXCollections.observableArrayList(db.getVecStrasse());

			this.cellStrasse.setCellValueFactory
	    	(
	    		new PropertyValueFactory<Strasse, String>("strasse")
	    	);
	    	this.cellPLZ.setCellValueFactory
	    	(
	    		new PropertyValueFactory<Strasse, Integer>("plz")
	    	);

	    	this.tableviewData.setItems(tmpList);

	    	this.cellStrasse.setCellFactory(TextFieldTableCell.forTableColumn());
	    	this.cellPLZ.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerConverter()));
		} catch (SQLException e) {
			System.out.println("an error occured: " + e.getMessage());
			e.printStackTrace();
		}
    }
}
