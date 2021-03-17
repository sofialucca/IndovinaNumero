package it.polito.tdp.IndovinaNumero.model;

import java.security.InvalidParameterException;
import java.util.*;

public class Model {
	
	private final int NMAX = 100;
	private final int TMAX = 8;
	private int segreto;
	private int tentativiFatti;
	private boolean inGioco = false;
	private Set<Integer> setTentativi;
	
	public void nuovaPartita() {
    	//gestione inizio nuova partita
    	this.segreto = (int) (Math.random() * NMAX) +1;
    	this.tentativiFatti = 0;
    	this.inGioco = true;
    	this.setTentativi=new HashSet<>();
	}

	public int tentativo(int tentativo) {
		//controlla se partita in corso
		if(!inGioco) {
			throw new IllegalStateException("Hai perso. La soluzione era "+segreto);
		}
		
		//controllo dell'input
		if(!tentativoValido(tentativo)) {
			throw new InvalidParameterException("Devi inserire un numero tra 1 e "+NMAX+"\nNon puoi inserire due volte lo stesso numero.");
		}
		
		//tentativo valido
		this.tentativiFatti++;
		this.setTentativi.add(tentativo);
		
		if(this.tentativiFatti==TMAX-1) {
			this.inGioco= false;
		}
		
		if(tentativo==this.segreto) {
			this.inGioco=false;
			return 0;
		}else if(tentativo<this.segreto) {
			return -1;
		}else {
			return 1;
		}
	}
	
	private boolean tentativoValido(int tentativo) {
		if(tentativo<1||tentativo>NMAX) {
			return false;
		}else if(setTentativi.contains(tentativo)){
			return false;
		}else {
			return true;
		}
	}
	
	public int getNMAX() {
		return NMAX;
	}

	public int getTMAX() {
		return TMAX;
	}

	public int getSegreto() {
		return segreto;
	}

	public int getTentativiFatti() {
		return tentativiFatti;
	}
	
}
