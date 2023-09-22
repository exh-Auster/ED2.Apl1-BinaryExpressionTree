/**
 * //TODO: description
 * @author André Matteucci - 32273541
 * @author Felipe Ribeiro  - 32212720
 * @author Enzo Koji       - 32273754
*/

public class Node {
    protected Node parent;
    protected Node left;
    protected Node right;
    protected char data; // TODO: change to Object?

    public Node() {
        this.parent = null;
        this.left = null;
        this.right = null;
        this.data = '\0'; // TODO
    }

    public Node(Node parent) {
        this.parent = parent;
        this.left = null;
        this.right = null;
        this.data = '\0'; // TODO
    }

    public Node(Node parent, Node left, Node right) {
        this.parent = parent;
        this.left = left;
        this.right = right;
        this.data = '\0'; // TODO
    }

    public Node(char data) {
        this.parent = null;
        this.left = null;
        this.right = null;
        this.data = data;
    }

    public Node(Node parent, char data) {
        this.parent = parent;
        this.left = null;
        this.right = null;
        this.data = data;
    }

    public Node(Node parent, char data, Node left, Node right) {
        this.parent = parent;
        this.left = left;
        this.right = right;
        this.data = data;
    }

    public Node(char data, Node left, Node right) {
        this.data = data;
        this.left = left;
        this.right = right;
    }
    
    public void setParent(Node parent) {
        this.parent = parent;
    }

    public void setLeft(Node left) {
        this.left = left;
        left.setParent(this);
    }

    public void setRight(Node right) {
        this.right = right;
        right.setParent(this);
    }

    public void setData(char data) {
        this.data = data;
    }
    
    public Node getParent() {
        return this.parent;
    }
    
    public Node getLeft() {
        return this.left;
    }
    
    public Node getRight() {
        return this.right;
    }

    public char getData() {
        return this.data;
    }

    public boolean hasLeft() {
        return this.left != null;
    }

    public boolean hasRight() {
        return this.right != null;
    }

    public boolean isRoot() {
        return this.parent == null;
    }

    public boolean isLeaf() {
        return this.left == null && this.right == null;
    }

    // TODO: getDegree?

    public float visitar() { // TODO: rename method
        return Float.NaN;
    }
}