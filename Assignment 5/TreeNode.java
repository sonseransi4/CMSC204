public class TreeNode<T> {
	
	
	private T data;
	
	public TreeNode<T> lChild;
	public TreeNode<T> rChild;
	
	public TreeNode(T dataNode) {
		data = dataNode;
	}
	
	public void addLeftChild(TreeNode<T> lChild) {
		
        this.lChild = lChild;
    }
	
	
	public void addRightChild(TreeNode<T> rChild) {
        this.rChild = rChild;
    }
	
	
	public TreeNode(TreeNode<T> node) {
		data = node.getData();
	}
	
	
	public T getData() {
		
		return data;
	}
	
	
	public void setData(T value) {
		data = value;
	}
	
	
}