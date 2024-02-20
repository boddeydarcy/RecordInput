import java.util.InputMismatchException;
import java.util.Objects;
import java.util.Scanner;

public class XYZBank {
    static Scanner sc = new Scanner(System.in); //creates a new scanner that will be accessed by the whole file

    public static String inputRecordID(int registeredRow) { //creates function to input the recordID
        System.out.println("Enter the RecordID in the form XXXXXX where X is a digit from 0 to 9");
        String newRecordID = sc.next();
        //if length of the recordID is less than or greater than 6 characters then it will throw an exception
        if (newRecordID.length() < 6) {
            throw new InputMismatchException("The Record ID you have entered is less than 6 characters");
        }
        if (newRecordID.length() > 6) {
            throw new InputMismatchException("The Record ID you have entered is greater than 6 characters");
        }
        /*will loop through the string and checks if the character at index i is a letter.
        Will throw an exception if it is */
        for (int i = 0; i < newRecordID.length(); i++) {
            if (Character.isLetter(newRecordID.charAt(i))) {
                throw new InputMismatchException("You have entered a letter, the input must be all digits");
            }
        }
        String[][] checkRecordID = Record.getRecordTable();
        if(registeredRow != 0) {
            for (int i = 0; i <= registeredRow; i++) {
                if (newRecordID.equals(checkRecordID[i][0])) {
                    throw new InputMismatchException("You have entered the same recordID more than once"+ " '"+newRecordID+"'");
                }
            }
        }
        return newRecordID; //returns the recordID to the new record object
    }

    public static String inputCustID() { //creates new function to input customerID
        System.out.println("Enter the Customer ID in the form AAAXXX where A is a letter from 'a' to 'z' and X is a digit from 0 to 9");
        String custID = sc.next();
        custID = custID.toUpperCase(); //changes all characters of the string to upper-case
        //checks if the length of the input is less than or greater than 6 and throws an exception if it is
        if (custID.length() < 6) {
            throw new InputMismatchException("The Customer ID you have entered is less than 6 characters");
        }
        if (custID.length() > 6) {
            throw new InputMismatchException("The Customer ID you have entered is greater than 6 characters");
        }
        //loops through the string
        for (int i = 0; i < custID.length(); ++i) {
            //this if statement will call for the first 3 indexes to check if the characters aren't letters.
            if (i <= 2) {
                if (Character.isDigit(custID.charAt(i))) {
                    throw new InputMismatchException("Incorrect formatting");
                }
            }
            //if statement will call from index 3 and beyond to check if the characters aren't numbers.
            if (i > 2) {
                if (Character.isLetter(custID.charAt(i))) {
                    throw new InputMismatchException("Incorrect formatting");
                }
            }
        }
        return custID; //returns the customerID to the new record if everything is valid.
    }

    public static String inputLoanType() { //create the loan type func
        System.out.println("Enter which loan type between 'Auto', 'Builder', 'Mortgage', 'Personal' or 'Other'");
        String loanType = sc.next().toLowerCase(); // if the user enters 'auto' or 'Auto' both are valid with toLowerCase()
        // will return the choice as a string to the new object
        return switch (loanType.toLowerCase()) {
            case "auto" -> "Auto";
            case "builder" -> "Builder";
            case "mortgage" -> "Mortgage";
            case "personal" -> "Personal";
            case "other" -> "Other";
            // throws an exception if the input isn't one of the options.
            default -> throw new InputMismatchException("Not a choice given: " + loanType);
        };
    }


    public static double inputIntRate() { //create an inputIntRate func
        System.out.println("Enter the interest rate for the record");
        double newInterest = sc.nextDouble(); //stores a double
        if (newInterest < 0) { //throws an exception if the interest rate is less than 0
            throw new InputMismatchException("You cannot have a negative interest rate");
        }
        return newInterest; //returns the double
    }

    public static int inputAmountLeft() { //creates the inputAmountLeft func
        System.out.println("Amount left to pay in thousands pounds");
        int leftToPay = sc.nextInt();
        if (leftToPay < 0) { //throws an exception if the amount left to pay is less than 0
            throw new InputMismatchException("The account cannot have a negative balance");
        }
        return leftToPay; //returns an integer to the new Record
    }


