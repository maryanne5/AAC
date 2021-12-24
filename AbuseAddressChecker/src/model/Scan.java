package model;

import java.util.ArrayList;
import java.util.HashSet;

import connection.BC_Connector;


public class Scan {
	protected ArrayList<ScanItem> scan = new ArrayList<ScanItem>();
	private IScan scanActions;
	private BC_Connector connector;
	
	public Scan(IScan actions) {
		connector = new BC_Connector();
		this.scanActions = actions;
	}


	public IScan createScan(String name) {
	      if(name == null){
	          return null;
	       }
	       if(name.equalsIgnoreCase("ScanExcel")){
	          return new ScanExcel();
	       }
	       return null;
	}
	public ArrayList<ScanItem> getDataAsArrayList() {
		return scan;
	}
	public void clear() {
		scan.clear();
	}
	
	public void addItem(ScanItem item) {
		scan.add(item);
	}

	public void SaveScanData() {
		scanActions.SaveScan(scan);
	}
	
	public void ScanAddresses(HashSet<String> addresses) {
		for (String address : addresses) {
			String numOfAbuses = connector.getNumberOfAbuses(address);
			String link = connector.getLink(address);
			ScanItem item = new ScanItem(address, numOfAbuses, link);
			scan.add(item);
		}
	}

}
