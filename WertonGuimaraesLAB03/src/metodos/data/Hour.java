package metodos.data;

import metodos.data.Hour;

public class Hour implements Comparable<Hour> {

	private Integer hora;
	private Integer minutos;

	public Hour(int hora, int minutos) throws IllegalArgumentException {
		if (hora > 23 || hora < 0)
			throw new IllegalArgumentException("Hora invalida");
		if (minutos > 59 || minutos < 0)
			throw new IllegalArgumentException("Minuto invalido");
		this.hora = hora;
		this.minutos = minutos;
	}

	public Hour() {
	}

	// GETS
	public Integer getHora() {
		return hora;
	}

	public Integer getMinutos() {
		return minutos;
	}

	// SETS
	public void setHora(int hora) throws IllegalArgumentException {
		if (hora > 23 || hora < 0)
			throw new IllegalArgumentException("Hora invalida");
		this.hora = hora;
	}

	public void setMinutos(int minutos) throws IllegalArgumentException {
		if (minutos > 59 || minutos < 0)
			throw new IllegalArgumentException("Minuto invalido");
		this.minutos = minutos;
	}

	// Auxiliares
	@Override
	public String toString() {
		if ((this.getHora() != null) && (this.getMinutos() != null)) {

			String hora = "", minutos = "";

			if (this.getHora() < 10)
				hora = "0" + this.getHora();
			else
				hora = "" + this.getHora();

			if (this.getMinutos() < 10)
				minutos = "0" + this.getMinutos();
			else
				minutos = "" + this.getMinutos();

			return hora + ":" + minutos;
		} else {
			return "Hora nao adicionada!";
		}
	}

	// Comparacao
	@Override
	public int compareTo(Hour hora) {
		// Testa Hora
		if (this.getHora() < hora.getHora())
			return -1;
		else if (this.getHora() > hora.getHora())
			return 1;
		// Testa Minutos
		else {
			if (this.getMinutos() > hora.getMinutos())
				return 1;
			else if (this.getMinutos() < hora.getMinutos())
				return -1;
		}
		return 0;
	}
}
