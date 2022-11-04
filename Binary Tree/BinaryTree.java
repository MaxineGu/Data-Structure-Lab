package lab6;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Queue;
import java.util.Stack;
import java.util.LinkedList;



public class BinaryTree<Key extends Comparable<Key>, Value> extends UR_BST<Key, Value> implements Iterable <Key> {
	private class UR_Node {
		private Key key; // sorted by key
		private Value val; // associated data
		private UR_Node left, right; // left and right subtrees
		private int size; // number of nodes in subtree
        public UR_Node(Key key, Value val, int size) {
            this.key = key;
            this.val = val;
            this.size = size;
        }
		}
	private UR_Node root;
	public BinaryTree(){
	}
	  
	@Override
	public boolean isEmpty() {
		if (size() ==0) return true; 
		else return false;
	}

	@Override
	public int size() {
		if (root == null) return 0;
		else return root.size;
		
	}
	   private int size(UR_Node node) {
	        if (node == null) return 0;
	        else return node.size;
	    }


	@Override
	public boolean contains(Key key) {
		if (key == null) throw new IllegalArgumentException("the input of the key is null");
		if (get(key) != null) return true;
		else return false;
	}
	public Value get(Key key) {
		if (key == null) throw new IllegalArgumentException("the input of the key is null");
		return get(root, key);
	}
		
	public Value get(UR_Node node, Key key) {
		 
		 int temp = key.compareTo(node.key);
	        if      (temp < 0) return get(node.left, key);
	        else if (temp > 0) return get(node.right, key);
	        else              return node.val;
	    }

	

	@Override
	public void put(Key key, Value val) {
		if (key == null) throw new IllegalArgumentException("the input of the key is null");
		if (val == null) {
            delete(key);
            return;
        }
		root = put(root, key, val);
		
	}
	 private UR_Node put(UR_Node node, Key key, Value val) {
	        if (node == null) return new UR_Node(key, val, 1);
	        int  temp = key.compareTo(node.key);
	        if      (temp < 0) node.left  = put(node.left,  key, val);
	        else if (temp > 0) node.right = put(node.right, key, val);
	        else              node.val   = val;
	        node.size = 1 + size(node.left) + size(node.left);
	        return node;
	    }

	@Override
	public void deleteMin() {
		 if (isEmpty()) throw new NoSuchElementException("The Binary Search Tree is empty");
		root = deleteMin(root);
		
	}
	 private UR_Node deleteMin(UR_Node node) {
	        if (node.left == null) {
	        	return node.right;	        	
	        }
	        node.left = deleteMin(node.left);
	        node.size = size(node.left) + size(node.right) + 1;
	        return node;
	    }


	@Override
	public void deleteMax() {
		 if (isEmpty()) throw new NoSuchElementException("The Binary Search Tree is empty");
			root = deleteMax(root);
		
	}
	 private UR_Node deleteMax(UR_Node node) {
	        if (node.right == null) return node.left;
	        node.right = deleteMax(node.right);
	        node.size = size(node.left) + size(node.right) + 1;
	        return node;
	    }


	@Override
	public void delete(Key key) {
		if (isEmpty()) throw new NoSuchElementException("The Binary Search Tree is empty");
		root = delete(root, key);
		
	}

	private UR_Node delete(UR_Node node, Key key) {
        if (node == null) return null;

        int cmp = key.compareTo(node.key);
        if      (cmp < 0) node.left  = delete(node.left,  key);
        else if (cmp > 0) node.right = delete(node.right, key);
        else {
            if (node.right == null) return node.left;
            if (node.left  == null) return node.right;
            UR_Node temp = node;
            node = min(temp.right);
            node.right = deleteMin(temp.right);
            node.left = temp.left;
        }
        node.size = size(node.left) + size(node.right) + 1;
        return node;
    }

    private UR_Node min(UR_Node node) {
        if (node.left == null) return node;
        else return min(node.left);
    }
    private UR_Node max(UR_Node node) {
        if (node.right == null) return node;
        else                 return max(node.right);
    }
	@Override
	public Iterable<Key> keys() {
	     if (isEmpty()) {
	    	 Queue<Key> new_queue = new LinkedList<Key>();
	    	 return new_queue;
	     }
	        return keys(min(root).key, max(root).key);

	}
	 public Iterable<Key> keys(Key low, Key high) {
	        if (low == null) throw new IllegalArgumentException("first argument to keys() is null");
	        if (high == null) throw new IllegalArgumentException("second argument to keys() is null");

	        Queue<Key> queue = new LinkedList<Key>();
	        keys(root, queue, low, high);
	        return queue;
	    }
	   private void keys(UR_Node node, Queue<Key> queue, Key low, Key high) {
	        if (node == null) return;
	        int com_low = low.compareTo(node.key);
	        int com_hi = high.compareTo(node.key);
	        if (com_low < 0) keys(node.left, queue, low, high);
	        if (com_low <= 0 && com_hi >= 0) queue.add(node.key);
	        if (com_hi > 0) keys(node.right, queue, low, high);
	    }

	@Override
	 public int height() {
        return height(root);
    }
    private int height(UR_Node node) {
        if (node == null) return -1;
        return 1 + Math.max(height(node.left), height(node.right));
    }


	@Override
	public Iterable<Key> levelOrder() {
		 Queue<Key> keys = new LinkedList<Key>();
	        Queue<UR_Node> queue = new LinkedList<UR_Node>();
	        queue.add(root);
	        while (!queue.isEmpty()) {
	            UR_Node x = queue.remove();
	            if (x == null) continue;
	            keys.add(x.key);
	            queue.add(x.left);
	            queue.add(x.right);
	        }
	        return keys;
	    }
	// reference: https://www.geeksforgeeks.org/implementing-forward-iterator-in-bst/
	private class KeyIterator {
		Stack<UR_Node> s;		 
		public KeyIterator(UR_Node node){
		    s = new Stack<>();
		    UR_Node curr = node;		     
		    while (curr != null)
		    {
		        s.push(curr);
		        curr = curr.left;
		    }
		}
		UR_Node curr()
		{
		    return s.peek();
		}
		public void next()
		{
		    UR_Node temp = s.peek().right;
		    s.pop();
		     
		    while (temp != null)
		    {
		        s.push(temp);
		        temp = temp.left;
		    }
		}
		public boolean isEnd()
		{
		    return !s.isEmpty();
		}
		}
	public Iterator<Key> iterator() {
		return (Iterator<Key>) new KeyIterator(root);
	}
	public static void main(String[] args) {
		BinaryTree<Integer, Integer> test = new BinaryTree<Integer, Integer>();
		test.put(8, 8);
		test.put(6, 6);
		test.put(10, 10);
		test.put(9, 9);
		test.put(3, 3);
		test.put(4, 4);
		test.put(7, 7);
		test.put(11, 11);
		test.deleteMin();
		test.deleteMax();
		System.out.println(test.size());
		System.out.println(test.isEmpty());
		System.out.println(test.get(10));
		System.out.println(test.contains(4));
		System.out.println(test.levelOrder());

	}



}
