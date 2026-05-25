package HashMap;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Objects;

public class MyHashMap<K, V> {

    private class Node{
        K key;
        V value;
        public Node(K key, V value){
            this.value = value;
            this.key = key;
        }
    }

    private int n;
    private int N;
    private double k;
    private LinkedList<Node> buckets[];

    @SuppressWarnings("unchecked")
    public MyHashMap(){
        this.N = 4;
        this.k = 2.0;
        this.buckets = new LinkedList[N];

        for(int i = 0; i<N;i++){
            this.buckets[i] = new LinkedList<>();
        }
    }

    //to Allocate bucket index 
    private int hashFunction(K key){
        int bi = key.hashCode();
        return Math.abs(bi)%N;
    }

    //Search in linked list to get data index
    private int searchInLL(K key, int bi){
        LinkedList<Node> list = buckets[bi];
        for(int i = 0; i< list.size(); i++){
 
            if(Objects.equals(list.get(i).key , key)){ //to handel null case used Objects.equals()
                return i;
            }
        }

        return -1;
    }


    //Rehsing when lambda crosses threshold(k) //increases bucket size to get O(1)
    @SuppressWarnings("unchecked")
    private void rehash(){
        LinkedList<Node>[] oldBuckets = buckets;
        N = 2*N;

        buckets = new LinkedList[N];

        for(int i = 0; i < N; i++){
            buckets[i] = new LinkedList<Node>();
        }

        //during put n will increase;
        n = 0;

        for(int i = 0; i < oldBuckets.length; i++){
            LinkedList<Node> list = oldBuckets[i];

            for(int j=0; j<list.size(); j++){
                Node node = list.get(j);
                put(node.key, node.value);
            }
        }
    }

    //put key and data 
    public void put(K key,V value){
        int bi = hashFunction(key);
        int di = searchInLL(key, bi);

        if(di == -1){
            buckets[bi].add(new Node(key, value));
            n++;
        }else{
            Node node = buckets[bi].get(di);
            node.value = value;
        }

        double lambda = (double)n/N;
        if(lambda > k){
            rehash();
        }

    }


    //To check a certain key is present or not
    public boolean containsKey(K key){
        int bi = hashFunction(key);
        int di = searchInLL(key, bi);

        if(di == -1){
            return false;
        }else{
            return true;
        }
    }


    //get value if the key is present
    public V get(K key){
        int bi = hashFunction(key);
        int di = searchInLL(key, bi);

        if(di == -1){
            return null;
        }else{
            Node node = buckets[bi].get(di);
            return node.value;
        }
    }


    //remove a certain key
    public V remove(K key){
        int bi = hashFunction(key);
        int di = searchInLL(key, bi);

        if(di == -1){
            return null;
        }else{
            Node node = buckets[bi].remove(di);
            n--;
            return node.value;
        }
    }


    //check isEmpty
    public boolean isEmpty(){
        return n == 0;
    }


    //returns all keys in the form of a list
    public ArrayList<K> keySet(){
        ArrayList<K> list = new ArrayList<>();

        //treverse through buckets(array)
        for(int i = 0; i < buckets.length; i++){
            LinkedList<Node> ll = buckets[i]; 

            //treverse through linkedList
            for(Node node : ll){
                list.add(node.key);
            }
        }

        return list;
    }


    //overriding toString() to print map directly by S.O.P()
    @Override
    public String toString(){
        if(isEmpty()){
            return "{}";
        }

        StringBuilder sb = new StringBuilder();
        sb.append("{");

        ArrayList<K> keys = keySet();

        for(int i = 0; i < keys.size(); i++){

            K key = keys.get(i);
            sb.append(key).append("=").append(get(key));

            if(i != keys.size() - 1){
                sb.append(", ");
            }
        }

        sb.append("}");

        return sb.toString();
    }

    

    public static void main(String[] args) {
        MyHashMap<String, Integer> map = new MyHashMap<>();

        map.put("India", 120);
        map.put("China", 200);
        map.put("Germani", 50);
        map.put("Japan", 20);

        System.out.println(map);

        map.put("India", 190);

        System.out.println(map);

        if(map.containsKey("India")){
            System.out.println("present india " + map.get("India"));
        }else{
            System.out.println("dont present");
        }

        ArrayList<String> keys = map.keySet();
        for(String key : keys){
            System.out.print(key + "=" + map.get(key) +" ");
        }
        System.out.println();

        map.remove("China");
        map.remove("Indoneshiya");
        
        System.out.println(map);

    }
}
