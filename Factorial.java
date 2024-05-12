//WAP to find factorial of entered number
import java.util.Scanner;
class Factorial
{
    Scanner sc = new Scanner(System.in);
    int n = sc.nextInt();
    int i=1,fact=1;
    int cal()
    {
        fact=fact*i;
        i++;
        if(i<=n)
        {
            cal();
        }
        return fact;
    }
    public static void main(String args[])
    {
        Factorial co = new Factorial();
        int f=co.cal();
        System.out.println("Factorial of is:"+f);
    }
}