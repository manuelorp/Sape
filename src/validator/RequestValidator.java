package validator;

import java.sql.Date;
import java.util.List;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import dao.RequestDao;
import domain.UserDetails;
import model.Request;





public class RequestValidator implements Validator{
	
	private List<Request> requestList;
	public RequestValidator(RequestDao requestDao) {
		requestList = requestDao.getRequests();
	}

	@Override
	public boolean supports(Class<?> cls) {
		return UserDetails.class.isAssignableFrom(cls);
	}

	@Override
	public void validate(Object obj, Errors errors) {
		Request request = (Request) obj;
		
		if(request == null) errors.rejectValue("request", "NOT_EXIST", "No object found");
		
		int skillId = request.getSkillId();
		String email = request.getEmail();
		Date startDate = request.getStartDate();
		Date endDate = request.getEndDate();
		String description = request.getDescription();
		if(description != null && description.length() > 200) errors.rejectValue("description", "INVALID_DESCRIPTION", "Please insert a valid request description (max 200 chars)");
		if(email == null || email.equals("")) errors.rejectValue("email", "NO_EMAIL", "Please insert the email");
		if(startDate == null || startDate.equals("")) errors.rejectValue("startDate", "NO_START_DATE", "Please insert the start date");
		if(endDate == null || endDate.equals("")) errors.rejectValue("endDate", "NO_END_DATE", "Please insert the end date");
		if( endDate.compareTo(startDate) < 0 ) errors.rejectValue("endDate", "INVALID_DATES", "End Date cannot be proir to Start Date");
		
		for(Request r : requestList) {
			if( email.equals(r.getEmail()) && startDate.equals(r.getStartDate()) && endDate.equals(r.getEndDate()) && skillId == r.getSkillId() )  errors.rejectValue("email", "DUPLICATE", "This request exists already");
		}
	}
}
