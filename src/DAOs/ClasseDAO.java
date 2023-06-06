package DAOs;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import model.Classe;
import model.MySQLConnection;

public class ClasseDAO {
	private Connection conn = null;
	
	public void buscarClassePorId(Classe idClasse) {
		conn = MySQLConnection.conectar();
		
	

		final String SQL = "SELECT * FROM classe WHERE nome = ?";

		try {
			PreparedStatement statement = conn.prepareStatement(SQL);

			statement.setString(1, idClasse.getNome() );

			
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


