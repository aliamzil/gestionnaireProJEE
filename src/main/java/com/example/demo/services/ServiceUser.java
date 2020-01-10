package com.example.demo.services;

import java.time.LocalDate;

import javax.mail.Message;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Service;

import com.example.demo.DAO.CryptitudeDao;
import com.example.demo.DAO.UserDao;
import com.example.demo.beans.User;

@Service
public class ServiceUser {

	@Autowired
	UserDao udao;
	
	@Autowired
	CryptitudeDao crpdao;
	
    @Autowired
    private JavaMailSender emailSender;

	public String createuser(String pseudo, String mail, String pass, String repass, LocalDate ddn) {

		System.out.println(pass + " - " + repass);

		User uu = null;
		uu = udao.chercheruser(pseudo);

		System.out.println("test 0");
		if (uu != null) {
			return "Pseudo d�ja utilis�";
		}

		User uu2 = null;
		uu2 = udao.chercheruseremail(mail);

		System.out.println("test 0");
		if (uu2 != null) {
			return "Email d�ja utilis�";
		}

		if (pass.equals(repass)) {
			User u = new User(pseudo, mail, crpdao.cryptage(pass), ddn);
			udao.create(u);
			this.envoimail(mail, "signup@agentask.app", "Compte cr��","Cr�ation compte ok, [a href=Lien de confirmation de l'adresse mail]clickez pour confirmer l'adresse mail[/a]");
			return "ok";
		} else {
			return "Le mot de passe de confirmation n'est pas identique au premier !";
		}
	}

	public int seconnecter(String pseudo, String pass) {
		int res = 0; // 0 = Default ; 1 = Pseudo incorect ; 2 = Mot de passe incorect ; 3 = OK
		User u1;

		try {
			u1 = udao.chercheruser(pseudo);
			String crpass = crpdao.cryptage(pass);
			String u1pass = u1.getPass();

			if (!crpass.equals(u1pass)) {
				res = 2;
			} else {
				res = 3;
			}
		} catch (Exception e) {
			res = 1;
		}

		return res;
	}

	public String connect(User u1, String pseudo, String pass, HttpSession session) {

		int res01 = this.seconnecter(pseudo, pass);

		switch (res01) {
		case 1:
			return "Pseudo incorect";
		case 2:
			return "Mot de passe incorect";
		case 3:
			u1 = udao.chercheruser(pseudo);
			session.setAttribute("user", u1);
			return "ok";
		default:
			return "Erreur inconue";
		}
	}

	public void deconnect(HttpSession session) {
		session.invalidate();
	}
	
	public void envoimail(final String to, final String from, final String sujet, final String texte) {
		MimeMessagePreparator messagePreparator = new MimeMessagePreparator() {
			public void prepare(MimeMessage mimeMessage) throws Exception {
				mimeMessage.setRecipient(Message.RecipientType.TO, new InternetAddress(to));
				mimeMessage.setFrom(new InternetAddress(from));
				mimeMessage.setSubject(sujet, "utf-8");
				mimeMessage.setContent(texte, "text/html");
			}
		};
		emailSender.send(messagePreparator);
	}
	
	public void passperdu(User u) {
		this.envoimail(u.getEmail(), "pasrecover@agentask.app", "mot de passe perdu","Blablabla, [a href=lien vers la page de cr�ation de nouveau mot de passe]lien[/a], clickez, la la la");
	}
}
