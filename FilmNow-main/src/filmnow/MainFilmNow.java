package filmnow;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

/**
 * Interface com menus texto para manipular o sistema FilmNow.
 * 
 * @author 123210361 - Maria Clara Augusta Santos
 *
 */
public class MainFilmNow {

	public static void main(String[] args) {
		FilmNow fn = new FilmNow();

		System.out.println("Carregando filmes ...");
		try {
			/*
			 * Essa é a maneira de lidar com possíveis erros por falta do arquivo. 
			 */
			carregaFilmes("filmes_inicial.csv", fn);
		} catch (FileNotFoundException e) {
			System.err.println("Arquivo não encontrado: " + e.getMessage());
		} catch (IOException e) {
			System.err.println("Erro lendo arquivo: " + e.getMessage());
		}

		Scanner scanner = new Scanner(System.in);
		String escolha = "";
		while (true) {
			escolha = menu(scanner);
			comando(escolha, fn, scanner);
		}
	}

	/**
	 * Exibe o menu e captura a escolha do/a usuário/a.
	 * 
	 * @param scanner Para captura da opção do usuário/a.
	 * @return O comando escolhido.
	 */
	
	private static String menu(Scanner scanner) {
		System.out.println(
				"\n---\nMENU\n" + 
						"(A)Adicionar filme\n" + 
						"(M)Mostrar todos\n" + 
						"(MN)Mostrar filme por nome\n" +
						"(MA)Mostrar filme por ano\n" +
						"(D)Detalhar filme\n" + 
						"(L)Adicionar local\n" +
						"(O)Remover local\n" +
						"(F)Remover filme\n" +
						"(E)Exibir HotList\n" +
						"(H)Atribuir Hot\n" +
						"(R)Remover Hot\n" +		
						"(S)Sair\n" + 
						"\n" + 
						"Opção> ");
		return scanner.next().toUpperCase();
	}

	/**
	 * Interpreta a opção escolhida por quem está usando o sistema.
	 * 
	 * @param opcao   Opção digitada.
	 * @param fn  O sistema FilmNow que estamos manipulando.
	 * @param scanner Objeto scanner para o caso do comando precisar de mais input.
	 */
	private static void comando(String opcao, FilmNow fn, Scanner scanner) {
		switch (opcao) {
		case "A":
			adicionaFilme(fn, scanner);
			break;
		case "M":
			mostrarFilmes(fn);
			break;
		case "MN":
			mostrarFilmesNome(fn, scanner);
			break;
		case "MA":
			mostrarFilmesAno(fn, scanner);
			break;
		case "D":
			detalharFilme(fn, scanner);
			break;		
		case "E":
			exibirHotList(fn);
			break;
		case "H":
			atribuirHot(fn, scanner);
			break;	
		case "R":
			removerHot(fn, scanner);
		case "L":
			adicionarLocal(fn, scanner);
			break;
		case "O":
			removerLocal(fn, scanner);
			break;
		case "F":
			removerFilme(fn, scanner);
			break;
		case "S":
			sai();
			break;
		default:
			System.out.println("OPÇÃO INVÁLIDA!");
		}
	}

	/**
	 * Imprime lista de filmes.
	 * 
	 * @param fn O sistema FilmNow a ser manipulado.
	 */
	private static void mostrarFilmes(FilmNow fn) {
		System.out.println(fn.exibeFilmes());	
	}
	
	private static void mostrarFilmesNome(FilmNow fn, Scanner scanner) {
		System.out.println("\nNome> ");
		scanner.nextLine();
		String nome = scanner.nextLine();
		System.out.println(fn.exibeFilmesNome(nome));	
	}
	
	private static void mostrarFilmesAno(FilmNow fn, Scanner scanner) {
		System.out.println("\nAno> ");
		scanner.nextLine();
		String ano = scanner.next();
		System.out.println(fn.exibeFilmesAno(ano));	
	}
	/**
	 * Imprime os detalhes de um dos filmes. 
	 * 
	 * @param fn O sistema FilmNow a ser manipulado.
	 * @param scanner Scanner para capturar qual contato.
	 */
	private static void detalharFilme(FilmNow fn, Scanner scanner) {
		System.out.print("\nPosição> ");
		int posicao = scanner.nextInt();		
		System.out.println("\n" + fn.detalhaFilme(posicao));		
	}

	/**
	 * Formata um filme para impressão. 
	 * 
	 * @param posicao A posição do filme (que é exibida)/
	 * @param filme O filme a ser impresso.
	 * @return A String formatada.
	 */

