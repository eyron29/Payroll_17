package Project;

import java.io.*;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Scanner;

public class Payroll {
    static Scanner inp = new Scanner(System.in);

    static ArrayList<Employee> emp = new ArrayList<>();

    static ArrayList<String> fileList = new ArrayList<String>();
    static String posCode, depCode, empName, empPos;
    static double hoursWorked, regPay, otPay, netPay;
    static double hourlyRate, taxRate;
    static double regHours = 176.00 ;
    static char subCode;

    static String[][] posHolder = {{"011A", "011B", "011C", "011D"},
            {"Senior Programmer", "Junior Programmer", "System Analyst", "Data Analyst"}};

    static String ans ="";

    static String fileName = "", fileName1 = "";

    static int choice, option;

    static String remName;

    static int x;

    static boolean nameIsFound = false;

    //index to remove
    static int i;

    static boolean isValidInput1 = false;

    static boolean isValidInput2 = false;

    static boolean isValidInput3 = false;

    static boolean nameIsEmpty = false;

    static String statCode;

    //static double presentNum;

    static double otHours;

    static boolean isValidName2 = false;

    static String integers = "0123456789";

    static boolean containsInt = false;

    static DecimalFormat twodec = new DecimalFormat("0.00");

    public static void main(String[] args) throws InvalidInputException{
        do {
            menu();

            while (!isValidInput1) {
                try{
                    System.out.print("Back to Main Menu? (YES/NO) : ");
                    ans = inp.nextLine();

                    if (ans.isEmpty()) {
                        System.out.println();
                        throw new InvalidInputException("Input is empty");
                    }

                    else if (!ans.equalsIgnoreCase("YES") && !ans.equalsIgnoreCase("NO")) {
                        System.out.println();
                        throw new InvalidInputException("Invalid input");
                    }

                    else if(ans.equalsIgnoreCase("NO")){
                        System.out.println("Exiting the program...");
                        System.exit(0);
                    }

                    else {
                        isValidInput1 = true; // To stop the nested loop
                    }
                }

                catch (InvalidInputException e) {
                    System.out.println("NOTE: Enter a valid option. Please enter 'YES' or 'NO'.");
                    System.out.println("------------------------------------------------------------------");
                }
            }

            isValidInput1 = false;
        } while (ans.equalsIgnoreCase("YES"));//members();

    }//main

    public static void menu(){
        System.out.println("\n------------------------------------------------------------------");
        System.out.println("•••••••••••••••••••EMPLOYEE PAYROLL SYSTEM •••••••••••••••••••••••");
        System.out.println("------------------------------------------------------------------");
        System.out.println("******************************************************************");
        System.out.println("               Select from the Following Options                   ");
        System.out.println("******************************************************************");
        System.out.println("------------------------------------------------------------------");
        System.out.println("\t [1] - Add Employee Record");
        System.out.println("\t [2] - Display Employee Record");
        System.out.println("\t [3] - Remove Employee Record");
        //System.out.println("\t [4] - File Options");
        System.out.println("\t [4] - Exit Program");
        System.out.println("------------------------------------------------------------------");

        while (!isValidInput1) {
            try {
                System.out.print("Enter Choice : ");
                String input = inp.nextLine(); // String input

                // Input is empty checker
                if (input.isEmpty()) {
                    System.out.println();
                    throw new InvalidInputException("Input is empty");
                }

                choice = Integer.parseInt(input); // String to Integer

                if (choice <= 0 || choice > 4) {
                    System.out.println();
                    throw new InvalidInputException("Invalid option");
                }

                else{
                    isValidInput1 = true;
                }
            }

            catch (InvalidInputException e) {
                System.out.println("Please select one of these option: [ 1 | 2 | 3 | 4 | 5 ]");
                System.out.println("------------------------------------------------------------------");
            }

            catch (NumberFormatException e) {
                System.out.println();
                System.out.println("Invalid input format: Enter a valid number");
                System.out.println("Please select one of these option: [ 1 | 2 | 3 | 4 | 5 ]");
                System.out.println("------------------------------------------------------------------");
            }
        }

        isValidInput1 = false;

        switch(choice){
            case 1:{
                do {
                    addRecord();

                    while (!isValidInput2) {
                        try{
                            System.out.print("Add another employee? (YES/NO) : ");
                            ans = inp.nextLine();

                            if (ans.isEmpty()) {
                                System.out.println();
                                throw new InvalidInputException("Input is empty");
                            }

                            else if (!ans.equalsIgnoreCase("YES") && !ans.equalsIgnoreCase("NO")) {
                                System.out.println();
                                throw new InvalidInputException("Invalid input");
                            }

                            else {
                                isValidInput2 = true; // Set flag to true if input is valid
                            }
                        }

                        catch (InvalidInputException e) {
                            System.out.println("NOTE: Enter a valid option. Please enter 'YES' or 'NO'.");
                            System.out.println("------------------------------------------------------------------");
                        }
                    }

                    isValidInput2 = false;
                } while (ans.equalsIgnoreCase("YES"));

                break;
            }

            case 2:{
                printRecord();
                break;
            }

            case 3:{
                removeRecord();
                break;
            }

            case 4:{
                fileOption();
                break;
            }

            case 5:{
                exitOption();
                break;
            }

            default:{
                break;
            }
        }
    }//menu - main

