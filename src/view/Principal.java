package view;

import java.util.List;
import java.util.Scanner;

import DAOs.AlunoDAO;
import DAOs.ClasseDAO;
import DAOs.ControleFrequenciaDAO;

import model.Aluno;
import model.Classe;
import model.ControleFrequencia;

public class Principal {
	static AlunoDAO alunoDAO = new AlunoDAO();
	static ClasseDAO classeDAO = new ClasseDAO();
	static ControleFrequenciaDAO frequenciaDAO = new ControleFrequenciaDAO();
	static Scanner scanner = new Scanner(System.in);

	public static void main(String[] args) {

		int opcao = -1;

		while (opcao != 0) {
			System.out.println(" ============================================ ");
			System.out.println("| *  MENU DE CADASTRO DE ALUNOS *            |");
			System.out.println(" ============================================ ");
			System.out.println("|    Escolha uma opção:                      |");
			System.out.println("|                                            |");
			System.out.println("|    1 - Cadastrar Aluno(a)                  |");
			System.out.println("|    2 - Matricular Aluno(a)                 |");
			System.out.println("|    3 - Buscar Aluno(a) por Id              |");
			System.out.println("|    4 - Listar Todos Alunos                 |");
			System.out.println("|    5 - Atualizar Aluno(a)                  |");
			System.out.println("|    6 - Deletar Aluno(a) por Id             |");
			System.out.println(" ============================================ ");
			System.out.println("|    7 - Cadastrar Classe                    |");
			System.out.println("|    8 - Buscar Classe por Id                |");
			System.out.println("|    9 - Listar Todas Classes                |");
			System.out.println("|   10 - Atualizar Classe                    |");
			System.out.println("|   11 - Deletar Classe por Id               |");
			System.out.println(" ============================================ ");
			System.out.println("|   12 - Cadastrar Frequência                |");
			System.out.println("|   13 - Buscar Frequência por Id do Aluno   |");
			System.out.println("|   14 - Deletar Frequência por Id           |");
			System.out.println(" ============================================ ");
			System.out.println("|    0 - Sair                                |");
			System.out.println(" ============================================ ");
			opcao = scanner.nextInt();

			switch (opcao) {
			case 1:
				menuCadastrarAluno();
				break;
			case 2:
				menuMatricularAluno();
				break;
			case 3:
				menuBuscarAlunoPorId();
				break;
			case 4:
				menuListarAlunos();
				break;
			case 5:
				menuAtualizarAluno();
				break;
			case 6:
				menuDeletarAluno();
				break;

			case 7:
				menuCadastrarClasse();
				break;
			case 8:
				menuBuscarClassePorId();
				break;
			case 9:
				menuListarClasses();
				break;
			case 10:
				menuAtualizarClasse();
				break;
			case 11:
				menuDeletarClasse();
				break;

			case 12:
				menuCadastrarFrequencia();
				break;
			case 13:
				menuBuscarFrequenciaPorIdAluno();
				break;
			case 14:
				menuDeletarFrequenciaPorId();
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

	private static void menuMatricularAluno() {
		Aluno aluno = new Aluno();
		Classe classe = new Classe();
		aluno.setClasse(classe);

		Aluno alunoBuscado = alunoDAO.buscarAlunoPorId(0);
		Classe classeBuscada = classeDAO.buscarClassePorId(0);

		while (alunoBuscado == null) {
			System.out.println("Informe o Id do Aluno que você quer Matricular: ");
			aluno.setId(scanner.nextInt());

			alunoBuscado = alunoDAO.buscarAlunoPorId(aluno.getId());
		}

		while (classeBuscada == null) {
			System.out.println("Informe o Id da Classe para Matricular o Aluno: ");
			classe.setId(scanner.nextInt());

			classeBuscada = classeDAO.buscarClassePorId(classe.getId());
		}

		alunoDAO.matricularAluno(aluno);
	}

	public static void menuBuscarAlunoPorId() {
		Aluno aluno = new Aluno();

		System.out.println("\nDigite o Id do Aluno(a) para buscar: ");
		aluno.setId(scanner.nextInt());

		Aluno alunoBuscado = alunoDAO.buscarAlunoPorId(aluno.getId());

		if (alunoBuscado != null) {
			System.out.println(alunoBuscado);
		}
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

	private static void menuCadastrarClasse() {
		ClasseDAO classeDAO = new ClasseDAO();
		Classe classe = new Classe();

		System.out.println("Digite o Nome da Classe: ");
		classe.setNome(scanner.next().concat(scanner.nextLine()));

		classeDAO.cadastrarClasse(classe);
	}

	private static void menuBuscarClassePorId() {
		Classe classe = new Classe();

		System.out.println("\nDigite o Id da Classe para buscar: ");
		classe.setId(scanner.nextInt());

		Classe classeBuscada = classeDAO.buscarClassePorId(classe.getId());

		if (classeBuscada != null) {
			System.out.println(classeBuscada);
		}
	}

	private static void menuListarClasses() {
		List<Classe> listaClasses = classeDAO.listarClasses();

		System.out.println();
		for (int i = 0; i < listaClasses.size(); i++) {
			System.out.println(i + 1 + "º -> " + listaClasses.get(i));
		}
	}

	private static void menuAtualizarClasse() {
		Classe classe = new Classe();

		System.out.println("Digite o Id da Classe para Atualizar:");
		classe.setId(scanner.nextInt());

		Classe classeBuscada = classeDAO.buscarClassePorId(classe.getId());

		if (classeBuscada != null) {
			System.out.println("\nEsse é a Classe que você quer alterar:\n\n" + classeBuscada);

			System.out.println("\n\nDigite o nome da Classe para Atualizar:");
			classe.setNome(scanner.next().concat(scanner.nextLine()));

			Classe classeAtualizada = classeDAO.atualizarClasse(classe);

			System.out.println("\n" + classeAtualizada);
		}
	}

	private static void menuDeletarClasse() {
		Classe classe = new Classe();

		System.out.println("\nDigite o Id da Classe para deletar: ");
		classe.setId(scanner.nextInt());

		Classe classeBuscada = classeDAO.buscarClassePorId(classe.getId());

		if (classeBuscada != null) {
			classeDAO.deletarClasse(classe.getId());
		}
	}

	private static void menuCadastrarFrequencia() {
		Aluno aluno = new Aluno();
		Classe classe = new Classe();
		aluno.setClasse(classe);
		ControleFrequencia frequencia = new ControleFrequencia();
		frequencia.setAluno(aluno);
		frequencia.setClasse(classe);

		System.out.println("Informe o Id do Aluno para Registrar a Frequência: ");
		aluno.setId(scanner.nextInt());

		Aluno alunoBuscado = alunoDAO.buscarAlunoPorId(aluno.getId());
		classe.setId(alunoBuscado.getClasse().getId());

		if (alunoBuscado.getClasse() != null) {
			System.out.println("Informe se o Aluno está presente: ");
			System.out.println("Digite: ");
			System.out.println("Sim / Não");
			frequencia.setStatus(scanner.next().concat(scanner.nextLine()));

			frequenciaDAO.cadastrarFrequencia(frequencia);
		} else {
			System.out.println("\nO Aluno não está matriculado em uma classe.");
		}
	}

	private static void menuBuscarFrequenciaPorIdAluno() {
		System.out.println("Informe o Id do Aluno para buscar todas Frequências: ");
		Integer idAluno = scanner.nextInt();

		List<ControleFrequencia> listaFrequencias = frequenciaDAO.listarFrequenciasDoAluno(idAluno);

		System.out.println();

		for (int i = 0; i < listaFrequencias.size(); i++) {
			System.out.println(i + 1 + "º -> " + listaFrequencias.get(i));
		}
	}

	private static void menuDeletarFrequenciaPorId() {
		ControleFrequencia frequencia = new ControleFrequencia();

		System.out.println("\nDigite o Id da Frequência para deletar: ");
		frequencia.setId(scanner.nextInt());

		ControleFrequencia frequenciaBuscada = frequenciaDAO.buscarFrequenciaPorId(frequencia.getId());

		if (frequenciaBuscada != null) {
			frequenciaDAO.deletarFrequenciaPorId(frequencia.getId());
		}
	}
}