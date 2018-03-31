package controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import dao.SkillDao;
import model.Skill;
import validator.SkillValidator;

/**
 * Created by Frost on 29/03/2017.
 */
@Controller
@RequestMapping(value="/skill")
public class SkillController {

	private SkillDao skillDao;
	
    @Autowired
    public void setSkillDao(SkillDao skillDao) {
        this.skillDao = skillDao;
    }

    @RequestMapping(value="/index")
    public String indexSkills(Model model) {
        return "skill/index";
    }
    
    @RequestMapping(value="/list")
    public String listSkill(Model model) {
        model.addAttribute("skills", skillDao.getSkills());
        return "skill/list";
    }

    @RequestMapping(value="/skill_list")
    public String skill(Model model) {
        model.addAttribute("skills", skillDao.getSkills());
        return "skill/skill_list";
    }

    @RequestMapping(value="/add")
    public String addSkill(Model model ) {
        model.addAttribute( "skill", new Skill() );
        model.addAttribute("action", "skill/add.html");
        return "skill/add";
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public String processAddSubmit(@ModelAttribute("skill") Skill skill, BindingResult bindingResult, Model model) {
    	SkillValidator sv = new SkillValidator(skillDao);
    	sv.validate(skill, bindingResult);
    	if(bindingResult.hasErrors()) return "skill/add";
        skillDao.addSkill(skill);
        return "redirect:list.html";
    }

   @RequestMapping(value = "/update/{id}", method = RequestMethod.GET)
   public String editSkill(Model model, @PathVariable int id) {
        model.addAttribute("skill", skillDao.getSkill(id));
        model.addAttribute("action", "skill/update.html");
        return "skill/update";
   }

   @RequestMapping(value = "/update", method = RequestMethod.POST)
   public String processUpdateSubmit(@ModelAttribute("skill") Skill skill,
                                     BindingResult bindingResult ) {
        if(bindingResult.hasErrors()) return "skill/update";
        System.out.println("----- Skill Update - Controller -----");
        System.out.println("Skill ID: " + skill.getId() + " - Name: " + skill.getName() + " - Lvl: " + skill.getLvl() + " - Status: " + skill.getStatus() + " - Kind: " + skill.getKind() + " - Description: " + skill.getDescription());
        System.out.println("-----------------------");
        skillDao.updateSkill(skill);
        return "redirect:../skill/list.html";
   }

   @RequestMapping(value = "/delete/{id}")
    public String processDelete(@PathVariable int id) {
        skillDao.deleteSkill(skillDao.getSkill(id));
        return "redirect:../list.html";
   }
}
