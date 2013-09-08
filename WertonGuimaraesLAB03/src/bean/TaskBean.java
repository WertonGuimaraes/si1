package bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import metodos.data.Data;
import metodos.data.DateConclusion;
import metodos.data.DateCriation;
import metodos.data.Hour;
import model.*;

@ManagedBean(name = "taskbean")
@SessionScoped
public class TaskBean implements Serializable {

	private static final long serialVersionUID = 1L;
	private String name, description, conclusionDate, conclusionTime,
			ordenationWay;
	private Controller controller;
	private Comparator<Task> comparator;
	private boolean status;
	private Task task;

	public TaskBean() throws IllegalArgumentException {
		this.controller = new Controller();
	}

	// GETS
	public boolean getStatus() {
		return status;
	}

	public Task getTarefa() {
		return task;
	}

	public String getOrdenacao() {
		return ordenationWay;
	}

	public String getHoraConclusao() {
		return conclusionTime;
	}

	public Comparator<Task> getComparador() {
		return comparator;
	}

	public Controller getController() {
		return controller;
	}

	public String getDataConclusao() {
		return conclusionDate;
	}

	public String getNome() {
		return name;
	}

	public String getDescricao() {
		return description;
	}

	public List<Task> getTarefas() {
		return controller.getTarefas();
	}

	// SETS
	public void setTarefa(Task Task) {
		this.task = Task;
	}

	public void setOrdenacao(String ordenacao) {
		this.ordenationWay = ordenacao;
	}

	public void setHoraConclusao(String horaConclusao) {
		this.conclusionTime = horaConclusao;
	}

	public void setComparador(Comparator<Task> comparador) {
		this.comparator = comparador;
	}

	public void setController(Controller controller) {
		this.controller = controller;
	}

	public void setDataConclusao(String dataConclusao) {
		this.conclusionDate = dataConclusao;
	}

	public void setNome(String nome) {
		this.name = nome;
	}

	public void setDescricao(String descricao) {
		this.description = descricao;
	}

	// PAGINAS
	public String voltar() {
		return "index.xhtml";
	}

	public String addTarefa() {
		clearSpaces();
		return "cadastro.xhtml";
	}

	public String editarTarefa() {
		if (this.getTarefa() == null) {
			msgUsuario("Clique em alguma tarefa para edita-la!");
			return "index.xhtml";
		}
		this.setNome(this.getTarefa().getNome());
		this.setDescricao(this.getTarefa().getDescricao());
		this.setDataConclusao(this.getTarefa().getDataConclusao().toString().replace("/", ""));
		this.setHoraConclusao(this.getTarefa().getHoraConclusao().toString().replace(":", ""));
		return "editor.xhtml";
	}

	// AUXILIARES
	private void clearSpaces() {
		this.setNome("");
		this.setDescricao("");
		this.setDataConclusao("");
		this.setHoraConclusao("");
	}

	public void mudaStatus() {
		if (this.task == null || this.getTarefa().getStatus() == true) {
			msgUsuario("Clique em alguma tarefa incompleta para Completa-la.");
			return;
		}
		this.task.setStatus(true);
		this.controller.addTarefaCompleta(this.getTarefa());
		this.controller.removeTarefaIncompleta(this.getTarefa());
	}

	private boolean testaNome() {
		if (this.getNome() == null || this.getNome().trim().equals("")) {
			msgUsuario("Eh necessario dar um nome a tarefa");
			return false;
		}
		return true;
	}

	private boolean testaData(Task tarefa) {
		if (!this.getDataConclusao().isEmpty()) {
			try {
				Data data = getConclusionDate(this.getDataConclusao());
				tarefa.setDataConclusao(data);
			} catch (Exception e) {
				msgUsuario("Data invalida");
				return false;
			}
		}
		return true;
	}

