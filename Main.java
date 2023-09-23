/**
 * //TODO: description
 * @author André Matteucci - 32273541
 * @author Felipe Ribeiro  - 32212720
 * @author Enzo Koji       - 32273754
 * @see <a href="https://docs.oracle.com/javase/7/docs/technotes/tools/windows/javadoc.html#see">javadoc - The Java API Documentation Generator</a>
 * @see <a href="https://www.cs.swarthmore.edu/~newhall/unixhelp/javacodestyle.html">Java Style Guidelines</a>
 * @see <a href="https://docs.oracle.com/javase/tutorial/java/IandI/hidevariables.html">Hiding Fields (The Java™ Tutorials)</a>
 * @see <a href="https://en.wikipedia.org/wiki/Binary_expression_tree">Binary expression tree - Wikipedia</a>
 * @see <a href="https://www.geeksforgeeks.org/tree-traversals-inorder-preorder-and-postorder/">Tree Traversal Techniques – Data Structure and Algorithm Tutorials</a>
*/

import java.util.Scanner;
import java.util.Stack;

public class Main {
    private static boolean expressionValidation(String infixExpression) { // TODO: allow Doubles
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
                                           .matches("[1-9](\s*[()+*/^-]\s*[1-9]){1,}");

        /* Retorna true apenas se ambos os componentes forem válidos. */
        return balancedParentheses && isValidExpression;
    }

    public static String expressionConversion(String infixExpression) {
        Stack<Character> conversion = new Stack<Character>();
        String output = "";
        char[] infixExpressionAsCharArray = infixExpression.toCharArray();
        
        for (int i = 0; i < infixExpressionAsCharArray.length; i++) {
            char currentChar = infixExpressionAsCharArray[i];

            /* Tratamento de cada tipo de char possível na expressão */
            if (currentChar >= 48 && currentChar <= 57) { // Caso 0-9
                output += currentChar;
            } else if (currentChar == 40) { // Caso '('
                conversion.push(currentChar);
            } else if (currentChar == 41) { // Caso ')'
                while ((char)conversion.peek() != 40) {
                    output+= conversion.pop();
                }
                
                conversion.pop();
            } else if (currentChar == 42 ||
                       currentChar == 43 ||
                       currentChar == 45 ||
                       currentChar == 47) { /* Caso geral para operadores */
                switch (currentChar) {

                    /* Análise individual para cada operador
                    (antes de empilhar, desempilha e copia para a saída
                    enquanto houver no topo operador de maior ou igual prioridade) */
                    case 42: // Caso '*'
                        while (!conversion.isEmpty() &&
                              ((char)conversion.peek() == 94 ||
                               (char)conversion.peek() == 47 ||
                               (char)conversion.peek() == 42)) {
                            output += conversion.pop();
                        }
                        
                        conversion.push(currentChar);
                        break;
                    case 43: // Caso '+'
                        while (!conversion.isEmpty() &&
                              ((char)conversion.peek() == 94 ||
                               (char)conversion.peek() == 47 ||
                               (char)conversion.peek() == 42 ||
                               (char)conversion.peek() == 45 ||
                               (char)conversion.peek() == 43)) {
                            output += conversion.pop();
                        }
                        
                        conversion.push(currentChar);
                        break;
                    case 45: // Caso '-'
                        while (!conversion.isEmpty() &&
                              ((char)conversion.peek() == 94 ||
                               (char)conversion.peek() == 47 ||
                               (char)conversion.peek() == 42 ||
                               (char)conversion.peek() == 43 ||
                               (char)conversion.peek() == 45)) {
                            output += conversion.pop();
                        }

                        conversion.push(currentChar);
                        break;
                    case 47: // Caso '/'
                        while (!conversion.isEmpty() &&
                              ((char)conversion.peek() == 94 ||
                               (char)conversion.peek() == 42 ||
                               (char)conversion.peek() == 47)) {
                            output += conversion.pop();
                        }
                        
                        conversion.push(currentChar);
                        break;
                }
            }
        }

        while (!conversion.isEmpty()) {
            output += conversion.pop();
        }

        return output;
    }

    public static BinaryTree createExpressionTree(String postfixExpression) {
        Stack<Node> expressionTree = new Stack<Node>();
        char[] postfixExpressionAsCharArray = postfixExpression.toCharArray();

        for (int i = 0; i < postfixExpressionAsCharArray.length; i++) {
            char currentChar = postfixExpressionAsCharArray[i];

            if (currentChar >= 48 && currentChar <= 57) { // Caso 0-9
                expressionTree.push(new Node(currentChar));
            } else if (currentChar == 42 ||
                       currentChar == 43 ||
                       currentChar == 45 ||
                       currentChar == 47) { /* Caso geral para operadores */
                Node right = expressionTree.pop();
                Node left = expressionTree.pop();

                expressionTree.push(new Node(currentChar, left, right));
            }
        }

        return new BinaryTree(expressionTree.pop());
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int option;
        BinaryTree expressionTree = null;

        String expression = "";

        while (true) {
            System.out.println("\n" + new String(new char[23])
                                .replace("\0", "#")
                                + " MENU " + new String(new char[23])
                                .replace("\0", "#"));
            
            System.out.println("1. Entrada da expressão aritmética na notação infixa");
            System.out.println("2. Criação da árvore binária de expressão aritmética");
            System.out.println("3. Exibição da árvore binária de expressão aritmética");
            System.out.println("4. Cálculo da expressão (realizando o percurso da árvore)");
            System.out.println("5. Encerramento do programa");
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
                    break;
                case 2:
                    expressionTree = createExpressionTree(expressionConversion(expression));
                    System.out.println("\nÁrvore binária de expressão criada!");
                    break;
                case 3: // TODO
                    System.out.println("\npreorder:  " + expressionTree.preorderTraversal());
                    System.out.println("inorder:   "   + expressionTree.inorderTraversal());
                    System.out.println("postorder: "   + expressionTree.postorderTraversal());
                    break;
                case 4:
                    // TODO
                    break;
                default:
                    System.out.println("Opção inválida!");
                    break;
            }
        }
    }
}
