package generic.company;

import generic.company.employees.Employee;

import java.util.ArrayList;
import java.util.List;

public class Departments<T extends Employee> {
    private String name;
    private int employeeNumber;
    private List<T> employees = new ArrayList<>();

    public Departments(String name, int employeeNumber) {
        this.name = name;
        this.employeeNumber = employeeNumber;
    }

    public boolean addEmployee(T emp)
    {
        return employees.add(emp);
    }

    public List<T> getEmployees() {
        return employees;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getEmployeeNumber() {
        return employeeNumber;
    }

    public void setEmployeeNumber(int employeeNumber) {
        this.employeeNumber = employeeNumber;
    }

    @Override
    public String toString() {
        return "\nDepartments{" +
                "name='" + name + '\'' +
                ", employeeNumber=" + employeeNumber +
                //", employees=" + employees +
                "}";
    }
}
