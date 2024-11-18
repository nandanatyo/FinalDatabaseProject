package internal.model;

public class Employee {
    private int numberOfEmployees;
    private int year;

    public Employee(int numberOfEmployees, int year) {
        this.numberOfEmployees = numberOfEmployees;
        this.year = year;
    }

    public int getNumberOfEmployees() {
        return numberOfEmployees;
    }

    public int getYear() {
        return year;
    }
}
