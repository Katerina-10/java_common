package generic.company;

import generic.company.employees.Accountant;
import generic.company.employees.ITSpecialist;
import generic.company.employees.Manager;

public class CompanyMain {
    public static void main(String[] args)
    {
        Company comp = new Company();

        Departments<Accountant> accounting = new Departments<>("Accounting", 5);
        Departments<Manager> management = new Departments<>("Management",2);
        Departments<ITSpecialist> itGuys = new Departments<>("ITspec", 10);

        Accountant accountant = new Accountant("Alex", 1000.0f);
        ITSpecialist it = new ITSpecialist("James", 2000.0f);
        Manager manager = new Manager("Michail", 4000.0f);


        accounting.addEmployee(accountant);
        //accounting.addEmployee(it); //это ошибка, accounting параметризован типом Accountant (если без параметра, то компилятор ошибку не выдает)
        itGuys.addEmployee(it);
        management.addEmployee(manager);


        comp.addDepartment(accounting);
        comp.addDepartment(itGuys);
        comp.addDepartment(management);


        //System.out.println(comp);
        comp.print(comp.getDepartments());
        comp.print(comp.getEmployees());

        comp.addToITemployees(itGuys.getEmployees());
        comp.addToITemployees(comp.getEmployees());

        comp.print(comp.getDepartments());
        comp.print(comp.getEmployees());

        String str = new String("ddd fff rrr eee aaa");
        System.out.println(str);
        str = str.replace("ddd fff rrr eee aaa", "fghfh");
        System.out.println(str);

        String newStr ="";
        for (int i = 0; i < str.length(); i++) //реверс строки
        {
            newStr = str.charAt(i) + newStr;
        }
        System.out.println(newStr);



    }
}
