//WAP to count number of uppercase,lowercase & vowels
import java.util.Scanner;
class Count
{
    public static void main(String args[])
    {
        System.out.println("Enter a String");
        Scanner sc = new Scanner(System.in);
        String str = sc.nextLine();
        char ch; int count1=0,count2=0,count3=0;
        for(int i=0;i<str.length();i++)
        {
            ch=str.charAt(i);
            if(ch=='a'||ch=='e'||ch=='i'||ch=='o'||ch=='u'||ch=='A'||ch=='E'||ch=='I'||ch=='O'||ch=='U')
            {
                count1++;
            }
            if(ch>='A'&&ch<='Z')
            {
                count2++;
            }
            if(ch>='a'&&ch<='z')
            {
                count3++;
            }
        }
        System.out.println("No. of UpperCase Characters:"+count2);
        System.out.println("No. of LowerCase Characters:"+count3);
        System.out.println("No. of Vowels:"+count1);
    }
}