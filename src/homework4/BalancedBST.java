/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package balancedbst;

/**
 *
 * Vicky Lym
 * November 9, 2016
 */

// import java.util.*;
// import java.io.File;
// import java.io.FileNotFoundException;
// import java.io.IOException;
// import java.io.FileWriter;
// import java.util.Scanner;
import java.util.*;
import java.io.*;
import java.lang.*;

/**
 *
 * @author rMBP
 */
  
 /* Class SBBSTNode */
 class SBBSTNode
 {    
     SBBSTNode left, right, parent;
     int data;
     String region;
     String state;
     boolean dupDMACode;
     int treeReBalD;
     int numTreeInsertD;
     int height;
 
     /* Constructor */
     public SBBSTNode()
     {
         left = null;
         right = null;
         parent = null;
         data = 0;
         region = "";
         state = "";
         dupDMACode = false;
         treeReBalD = 0;
         numTreeInsertD = 0;
         height = 0;
     }
     /* Constructor */
     public SBBSTNode(int n, String rgn, String states, boolean dupDMACd, int treeBalD, int nmTreeInsertD)
     {
         left = null;
         right = null;
         parent = null;
         data = n;
         region = rgn;
         state = states;
         dupDMACode = dupDMACd;
         treeReBalD = treeBalD;
         numTreeInsertD = nmTreeInsertD;
         height = 0;
     }     
 }    
 
 /* Class SelfBalancingBinarySearchTree */
 class PreorderBalancedBST
 {
     private SBBSTNode root;     
     
     /* Constructor */
     // public SelfBalancingBinarySearchTree()
     public PreorderBalancedBST()
     {
         root = null;
     }
 
     /* Function to check if tree is empty */
     public boolean isEmpty()
     {
         return root == null;
     }
 
     /* Make the tree logically empty */
     public void clear()
     {
         root = null;
     }
     public void deleteMin()
     {
         root = deleteMin(root);
     }
     
     private SBBSTNode deleteMin(SBBSTNode t)
     {
         if (t.left == null)
             return t.right;
         t.left = deleteMin(t.left);
         return t;
     }
     
    public void deleteNode(int x, int treeReBalD)
    {
        root =  deleteNode(x, treeReBalD, root);
    }
     /* Function to insert data */
     public SBBSTNode insert(int data, String region, String state, boolean dupDMACode, int treeReBalD, int numTreeInsertD )
     {
         SBBSTNode y = new SBBSTNode(0, "", "", false, 0, 0);
         root = insert(data, region, state, dupDMACode, treeReBalD, numTreeInsertD, root, y);
         return root;
     }
     /* Function to get height of node */
     private int height(SBBSTNode t )
     {
         return t == null ? -1 : t.height;
     }
     /* Function to max of left/right node */
     private int max(int lhs, int rhs)
     {
         return lhs > rhs ? lhs : rhs;
     }
    private SBBSTNode minValueNode(SBBSTNode node)
    {
        SBBSTNode current = node;
 
        /* loop down to find the leftmost leaf */
        while (current.left != null)
           current = current.left;
 
        return current;
    }
     /* Function to insert data recursively */
     private SBBSTNode insert(int x, String rgon, String stt, boolean dupDMAC, int tBalD, int nTreeInD, SBBSTNode t, SBBSTNode z)
     {
        // int treeBalD, int nmTreeInsertD
        // int treeReBalR = 0;
        // int numTreeInsertR = 0;
         if (t == null)
         {
             t = new SBBSTNode(x, rgon, stt, dupDMAC, tBalD, nTreeInD);
             t.numTreeInsertD = t.numTreeInsertD + 1;
             t.parent = z;
             //     int treeReBalD;
         }
         else if (x < t.data)
         {
             t.left = insert( x, rgon, stt, dupDMAC, tBalD, nTreeInD, t.left, z );
             if (height( t.left ) - height( t.right ) == 2)
                 if (x < t.left.data)
                 {
                     t = rotateWithLeftChild( t );
                     t.treeReBalD = t.treeReBalD + 1;
                 }
                 else
                 {
                     t = doubleWithLeftChild( t );
                     t.treeReBalD = t.treeReBalD + 1;
                 }
         }
         else if (x > t.data)
         {
             t.right = insert( x, rgon, stt, dupDMAC, tBalD, nTreeInD, t.right, t );
             if (height( t.right ) - height( t.left ) == 2)
                 if (x > t.right.data) 
                 {
                     t = rotateWithRightChild( t );
                     t.treeReBalD = t.treeReBalD + 1;
                 }
                 else
                 {
                     t = doubleWithRightChild( t );
                     t.treeReBalD = t.treeReBalD + 1;
                 }
         }
         else
            ;  // Duplicate; do nothing

         t.height = max( height( t.left ), height( t.right ) ) + 1;
         return t;
     }
     /* Rotate binary tree node with left child */     
     private SBBSTNode rotateWithLeftChild(SBBSTNode k2)
     {
         SBBSTNode k1 = k2.left;
         k2.left = k1.right;
         k1.right = k2;
         k2.parent = k1;
         k2.height = max( height( k2.left ), height( k2.right ) ) + 1;
         k1.height = max( height( k1.left ), k2.height ) + 1;
         return k1;
     }
 
     /* Rotate binary tree node with right child */
     private SBBSTNode rotateWithRightChild(SBBSTNode k1)
     {
         SBBSTNode k2 = k1.right;
         k1.right = k2.left;
         k2.left = k1;
         k2.parent = k1.parent;
         k1.parent = k2;
         k1.height = max( height( k1.left ), height( k1.right ) ) + 1;
         k2.height = max( height( k2.right ), k1.height ) + 1;
         return k2;
     }
     /**
      * Double rotate binary tree node: first left child
      * with its right child; then node k3 with new left child */
     private SBBSTNode doubleWithLeftChild(SBBSTNode k3)
     {
         k3.left = rotateWithRightChild( k3.left );
         return rotateWithLeftChild( k3 );
     }
     /**
      * Double rotate binary tree node: first right child
      * with its left child; then node k1 with new right child */      
     private SBBSTNode doubleWithRightChild(SBBSTNode k1)
     {
         k1.right = rotateWithLeftChild( k1.right );
         return rotateWithRightChild( k1 );
     }    
     /* Functions to count number of nodes */
     public int countNodes()
     {
         return countNodes(root);
     }
     private int countNodes(SBBSTNode r)
     {
         if (r == null)
             return 0;
         else
         {
             int l = 1;
             l += countNodes(r.left);
             l += countNodes(r.right);
             return l;
         }
     }
     /* Functions to search for an element */
     public boolean search(int val)
     {
         return search(root, val);
     }
     private boolean search(SBBSTNode r, int val)
     {
         boolean found = false;
         while ((r != null) && !found)
         {
             int rval = r.data;
             if (val < rval)
                 r = r.left;
             else if (val > rval)
                 r = r.right;
             else
             {
                 found = true;
                 break;
             }
             found = search(r, val);
         }
         return found;
     }
     /* Function for inorder traversal */
     public void inorder(int lenRegionMax)
     {
         inorder(root, lenRegionMax);
     }
     private void inorder(SBBSTNode r, int lenRegionMax)
     {
        int diffLen; 
                           
        if (r != null)
        {
            diffLen = lenRegionMax - (r.region.length());
                        
            inorder(r.left, lenRegionMax);
                   
            System.out.print("(" + r.data + ")" + " " + r.region + " ");
            for (int i = 0; i < diffLen; i++)
                System.out.print(" ");
        
            System.out.println(r.state + " ");
            
            inorder(r.right, lenRegionMax);
         }
     }
     /* Function for preorder traversal */
     public int[] preorder(int lenRegionMax)
     {
         int nTreeInsertD;
         int nTreeReBlD;
         int[] metrics = new int[2];
         metrics[0] = 0;
         metrics[1] = 0;
         
         metrics = preorder(root, lenRegionMax, metrics);
         return metrics;
     }
     private int[] preorder(SBBSTNode r, int lenRegionMax, int[] metricsC)
     {      
        int diffLen; 
        
         if (r != null)
         {         
            diffLen = lenRegionMax - (r.region.length());

            metricsC[0] = metricsC[0] + r.treeReBalD;
            metricsC[1] = metricsC[1] + r.numTreeInsertD;
        
            System.out.print("(" + r.data + ")" + " " + r.region + " ");
            for (int i = 0; i < diffLen; i++)   
                System.out.print(" ");
        
            System.out.println(r.state + " ");
            preorder(r.left, lenRegionMax, metricsC);             
            preorder(r.right, lenRegionMax, metricsC);
         }
         return metricsC;
     }
     /* Function for postorder traversal */
     public void postorder(int lenRegionMax)
     {
         postorder(root, lenRegionMax);
     }
     private void postorder(SBBSTNode r, int lenRegionMax)
     {
         int diffLen; 
        
         if (r != null)
         {         
            diffLen = lenRegionMax - (r.region.length());
            
            postorder(r.left, lenRegionMax);             
            postorder(r.right, lenRegionMax);
        
            System.out.print("(" + r.data + ")" + " " + r.region + " ");
            for (int i = 0; i < diffLen; i++)
                System.out.print(" ");
        
            System.out.println(r.state + " ");
         }
     }  
     // Get Balance factor of node N
    int getBalance(SBBSTNode N)
    {
        if (N == null)
            return 0;
        return height(N.left) - height(N.right);
    }
    
    SBBSTNode rightRotate(SBBSTNode y)
    {
        SBBSTNode x = y.left;
        SBBSTNode T2 = x.right;
 
        // Perform rotation
        x.right = y;
        y.parent = x;
        y.left = T2;
        y.left.parent = y;
 
        // Update heights
        y.height = max(height(y.left), height(y.right)) + 1;
        x.height = max(height(x.left), height(x.right)) + 1;
 
        // Return new root
        return x;
    }
 
    // A utility function to left rotate subtree rooted with x
    // See the diagram given above.
    SBBSTNode leftRotate(SBBSTNode x)
    {
        SBBSTNode y = x.right;
        SBBSTNode T2 = y.left;
 
        // Perform rotation
        y.left = x;
        y.parent = x;
        x.right = T2;
        x.right.parent = x;
 
        //  Update heights
        x.height = max(height(x.left), height(x.right)) + 1;
        y.height = max(height(y.left), height(y.right)) + 1;
 
        // Return new root
        return y;
    }
    
    public SBBSTNode min(SBBSTNode t)
    {
        if (t.left == null)
            return t;
        return min(t.left);
    }
    
    public void delete(int x, int treeReBalD, int numTreeInsertD)
    {
        root = delete(root, x, root.treeReBalD, root.numTreeInsertD);
    }
    
    private SBBSTNode delete(SBBSTNode root, int x, int treeReBalD, int numTreeInsertD )
    {
        if (root == null)
            return root;
        
        // If the key to be deleted is smaller than
        // the root's key, then it lies in left subtree
        if (x < root.data)
        {
            // treeReBalD = root.left.treeReBalD;
            // numTreeInsertD = root.left.numTreeInsertD;
            root.left = delete(root.left, x, treeReBalD, numTreeInsertD);
        }
        // If the key to be deleted is greater than the
        // root's key, then it lies in right subtree
        else if (x > root.data)
        {
            // treeReBalD = root.right.treeReBalD;
            // numTreeInsertD = root.right.numTreeInsertD;
            root.right = delete(root.right, x, treeReBalD, numTreeInsertD);
        }
        else
        {
           if (root.right ==  null) 
               return root.left;
           if (root.left == null)
               return root.right;
           SBBSTNode temp = root;
           // int numTreeRBD = 0;
           // int numTreeID = 0;
           // numTreeRBD = root.treeReBalD;
           // numTreeID = root.numTreeInsertD;
           root.parent.treeReBalD = root.parent.treeReBalD + root.treeReBalD;
           root.parent.numTreeInsertD = root.parent.numTreeInsertD + root.numTreeInsertD;      
           root = min(temp.right);
           root.right = deleteMin(temp.right);
           root.left = temp.left;
        }
        
        // If the tree had only one node then return
        if (root == null)
            return root;
 
        // STEP 2: UPDATE HEIGHT OF THE CURRENT NODE
        root.height = max(height(root.left), height(root.right)) + 1;
 
        // STEP 3: GET THE BALANCE FACTOR OF THIS NODE (to check whether
        //  this node became unbalanced)
        int balance = getBalance(root);           
 
        // If this node becomes unbalanced, then there are 4 cases
        // Left Left Case
        if (balance > 1 && getBalance(root.left) >= 0)
        {
            root.treeReBalD = root.treeReBalD + 1;
            return rightRotate(root);
        }
        // Left Right Case
        if (balance > 1 && getBalance(root.left) < 0)
        {
            root.left.treeReBalD = root.left.treeReBalD + 1;
            root.left = leftRotate(root.left);
            root.treeReBalD = root.treeReBalD + 1;
            return rightRotate(root);
        }
 
        // Right Right Case
        if (balance < -1 && getBalance(root.right) <= 0)
        {
            root.treeReBalD = root.treeReBalD + 1;
            return leftRotate(root);
        }
        // Right Left Case
        if (balance < -1 && getBalance(root.right) > 0)
        {
            root.right.treeReBalD = root.right.treeReBalD + 1;
            root.right = rightRotate(root.right);
            root.treeReBalD = root.treeReBalD + 1;
            return leftRotate(root);
        }
 
        return root;
    }    
    /* private SBBSTNode insert(int x, String rgon, String stt, int tBalD, int nTreeInD, SBBSTNode t)
    */ 
     private SBBSTNode deleteNode(int x, int treeReBalD, SBBSTNode root)
    {
        // STEP 1: PERFORM STANDARD BST DELETE
        if (root == null)
            return root;
        
        if (x < root.data)
            root.left = deleteNode(x, treeReBalD, root.left);
 
        // If the key to be deleted is smaller than
        // the root's key, then it lies in left subtree
        if (x < root.data)
            root.left = deleteNode(x, treeReBalD, root.left);
 
        // If the key to be deleted is greater than the
        // root's key, then it lies in right subtree
        else if (x > root.data)
            root.right = deleteNode(x, treeReBalD, root.right);
 
        // if key is same as root's key, then this is the node
        // to be deleted
        else
        {
 
            // node with only one child or no child
            if ((root.left == null) || (root.right == null))
            {
                SBBSTNode temp = null;
                if (temp == root.left)
                    temp = root.right;
                else
                    temp = root.left;
 
                // No child case
                if (temp == null)
                {
                    temp = root;
                    root = null;
                }
                else   // One child case
                    root = temp; // Copy the contents of
                                 // the non-empty child
            }
            else
            {
 
                // node with two children: Get the inorder
                // successor (smallest in the right subtree)
                SBBSTNode temp = minValueNode(root.right);
                // SBBSTNode temp = minValueNode(root.left);
 
                // Copy the inorder successor's data to this node
                root.data = temp.data;
 
                // Delete the inorder successor
                root.right = deleteNode(temp.data, treeReBalD, root.right);
                // root.left = deleteNode(temp.data, treeReBalD, root.left);
            }
        }
        
        // If the tree had only one node then return
        if (root == null)
            return root;
 
        // STEP 2: UPDATE HEIGHT OF THE CURRENT NODE
        root.height = max(height(root.left), height(root.right)) + 1;
 
        // STEP 3: GET THE BALANCE FACTOR OF THIS NODE (to check whether
        //  this node became unbalanced)
        int balance = getBalance(root);           
 
        // If this node becomes unbalanced, then there are 4 cases
        // Left Left Case
        if (balance > 1 && getBalance(root.left) >= 0)
            return rightRotate(root);
 
        // Left Right Case
        if (balance > 1 && getBalance(root.left) < 0)
        {
            root.left = leftRotate(root.left);
            return rightRotate(root);
        }
 
        // Right Right Case
        if (balance < -1 && getBalance(root.right) <= 0)
            return leftRotate(root);
 
        // Right Left Case
        if (balance < -1 && getBalance(root.right) > 0)
        {
            root.right = rightRotate(root.right);
            return leftRotate(root);
        }
 
        return root;
    }    
     
 }

