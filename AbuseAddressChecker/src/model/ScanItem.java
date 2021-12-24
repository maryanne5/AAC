package model;


public class ScanItem {
	private String address;
	private String numOfAbuses;
	private String link;
	
	public ScanItem(String address, String numOfAbuses, String link) {
		this.address = address;
		this.numOfAbuses = numOfAbuses;
		this.link = link;
	}
	
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getNumOfAbuses() {
		return numOfAbuses;
	}
	public void setNumOfAbuses(String numOfAbuses) {
		this.numOfAbuses = numOfAbuses;
	}
	public String getLink() {
		return link;
	}
	public void setLink(String link) {
		this.link = link;
	}
		


}
