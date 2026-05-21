package LinkedList;
public class MyLinkedList{
    private int size;
    private Node head;
    
    private class Node{
        String data;
        Node next;

        Node(String data){
            this.data = data;
            this.next = null;
            size++;
        }
    }

    //add node at first
    public void addFirst(String data){
        Node newNode = new Node(data);
        if(head == null){
            head = newNode;
            return;
        }
        newNode.next = head;
        head = newNode;

    }

    //add node at last
    public void addLast(String data){
        Node newNode = new Node(data);
        if(head == null){
            head = newNode;
            return;
        }
        Node currentNode = head;
        while(currentNode.next != null){
            currentNode = currentNode.next;
        }
        currentNode.next = newNode;
    }

    //add node at a location
    public void add(int index, String data){

        if(index > size || index < 0){
            System.out.println("Invalid location");
            return;
        }

        Node newNode = new Node(data);
        if(index == 0){
            newNode.next = head;
            head = newNode;
            return;
        }

        Node currentNode = head;

        while(index > 1){
            currentNode = currentNode.next;
            index--;
        }
        newNode.next = currentNode.next;
        currentNode.next = newNode;
    }

    //delete firstnode
    public void removeFirst(){
        if(head == null){
            System.out.println("List is empty!");
            return;
        }
        size--;
        head = head.next;
    }

    //delete at last
    public void removeLast(){
        if(head == null){
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

        Node currentNode = head;
        Node nextNode = currentNode.next;
        while(index-1 !=0 && nextNode.next != null){
            currentNode = currentNode.next;
            nextNode = nextNode.next;
            index--;
        }

        size--;
        currentNode.next = nextNode.next;
    }
    

    //peek value at first
    public String getFirst(){
        if(head == null){
            return null;
        }
        return head.data;
    }

    //peek at end
    public String getLast(){
        if(head == null){
            return null;
        }

        Node temp = head;

        while(temp.next != null){
            temp = temp.next;
        }
        return temp.data;
    }

    //get at a position
    public String get(int index){
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
    public boolean contains(String data){
        if(size == 0){
            return false;
        }

        Node temp = head;

        while(temp != null){
            if(data.equals(temp.data)){
                return true;
            }

            temp = temp.next;
        }

        return false;
    }

    //print
    public void printList(){
        if(head == null){
            System.out.println("List is empty");
            return;
        }

        Node currNode = head;

        while(currNode != null){
            System.out.print(currNode.data);

            if(currNode.next != null){
                System.out.print(" -> ");
            }

            currNode = currNode.next;
        }

        System.out.println();
    }


    //Reverse by recursion
    public void reverseRecursive(){
        this.head = reverseRecursive(this.head);
    }

    private Node reverseRecursive(Node Head){
        if(Head == null || Head.next == null ){
            return Head;
        }

        Node newHead = reverseRecursive(Head.next);

        Head.next.next = Head;
        Head.next = null;

        return newHead;
    }

    
    //Reverse by itereation
    public void reverse(){
        if(head == null || head.next == null){
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


    //non parameterised constructor
    MyLinkedList(){
        this.size = 0;
        this.head = null;
    }


    public static void main(String[] args){
        MyLinkedList list = new MyLinkedList();
        list.addLast("I");
        list.addLast("am");
        list.printList();
    }
    
}