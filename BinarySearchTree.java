/**
 * Auther: Namesh Sanjitha 
 * */

import java.util.Scanner;

class Node {
	private int data;
	private Node left, right;
	
	// method to set data
	public void setData(int data) {
		this.data = data;
	}
	
	// method to get data
	public int getData() {
		return data;
	}
	
	public void setLeft(Node node) {
		this.left = node;
	}
	
	public Node getLeft() {
		return left;
	}
	
	public void setRight(Node node) {
		this.right = node;
	}
	
	public Node getRight() {
		return right;
	}	
}

class BinarySearchTree {
	
	private Node root;
	
	// constructor
	public BinarySearchTree() {}
	
	public BinarySearchTree(Node root) {
		this.root = root;
	}
	
	// method to initialize tree
	public void initTree() {
		root = null;
	}
	
	// method to get root
	public Node getRoot() {
		return this.root;
	}
	
	// method to set root
	public void setRoot(Node root) {
		this.root = root;
	}
	
	// in order traversal
	public void inOrder(Node current) {
		if (current != null) {
			inOrder(current.getLeft());
			System.out.print(current.getData() + " ");
			inOrder(current.getRight());
		}
	}
	
	// pre order traversal
	public void preOrder(Node current) {
		if (current != null) {
			System.out.print(current.getData() + " ");
			preOrder(current.getLeft());
			preOrder(current.getRight());
		}
	}
	
	// post order traversal
	public void postOrder(Node current) {
		if(current != null) {
			postOrder(current.getLeft());
			postOrder(current.getRight());
			System.out.print(current.getData() + " ");
		}
	}
	
	// insert an element
	public void insert(int data) {
		Node current = root, parent = current;
		Node newNode = new Node();
		newNode.setData(data);
		
		if(root == null) {
			root = newNode;
		}
		else {
			while(current != null) {
				parent = current;
				if(data < parent.getData()) {
					current = parent.getLeft();
				}
				else {
					current = parent.getRight();
				}
			}
			
			if(data < parent.getData()) {
				parent.setLeft(newNode);
			}
			else {
				parent.setRight(newNode);
			}
		}
	}
	
	// find max
	public int max() {
		Node parent = root, current = root;
		
		if(root == null) {
			return -1;
		}
		else {
			while(current != null) {
				parent = current;
				current = current.getRight();
			}
			return parent.getData();
		}
	}
	
	// find min
	public int min() {
		Node parent = root, current = root;
		
		if(root == null) {
			return -1;
		}
		else {
			while(current != null) {
				parent = current;
				current = current.getLeft();
			}
			
			return parent.getData();
		}
	}
	
	// search an element
	public Node search(int key) {
		Node current = root;
		
		if(root == null) {
			return null;
		}
		else {
			while(current != null) {
				if(current.getData() == key) {
					return current;
				}
				else if(key < current.getData()) {
					current = current.getLeft();
				}
				else {
					current = current.getRight();
				}
			}
			
			return null;
		}			
	}
	
	// delete a node in tree
	public boolean delete( int data ) throws Exception {
		if(this.search(data) != null) { // if data is in tree
			Node current = this.root, parent = this.root;
			boolean isLeft = false;
			// go to the node that has to be deleted
			while(current != this.search(data) && (current != null)) {				
				parent = current;
				if(data < current.getData()) {
					current = current.getLeft();
					isLeft = true;
				}
				else {
					current = current.getRight();
					isLeft = false;
				}
			}
			
			// deleting process
			try {
				if((current.getLeft() == null) && (current.getRight() == null))	{ // if it has no children
					if(isLeft == true) { // if current is left of parent
						parent.setLeft(null);
					}
					else { // if current is right of parent
						parent.setRight(null);
					}
					return true;
				}
				else if((current.getLeft() == null) || (current.getRight() == null)) {	// if it has one child
					if(isLeft == true) {  // if  current is left of parent
						if(current.getLeft() != null) { // if current has a left child
							parent.setLeft(current.getLeft());
						}
						else { // if current has a right child
							parent.setLeft(current.getRight());
						}
						return true;
					}
					else { // if current is right of parent
						if(current.getLeft() != null) { // if current has a left child
							parent.setRight(current.getLeft());
						}
						else { // if current has a right child
							parent.setRight(current.getRight());
						}
						return true;
					}
				}
				else { // if current has two children
					BinarySearchTree bst = new BinarySearchTree();
					bst.setRoot(current.getLeft());
					int predecessor = bst.max();	
					this.delete(predecessor);
					current.setData(predecessor);
					return true;
				}
			}
			catch (Exception e) {
				return false;
			}
		}
		else { // if data is not in tree
			return false;
		}
	}
	
	// method to find predecessor of a elemnt
	public int findPredecessor(int data) {
		Node current = this.search(data);
		
		if((current != null) && (current.getLeft() != null) ) {	// if data is a node
			BinarySearchTree bst = new BinarySearchTree(current.getLeft());
			return bst.max();
		}
		else {
			return data;
		}
	}
	
	// method to find successor
	public int findSuccessor(int data) {
		Node current = this.search(data);
		
		if( current != null && current.getRight() != null ) {	// if data is a node
			BinarySearchTree bst = new BinarySearchTree(current.getRight());
			return bst.min();
		}
		else {
			return data;
		}
	}
}
