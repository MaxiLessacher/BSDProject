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
import pkgClasses.Haushalt;
import pkgConverter.BooleanConverter;
import pkgConverter.IntegerConverter;
import pkgDatabase.Database;

public class HaushaltsController implements Initializable {
	@FXML
	private Button btnCreate;
	@FXML
	private Button btnDelete;
	@FXML
	private Button btnCancel;
	@FXML
	private Button btnLoad;

	private Database db = Database.getInstance();
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
    private TableColumn<Haushalt, Boolean> cellLandwirtschaft;
	@FXML
    private TableColumn<Haushalt, Boolean> cellGarten;

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
			Stage stage = new Stage();
    		Parent rootframe = FXMLLoader.load(getClass().getResource("/resources/addHaushalt.fxml"));
    		Scene scene = new Scene(rootframe);

    		stage.setScene(scene);
    		stage.setTitle("Insert Haushalt");
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
				Haushalt temp = this.tableviewData.getSelectionModel().getSelectedItem();
				this.tableviewData.getSelectionModel().clearSelection();
				db.deleteHaushalt(temp);
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
		db.loadHaushalteFromDb();
		ObservableList<Haushalt> tmpList = FXCollections.observableArrayList(db.getVecHaushalt());
		this.tableviewData.setItems(tmpList);
	}

	@FXML
    void onAction_btnLoad(Event event) {
    	try {
			db.loadHaushalteFromDb();
			ObservableList<Haushalt> tmpList = FXCollections.observableArrayList(db.getVecHaushalt());
	    	this.tableviewData.setItems(tmpList);
		} catch (SQLException e) {
			System.out.println("an error occured: " + e.getMessage());
			e.printStackTrace();
		}
    }

	@Override
	public void initialize(URL location, ResourceBundle resources) {
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
    		new PropertyValueFactory<Haushalt, Boolean>("Landwirtschaft")
    	);
    	this.cellGarten.setCellValueFactory
    	(
    		new PropertyValueFactory<Haushalt, Boolean>("Garten")
    	);

    	this.cellStrasse.setCellFactory(TextFieldTableCell.forTableColumn());
    	this.cellPLZ.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerConverter()));
    	this.cellHausnummer.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerConverter()));
    	this.cell_HH_ID.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerConverter()));
    	this.cellTuerNr.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerConverter()));
    	this.cellWohnflaeche.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerConverter()));
    	this.cellLandwirtschaft.setCellFactory(TextFieldTableCell.forTableColumn(new BooleanConverter()));
    	this.cellGarten.setCellFactory(TextFieldTableCell.forTableColumn(new BooleanConverter()));
	}
}
