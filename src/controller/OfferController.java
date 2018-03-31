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
import validator.OfferValidator;

/**
 * Created by Frost on 29/03/2017.
 */
@Controller
@RequestMapping(value="/offer")
public class OfferController {

	private OfferDao offerDao;
	private SkillDao skillDao;
	private StudentDao studentDao;
	private RequestDao requestDao;
	private UserDetails user;
	
    @Autowired
    public void setOfferDao(OfferDao offerDao) {
    	this.offerDao = offerDao;
    }
	
    @Autowired
	public void setSkillDao( SkillDao skillDao ) {
		this.skillDao = skillDao;
	}
    
    @Autowired
    public void setStudentDao( StudentDao studentDao) {
    	this.studentDao = studentDao;
    }
    
    @Autowired
    public void setRequestDao(RequestDao requestDao) {
    	this.requestDao = requestDao;
    }
    
    @RequestMapping(value="/index")
    public String indexOffers(Model model) {
        return "offer/index";
    }
    
    @RequestMapping(value="/list")
    public String listOffers(Model model) {
        model.addAttribute("offers", offerDao.getOffers());
        return "offer/list";
    }

    @RequestMapping(value="/offer_list")
    public String offers(Model model, HttpSession session) {
        model.addAttribute("offers", offerDao.getOffers());
        return "offer/offer_list";
    }

    
    @RequestMapping(value="/myOffers")
    public String myOffers(Model model, HttpSession session) {
    	user = (UserDetails) session.getAttribute("user");
    	if(user != null) model.addAttribute("offers", offerDao.getUserOffers(user));
        return "offer/myOffers";
    }
    
    @RequestMapping(value="/add")
    public String addOffer(Model model ) {
        model.addAttribute( "offer", new Offer() );
        model.addAttribute("action", "offer/add.html");
        return "offer/add";
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public String processAddSubmit(@ModelAttribute("offer") Offer offer, BindingResult bindingResult) {
		if(skillDao.getSkill(offer.getSkillId()) == null ) {
			bindingResult.rejectValue("skillId", "INVALID_SKILL_ID", "This skill_ID does not existin our database");
		}
		
		if(studentDao.getStudentByEmail(offer.getEmail()) == null )
			bindingResult.rejectValue("email", "INVALID_EMAIL", "This email does not existin our database");
		
		OfferValidator ov = new OfferValidator(offerDao);
		ov.validate(offer, bindingResult);

        if(bindingResult.hasErrors()) return "offer/add";
        
        offerDao.addOffer(offer);
        List<Request> reqList = requestDao.getRequestsBySkillIdAndDate(offer.getSkillId(), offer.getStartDate(), offer.getEndDate() );
        List<String> dest = new ArrayList<>();
		StringBuilder msg = new StringBuilder();
        msg.append("There is a new offer!\n\n");
        msg.append("ID - Start Date - End Date - Offeror - Skill Id - Description\n");
        msg.append(offer.toString());
        msg.append("\n\n\n This is an automatic response email.\nPlease, DO NOT ANSWER BACK, as this email is not supervised.\n");
        msg.append("\nCOPYRIGHT: EI102716GGV - SKILL SHARING\n");
        if(reqList.size() > 0) {
        	for(Request r : reqList) {
        		dest.add(r.getEmail());
        	}
        }
        if(dest.size() > 0) {
	        try {
				new Mail().sendMessage("SKILL SHARING - Disponible offer [DO NOT REPLY TO THIS EMAIL!]", msg.toString(), dest);
			} catch (MessagingException e) {
				e.printStackTrace();
			}
        }
    	return "redirect:myOffers.html";
    }

   @RequestMapping(value = "/update/{id}", method = RequestMethod.GET)
   public String editOffer(Model model, @PathVariable int id) {
        model.addAttribute("offer", offerDao.getOffer(id));
        model.addAttribute("action", "offer/update.html");
        return "offer/update";
   }

   @RequestMapping(value = "/update", method = RequestMethod.POST)
   public String processUpdateSubmit(@ModelAttribute("offer") Offer offer,
                                     BindingResult bindingResult ) {
        if(bindingResult.hasErrors()) return "offer/update";
        offerDao.updateOffer(offer);
        return "redirect:../offer/myOffers.html";
   }

   @RequestMapping(value = "/delete/{id}")
    public String processDelete(@PathVariable int id) {
        offerDao.deleteOffer(offerDao.getOffer(id));
        return "redirect:../myOffers.html";
   }
}
