class A
{
    int a=5;
    void fun1()
    {
        a=10;
        System.out.println("a:"+a);
    }
}
class B extends A
{
    int x=15;
    void fun2()
    {
        int sum=x+a;
        System.out.println("Sum:"+sum);
    }
    public static void main(String args[])
    {
        B ob = new B();
        ob.fun2();
    }
}