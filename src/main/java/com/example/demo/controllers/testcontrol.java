package com.example.demo.controllers;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.DAO.CryptitudeDao;
import com.example.demo.DAO.UserDao;
import com.example.demo.beans.Action;
import com.example.demo.beans.AgendaGroupe;
import com.example.demo.beans.AgendaPerso;
import com.example.demo.beans.Invitation;
import com.example.demo.beans.User;
import com.example.demo.enums.Role;
import com.example.demo.services.ServiceAgendaGroupe;
import com.example.demo.services.ServiceUser;

@RestController
@CrossOrigin(origins="http://localhost:4200", allowCredentials = "true")                           // @CrossOrigin is used to handle the request from a difference origin.
public class testcontrol {
	
	@Autowired
	ServiceUser su;
    @Autowired
    private JavaMailSender emailSender;
    @Autowired
    CryptitudeDao cd;

//    @Autowired
//    @Qualifier("genericdDao")
//    GenericDAO genericdDao;
    
	@Autowired
	UserDao userDao;

	@Autowired
	ServiceUser serviceUser;
	@Autowired
	ServiceAgendaGroupe serviceAgendaGroupe;
    
      //--------------------//
	 //--- --- USER --- ---//
	//--------------------//
	
    @GetMapping(path="/user", produces = "application/json")
    public User users() {
		User u = new User("toto","toto@toto.fr","123",LocalDate.now());
		return u;
	}

    //findall
    @GetMapping(path="/findusers", produces = "application/json")
	@ResponseBody
    public List<User> findusers() {
    	return userDao.findAll(User.class);
    }

    //findById
    @GetMapping(path="/finduser/{id}", produces = "application/json")
	@ResponseBody
    public User finduser(@PathVariable(name = "id") long id) {
    	return userDao.findById(User.class, id);
    }

    //findByPseudo
    @GetMapping(path="/finduser/bypseudo/{pseudo}", produces = "application/json")
	@ResponseBody
    public User findpseudouser(@PathVariable(name = "pseudo") String pseudo) {
    	return userDao.chercheruser(pseudo);
    }
    
    //signup
    @PostMapping(path="/signup", produces = "text/plain")
    public String signup(@RequestParam String pseudo, @RequestParam String mail,
    					  @RequestParam String pass, @RequestParam String pass2,
    					  @RequestParam String ddn, HttpServletRequest request) {
    	LocalDate ladate = LocalDate.parse(ddn);
    	HttpSession session = request.getSession();
    	return serviceUser.createuser(pseudo,mail,pass,pass2,ladate,session);
    }
    
    //connect
    @PostMapping(path="/log", produces = "text/plain")
    public String log(@RequestParam String pseudo, @RequestParam String pass, HttpServletRequest request) {
    	User u = new User();
    	HttpSession session = request.getSession();
    	return serviceUser.connect(u, pseudo, pass, session);
    }
    
    //vérifier session
    @GetMapping(path="/checksession", produces = "application/json")
    public User sessioncheck(HttpServletRequest request) {
    	HttpSession session = request.getSession();
    	User u = (User) session.getAttribute("user");
    	return u;
    }
    
    //logout
    @GetMapping(path="/logout")
    public void logout(HttpServletRequest request) {
    	HttpSession session = request.getSession();
    	serviceUser.deconnect(session);
    }
    
    //mot de passe perdu
    @PostMapping(path="/motdepasseperdu")
    public void passperdu2(@RequestParam String mail) {
    	serviceUser.passperdu2(mail);
    }
    
      //----------------------------//
	 //--- --- AGENDA PERSO --- ---//
	//----------------------------//

    //creer les agenda perso
    @GetMapping(path="/clap")
    public void clap() {
    	List<User> lu = userDao.findAll(User.class);
    	for(User u : lu) {
    		AgendaPerso ap = new AgendaPerso("Mon Agenda", "Agenda de " + u.getPseudo(), u);
    		userDao.create(ap);
    	}
    }   
    
    //mon agenda
    @PostMapping(path="/monagenda", produces = "application/json")
    public AgendaPerso monagenda(HttpServletRequest request) {
    	User u = sessioncheck(request);
    	return userDao.chercheragendaperso(u);
    }
    
    //ajouter une action
    @PostMapping(path="/addaction")
    public void addaction(HttpServletRequest request,
    					  @RequestParam String titre,
    					  @RequestParam String description,
    					  @RequestParam String date_debut,
    					  @RequestParam String date_fin,
    					  @RequestParam String lieu) {
    	User u = sessioncheck(request);
    	DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
    	LocalDateTime ladatedebut = LocalDateTime.parse(date_debut,formatter);
    	LocalDateTime ladatefin = LocalDateTime.parse(date_fin,formatter);
    	AgendaPerso ap = userDao.chercheragendaperso(u);
    	Action a = new Action(titre, description, ladatedebut, ladatefin, lieu, ap);
    	userDao.create(a);
    }   
    
    //find group by idadmin
    @PostMapping(path="/mesactions", produces = "application/json")
    public List<Action> findmystuff(HttpServletRequest request) {
    	User u = sessioncheck(request);
		return userDao.chercheractionsperso(u);
    }
    
    
      //-----------------------------//
	 //--- --- AGENDA GROUPE --- ---//
	//-----------------------------//

