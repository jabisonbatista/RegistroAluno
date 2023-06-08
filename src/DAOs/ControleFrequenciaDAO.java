package DAOs;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import model.ControleFrequencia;
import model.MySQLConnection;

public class ControleFrequenciaDAO {

	private Connection conn = null;

	public void cadastrarFrequencia(ControleFrequencia frequencia) {
		conn = MySQLConnection.conectar();

		final String SQL = "INSERT INTO frequencias (id_aluno, id_classe, status, data) VALUES (?, ?, ?, ?)";

		try {
			PreparedStatement statement = conn.prepareStatement(SQL);

			statement.setInt(1, frequencia.getAluno().getId());
			statement.setInt(2, frequencia.getAluno().getClasse().getId());
			statement.setString(3, frequencia.getStatus());
			statement.setTimestamp(4, Timestamp.valueOf(LocalDateTime.now()));

			int rowsInserted = statement.executeUpdate();
			if (rowsInserted > 0) {
				System.out.println("\nFrequencia do aluno Realizada!");
			} else {
				System.out.println("\nFalha ao tentar realizar frequencia do aluno(a).");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			MySQLConnection.desconectar(conn);
		}
	}

	public List<ControleFrequencia> listarFrequenciasDoAluno(Integer idALuno) {
		conn = MySQLConnection.conectar();
		AlunoDAO alunoDAO = new AlunoDAO();
		ClasseDAO classeDAO = new ClasseDAO();
		List<ControleFrequencia> frequenciasRetornadas = new ArrayList<>();
		ResultSet res;

		final String SQL = "SELECT * FROM frequencias WHERE id_aluno = ?";

		try {
			PreparedStatement statement = conn.prepareStatement(SQL);

			statement.setInt(1, idALuno);

			res = statement.executeQuery();

			while (res.next()) {
				ControleFrequencia frequencia = new ControleFrequencia();

				frequencia.setId(Integer.valueOf(res.getInt("id_frequencia")));

				Integer idAluno = Integer.valueOf(res.getInt("id_aluno"));
				frequencia.setAluno( alunoDAO.buscarAlunoPorId(idAluno) );

				Integer idClasse = Integer.valueOf(res.getInt("id_classe"));
				frequencia.setClasse( classeDAO.buscarClassePorId(idClasse) );

				frequencia.setStatus(res.getString("status"));

				String dataString = res.getString("data");
				DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
				LocalDate data = LocalDate.parse(dataString, formatter);

				frequencia.setData(data);

				frequenciasRetornadas.add(frequencia);
			}
		} catch (Exception e) {
			System.out.println("Falha ao listar Frequências do Aluno(a)");
			e.printStackTrace();
		} finally {
			MySQLConnection.desconectar(conn);
		}

		return frequenciasRetornadas;
	}

	public ControleFrequencia buscarFrequenciaPorId(Integer idFrequencia) {
		conn = MySQLConnection.conectar();
		ControleFrequencia frequencia = new ControleFrequencia();
		AlunoDAO alunoDAO = new AlunoDAO();
		ClasseDAO classeDAO = new ClasseDAO();
		ResultSet res = null;
		boolean existeRegistro = false;

		final String SQL = "SELECT * FROM frequencias WHERE id_frequencia = ?";

		try {
			PreparedStatement statement = conn.prepareStatement(SQL);

			statement.setInt(1, idFrequencia);

			res = statement.executeQuery();

			if (res.next()) {
				existeRegistro = true;

				frequencia.setId(Integer.valueOf(res.getInt("id_frequencia")));

				Integer idAluno = Integer.valueOf(res.getInt("id_aluno"));
				frequencia.setAluno( alunoDAO.buscarAlunoPorId(idAluno) );

				Integer idClasse = Integer.valueOf(res.getInt("id_classe"));
				frequencia.setClasse( classeDAO.buscarClassePorId(idClasse) );

				frequencia.setStatus(res.getString("status"));

				String dataString = res.getString("data");
				DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
				LocalDate data = LocalDate.parse(dataString, formatter);

				frequencia.setData(data);
			} else {
				if (idFrequencia != 0) {
					System.out.println("\n** Frequência não encontrada para o Id informado. **");
				}
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			MySQLConnection.desconectar(conn);
		}

		return existeRegistro ? frequencia : null;
	}

	public void deletarFrequenciaPorId(Integer idFrequencia) {
		conn = MySQLConnection.conectar();

		final String SQL = "DELETE FROM frequencias WHERE id_frequencia = ?";

		try {
			PreparedStatement statement = conn.prepareStatement(SQL);

			statement.setInt(1, idFrequencia);
			statement.executeUpdate();

			System.out.println("\nFrequência deletada com sucesso!");

		} catch (Exception e) {
			System.out.println("Falha ao deletar Frequência.");
			e.printStackTrace();
		} finally {
			MySQLConnection.desconectar(conn);
		}
	}
}