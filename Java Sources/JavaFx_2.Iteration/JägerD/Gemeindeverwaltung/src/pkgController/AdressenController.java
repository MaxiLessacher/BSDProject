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
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.control.TableColumn.CellEditEvent;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import pkgClasses.Adresse;
import pkgConverter.IntegerConverter;
import pkgDatabase.Database;

public class AdressenController implements Initializable {
	Database db = Database.getInstance();
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

    @FXML
	void onAction_btnCreate(Event ev) {
		try
    	{
			Stage stage = new Stage();
    		Parent rootframe = FXMLLoader.load(getClass().getResource("/resources/addAdresse.fxml"));
    		Scene scene = new Scene(rootframe);

    		stage.setScene(scene);
    		stage.setTitle("Insert Adresse");
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
				Adresse temp = this.tableviewData.getSelectionModel().getSelectedItem();
				this.tableviewData.getSelectionModel().clearSelection();
				db.deleteAdresse(temp);
				this.updateTable();
			}
			else {
				System.out.println("you have to select an element first");
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
		db.loadAdressenFromDb();
		ObservableList<Adresse> tmpList = FXCollections.observableArrayList(db.getVecAdresse());
		this.tableviewData.setItems(tmpList);
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

	    	this.tableviewData.setItems(tmpList);

    	} catch (SQLException e) {
			System.out.println("an error occured: " + e.getMessage());
			e.printStackTrace();
		}
    }

    public void setDatabase(Database db) {
    	this.db = db;
    }
	@Override
	public void initialize(URL location, ResourceBundle resources) {
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
    	
     	this.cellStrasse.setCellFactory(TextFieldTableCell.forTableColumn());
    	this.cellPLZ.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerConverter()));
    	this.cellHausnummer.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerConverter()));
	}

}