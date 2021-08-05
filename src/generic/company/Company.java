package generic.company;

import generic.company.employees.Employee;
import generic.company.employees.ITSpecialist;

import java.util.ArrayList;
import java.util.List;

public class Company {
    private List<Departments> departments = new ArrayList<>();
    private List<Employee> employees = new ArrayList<>();

    public <T extends Employee> void addDepartment(Departments<T> dep)
    {
        departments.add(dep);
        List<T> empList = dep.getEmployees();
        giveRise(empList);
        this.employees.addAll(empList);
    }

                                                            // граничения сверху (wildcard)
    private void giveRise(List<? extends Employee> listEmp) //любые типы, которые наследуют Employee
    {  // тут хранится ссылка на объект, поэтому можно не возвращать значение
        for (Employee e : listEmp)
        {
            Float rise = e.getSalary() * 0.2f;
            e.setSalary(e.getSalary()+rise);
        }
    }

    protected void print(List<?> listEmp) //без ограничения (wildcard)
    {
        System.out.println("Size " + listEmp.size());
        System.out.println(listEmp);
    }
                                                                    // (wildcard)
    public void addToITemployees(List<? super ITSpecialist> list_m) //граниечение снизу, те можно передать лист типа ITSpecialist
                                                             // или типа, котокрый я вляется родителем для ITSpecialist
    {
        list_m.add(new ITSpecialist("Joe", 3500.0f));
    }

    public List<Departments> getDepartments() {
        return this.departments;
    }

    public List<Employee> getEmployees() {
        return employees;
    }

    @Override
    public String toString() {
        return "Company{" +
                "departments=\n" + departments +
                ", \nemployees=" + employees +
                '}';
    }
}
