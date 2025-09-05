package services;

import java.sql.Date;
import java.util.List;
import java.util.Scanner;

import dao.LivroDAO;
import entitys.Livro;

public class LivroService {
	
	private LivroDAO livrodao = new LivroDAO();
	private static Scanner scan = new Scanner(System.in);
	
	
	public void createLivro() {
		Livro liv = new Livro();
		System.out.println("Digite o titulo de seu livro");
		String tit = scan.nextLine();
		liv.setTitulo(tit);
		System.out.println("Digite o autor de seu livro");
		String aut = scan.nextLine();
		liv.setAutor(aut);
		System.out.println("Digite o Lançamento de seu livro (yyyy-mm-dd)");
		liv.setLançamento(Date.valueOf(scan.nextLine()));
		livrodao.saveLivro(liv);
		System.out.println("Livro Salvo com sucesso!");
		scan.nextLine();
	}
	
	public void listAll() {
		List<Livro> liv = livrodao.findAll();
		if (liv.isEmpty()) {
			System.out.println("A lista está vazia");
		}
		else {
			for (int x= 0; x<liv.size();x++) {
				System.out.printf("%d° livro \n", x+1);
				System.out.println("Id: " + liv.get(x).getId());
				System.out.println("Titulo: " + liv.get(x).getTitulo());
				System.out.println("Autor: " + liv.get(x).getAutor());
				System.out.println("Lançamento: " + liv.get(x).getLançamento());
				System.out.println("-----------------------");
			}
		}
	}
	public void listAutor() {
		System.out.println("Qual autor você procura?");
		String aut = scan.nextLine().trim();
		
		List<Livro> autorLiv = livrodao.findByAutor(aut);
		if(autorLiv.isEmpty()) {
			System.out.println("Autor não encontrado");
		} else {
			for(int x = 0; x < autorLiv.size();x++) {
				System.out.printf("%dº Livro\n", x+1);
				System.out.println("Id: "+ autorLiv.get(x).getId());
				System.out.println("Título: " + autorLiv.get(x).getTitulo());
				System.out.println("Autor: "+ autorLiv.get(x).getAutor());
				System.out.println("Lançamento: " + autorLiv.get(x).getLançamento());
				System.out.println("-----------------------");

			}
		}
	}

	public Livro procurarLivro() {
		System.out.println("Qual o Id do livro que você quer?");
		Integer idLivro = scan.nextInt();
		
		
		Livro liv = livrodao.findById(idLivro);
		if (liv != null) {
			System.out.println("-----------------------");
			System.out.println("Id: " + liv.getId());
			System.out.println("Titulo: " + liv.getTitulo());
			System.out.println("Autor: " + liv.getAutor());
			System.out.println("Lançamento: " + liv.getLançamento());
			System.out.println("-----------------------");
		} else {
			System.out.println("Livro inexistente");
		}
		return liv;
		
	}
	public void deletarLivro() {
        System.out.print("Digite o ID do livro que deseja remover: ");
        int id = scan.nextInt();
        scan.nextLine();
 		boolean deletado = livrodao.deleteLivro(id);
 		if(deletado) {
 			System.out.println("Livro deletado com sucesso");
 			System.out.println("Livros Restantes:");
 			List<Livro> lista = livrodao.findAll();
				for (Livro l : lista) {
					System.out.println("Id: "+ l.getId());
					System.out.println("Título: "+ l.getTitulo() );
					System.out.println("Autor: "+ l.getAutor());
					System.out.println("Lançamento: "+ l.getLançamento());
					System.out.println("-----------------------");
				}	
 		} else {
 			System.out.println("Livro não encontrado para remoção");
 		}

	}

	public void updateLivro() {
		System.out.print("Digite o ID do livro que deseja Atualizar: ");
		int id = scan.nextInt();
		Livro liv = livrodao.findById(id);
		if (liv == null) {
	            System.out.println("Livro não encontrado.");
	            return;
	        }
		scan.nextLine();
		System.out.println("Digite as informações a serem trocadas, deixe em branco se deseja não alteralas");
		System.out.println("Digite o novo titulo atual( " + liv.getTitulo() + "): ");
		String tit = scan.nextLine();
		if (tit != null) {
			liv.setTitulo(tit);
		}
		System.out.println("Digite o novo autor atual( " + liv.getAutor() + "): ");
		String aut = scan.nextLine();
		if (aut != null) {
			liv.setAutor(aut);
		}
		boolean Datavalida = false;
		while(!Datavalida) {
			
			System.out.println("Digite o novo lançamento (yyyy-mm-dd) atual(" + liv.getLançamento() +": ");
			String data = scan.nextLine();
			if (data == null) {
				break;
			}
			try {
				liv.setLançamento(Date.valueOf(data));
				Datavalida = true;
			} catch (IllegalArgumentException e) {
				System.out.println("Data inválida digite novalmente no formato (yyyy-mm-dd)");
			}
			
			
		}
		livrodao.updateLivro(liv);
		System.out.println("Livro atualizado com sucesso");
	}
}








