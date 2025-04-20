import java.util.ArrayList;

public class MorseCodeTree implements LinkedConverterTreeInterface<String> {
	TreeNode<String> head;
	
	public MorseCodeTree() {
		head = new TreeNode<String>("");
	}
	
	
	/**
	 * Returns a reference to the root
	 * @return reference to root
	 */
	@Override
	public TreeNode<String> getRoot() {
		return head;
	}
	
	
	/**
	 * sets the root of the Tree
	 * @param newNode a TreeNode<T> that will be the new root
	 */
	@Override
	public void setRoot(TreeNode newNode) {
		head = newNode;
	}

	
	/**
	 * Adds result to the correct position in the tree based on the code
	 * This method will call the recursive method addNode
	 * 
	 * @param code the code for the new node to be added
	 * 
	 */
	@Override
	public void insert(String code, String letter) {
		
		if(head==null)
			head=new TreeNode<String>(letter);
		else 
			addNode(head,code,letter);
	}

	
	/**
	 * This is a recursive method that adds element to the correct position 
	 * in the tree based on the code.
	 * 
	 * @param root the root of the tree for this particular recursive instance of addNode
	 * @param code the code for this particular recursive instance of addNode
	 * @param letter the data of the new TreeNode to be added
	 */
	@Override
	public void addNode(TreeNode root, String code, String letter) {
		
	    if (code.length() == 1) 
	    {
	        TreeNode<String> newNode = new TreeNode<>(letter);
	        
	        
	        if (code.equals(".")) 
	        {
	            root.lChild = newNode;
	        }
	        else if (code.equals("-")) 
	        {
	            root.rChild = newNode;
	        }
	        return;
	    }
	    char direction = code.charAt(0);
	    String remainingCode = code.substring(1);

	    if (direction == '.') 
	    {
	        if (root.lChild == null) root.lChild = new TreeNode<>("");
	        
	        addNode(root.lChild, remainingCode, letter);
	    }
	    
	    else if (direction == '-') 
	    {
	        if (root.rChild == null) root.rChild = new TreeNode<>("");
	        addNode(root.rChild, remainingCode, letter);
	    }
	}

	/**
	 * Fetch the data in the tree based on the code
	 * This method will call the recursive method fetchNode
	 * 
	 * @param code the code that describes the traversals within the tree
	 * @return the result that corresponds to the code
	 */
	@Override
	public String fetch(String code) {
		return fetchNode(head,code);
	}
	
	/**
	 * This is the recursive method that fetches the data of the TreeNode
	 * that corresponds with the code
	 * 
	 * @param root the root of the tree for this particular recursive instance of addNode
	 * @param code the code for this particular recursive instance of fetchNode
	 * @return the data corresponding to the code
	 */
	@Override
	public String fetchNode(TreeNode<String> root, String code) {
	    if (code.length() == 0)
	        return root.getData();
	    
	    char direc = code.charAt(0);
	    String remaining = code.substring(1);

	    switch (direc) 
	    {
	        case '.':
	        	
	            return fetchNode(root.lChild, remaining);
	        case '-':
	            return fetchNode(root.rChild, remaining);
	        default:
	        	
	            throw new RuntimeException("Error");
	    }
	}

	/**
	 * This operation is not supported for a LinkedConverterTree
	 * @param data data of node to be deleted
	 * @return reference to the current tree
	 * @throws UnsupportedOperationException
	 */
	@Override
	public LinkedConverterTreeInterface delete(String data) throws UnsupportedOperationException {
		throw new UnsupportedOperationException();
	}

	/**
	 * This operation is not supported for a LinkedConverterTree
	 * @return reference to the current tree
	 * @throws UnsupportedOperationException
	 */
	@Override
	public LinkedConverterTreeInterface update() throws UnsupportedOperationException {
		throw new UnsupportedOperationException();
	}

	/**
	 * This method builds the LinkedConverterTree by inserting TreeNodes<T>
	 * into their proper locations
	 * 
	 */
	@Override
	public void buildTree() {
		//1
		insert(".", "e");
        insert("-", "t");
        //2
        insert("..", "i");
        insert(".-", "a");
        insert("-.", "n");
        insert("--", "m");
        //3
        insert("...", "s");
        insert("..-", "u");
        insert(".-.", "r");
        insert(".--", "w");
        insert("-..", "d");
        insert("-.-", "k");
        insert("--.", "g");
        insert("---", "o");
        //4
        insert("....", "h");
        insert("...-", "v");
        insert("..-.", "f");
        insert(".-..", "l");
        insert(".--.", "p");
        insert(".---", "j");
        insert("-...", "b");
        insert("-..-", "x");
        insert("-.-.", "c");
        insert("-.--", "y");
        insert("--..", "z");
        insert("--.-", "q");
	}

	
	/**
	 * Returns an ArrayList of the items in the linked converter Tree in LNR (Inorder) Traversal order
	 * Used for testing to make sure tree is built correctly
	 * @return an ArrayList of the items in the linked Tree
	 */
	@Override
	public ArrayList toArrayList() {
		ArrayList<String> arList = new ArrayList<>();
		LNRoutputTraversal(head, arList);
        return arList;
	}

	/**
	 * The recursive method to put the contents of the linked converter tree in an ArrayList<T> 
	 * LNR (Inorder)
	 * @param root the root of the tree for this particular recursive instance
	 * @param list the ArrayList that will hold the contents of the tree in LNR order
	 */
	@Override
	public void LNRoutputTraversal(TreeNode root, ArrayList list) {
		if (root == null) 	
            return; 
		LNRoutputTraversal(root.lChild, list);
        list.add(root.getData());
        LNRoutputTraversal(root.rChild, list);
	}
	
}
