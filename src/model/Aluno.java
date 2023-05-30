package model;

public class Aluno extends Pessoa {

	private String matricula;

	public String getMatricula() {
		return matricula;
	}

	public void setMatricula(String matricula) {
		this.matricula = matricula;
	}

	@Override
	public String toString() {
		return "Aluno [Id = " + getId() + ", Nome = " + getNome() + ", Idade = " + getIdade() + ", Matrícula = "
				+ getMatricula() + ", Telefone = " + getTelefone() + "]";
	}

}
