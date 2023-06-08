package model;

public class Aluno extends Pessoa {

	private String matricula;

	private Classe classe;

	public String getMatricula() {
		return matricula;
	}

	public void setMatricula(String matricula) {
		this.matricula = matricula;
	}


	public Classe getClasse() {
		return classe;
	}

	public void setClasse(Classe classe) {
		this.classe = classe;
	}

	@Override
	public String toString() {
		return "Aluno [ Id = " + getId() + ", Nome = " + getNome() + ", Idade = " + getIdade() + ", Matrícula = "
				+ getMatricula() + ", Telefone = " + getTelefone() + ", Classe = " + getClasse() + " ]";
	}

}