package mrbet;

import java.util.Scanner;


public class MrBetSistema {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		MrBet mb = new MrBet();
		while(true) {
			String escolha = menu(scanner);
			if(escolha == null || escolha == "") {
				throw new IllegalArgumentException("escolha nula ou vazia");
			}
			comando(mb, scanner, escolha);
			
		}
	}
	
	private static String menu(Scanner sc) {
		System.out.println("(M)Minha inclusão de times\n"
						+ "(R)Recuperar time\n"
						+ "(.)Adicionar campeonato\n"
						+ "(B)Bora incluir time em campeonato e Verificar se time está em campeonato\n"
						+ "(E)Exibir campeonatos que o time participa\n"
						+ "(T)Tentar a sorte e status\n"
						+ "(H) Histórico\n"
						+ "(!)Já pode fechar o programa!\n"
						+ "Opção>\n"
						+ "");
		return sc.next().toUpperCase();
	}
	
	private static void comando(MrBet mb, Scanner sc, String escolha) {
		switch(escolha) {
		case "M":
			incluirTimes(mb, sc);
			break;
		case "R":
			recuperarTimes(mb, sc);
			break;
		case ".":
			adicionarCampeonato(mb, sc);
			break;
		case "B":
			incluirTimeEmCampeonato(mb, sc);
			break;
		case "E":
			exibirCampeonatos(mb, sc);
			break;
		case "T":
			tentarSorte(mb, sc);
			break;
		case "H":
			exibirHistorico(mb, sc);
			break;
		case "!":
			saiPrograma();
			break;
		default:
			System.out.println("OPÇÃO INVÁLIDA!");
			break;
		}
	}
	
	private static void incluirTimes(MrBet mb, Scanner sc) {
		sc.nextLine();
		System.out.println("\nCódigo: ");
		String codigo = sc.nextLine();
		System.out.println("\nNome: ");
		String nome = sc.nextLine();
		System.out.println("\nMascote: ");
		String mascote = sc.nextLine();
		System.out.println(mb.cadastraTime(codigo, nome, mascote));
	}
	
	private static void recuperarTimes(MrBet mb, Scanner sc) {
		System.out.println("\nCódigo: ");
		String codigo = sc.next();
		System.out.println(mb.mostraTime(codigo));
	}
	
	private static void adicionarCampeonato(MrBet mb, Scanner sc) {
		sc.nextLine();
		System.out.println("\nCampeonato: ");
		String campeonato = sc.nextLine();
		System.out.println("\nParticipantes: ");
		int participantes = sc.nextInt();
		mb.cadastraCampeonato(campeonato, participantes);
	}
	
	private static void incluirTimeEmCampeonato(MrBet mb, Scanner sc) {
		System.out.println("\n(I) Incluir time em campeonato ou (V) Verificar se time está em campeonato? ");
		String op = sc.next().toUpperCase();
		switch(op) {
		case "I":
			sc.nextLine();
			System.out.println("\nCódigo: ");
			String codigo = sc.nextLine();
			System.out.println("\nCampeonato: ");
			String campeonato = sc.nextLine();
			System.out.println(mb.incluirTime(codigo, campeonato));
			break;
		case "V":
			sc.nextLine();
			System.out.println("\nCódigo: ");
			codigo = sc.nextLine();
			System.out.println("\nCampeonato: ");
			campeonato = sc.nextLine();
			System.out.println(mb.verificaTimeCampeonato(codigo, campeonato));
			break;
		default:
			System.out.println("OPÇÃO INVÁLIDA!");
			break;
		}
	}
	
	private static void exibirCampeonatos(MrBet mb, Scanner sc) {
		System.out.println("\nTime: ");
		String codigo = sc.next();
		System.out.println(mb.exibeCampeonatos(codigo));
	}
	
	private static void tentarSorte(MrBet mb, Scanner sc) {
		System.out.println("\n(A)Apostar ou (S)Status das Apostas? ");
		String op = sc.next().toUpperCase();
		switch(op) {
		case "A":
			sc.nextLine();
			System.out.println("\nCódigo: ");
			String codigo = sc.nextLine();
			System.out.println("\nCampeonato: ");
			String campeonato = sc.nextLine();
			System.out.println("\nColocação: ");
			int colocacao = sc.nextInt();
			System.out.println("\nValor da Aposta: ");
			double valor = sc.nextDouble();
			System.out.println(mb.insereAposta(codigo, campeonato, colocacao, valor));
			break;
		case "S":
			System.out.println(mb.listaApostas());		
			break;
		}
	}
	
	private static void exibirHistorico(MrBet mb, Scanner sc) {
		System.out.println(mb.historico());
	}
	
	private static void saiPrograma() {
		System.out.println("\nPor hoje é só, pessoal!");
		System.exit(0);
	}
	
}
