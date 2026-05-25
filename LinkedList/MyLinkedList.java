package LinkedList;
import java.util.Objects;
public class MyLinkedList<T>{
    private int size;
    private Node head;
    
    private class Node{
        T data;
        Node next;

        Node(T data){
            this.data = data;
            this.next = null;
        }
    }

    //non parameterised constructor
    public MyLinkedList(){
        this.size = 0;
        this.head = null;
    }


    //add node at first
    public void addFirst(T data){
        Node newNode = new Node(data);

        if(isEmpty()){
            head = newNode;
            size++;
            return;
        }

        newNode.next = head;
        head = newNode;
        size++;
    }

    //add node at last
    public void addLast(T data){
        Node newNode = new Node(data);

        if(isEmpty()){
            head = newNode;
            size++;
            return;
        }

        Node currentNode = head;
        //traverse to end
        while(currentNode.next != null){
            currentNode = currentNode.next;
        }

        currentNode.next = newNode;
        size++;
    }

    //add node at a location
    public void add(int index, T data){

        if(index > size || index < 0){
            System.out.println("Invalid location");
            return;
        }

        Node newNode = new Node(data);

        if(index == 0){
            newNode.next = head;
            head = newNode;
            size++;

            return;
        }

        Node currentNode = head;

        while(index > 1){
            currentNode = currentNode.next;
            index--;
        }

        newNode.next = currentNode.next;
        currentNode.next = newNode;

        size++;
    }

    //delete firstnode
    public void removeFirst(){
        if(isEmpty()){
            System.out.println("List is empty!");
            return;
        }
        size--;
        head = head.next;
    }

    //delete at last
    public void removeLast(){
        if(isEmpty()){
            System.out.println("List is empty!");
            return;
        }
        size--;
        if(head.next == null){
            head = null;
            return;
        }
        Node currentNode = head;
        while(currentNode.next.next != null){
            currentNode = currentNode.next;
        }
        currentNode.next = null;
    }

    //delete at a location
    public void remove(int index){        
        if(index >= size || index < 0){
            System.out.println("Invalid location");
            return;
        }
        if(index == 0){
            size--;
            head = head.next;
            return;
        }

        Node temp = head;

        for(int i = 0; i < index - 1; i++){
            temp = temp.next;
        }

        temp.next = temp.next.next;
        size--;
    }
    

    //peek value at first
    public T getFirst(){
        if(isEmpty()){
            return null;
        }
        return head.data;
    }

    //peek at end
    public T getLast(){
        if(isEmpty()){
            return null;
        }

        Node temp = head;

        while(temp.next != null){
            temp = temp.next;
        }
        return temp.data;
    }

    //get at a position
    public T get(int index){
        if(index >= size || index < 0){
            return null;
        }

        Node temp = head;

        int i = 0;
        while(i < index){
            temp = temp.next;
            i++;
        }

        return temp.data;
    }

    //is searched element pesent in linklist
    public boolean contains(T data){
        if(isEmpty()){
            return false;
        }

        Node temp = head;

        while(temp != null){
            //to avoid null crash problem used Objects.equals()
            if(Objects.equals(temp.data,data)){
                return true;
            }

            temp = temp.next;
        }

        return false;
    }


    //Reverse by recursion
    public void reverseRecursive(){
        this.head = reverseRecursive(this.head);
    }

    private Node reverseRecursive(Node Head){
        if( Head == null || Head.next == null ){
            return Head;
        }

        Node newHead = reverseRecursive(Head.next);

        Head.next.next = Head;
        Head.next = null;

        return newHead;
    }

    
    //Reverse by itereation
    public void reverse(){
        if(isEmpty() || head.next == null){
            return;
        }

        Node prevNode = head;
        Node currentNode = head.next;

        while(currentNode != null){
            Node nextNode = currentNode.next;
            currentNode.next = prevNode;
            prevNode = currentNode;
            currentNode = nextNode;
        }
        
        head.next = null;
        head = prevNode;
    }

    //size
    public int size(){
        return size;
    }

    //list mpty or not
    public boolean isEmpty(){
        return size == 0;
    }

    //clear the list
    public void clear(){
        head = null;
        size = 0;
    }

    //to print list directly SOP(list)
    @Override
    public String toString(){
        if(isEmpty()){
            return "[]";
        }

        StringBuilder sb = new StringBuilder();

        sb.append("[");

        Node temp = head;

        while(temp != null){
            sb.append(temp.data);

            if(temp.next != null){
                sb.append(", ");
            }

            temp = temp.next;
        }

        sb.append("]");

        return sb.toString();
    }



    public static void main(String[] args){
        MyLinkedList<String> list = new MyLinkedList<>();
        list.addLast("I");
        list.addLast("am");
        System.out.println(list);


    } 
}