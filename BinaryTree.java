/**
 * //TODO: description
 * @author André Matteucci - 32273541
 * @author Felipe Ribeiro  - 32212720
 * @author Enzo Koji       - 32273754
*/

public class BinaryTree {
    private Node root;

    public BinaryTree() {
        this.root = null;
    }

    public BinaryTree(Node root) {
        this.root = root;
    }

    public boolean isEmpty() {
        return this.root == null;
    }

    public Node getRoot() {
        return this.root;
    }

    public void setRoot(Node root) {
        this.root = root;
    }

    public String inorderTraversal() {
		return inorderTraversal(root);
	}

	private String inorderTraversal(Node node) {
		if (node == null) {
			return "";
		}

		StringBuilder traversal = new StringBuilder();
		
		traversal.append(inorderTraversal(node.getLeft()));
		traversal.append(node.getData() + " ");
		traversal.append(inorderTraversal(node.getRight()));
		
		return traversal.toString();
	}

	public String preorderTraversal() {
		return preorderTraversal(root);
	}

	private String preorderTraversal(Node node) {
		if (node == null) {
			return "";
		}

		StringBuilder traversal = new StringBuilder();
		
		traversal.append(node.getData() + " ");
		traversal.append(preorderTraversal(node.getLeft()));
		traversal.append(preorderTraversal(node.getRight()));
		
		return traversal.toString();
	}

	public String postorderTraversal() {
		return postorderTraversal(root);
	}

	private String postorderTraversal(Node node) {
		if (node == null) {
			return "";
		}

		StringBuilder traversal = new StringBuilder();
		
		traversal.append(postorderTraversal(node.getLeft()));
		traversal.append(postorderTraversal(node.getRight()));
		traversal.append(node.getData() + " ");
		
		return traversal.toString();
	}
}