    public static int inputTimeLeft() { //create a func to input time left of loan
        System.out.println("Enter the amount of time left on the loan in years");
        int loanTerm = sc.nextInt(); //will throw an exception if the user enters anything other than an int
        if (loanTerm < 0) {
            //if the user enters a loan term less than 0 years then it throws an exception.
            throw new InputMismatchException("The term cannot have less than 0 years left");
        }
        return loanTerm; //returns an integer to the new object.
    }

    public static String[] tableHeader() {
        //creates a new array with a size of 6 and assigns each index to a header.
        String[] recordTableHeader = new String[6];
        recordTableHeader[0] = "RecordID";
        recordTableHeader[1] = "CustomerID";
        recordTableHeader[2] = "LoanType";
        recordTableHeader[3] = "IntRate";
        recordTableHeader[4] = "AmountLeft";
        recordTableHeader[5] = "TimeLeft";
        return recordTableHeader; //returns the 1d array with just the headers
    }

    public static void main(String[] args) {
        System.out.println("How many records do you want to enter? ");
        int recordAmount = sc.nextInt();
        if (recordAmount < 1) {
            throw new InputMismatchException("You must enter 1 or more records");
        }
        /* creates a new 2D array with the amount of columns being 6 for the 6 functions and the rows
        being the amount for how many the user wanted. */
        int registeredRow = 0;
        String[][] initialRecordTable = new String[recordAmount][6];
        for(int row = 1; row <= recordAmount; ++row){
            if(row == 1){
                new Record(inputRecordID(registeredRow), inputCustID(), inputLoanType(), inputIntRate(), inputAmountLeft(), inputTimeLeft()).addToArray(initialRecordTable, row);
                registeredRow++; //increments the value to show that a record has been inputted
            }
            if(row > 1) {
                //will only run if it is not the first record
                System.out.println("Do you wanted to enter another record? ");
                if (Objects.equals(sc.next().toLowerCase(), "yes")) {
                    new Record(inputRecordID(registeredRow), inputCustID(), inputLoanType(), inputIntRate(), inputAmountLeft(), inputTimeLeft()).addToArray(initialRecordTable, row);
                    registeredRow++;
                } else {
                    break;
                }
            }
        }

        if(registeredRow != recordAmount) { //will run if the amount of rows in the array is not equals to the total rows entered
            System.out.println("Do you want to fill up the table with empty records? ");
            String fillTable = sc.next().toLowerCase();
            if (fillTable.equals("yes")) {
                for (int i = registeredRow; i < recordAmount; i++) {
                    registeredRow++;
                    //new Record("XXXXXX", "AAAXXX", "NULL", 0, 0, 0).addToArray(initialRecordTable, registeredRow);
                    new Record().addToArray(initialRecordTable, registeredRow);
                }
            }
        }

        // creates new array with the size of registeredRow so there aren't any null records
        String[][] finalRecordTable = new String[registeredRow][6];
        //will loop through the amount of records that are registered and copy that record to the finalRecordTable
        for (int row = 0; row < registeredRow; ++row) {
            /*size of the original array was recordAmount, if the size didn't change to the amount of rows
            actually entered then it would print null for the amount of records that are empty */
            System.arraycopy(Record.recordTable[row], 0, finalRecordTable[row], 0, 6);
        }


        /*printing of the max num of records and the num of records
        will print out a new line before and after to present the tabular record clearly*/
        System.out.println("\nMaximum number of records: "+recordAmount);
        System.out.println("Registered Records: "+registeredRow+"\n");


        String[] tableHeader = tableHeader();
        //loops through each of the table headers in tableHeader array from the function
        for (int i = 0; i < 6; i++) {
            //formats the table headers to be 15 characters wide and the text to be aligned on the left hand side
            System.out.printf("%1$-15s", tableHeader[i]);
        }
        System.out.println(); //so that the records aren't printed on the same line as the headers
        for (int row = 0; row < registeredRow; ++row) {
            for (int col = 0; col < 6; ++col) {
                /*formats the table headers to be 15 characters wide and the text to be aligned on the left hand side
                Loops through two for loops for the rows and the columns*/
                System.out.printf("%1$-15s", finalRecordTable[row][col]);
            }
            System.out.println(); //needed so all the records aren't printed on the same line.
        }
    }
}