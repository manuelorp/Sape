package controller;

import java.util.ArrayList;
import java.util.List;

import javax.mail.MessagingException;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import dao.OfferDao;
import dao.RequestDao;
import dao.SkillDao;
import dao.StudentDao;
import domain.Mail;
import domain.UserDetails;
import model.Offer;
import model.Request;
import validator.RequestValidator;

/**
 * Created by Frost on 29/03/2017.
 */
@Controller
@RequestMapping(value="/request")
public class RequestController {

	private RequestDao requestDao;
	private StudentDao studentDao;
	private OfferDao offerDao;
	private SkillDao skillDao;
	private UserDetails user;

    @Autowired
    public void setStudentDao(RequestDao requestDao) {
        this.requestDao = requestDao;
    }

    @Autowired
	public void setSkillDao( SkillDao skillDao ) {
		this.skillDao = skillDao;
	}

    @Autowired
    public void setOfferDao(OfferDao offerDao) {
    	this.offerDao = offerDao;
    }
    
    @Autowired
    public void setStudentDao( StudentDao studentDao) {
    	this.studentDao = studentDao;
    }
    
    @RequestMapping(value="/index")
    public String indexRequest(Model model) {
        return "request/index";
    }

    @RequestMapping(value="/list")
    public String listRequests(Model model) {
        model.addAttribute("requests", requestDao.getRequests());
        return "request/list";
    }
    
    @RequestMapping(value="/request_list")
    public String requests(Model model) {
        model.addAttribute("requests", requestDao.getRequests());
        return "request/request_list";
    }

    @RequestMapping(value="/myRequests")
    public String myRequests(Model model, HttpSession session) {
    	user = (UserDetails) session.getAttribute("user");
    	if(user != null) model.addAttribute("requests", requestDao.getUserRequests(user));
        return "request/myRequests";
    }

    @RequestMapping(value="/add")
    public String addRequest(Model model ) {
        model.addAttribute( "request", new Request() );
        model.addAttribute("action", "request/add.html");
        return "request/add";
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public String processAddSubmit(@ModelAttribute("request") Request request, BindingResult bindingResult) {
		if(skillDao.getSkill(request.getSkillId()) == null ) {
			bindingResult.rejectValue("skillId", "INVALID_SKILL_ID", "This skill_ID does not exist in our database");
		}
		if(studentDao.getStudentByEmail(request.getEmail()) == null )
			bindingResult.rejectValue("email", "INVALID_EMAIL", "This email does not exist in our database");
		
		
		RequestValidator rv = new RequestValidator(requestDao);
		rv.validate(request, bindingResult);

    	if(bindingResult.hasErrors()) return "request/add";
        
    	requestDao.addRequest(request);
        List<Offer> offerList = offerDao.getOffersBySkillIdAndDate(request.getSkillId(), request.getStartDate(), request.getEndDate() );
        List<String> dest = new ArrayList<>();
        StringBuilder msg = new StringBuilder();
        msg.append("There is a new request!\n\n");
        msg.append("ID - Start Date - End Date - Requester - Skill Id - Description\n");
        msg.append(request.toString());
        msg.append("\n\n\n This is an automatic response email.\nPlease, DO NOT ANSWER BACK, as this email is not supervised.\n");
        msg.append("\nCOPYRIGHT: EI102716GGV - SKILL SHARING\n");
        if(offerList.size() > 0) {
        	for(Offer o : offerList) {
        		dest.add(o.getEmail());
        	}
        }
        if(dest.size() > 0) {
			try {
				new Mail().sendMessage("SKILL SHARING - Disponible request [DO NOT REPLY TO THIS EMAIL!]", msg.toString(), dest);
			} catch (MessagingException e) {
				e.printStackTrace();
			}
        }
        return "redirect:myRequests.html";
    }

   @RequestMapping(value = "/update/{id}", method = RequestMethod.GET)
   public String editStudent(Model model, @PathVariable int id) {
        model.addAttribute("request", requestDao.getRequest(id));
        model.addAttribute("action", "request/update.html");
        return "request/update";
   }

   @RequestMapping(value = "/update", method = RequestMethod.POST)
   public String processUpdateSubmit(@ModelAttribute("request") Request request,
                                     BindingResult bindingResult ) {
        if(bindingResult.hasErrors()) return "request/update";
        requestDao.updateRequest(request);
        return "redirect:../request/myRequests.html";
   }

   @RequestMapping(value = "/delete/{id}")
    public String processDelete(@PathVariable int id) {
        requestDao.deleteRequest(requestDao.getRequest(id));
        return "redirect:../myRequests.html";
   }
}
