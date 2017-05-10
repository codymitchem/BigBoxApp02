package bigbox.db;

import java.util.*;
import bigbox.business.Store;

public class StoreArray implements StoreDAO {
	// create my instance variables
	private ArrayList<Store> stores = null;

	// create my constructors
	public StoreArray() {
		stores = getAllStores();
	}

	@Override
	public Store getStoreByDivionAndStoreNumber(String inDiv, String inStoreNbr) {
		Store store = null;
		for (Store s : stores) {
			if (s.getDivisionNbr().equals(inDiv) && s.getStoreNbr().equals(inStoreNbr)) {
				store = s;
			}
		}
		return store;
	}

	public ArrayList<Store> getAllStores() {
		if (stores == null)
			setupStoresArray();
		return stores;
	}

	private void setupStoresArray() {
		stores = new ArrayList<>();

		stores.add(
				new Store(1, "001", "00011", 25000, "Mason BigBox", "5711 Fields Ertel Rd.", "Mason", "OH", "45249"));

		stores.add(
				new Store(2, "001", "00255", 27500, "Downtown BigBox", "9330 Main St.", "Cincinnati", "OH", "45202"));

		stores.add(new Store(3, "001", "00172", 32555.55, "Goshen BigBox", "6777 Goshen Rd.", "Goshen", "OH", "45122"));

		stores.add(new Store(4, "001", "00075", 21425.37, "Bridgetown BigBox", "3888 Race Rd.", "Cincinnati", "OH",
				"45211"));

		stores.add(new Store(5, "111", "00001", 14432.77, "Louisville BigBox", "1111 Washington St.", "Louisville",
				"KY", "40206"));

		stores.add(new Store(6, "111", "00044", 17555.11, "Lawrenceburg BigBox", "8000 Liberty St.", "Louisville", "KY",
				"40204"));

	}

	@Override
	public ArrayList<Store> getAllStoresByDivision(String inDiv) {
		ArrayList<Store> storesForDiv = new ArrayList<>();
		for (Store s : stores) {
			if (s.getDivisionNbr().equals(inDiv)) {
				storesForDiv.add(s);
			}
		}
		return storesForDiv;
	}

	@Override
	public boolean addStore(Store s) {
		if (s.getId() == -1) { // the id needs to be determined
			s.setId(getNextId());
		}
		return stores.add(s);
	}

	@Override
	public boolean deleteStore(Store s) {
		return stores.remove(s);
	}

	private int getNextId() {
		int maxID = 1;
		for (Store s : stores) {
			maxID = Math.max(maxID, s.getId());
		}
		// once max id is determined, add 1 to it for new facID assignment
		return maxID + 1;
	}
}
