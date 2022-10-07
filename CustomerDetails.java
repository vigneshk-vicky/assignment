package atm;

	import java.util.TreeMap;
	public class CustomerDetails {
	    private  String AccountNumber;
	    private String AccountHolder;
	    private String pinNumber;
	    private int accountBalance;


	    public CustomerDetails(){

	    }

	    public CustomerDetails(String AccountNumber, String AccountHolder, String pinNumber, int accountBalance) {
	        this.AccountNumber = AccountNumber;
	        this.AccountHolder = AccountHolder;
	        this.pinNumber = pinNumber;
	        this.accountBalance = accountBalance;
	    }

	    public String getAccountNumber() {
	        return AccountNumber;
	    }

	    public String getAccountHolder() {
	        return AccountHolder;
	    }

	    public String getPinNumber() {
	        return pinNumber;
	    }

	    public int getAccountBalance() {
	        return accountBalance;
	    }

	    public void setCustomerName(String AccountHolder) {
	        this.AccountHolder = AccountHolder;
	    }

	    public void setPinNumber(String pinNumber) {
	        this.pinNumber = pinNumber;
	    }

	    public void setAccountBalance(int accountBalance) {
	        this.accountBalance = accountBalance;
	    }

	    public void withdraw(String AccountNumber, int amount, TreeMap<String, CustomerDetails> db){
	         db.get(AccountNumber).setAccountBalance(getAccountBalance()-amount);
	    }

	    public void deposit(String AccountNumber, int amount, TreeMap<String, CustomerDetails> db){
	        db.get(AccountNumber).setAccountBalance(amount);
	    }

		public String getCustomerName() {
			// TODO Auto-generated method stub
			return null;
		}
	}