    public static void addRecord(){
        System.out.println("\n------------------------------------------------------------------");
        System.out.println("                       POSITION CODES                             ");
        System.out.println("------------------------------------------------------------------");
        for(x=0; x <= 3; x++){
            System.out.println("\t ["+ posHolder[0][x] + "] - " + posHolder[1][x]);
        }
        System.out.println("------------------------------------------------------------------");

        while(!isValidInput1){
            try {
                System.out.print("Employee Code (000X) : ");
                posCode = inp.nextLine().toUpperCase();

                if(posCode.equals("011A") || posCode.equals("011B")
                        || posCode.equals("011C") || posCode.equals("011D")){
                    isValidInput1 = true;
                }

                else if(posCode.isEmpty()){
                    System.out.println();
                    throw new InvalidInputException("Input is empty");

                }

                else {
                    System.out.println();
                    throw new InvalidInputException("Invalid input");
                }
            }

            catch (InvalidInputException e) {
                System.out.println("NOTE: Enter a valid input");
                System.out.println("------------------------------------------------------------------");
            }
        }

        subCode = posCode.charAt(3);

        while(!isValidInput2){
            try {
                System.out.print("Status (HOF/SWD/SOD) : ");
                statCode = inp.nextLine().toUpperCase();

                if(statCode.equals("HOF") || statCode.equals("SWD") || statCode.equals("SOD")){
                    isValidInput2 = true;
                }

                else if(statCode.isEmpty()){
                    System.out.println();
                    throw new InvalidInputException("Input is empty");

                }

                else {
                    System.out.println();
                    throw new InvalidInputException("Invalid input");
                }
            }

            catch (InvalidInputException e) {
                System.out.println("NOTE: Enter a valid input");
                System.out.println("------------------------------------------------------------------");
            }
        }

        while(!nameIsEmpty){
            try {
                System.out.print("Employee Name    : ");
                empName = inp.nextLine();

                if(empName.isEmpty()){
                    System.out.println();
                    throw new InvalidInputException("Input is empty");
                }

                else{
                    for(x = 0; x<empName.length(); x++){
                        char eN = empName.charAt(x);

                        if(integers.indexOf(eN) >= 0){
                            containsInt = true;
                        }

                        else {
                            continue;
                        }//else
                    }//for

                    if(containsInt == true){
                        System.out.println();
                        containsInt = false;
                        throw new InvalidInputException("Name cannot contain a number");
                        //  System.out.println("Name cannot contain a number");
                    }

                    else {
                        nameIsEmpty = true;
                    }

                }
            }//try

            catch (InvalidInputException e) {
                System.out.println("NOTE: Enter a valid input");
                System.out.println("------------------------------------------------------------------");
            }
        }//nameIsEmpty

        while(!isValidInput3){
            try {
                System.out.print("Hours Worked     : ");
                String input = inp.nextLine(); // String input

                // Input is empty checker
                if (input.isEmpty()) {
                    System.out.println();
                    throw new InvalidInputException("Input is empty");
                }

                hoursWorked = Double.parseDouble(input); // String to Integer

                if(!(hoursWorked > 0.0)){
                    System.out.println();
                    throw new InvalidInputException("Invalid input");
                }

                else {
                    isValidInput3 = true;
                }
            }

            catch (InvalidInputException e) {
                System.out.println("NOTE: Enter a valid amount of hours");
                System.out.println("------------------------------------------------------------------");
            }

            catch (NumberFormatException e) {
                System.out.println();
                System.out.println("Invalid input format: Enter a valid number");
                System.out.println("NOTE: Enter a valid amount of hours");
                System.out.println("------------------------------------------------------------------");
            }
        }//isValidInput3

        //Counting the number of days present based on hours
        //presentNum = hoursWorked / 8.00;

        switch(subCode){
            case 'A':{
                hourlyRate = 290.00;
                empPos = posHolder[1][0];
                break;
            }

            case 'B': {
                hourlyRate = 148.00;
                empPos = posHolder[1][1];
                break;
            }

            case 'C': {
                hourlyRate = 159.00;
                empPos = posHolder[1][2];
                break;
            }

            case 'D': {
                hourlyRate = 165.00;
                empPos = posHolder[1][3];
                break;
            }

            default:{
                break;
            }
        }
        depCode = "Management Information Systems";
        //computeSalary(hoursWorked, hourlyRate);
        computeSalary();

        emp.add(new Employee(empName,posCode,empPos, statCode, depCode,hourlyRate,hoursWorked, regPay,otPay,taxRate,netPay));
        System.out.println("------------------------------------------------------------------");

        isValidInput1 = false;
        isValidInput2 = false;
        isValidInput3 = false;
        nameIsEmpty = false;
    }//addRecord - menu

