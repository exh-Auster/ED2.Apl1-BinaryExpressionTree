/**
 * Representação de um nó da árvore binária que contém um operando (float).
 * 
 * @author André Matteucci - 32273541
 * @author Felipe Ribeiro  - 32212720
 * @author Enzo Koji       - 32273754
 * 
 * @see BinaryTree
 * @see Node
 * @see OperatorNode
 */

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

    /**
     * Método utilizado para visitar um nó da árvore.
     * No caso particular do nó operando, retorna o valor do operando.
     * 
     * @return valor do operando.
     */
    @Override
    public float visitar() {
        return this.data;
    }  
}
