package model;

import java.time.LocalDate;

public class ControleFrequencia {

	private Integer id;

	private Aluno aluno;

	private Classe classe;

	private String status;

	private LocalDate data;

	public void controle(Aluno aluno, Classe salaDeaula) {

	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Aluno getAluno() {
		return aluno;
	}

	public void setAluno(Aluno aluno) {
		this.aluno = aluno;
	}

	public Classe getClasse() {
		return classe;
	}

	public void setClasse(Classe classe) {
		this.classe = classe;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public LocalDate getData() {
		return data;
	}

	public void setData(LocalDate data) {
		this.data = data;
	}

	@Override
	public String toString() {
		return "ControleFrequencia [id = " + id + ", aluno = " + aluno.getId() + ", classe = " + classe.getId()
				+ ", status = " + status + ", data = " + data + "]";
	}
}