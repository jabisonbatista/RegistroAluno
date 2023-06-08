package DAOs;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Classe;
import model.MySQLConnection;

public class ClasseDAO {

	private Connection conn = null;

	public void cadastrarClasse(Classe classe) {
		conn = MySQLConnection.conectar();

		final String SQL = "INSERT INTO classes (nome) VALUES (?)";

		try {
			PreparedStatement statement = conn.prepareStatement(SQL);

			statement.setString(1, classe.getNome());

			int rowsInserted = statement.executeUpdate();
			if (rowsInserted > 0) {
				System.out.println("Classe cadastrada com sucesso!");
			} else {
				System.out.println("Falha ao cadastrar Classe.");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			MySQLConnection.desconectar(conn);
		}
	}

	public Classe buscarClassePorId(Integer idClasse) {
		conn = MySQLConnection.conectar();
		Classe classe = new Classe();
		ResultSet res = null;
		boolean existeRegistro = false;

		final String SQL = "SELECT * FROM classes WHERE id_classe = ?";

		try {
			PreparedStatement statement = conn.prepareStatement(SQL);

			statement.setInt(1, idClasse);

			res = statement.executeQuery();

			if (res.next()) {
				existeRegistro = true;
				classe.setId(Integer.valueOf(res.getString("id_classe")));
				classe.setNome(res.getString("nome"));
			} else {
				if (idClasse != 0) {
					System.out.println("\n** Classe não encontrada para o Id informado. **");
				}
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			MySQLConnection.desconectar(conn);
		}

		return existeRegistro ? classe : null;
	}

	public List<Classe> listarClasses() {
		conn = MySQLConnection.conectar();
		List<Classe> classesRetornadas = new ArrayList<>();
		ResultSet res;

		final String SQL = "SELECT * FROM classes";

		try {
			PreparedStatement statement = conn.prepareStatement(SQL);

			res = statement.executeQuery();

			while (res.next()) {
				Classe classe = new Classe();
				classe.setId(Integer.valueOf(res.getString("id_classe")));
				classe.setNome(res.getString("nome"));

				classesRetornadas.add(classe);
			}
		} catch (Exception e) {
			System.out.println("Falha ao listar Classes.");
			e.printStackTrace();
		} finally {
			MySQLConnection.desconectar(conn);
		}

		return classesRetornadas;
	}

	public Classe atualizarClasse(Classe classe) {
		conn = MySQLConnection.conectar();

		final String SQL = "UPDATE classes SET nome = ? WHERE id_classe = ?";

		try {
			PreparedStatement statement = conn.prepareStatement(SQL);

			statement.setString(1, classe.getNome());
			statement.setInt(2, classe.getId());

			statement.executeUpdate();

			System.out.println("\nClasse Atualizada com sucesso.");
		} catch (Exception e) {
			System.out.println("\nFalha ao atualizar Classe.");
			e.printStackTrace();
		} finally {
			MySQLConnection.desconectar(conn);
		}

		return classe;
	}

	public void deletarClasse(Integer idClasse) {
		conn = MySQLConnection.conectar();

		final String SQL = "DELETE FROM classes WHERE id_classe = ?";

		try {
			PreparedStatement statement = conn.prepareStatement(SQL);

			statement.setInt(1, idClasse);
			statement.executeUpdate();

			System.out.println("\nClasse deletada com sucesso!");

		} catch (Exception e) {
			System.out.println("Falha ao deletar Classe.");
			e.printStackTrace();
		} finally {
			MySQLConnection.desconectar(conn);
		}
	}
}