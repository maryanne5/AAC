package gui;

import java.io.File;
import java.util.ArrayList;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ListView;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import model.Input;
import model.Logger;
import model.Scan;
import model.ScanFactory;
import model.ScanItem;
import model.ScanTableView;

public class AbuseAddressChecker{

	private Input importFile;
	private Scan scan;
	private Logger logger;

	public AbuseAddressChecker() {
		ScanFactory scanFactory = new ScanFactory();
		logger = Logger.getLogger();
		scan = new Scan(scanFactory.create("ScanExcel"));
		this.importFile = new Input();
	}

	public void ImportAdressesFile(ListView<String> Address_ListvVew, TextField Address_textField) {
		Address_ListvVew.getItems().clear();
		FileChooser fileChooser = new FileChooser();
		String currentDirectory = System.getProperty("user.dir");
		fileChooser.setInitialDirectory(new File(currentDirectory));
		fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("Text Files", "*.txt"));
		File selectedFile = fileChooser.showOpenDialog((Stage) Address_textField.getScene().getWindow());

		if (selectedFile != null) {
			importFile.upload(selectedFile.toString());

			for (String address : importFile.getAddresses()) {
				Address_ListvVew.getItems().add(address);
			}
			logger.log("The addresses file is uploaded Successfully!");
		} else {
			logger.log("File NOT Selected - Please select a file.");

		}
	}

	
	public void DeleteRow(ListView<String> Address_ListvVew) {
		if (Address_ListvVew.getSelectionModel().getSelectedItem() != null) {
			importFile.delete(Address_ListvVew);
			Address_ListvVew.getItems().remove(Address_ListvVew.getSelectionModel().getSelectedItem());
			logger.log("Selected row was removed successfully.");
		} else {
			logger.log("Row NOT selected - Please select a row.");
		}
	}

	
	public void ClearTable(ListView<String> Address_ListvVew) {
		Address_ListvVew.getItems().clear();
		importFile.clear();
		logger.log("The table was cleared successfully.");

	}

	
	public void AddToList(ListView<String> listvVew, TextField textField) {
		if (textField.getText() == null || textField.getText().isEmpty()) {
			logger.log("Address wasn't inserted - please insert an address");
			return;
		}

		importFile.add(textField.getText());
		listvVew.getItems().clear();

		for (String address : importFile.getAddresses()) {
			listvVew.getItems().add(address);
		}
		textField.clear();
		logger.log("The table's content was added to the list successfully ");
	}

	
	public void Scan(TableView<ScanTableView> tableView) {
		scan.clear();
		tableView.getItems().clear();

		scan.ScanAddresses(importFile.getAddresses());
		ObservableList<ScanTableView> ov = FXCollections.observableArrayList();
		ArrayList<ScanTableView> scanForTableView = new ArrayList<>();
		ImageView img;
	
		for (ScanItem item : scan.getDataAsArrayList()) {
			if(!item.getNumOfAbuses().equals("0")) 
				img = new ImageView(new Image(this.getClass().getResourceAsStream("/gui/resources/iconX.png")));
			else
				img = new ImageView(new Image(this.getClass().getResourceAsStream("/gui/resources/iconV.png")));
			
			img.setFitWidth(30);
			img.setFitHeight(30);
			scanForTableView.add(new ScanTableView(item.getAddress(), item.getNumOfAbuses(),img, item.getLink()));
			
		}
		
		for (ScanTableView item : scanForTableView) {
			ov.add(item);
		}
		tableView.setItems(ov);
		
			
		logger.log("Scan is complete.");

	}

	
	public void Save() {
		scan.SaveScanData();
	}

	
	public void LoadLogsFromFile(TextArea log_textArea) {
		log_textArea.clear();
		logger.loadLogsFromFile();

		for (String log : logger.getLogs()) {
			log_textArea.appendText("\n" + log);
		}
	}
	
	public void editInput(ListView<String> Address_ListView, String Address) {
		importFile.editAddress(Address_ListView,Address);
		
	}
}
