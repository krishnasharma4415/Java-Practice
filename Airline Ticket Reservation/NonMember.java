 

public class NonMember extends Passenger 
{
    public NonMember(int a, String n) {
        super(a, n);
    }

    @Override
    public double applyDiscount(double p) {
        if (age > 65) {
            p = (p *9)/10;
            return p;
        }
        return p;
    }

}
