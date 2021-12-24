package model;
import javafx.application.HostServices;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.util.Callback;
public class HyperlinkCell implements Callback<TableColumn<ScanTableView, Hyperlink>, TableCell<ScanTableView, Hyperlink>> {
	
	 private static HostServices hostServices ;

	    public static HostServices getHostServices() {
	        return hostServices ;
	    }

	    public static void setHostServices(HostServices hostServices) {
	        HyperlinkCell.hostServices = hostServices ;
	    }

    
	@Override
	public TableCell<ScanTableView, Hyperlink> call(TableColumn<ScanTableView, Hyperlink> arg0) {
		TableCell<ScanTableView, Hyperlink> cell = new TableCell<ScanTableView, Hyperlink>() {
			protected void updateItem(Hyperlink item, boolean empty) {
			    super.updateItem(item, empty);
			    setGraphic(empty ? null : item);
			    if (! empty) {
			        item.setOnAction(e -> {
			        	 hostServices.showDocument(item.getText());
			        });
			    }
			}
        };
        return cell;
	}
	 
}



