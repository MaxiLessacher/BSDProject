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
import pkgClasses.Mitglied;
import pkgConverter.BooleanConverter;
import pkgConverter.IntegerConverter;
import pkgDatabase.Database;

public class MitgliederController implements Initializable {
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
    private TableColumn<Mitglied, Boolean> cell_HH_Vorstand;
	@FXML
    private TableColumn<Mitglied, Integer> cell_HH_ID;
	@FXML
	private TableView<Mitglied> tableviewData;


	private Database db = Database.getInstance();

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
			Stage stage = new Stage();
    		Parent rootframe = FXMLLoader.load(getClass().getResource("/resources/addMitglied.fxml"));
    		Scene scene = new Scene(rootframe);

    		stage.setScene(scene);
    		stage.setTitle("Insert Mitglied");
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
				Mitglied temp = this.tableviewData.getSelectionModel().getSelectedItem();
				this.tableviewData.getSelectionModel().clearSelection();
				db.deleteMitglied(temp);
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
		db.loadMitgliederFromDb();
		ObservableList<Mitglied> tmpList = FXCollections.observableArrayList(db.getVecMitglied());
		this.tableviewData.setItems(tmpList);
	}

	@FXML
    void onAction_btnLoad(Event event) {
    	try {
			db.loadMitgliederFromDb();
			ObservableList<Mitglied> tmpList = FXCollections.observableArrayList(db.getVecMitglied());
	    	this.tableviewData.setItems(tmpList);

		} catch (SQLException e) {
			System.out.println("an error occured: " + e.getMessage());
			e.printStackTrace();
		}
    }

	@Override
	public void initialize(URL location, ResourceBundle resources) {
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
    		new PropertyValueFactory<Mitglied, Boolean>("HH_Vorstand")
    	);
    	this.cell_HH_ID.setCellValueFactory
    	(
    		new PropertyValueFactory<Mitglied, Integer>("HH_ID")
    	);
    	this.cellMitglieds_ID.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerConverter()));
    	this.cellName.setCellFactory(TextFieldTableCell.forTableColumn());
    	this.cell_HH_Vorstand.setCellFactory(TextFieldTableCell.forTableColumn(new BooleanConverter()));
    	this.cell_HH_ID.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerConverter()));
	}
}
