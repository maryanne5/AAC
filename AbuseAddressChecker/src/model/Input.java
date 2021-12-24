package model;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashSet;
import java.util.Scanner;

import javafx.scene.control.ListView;

public class Input {
	
	private HashSet<String> addresses = new HashSet<>(); 

	
	public void clear() {
		addresses.clear();
	}
	
	public void add(String address) {
		if (!addresses.contains(address))
			addresses.add(address);
	}
	
	public HashSet<String> getAddresses() {
		return addresses;
	}

	public void delete(ListView<String> Address_ListvVew) {
		addresses.remove(Address_ListvVew.getSelectionModel().getSelectedItem());
	}
	
	public void editAddress(ListView<String> Address_ListvVew,String Address) {
		addresses.remove(Address_ListvVew.getSelectionModel().getSelectedItem());
		addresses.add(Address);
	}
	
	public void upload(String filePath) {
		try {
		      File myObj = new File(filePath);
		      Scanner myReader = new Scanner(myObj);
		      while (myReader.hasNextLine()) {
		        String address = myReader.nextLine();
				if (!addresses.contains(address))
					addresses.add(address);
		      }
		      myReader.close();
		    } catch (FileNotFoundException e) {
				Logger.getLogger().log("[upload] - Could not load address file. Error msg: " + e.getMessage());
		    }
	}

}