    //add Group
    @PostMapping(path="/creategroup", produces = "text/plain")
    public void addgroup(@RequestParam String intitule, 
    					 @RequestParam String description, 
    					 @RequestParam String titre,
    					 HttpServletRequest request) {
    	User u = sessioncheck(request);
    	serviceAgendaGroupe.creerAgendaGroupe(u, intitule, description, titre);
    }
    
    //find all agenda groupe
    @PostMapping(path="/listgroups", produces = "application/json")
    public List<AgendaGroupe> findgroups() {
    	return userDao.findAll(AgendaGroupe.class);
    }    
    
    //find group by idadmin
    @PostMapping(path="/listgroupuser", produces = "application/json")
    public List<AgendaGroupe> findgrpbyadm(HttpServletRequest request) {
    	List<AgendaGroupe> ladg = userDao.findAll(AgendaGroupe.class);
    	List<AgendaGroupe> ladgres = new ArrayList<AgendaGroupe>();
    	User u = sessioncheck(request);
    	
    	for(AgendaGroupe ag : ladg) {
    		if(ag.getLstu().get(u.getId())==Role.Administrateur) {
    			ladgres.add(ag);
    			System.out.println(ag);
    		}
    	}
    	
    	return ladgres;
    }
    
    //ajouter membre au groupe : 
    	//1) inviter Invitation(AgendaGroupe agGroupe, User expediteur, User destinataire, String libelle, String description,
		//						Date date_invit, int duree_avant_expiration)
    	
    @PostMapping(path="/inviter")
    public void inviter(@RequestParam Long idag, // <input hide
    					 @RequestParam String dest, // Pseudo
    					 @RequestParam String libelle,
    					 @RequestParam String description,
    					 HttpServletRequest request) {
    	User u = sessioncheck(request);
    	LocalDate dateinvit = LocalDate.now();
    	Invitation inv = new Invitation();
    	inv.setIdag(idag);
    	inv.setDest(dest);
    	inv.setExp(u);
    	inv.setLibelle(libelle);
    	inv.setDescription(description);
    	inv.setDate_invit(dateinvit);
    	userDao.create(inv);
    }
    
    	//2) accepter ou déclinner invitation
    @PostMapping(path="/listinvituser")
    public List<Invitation> listinvituser(HttpServletRequest request) {
    	List<Invitation> lstinv = new ArrayList<Invitation>();
    	User u = sessioncheck(request);
    	for(Invitation inv : userDao.findAll(Invitation.class)) {
    		long idd = userDao.chercheruser(inv.getDest()).getId();
    		if(idd==u.getId()) {
    			lstinv.add(inv);
    		}
    	}
    	return lstinv;
    }
    
    @PostMapping(path="/repondreinvit")
    public void repondreinvit(HttpServletRequest request, @RequestParam int reponse, @RequestParam Long idinv) {
    	User u = sessioncheck(request);
    	Invitation inv = userDao.findById(Invitation.class, idinv);
    	AgendaGroupe ag = userDao.findById(AgendaGroupe.class, inv.idag);
    	if(reponse==1) {
    		serviceAgendaGroupe.adduser(ag, u);
    	}
    	userDao.delete(Invitation.class, idinv);
    }
    
    
    /*
	@GetMapping(path="/home")
	public String home(HttpServletRequest request) {
		HttpSession session = request.getSession();
		User u1 = null;
		
		if(session.getAttribute("user")==null) {
			return "testlog";
		}else {
			LocalDate serverTime = LocalDate.now();
			u1 = (User) session.getAttribute("user");
			request.setAttribute("serverTime", serverTime);
			request.setAttribute("u1", u1);
			return "home";
		}
	}
	
	@GetMapping("/testuser404")
	public String testuser(HttpServletRequest request) {
		HttpSession session = request.getSession();
		String res = "home";
		
		User u1 = null;
		String co = su.connect(u1,"Roger2", "rogerpass", session);
		if(session.getAttribute("user")!=null) {
			u1 = (User) session.getAttribute("user");
			System.out.println(u1.getPseudo());
		}
		
		su.passperdu(u1);
		
		su.createuser("Roberto", "rbt@mail.com", "blabla", "blabla", LocalDate.now());
		
		return res;
	}

	@GetMapping("/testmailhtml")
	public String testMailhtml() {
		su.envoimail("test@mail.com", "essai@mail.com", "Sujet du mail", "Texte du mail");
		return "home";
	}

	@GetMapping("/crypt")
	public String crypter() {
		System.out.println(cd.cryptage("rogerpass"));
		return "home";
	}

	@GetMapping("/deco")
	public String deco(HttpServletRequest request) {
		HttpSession session = request.getSession();
		su.deconnect(session);
		return "home";
	}
	
	@GetMapping("/testgetclass")
	public String tgc() {
		Invitation i = new Invitation();
		System.out.println(i.getClass());
		return "home";
	}
	
	@GetMapping("/login")
	public String userGetLogin() {
		return "testlog";
	}
	
	@PostMapping("/login")
	public ModelAndView userLogin(@Valid @ModelAttribute("loginform") LoginForm logform, BindingResult result, HttpServletRequest request) {
		ModelAndView mav = new ModelAndView();
		HttpSession session = request.getSession();
		User u1=null;
		
		if(result.hasErrors()) {
			mav.addObject("error", result);
			mav.addObject("loginform", logform);
			mav.setViewName("testlog");
		} else {
			String pseudo = logform.getPseudo();
			String pass = logform.getPass();
			su.connect(u1, pseudo, pass, session);
			mav.setViewName("redirect:/home");
		}
		return mav;
	}*/

}
