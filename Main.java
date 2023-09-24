/**
 * Classe principal, responsável por executar o menu e controlar o fluxo do programa.
 * 
 * @author André Matteucci - 32273541
 * @author Felipe Ribeiro  - 32212720
 * @author Enzo Koji       - 32273754
 * 
 * @see <a href="https://docs.oracle.com/javase/7/docs/technotes/tools/windows/javadoc.html#see">javadoc - The Java API Documentation Generator</a>
 * @see <a href="https://www.cs.swarthmore.edu/~newhall/unixhelp/javacodestyle.html">Java Style Guidelines</a>
 * @see <a href="https://docs.oracle.com/javase/tutorial/java/IandI/hidevariables.html">Hiding Fields (The Java™ Tutorials)</a>
 * @see <a href="https://en.wikipedia.org/wiki/Binary_expression_tree">Binary expression tree - Wikipedia</a>
 * @see <a href="https://www.geeksforgeeks.org/tree-traversals-inorder-preorder-and-postorder/">Tree Traversal Techniques – Data Structure and Algorithm Tutorials</a>
*/

import java.util.List;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Stack;

public class Main {
    /**
     * Método utilizado para checar se uma string é um número.
     * 
     * @param str String a ser checada
     * @return    true se a string for um número, false caso contrário
     */
    public static boolean isNumeric(String str) {
        try {
            Double.parseDouble(str);
            return true;
        } catch(NumberFormatException e) {
            return false;
        }
    }

    /**
     * Método utilizado para validar se uma expressão matemática está em formato válido.
     * 
     * @param infixExpression expressão matemática a ser validada
     * @return                true se a expressão estiver em formato válido,
     *                        false caso contrário
     */
    private static boolean expressionValidation(String infixExpression) {
        Stack<Character> parentheses = new Stack<Character>();
        boolean balancedParentheses = true;
        boolean isValidExpression;

        for (int i = 0; i < infixExpression.toCharArray().length; i++) { /* Checa se os parênteses estão em formato válido. */
            char currentChar = infixExpression.toCharArray()[i];

            if (currentChar == '(') {
                parentheses.push(currentChar);
            } else if (currentChar == ')') {
                if (parentheses.isEmpty()) { /* Caso de fechamento de parenteses não aberto */
                    balancedParentheses = false;
                    break;
                }
                
                char popChar = (char)parentheses.pop();
                if (popChar != '(') { /* Caso geral de mismatch dos parênteses */
                    balancedParentheses = false;
                    break;
                }
            }
        }
        
        /* Caso pilha não vazia (número ímpar de parenteses) após o final do loop */
        balancedParentheses = parentheses.isEmpty()? balancedParentheses:false;

        /* Checa, por meio de regex, se a expressão está em formato válido. */
        isValidExpression = infixExpression.replaceAll("[()]", "")
                                           .matches("[0-9]+(\\.\\d+)?(\\s*[()+*/-]\\s*[0-9]+(\\.\\d+)?)+"); // TODO: change to Windows format

        /* Retorna true apenas se ambos os componentes forem válidos. */
        return balancedParentheses && isValidExpression;
    }

    /**
     * Método utilizado para converter uma expressão matemática
     * da notação infixa para a notação posfixa.
     * 
     * @param infixExpression expressão matemática na notação infixa
     * @return                expressão matemática na notação posfixa
     */
    public static List<String> expressionConversion(String infixExpression) {
        Tokenizer tk = new Tokenizer(infixExpression);
        /* Lista utilizada para armazenar os tokens da expressão infixa */
        List<String> tokens = tk.tokenize();

        /* Pilha auxiliar utilizada diramte a conversão */
        Stack<String> conversion = new Stack<String>();

        /* Lista utilizada para armazenar a expressão posfixa final */
        List<String> output = new ArrayList<String>();

        for (String token : tokens) {
            if (isNumeric(token)) {
                output.add(token);
            } else if (token.equals("(")) {
                conversion.push(token);
            } else if (token.equals(")")) {
                while (!conversion.isEmpty() &&
                       !conversion.peek().equals("(")) {
                    output.add(conversion.pop());
                }
                
                conversion.pop();
            } else if (token.equals("*") ||
                       token.equals("+") ||
                       token.equals("-") ||
                       token.equals("/")) { /* Caso geral para operadores */
                switch (token) {

                    /* Análise individual para cada operador
                    (antes de empilhar, desempilha e copia para a saída
                    enquanto houver no topo operador de maior ou igual prioridade) */
                    case "*":
                        while (!conversion.isEmpty() &&
                              (conversion.peek().equals("^") ||
                               conversion.peek().equals("/") ||
                               conversion.peek().equals("*"))) {
                            output.add(conversion.pop());
                        }
                        
                        conversion.push(token);
                        break;
                    case "+":
                        while (!conversion.isEmpty() &&
                              (conversion.peek().equals("^") ||
                               conversion.peek().equals("/") ||
                               conversion.peek().equals("*") ||
                               conversion.peek().equals("-") ||
                               conversion.peek().equals("+"))) {
                            output.add(conversion.pop());
                        }
                        
                        conversion.push(token);
                        break;
                    case "-":
                        while (!conversion.isEmpty() &&
                              (conversion.peek().equals("^") ||
                               conversion.peek().equals("/") ||
                               conversion.peek().equals("*") ||
                               conversion.peek().equals("+") ||
                               conversion.peek().equals("-"))) {
                            output.add(conversion.pop());
                        }

                        conversion.push(token);
                        break;
                    case "/":
                        while (!conversion.isEmpty() &&
                              (conversion.peek().equals("^") ||
                               conversion.peek().equals("*") ||
                               conversion.peek().equals("/"))) {
                            output.add(conversion.pop());
                        }
                        
                        conversion.push(token);
                        break;
                }
            }
        }

        while (!conversion.isEmpty()) {
            output.add(conversion.pop());
        }

        return output;
    }

