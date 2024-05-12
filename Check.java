//WAP to check if a word is Palindrome or not
import java.util.Scanner;
class Check
{
    String wrd,revwrd; 
    int len;
    Check()
    {
        wrd="";
        revwrd="";
        len=0;
    }
    void acceptword()
    {
        System.out.println("Enter a Word");
        Scanner sc = new Scanner(System.in);
        wrd = sc.nextLine();
        len = wrd.length();
    }
    boolean palindrome()
    {
        /*int i=len-1;
        if(i>=0)
        {
            char ch=wrd.charAt(i);
            revwrd=revwrd+ch;
            i--;
            palindrome(i);
        }*/
        for(int i=len-1;i>=0;i--)
        {
            char ch=wrd.charAt(i);
            revwrd=revwrd+ch;
        }
        if(wrd.equals(revwrd))
        {
            return true;
        }
        else
        {
            return false;
        }
    }
    void display()
    {
        if(palindrome())
        
        {
            System.out.println("PALINDROME");
        }
        else
        {
            System.out.println("NOT PALINDROME");
        }
    }
    public static void main(String args[])
    {
        Check co = new Check();
        co.acceptword();
        co.display();
    }
}