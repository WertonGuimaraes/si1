package metodos.data;

import java.util.Comparator;

import model.Task;
public class DateConclusion implements Comparator<Task>{
	@Override
	public int compare(Task task1, Task task2) {
		if(task1.getDataConclusao().equals(task2.getDataConclusao())){
			return task1.getNome().compareTo(task2.getNome());
		}
		return task1.getDataConclusao().compareTo(task2.getDataConclusao());
	}
}