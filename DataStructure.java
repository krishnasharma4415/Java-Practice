import java.util.Collections;
import java.util.ArrayList;
public class DataStructure {

    void ArrayList(){
        ArrayList<Integer> list1 = new ArrayList<Integer>();
        ArrayList<String> list2 = new ArrayList<String>();

        list1.add(0);
        list1.add(2);
        list1.add(3);
        System.out.println(list1);

        int element = list1.get(0);
        System.out.println(element);

        list1.add(1, 1);
        System.out.println(list1); 

        list1.set(0,5);
        System.out.println(list1);

        list1.remove(3);
        System.out.println(list1);

        int size = list1.size();
        System.out.println(size);

        for(int i=0;i<list1.size();i++){
            System.out.println(list1.get(i));
        }
        System.out.println();

        Collections.sort(list1);
        System.out.println(list1);
    }

    class Node{
        String data;
        Node next;

        Node(String data){
            this.data = data;
            this.next = null;
        }
    }
    void LinkedList(String data){
        Node newNode = new Node(data);
        if(head==null){
            head=newNode;
            return;
    }
    newNode.next = head;
    head=newNodes;

     public static void main(String args[]){
        DataStructure ob = new DataStructure();
        ob.ArrayList();
        
    }
}
