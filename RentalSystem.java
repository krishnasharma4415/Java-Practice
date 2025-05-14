interface Vehicle 
{
    int rentalDuration();
    double hourlyRate();
    double calculateTotal();
}

class Car implements Vehicle 
{
    private int hours;
    private double ratePerHour;

    Car(int hours, double ratePerHour) 
    {
        this.hours = hours;
        this.ratePerHour = ratePerHour;
    }

    public int rentalDuration() 
    {
        return hours;
    }

    public double hourlyRate() 
    {
        return ratePerHour;
    }

    public double calculateTotal() 
    {
        return hours * ratePerHour;
    }
}

class Bike implements Vehicle 
{
    private int hours;
    private double ratePerHour;

    Bike(int hours, double ratePerHour) 
    {
        this.hours = hours;
        this.ratePerHour = ratePerHour;
    }

    public int rentalDuration() 
    {
        return hours;
    }

    public double hourlyRate() 
    {
        return ratePerHour;
    }

    public double calculateTotal() 
    {
        return hours * ratePerHour;
    }
}

public class RentalSystem 
{
    public static void main(String[] args) 
    {
        Vehicle vehicle = new Car(5, 20.0);
        System.out.println("Car Rental:");
        System.out.println("Hours Rented: " + vehicle.rentalDuration());
        System.out.println("Cost per Hour: $" + vehicle.hourlyRate());
        System.out.println("Total Rental Amount: $" + vehicle.calculateTotal());

        Vehicle bike = new Bike(3, 10.0);
        System.out.println("Bike Rental:");
        System.out.println("Hours Rented: " + bike.rentalDuration());
        System.out.println("Cost per Hour: $" + bike.hourlyRate());
        System.out.println("Total Rental Amount: $" + bike.calculateTotal());
    }    
}