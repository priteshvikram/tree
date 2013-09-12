/**
 * linked list tree representation
 *
 * @author Pritesh Vikram
 * @version 1.00 13/09/08
 */

import java.util.Scanner;

class node<T> {
	T data;
	node<T> left;
	node<T> right;
}
 
public class tree {
    static node<Integer> root;
    static node<?> queue[];
    static int front=0,rear=0;
    tree() {
    	root=null;
    }
    static void treeInsert(int data){
    	if(root==null){
    		root=new node<Integer>();
    		root.data=data;
    		root.left=null;
    		root.right=null;
    	}
    	else{
    		node<Integer> temp,temp2;
    		temp=new node<Integer>();
    		temp.data=data;
    		temp.left=null;
    		temp.right=null;
    		temp2=root;
    		while((temp2.left!=null && data<temp2.data)||(temp2.right!=null && data>temp2.data)){
    			if(data<temp2.data)
    				temp2=temp2.left;
    			else if(data>temp2.data)
    				temp2=temp2.right;
    			else if(data==temp2.data){
    				System.out.println("The tree cannot contain redundant data");
    				return;
    			}
    		}
    		if(temp2.left==null && data<temp2.data){
    			temp2.left=temp;
    		}
    		else if(temp2.right==null && data>temp2.data){
    			temp2.right=temp;
    		}
    	}
    }
    static void treeDelete(int data){
    	if(root==null){
    		System.out.println("The tree is empty");
    		return;
    	}
    	else{
    		node<Integer> temp=root,prev=null;
    		while(temp!=null && temp.data!=data)
    			if(data<temp.data){
    				prev=temp;
    				temp=temp.left;
    			}
    			else if(data>temp.data){
    				prev=temp;
    				temp=temp.right;
    			}
    		if(data==temp.data){
    			if(temp.left!=null)
    				join(temp.left);
    			if(temp.right!=null)
    				join(temp.right);
    			temp.left=null;
    			temp.right=null;
    			if(prev.left==temp)
    				prev.left=null;
    			else if(prev.right==temp)
    				prev.right=null;
    		}
    		else if(temp==null){
    			System.out.println("The specified data not found");
    			return;
    		}
    	}
    }
    static void join(node<Integer> n){
    	if(n!=null){
    		node<Integer> temp;
    		temp=find(root,n.data);
    		if(n.data<temp.data)
    			temp.left=n;
    		else if(n.data>temp.data)
    			temp.right=n;
    	}
    }
    static node<Integer> find(node<Integer> n,int data){
    	if(data<n.data){
    		if(n.left!=null)
    			find(n.left,data);
    		else
    			return n;
    	}
    	else if(data>n.data){
    		if(n.right!=null)
    			find(n.right,data);
    		else
    			return n;
    	}
    	return null;
    }
    static void display(){
    	//node<Integer> temp=root;
    	node<?> temp2;
    	/*int width=0;
    	while(temp!=null){
    		temp=temp.left;
    		width++;
    	}
    	temp=root;
    	while(temp!=null){
    		temp=temp.right;
    		width++;
    	}*/
    	queue=new node<?>[20];
    	temp2=root;
    	addq(temp2);
    	for(;;){
    		temp2=deleteq();
    		if(temp2!=null){
    			System.out.println(temp2.data);
    			if(temp2.left!=null)
    				addq(temp2.left);
    			if(temp2.right!=null)
    				addq(temp2.right);
    		}
    		else break;
    	}
    }
    static void addq(node<?> n){
    	queue[rear++]=n;
    }
    static node<?> deleteq(){
    	return queue[front++];
    }
    public static void main(String[] args) {
        int choice,data;
        Scanner s=new Scanner(System.in);
        root=null;
        do{
        	System.out.println("1. Insert tree node\n2. Delete tree node\n3. List tree\n4. Exit");
        	System.out.print("Enter the choice: ");
        	choice=s.nextInt();
        	switch(choice){
        		case 1: System.out.println("Enter the node value: ");
        			    data=s.nextInt();
        			    treeInsert(data);
        			    break;
        		case 2: System.out.println("Enter the node to delete: ");
        				data=s.nextInt();
        				treeDelete(data);
        				break;
        		case 3: System.out.println("The tree formed is");
        				display();
        				break;
        		case 4: break;
        		default: System.out.println("Undefined choice");
        	}
        }while(choice!=4);
    }
}