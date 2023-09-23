/**
 * //TODO: description
 * @author André Matteucci - 32273541
 * @author Felipe Ribeiro  - 32212720
 * @author Enzo Koji       - 32273754
*/

import java.util.LinkedList;
import java.util.Queue;

public class BinaryTree {
    private OperatorNode root;

    public BinaryTree() {
        this.root = null;
    }

    public BinaryTree(OperatorNode root) {
        this.root = root;
    }

    public boolean isEmpty() {
        return this.root == null;
    }

    public Node getRoot() {
        return this.root;
    }

    public void setRoot(OperatorNode root) {
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

	public String levelOrderTraversal() {
		return levelOrderTraversal(root);
	}
	
	private String levelOrderTraversal(Node node) {
		if (node == null) {
			return "";
		}
		
		StringBuilder traversal = new StringBuilder();

		Queue<Node> queue = new LinkedList<>();

		queue.add(node);

		while (!queue.isEmpty()) {
			Node visited = queue.remove();
			traversal.append(visited.getData() + " ");

			if (visited.hasLeft()) {
				queue.add(visited.getLeft());
			}

			if (visited.hasRight()) {
				queue.add(visited.getRight());
			}
		}
		
		return traversal.toString();
	}
}