    //public static double computeSalary(double hoursWorked, double hourlyRate){
    public static void computeSalary(){
        regPay= hoursWorked * hourlyRate;

        //with Overtime Pay (HOF/SWD/SOD)
        if(statCode.equals("HOF")){ //HOF: Head Of The Family
            taxRate = regPay * 0.05;
        }

        else if(statCode.equals("SOD")) { //SWD: Single With Dependent
            taxRate = regPay * 0.10;
        }

        else{ //SWD: Single Without Dependent
            taxRate = regPay * 0.15;
        }

        if(hoursWorked > regHours){
            otHours = hoursWorked - regHours;
            otPay =  otHours * hourlyRate * 1.5;
            netPay = (regPay + otPay) - taxRate;
        }

        else{
            otPay = 0.00;
            netPay = regPay - taxRate;
        }

        //return netPay;
    }//computeSalary - addRecord

    public static void printRecord(){
        if(emp.isEmpty()){
            System.out.println("NO EMPLOYEE RECORD FOUND...");
            System.out.println("------------------------------------------------------------------");
        }

        else{
            System.out.println("\n------------------------------------------------------------------");
            System.out.println("                   EMPLOYEE PAYROLL RECORD                        ");
            System.out.println("------------------------------------------------------------------");

            for(x =0; x<emp.size(); x++){
                System.out.println("EMPLOYEE NAME         : " + emp.get(x).empName);
                System.out.println("POSITION CODE         : " + emp.get(x).posCode);
                System.out.println("POSITION              : " + emp.get(x).empPos);
                System.out.println("STATUS                : " + emp.get(x).statCode);
                System.out.println("DEPARTMENT            : " + emp.get(x).depCode);
                System.out.println("PAY PER HOUR          : " + twodec.format(emp.get(x).hourlyRate));
                System.out.println("HOURS WORKED          : " + twodec.format(emp.get(x).hoursWorked));
                //System.out.println("PRESENT DAYS          : " + emp.get(x).presentNum);
                System.out.println("REGULAR PAY           : " + twodec.format(emp.get(x).regPay));
                System.out.println("OVERTIME PAY          : " + twodec.format(emp.get(x).otPay));
                System.out.println("TAX/DEDUCTIONS        : " + twodec.format(emp.get(x).taxRate));
                System.out.println("NET PAY               : " + twodec.format(emp.get(x).netPay));
                System.out.println("------------------------------------------------------------------");
            }
        }
    }//printRecord - menu

