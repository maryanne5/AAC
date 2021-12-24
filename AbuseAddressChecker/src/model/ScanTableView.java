package model;

import javafx.beans.property.SimpleStringProperty;
import javafx.scene.control.Hyperlink;
import javafx.scene.image.ImageView;

public class ScanTableView {
	private SimpleStringProperty address;
	private SimpleStringProperty numOfAbuses;
	private ImageView icon;
	private Hyperlink link;
	
	public ScanTableView(String address, String numOfAbuses,ImageView icon, String link) {
		this.address = new SimpleStringProperty(address);
		this.numOfAbuses = new SimpleStringProperty(numOfAbuses);
		this.icon = icon;
		this.link =  new Hyperlink(link);
	}
	
	public String getAddress() {
		return address.get();
	}
	public void setAddress(SimpleStringProperty address) {
		this.address = address;
	}
	public String getNumOfAbuses() {
		return numOfAbuses.get();
	}
	public void setNumOfAbuses(SimpleStringProperty numOfAbuses) {
		this.numOfAbuses = numOfAbuses;
	}
	public Hyperlink getLink() {
		return link;
	}
	public void setLink(Hyperlink link) {
		this.link = link;
	}
	public ImageView getIcon() {
		return icon;
	}

	public void setIcon(ImageView icon) {
		this.icon = icon;
	}
}

