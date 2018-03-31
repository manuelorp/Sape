package controller;

import dao.StudentDao;
import domain.UserDetails;
import model.Student;
import validator.StudentValidator;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

/**
 * Created by Frost on 29/03/2017.
 */
@Controller
@RequestMapping(value="/student")
public class StudentController {

    private StudentDao studentDao;

    @Autowired
    public void setStudentDao( StudentDao studentDao) {
        this.studentDao = studentDao;
    }

    @RequestMapping(value="/index")
    public String indexStudents(Model model) {
        return "student/index";
    }

    
    @RequestMapping(value="/list")
    public String listStudents(Model model) {
        model.addAttribute("students", studentDao.getStudents());
        return "student/list";
    }

    @RequestMapping(value="/add")
    public String addStudent(Model model ) {
        model.addAttribute( "student", new Student() );
        model.addAttribute("action", "student/add.html");
        return "student/add";
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public String processAddSubmit(@ModelAttribute("student") Student student, BindingResult bindingResult, HttpSession session) {
        StudentValidator sv = new StudentValidator(studentDao);
        sv.validate(student, bindingResult);
    	if(bindingResult.hasErrors()) return "student/add";
        studentDao.addStudent(student);
        UserDetails ud = (UserDetails) session.getAttribute("user");
        if( ud != null && ud.isAdmin()) return "redirect:list.html";
        return "redirect:index.html";
    }

   @RequestMapping(value = "/update/{nif}", method = RequestMethod.GET)
   public String editStudent(Model model, @PathVariable String nif, HttpSession session) {
        model.addAttribute("student", studentDao.getStudent(nif));
        model.addAttribute("action", "student/update.html");
        return "student/update";
   }

   @RequestMapping(value = "/update", method = RequestMethod.POST)
   public String processUpdateSubmit(@ModelAttribute("student") Student student,
                                     BindingResult bindingResult, HttpSession session ) {
        if(bindingResult.hasErrors()) return "student/update";
        studentDao.updateStudent(student);
        return "redirect:../student/list.html";
   } 

   @RequestMapping(value = "/delete/{nif}")
    public String processDelete(@PathVariable String nif) {
        studentDao.deleteStudent(studentDao.getStudent(nif));
        return "redirect:../list.html";
   }
}
