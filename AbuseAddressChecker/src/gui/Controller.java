package gui;

import java.net.URL;
import java.util.ResourceBundle;


import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;

import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldListCell;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import model.HyperlinkCell;
import model.ScanTableView;

public class Controller implements Initializable{

    @FXML
    private AnchorPane anchorP;
    
    @FXML
    private TextField Address_textField;

    @FXML
    private Button Add_button;

    @FXML
    private ListView<String> Address_ListView;

    @FXML
    private Button clear_button;

    @FXML
    private Button scan_button;

    @FXML
    private TextArea log_textArea;

    @FXML
    private TableView<ScanTableView> tableView;
    
    @FXML
    private TableColumn<ScanTableView, String> addressCol;
    
    @FXML
    private TableColumn<ScanTableView, String> iconCol;

    @FXML
    private TableColumn<ScanTableView, String> abusesCol;

    @FXML
    private TableColumn<ScanTableView, Hyperlink> linkCol;

    @FXML
    private ImageView upload_button;

    @FXML
    private ImageView save_button;

    @FXML
    private Button clear_button1;

    @FXML
    private Button clear_button2;
    
    AbuseAddressChecker addressCheck;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		init();
		addressCheck = new AbuseAddressChecker();
		Address_ListView.setCellFactory(TextFieldListCell.forListView());
		
		Address_ListView.setOnEditCommit(new EventHandler<ListView.EditEvent<String>>() {
			@Override
			public void handle(ListView.EditEvent<String> t) {
				addressCheck.editInput( Address_ListView, t.getNewValue());
				Address_ListView.getItems().set(t.getIndex(), t.getNewValue());
				
			}
						
		});
		
		addressCheck.LoadLogsFromFile(log_textArea);
		
		
	}
	
	private void init() {
		addressCol.setCellValueFactory(new PropertyValueFactory<ScanTableView, String>("address"));
		abusesCol.setCellValueFactory(new PropertyValueFactory<ScanTableView, String>("numOfAbuses"));
		iconCol.setCellValueFactory(new PropertyValueFactory<ScanTableView, String>("icon"));
		linkCol.setCellValueFactory(new PropertyValueFactory<ScanTableView, Hyperlink>("link"));
		linkCol.setCellFactory(new HyperlinkCell());
		
		
	}
	
	@FXML
	private void ImportAdressesFile() {
		addressCheck.ImportAdressesFile(Address_ListView, Address_textField);
		addressCheck.LoadLogsFromFile(log_textArea);
	}

	@FXML
	private void DeleteRow() {
		addressCheck.DeleteRow(Address_ListView);
		addressCheck.LoadLogsFromFile(log_textArea);

	}

	@FXML
	private void ClearTable() {
		addressCheck.ClearTable(Address_ListView);
		addressCheck.LoadLogsFromFile(log_textArea);

	}

	@FXML
	private void AddToList() {
		addressCheck.AddToList(Address_ListView, Address_textField);
		addressCheck.LoadLogsFromFile(log_textArea);

	}
	
	@FXML
	private void Scan() {
		addressCheck.Scan(tableView);
		addressCheck.LoadLogsFromFile(log_textArea);

	}
	
	@FXML 
	private void SaveScanAction() {
		addressCheck.Save();
        Alert a = new Alert(AlertType.NONE);
        a.setAlertType(AlertType.INFORMATION);
        a.setTitle("Alerting Confermation");
        a.setContentText("The Scan Was Saved");
        a.show();
		addressCheck.LoadLogsFromFile(log_textArea);

	}

}


