package application;

public class Student {
    private String firstName;
    private String lastName;

    public Student(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }
   
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String first) {
        this.firstName = first;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String last) {
        this.lastName = last;
    }
    
    public String getFullName() {
    	return this.lastName + ", " + this.firstName;
    }
}