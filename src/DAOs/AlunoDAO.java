package DAOs;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Aluno;
import model.MySQLConnection;

public class AlunoDAO {

	private Connection conn = null;

	public void cadastrarAluno(Aluno aluno) {
		conn = MySQLConnection.conectar();

		final String SQL = "INSERT INTO aluno (nome, idade, telefone, matricula) VALUES (?, ?, ?, ?)";

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

	public Aluno buscarAlunoPorId(Integer idAluno) {
		conn = MySQLConnection.conectar();
		Aluno aluno = new Aluno();
		ResultSet res = null;
		boolean existeRegistro = false;

		final String SQL = "SELECT * FROM aluno WHERE id_aluno = ?";

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
			} else {
				System.out.println("\n*** Aluno(a) não encontrado para o Id informado. ***");
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
		List<Aluno> alunosRetornados = new ArrayList<>();
		ResultSet res;

		final String SQL = "SELECT * FROM aluno";

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

		final String SQL = "UPDATE aluno SET nome = ?, idade = ?, telefone = ?, matricula = ? WHERE id_aluno = ?";

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

		final String SQL = "DELETE FROM aluno WHERE id_aluno = ?";

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
