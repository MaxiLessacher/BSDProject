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
import pkgClasses.Ort;
import pkgConverter.IntegerConverter;
import pkgDatabase.Database;

public class OrtsController implements Initializable {
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


	private Database db = Database.getInstance();

	@FXML
	void onAction_btnCreate(Event ev) {
		try{
    		Stage stage = new Stage();
    		Parent rootframe = FXMLLoader.load(getClass().getResource("/resources/addOrt.fxml"));
    		Scene scene = new Scene(rootframe);

    		stage.setScene(scene);
    		stage.setTitle("Details of Ort");
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
    	catch(Exception ex){
    		System.out.println(ex.getMessage());
    	}
	}
	@FXML
	void onAction_btnDelete(Event ev) {
		try {
			if (this.tableviewData.getSelectionModel().getSelectedItem() != null) {
				Ort temp = this.tableviewData.getSelectionModel().getSelectedItem();
				this.tableviewData.getSelectionModel().clearSelection();
				db.deleteOrt(temp);
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
		db.loadOrtFromDb();
		ObservableList<Ort> tmpList = FXCollections.observableArrayList(db.getVecOrt());
		this.tableviewData.setItems(tmpList);
	}

	@FXML
    void onAction_btnLoad(Event event) {
    	try {
			db.loadOrtFromDb();
			ObservableList<Ort> tmpList = FXCollections.observableArrayList(db.getVecOrt());
	    	this.tableviewData.setItems(tmpList);
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
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		this.cellOrt.setCellValueFactory
    	(
    		new PropertyValueFactory<Ort, String>("ort")
    	);
    	this.cellPLZ.setCellValueFactory
    	(
    		new PropertyValueFactory<Ort, Integer>("plz")
    	);
    	this.cellOrt.setCellFactory(TextFieldTableCell.forTableColumn());
    	this.cellPLZ.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerConverter()));
	}
}
