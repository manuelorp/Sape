package validator;

import java.sql.Date;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import domain.UserDetails;
import model.Collaboration;

public class CollaborationValidator implements Validator {

	
	@Override
	public boolean supports(Class<?> cls) {
		return UserDetails.class.isAssignableFrom(cls);
	}

	@Override
	public void validate(Object obj, Errors errors) {
		Collaboration collaboration = (Collaboration) obj;
		
		if(collaboration == null) errors.rejectValue("collaboration", "NOT_EXIST", "No object found");

		int skillId = collaboration.getSkillId();
		int grade = collaboration.getAssessment();
		double hours = collaboration.getTotalHours();
		String email1 = collaboration.getEvaluatorStudent();
		String email2 = collaboration.getEvaluatedStudent();
		Date start = collaboration.getStartDate();
		Date end = collaboration.getEndDate();
		
		if(email1 == null || email1.equals("")) errors.rejectValue("evaluatorStudent", "NO_EVALUATOR_EMAIL", "Please insert the evaluator student's email");
		if(email2 == null || email2.equals("")) errors.rejectValue("evaluatedStudent", "NO_EVALUATED_EMAIL", "Please insert the evaluated student's email");
		if(start == null || start.equals("")) errors.rejectValue("starDate", "NO_START_DATE", "Please insert the collaboration's start date");
		if(end == null || end.equals("")) errors.rejectValue("endDate", "NO_END_DATE", "Please insert the collaboration's end date");
		if( !email1.contains("@")) errors.rejectValue("evaluatorStudent", "INVALID_EMAIL", "Please insert a valid email");
		if( !email2.contains("@")) errors.rejectValue("evaluatedStudent", "INVALID_EMAIL", "Please insert a valid email");
		if( grade < 1 || grade > 5) errors.rejectValue("assessment", "INVALID_GRADE", "Please insert a valid grade (from 1 to 5)");
		if( skillId < 1 ) errors.rejectValue("skillId", "INVALID_SKILL_ID", "Please insert a valid skill ID");
		if( hours < 0 || hours > 10) errors.rejectValue("totalHours", "INVALID_HOURS_NUMBER", "Please insert a valid number of hours (higher than 0, lower than or equal to 10)");
		
	}

}
