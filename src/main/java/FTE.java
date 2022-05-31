// FULL TIME EMPLOYEE
public class FTE extends EmployeeInfo {

    //ATTRIBUTES
    public double yearlySalary;

    //CONSTRUCTORS
    public FTE(int eN, String fN, String lN, int g, int wL, double dR, double yS) {
        
        super(eN, fN, lN, g, wL, dR);
        yearlySalary = yS;
        
    }

    //METHODS
    public void modifyFTE(double newYS) {//Edits employee to updated info specifically for the PTE subclass

        this.yearlySalary = newYS;

    }
    
    public double getYearlySalary() {
        return yearlySalary;
    }
    
}
