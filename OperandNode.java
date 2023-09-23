public class OperandNode extends Node {
    protected float data;

    public OperandNode() {
        super();
        this.data = Float.NaN;
    }

    public OperandNode(float data) {
        super();
        this.data = data;
    }

    public OperandNode(Node left, Node right) {
        super(left, right);
        this.data = Float.NaN;
    }

    public OperandNode(float data, Node parent, Node left, Node right) {
        super(parent, left, right);
        this.data = data;
    }

    public OperandNode(float data, Node parent) {
        super(parent);
        this.data = data;
    }

    public Float getData() {
        return this.data;
    }

    // TODO: rename method
    // TODO: "Na classe do nó que armazena um operando, esse método deve retornar o valor do operando"
    @Override
    public float visitar() {
        return this.data;
    }  
}
