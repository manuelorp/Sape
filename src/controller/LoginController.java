package controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import dao.UserProvider;
import domain.Login;
import domain.UserDetails;

class UserValidator implements Validator {

	@Override
	public boolean supports(Class<?> cls) {
		return UserDetails.class.isAssignableFrom(cls);
	}

	@Override
	public void validate(Object obj, Errors errors) {
		// comprobar que 'username' y 'pw' no sean vacíos
		Login user = (Login) obj;
		if(user.getUsername() == null || user.getUsername().equals("") || user.getUsername().contains(" ")) errors.rejectValue("username", "Error", "Please enter a valid username");
		if(user.getPw() == null || user.getPw().equals("")) errors.rejectValue("pw", "Error", "Please enter your password");
	}
	
}

@Controller
public class LoginController {

	private UserProvider userProvider;
	
	
	@Autowired
	public void setUserProvider(HttpSession session, UserProvider userProvider) {
		this.userProvider = userProvider;
	}

	
	@RequestMapping(value="/login")
	public String login(HttpSession session, Model model) {
		model.addAttribute("login", new Login());
		return "login";
	}
	
	@RequestMapping(value="login", method=RequestMethod.POST)
	public String checkLogin(@ModelAttribute("login") Login login, BindingResult bindingResult, HttpSession session) {
		UserValidator userValidator = new UserValidator();
		userValidator.validate(login, bindingResult);
		if(bindingResult.hasErrors()) return "login";
		// comprueba login
		
		UserDetails newUser = userProvider.getUser(login.getUsername().toLowerCase(), login.getPw());
		if(newUser == null) {
			bindingResult.rejectValue("pw", "badpw", "Invalid username or password");
			return "login";
		}
		// datos correctos. Se guardan en la sesion
		session.setAttribute("user", newUser);
		if( newUser.isAdmin()) return "redirect:admin.jsp";
		return "redirect:student/index.html";
	}
	
	@RequestMapping(value="logout")
	public String logout(HttpSession session) {
		session.invalidate();
		return "redirect:index.jsp";
	}
}

