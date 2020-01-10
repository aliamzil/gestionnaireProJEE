package com.example.demo.beans;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@Entity
public class Reponse extends DbObject {
	
	@ManyToOne
	private Sondage sondage;
	

	//private List<Integer> listrep=new ArrayList<Integer>();
	private int rep = 0;
	
	
	public Reponse(Sondage sondage, int rep) {
		super();
		this.sondage = sondage;
		this.rep = rep;
	}
	public Reponse() {
		super();
	}
	
}
