package atm;

import java.util.TreeMap;

public class customer {
	public boolean validAccountNumber(String accountNumber, TreeMap<String, CustomerDetails> db){
        if(accountNumber.equals(db.get(accountNumber).getAccountNumber())){
            return true;
        }
        return false;
    }
    public boolean validPinNumber(String accountNumber, String pinNumber, TreeMap<String, CustomerDetails> db){
        if(pinNumber.equals(db.get(accountNumber).getPinNumber())){
            return true;
        }
        return false;
    }

    public void changePin(String accountNumber, String pinNumber, TreeMap<String, CustomerDetails> db){
        db.get(accountNumber).setPinNumber(pinNumber);
    }


    public void transferAmount(String fromAccountNumber, String toAccountNumber, int amount, TreeMap<String, CustomerDetails> db){
        db.get(fromAccountNumber).withdraw(fromAccountNumber, amount, db);
        db.get(toAccountNumber).deposit(toAccountNumber, amount, db);
    }

}