/*  Vicky add more code
*/
     /* Class SBBSTNodeRegion */
 class SBBSTNodeR
 {    
     SBBSTNodeR leftR, rightR, parentR;
     String regionR;
     int dataR;
     String stateR;
     boolean dupRegion;
     int treeReBalDR;
     int numTreeInsertDR;
     int heightR;
 
     /* Constructor */
     public SBBSTNodeR()
     {
         leftR = null;
         rightR = null;
         regionR = "";
         dataR = 0;
         stateR = "";
         dupRegion = false;
         treeReBalDR = 0;
         numTreeInsertDR = 0;
         heightR = 0;
     }
     /* Constructor */
     public SBBSTNodeR(String rgn, int n, String states, boolean dupRgn, int treeBalDR, int nmTreeInsertDR)
     {
         leftR = null;
         rightR = null;
         regionR = rgn;
         dataR = n;
         stateR = states;
         dupRegion = dupRgn;
         treeReBalDR = treeBalDR;
         numTreeInsertDR = nmTreeInsertDR;
         heightR = 0;
     }     
 }

/* Class SelfBalancingBinarySearchTree */
 class PreorderBalancedBSTR
 {
     private SBBSTNodeR rootR;  
 
     /* Constructor */
     // public SelfBalancingBinarySearchTree()
     public PreorderBalancedBSTR()
     {
         rootR = null;
     }
 
     /* Function to check if tree is empty */
     public boolean isEmptyR()
     {
         return rootR == null;
     }
 
     /* Make the tree logically empty */
     public void clearR()
     {
         rootR = null;
     }
     
     public void deleteMinR()
     {
         rootR = deleteMinR(rootR);
     }
     
     private SBBSTNodeR deleteMinR(SBBSTNodeR tR)
     {
         if (tR.leftR == null)
             return tR.rightR;
         tR.leftR = deleteMinR(tR.leftR);
         return tR;
     }
     /* Function to insert data */
     public SBBSTNodeR insertR(String regionR, int dataR, String stateR, boolean dupRegion, int treeReBalDR, int numTreeInsertDR)
     {
         SBBSTNodeR yR = new SBBSTNodeR("",0,"", false, 0,0);
         rootR = insertR(regionR, dataR, stateR, dupRegion, treeReBalDR, numTreeInsertDR, rootR, yR);
         return rootR;
     }
     /* Function to get height of node */
     private int heightR(SBBSTNodeR tR )
     {
         return tR == null ? -1 : tR.heightR;
     }
     /* Function to max of left/right node */
     private int maxR(int lhsR, int rhsR)
     {
         return lhsR > rhsR ? lhsR : rhsR;
     }
     /* Given a non-empty binary search tree, return the
       node with minimum key value found in that tree.
       Note that the entire tree does not need to be
       searched. */
    private SBBSTNodeR minValueNodeR(SBBSTNodeR nodeR)
    {
        SBBSTNodeR currentR = nodeR;
 
        /* loop down to find the leftmost leaf */
        while (currentR.leftR != null)
           currentR = currentR.leftR;
 
        return currentR;
    }

     /* Function to insert data recursively */
    
     public SBBSTNodeR insertR(String rgon, int xR, String stt, boolean dupRg, int tBalDR, int nTreeInDR, SBBSTNodeR tR, SBBSTNodeR zR)
     // Pair<String, String>
     {
         if (tR == null)
         {
             tR = new SBBSTNodeR(rgon, xR, stt, dupRg, tBalDR, nTreeInDR);
             tR.numTreeInsertDR = tR.numTreeInsertDR + 1;
             tR.parentR = zR;
         }
         else if ((rgon.compareTo(tR.regionR) < 0) && (!(tR.dupRegion)))
         {
             tR.leftR = insertR( rgon, xR, stt, dupRg, tBalDR, nTreeInDR, tR.leftR, zR );
             if (heightR( tR.leftR ) - heightR( tR.rightR ) == 2)
                 if (rgon.compareTo(tR.leftR.regionR) < 0)
                 {
                     tR = rotateWithLeftChildR( tR );
                     tR.treeReBalDR = tR.treeReBalDR + 1;
                 }
                 else
                 {
                     tR = doubleWithLeftChildR( tR );
                     tR.treeReBalDR = tR.treeReBalDR + 1;
                 }
         }
         else if ((rgon.compareTo(tR.regionR) > 0) && (!(tR.dupRegion)))
         {
             tR.rightR = insertR( rgon, xR, stt, dupRg, tBalDR, nTreeInDR, tR.rightR, tR);
             if (heightR( tR.rightR ) - heightR( tR.leftR ) == 2)
                 if (rgon.compareTo(tR.rightR.regionR) > 0)
                 {
                     tR = rotateWithRightChildR( tR );
                     tR.treeReBalDR = tR.treeReBalDR + 1;
                 }
                 else
                 {
                     tR = doubleWithRightChildR( tR );
                     tR.treeReBalDR = tR.treeReBalDR + 1;
                 }
         }
         else
         {
            ;  // Duplicate; do nothing 
            // SBBSTNodeR wR = new SBBSTNodeR(rgon,xR,stt, true, 0,0);
            // wR.leftR = null;
            // wR.rightR = null;
            // wR.heightR = 0;
         }

         tR.heightR = maxR( heightR( tR.leftR ), heightR( tR.rightR ) ) + 1;
         return tR;
     }
     /* Rotate binary tree node with left child */     
     private SBBSTNodeR rotateWithLeftChildR(SBBSTNodeR k5)
     {
         SBBSTNodeR k4 = k5.leftR;
         k5.leftR = k4.rightR;
         k4.rightR = k5;
         k5.heightR = maxR( heightR( k5.leftR ), heightR( k5.rightR ) ) + 1;
         k4.heightR = maxR( heightR( k4.leftR ), k5.heightR ) + 1;
         return k4;
     }
 
     /* Rotate binary tree node with right child */
     private SBBSTNodeR rotateWithRightChildR(SBBSTNodeR k4)
     {
         SBBSTNodeR k5 = k4.rightR;
         k4.rightR = k5.leftR;
         k5.leftR = k4;
         k4.heightR = maxR( heightR( k4.leftR ), heightR( k4.rightR ) ) + 1;
         k5.heightR = maxR( heightR( k5.rightR ), k4.heightR ) + 1;
         return k5;
     }
     /**
      * Double rotate binary tree node: first left child
      * with its right child; then node k3 with new left child */
     private SBBSTNodeR doubleWithLeftChildR(SBBSTNodeR k6)
     {
         k6.leftR = rotateWithRightChildR( k6.leftR );
         return rotateWithLeftChildR( k6 );
     }
     /**
      * Double rotate binary tree node: first right child
      * with its left child; then node k1 with new right child */      
     private SBBSTNodeR doubleWithRightChildR(SBBSTNodeR k4)
     {
         k4.rightR = rotateWithLeftChildR( k4.rightR );
         return rotateWithRightChildR( k4 );
     }    
     /* Functions to count number of nodes */
     public int countNodesR()
     {
         return countNodesR(rootR);
     }
     private int countNodesR(SBBSTNodeR rR)
     {
         if (rR == null)
             return 0;
         else
         {
             int lR = 1;
             lR += countNodesR(rR.leftR);
             lR += countNodesR(rR.rightR);
             return lR;
         }
     }
     /* Functions to search for an element */
     public boolean searchR(String regionStr)
     {
         return searchR(rootR, regionStr);
     }
     private boolean searchR(SBBSTNodeR rR, String regionStr)
     {
         boolean found = false;
         String cvsSplitBy3 = ",";
         String[] piecesStr = regionStr.split(cvsSplitBy3);
         int sizeOfStrInput = piecesStr.length;
         String newRegionStr = "";
         if (!(sizeOfStrInput == 0))
         {   
             for (int i = 0; i < sizeOfStrInput; i++)
             {
               if (i == 0)
                  newRegionStr = newRegionStr + piecesStr[i];
               else
                newRegionStr = newRegionStr + " " + piecesStr[i];
             }
             regionStr = newRegionStr;
         }
         while ((rR != null) && (!(found)))
         {
             String regionRStr = rR.regionR;
             if (regionStr.compareTo(regionRStr) < 0)
                 rR = rR.leftR;
             else if (regionStr.compareTo(regionRStr) > 0)
                 rR = rR.rightR;
             else
             {
                 found = true;
                 break;
             }
             found = searchR(rR, regionStr);
         }
         return found;
     }
     /* Function for inorder traversal */
     public void inorderR(int lenRegionMax)
     {
         inorderR(rootR, lenRegionMax);
     }
     private void inorderR(SBBSTNodeR rR, int lenRegionMax)
     {
        int diffLen; 
                           
        if (rR != null)
        {
            diffLen = lenRegionMax - (rR.regionR.length());
                        
            inorderR(rR.leftR, lenRegionMax);

            System.out.print(rR.regionR + " ");
            for (int i = 0; i < diffLen; i++)
                System.out.print(" ");
        
            System.out.println("(" + rR.dataR + ")" + " " + rR.stateR + " ");
            
            inorderR(rR.rightR, lenRegionMax);
        }         
     }
          /* Function for preorder traversal */
     public int[] preorderR(int lenRegionMax)
     {
         int[] metricsR = new int[2];
         metricsR[0] = 0;
         metricsR[1] = 0;
         
         metricsR = preorderR(rootR, lenRegionMax, metricsR);
         return metricsR;
     }
     private int[] preorderR(SBBSTNodeR rR, int lenRegionMax, int[] metricsCR)
     {      
        int diffLen; 
        
         if (rR != null)
         {         
            diffLen = lenRegionMax - (rR.regionR.length());

            metricsCR[0] = metricsCR[0] + rR.treeReBalDR;
            metricsCR[1] = metricsCR[1] + rR.numTreeInsertDR;
        
            diffLen = lenRegionMax - (rR.regionR.length());
        
            System.out.print(rR.regionR + " ");
            for (int i = 0; i < diffLen; i++)
                System.out.print(" ");
        
            System.out.println("(" + rR.dataR + ")" + " " + rR.stateR + " ");
            preorderR(rR.leftR, lenRegionMax, metricsCR);             
            preorderR(rR.rightR, lenRegionMax, metricsCR);
         }
         return metricsCR;
     }

     /* Function for postorder traversal */
     public void postorderR(int lenRegionMax)
     {
         postorderR(rootR, lenRegionMax);
     }
     private void postorderR(SBBSTNodeR rR, int lenRegionMax)
     {
         int diffLen; 
        
         if (rR != null)
         {         
            diffLen = lenRegionMax - (rR.regionR.length());
            
            postorderR(rR.leftR, lenRegionMax);             
            postorderR(rR.rightR, lenRegionMax);
        
            System.out.print(rR.regionR + " ");
            for (int i = 0; i < diffLen; i++)
                System.out.print(" ");
        
            System.out.println("(" + rR.dataR + ")" + " " + rR.stateR + " ");
         }
     }  
     
         // Get Balance factor of node N
    int getBalanceR(SBBSTNodeR NR)
    {
        if (NR == null)
            return 0;
        return heightR(NR.leftR) - heightR(NR.rightR);
    }
    
    SBBSTNodeR rightRotateR(SBBSTNodeR yR)
    {
        SBBSTNodeR xR = yR.leftR;
        SBBSTNodeR T2R = xR.rightR;
 
        // Perform rotation
        xR.rightR = yR;
        yR.parentR = xR;
        yR.leftR = T2R;
        if (!(yR.leftR == null))
        yR.leftR.parentR = yR;
 
        // Update heights
        yR.heightR = maxR(heightR(yR.leftR), heightR(yR.rightR)) + 1;
        xR.heightR = maxR(heightR(xR.leftR), heightR(xR.rightR)) + 1;
 
        // Return new root
        return xR;
    }
 
    // A utility function to left rotate subtree rooted with x
    // See the diagram given above.
    SBBSTNodeR leftRotateR(SBBSTNodeR xR)
    {
        SBBSTNodeR yR = xR.rightR;
        SBBSTNodeR T2R = yR.leftR;
 
        // Perform rotation
        yR.leftR = xR;
        yR.parentR = xR;
        xR.rightR = T2R;
        xR.rightR.parentR = xR;
 
        //  Update heights
        xR.heightR = maxR(heightR(xR.leftR), heightR(xR.rightR)) + 1;
        yR.heightR = maxR(heightR(yR.leftR), heightR(yR.rightR)) + 1;
 
        // Return new root
        return yR;
    }
    
    public SBBSTNodeR minR(SBBSTNodeR tR)
    {
        if (tR.leftR == null)
            return tR;
        return minR(tR.leftR);
    }
     
    public void deleteR(String rgonR, int treeReBalDR, int numTreeInsertDR)
    {
        rootR = deleteR(rootR, rgonR, rootR.treeReBalDR, rootR.numTreeInsertDR);
    }
    
    private SBBSTNodeR deleteR(SBBSTNodeR rootR, String rgonR, int treeReBalDR, int numTreeInsertDR )
    {
        if (rootR == null)
            return rootR;
        
        // If the key to be deleted is smaller than
        // the root's key, then it lies in left subtree
        // if (xR < rootR.dataR)    
        if (rgonR.compareTo(rootR.regionR) < 0)
        {
            // treeReBalD = root.left.treeReBalD;
            // numTreeInsertD = root.left.numTreeInsertD;
            rootR.leftR = deleteR(rootR.leftR, rgonR, treeReBalDR, numTreeInsertDR);
        }
        // If the key to be deleted is greater than the
        // root's key, then it lies in right subtree
        // else if (xR > rootR.dataR)
        else if (rgonR.compareTo(rootR.regionR) > 0)
        {
            // treeReBalD = root.right.treeReBalD;
            // numTreeInsertD = root.right.numTreeInsertD;
            rootR.rightR = deleteR(rootR.rightR, rgonR, treeReBalDR, numTreeInsertDR);
        }
        else
        {
           if (rootR.rightR ==  null) 
               return rootR.leftR;
           if (rootR.leftR == null)
               return rootR.rightR;
           SBBSTNodeR tempR = rootR;

           rootR.parentR.treeReBalDR = rootR.parentR.treeReBalDR + rootR.treeReBalDR;
           rootR.parentR.numTreeInsertDR = rootR.parentR.numTreeInsertDR + rootR.numTreeInsertDR;      
           rootR = minR(tempR.rightR);
           rootR.rightR = deleteMinR(tempR.rightR);
           rootR.leftR = tempR.leftR;
        }
        
        // If the tree had only one node then return
        if (rootR == null)
            return rootR;
 
        // STEP 2: UPDATE HEIGHT OF THE CURRENT NODE
        rootR.heightR = maxR(heightR(rootR.leftR), heightR(rootR.rightR)) + 1;
 
        // STEP 3: GET THE BALANCE FACTOR OF THIS NODE (to check whether
        //  this node became unbalanced)
        int balanceR = getBalanceR(rootR);           
 
        // If this node becomes unbalanced, then there are 4 cases
        // Left Left Case
        if (balanceR > 1 && getBalanceR(rootR.leftR) >= 0)
        {
            rootR.treeReBalDR = rootR.treeReBalDR + 1;
            return rightRotateR(rootR);
        }
        // Left Right Case
        if (balanceR > 1 && getBalanceR(rootR.leftR) < 0)
        {
            rootR.leftR.treeReBalDR = rootR.leftR.treeReBalDR + 1;
            rootR.leftR = leftRotateR(rootR.leftR);
            rootR.treeReBalDR = rootR.treeReBalDR + 1;
            return rightRotateR(rootR);
        }
 
        // Right Right Case
        if (balanceR < -1 && getBalanceR(rootR.rightR) <= 0)
        {
            rootR.treeReBalDR = rootR.treeReBalDR + 1;
            return leftRotateR(rootR);
        }
        // Right Left Case
        if (balanceR < -1 && getBalanceR(rootR.rightR) > 0)
        {
            rootR.rightR.treeReBalDR = rootR.rightR.treeReBalDR + 1;
            rootR.rightR = rightRotateR(rootR.rightR);
            rootR.treeReBalDR = rootR.treeReBalDR + 1;
            return leftRotateR(rootR);
        }
 
        return rootR;
    }    
 }
 
 /* Class SelfBalancingBinarySearchTreeTest */

