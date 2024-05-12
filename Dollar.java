//WAP to replace vowels of a string to dollar($)
import java.util.Scanner;
class Dollar
{
    public static void main(String args[])
    {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter a Word");
        String str1 = sc.nextLine();
        String str2 = ""; char ch;
        for(int i=0;i<str1.length();i++)
        {
            ch=str1.charAt(i);
            if(ch=='a'||ch=='e'||ch=='i'||ch=='o'||ch=='u'||ch=='A'||ch=='E'||ch=='I'||ch=='O'||ch=='U')
            {
                ch='$';
            }
            str2=str2+ch;
        }
        System.out.print(str2);
    }
}