	/**
	 * Cadastra um filme no sistema. 
	 * 
	 * @param fn O sistema FilmNow a ser manipulado.
	 * @param scanner Scanner para pedir informações do contato.
	 */
	private static void adicionaFilme(FilmNow fn, Scanner scanner) {
		System.out.print("\nPosição no sistema> ");
		int posicao = scanner.nextInt();
		scanner.nextLine();
		System.out.print("\nNome> ");
		String nome = scanner.nextLine();
		System.out.print("\nAno> ");
		String ano = scanner.next();
		scanner.nextLine(); 
		System.out.print("\nLocal> ");
		String local = scanner.next();
		System.out.println(fn.cadastraFilme(posicao, nome, ano, local));
	}
	
	/**
	 * Adiciona um local à um filme no sistema.
	 * 
	 * @param fn O sistema FilmNow a ser manipulado.
	 * @param scanner Scanner para pedir informações do contato.
	 */
	private static void adicionarLocal(FilmNow fn, Scanner scanner) {
		System.out.println("Filme>");
		int posicao = scanner.nextInt();
		scanner.nextLine();
		System.out.println("Local>");
		String local = scanner.nextLine();
		fn.adicionaLocal(posicao, local);
	}
	
	/**
	 * Remove um local de um filme no sistema.
	 * 
	 * @param fn O sistema FilmNow a ser manipulado.
	 * @param scanner Scanner para pedir informações do contato.
	 */
	private static void removerLocal(FilmNow fn, Scanner scanner) {
		System.out.println("Filme>");
		int posicao = scanner.nextInt();
		scanner.nextLine();
		System.out.println("Local>");
		String local = scanner.nextLine();
		fn.removeLocal(posicao, local);
	}
	
	/**
	 * Remove filme no sistema.
	 * 
	 * @param fn O sistema FilmNow a ser manipulado.
	 * @param scanner Scanner para pedir informações do contato.
	 */
	private static void removerFilme(FilmNow fn, Scanner scanner) {
		System.out.println("Filme>");
		int posicao = scanner.nextInt();
		fn.removeFilme(posicao);
	}
	
	/**
	 * Exibe os filmes mais populares (HotList).
	 * 
	 * @param fn O sistema FilmNow a ser manipulado.
	 */
	private static void exibirHotList(FilmNow fn) {
		for(int i = 0; i < fn.getHotList().length; i++) {
			if(fn.getHotList()[i] != null) {
				System.out.println(i+1 + fn.getHotList()[i].getHot());
			}
		}
	}
	
	/**
	 * Adiciona um filme à HotList.
	 * 
	 * @param fn O sistema FilmNow a ser manipulado.
	 * @param scanner Scanner para pedir informações do contato.
	 */
	private static void atribuirHot(FilmNow fn, Scanner scanner) {
		System.out.println("Filme>");
		int filme = scanner.nextInt();
		System.out.println("Posição>");
		int posicao = scanner.nextInt();
		System.out.println(fn.adicionaHot(filme, posicao));
	}
	
	/**
	 * Remove um filme da HotList.
	 * 
	 * @param fn O sistema FilmNow a ser manipulado.
	 * @param scanner Scanner para pedir informações do contato.
	 */
	private static void removerHot(FilmNow fn, Scanner scanner) {
		System.out.println("Posição>");
		int posicao = scanner.nextInt();
		if(posicao > 0 && posicao < fn.getHotList().length && fn.getHotList()[posicao] != null) {
			fn.removeHot(posicao);
		} else {
			System.out.println("POSIÇÃO INVÁLIDA");
		}
	}
	/**
	 * Sai da aplicação.
	 */
	private static void sai() {
		System.out.println("\nVlw flw o/");
		System.exit(0);
	}

	/**
	 * Lê carga de filmes de um arquivo csv. 
	 * 
	 * @param arquivoFilmes O caminho para o arquivo.
	 * @param fn O sistema FilmNow a ser populado com os dados.
	 * @throws IOException Caso o arquivo não exista ou não possa ser lido.
	 */
	private static void carregaFilmes(String arquivoFilmes, FilmNow fn)  throws FileNotFoundException, IOException {
		LeitorFilmNow leitor = new LeitorFilmNow();
		
		int carregados =  leitor.carregaContatos(arquivoFilmes, fn);
		System.out.println("Carregamos " + carregados + " registros.");
	}
	
}
