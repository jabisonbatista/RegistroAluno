package model;

import java.io.Serializable;

public class Classe implements Serializable{

	private static final long serialVersionUID = 1L;

	private Integer id;

	private String nome;

	public Classe() {

	}

	public Classe(Integer id, String nome) {

		this.id = id;
		this.nome = nome;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	@Override
	public String toString() {
		return "Classe [id = " + id + ", nome = " + nome + "]";
	}
}