// public class SelfBalancingBinarySearchTreeTest
public class BalancedBST
{

   int duplicateDMACodeItems = 0;
   int duplicateRegionItems = 0;
    
    public static void main(String[] args)
    {            
        /* Creating object of SelfBalancingBinarySearchTree */
    //    SelfBalancingBinarySearchTree sbbst = new SelfBalancingBinarySearchTree(); 
        PreorderBalancedBST sbbst3 = new PreorderBalancedBST();
        PreorderBalancedBSTR sbbst4 = new PreorderBalancedBSTR();
        SBBSTNode sbbst6 = new SBBSTNode();
        SBBSTNodeR sbbst7 = new SBBSTNodeR();
        boolean deletedItems = false;
        boolean deletedItemsR = false;
        boolean insertedItems = false;
        boolean insertedItemsR = false;

        System.out.println("SelfBalancingBinarySearchTree Test\n");          
        char ch;
        int numberOfInserts = 0;
        int numberOfRebalances = 0;
        int numInsertD = 0;
        int numtreeReBalD = 0;
        int numInsertDR = 0;
        int numtreeReBalDR = 0;
        
        int[] metricsVal = new int[2];
        int[] metricsValR = new int[2];
        String cvsSplitBy2 = ",";
        String cvsSplitBy4 = "?";
        // String lineNxt = " ";

        int lenRegion = 0;
        String fileName = "dma.txt";
        
        int lenMaxRegion = 0;
        long cpuTime = 0;
        lenMaxRegion = readFile(fileName, sbbst3, sbbst4);
                
        /*  Perform tree operations  */
        Scanner scan = new Scanner(System.in);
        // Scanner scan = new Scanner(System.in).useDelimiter(cvsSplitBy2);

        do    
        {
            System.out.println("\nSelfBalancingBinarySearchTree Operations\n");
            System.out.println("Choose 0 for list of statistics from file input");
            System.out.println(" ");
            System.out.println("Following are options for the DMA Code Key List");
            System.out.println("1. insert");
            System.out.println("2. search");
            System.out.println("3. count nodes");
            System.out.println("4. check empty");
            System.out.println("5. clear tree");
            System.out.println("6. delete from");
            System.out.println(" ");
            System.out.println("Following are options for the Region Key List");
            System.out.println("7.  insert");
            System.out.println("8.  search");
            System.out.println("9.  count nodes");
            System.out.println("10. check empty");
            System.out.println("11. clear tree");
            System.out.println("12. delete from");
            
            int choice = scan.nextInt();            
            switch (choice)
            {
            case 0 :
                  /*  Display tree  */  
                System.out.print("\nPart 2, DMA Code Key Preorder : \n");
                metricsVal = sbbst3.preorder(lenMaxRegion);
                System.out.printf("\nPart 2, Metrics Number of times tree rebalanced %d number of inserts %d\n", metricsVal[0], metricsVal[1]);       
        
                 /*  Display tree   */  
                System.out.print("\nPart 3 - Region Key Preorder : \n");
                metricsValR = sbbst4.preorderR(lenMaxRegion);
                System.out.printf("\nPart 3, Metrics Number of times tree rebalanced %d number of inserts %d\n", metricsValR[0], metricsValR[1]);
        
                System.out.print("\nEXTRA CREDIT - DMA Code Key Inorder : \n");
                sbbst3.inorder(lenMaxRegion);

                System.out.print("\nEXTRA CREDIT - Region Key Inorder : \n");
                sbbst4.inorderR(lenMaxRegion);
            
                // System.out.print("\nPost order : \n");
                // sbbst3.postorder(lenMaxRegion);
        
                System.out.println();    
                break;        
            case 1 : 
                System.out.println("Enter DMA code, Region, and State element separated by commas to insert");
 
                String lineNxt = scan.next();
                String[] pieces = lineNxt.split(cvsSplitBy2);
                boolean stoploop = false;
                // if (pieces[2].equals(""))
                int sizeOfInput = pieces.length;
                String piecesState = "";
                if (sizeOfInput == 3)
                    piecesState = pieces[2];
                else
                {
                    // String lineNxtAgn = scan.next();
                    // String[] piecesNxt = lineNxtAgn.split(cvsSplitBy2);
                    int sizeOfRegionInput = sizeOfInput - 2;
                    int sizeOf2ndInput = 2;
                    
                    if (sizeOfRegionInput > 1)
                    {
                        for (int i = 2; i < sizeOfRegionInput+1; i++)
                        {
                            if (i < sizeOfRegionInput+1)
                                pieces[1] = pieces[1] + " ";
                            pieces[1] = pieces[1] + pieces[i];
                            sizeOf2ndInput = sizeOf2ndInput + 1;
                        }
                    }
                    piecesState = pieces[sizeOf2ndInput];
                }               
                try {
                int dmacode = Integer.parseInt(pieces[0]);
                boolean duplicateDMACode = false;
                lenRegion = (pieces[1].length());
                if (lenMaxRegion < lenRegion)
                   lenMaxRegion = lenRegion;
                sbbst6 = sbbst3.insert(dmacode, pieces[1], piecesState, duplicateDMACode, numtreeReBalD, numInsertD);
                insertedItems = true;  
                } catch (Exception e)
                {     
                // dma code,region,state
                    System.out.println("First line of input is bad data or Headings");
                     System.out.println("Will read second record to see if valid data");
                }
                // sbbst.insert( scan.nextInt() );                     
                break;                          
            case 2 : 
                System.out.println("Enter integer DMA Code element to search");
                System.out.println("Search result : "+ sbbst3.search( scan.nextInt() ));
                break;                                          
            case 3 : 
                System.out.println("Nodes = "+ sbbst3.countNodes());
                break;     
            case 4 : 
                System.out.println("Empty status = "+ sbbst3.isEmpty());
                break;     
            case 5 : 
                System.out.println("\nTree Cleared");
                sbbst3.clear();
                break;        
            case 6 : 
                System.out.print("\nDMA Code Key Pre order Before Delete : \n");
                metricsVal = sbbst3.preorder(lenMaxRegion);
                System.out.println("\nEnter integer element to delete");
                int deleteItem = scan.nextInt();
                sbbst3.delete(deleteItem, numtreeReBalD, numInsertD);
                deletedItems = true;
                break; 
            case 7 : 
                System.out.println("Enter Region, DMA code, and State element separated by commas to insert");
                System.out.println("For Region, Replace any space with a comma.");
                System.out.println("Region will be case sensitive.");
                String lineNxtR = scan.next();
                String[] piecesR1 = lineNxtR.split(cvsSplitBy2);

                boolean stoploopR = false;
                int sizeOfInputR = piecesR1.length;
                int loopCntr = 0;
                String piecesStateR1 = "";
                
                int dmacodeR = 0;
                String stateName = "";
                String regionName = "";    
                if (sizeOfInputR == 3)
                { 
                    regionName = piecesR1[0];
                    dmacodeR = Integer.parseInt(piecesR1[1]);
                    stateName = piecesR1[2];  
                }
                else
                {
                    int sizeOfInputRegion = sizeOfInputR - 2;
                    dmacodeR = Integer.parseInt(piecesR1[sizeOfInputRegion]);
                    stateName = piecesR1[sizeOfInputRegion+1];
                    for (int i = 0; i < sizeOfInputRegion; i++)
                    {
                        if ((i < sizeOfInputRegion) && (i != 0))
                            regionName = regionName + " ";
                        regionName = regionName + piecesR1[i];
                    }
                }              
                try {
                boolean duplicateRegion = false;
                lenRegion = (regionName.length());
                if (lenMaxRegion < lenRegion)
                   lenMaxRegion = lenRegion;
                sbbst7 = sbbst4.insertR(regionName, dmacodeR, stateName, duplicateRegion, numtreeReBalDR, numInsertDR);                    
                insertedItemsR = true; 
                } catch (Exception e)
                {     
                // region,dma code,state
                    System.out.println("First line of input is bad data or Headings");
                    System.out.println("Will read second record to see if valid data");
                }                   
                break; 
            case 8 : 
                System.out.println("Enter Region element to search. Replace any space with a comma.");
                System.out.println("Case sensitive, too.");
                System.out.println("Search result : "+ sbbst4.searchR( scan.next() ));
                break; 
            case 9 : 
                System.out.println("Nodes = "+ sbbst4.countNodesR());
                break;     
            case 10 : 
                System.out.println("Empty status = "+ sbbst4.isEmptyR());
                break;     
            case 11 : 
                System.out.println("\nTree Cleared");
                sbbst4.clearR();
                break;  
            case 12 : 
                System.out.println("Enter Region element to Delete. Replace any space with a comma.");
                System.out.println("Case sensitive, too.");
                String deleteItemR = scan.next();
                String[] piecesRD = deleteItemR.split(cvsSplitBy2); 
                int sizeOfInputRD = piecesRD.length;
                deleteItemR = "";
                for (int i = 0; i < sizeOfInputRD; i++)
                {
                    if ((i < sizeOfInputRD) && (i != 0 ))
                       deleteItemR = deleteItemR + " "; 
                    deleteItemR = deleteItemR + piecesRD[i];
                }
                System.out.println("Part 3, Region Key Preorder Before Deletion :");
                metricsValR = sbbst4.preorderR(lenMaxRegion);
                sbbst4.deleteR(deleteItemR, numtreeReBalDR, numInsertDR);
                deletedItemsR = true;
                break;   
 
            default : 
                System.out.println("Wrong Entry \n "); 
                break;   
            }

            System.out.println("\nDo you want to continue (Type y or n) \n");
            ch = scan.next().charAt(0);                        
        } while (ch == 'Y'|| ch == 'y');  
        
        if (deletedItems || insertedItems)
        {
            System.out.print("\nPart 2, DMA Code Key Preorder After Insertions and/or Deletions : \n");
            metricsVal = sbbst3.preorder(lenMaxRegion);
        }
        
        if (deletedItemsR || insertedItemsR)
        {
            System.out.print("\nPart 3, Region Key Preorder After Insertions and/or Deletions : \n");
            metricsValR = sbbst4.preorderR(lenMaxRegion);
        }
        
    }
    
