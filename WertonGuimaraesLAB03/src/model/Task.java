package model;


import metodos.data.Data;
import metodos.data.Hour;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;



public class Task implements Comparable<Task> {

	private String nome, descricao;
	private Data dataCriacao, dataConclusao;
	private Date date = new Date();
	private Hour horaCriacao, horaConclusao;
	private DateFormat dateFormat = new SimpleDateFormat("HH:mm");
	private boolean status;

	public Task(String nome) throws IllegalArgumentException {
		this.nome = nome;		
		this.dataConclusao = new Data();
		this.horaConclusao = new Hour();
		this.descricao = "";	
		
		this.status = false;
		
		String horaAtual = this.dateFormat.format(this.date);
		this.horaCriacao = new Hour(Integer.parseInt(horaAtual.substring(0, 2)),
								    Integer.parseInt(horaAtual.substring(3, 5)));
		
		this.dataCriacao = getDataSystem(new SimpleDateFormat("dd/MM/yyyy").format(Calendar.getInstance()
							.getTime()));
	}
	
	//   GETS
	public String getNome() { return nome;}
	public Data getDataCriacao() { return dataCriacao;}
	public Hour getHoraCriacao() { return horaCriacao;}
	public Data getDataConclusao() { return dataConclusao;}
	public Hour getHoraConclusao() { return horaConclusao;}
	public String getDescricao() { return descricao;}
	public boolean getStatus() { return this.status;}
	
	
	//   SETS
	public void setNome(String nome) { this.nome = nome;}
	public void setDataCriacao(Data dataCriacao) { this.dataCriacao = dataCriacao;}
	public void setHoraCriacao(Hour horaCriacao) { this.horaCriacao = horaCriacao;}	
	public void setHoraConclusao(Hour horaConclusao) { this.horaConclusao = horaConclusao;}
	public void setStatus(boolean status) { this.status = status;}
	
	public void setDescricao(String descricao) {
		if (descricao == null) throw new NullPointerException();
		this.descricao = descricao;
	}
	
	public void setDataConclusao(Data dataConclusao) throws IllegalArgumentException{
		if (this.getDataCriacao().compareTo(dataConclusao) > 0) {
			throw new IllegalArgumentException("Data invalida");
		}
		else if (this.getDataCriacao().compareTo(dataConclusao) == 0) {
			if (this.getHoraConclusao().compareTo(this.getHoraCriacao()) > 0) {
				throw new IllegalArgumentException("Data invalida");
			}
		}		
		this.dataConclusao = dataConclusao;
	}

	//Auxiliares
	private Data getDataSystem(String data) throws IllegalArgumentException {
		return new Data(Integer.parseInt(data.substring(0, 2)),
						Integer.parseInt(data.substring(3, 5)),
						Integer.parseInt(data.substring(6, 10)));
	}
	
	@Override
	public String toString() {
		return "Nome: " + this.getNome() + "\nDataCriacao: " + this.getDataCriacao() + "\nDataConclusao: "
				+ this.getDataConclusao()+"\n";
	}
	
	
	//Comaparacao
	@Override
	public int compareTo(Task tarefa) {
		if (this.getDataConclusao().compareTo(tarefa.getDataConclusao()) != 0) {
			return getDataConclusao().compareTo(tarefa.getDataConclusao());
		}
		return this.getNome().compareTo(tarefa.getNome());
	}
	
	@Override
	public boolean equals(Object task) {
		if (!(task instanceof Task))
			return false;
		return this.getNome().equals(((Task) task).getNome());
	}

	
}
