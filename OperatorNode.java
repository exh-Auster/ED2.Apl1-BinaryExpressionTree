/**
 * Representação de um nó da árvore binária que contém um operador (char).
 * 
 * @author André Matteucci - 32273541
 * @author Felipe Ribeiro  - 32212720
 * @author Enzo Koji       - 32273754
 * 
 * @see BinaryTree
 * @see Node
 * @see OperandNode
 */

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
    
    /**
     * Método utilizado para visitar um nó da árvore.
     * No caso particular do nó operador, retorna o resultado da operação.
     * 
     * @return resultado da operação.
     */
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