    public static int readFile(String fileNm, PreorderBalancedBST sbbst, PreorderBalancedBSTR sbbst2)
    {   
        SBBSTNode sbbst8 = new SBBSTNode();
        SBBSTNodeR sbbst9 = new SBBSTNodeR(); 
        int rowsCols = 100;
        String[] multDMA = new String[rowsCols + rowsCols + rowsCols];   
        String[] multRegion = new String[rowsCols + rowsCols + rowsCols];
        int indxDMA = 0;
        int indxRegion = 0;
        int tempIndx = 0;
        String tempInt;
 
        long estimatedTime = 0;
        int regionLenMax = 0;
        int regionLen = 0;
        int numInsertDT = 0;
        int numtreeReBalDT = 0;
        int recCount = 0;

        String s = "";
        String cvsSplitBy = ",";
        String line = " ";
        // PreorderBalancedBST sbbst = new PreorderBalancedBST();
        // PreorderBalancedBST sbbst2 = new PreorderBalancedBST();
        File file = new File(fileNm);
        try {
            long startTime = System.nanoTime(); 
            Scanner fileScan = new Scanner(file);
            do 
            {
                line = fileScan.nextLine();	// get the first line of the file
                // process the current line by adding it to the array
                String[] piecesF = line.split(cvsSplitBy);
                try {
                    int dmacodeF = Integer.parseInt(piecesF[0]);
                    boolean duplDMACode = false;
                    boolean dupRegionVal = false;
                    regionLen = (piecesF[1].length());
                    if (regionLenMax < regionLen)
                       regionLenMax = regionLen;
                    recCount = recCount + 1;
                    sbbst8 = sbbst.insert(dmacodeF, piecesF[1], piecesF[2], duplDMACode, numtreeReBalDT, numInsertDT);
                    // if ((sbbst8.dupDMACode) && (indxDMA < 100))
                    // {
                    //    tempInt = Integer.toString(dmacodeF);
                    //    multDMA[indxDMA] = tempInt;
                    //    tempIndx = indxDMA + 100;
                    //    multDMA[tempIndx] = piecesF[1];
                    //    tempIndx = indxDMA + 200;
                    //    multDMA[tempIndx] = piecesF[2];
                    //    indxDMA ++;
                    //}
                    numInsertDT = 0;
                    numtreeReBalDT = 0;
                    sbbst9 = sbbst2.insertR(piecesF[1], dmacodeF, piecesF[2], dupRegionVal, numtreeReBalDT, numInsertDT);
    
                    // if ((sbbst9.dupRegion) && (indxRegion < 100))
                    // {
                    //    multRegion[indxRegion] = piecesF[1];
                    //    tempIndx = indxRegion + 100;
                    //    tempInt = Integer.toString(dmacodeF);
                    //    multRegion[tempIndx] = tempInt;
                    //    tempIndx = indxRegion + 200;
                    //    multRegion[tempIndx] = piecesF[2];
                    //    indxRegion ++;
                    //}
                    
                } catch (Exception e)
                {     
                      // dma code,region,state
                      System.out.println("First line of input is bad data or Headings");
                      System.out.println("Will read second record to see if valid data");
                }
       
                // get the next line until the entire file has been read
            } while (fileScan.hasNextLine());
            long stopTime = System.nanoTime();
            estimatedTime = System.nanoTime() - startTime;
            System.out.printf("\nCPU Time is combination of DMA Code and Region Key Order \n");
            System.out.printf("CPU Time is %,d \n", estimatedTime);
            // System.out.printf("CPU Time for startTime is %,d stopTime %,d estimated time %,d \n", startTime, stopTime, estimatedTime);
            /* Print duplicate keys if there were any */
            // if (indxDMA > 0)
            // {  
            //    System.out.println("\n There are duplicate DMA Code keys \n");
            //    int j = 0;
            //    int i = 0;
            //    while ((!(multDMA[i].equals(""))) && (j < 100))
            //    {
            //        System.out.print(multDMA[i] + " ");
            //        i = j + 100;
            //        System.out.print(multDMA[i] + " ");
            //        i = i + 100;
            //        System.out.println(multDMA[i]);
            //        j = j + 1;
            //    }
            //}
            // if (indxRegion > 0)
            // {  
            //    System.out.println("\n There are duplicate Region keys \n");
            //    int j = 0;
            //    int i = 0;
            //    while ((!(multRegion[i].equals(""))) && (j < 100))
            //    {
            //        System.out.print(multRegion[i] + " ");
            //        i = j + 100;
            //        System.out.print(multRegion[i] + " ");
            //        i = i + 100;
            //        System.out.println(multRegion[i]);
            //        j = j + 1;
            //    }
            // }
            
            System.out.printf("\nNumber of Records read %,d \n", recCount);

            fileScan.close();

            // return regionLenMax;
        // } catch (FileNotFoundException e) {
        } catch (Exception e) {
            System.out.println("Exception reading file" + fileNm);
            e.printStackTrace();
        }

        return regionLenMax;
    }
}

