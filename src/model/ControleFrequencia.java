package model;

import java.time.LocalDateTime;
import java.util.List;

public class ControleFrequencia {
   
	private Integer id;
	
	private Aluno aluno;
	
	private Classe classe;
	
	private String status;
	
	private LocalDateTime dataHora;
	
	public void controle(Aluno aluno, Classe salaDeaula) {
        
        
    }
    
    //public List<Classe> obterFrequencia(Aluno aluno) {
     
    //}
    

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

	public LocalDateTime getDataHora() {
		return dataHora;
	}

	public void setDataHora(LocalDateTime dataHora) {
		this.dataHora = dataHora;
	}
    
    
}

