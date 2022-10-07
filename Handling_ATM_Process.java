package atm;

public class Handling_ATM_Process {
	 public void updateDenomination(int amount, int denomination, Denomination hd){
	        if(amount==2000){
	                hd.setTwoThousand(hd.getTwoThousand()+denomination);
	        }
	        else if(amount==500){
	                hd.setFiveHundred(hd.getFiveHundred()+denomination);
	        }
	        else if(amount==100){
	                hd.setOneHundred(hd.getOneHundred()+denomination);
	        }
	    }

	    public static int reduceDenomination(int amount, int denomination, Denomination hd){
	        int flag1=0, flag2=0;
	        if(amount==2000){
	            if(hd.getTwoThousand()>0){
	                hd.setTwoThousand(hd.getTwoThousand()-denomination);
	                return 1;
	            }
	            else if(hd.getFiveHundred()>0){
	                flag1=1;
	                hd.setFiveHundred(hd.getFiveHundred()-denomination);
	            }
	            else if(hd.getOneHundred()>0){
	                flag2=1;
	                hd.setOneHundred(hd.getOneHundred()-denomination);
	            }
	        }
	        else if(amount==500){
	            if(hd.getFiveHundred()>0){
	                if(flag1==0){
	                    hd.setFiveHundred(hd.getFiveHundred()-denomination);
	                    return 1;
	                }
	            }
	            else if(hd.getOneHundred()>0)
	                if(flag2==0)
	                hd.setOneHundred(hd.getOneHundred()-denomination);
	        }
	        else if(amount==100){
	            if(hd.getOneHundred()>0){
	                if(flag2==0){
	                hd.setOneHundred(hd.getOneHundred()-denomination);
	                return 1;}
	            }
	        }
	        return -1;
	    }

	    public static void updateDepositingAmount(ATMProcess atmDatabase, Denomination hd){
	        int depositingAmount=hd.getTwoThousand()*2000+hd.getFiveHundred()*500+hd.getOneHundred()*100;
	        atmDatabase.setDeposingAmount(depositingAmount);
	        atmDatabase.setBalaceAmount(atmDatabase.getDeposingAmount());
	    }

	    public static void updateWithdraw(ATMProcess atmDatabase, int withdrawAmount){
	        atmDatabase.setWithdrawAmount(withdrawAmount);
	        atmDatabase.setBalaceAmount(atmDatabase.getBalaceAmount()-withdrawAmount);
	    }

	    public static int[] dispensingDenomination(int[] notes, int withdrawAmount){
	        int[] noteCounter=new int[notes.length];
	        for(int i=0;i<notes.length;i++){
	            if(withdrawAmount>=notes[i]){
	                noteCounter[i]=withdrawAmount/notes[i];
	                withdrawAmount=withdrawAmount-noteCounter[i]*notes[i];
	            }
	        }
	        return noteCounter;
	    }

}
