public class OperatorNode extends Node {
    protected char data;

    public OperatorNode() {
        super();
        this.data = '\0';
    }

    public OperatorNode(char data) {
        super();
        this.data = data;
    }

    public OperatorNode(char data, Node left, Node right) {
        super(left, right);
        this.data = data;
    }

    public OperatorNode(char data, Node parent, Node left, Node right) {
        super(parent, left, right);
        this.data = data;
    }

    public OperatorNode(char data, Node parent) {
        super(parent);
        this.data = data;
    }

    public Character getData() {
        return this.data;
    }
    
    @Override
    public float visitar() {
        if (this.data == '+') {
            return this.left.visitar() + this.right.visitar();
        } else if (this.data == '-') {
            return this.left.visitar() - this.right.visitar();
        } else if (this.data == '*') {
            return this.left.visitar() * this.right.visitar();
        } else if (this.data == '/') {
            return this.left.visitar() / this.right.visitar();
        } else {
            return Float.NaN;
        }
    }
}
