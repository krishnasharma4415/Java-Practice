//WAP to print factor of a number
import java.util.Scanner;
class Factors
{
    public static void main(String args[])
    {
        Scanner sc = new Scanner(System.in);
        int num,digit;
        num = sc.nextInt();
        for(int i=1;i<=num;i++)
        {
            if(num%i==0)
            {
                System.out.print(" "+i);
            }
        }
    }
}