    public static void removeRecord(){
        //Status: Not yet finish
        do {
            if(emp.isEmpty()){
                System.out.println("NO EMPLOYEE RECORD FOUND...");
                System.out.println("NOTE: ADD EMPLOYEE RECORD FIRST");
                System.out.println("------------------------------------------------------------------");
                break;
            }

            else {
                System.out.println("\n------------------------------------------------------------------");
                System.out.println("                LIST OF EXISTING EMPLOYEES                        ");
                System.out.println("------------------------------------------------------------------");
                System.out.println("NAME POSITION");
                System.out.println("------------------------------------------------------------------");
                System.out.println("NUMBER \t\tNAME \t\tPOSITION");

                for(x=0; x<emp.size(); x++){
                    System.out.println((x+1) + ". " + emp.get(x).posCode + "\t\t"+ emp.get(x).empName + "\t\t" + emp.get(x).empPos);
                }

                System.out.println("------------------------------------------------------------------");
                System.out.println("ENTER 'EXIT' TO CANCEL...");
                System.out.println("NOTE: Please enter the full name of the record you wan't to remove");
                System.out.println("------------------------------------------------------------------");

                while (!isValidInput1) {
                    try{
                        System.out.print("Name to Remove : ");
                        remName = inp.nextLine();

                        if(remName.isEmpty()){
                            System.out.println();
                            throw new InvalidInputException("Input is empty");
                        }

                        else if(remName.equalsIgnoreCase("EXIT")){
                            System.out.println();
                            exitOption();
                            isValidInput1 = true;
                            isValidInput2 = true;
                            continue;
                        }

                        else {
                            for(x = 0; x < emp.size(); x++){
                                if(remName.contains(emp.get(x).empName)){
                                    i = x;
                                    emp.remove(i);
                                    nameIsFound = true;
                                    isValidInput1 = true;
                                }
                            }
                        }

                        if(!nameIsFound){
                            System.out.println();
                            System.out.println("ERROR: NAME NOT FOUND...");
                            System.out.println("NOTE: Enter a name that is within the records.");
                            System.out.println("------------------------------------------------------------------");
                        }

                        else{
                            System.out.println();
                            System.out.println("SUCCESSFULLY REMOVED...");
                            System.out.println("------------------------------------------------------------------");
                        }
                    }

                    catch (InvalidInputException e) {
                        System.out.println("NOTE: Enter a name that is within the records.");
                        System.out.println("------------------------------------------------------------------");
                    }
                }

                isValidInput1 = false;
                nameIsFound = false;

                while (!isValidInput2) {
                    try{
                        System.out.print("Remove another employee? (YES/NO) : ");
                        ans = inp.nextLine();

                        if (ans.isEmpty()) {
                            System.out.println();
                            throw new InvalidInputException("Input is empty");
                        }

                        else if (!ans.equalsIgnoreCase("YES") && !ans.equalsIgnoreCase("NO")) {
                            System.out.println();
                            throw new InvalidInputException("Invalid input");
                        }

                        else {
                            isValidInput2 = true;
                        }
                    }

                    catch (InvalidInputException e) {
                        System.out.println("NOTE: Enter a valid option. Please enter 'YES' or 'NO'.");
                        System.out.println("------------------------------------------------------------------");
                    }
                }

                isValidInput2 = false;
            }
        }while (ans.equalsIgnoreCase("YES"));
    }//removeRecord - menu

