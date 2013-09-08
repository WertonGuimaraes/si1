package metodos.data;

import java.util.Comparator;

import model.Task;
public class DateCriation implements Comparator<Task>{
	
	@Override
	public int compare(Task task1, Task task2) {
		if(task1.getDataCriacao().equals(task2.getDataCriacao())){
			return task1.getNome().compareTo(task2.getNome());
		}
		return task1.getDataCriacao().compareTo(task2.getDataCriacao());
	}
}