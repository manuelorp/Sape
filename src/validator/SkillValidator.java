package validator;

import java.util.List;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import dao.SkillDao;
import domain.UserDetails;
import model.Skill;

public class SkillValidator implements Validator {
	
	
	private List<Skill> skillList;
	public SkillValidator(SkillDao skillDao) {
		skillList = skillDao.getSkills();
	}
	
	@Override
	public boolean supports(Class<?> cls) {
		return UserDetails.class.isAssignableFrom(cls);
	}

	@Override
	public void validate(Object obj, Errors errors) {
		Skill skill = (Skill) obj;
		if(skill == null) errors.rejectValue("skill", "NOT_EXIST", "No object found");
		String name = skill.getName();
		String lvl = skill.getLvl();
		String status = skill.getStatus();
		String kind = skill.getKind();
		String description = skill.getDescription();
		if(description != null && description.length() > 200) errors.rejectValue("description", "INVALID_DESCRIPTION", "Please insert a valid skill description (max 200 chars)");
		if (kind != null && kind.length() > 50) errors.rejectValue("kind", "INVALID_TYPE", "Please insert a valid skill type (max 50 chars)");
		if(name == null || name.equals("") || name.length() < 2 || name.length() > 50) errors.rejectValue("name", "NO_NAME", "Please insert the skill's name");
		if(lvl == null || lvl.equals("")) errors.rejectValue("lvl", "NO_LEVEL", "Please insert the skill's level");
		if(status == null || status.equals("")) errors.rejectValue("status", "NO_STATUS", "Please insert the skill's status");
		if( (!lvl.equals("Basic")) && (!lvl.equals("Medium")) && (!lvl.equals("Advanced")) ) errors.rejectValue("lvl", "INVALID_LEVEL", "Please insert a valid skill level");
		if( (!status.equals("Active")) && (!status.equals("Cancelled")) ) errors.rejectValue("status", "INVALID_STATUS", "Please insert a valid skill status");
		
		if(skillList != null) 
			for(Skill s : skillList) 
				if( name.equals(s.getName()) && lvl.equals(s.getLvl()) ) errors.rejectValue("name", "DUPLICATE", "Skill exists already");
	}
}
