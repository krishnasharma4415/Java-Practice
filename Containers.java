import java.util.ArrayList;
import java.util.Scanner;

public class Containers 
{
    void javaArrayList()
    {   
        Scanner sc = new Scanner(System.in);
        ArrayList<String> list = new ArrayList<>();
        
        System.out.println("Enter the number of elements in the list:");
        int n = sc.nextInt();
        System.out.println("Enter the elements of the list:");
        for (int i = 0; i < n; i++) 
        {
            String element = sc.nextLine();
            list.add(element);
        }

        System.out.println("The elements in the list are:");
        for (int i = 0; i < n; i++)
        {
            String element = list.get(i);
            System.out.println(element);
        }

        System.out.println("The size of the list is: " + list.size());
        System.out.println("The first element in the list is: " + list.get(0));
        System.out.println("The last element in the list is: " + list.get(list.size() - 1));

        sc.close();
    }    
}
