// PART TIME EMPLOYEE
public class PTE extends EmployeeInfo {

    //ATTRIBUTES
    public double hourlyWage;
    public double hoursPerWeek;
    public double weeksPerYear;

    //CONSTRUCTORS
    public PTE(int eN, String fN, String lN, int g, int wL, double dR, double hW, double hPW, double wPY) {
        super(eN, fN, lN, g, wL, dR);
        hourlyWage = hW;
        hoursPerWeek = hPW;
        weeksPerYear = wPY;

    }
    
    //METHODS
    public void modifyPTE(double newHW, double newHPW, double newWPY){//Edits employee to updated info specifically for the PTE subclass
        
        this.hourlyWage = newHW;
        this.hoursPerWeek = newHPW;
        this.weeksPerYear = newWPY;
    }
    
    public double getHourWage() {
        return hourlyWage;
    }

    public double getHourPerWeek() {
        return hoursPerWeek;
    }

    public double getWeekPerYear() {
        return weeksPerYear;
    }

}
