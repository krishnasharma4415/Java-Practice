//WAP to count number of vowels and consonants of every word of string
import java.util.Scanner;
class Count2
{
    public static void main(String args[])
    {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter a String");
        String str = sc.nextLine();str=str+" ";
        String str2 = "";
        String word = "";
        char ch;int v=0,c=0;
        for(int i=0;i<str.length()-1;i++)
        {
            ch=str.charAt(i);
            word="";
            v=0;c=0;
            while(ch!=' ')
            {
                word = word+ch;
                i=i+1;
                ch=str.charAt(i);
                if(ch=='a'||ch=='e'||ch=='i'||ch=='o'||ch=='u'||ch=='A'||ch=='E'||ch=='I'||ch=='O'||ch=='U')
                {
                    v++;
                }
                else
                {
                    c++;
                }
            }
            System.out.println("Word:"+word+" Vowels:"+v+" Consonants:"+c);
        }
    }
}