    public static void fileOption(){
        //NOTE: This includes all option for File Handling for organization

        do{
            System.out.println("\n------------------------------------------------------------------");
            System.out.println("                     FILE OPTIONS MENU                        ");
            System.out.println("------------------------------------------------------------------");
            System.out.println("******************************************************************");
            System.out.println("               Select from the Following Options                   ");
            System.out.println("******************************************************************");
            System.out.println("------------------------------------------------------------------");
            System.out.println("\t [1] - Create New File");
            System.out.println("\t [2] - Display File Content");
            System.out.println("\t [3] - Check for Existing File/s");
            System.out.println("\t [4] - Exit / Main Menu");
            System.out.println("------------------------------------------------------------------");

            while (!isValidInput1) {
                try {
                    System.out.print("Enter option : ");
                    String input = inp.nextLine(); // String input

                    // Input is empty checker
                    if (input.isEmpty()) {
                        System.out.println();
                        throw new InvalidInputException("Input is empty");
                    }

                    option = Integer.parseInt(input); // String to Integer

                    if (option <= 0 || option > 4) {
                        System.out.println();
                        throw new InvalidInputException("Invalid option");
                    }

                    else{
                        isValidInput1 = true;
                    }
                }

                catch (InvalidInputException e) {
                    System.out.println("Please select one of these option: [ 1 | 2 | 3 | 4 ]");
                    System.out.println("------------------------------------------------------------------");
                }

                catch (NumberFormatException e) {
                    System.out.println();
                    System.out.println("Invalid input format: Enter a valid number");
                    System.out.println("Please select one of these option: [ 1 | 2 | 3 | 4 ]");
                    System.out.println("------------------------------------------------------------------");
                }
            }

            isValidInput1 = false;

            switch(option){
                case 1:{
                    if(emp.isEmpty()){
                        System.out.println("------------------------------------------------------------------");
                        System.out.println("ERROR: CANNOT GENERATE A FILE");
                        System.out.println("NOTE: NO EMPLOYEE RECORD FOUND...");
                        System.out.println("------------------------------------------------------------------");
                    }

                    else {
                        System.out.println("------------------------------------------------------------------");

                        while(!isValidName2){
                            try{
                                System.out.print("Enter a File Name : ");
                                fileName = inp.nextLine();

                                if(!(fileName.isEmpty())){
                                    isValidName2 = true;
                                }

                                else {
                                    System.out.println();
                                    throw new InvalidInputException("FILE NAME CANNOT BE EMPTY");
                                }
                            }

                            catch (InvalidInputException e) {
                                System.out.println("NOTE: Enter a valid input");
                                System.out.println("------------------------------------------------------------------");
                            }
                        }

                        for(x = 0; x < fileList.size(); x++) {
                            if (fileName.equals(fileList.get(x))) {
                                System.out.println("------------------------------------------------------------------");
                                System.out.println("NOTICE: FILE ALREADY EXIST");
                                System.out.println("NOTE: FILE'S CONTENT WILL NOW BE OVERWRITE...");
                                fileList.remove(x);
                            }
                        }//for
                        fileName1 = "C:/Users/Owner/Desktop/Payroll_Records/" + fileName + ".txt";
                        fileList.add(fileName);
                        writeFile(fileName, fileName1);
                        System.out.println("------------------------------------------------------------------");
                        System.out.println("--FILE SUCCESSFULLY CREATED--");
                        System.out.println("File Name     : " + fileName);
                        System.out.println("File Location : " + fileName1);
                        System.out.println("File Type     : Text Document (.txt)" );
                        System.out.println("------------------------------------------------------------------");
                        isValidName2 = false;
                    }//else
                    break;
                }

                case 2:{
                    readFile();
                    break;
                }

                case 3:{
                    checkFile();
                    break;
                }

                case 4:{
                    System.out.println();
                    exitOption(); //Will end the file option method and will go at the end of menu
                    continue;
                }

                default:{
                    break;
                }
            }//switch

            while (!isValidInput2) {
                try{
                    System.out.print("Go back to file option? (YES/NO) : ");
                    ans = inp.nextLine();

                    if (ans.isEmpty()) {
                        System.out.println();
                        throw new InvalidInputException("Input is empty");
                    }

                    else if (!ans.equalsIgnoreCase("YES") && !ans.equalsIgnoreCase("NO")) {
                        System.out.println();
                        throw new InvalidInputException("Invalid input");
                    }

                    else {
                        isValidInput2 = true;
                    }
                }

                catch (InvalidInputException e) {
                    System.out.println("NOTE: Enter a valid option. Please enter 'YES' or 'NO'.");
                    System.out.println("------------------------------------------------------------------");
                }
            }

            isValidInput2 = false;
        }while(ans.equalsIgnoreCase("YES"));
    }//fileOption - menu

    public static void writeFile(){
        //C:/Users/Owner/Desktop/Payroll_Records/fileName.txt

        try{
            FileWriter writer = new FileWriter("C:/Users/Owner/Desktop/Payroll_Records/empRecord.txt");
            writer.write("------------------------------------------------------------------");
            writer.write("\n                   EMPLOYEE PAYROLL RECORD                        ");
            writer.write("\n------------------------------------------------------------------");

            for(x=0; x<emp.size(); x++){
                writer.write("\nEMPLOYEE NAME         : " + emp.get(x).empName);
                writer.write("\nPOSITION CODE         : " + emp.get(x).posCode);
                writer.write("\nPOSITION              : " + emp.get(x).empPos);
                writer.write("\nSTATUS                : " + emp.get(x).statCode);
                writer.write("\nDEPARTMENT            : " + emp.get(x).depCode);
                writer.write("\nPAY PER HOUR          : " + twodec.format(emp.get(x).hourlyRate));
                writer.write("\nHOURS WORKED          : " + twodec.format(emp.get(x).hoursWorked));
                //writer.write("\nPRESENT DAYS          : " + emp.get(x).presentNum);
                writer.write("\nREGULAR PAY           : " + twodec.format(emp.get(x).regPay));
                writer.write("\nOVERTIME PAY          : " + twodec.format(emp.get(x).otPay));
                writer.write("\nTAX/DEDUCTIONS        : " + twodec.format(emp.get(x).taxRate));
                writer.write("\nNET PAY               : " + twodec.format(emp.get(x).netPay));
                writer.write("\n------------------------------------------------------------------");
            }
            writer.write("\n                  FILE DOCUMENT PROPERTIES                        ");
            writer.write("\n------------------------------------------------------------------");
            writer.write("\nFile Name     : empRecord.txt");
            writer.write("\nFile Location : C:/Users/Owner/Desktop/Payroll_Records");
            writer.write("\nFile Type     : Text Document (.txt)");
            writer.write("\n------------------------------------------------------------------");
            writer.close();
        }
        catch (IOException e) {
            System.out.println("An error occured");
        }
    }//writeFile - fileOption

