/**
 * //TODO: description
 * @author André Matteucci - 32273541
 * @author Felipe Ribeiro  - 32212720
 * @author Enzo Koji       - //TODO: TIA
*/

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int option;

        String expression = "";

        while (true) {
            System.out.println("\n\n" + new String(new char[23]).replace("\0", "#") + " MENU " + new String(new char[23]).replace("\0", "#"));
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
        }
    }
}
