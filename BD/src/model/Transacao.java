package model;

import java.util.List;

public class Transacao {
	
	private String id;
	private List<String> comandos;
	private boolean inicio;
	private boolean fim;
	
	public Transacao(String id){
		this.id = id;
		setInicio(false);
		setFim(false);
	}
	

	public boolean isInicio() {
		return inicio;
	}

	public void setInicio(boolean inicio) {
		this.inicio = inicio;
	}

	public boolean isFim() {
		return fim;
	}

	public void setFim(boolean fim) {
		this.fim = fim;
	}
	
	public String getId(){
		return this.id;
	}
	
	
}
