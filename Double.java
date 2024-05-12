//WAP count the number of double letter sequence
import java.util.Scanner;
class Double
{
    public static void main(String args[])
    {
        Scanner sc = new Scanner(System.in);
        String str = sc.nextLine();
        char ch1,ch2;
        int d=0;
        for(int i=0;i<str.length()-1;i++)
        {
            ch1=str.charAt(i);
            ch2=str.charAt(i+1);
            if(ch1==ch2)
            {
                d++;
            }
        }
        System.out.println(+d);
    }
}