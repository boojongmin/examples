package com.school.bank_java.code;

public enum AccountDetailType {
		SAVE(0), WITHDRAW(1), TRANSFER(2) ;
		
		private final int value;
	    private AccountDetailType(int value) {
	        this.value = value;
	    }

	    public int getValue() {
	        return value;
	    }
	    
	    public static AccountDetailType valueOf(int value) {
          switch(value) {
            case 0: return AccountDetailType.SAVE;
            case 1: return AccountDetailType.WITHDRAW;
            case 2: return AccountDetailType.TRANSFER;
            default: throw new AssertionError("Unknown r: " + value);
          }
        }
	    

}
