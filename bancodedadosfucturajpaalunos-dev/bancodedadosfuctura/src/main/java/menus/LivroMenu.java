package menus;
import java.util.Scanner;

import services.LivroService;
public class LivroMenu {

public void exibirMenu() {
	Scanner scan = new Scanner(System.in);
	LivroService livroService = new LivroService();
    int opcao = -1;

    while (opcao != 0) {
        System.out.println("\n=== MENU LIVRARIA ===");
        System.out.println("1 - Cadastrar Livro");
        System.out.println("2 - Listar Todos os Livros");
        System.out.println("3 - Procurar Livro por ID");
        System.out.println("4 - Atualizar Livro");
        System.out.println("5 - Deletar Livro");
        System.out.println("0 - Sair");
        System.out.print("Escolha uma opção: ");

 
        if (!scan.hasNextInt()) {
            System.out.println("Opção inválida! Digite um número.");
            scan.next(); 
            continue;
        }

        opcao = scan.nextInt();
        scan.nextLine();

        switch (opcao) {
            case 1:
                livroService.createLivro();
                break;
            case 2:
                livroService.listAll();
                break;
            case 3:
                livroService.procurarLivro();
                break;
            case 4:
                livroService.updateLivro();
                break;
            case 5:
                livroService.deletarLivro();
                break;
            case 0:
                System.out.println("Encerrando o sistema...");
                break;
            default:
                System.out.println("Opção inválida, tente novamente.");
        }
    }
}

}
