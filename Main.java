/**
 * //TODO: description
 * @author André Matteucci - 32273541
 * @author Felipe Ribeiro  - 32212720
 * @author Enzo Koji       - 32273754
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

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int option;

        String expression = "";

        while (true) {
            System.out.println("\n\n" + new String(new char[23])
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
                    System.out.print("Digite a expressão no formato infixo: ");
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

                    System.out.println("\nExpressão válida!");

                    // TODO
                    break;
                case 2:
                    // TODO
                    break;
                case 3:
                    // TODO
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
