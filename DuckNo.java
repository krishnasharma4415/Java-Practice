//Write a program to check whether a number is Duck number or not
import java.util.Scanner;
class DuckNo
{
    public static void main(String args[])
    {
        Scanner sc = new Scanner(System.in);
        int num,digit,flag=0;
        num=sc.nextInt();
        while(num>0)
        {
            digit=num%10;
            num=num/10;
            if(digit==0)
            {
                flag=1;
            }
        }
        if(flag==1)
        {
            System.out.println("DUCK NUMBER");
        }
        else
        {
            System.out.println("NOT DUCK NUMBER");
        }
    }
}