import java.util.Stack;

class Node{
   int value;
   Node left, right;
   
   public Node(int value){
      this.value = value;
      left = null;
      right = null;
   }

}

class BinarySearchTree{

   Node root;
   
   
   /*
   recursive insert method
   */
   public Node insert(Node root, int value){
      //base case
      if(root == null){
         root = new Node(value);
         return root;
      }
      
      //recursive step
      if(value < root.value){
         root.left = insert(root.left, value); 
      }else{
         root.right = insert(root.right, value);
      }
      
      return root;
   }



   /**
    * pre-order traversal
    * Done in the format of node->left ->right and saves the nodes popped
    * and prints the solution.
    *
    * @param root - the root of the tree
    */
   public void preOrderTraversal(Node root){
      //If tree is empty
      if (root == null){
         System.out.println("The tree is empty");
      }
      String preOrder = "";
      Stack<Node> s = new Stack<>();

      s.push(root);
      Node temp = root;

      while(!s.isEmpty()) {

         //Push to the left until null is found
         if (temp != null) {
            s.push(temp);
            // Add the node value to the preOrder String
            preOrder += temp.value + ", ";
            temp = temp.left;
         } else {
            //Pop the node that can't be visited left anymore
            temp = s.pop();
            //Go to the right
            temp = temp.right;
         }
      } // while

      //print out the inorder traversal
      System.out.println(preOrder);
   }

   
   
   /**
    * in-order traversal
    * Done in the format of left ->node->right and saves the nodes popped
    * and prints the solution.
    *
    * @param root - the root of the tree
    */
   public void inOrderTraversal(Node root){
      //If tree is empty
      if (root == null){
         System.out.println("The tree is empty");
      }
      String inOrder = "";
      Stack<Node> s = new Stack<>();

      Node temp = root;

      while(true) {

         //Push to the left until null is found
         if (temp != null) {

            s.push(temp);
            temp = temp.left;
         } else {
            //end while when stack is empty
            if(s.isEmpty()){break;}

            // Add the temp value to the inorder string
            temp = s.pop();
            inOrder += temp.value + ", ";
            //Go to the right
            temp = temp.right;
         }
      } // while

      //print out the inorder traversal
      System.out.println(inOrder);
   }



   /**
    * post-order traversal
    * Done in the format of left->right->node and saves the nodes popped
    * and prints the solution. This implementation uses 2 stacks one for the
    * nodes visited and the other to count the amount of times a node has been
    * visited.
    *
    * @param root - the root of the tree
    */
   public void postOrderTraversal(Node root){
      //If tree is empty
      if (root == null){
         System.out.println("The tree is empty");
      }

      String postOrder = "";
      Stack<Node> s = new Stack<>();
      Stack<Integer> sCounter = new Stack<>();
      Node temp = root;

      s.push(temp);
      sCounter.push(1);

      while(!s.isEmpty()) {

         //keep track of the amount of times a node has been visited
         int counter = sCounter.pop();
         temp = s.peek(); //retrieve value but don't remove

         if (counter == 1) { // first visit

            sCounter.push(2); // mark for second visit

            //Push to the left until null is found
            if (temp.left != null) {

               sCounter.push(1); // Mark as first visit
               s.push(temp.left); // push into the stack
            }

         } else if (counter == 2) { // second visit

            sCounter.push(3); // Mark for third visit

            if (temp.right != null) {
               sCounter.push(1);// Mark first visit
               s.push(temp.right);// push the node onto the stack
            }

         } else { // third visit

               //Pop the node that can't been visited 3 times
               s.pop();
               //Add the value to postOrder String
               postOrder += temp.value + ", ";
         }
      } // while

      System.out.println(postOrder);
   } // Method preOrderTraversal
   
   
   
   /*
   a method to find the node in the tree
   with a specific value
   */
   public boolean find(Node root, int key){
	  //implement me
      return false;           
   }
   
   
   
   /*
   a method to find the node in the tree
   with a smallest key
   */
   public int getMin(Node root){
      //implement me
      return -1;
   }
  
  
  
   /*
   a method to find the node in the tree
   with a largest key
   */
   public int getMax(Node root){
	  //implement me
      return -1;
   }
   
   
   
   /*
   this method will not compile until getMax
   is implemented
   */
   public Node delete(Node root, int key){
      
      if(root == null){
         return root;
      }else if(key < root.value){
         root.left = delete(root.left, key);
      }else if(key > root.value){
         root.right = delete(root.right, key);
      }else{
         //node has been found
         if(root.left==null && root.right==null){
            //case #1: leaf node
            root = null;
         }else if(root.right == null){
            //case #2 : only left child
            root = root.left;
         }else if(root.left == null){
            //case #2 : only right child
            root = root.right;
         }else{
            //case #3 : 2 children
            root.value = getMax(root.left);
            root.left = delete(root.left, root.value);
         }
      }
      return root;  
   }
   
   
   
}



public class TreeDemo{
   public static void main(String[] args){
      BinarySearchTree t1  = new BinarySearchTree();
      t1.root = new Node(24);

      t1.insert(t1.root,80);
      t1.insert(t1.root,18);
      t1.insert(t1.root,9);
      t1.insert(t1.root,90);
      t1.insert(t1.root,22);
            
      System.out.print("in-order : ");
      t1.inOrderTraversal(t1.root);
      System.out.println();

      System.out.print("pre-order :   ");
      t1.preOrderTraversal(t1.root);
      System.out.println();

      System.out.print("post-order :   ");
      t1.postOrderTraversal(t1.root);
      System.out.println();
           
      
   }  
}