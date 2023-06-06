package DAOs;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;

import model.ControleFrequencia;
import model.MySQLConnection;

public class ControleDeFrequenciaDAO {
	
	private Connection conn = null;
	
	
	public void cadastrarFrequencia(ControleFrequencia frequencia) {
		conn = MySQLConnection.conectar();

		final String SQL = "INSERT INTO frequencia (aluno, classe, status, data) VALUES (?, ?, ?, ?)";

		try {
			PreparedStatement statement = conn.prepareStatement(SQL);

			statement.setInt(1, frequencia.getAluno().getId());
			statement.setInt(2, frequencia.getClasse().getId());
			statement.setString(3, frequencia.getStatus());
			statement.setTimestamp(4, Timestamp.valueOf(LocalDateTime.now()));

			int rowsInserted = statement.executeUpdate();
			if (rowsInserted > 0) {
				System.out.println("Frequencia do aluno Realizada!");
			} else {
				System.out.println("Falha ao tentar realizar frequencia do aluno(a).");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			MySQLConnection.desconectar(conn);
		}
	}

}
