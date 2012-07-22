package tddact;

import java.util.HashSet;
import java.util.Set;


public class VendingMachine {

	private int availableTotalAmount = 0;
	private int unavailableTotalAmount = 0;
	private int[] availableCoinAndBill = {
			10, 50, 100, 500, 1000
	};
	private Set<Juice> juiceStock = null;
		
	public VendingMachine() {
		juiceStock = new HashSet<Juice>();
		juiceStock.add(new Juice("コーラ", 150, 5));		
		juiceStock.add(new Juice("レッドブル", 260, 0));
		juiceStock.add(new Juice("レッドブル", 500, 10));
	}
	
	public void insert(int amount) {
		if (validateAmount(amount)) {
			availableTotalAmount += amount;
			return;
		}
		unavailableTotalAmount += amount;
	}

	private boolean validateAmount(int amount) {
		for (int i = 0; i < availableCoinAndBill.length; ++i) {
			if (availableCoinAndBill[i] == amount) {
				return true;
			}
		}
		
		return false;
	}

	public int getAvailableTotalAmount() {
		return availableTotalAmount;
	}

	public int cancel() {
		int result = availableTotalAmount + unavailableTotalAmount;
		availableTotalAmount = 0;
		unavailableTotalAmount = 0;
		return result;
	}

	public Set<Juice> getJuiceStock() {
		return juiceStock;
	}

}
