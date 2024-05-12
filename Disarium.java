//WAP to check if a number is Disarium or not
import java.util.Scanner;
class Disarium
{
    int num;
    int size;
    Disarium(int nn)
    {
        num=nn;
        size=0;
    }
    void countdigit()
    {
        //int len = (""+num).length();
        int len=0,temp=num;
        while(temp!=0)
        {
            temp=temp/10;
            len++;
        }
        size=len;
    }
    int sumofdigits(int n,int p)
    {
        if(n==0)
        {
            return 0;
        }
        else
        {
            return (int)Math.pow((n%10),p) + sumofdigits((n/10),p-1);
        }
    }
    void check()
    {
        if(num == sumofdigits(num,size))
        {
            System.out.println("Number is Disarium");
        }
        else
        {
            System.out.println("Number is not Disarium");
        }
    }
    public static void main(String args[])
    {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter a number");
        int n = sc.nextInt();
        Disarium obj = new Disarium(n);
        obj.countdigit();
        obj.check();
    }
}