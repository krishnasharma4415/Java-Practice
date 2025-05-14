import java.util.Scanner;
import java.util.*;

class Calculator
{
    int Sum(int n, int arr[])
    {
        int sum = 0;
        for (int i = 0; i < n; i++)
        {
            sum += arr[i];
        }
        return sum;
    }

    double Sum(int n, double arr[]) 
    {
        double sum = 0;
        for (int i = 0; i < n; i++)
        {
            sum += arr[i];
        }
        return sum;
    }
}

class Main
{
    public static void main(String args[])
    {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] a1 = new int[n];
        double[] a2 = new double[n];
        for (int i = 0; i < n; i++)
        {
            a1[i] = sc.nextInt();
        }
        for (int i = 0; i < n; i++)
        {
            a2[i] = sc.nextDouble();
        }
        Calculator cal = new Calculator();
        int sum1 = cal.Sum(n, a1);
        double sum2 = cal.Sum(n, a2);
        System.out.println("Integer array sum: " + sum1);
        System.out.println("Double array sum: " + sum2);
    }
}