	private boolean TestaHora(Task tarefa) {
		if (!this.getHoraConclusao().isEmpty()) {
			try {
				Hour hora = getConclusionTime(this.getHoraConclusao());
				tarefa.setHoraConclusao(hora);
			} catch (Exception e) {
				msgUsuario("Hora invalida!");
				return false;
			}
		}
		return true;
	}

	public void cadastraTarefa() throws IllegalArgumentException {
		Task Task = new Task(this.getNome());

		for (int i = 0; i < this.controller.getTarefas().size(); i++) {
			if (this.controller.getTarefas().get(i).getNome()
					.equals(this.getNome())) {
				msgUsuario("ERRO: jah existe uma tarefa com esse nome!");
				return;
			}
		}

		Task.setDescricao(this.getDescricao());
		if (!testaData(Task) || !TestaHora(Task) || !testaNome()) {
			return;
		}

		this.controller.adicionaTarefa(Task);
		msgUsuario("Tarefa Cadastrada com sucesso!!!");
		clearSpaces();
	}

	public void salvarTarefa() {
		Task novaTarefa = new Task(this.getNome());
		novaTarefa.setDataCriacao(this.getTarefa().getDataCriacao());
		novaTarefa.setStatus(this.getTarefa().getStatus());

		for (int i = 0; i < this.controller.getTarefas().size(); i++) {
			if (this.controller.getTarefas().get(i).getNome()
					.equals(this.getNome())) {
				if (!this.controller.getTarefas().get(i)
						.equals(this.getTarefa())) {
					msgUsuario("ERRO: jah existe uma tarefa com esse nome!");
					return;
				}

			}
		}

		novaTarefa.setDescricao(this.getDescricao());
		if (!testaData(novaTarefa) || !TestaHora(novaTarefa) || !testaNome()) {
			return;
		}

		this.controller.editTarefa(this.getTarefa(), novaTarefa);
		msgUsuario("Tarefa Editada com sucesso!!!");
	}

	public void removeTarefa() {
		if (this.task == null) {
			msgUsuario("Clique em alguma tarefa incompleta para remove-la");
			return;
		}
		this.controller.removeTarefa(task);
	}

	public void ordena() {
		if (this.getOrdenacao().equals("")) {
			msgUsuario("Invalida");
			return;
		} else if (this.getOrdenacao().equals("dataCriacao")) {
			this.setComparador(new DateCriation());
		} else {
			this.setComparador(new DateConclusion());
		}
		this.controller.ordena(this.getComparador());
	}

	public void ordenaCompletas() {
		if (this.getOrdenacao().equals("")) {
			msgUsuario("Invalida");
			return;
		} else if (this.getOrdenacao().equals("dataCriacao")) {
			this.setComparador(new DateCriation());
		} else {
			this.setComparador(new DateConclusion());
		}
		this.controller.ordenaCompletas(this.getComparador());
	}

	public void ordenaIncompletas() {
		if (this.getOrdenacao().equals("")) {
			msgUsuario("Invalida");
			return;
		} else if (this.getOrdenacao().equals("dataCriacao")) {
			this.setComparador(new DateCriation());
		} else {
			this.setComparador(new DateConclusion());
		}
		this.controller.ordenaIncompletas(this.getComparador());
	}

	private Data getConclusionDate(String conclusiondate)
			throws IllegalArgumentException {
		return new Data(Integer.parseInt(conclusiondate.substring(0, 2)),
				Integer.parseInt(conclusiondate.substring(3, 5)),
				Integer.parseInt(conclusiondate.substring(6, 10)));
	}

	private Hour getConclusionTime(String conclusionTime)
			throws IllegalArgumentException {
		return new Hour(Integer.parseInt(conclusionTime.substring(0, 2)),
				Integer.parseInt(conclusionTime.substring(3, 5)));
	}

	private void msgUsuario(String string1) {
		FacesContext context = FacesContext.getCurrentInstance();
		context.addMessage(null, new FacesMessage(string1));
	}

}
