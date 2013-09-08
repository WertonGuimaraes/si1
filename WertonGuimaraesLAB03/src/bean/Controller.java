package bean;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import metodos.data.Data;
import metodos.data.Hour;
import model.Task;
public class Controller {

	private List<Task> tarefas;
	private List<Task> tarefasCompletas;
	private List<Task> tarefafsIncompletas;
	
	public Controller() {
		this.tarefas = new ArrayList<Task>();
		this.tarefasCompletas = new ArrayList<Task>();
		this.tarefafsIncompletas = new ArrayList<Task>();
		try {
			Task task1 = new Task("Reuniao");
			task1.setDescricao("reuniao do projeto");
			task1.setDataConclusao(new Data(10,11,2015));
			adicionaTarefa(task1);
			
			
			Task task2 = new Task("Meu Aniversaio");
			task2.setDataConclusao(new Data(12,02,2014));
			adicionaTarefa(task2);
			
			Task task3 = new Task("Aula");
			task3.setDescricao("aula de si1");
			task3.setDataConclusao(new Data(20,9,2013));
			task3.setHoraConclusao(new Hour(16, 0));
			adicionaTarefa(task3);
			
			Task task4 = new Task("Festa de Aniversaio");
			task4.setDataConclusao(new Data(12,2,2014));
			task4.setDescricao("A regra eh Clara! Soh pode sair BEBADO!");
			task4.setHoraConclusao(new Hour(22, 0));
			adicionaTarefa(task4);
			
			Task task5 = new Task("Sem Ideia  :\\ ");
			adicionaTarefa(task5);
		} catch (Exception e) {
		}
	}
	
	public void adicionaTarefa(Task Task) {
		this.tarefas.add(Task);
		this.addTarefaIncompleta(Task);
	}


	public List<Task> getTarefasCompletas() {
		return tarefasCompletas;
	}

	public void setTarefasCompletas(List<Task> TasksCompletas) {
		this.tarefasCompletas = TasksCompletas;
	}

	public List<Task> getTarefasIncompletas() {
		return tarefafsIncompletas;
	}

	public void setTarefasIncompletas(List<Task> TasksIncompletas) {
		this.tarefafsIncompletas = TasksIncompletas;
	}

	public List<Task> getTarefas() {
		return this.tarefas;
	}

	public void ordena(Comparator<Task> comparador) {
		Collections.sort(this.tarefas, comparador);
	}

	public void ordenaCompletas(Comparator<Task> comparador) {
		Collections.sort(this.tarefasCompletas, comparador);
	}

	public void ordenaIncompletas(Comparator<Task> comparador) {
		Collections.sort(this.tarefafsIncompletas, comparador);
	}

	public void addTarefaIncompleta(Task Task) {
		this.tarefafsIncompletas.add(Task);
	}

	public void addTarefaCompleta(Task Task) {
		this.tarefasCompletas.add(Task);
	}

	public void removeTarefaIncompleta(Task Task) {
		this.tarefafsIncompletas.remove(Task);
		this.tarefas.remove(Task);
	}
	
	
	public void mudaStatusDaTask(Task task1) {
		for (Task task : this.tarefas) {
			if (task1.getNome().equals(task.getNome())) {
				if (!task.getStatus()) {
					task.setStatus(true);
					addTarefaCompleta(task);
					removeTarefaIncompleta(task);
				}
				break;
			}
		}
	}

	public void removeTarefaCompleta(Task Task) {
		this.tarefasCompletas.remove(Task);
	}

	public void removeTarefa(Task Task) {
		if (Task.getStatus() == true) {
			this.removeTarefaCompleta(Task);
		} else {
			this.removeTarefaIncompleta(Task);
		}
		this.getTarefas().remove(Task);
	}

	public void editTarefa(Task tarefaAntiga, Task novaTarefa) throws IllegalArgumentException{
		for (int i = 0; i < this.getTarefas().size(); i++) {
			if (tarefaAntiga.equals(this.getTarefas().get(i))) {
				this.removeTarefa(tarefaAntiga);
				this.getTarefas().add(novaTarefa);
				if (novaTarefa.getStatus())		this.addTarefaCompleta(novaTarefa);
				else  							this.addTarefaIncompleta(novaTarefa);
				break;
			}
		}
	}
}