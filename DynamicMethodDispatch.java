class Employee 
{
    String name;
    int basePay;

    Employee(String name, int basePay) 
    {
        this.name = name;
        this.basePay = basePay;
    }

    int calculateSalary()
    {
        return basePay;
    }
}

class Manager extends Employee
{
    int bonus;

    Manager(String name, int basePay, int bonus)
    {
        super(name, basePay);
        this.bonus = bonus;
    }

    int calculateSalary()
    {
        return basePay + bonus;
    }
}

class Programmer extends Employee
{
    int overtimePay;

    Programmer(String name, int basePay, int overtimePay) 
    {
        super(name, basePay);
        this.overtimePay = overtimePay;
    }

    int calculateSalary()
    {
        return basePay + overtimePay;
    }
}

public class DynamicMethodDispatch
{
    public static void main(String[] args) 
    {
        Employee emp;

        emp = new Manager("Krishna Sharma", 60000, 10000);
        System.out.println("Manager Salary: " + emp.calculateSalary());

        emp = new Programmer("Nafisa Rehmani", 50000, 5000);
        System.out.println("Programmer Salary: " + emp.calculateSalary());

        emp = new Programmer("Sonu Sarojini", 50000, 5000);
        System.out.println("Programmer Salary: " + emp.calculateSalary());
    }
}