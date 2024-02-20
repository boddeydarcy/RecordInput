public class Record {
    //initialising private variables to be accessed by the getter methods
    private final String recordID;
    private final String customerID;
    private final String loanType;
    private final double interestRate;
    private final int leftToPay;
    private final int loanTerm;
    public static String[][] recordTable;


    //creates an instance of Record with no name these values will be stored into the array straight away
    public Record(String recordID, String customerID, String loanType, double interestRate, int leftToPay, int loanTerm) {
        this.recordID = recordID;
        this.customerID = customerID;
        this.loanType = loanType;
        this.interestRate = interestRate;
        this.leftToPay = leftToPay;
        this.loanTerm = loanTerm;
    }

    public Record(){ //default values for the default instances of Records
        this.recordID = "XXXXXX";
        this.customerID = "AAAXXX";
        this.loanType = "NULL";
        this.interestRate = 0;
        this.leftToPay = 0;
        this.loanTerm = 0;
    }
    //getter methods for all the private variables
    public String getRecordID(){
        return recordID;
    }

    public String getCustomerID(){
        return customerID;
    }

    public String getLoanType(){
        return loanType;
    }

    public double getInterestRate(){
        return interestRate;
    }

    public int getLeftToPay(){
        return leftToPay;
    }

    public int getLoanTerm(){
        return loanTerm;
    }

    public static String[][] getRecordTable(){
        return recordTable;
    }

    public void addToArray(String[][] recordTable, int i){ //called when creating a new object after the object has been created
        --i; //decrements i to start
        recordTable[i][0] = getRecordID();
        recordTable[i][1] = getCustomerID();
        recordTable[i][2] = getLoanType();
        //the three getter methods of integers have to be converted to a string
        recordTable[i][3] = String.format("%.2f", getInterestRate()); //formats the interest rate to 2 decimal point and will round accordingly
        recordTable[i][4] = String.valueOf(getLeftToPay());
        recordTable[i][5] = String.valueOf(getLoanTerm());
        Record.recordTable = recordTable; //will save recordTable with these values
    }
}