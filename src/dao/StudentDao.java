package dao;

import model.Student;

import org.jasypt.util.password.BasicPasswordEncryptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by Frost on 15/03/2017.
 */
@Repository
public class StudentDao {

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public void setDataSource(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

	BasicPasswordEncryptor pwEncryptor = new BasicPasswordEncryptor();
    
    private static final class StudentMapper implements RowMapper<Student> {
        public Student mapRow(ResultSet rs, int rowNum) throws SQLException {
            Student student = new Student();
            student.setName(rs.getString("name"));
            student.setEmail(rs.getString("email"));
            student.setNif(rs.getString("nif"));
            student.setUsername(rs.getString("username"));
            student.setPw(rs.getString("pw"));
            student.setDegree(rs.getString("degree"));
            student.setCourse(rs.getInt("course"));
            student.setAdmin(rs.getString("is_admin"));
            return student;
        }
    }

    public List<Student> getStudents() {
        return this.jdbcTemplate.query("SELECT * FROM student", new StudentMapper() );
    }

    public Student getStudent(String nif) {
        return this.jdbcTemplate.queryForObject("SELECT * FROM student WHERE nif = ? ", new Object[] {nif.toUpperCase()}, new StudentMapper());
    }

    public Student getStudentByEmail(String email) {
    	return this.jdbcTemplate.queryForObject("SELECT * FROM student WHERE email = ?", new Object[] {email.toLowerCase()}, new StudentMapper());
    }
    
    public void addStudent( Student student) {
        this.jdbcTemplate.update("INSERT INTO student (nif, username, pw, email, name, degree, course, is_admin) VALUES (?,?,?,?,?,?,?,?)",
                student.getNif().toUpperCase(), student.getUsername().toLowerCase(), pwEncryptor.encryptPassword(student.getPw()), student.getEmail().toLowerCase(), student.getName(), student.getDegree(), student.getCourse(), student.isAdmin() );
    }

    public void updateStudent(Student student) {
        this.jdbcTemplate.update("UPDATE student SET name = ?, email = ?,  degree = ?, course = ?, pw = ? WHERE nif = ? ",
                student.getName(), student.getEmail().toLowerCase(), student.getDegree(), student.getCourse(), student.getPw(), student.getNif().toUpperCase() );
    }

    public void deleteStudent(Student student) {
        this.jdbcTemplate.update("DELETE FROM student WHERE nif = ? ", student.getNif().toUpperCase());
    }
   
}
