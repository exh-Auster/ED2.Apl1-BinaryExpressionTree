public class OperandNode extends Node {
    protected float data;

    // TODO: rename method
    // TODO: "Na classe do nó que armazena um operando, esse método deve retornar o valor do operando"
    
    @Override
    public float visitar() {
        return this.data;
    }  
}
