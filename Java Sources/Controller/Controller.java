package pkgController;

import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class Controller {
	@FXML
	private Button btnOrt;
	@FXML
	private Button btnStrasse;
	@FXML
	private Button btnAdresse;
	@FXML
	private Button btnVerwaltungspersonal;
	@FXML
	private Button btnWasserstandsmeldung;
	@FXML
	private Button btnWasserzaehler;
	@FXML
	private Button btnHaushalt;
	@FXML
	private Button btnMitglieder;

	@FXML
	void onAction_btnOrt(Event e) {
		try{
    		Stage stage = new Stage();
    		Parent rootframe = FXMLLoader.load(getClass().getResource("/resources/Ort.fxml"));
    		Scene scene = new Scene(rootframe);

    		stage.setScene(scene);
    		stage.setTitle("Details of profile");
    		stage.initModality(Modality.APPLICATION_MODAL);

    		stage.showAndWait();
    	}
    	catch(Exception ex){
    		System.out.println(ex.getMessage());
    	}
	}
	@FXML
	void onAction_btnStrasse(Event e) {
		try{
    		Stage stage = new Stage();
    		Parent rootframe = FXMLLoader.load(getClass().getResource("/resources/Strasse.fxml"));
    		Scene scene = new Scene(rootframe);

    		stage.setScene(scene);
    		stage.setTitle("Details of profile");
    		stage.initModality(Modality.APPLICATION_MODAL);

    		stage.showAndWait();
    	}
    	catch(Exception ex){
    		System.out.println(ex.getMessage());
    	}
	}
	@FXML
	void onAction_btnAdresse(Event e) {
		try{
    		Stage stage = new Stage();
    		Parent rootframe = FXMLLoader.load(getClass().getResource("/resources/Adresse.fxml"));
    		Scene scene = new Scene(rootframe);

    		stage.setScene(scene);
    		stage.setTitle("Details of profile");
    		stage.initModality(Modality.APPLICATION_MODAL);

    		stage.showAndWait();
    	}
    	catch(Exception ex){
    		System.out.println(ex.getMessage());
    	}
	}
	@FXML
	void onAction_btnHaushalt(Event e) {
		try{
    		Stage stage = new Stage();
    		Parent rootframe = FXMLLoader.load(getClass().getResource("/resources/Haushalt.fxml"));
    		Scene scene = new Scene(rootframe);

    		stage.setScene(scene);
    		stage.setTitle("Details of profile");
    		stage.initModality(Modality.APPLICATION_MODAL);

    		stage.showAndWait();
    	}
    	catch(Exception ex){
    		System.out.println(ex.getMessage());
    	}
	}
	@FXML
	void onAction_btnWasserzaehler(Event e) {
		try{
    		Stage stage = new Stage();
    		Parent rootframe = FXMLLoader.load(getClass().getResource("/resources/Wasserzaehler.fxml"));
    		Scene scene = new Scene(rootframe);

    		stage.setScene(scene);
    		stage.setTitle("Details of profile");
    		stage.initModality(Modality.APPLICATION_MODAL);

    		stage.showAndWait();
    	}
    	catch(Exception ex){
    		System.out.println(ex.getMessage());
    	}
	}
	@FXML
	void onAction_btnWasserstandsmeldung(Event e) {
		try{
    		Stage stage = new Stage();
    		Parent rootframe = FXMLLoader.load(getClass().getResource("/resources/Wasserstandsmeldung.fxml"));
    		Scene scene = new Scene(rootframe);

    		stage.setScene(scene);
    		stage.setTitle("Details of profile");
    		stage.initModality(Modality.APPLICATION_MODAL);

    		stage.showAndWait();
    	}
    	catch(Exception ex){
    		System.out.println(ex.getMessage());
    	}
	}
	@FXML
	void onAction_btnMitglieder(Event e) {
		try{
    		Stage stage = new Stage();
    		Parent rootframe = FXMLLoader.load(getClass().getResource("/resources/Mitglieder.fxml"));
    		Scene scene = new Scene(rootframe);

    		stage.setScene(scene);
    		stage.setTitle("Details of profile");
    		stage.initModality(Modality.APPLICATION_MODAL);

    		stage.showAndWait();
    	}
    	catch(Exception ex){
    		System.out.println(ex.getMessage());
    	}
	}
	@FXML
	void onAction_btnVerwaltungspersonal(Event e) {
		try{
    		Stage stage = new Stage();
    		Parent rootframe = FXMLLoader.load(getClass().getResource("/resources/Verwaltungspersonal.fxml"));
    		Scene scene = new Scene(rootframe);

    		stage.setScene(scene);
    		stage.setTitle("Details of profile");
    		stage.initModality(Modality.APPLICATION_MODAL);

    		stage.showAndWait();
    	}
    	catch(Exception ex){
    		System.out.println(ex.getMessage());
    	}
	}
}
