/**
 * //TODO: description
 * @author André Matteucci - //TODO: TIA
 * @author Felipe Ribeiro  - 32212720
 * @author Enzo Koji       - //TODO: TIA
*/

public class Node {
    char data;
    Node left;
    Node right;

    public Node(char data) {
        this.data = data;
        this.left = null;
        this.right = null;
    }

    public Node(char data, Node left, Node right) {
        this.data = data;
        this.left = left;
        this.right = right;
    }

    public void setData(char data) {
        this.data = data;
    }
    
    public void setLeft(Node left) {
        this.left = left;
    }

    public void setRight(Node right) {
        this.right = right;
    }

    public char getData() {
        return this.data;
    }

    public Node getLeft() {
        return this.left;
    }

    public Node getRight() {
        return this.right;
    }
}