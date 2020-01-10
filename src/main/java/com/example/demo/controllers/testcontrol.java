package com.example.demo.controllers;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.DAO.CryptitudeDao;
import com.example.demo.DAO.UserDao;
import com.example.demo.beans.User;
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

	@Autowired
	UserDao userDao;
    
    
    @GetMapping(path="/user", produces = "application/json")
    public User users() {
		User u = new User("toto","toto@toto.fr","123",LocalDate.now());
		return u;
	}
    
    @GetMapping(path="/finduser/{id}", produces = "application/json")
	@ResponseBody
    public User finduser(@PathVariable(name = "id") long id) {
    	return userDao.findById(User.class, id);
    }
    
    //findById
    //add
    
    
    
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
