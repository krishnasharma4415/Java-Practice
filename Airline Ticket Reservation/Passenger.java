 

public abstract class Passenger 
{

    String name;
    int age;

    public Passenger(int a, String n) {
        name = n;
        age = a;

    }
    public String getName(){
        return name;
    }
    public int getAge(){
        return age;
    }
    abstract public double applyDiscount(double p);

}