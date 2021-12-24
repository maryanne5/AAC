package model;

public class ScanFactory {


	public IScan create(String name) {
	       if(name.equalsIgnoreCase("ScanExcel")){
	          return new ScanExcel();
	       }
	       return new NullScan();
	}
}