    /**
     * Método utilizado para criar uma árvore binária de expressão.
     * 
     * @param postfixExpression expressão matemática na notação posfixa
     * @return                  árvore binária de expressão
     */
    public static BinaryTree createExpressionTree(List<String> postfixExpression) {
        Stack<Node> expressionTree = new Stack<Node>();

        for (String token : postfixExpression) {
            if (isNumeric(token)) { // Caso 0-9
                expressionTree.push(new OperandNode(Float.parseFloat(String.valueOf(token))));
            } else if (token.equals("*") ||
                       token.equals("+") ||
                       token.equals("-") ||
                       token.equals("/"))  { /* Caso geral para operadores */
                Node right = expressionTree.pop();
                Node left = expressionTree.pop();

                expressionTree.push(new OperatorNode(token.toCharArray()[0], left, right));
            }
        }

        return new BinaryTree((OperatorNode) expressionTree.pop());
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int option, maxCompleted = 0;
        BinaryTree expressionTree = null;

        String expression = "";
        String notCompleted = "   ";
        String completed = " x ";

        while (true) {
            System.out.println("\n" + new String(new char[23])
                                .replace("\0", "#")
                                + " MENU " + new String(new char[23])
                                .replace("\0", "#"));
            
            System.out.println((maxCompleted >= 1? completed:notCompleted) +
                                "1. Entrada da expressão aritmética na notação infixa");
            System.out.println((maxCompleted >= 2? completed:notCompleted) +
                                "2. Criação da árvore binária de expressão aritmética");
            System.out.println((maxCompleted >= 3? completed:notCompleted) +
                                "3. Exibição da árvore binária de expressão aritmética");
            System.out.println((maxCompleted >= 4? completed:notCompleted) +
                                "4. Cálculo da expressão (realizando o percurso da árvore)");
            System.out.println((maxCompleted >= 5? completed:notCompleted) +
                                "5. Encerramento do programa");
            System.out.println(new String(new char[52]).replace("\0", "#") + "\n");

            System.out.print("Digite a opção desejada: ");

            option = scanner.nextInt();

            if (option == 5) {
                scanner.close();
                System.exit(0);
            }

            switch (option) {
                case 1:
                    System.out.print("\nDigite a expressão no formato infixo: ");
                    scanner.nextLine();
                    expression = scanner.nextLine()
                                        .replaceAll("[ ]", "");

                    while (!expressionValidation(expression)) {
                        // TODO: indicar qual é o problema na expressão
                        System.out.print("Expressão inválida! São pertmitidos números, parenteses e os quatro operadores básicos. Digite novamente: ");
                        scanner.nextLine();
                        expression = scanner.nextLine()
                                            .replaceAll("[ ]", "");
                    }

                    System.out.println("\nExpressão infixa válida! " +
                                       "Expressão posfixa: " +
                                       expressionConversion(expression));
                    
                    maxCompleted = 1;

                    break;
                case 2:
                    expressionTree = createExpressionTree(expressionConversion(expression));
                    System.out.println("\nÁrvore binária de expressão criada!");
                    
                    maxCompleted = 2;
                    
                    break;
                case 3: // TODO
                    System.out.println("\nÁrvore em pré-ordem: " + expressionTree.preorderTraversal());
                    System.out.println("\nÁrvore em ordem    : " + expressionTree.inorderTraversal());
                    System.out.println("\nÁrvore em pós-ordem: " + expressionTree.postorderTraversal());

                    maxCompleted = 3;

                    break;
                case 4:
                    System.out.println("\nO resultado da expressão é " + expressionTree.getRoot().visitar());
                    
                    maxCompleted = 4;
                    
                    break;
                default:
                    System.out.println("Opção inválida!");
                    break;
            }
        }
    }
}
