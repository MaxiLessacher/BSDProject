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
import pkgClasses.Wasserzaehler;
import pkgConverter.BooleanConverter;
import pkgConverter.IntegerConverter;
import pkgDatabase.Database;

public class WasserzaehlerController implements Initializable {
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
    private TableColumn<Wasserzaehler, Boolean> cellHauptzaehler;
	@FXML
    private TableColumn<Wasserzaehler, Integer> cellZaehlerstand;
	@FXML
	private TableView<Wasserzaehler> tableviewData;

	private Database db = Database.getInstance();

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
			Stage stage = new Stage();
    		Parent rootframe = FXMLLoader.load(getClass().getResource("/resources/addWasserzaehler.fxml"));
    		Scene scene = new Scene(rootframe);

    		stage.setScene(scene);
    		stage.setTitle("Insert Wasserzähler");
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
				Wasserzaehler temp = this.tableviewData.getSelectionModel().getSelectedItem();
				this.tableviewData.getSelectionModel().clearSelection();
				db.deleteWasserzaehler(temp);
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
		db.loadWasserzaehlerFromDb();
		ObservableList<Wasserzaehler> tmpList = FXCollections.observableArrayList(db.getVecWasserzaehler());
		this.tableviewData.setItems(tmpList);
	}

	@FXML
    void onAction_btnLoad(Event event) {
    	try {
			db.loadWasserzaehlerFromDb();
			ObservableList<Wasserzaehler> tmpList = FXCollections.observableArrayList(db.getVecWasserzaehler());
	    	this.tableviewData.setItems(tmpList);
		} catch (SQLException e) {
			System.out.println("an error occured: " + e.getMessage());
			e.printStackTrace();
		}
    }

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		this.cellZaehlerNr.setCellValueFactory
    	(
    		new PropertyValueFactory<Wasserzaehler, Integer>("zaehler_nr")
    	);
		this.cell_HH_ID.setCellValueFactory
    	(
    		new PropertyValueFactory<Wasserzaehler, Integer>("HH_ID")
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
    		new PropertyValueFactory<Wasserzaehler, Boolean>("Hauptzaehler")
    	);
		this.cellZaehlerstand.setCellValueFactory
    	(
    		new PropertyValueFactory<Wasserzaehler, Integer>("zaehlerstand")
    	);
    	this.cellZaehlerNr.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerConverter()));
    	this.cellHauptzaehler.setCellFactory(TextFieldTableCell.forTableColumn(new BooleanConverter()));
    	this.cellStandort_X.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerConverter()));
    	this.cellStandort_Y.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerConverter()));
    	this.cell_HH_ID.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerConverter()));
    	this.cellZaehlerstand.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerConverter()));
	}
}
