import java.util.ArrayList;
import java.util.List;

public class Tokenizer {
    private char[] input;
    private int index;

    public Tokenizer(String str) {
        this.input = str.toCharArray();
        this.index = 0;
    }

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