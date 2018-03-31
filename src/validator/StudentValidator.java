package validator;

import java.util.List;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import dao.StudentDao;
import domain.UserDetails;
import model.Student;

public class StudentValidator implements Validator {

	private List<Student> studentList;
	public StudentValidator(StudentDao studentDao) {
		studentList = studentDao.getStudents();
	}
	
	@Override
	public boolean supports(Class<?> cls) {
		return UserDetails.class.isAssignableFrom(cls);
	}

	@Override
	public void validate(Object obj, Errors errors) {
		Student student = (Student) obj;
		
		if(student == null) errors.rejectValue("student", "NOT_EXIST", "No object found");
		
		int course = student.getCourse();
		String nif = student.getNif();
		String name = student.getName();
		String email = student.getEmail();
		String username = student.getUsername();
		String degree = student.getDegree();
		String pw = student.getPw();
		String pw2 = student.getPw2();
		
		if(name == null || name.equals("") || name.length() > 100) errors.rejectValue("name", "NO_NAME", "Please insert the student's name");
		if(nif == null || nif.equals("")) errors.rejectValue("nif", "NO_NIF", "Please insert the student's NIF");
		if(nif.contains(" ") || nif.length() > 9) errors.rejectValue("nif", "NO_NIF", "Please insert a valid NIF");
		if(email == null || email.equals("")) errors.rejectValue("email", "NO_EMAIL", "Please insert the student's email");
		if(email.contains(" ")) errors.rejectValue("email", "NO_EMAIL", "Please insert a valid email");
		if(username == null || username.equals("")) errors.rejectValue("username", "NO_USERNAME", "Please insert the student's username");
		if(username.contains(" ") || username.length() > 20) errors.rejectValue("username", "NO_USERNAME", "Please insert a valid username");
		if(degree == null || degree.equals("") || degree.length() > 100) errors.rejectValue("degree", "NO_DEGREE", "Please insert the student's degree");
		if(pw == null || pw.equals("")) errors.rejectValue("pw", "NO_PW", "Please insert the student's password");
		if(pw.contains(" ")) errors.rejectValue("pw", "NO_PW", "Please insert a valid password");
		if(pw.length() < 6 || pw.length() > 20) errors.rejectValue("pw", "NO_PW", "Please insert a valid password");
		if(pw2 == null || pw2.equals("")) errors.rejectValue("pw2", "NO_PW2", "Please insert the student's confirmation password");
		if(course < 1|| course > 6) errors.rejectValue("course", "INVALID_COURSE", "Please insert the student's course (from 1 to 6");
		if( !email.contains("al")) errors.rejectValue("email", "INVALID_EMAIL", "Please insert a valid email (alxXxXxX@uji.es)");
		if( !email.contains("@uji.es")) errors.rejectValue("email", "INVALID_EMAIL", "Please insert a valid email (alxXxXxX@uji.es)");
		if( email.length() != 15) errors.rejectValue("email", "INVALID_EMAIL", "Please insert a valid email (alxXxXxX@uji.es)");
		if( !pw.equals(pw2) ) errors.rejectValue("pw2", "INVALID_CONFIRM_PW", "Please insert the same password");
		
		if(studentList != null) 
			for(Student s : studentList) {
				if( nif.toUpperCase().equals(s.getNif().toUpperCase()) ) errors.rejectValue("nif", "DUPLICATE_NIF", "This NIF already exists in our database");
				if( email.toLowerCase().equals(s.getEmail().toLowerCase()) ) errors.rejectValue("email", "DUPLICATE_EMAIL", "This email already exists in our database");
				if( username.toLowerCase().equals(s.getUsername().toLowerCase()) ) errors.rejectValue("username", "DUPLICATE_USERNAME", "This username already exists in our database");
			}
	}

}
