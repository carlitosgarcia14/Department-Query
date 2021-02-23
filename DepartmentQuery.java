// Carlos Garcia
// CSC 350-001
// This program queries a .csv file in user input then finds the departments and the frequency 
// of employee's in said department. It then prints each department and the number of employees 
// in it. The program throws an I/O exception if an error occurs with the file reader.

import java.lang.reflect.Array;
import java.util.*;
import java.io.*;

public class DepartmentQuery {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the name of the file to query: ");
        String fileName = scanner.next(); // reads fileName from user input to query
        scanner.close();

        try {
            BufferedReader csvReader = new BufferedReader(new FileReader(fileName)); // file reader for .csv files
            String line; // contains a line of string for each line in the .csv file
            List<String[]> records = new ArrayList<String[]>(); // stores an array of strings for each employee in a ArrayList
            int deptIndex = 0; // sets the index for the column of department codes

            while ((line = csvReader.readLine()) != null) { // reads each line of the .csv file until the end of the file
                String[] emoloyees = line.split(","); // stores each line of .csv file into an array of strings
                records.add(emoloyees);
            }
            csvReader.close();

            for (String columnLabel : records.get(0)) { // takes the first record of employees to find department index
                if (columnLabel.equals("Department")) { // checks each label until if finds the one equal to Department
                    break; // breaks loop once Department label is found
                }
                deptIndex++; // increases counter for Department index until it is found
            }

            Set deptCodes = new HashSet<>(); // stores the department codes in a HashSet (no duplicates)
            ArrayList deptCounter = new ArrayList<>(); // stores the deparment for each line of the .csv in an ArrayList (duplicates)
            for (String[] employee : records) {  // takes each employee array and adds each employee department to the HashSet and ArrayList
                if (Array.get(employee, deptIndex).equals("Department")) { // ensures that Department label is not included
                    continue;                                              // in neither the HashSet or ArrayList
                }
                deptCodes.add(Array.get(employee, deptIndex));
                deptCounter.add(Array.get(employee, deptIndex));
            }

            for (Object dept : deptCodes) { // loops through every department code and prints out the number of employees in it
                System.out.println("The number of employees in the " + dept.toString() + " department is " 
                + Collections.frequency(deptCounter, dept) + "."); // number of department employees is found by
            }                                                      // finding the frequency of each department stored in the ArrayList
        } catch (IOException ex) { // catches any I/O exception that may occur when reading the file
            System.out.println("File was not found. Please re-run the program and try again.");
        }
    }
}