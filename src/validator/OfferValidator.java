package validator;

import java.sql.Date;
import java.util.List;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import dao.OfferDao;
import domain.UserDetails;
import model.Offer;



public class OfferValidator implements Validator {
	
	private List<Offer> offerList;
	
	public OfferValidator(OfferDao offerDao) {
		offerList = offerDao.getOffers();
	}

	@Override
	public boolean supports(Class<?> cls) {
		
		return UserDetails.class.isAssignableFrom(cls);
	}

	@Override
	public void validate(Object obj, Errors errors) {
		Offer offer = (Offer) obj;
		if(offer == null) errors.rejectValue("offer", "NOT_EXIST", "No object found");
		
		int skillId = offer.getSkillId();
		String email = offer.getEmail();
		Date startDate = offer.getStartDate();
		Date endDate = offer.getEndDate();
		String description = offer.getDescription();
		if(description != null && description.length() > 200) errors.rejectValue("description", "INVALID_DESCRIPTION", "Please insert a valid offer description (max 200 chars)");
		if(email == null || email.equals("")) errors.rejectValue("email", "NO_EMAIL", "Please insert the email");
		if(startDate == null || startDate.equals("")) errors.rejectValue("startDate", "NO_START_DATE", "Please insert the start date");
		if(endDate == null || endDate.equals("")) errors.rejectValue("endDate", "NO_END_DATE", "Please insert the end date");
		if( endDate.compareTo(startDate) < 0 ) errors.rejectValue("endDate", "INVALID_DATES", "End Date cannot be proir to Start Date");
		
		for(Offer r : offerList) {
			if( email.equals(r.getEmail()) && startDate.equals(r.getStartDate()) && endDate.equals(r.getEndDate()) && skillId == r.getSkillId() )  errors.rejectValue("email", "DUPLICATE", "This offer exists already");
		}
		
	}

}
