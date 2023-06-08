package DAOs;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Aluno;
import model.Classe;
import model.MySQLConnection;

public class AlunoDAO {

	private Connection conn = null;

	public void cadastrarAluno(Aluno aluno) {
		conn = MySQLConnection.conectar();

		final String SQL = "INSERT INTO alunos (nome, idade, telefone, matricula) VALUES (?, ?, ?, ?)";

		try {
			PreparedStatement statement = conn.prepareStatement(SQL);

			statement.setString(1, aluno.getNome());
			statement.setInt(2, aluno.getIdade());
			statement.setString(3, aluno.getTelefone());
			statement.setString(4, aluno.getMatricula());

			int rowsInserted = statement.executeUpdate();
			if (rowsInserted > 0) {
				System.out.println("Aluno(a) cadastrado com sucesso!");
			} else {
				System.out.println("Falha ao cadastrar Aluno(a).");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			MySQLConnection.desconectar(conn);
		}
	}

	public Aluno matricularAluno(Aluno aluno) {
		conn = MySQLConnection.conectar();

		AlunoDAO alunoDAO = new AlunoDAO();

		final String SQL = "UPDATE alunos SET id_classe = ? WHERE id_aluno = ?";

		Aluno alunoBuscado = alunoDAO.buscarAlunoPorId(aluno.getId());

		if (alunoBuscado.getClasse() == null) {
			try {
				PreparedStatement statement = conn.prepareStatement(SQL);

				statement.setInt(1, aluno.getClasse().getId());
				statement.setInt(2, aluno.getId());

				statement.executeUpdate();

				System.out.println("\nAluno(a) Matriculado com sucesso.");
			} catch (Exception e) {
				System.out.println("\nFalha ao Matricular Aluno(a).");
				e.printStackTrace();
			} finally {
				MySQLConnection.desconectar(conn);
			}
		} else {
			System.out.println("\n Erro! O Aluno já está Matriculado em uma Classe.");
		}

		return aluno;
	}

	public Aluno buscarAlunoPorId(Integer idAluno) {
		conn = MySQLConnection.conectar();
		Aluno aluno = new Aluno();
		ClasseDAO classeDAO = new ClasseDAO();
		ResultSet res = null;
		boolean existeRegistro = false;

		final String SQL = "SELECT * FROM alunos WHERE id_aluno = ?";

		try {
			PreparedStatement statement = conn.prepareStatement(SQL);

			statement.setInt(1, idAluno);

			res = statement.executeQuery();

			if (res.next()) {
				existeRegistro = true;
				aluno.setId(Integer.valueOf(res.getString("id_aluno")));
				aluno.setNome(res.getString("nome"));
				aluno.setIdade(Integer.valueOf(res.getString("idade")));
				aluno.setTelefone(res.getString("telefone"));
				aluno.setMatricula(res.getString("matricula"));

				Integer idClasseAluno = Integer.valueOf(res.getInt("id_classe"));

				if (idClasseAluno != null) {
					aluno.setClasse(classeDAO.buscarClassePorId(idClasseAluno));
				} else {
					aluno.setClasse(null);
				}

			} else {
				if (idAluno != 0) {
					System.out.println("\n** Aluno(a) não encontrado para o Id informado. **");
				}
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			MySQLConnection.desconectar(conn);
		}

		return existeRegistro ? aluno : null;
	}

	public List<Aluno> listarAlunos() {
		conn = MySQLConnection.conectar();
		ClasseDAO classeDAO = new ClasseDAO();
		List<Aluno> alunosRetornados = new ArrayList<>();
		ResultSet res;

		final String SQL = "SELECT * FROM alunos";

		try {
			PreparedStatement statement = conn.prepareStatement(SQL);

			res = statement.executeQuery();

			while (res.next()) {
				Aluno aluno = new Aluno();
				aluno.setId(Integer.valueOf(res.getString("id_aluno")));
				aluno.setNome(res.getString("nome"));
				aluno.setIdade(Integer.valueOf(res.getString("idade")));
				aluno.setTelefone(res.getString("telefone"));
				aluno.setMatricula(res.getString("matricula"));

				Integer idClasseAluno = res.getInt("id_classe");
				Classe classeALuno = classeDAO.buscarClassePorId(idClasseAluno);

				aluno.setClasse(classeALuno);

				alunosRetornados.add(aluno);
			}
		} catch (Exception e) {
			System.out.println("Falha ao listar Alunos.");
			e.printStackTrace();
		} finally {
			MySQLConnection.desconectar(conn);
		}

		return alunosRetornados;
	}

	public Aluno atualizarAluno(Aluno aluno) {
		conn = MySQLConnection.conectar();

		final String SQL = "UPDATE alunos SET nome = ?, idade = ?, telefone = ?, matricula = ? WHERE id_aluno = ?";

		try {
			PreparedStatement statement = conn.prepareStatement(SQL);

			statement.setString(1, aluno.getNome());
			statement.setInt(2, aluno.getIdade());
			statement.setString(3, aluno.getTelefone());
			statement.setString(4, aluno.getMatricula());
			statement.setInt(5, aluno.getId());

			statement.executeUpdate();

			System.out.println("\nAluno(a) Atualizado com sucesso.");
		} catch (Exception e) {
			System.out.println("\nFalha ao atualizar Aluno(a).");
			e.printStackTrace();
		} finally {
			MySQLConnection.desconectar(conn);
		}

		return aluno;
	}

	public void deletarAluno(Integer idAluno) {
		conn = MySQLConnection.conectar();

		final String SQL = "DELETE FROM alunos WHERE id_aluno = ?";

		try {
			PreparedStatement statement = conn.prepareStatement(SQL);

			statement.setInt(1, idAluno);
			statement.executeUpdate();

			System.out.println("\nAluno(a) deletado com sucesso!");

		} catch (Exception e) {
			System.out.println("Falha ao deletar Aluno(a).");
			e.printStackTrace();
		} finally {
			MySQLConnection.desconectar(conn);
		}
	}
}