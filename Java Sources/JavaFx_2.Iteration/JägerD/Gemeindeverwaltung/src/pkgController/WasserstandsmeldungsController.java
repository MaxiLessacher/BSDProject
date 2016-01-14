package pkgController;

import java.net.URL;
import java.sql.SQLException;
import java.util.Date;
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
import pkgClasses.Wasserstandsmeldung;
import pkgConverter.DateConverter;
import pkgConverter.IntegerConverter;
import pkgDatabase.Database;

public class WasserstandsmeldungsController implements Initializable {
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


	private Database db = Database.getInstance();

	@FXML
    void onEdit_cellDatum(CellEditEvent<Wasserstandsmeldung, Date> event) {
    	try
    	{
    		Wasserstandsmeldung temp = db.getVecWasserstandsmeldung().get(event.getTablePosition().getRow());
    		Wasserstandsmeldung oldWasserstandsmeldung = new Wasserstandsmeldung(temp.getDatum(), temp.getZaehlerNr(), temp.getNeuZaehlerstand());
    		event.getTableView().getItems().get(event.getTablePosition().getRow()).setDatum(event.getNewValue());
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
    		Wasserstandsmeldung oldWasserstandsmeldung = new Wasserstandsmeldung(temp.getDatum(), temp.getZaehlerNr(), temp.getNeuZaehlerstand());
    		event.getTableView().getItems().get(event.getTablePosition().getRow()).setZaehlerNr(event.getNewValue());
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
    		Wasserstandsmeldung oldWasserstandsmeldung = new Wasserstandsmeldung(temp.getDatum(), temp.getZaehlerNr(), temp.getNeuZaehlerstand());
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
			Stage stage = new Stage();
    		Parent rootframe = FXMLLoader.load(getClass().getResource("/resources/addWasserstandsmeldung.fxml"));
    		Scene scene = new Scene(rootframe);

    		stage.setScene(scene);
    		stage.setTitle("Insert Strasse");
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
				Wasserstandsmeldung temp = this.tableviewData.getSelectionModel().getSelectedItem();
				this.tableviewData.getSelectionModel().clearSelection();
				db.deleteWasserstandsmeldung(temp);
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
		db.loadWasserstandsmeldungFromDb();
		ObservableList<Wasserstandsmeldung> tmpList = FXCollections.observableArrayList(db.getVecWasserstandsmeldung());
		this.tableviewData.setItems(tmpList);
	}

	@FXML
    void onAction_btnLoad(Event event) {
    	try {
			db.loadWasserstandsmeldungFromDb();
			ObservableList<Wasserstandsmeldung> tmpList = FXCollections.observableArrayList(db.getVecWasserstandsmeldung());
			this.tableviewData.setItems(tmpList);
		} catch (SQLException e) {
			System.out.println("an error occured: " + e.getMessage());
			e.printStackTrace();
		}
    }

	@Override
	public void initialize(URL location, ResourceBundle resources) {
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
    	this.cellDatum.setCellFactory(TextFieldTableCell.forTableColumn(new DateConverter()));
    	this.cellZaehlerNr.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerConverter()));
    	this.cell_neuZaehlerstand.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerConverter()));
	}
}
