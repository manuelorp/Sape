package controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import dao.CollaborationDao;
import dao.SkillDao;
import dao.StudentDao;
import domain.UserDetails;
import model.Collaboration;
import validator.CollaborationValidator;

/**
 * Created by Frost on 29/03/2017.
 */
@Controller
@RequestMapping(value="/collaboration")
public class CollaborationController {

	private CollaborationDao collaborationDao;
	private SkillDao skillDao;
	private StudentDao studentDao;

	private UserDetails user;
	
	@Autowired
	public void setCollaborationDao( CollaborationDao collaborationDao ) {
		this.collaborationDao = collaborationDao;
	}

	@Autowired
	public void setSkillDao( SkillDao skillDao ) {
		this.skillDao = skillDao;
	}
	
    @Autowired
    public void setStudentDao( StudentDao studentDao) {
    	this.studentDao = studentDao;
    }
    
    @RequestMapping(value="/index")
    public String indexCollaborations(Model model) {
        return "collaboration/index";
    }
    
	@RequestMapping(value="/list")
	public String listCollaborations(Model model) {
		model.addAttribute("collaborations", collaborationDao.getCollaborations());
		return "collaboration/list";
	}

    @RequestMapping(value="/myCollaborations")
    public String myCollaborations(Model model, HttpSession session) {
    	user = (UserDetails) session.getAttribute("user");
    	if(user != null) model.addAttribute("collaborations", collaborationDao.getUserCollaborations(user));
        return "collaboration/myCollaborations";
    }
	
	@RequestMapping(value="/add")
	public String addCollaboration(Model model ) {
		model.addAttribute( "collaboration", new Collaboration() );
        model.addAttribute("action", "collaboration/add.html");
		return "collaboration/add";
	}

	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public String processAddSubmit(@ModelAttribute("collaboration") Collaboration collaboration, BindingResult bindingResult) {
		if(skillDao.getSkill(collaboration.getSkillId()) == null ) {
			bindingResult.rejectValue("skillId", "INVALID_SKILL_ID", "This skill_ID does not exist in our database");
		}
		if(studentDao.getStudentByEmail(collaboration.getEvaluatorStudent()) == null )
			bindingResult.rejectValue("evaluatorStudent", "INVALID_EMAIL", "This email does not exist in our database");

		if(studentDao.getStudentByEmail(collaboration.getEvaluatedStudent()) == null )
			bindingResult.rejectValue("evaluatedStudent", "INVALID_EMAIL", "This email does not exist in our database");

		CollaborationValidator cv = new CollaborationValidator();
		cv.validate(collaboration, bindingResult);
		if(bindingResult.hasErrors()) {
			return "collaboration/add";
		}
		collaborationDao.addCollaboration(collaboration);
		return "redirect:myCollaborations.html";
	}

	@RequestMapping(value = "/update/{id}", method = RequestMethod.GET)
	public String editCollaboration(Model model, @PathVariable int id) {
		model.addAttribute("collaboration", collaborationDao.getCollaboration(id));
        model.addAttribute("action", "collaboration/update.html");
		return "collaboration/update";
	}

	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public String processUpdateSubmit(@ModelAttribute("collaboration") Collaboration collaboration,
			BindingResult bindingResult ) {
		if(bindingResult.hasErrors()) return "collaboration/update";
		collaborationDao.updateCollaboration(collaboration);
		return "redirect:../collaboration/myCollaborations.html";
	}

	@RequestMapping(value = "/delete/{id}")
	public String processDelete(@PathVariable int id) {
		collaborationDao.deleteCollaboration(collaborationDao.getCollaboration(id));
		return "redirect:../myCollaborations.html";
	}
}
