package application;

public class Log {
    private String firstName;
    private String lastName;
    private String swipeTime;
    private String swipeDate;

    public Log(String first, String last, String time, String date) {
        firstName = first;
        lastName = last;
        swipeTime = time;
        swipeDate = date;
    }
  
    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getSwipeTime() {
        return swipeTime;
    }

    public String getSwipeDate() {
        return swipeDate;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setSwipeTime(String swipeTime) {
        this.swipeTime = swipeTime;
    }

    public void setSwipeDate(String swipeDate) {
        this.swipeDate = swipeDate;
    }
    
    public String getFullName() {
    	return this.lastName + ", " + this.firstName;
    }
    
    public String getTimeAndDate() {
    	return this.swipeTime.replaceAll("\\s+","") + ", " + this.swipeDate.replaceAll("\\s+","");
    }
    
    public String getPrint() {
    	return this.getFullName() + ", " + this.getTimeAndDate();
    }
}
