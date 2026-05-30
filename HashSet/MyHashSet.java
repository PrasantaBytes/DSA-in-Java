/*
 * Custom HashSet implementation using Separate Chaining.
 *
 * Features:
 * - Generic Type Support
 * - Collision Handling using LinkedList
 * - Dynamic Rehashing
 * - Iterable Support
 *
 * Author: Prasanta
 */

package HashSet;
import java.util.LinkedList;
import java.util.Iterator;

public class MyHashSet<K> implements Iterable<K> {

    private int n;
    private int N;
    private static final double LOAD_FACTOR = 0.75;
    private LinkedList<K>[] buckets;

    @SuppressWarnings("unchecked")
    public MyHashSet(){
        this.n = 0;
        this.N = 10;
        this.buckets = (LinkedList<K>[]) new LinkedList[N];

        for(int i=0; i<N; i++){
            buckets[i] = new LinkedList<>();
        }
    }

    // Hash function allocates bucket index
    private int hashFunction(K key){
        
        return (key.hashCode() & 0x7fffffff) % N;
    }

    //To get data index 
    private int searchInLL(int bi, K key){
        LinkedList<K> list = buckets[bi];

        int i = 0;
        for(K k : list){
            if(k.equals(key)){
                return i;
            }
            i++;
        }

        return -1;
    }


    // Rehashing when load factor exceeds threshold
    @SuppressWarnings("unchecked")
    private void rehash(){
        LinkedList<K> oldBuckets[] = buckets;

        N = N*2;
        buckets = (LinkedList<K>[]) new LinkedList[N];

        for(int i=0; i<N; i++){
            buckets[i] = new LinkedList<>();
        }

        n=0;

        for(int i=0; i<oldBuckets.length; i++){
            LinkedList<K> list = oldBuckets[i];

            for(K key : list){
                add(key);
            }
        }   
    }

    //adding new values to the hashset
    public void add(K key){
        int bi = hashFunction(key);
        int di = searchInLL(bi, key);

        if(di == -1){
            buckets[bi].add(key);
            n++;
        }

        //check is lambda exceds LOAD_FACTOR or not. if excedes then rehash
        double lambda = (double)n/N;
        if(lambda > LOAD_FACTOR){
            rehash();
        }
    }


    //Remove operation
    public void remove(K key){
        int bi = hashFunction(key);
        int di = searchInLL(bi, key);
        if(di != -1){
            buckets[bi].remove(di);
            n--;
        }
    }

    //check data contains or not to the set
    public boolean contains(K key){
        int bi = hashFunction(key);
        int di = searchInLL(bi, key);
        
        return di != -1;
    }

    //get size of the set
    public int size(){
        return n;
    }

    //Clear the whole set
    //@SuppressWarnings("unchecked")
    public void clear(){
        this.n = 0;
        // this.N = 10;
        // this.buckets = (LinkedList<K>[]) new LinkedList[N];

        for(int i=0; i<N; i++){
            buckets[i] = new LinkedList<>();
        }
    }

    //check the list is empty or not
    public boolean isEmpty(){
        return n == 0;
    }


    //Override toString() to print set normally simillar to the real HashSet
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("[");

        Iterator<K> it = iterator();

        while(it.hasNext()){
            sb.append(it.next());

            if(it.hasNext()){
                sb.append(", ");
            }
        }

        sb.append("]");

        return sb.toString();
    }


    // overriding Iterator by implementing Iterable interface , and override its function
    @Override
    public Iterator<K> iterator() {
        return new MyIterator();
    }

    private class MyIterator implements Iterator<K>{

        int bucketIndex; 
        int listIndex;

        public MyIterator(){ 
            bucketIndex = 0; 
            listIndex = 0; 
        }

        @Override 
        public boolean hasNext(){ 
            while(bucketIndex < buckets.length){ 
                if(listIndex < buckets[bucketIndex].size()){ 
                    return true; 
                } 
                bucketIndex++; 
                listIndex = 0; 
            } 
            return false; 
        }
        @Override 
        public K next(){ 
            K value = buckets[bucketIndex].get(listIndex); 
            listIndex++; 

            return value; 
        } 
    }

    public static void main(String[] args){
        MyHashSet<Integer> set = new MyHashSet<>();

        set.add(10);
        set.add(20);
        set.add(50);
        set.add(50);
        set.remove(10);
        System.out.println(set.size());
        System.out.println(set);

        Iterator<Integer> it = set.iterator();

        while(it.hasNext()){
            System.out.println(it.next());
        }

    }
}