    public static void checkFile(){

        if(fileList.isEmpty()){
            System.out.println();
            System.out.println("------------------------------------------------------------------");
            System.out.println("ERROR: LIST IS EMPTY");
            System.out.println("NOTE: CREATE A NEW FILE...");
        }

        else {
            System.out.println("------------------------------------------------------------------");
            System.out.println("FILE LIST RECORD:");

            for (x = 0; x < fileList.size(); x++) {
                System.out.println("\t" + (x + 1) + ". " + fileList.get(x));
            }
        }

        System.out.println("------------------------------------------------------------------");

        //since the file is already created from createFile() method
        //this does not create a new file, it just checks if the fileName exist already
        File file = new File(fileName1);

        //check if the file exist
        if(file.exists()){
            //return the name & path of file
            //return the file size
            System.out.println("--MOST RECENT FILE--");
            System.out.println("File Name     : " + file.getName());
            System.out.println("File Location : " + file.getAbsolutePath());
            System.out.println("File Size     : " + file.length()  + " bytes" );
            System.out.println("File Type     : Text Document (.txt)" );
            System.out.println("------------------------------------------------------------------");
            fileOption();
        }

        else {
            System.out.println();
            System.out.println("NO EXISTING FILE...");
            System.out.println("------------------------------------------------------------------");
        }

    }//checkFile - fileOption

    public static void readFile() {

        if (fileList.isEmpty()) {
            System.out.println("------------------------------------------------------------------");
            System.out.println("NO EXISTING FILE...");
            System.out.println("------------------------------------------------------------------");
        } else {
            System.out.println();

            try {
                File file = new File(fileName1);
                Scanner reader = new Scanner(file);

                while (reader.hasNextLine()) {
                    System.out.println(reader.nextLine());
                }
                reader.close();
            } catch (FileNotFoundException e) {
                System.out.println("File not found");
            }
        }
    }//readFile()//readFile - fileOption
    public static void exitOption(){
        do {
            try {
                System.out.println("------------------------------------------------------------------");
                System.out.print("Do you want to exit the program? (YES/NO) : ");
                ans = inp.nextLine();

                if (ans.isEmpty()) {
                    System.out.println();
                    throw new InvalidInputException("Input is empty");
                }

                else if (!ans.equalsIgnoreCase("YES") && !ans.equalsIgnoreCase("NO")) {
                    throw new InvalidInputException("Invalid option");
                }

                else{
                    if (ans.equalsIgnoreCase("YES")) {
                        System.out.print("Are you sure? (YES/NO) : ");
                        ans = inp.nextLine();
                        if (!ans.equalsIgnoreCase("YES") && !ans.equalsIgnoreCase("NO")) {
                            throw new InvalidInputException("Invalid option");
                        }

                        else if (ans.equalsIgnoreCase("YES")) {
                            System.out.println("Exiting the program...");
                            System.exit(0);
                        }

                        else if (ans.equalsIgnoreCase("NO")) {
                            exitOption();
                        }
                    }

                    else{
                        System.out.println("Returning to main menu...");
                        menu();
                    }
                }

            }

            catch (InvalidInputException e) {
                System.out.println("NOTE: Enter a valid option. Please enter 'YES' or 'NO'.");
                System.out.println("------------------------------------------------------------------");
            }

        } while (!ans.equalsIgnoreCase("No"));
    }
}//class
