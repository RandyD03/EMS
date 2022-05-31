import java.util.*;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class MyHashTable {

    // ATTRIBUTES
    // Buckets is an array of ArrayList.  Each item in an ArrayList is a StudentInfo
    // Object holding a reference value pointing to a student.
    private ArrayList<EmployeeInfo>[] buckets;
    private int numInTable;
    String[][] data;

    // CONSTRUCTORS
    public MyHashTable(int howManyBuckets) {
        // Construct the hash table (open hashing/closed addressing) as an array of howManyBuckets ArrayLists.
        // Instantiate buckets as an array to have an ArrayList as each element of the array.
        buckets = new ArrayList[howManyBuckets];
        numInTable = 0;
        // For each element in the array, instantiate its ArrayList.
        for (int i = 0; i < howManyBuckets; i++) {
            buckets[i] = new ArrayList();  // Instantiate the ArrayList for bucket i.
        }
    }

    // METHODS
    public int getNumInTable() {
        return numInTable;
    }
    
    public int calcBucket(int theEmployee) {
        //Returns designated bucket
        return (theEmployee % buckets.length);
    }

    public void saveToFile(String location) {
        //Writes hashtable data onto a specified text file
        try {
            File archive = new File(location);

            if (!archive.exists()) {
                archive.createNewFile();//Archive file will be created if not already
            }

            FileWriter fw = new FileWriter(archive);
            BufferedWriter bw = new BufferedWriter(fw);

            for (int i = 0; i < buckets.length; i++) {
                for (int n = 0; n < buckets[i].size(); n++) {
                    //The employee currently being saved
                    EmployeeInfo theEmp = buckets[i].get(n);
                    
                    //Detection for Employee Inheritance
                    if (theEmp instanceof PTE) {//Checks if item is PTE(0)
                        bw.write("PTE");
                        bw.write("^");
                    } else {
                        if (theEmp instanceof FTE) {//Checks if item is FTE(1)
                            bw.write("FTE");
                            bw.write("^");
                        }
                    }

                    bw.write(Integer.toString(theEmp.getEmpNum()));
                    bw.write("^");
                    bw.write(theEmp.getFirstName());
                    bw.write("^");
                    bw.write(theEmp.getLastName());
                    bw.write("^");
                    bw.write(Integer.toString(theEmp.getEmpGender()));
                    bw.write("^");
                    bw.write(Integer.toString(theEmp.getEmpWorkLoc()));
                    bw.write("^");
                    bw.write(Double.toString(theEmp.getEmpDeductRate()));
                    bw.write("^");

                    //Detection for Employee Inheritance-Writes respective subclass info
                    if (theEmp instanceof FTE) {//Checks if item is FTE(0)
                        FTE theFTE = (FTE) theEmp;
                        bw.write(Double.toString(theFTE.yearlySalary));
                        
                    } else {
                        if (theEmp instanceof PTE) {//Checks if item is PTE(1)
                            PTE thePTE = (PTE) theEmp;
                            bw.write(Double.toString(thePTE.hourlyWage));
                            bw.write("^");
                            bw.write(Double.toString(thePTE.hoursPerWeek));
                            bw.write("^");
                            bw.write(Double.toString(thePTE.weeksPerYear));
                        }
                    }
                    bw.newLine();
                }
            }
            bw.write("#");//End detection
            System.out.println("Saved HT to archive file.");
            bw.close();
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }

    }

    public String[][] displayEmployees() {
        //Creates 2d array of emp data
        if (numInTable > 0) {//check if there are employees in the table
            System.out.println("Here are the employees:");
            
            data = new String[numInTable][4];
            int i = 0;
            for (ArrayList<EmployeeInfo> bucket : buckets) {
                for (int n = 0; n < bucket.size(); n++) {
                    EmployeeInfo theEmp = bucket.get(n);
                    System.out.println("  Employee number " + Integer.toString(theEmp.getEmpNum()));
                    System.out.println("  First name, last name : " + theEmp.getFirstName() + " " + theEmp.getLastName());
                    System.out.println("Gender:" + theEmp.getEmpGender());
                    System.out.println("Work Location:" + theEmp.getEmpWorkLoc());
                    System.out.println("Deduction Rate:" + theEmp.getEmpDeductRate());                    
                    if (theEmp instanceof FTE) {
                        FTE theFTE = (FTE) theEmp;
                        System.out.println("That employee has yearly salary $" + Double.toString(theFTE.getYearlySalary()));
                        
                        //Populating Display Employee table
                        data[i][0] = Integer.toString(theEmp.getEmpNum());
                        data[i][1] = "FTE";
                        data[i][2] = theEmp.getFirstName();
                        data[i][3] = theEmp.getLastName();
                        i++;
                    }
                    if (theEmp instanceof PTE) {
                        PTE thePTE = (PTE) theEmp;
                        System.out.println("    That employee has hourly wage $" + Double.toString(thePTE.getHourWage()));
                        System.out.println("    That employee has hours per week " + Double.toString(thePTE.getHourPerWeek()));
                        System.out.println("    That employee has weeks per year " + Double.toString(thePTE.getWeekPerYear()));
                        
                        //Populating Display Employee table
                        data[i][0] = Integer.toString(theEmp.getEmpNum());
                        data[i][1] = "PTE";
                        data[i][2] = theEmp.getFirstName();
                        data[i][3] = theEmp.getLastName();
                        i++;
                    } else {
                        System.out.println("Nothing in the HT!");
                    }
                }
            }
        }
        return data;
    }

    public void addToTable(EmployeeInfo theEmployee) {
        // Add the student referenced by theStudent to the hash table.
        if (theEmployee.getEmpNum() > 0) {

            int targetBucket = calcBucket(theEmployee.getEmpNum());
            buckets[targetBucket].add(theEmployee);
            numInTable++;
        }
    }

    public EmployeeInfo searchFromTable(int theEmployee) {
        // Return the reference value for student searched with employee number.
        // Return null if that student isn't in the table.

        int targetBucket = calcBucket(theEmployee);

        for (int n = 0; n < buckets[targetBucket].size(); n++) {
            if (buckets[targetBucket].get(n).getEmpNum() == theEmployee) {//removes employee
                EmployeeInfo locatedEmp = buckets[targetBucket].get(n);
                System.out.println("The reference Value is:" + locatedEmp + "     located in bucket:" + targetBucket + "in position" + n);
                return locatedEmp;
            }
        }
        return null;
    }
    
    
    
        public EmployeeInfo removeFromTable(int theEmployee) {
        // Remove the emp if found
        // Return null if that emp isn't in the table.
        
        int targetBucket = calcBucket(theEmployee);

        for (int n = 0; n < buckets[targetBucket].size(); n++) {
            if (buckets[targetBucket].get(n).getEmpNum() == theEmployee) {//removes employee
                EmployeeInfo locatedEmp = buckets[targetBucket].get(n);
                buckets[targetBucket].remove(n);
                numInTable--;
                return locatedEmp;
            }
        }
        return null;
    }
}
