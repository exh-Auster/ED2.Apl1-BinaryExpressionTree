public class OperatorNode extends Node {
    protected char data;

    // TODO: rename method
    // TODO: "Na classe do nó que armazena um operador, esse método deve retornar o resultado da operação indicada pelo operador."
    
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
            return Float.NaN; // TODO
        }
    }
}
