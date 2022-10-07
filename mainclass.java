package atm;
	import java.util.Scanner;
	import java.util.TreeMap;

	public class mainclass
	{
	   
		public static void main(String[] args)
	    {
	        try(Scanner s=new Scanner(System.in);)
	        {
	            ATMProcess ad=new ATMProcess();
	            Handling_ATM_Process hd=new Handling_ATM_Process();
	            customer hc=new customer();

	            TreeMap<String, CustomerDetails> db=new TreeMap<>();
	            int[] notes=new int[]{2000, 500, 100};
	            Denomination den=new Denomination();
	          
	            while (true){
	                int option=0;
	                System.out.println("Choose any Option\n1. Load ATM\n2. Withdrawal\n3. Check ATMBalance\n4. Create Account\n5. Transfer Money\n6. Check AccountBalance\n7. End");
	                option=s.nextInt();
	                System.out.println();
	                s.nextLine();
	                switch (option){
	                    case 1:
	                    {
	                        System.out.println("--------    Load ATM    --------");
	                        System.out.println("Enter the Denomination to deposit(2000:10, 500:5) : ");
	                        String[] denominations=s.nextLine().split(",");
	                        @SuppressWarnings("unused")
							int flag=1;
	                        for(String seperate:denominations){
	                            String[] values=seperate.split(":");
	                            int amount=Integer.valueOf(values[0].trim());
	                            int denomination=Integer.valueOf(values[1]);
	                            if(amount<0||denomination<0){
	                                System.out.println("Incorrect Deposit amount");
	                            }
	                            else if (amount==0||denomination==0){
	                                System.out.println("Deposit amount cannot be Zero");
	                            }
	                            else{
	                                hd.updateDenomination(amount, denomination, den);
	                            }
	                        }

	                            hd.updateDepositingAmount(ad, den);


	                        System.out.println("Denomination            Number  Value       ");
	                        System.out.println("2000                    "+den.getTwoThousand()+"     "+2000*den.getTwoThousand());
	                        System.out.println("500                    "+den.getFiveHundred()+"     "+500*den.getFiveHundred());
	                        System.out.println("100                    "+den.getOneHundred()+"     "+100*den.getOneHundred());
	                        break;
	                    }
	                    case 2:
	                    {
	                        System.out.println("--------    Withdraw    --------");

	                        System.out.println("Enter the Account Number : ");
	                        String accountNumber=s.next();
	                        System.out.println("Enter the Pin Number : ");
	                        String pinNumber=s.next();
	                        if(hc.validAccountNumber(accountNumber, db)&&hc.validPinNumber(accountNumber, pinNumber, db)){
	                            System.out.println("Enter the amount to Withdraw : ");
	                            int withdrawAmount=s.nextInt();
	                            if(withdrawAmount<=0||withdrawAmount>ad.getBalaceAmount()){
	                                System.out.println("Incorrect or Insufficient Funds");break;
	                            }
	                            else if(db.get(accountNumber).getAccountBalance()>10000||db.get(accountNumber).getAccountBalance()<100){
	                                System.out.println("Withdraw Amount should maximum 10000 and minimum 100");break;
	                            }
	                            db.get(accountNumber).withdraw(accountNumber, withdrawAmount, db);
	                            int flag=1;
	                            int[] dispensingDenominations=hd.dispensingDenomination(notes, withdrawAmount);
	                            for(int i=0;i< notes.length;i++){
	                                if(dispensingDenominations[i]>0){
	                                    flag=hd.reduceDenomination(notes[i], dispensingDenominations[i], den);
	                                }
	                            }
	                            System.out.println();
	                            if(flag==1)
	                                hd.updateWithdraw(ad, withdrawAmount);
	                            else
	                                System.out.println("No Available Denomination");
	                        }
	                        else{
	                            System.out.println("Invalid Acoount Number or Pin Number");break;
	                        }
	                        break;
	                    }
	                    case 3:
	                    {
	                        int currentAtmBalance=ad.getBalaceAmount();
	                        if(currentAtmBalance<=0){
	                            System.out.println("No Fund");
	                            continue;
	                        }
	                        else{
	                            System.out.println("Current ATM Balance");
	                            System.out.println("Denomination            Number  Value       ");
	                            System.out.println("2000                    "+den.getTwoThousand()+"     "+2000*den.getTwoThousand());
	                            System.out.println("500                    "+den.getFiveHundred()+"     "+500*den.getFiveHundred());
	                            System.out.println("100                    "+den.getOneHundred()+"     "+100*den.getOneHundred());
	                            System.out.println("Total Amount available in ATM is = Rs. "+ad.getBalaceAmount());
	                        }
	                        break;
	                    }
	                    case 4:
	                    {
	                        System.out.println("Create Savings Account : ");
	                        System.out.println("Enter the New Account Number : ");
	                        String accountNumber=s.nextLine();
	                        System.out.println("Enter the Customer Name : ");
	                        String customerName=s.nextLine();
	                        System.out.println("Enter the New Pin Number : ");
	                        String pinNumber=s.nextLine();
	                        System.out.println("Enter the Amount to Deposit : ");
	                        int acoountBalance=s.nextInt();
	                        if(acoountBalance>=500){
	                            CustomerDetails customerDatabase=new CustomerDetails(accountNumber,customerName, pinNumber, acoountBalance);
	                            db.put(accountNumber, customerDatabase);
	                        }
	                        else{
	                            System.out.println("Minimum Balance Must be 500 or above");
	                        }
	                        break;
	                    }
	                    case 5:
	                    {
	                        System.out.println("--------    Money Transfer    --------");

	                        System.out.println("Enter the Account Number : ");
	                        String fromAccountNumber=s.next();
	                        System.out.println("Enter the Pin Number : ");
	                        String fromPinNumber=s.next();
	                        if(hc.validAccountNumber(fromAccountNumber, db)&&hc.validPinNumber(fromAccountNumber, fromPinNumber, db)){
	                            String toAccountNumber=s.next();
	                            int transferAmount=s.nextInt();
	                            hc.transferAmount(fromAccountNumber, toAccountNumber, transferAmount, db);
	                        }
	                        else{
	                            System.out.println("Invalid Account Number or Pin Number");
	                        }
	                        break;
	                    }
	                    case 6:
	                    {
	                        System.out.println("--------    Check Account Balance    --------");

	                        System.out.println("Enter the Account Number : ");
	                        String accountNumber=s.next();
	                        System.out.println("Enter the Pin Number : ");
	                        String pinNumber=s.next();
	                        if(hc.validAccountNumber(accountNumber, db)&&hc.validPinNumber(accountNumber, pinNumber, db)){
	                            System.out.println("AccNo  AccountHolder    PinNumber AccountBalance");
	                            System.out.println(accountNumber+" "+db.get(accountNumber).getCustomerName()+"    "+pinNumber+" "+db.get(accountNumber).getAccountBalance());
	                        }
	                        else{
	                            System.out.println("Invalid Account Number or Pin Number");
	                        }
	                        break;
	                    }
	                    case 7:
	                    
	                    {
	                        System.out.println("Thankyou!");
	                        System.exit(0);
	                    }
	                    default:
	                    {
	                        System.out.println("Please Enter valid option...");
	                    }
	                }
	            }
	        }
	        catch (Exception e){
	            e.printStackTrace();
	        }
	    }


}
