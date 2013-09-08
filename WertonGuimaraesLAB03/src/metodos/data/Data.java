package metodos.data;

import metodos.data.Data;


public class Data implements Comparable<Data> {

	private int day;
	private int year;
	private int month;

	public Data(int day, int month, int year) throws IllegalArgumentException {
		//Verifica Dia
		if ((month == 2 && day > 29) || (day < 1 || day > 31)) 	throw new IllegalArgumentException("Dia invalido");
		
		//Verifica Mes
		if ((day == 31) && (month == 2 || month == 4 || month == 6 || month == 9 || month == 11))
				throw new IllegalArgumentException("Mes invalido");
		//Verifica Mes
		if ((month < 1 || month > 12))
				throw new IllegalArgumentException("Mes invalido");
		
		//Verifica Bissexto;
		if (month == 2 && day == 29) {
			if (!(year % 4 == 0))	throw new IllegalArgumentException("Ano invalido");
			else {
				if ((year % 100 == 0))	throw new IllegalArgumentException("Ano invalido");
			}
		}
		

		this.day = day;
		this.month = month;
		this.year = year;
	}
	
	public Data() {}
	
	
	//    GETS	
	public int getDia() { return this.day;}	
	public int getMes() { return month;}	
	public int getAno() { return year;}
	
	//   SETS
	public void setDia(int dia) throws IllegalArgumentException {
		if (month==2)   {
			if(dia >= 1 && dia <= 28){
				this.day = dia; 
			}else{
				if (dia == 29 && ehBissexto()){
					this.day = dia; 
				}
			}
		}
		
		else if ((month==4 || month==6 || month==9 || month==11) && (dia >= 1 && dia <= 30)){
			this.day = dia;
		}
		
		else if ((month==1 || month==3 || month==5 || month==7 || month==8 || month==10 || month==12) && (dia >= 1 && dia <= 31)){
			this.day = dia;
		}
		else {
			throw new IllegalArgumentException("Dia invalido");
		}
	}

	public void setMes(int mes) throws IllegalArgumentException {
		if (mes >= 1 && mes <= 12) {
			this.month = mes;
		} else {
			throw new IllegalArgumentException("Mes invalido");
		}
	}
	
	public void setAno(int ano) throws IllegalArgumentException {
		if (ano > 1900) this.year = ano;
		else throw new IllegalArgumentException("Ano invalido");		
	}
	
	//Auxiluares
	private boolean ehBissexto() {
		return ( ( (!(year % 4 ==0)) && (year % 100) == 0 ) || (!(year % 400 == 0)) ) ;
	}
	
	@Override
	public String toString() {
		
		if(day==0 || month== 0 || year == 0){
			return "Data nao Adicionada";
		}
		
		String dia = "", mes = "", ano = "";
		//dia
		if(this.getDia() < 10) dia = "0"+this.getDia();
		else dia = ""+this.getDia();
		//mes
		if(this.getMes() < 10) mes = "0"+this.getMes();
		else mes = ""+this.getMes();
		//ano
		if(this.getAno() < 10) ano = "000"+this.getAno();
		else if (this.getAno() > 9 && this.getAno() < 100) ano = "00"+this.getAno();
		else if (this.getAno() > 99 && this.getAno() < 1000) ano = "0"+this.getAno();
		else ano = ""+this.getAno();
		
		return dia + "/" + mes + "/" + ano;
	}
	
	//Comaparacao
	@Override
	public int compareTo(Data data) {
		if (this.getAno() == 0 && this.getMes() == 0 && this.getDia() == 0)
			return 2;
		
		//Testa Ano
		if (data.getAno() > this.getAno()) {
			return -1;
		} else if (data.getAno() < this.getAno()) {
			return 1;
		} else {
			//Testa Mes
			if (data.getMes() > this.getMes()) {
				return -1;
			} else if (data.getMes() < this.getMes()) {
				return 1;
			//Testa Dia
			} else {
				if (data.getDia() > this.getDia()) {
					return -1;
				} else if (data.getDia() < this.getDia()) {
					return 1;
				}
			}
		}
		return 0;
	}
	
	
}
