package com.example.demo.form;

import com.sun.istack.internal.NotNull;

public class LoginForm {
	
	@NotNull
	private String pseudo;
	
	@NotNull
	private String pass;
	
	public LoginForm() {
		super();
	}
	
	public LoginForm(String pseudo, String pass) {
		super();
		this.pseudo = pseudo;
		this.pass = pass;
	}

	public String getPseudo() {
		return pseudo;
	}

	public void setPseudo(String pseudo) {
		this.pseudo = pseudo;
	}

	public String getPass() {
		return pass;
	}

	public void setPass(String pass) {
		this.pass = pass;
	}
	
}
