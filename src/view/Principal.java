package view;

import java.util.List;
import java.util.Scanner;

import DAOs.AlunoDAO;
import DAOs.ControleDeFrequenciaDAO;
import model.Aluno;
import model.Classe;
import model.ControleFrequencia;

public class Principal {

	private static final Classe SalaDeAula = null;
	static AlunoDAO alunoDAO = new AlunoDAO();
	static Scanner scanner = new Scanner(System.in);

	public static void main(String[] args) {

		int opcao = -1;

		while (opcao != 0) {
			System.out.println(" =================================");
			System.out.println("| *  MENU DE CADASTRO DE ALUNOS * |");
			System.out.println("|=================================|");
			System.out.println("|    Escolha uma opção:           |");
			System.out.println("|                                 |");
			System.out.println("|    1 - Cadastrar Aluno(a)       |");
			System.out.println("|    2 - Buscar Aluno(a) por Id   |");
			System.out.println("|    3 - Listar Todos Alunos      |");
			System.out.println("|    4 - Atualizar Aluno(a)       |");
			System.out.println("|    5 - Deletar Aluno(a)         |");
			System.out.println("|    6 - Registrar Frequençia     |");
			System.out.println("|    7 - Listar Classes           |");
			System.out.println("|    0 - Sair                     |");
			System.out.println(" =================================");
			opcao = scanner.nextInt();

			switch (opcao) {
			case 1:
				menuCadastrarAluno();
				break;
			case 2:
				menuBuscarAlunoPorId();
				break;
			case 3:
				menuListarAlunos();
				break;
			case 4:
				menuAtualizarAluno();
				break;
			case 5:
				menuDeletarAluno();
				break;
			case 6:
				menuFrequenciaAluno();
				break;
			case 7:
				menuClasseDeAluno();
				break;
			}

			if (opcao != 0) {
				System.out.println("\n\n1 - Para continuar no programa");
				System.out.println("0 - Para encerrar o programa");

				opcao = scanner.nextInt();
			}
		}

		scanner.close();
	}

	public static void menuCadastrarAluno() {
		Aluno aluno = new Aluno();

		System.out.println("Digite o nome do Aluno(a) para Cadastrar:");
		aluno.setNome(scanner.next().concat(scanner.nextLine()));

		System.out.println("Digite a idade do Aluno(a) para Cadastrar:");
		aluno.setIdade(scanner.nextInt());

		System.out.println("Digite o telefone do Aluno(a) para Cadastrar:");
		aluno.setTelefone(scanner.next().concat(scanner.nextLine()));

		System.out.println("Digite a matricula do Aluno(a) para Cadastrar:");
		aluno.setMatricula(scanner.next().concat(scanner.nextLine()));

		alunoDAO.cadastrarAluno(aluno);
	}

	public static void menuBuscarAlunoPorId() {
		Integer idAluno;
		System.out.println("\nDigite o Id do Aluno(a) para buscar: ");
		idAluno = scanner.nextInt();
		Aluno aluno = alunoDAO.buscarAlunoPorId(idAluno);
		System.out.println(aluno);
	}

	public static void menuListarAlunos() {
		List<Aluno> listaAlunos = alunoDAO.listarAlunos();

		System.out.println();
		for (int i = 0; i < listaAlunos.size(); i++) {
			System.out.println(i + 1 + "º -> " + listaAlunos.get(i));
		}
	}

	public static void menuAtualizarAluno() {

		Aluno aluno = new Aluno();

		System.out.println("Digite o Id do Aluno(a) para Atualizar:");
		aluno.setId(scanner.nextInt());

		Aluno alunoBuscado = alunoDAO.buscarAlunoPorId(aluno.getId());
		if (alunoBuscado != null) {
			System.out.println("\nEsse é o Aluno(a) que você quer alterar:\n\n" + alunoBuscado);

			System.out.println("\n\nDigite o nome do Aluno(a) para Atualizar:");
			aluno.setNome(scanner.next().concat(scanner.nextLine()));

			System.out.println("Digite a idade do Aluno(a) para Atualizar:");
			aluno.setIdade(scanner.nextInt());

			System.out.println("Digite o telefone do Aluno(a) para Atualizar:");
			aluno.setTelefone(scanner.next().concat(scanner.nextLine()));

			System.out.println("Digite a matricula do Aluno(a) para Atualizar:");
			aluno.setMatricula(scanner.next().concat(scanner.nextLine()));

			Aluno AlunoAtualizado = alunoDAO.atualizarAluno(aluno);
			System.out.println("\n" + AlunoAtualizado);
		}
	}

	public static void menuDeletarAluno() {
		Aluno aluno = new Aluno();

		System.out.println("\nDigite o Id do Aluno(a) para deletar: ");
		aluno.setId(scanner.nextInt());

		Aluno alunoBuscado = alunoDAO.buscarAlunoPorId(aluno.getId());

		if (alunoBuscado != null) {
			alunoDAO.deletarAluno(aluno.getId());
		}
	}

	public static void menuFrequenciaAluno() {
		
		System.out.println("informe o id do aluno: ");
		Integer idAluno = scanner.nextInt();
		
		AlunoDAO alunoDAO = new AlunoDAO();
		
		Aluno aluno = alunoDAO.buscarAlunoPorId(idAluno);
		
		
		
		
		
		//Aluno aluno = new Aluno();
		aluno.setId(1);
		aluno.setNome("anderson");
		
		Classe classe = new Classe();
		classe.setNome("ADULTO");
		classe.setId(2);
		
		
		ControleFrequencia frequencia = new ControleFrequencia();
		frequencia.setAluno(aluno);
		frequencia.setClasse(classe);
		frequencia.setStatus("sim");
		
		
		ControleDeFrequenciaDAO frequenciaDAO = new ControleDeFrequenciaDAO();
		frequenciaDAO.cadastrarFrequencia(frequencia);
		

	}

	public static void menuClasseDeAluno() {
		
		System.out.println("Listar Classes: ");

	}

}
