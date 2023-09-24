import java.util.ArrayList;
import java.util.List;


/**
 * Classe responsável por tokenizar uma expressão matemática.
 * 
 * @author André Matteucci - 32273541
 * @author Felipe Ribeiro  - 32212720
 * @author Enzo Koji       - 32273754
 */

public class Tokenizer {
    private char[] input;
    private int index;

    public Tokenizer(String str) {
        this.input = str.toCharArray();
        this.index = 0;
    }

    /**
     * Método utilizado para tokenizar uma expressão matemática.
     * 
     * @return lista de tokens
     */
    public List<String> tokenize() {
        List<String> tokens = new ArrayList<String>();

        while (this.index < this.input.length) {
            char currentChar = this.input[this.index];

            if (Character.isDigit(currentChar)) {
                tokens.add(this.readNumber());
            } else if (currentChar == '+' || currentChar == '-' ||
                       currentChar == '*' || currentChar == '/') {
                tokens.add(this.readOperator());
            } else if (currentChar == '(' || currentChar == ')') {
                tokens.add(this.readParenthesis());
            } else {
                this.index++;
            }
        }

        return tokens;
    }

    /**
     * Método utilizado para ler um número da expressão.
     * 
     * @return número lido em formato de string
     */
    private String readNumber() {
        StringBuilder number = new StringBuilder();
        boolean decimalEncountered = false;

        while (this.index < this.input.length &&
               (Character.isDigit(this.input[this.index]) ||
               this.input[this.index] == '.')) {
            if (this.input[this.index] == '.') {
                if (decimalEncountered) {
                    break;
                } else {
                    decimalEncountered = true;
                }
            }
            number.append(this.input[this.index]);

            this.index++;
        }

        return number.toString();
    }

    /**
     * Método utilizado para ler um operador da expressão.
     * 
     * @return operador lido em formato de string
     */
    private String readOperator() {
        StringBuilder operator = new StringBuilder();

        while (this.index < this.input.length && (this.input[this.index] == '+'||
               this.input[this.index] == '-' || this.input[this.index] == '*'  ||
               this.input[this.index] == '/')) {
            operator.append(this.input[this.index]);

            this.index++;
        }

        return operator.toString();
    }

    /**
     * Método utilizado para ler um parêntese da expressão.
     * 
     * @return parêntese lido em formato de string
     */
    private String readParenthesis() {
        StringBuilder parenthesis = new StringBuilder();

        while (this.index < this.input.length && (this.input[this.index] == '(' ||
               this.input[this.index] == ')')) {
            parenthesis.append(this.input[this.index]);
            this.index++;
        }

        return parenthesis.toString();
    }
}