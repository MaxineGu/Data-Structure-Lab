//Jiayi Gu 
//31526890

package lab5;

import java.util.Iterator;
import java.util.ArrayList;
import java.util.Arrays;
public class LinearProbing<Key, Value> extends UR_HashTable<Key, Value> implements Iterable<Key> {
	   private static final int INIT_CAPACITY = 5;

	    private int size_data;           // size of the data n
	    private int size_table;           // size of linear probing table m
	    private Key[] keys;      
	    private Value[] vals;
	    public LinearProbing() {
	    	 this(INIT_CAPACITY);
	    }
	    public LinearProbing(int cap) {
	    	size_table = cap;
	    	size_data = 0;
	    	keys = (Key[]) new Object[size_table];
	    	vals = (Value[]) new Object[size_table];
	    }
		@Override
		public Iterator<Key> iterator() {
			Object[] iterator_hash = new Object[size_table];
			  for(int index = 0; index < size_table; index++)
				  iterator_hash[index] = keys[index];
			  return (Iterator<Key>) Arrays.asList(iterator_hash).iterator();
			
		}
			
		
		
		public int hash(Key key) {
			  return key.hashCode();
		}
		public void resize(int cap) {
			 LinearProbing<Key, Value> new_table = new LinearProbing<>(cap);
		        for (int i = 0; i < size_table; i++) {
		            if(keys[i] != null){
		            	new_table.put(keys[i], vals[i]);
		            
		        }
		        }
		        keys = new_table.keys;
		        vals = new_table.vals;
		        size_table = new_table.size_table;
		    }

		
		@Override
		public void put(Key key, Value val) {
			int i;
			if (key == null) {
				System.out.print("Invalid Input");
				return;
			}
				if (val == null) {
					delete(key);
					return;
				}
				if(size_data >= size_table)
					resize(2*size_table);
			for(i = hash(key)%size_table; keys[i]!= null; i = (i+1)%size_table) {
				if(keys[i].equals(key)) {
					vals[i] = val;
					return;
				}
			}
			keys[i] = key;
			vals[i] = val;
			size_data ++;
			
		}
		@Override
		public Value get(Key key) {
			for(int i = hash(key)%size_table; keys[i]!= null; i = (i+1)%size_table) {
				if(keys[i].equals(key)) {
					return vals[i];
				}
		}
			return null;
		}
		@Override
		public void delete(Key key) {
			if (key == null) return;
			if (!contains(key)) return;
			int i = hash(key)%size_table;
			while (!key.equals(keys[i])) {
	            i = (i + 1) % size_table;
	        }
	        keys[i] = null;
	        vals[i] = null;
	        i = (i + 1) % size_table;
	        //null after delete 
	        while (keys[i] != null) {
	            Key   new_key = keys[i];
	            Value new_val = vals[i];
	            keys[i] = null;
	            vals[i] = null;
	            size_data --;
	            put(new_key, new_val);
	            i = (i + 1) % size_table;
	        }
	        size_data --;
	        
		}
		@Override
		public int size() {
			return size_data;
		}
		@Override
		public boolean isEmpty() {
			if (size_data == 0) return true;
			else return false;
		}
		@Override
		public boolean contains(Key key) {
			for(int i = hash(key); keys[i]!= null; i = (i+1)%size_table) {
				if(keys[i].equals(key)) return true;
			}
			return false;
			
		}
		@Override
		public Iterable<Key> keys() {
			ArrayList<Key> iterable_key = new ArrayList<Key>();
	        for(int i = 0; i < m; i++){
	            if(keys[i] != null) iterable_key.add(keys[i]);
	        }
	        return iterable_key;

		}
		public static void main(String[] args) {
			LinearProbing HT = new LinearProbing<Integer, String>(10);
			  HT.put(5, "new");
			  HT.put(6, "apple");
			  HT.put(7, "good");
			  HT.put(11, "best");			  
			  HT.put(13, "perfect");
			  HT.put(14, "best");
			  HT.put(19, "set");
			  HT.delete(7);

			  System.out.println(HT.isEmpty());
			  System.out.println(HT.contains(6));
			  System.out.println(HT.get(16));
			  System.out.println(HT.size());
		}
}
