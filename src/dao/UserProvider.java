package dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.jasypt.util.password.BasicPasswordEncryptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import domain.UserDetails;

@Repository
public class UserProvider {
    private JdbcTemplate jdbcTemplate;

    @Autowired
    public void setDataSource(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    
    private static final class UserProviderMapper implements RowMapper<UserDetails> {
        public UserDetails mapRow(ResultSet rs, int rowNum) throws SQLException {
            UserDetails user = new UserDetails();
            user.setName(rs.getString("name"));
            user.setEmail(rs.getString("email"));
            user.setNif(rs.getString("nif"));
            user.setUsername(rs.getString("username"));
            user.setPw(rs.getString("pw"));
            user.setDegree(rs.getString("degree"));
            user.setCourse(rs.getInt("course"));
            user.setAdmin(rs.getString("is_admin"));
            return user;
        }
    }

    public List<UserDetails> getUsers() {
        return this.jdbcTemplate.query("SELECT * FROM student", new UserProviderMapper() );
    }

    public UserDetails getUser(String username, String pw) {
    	try {
    		UserDetails user = this.jdbcTemplate.queryForObject("SELECT * FROM student WHERE username = ?", new Object[] {username.toLowerCase()}, new UserProviderMapper());
    		BasicPasswordEncryptor pwEncryptor = new BasicPasswordEncryptor();
    		if(pwEncryptor.checkPassword(pw, user.getPw()))
    			return user;
    		return null;
    	} catch (EmptyResultDataAccessException ex) {
    		return null;
    	}
    }

//    public void addStudent( Student student) {
//        this.jdbcTemplate.update("INSERT INTO student (nif, username, pw, email, name, degree, course, is_admin) VALUES (?,?,?,?,?,?,?,?)",
//                student.getNif(), student.getUsername(), pwEncryptor.encryptPassword(student.getPw()), student.getEmail(), student.getName(), student.getDegree(), student.getCourse(), student.isAdmin() );
//    }

//    public void updateStudent(Student student) {
//        this.jdbcTemplate.update("UPDATE student SET name = ?, degree = ?, course = ? WHERE nif = ? ",
//                student.getName(), student.getDegree(), student.getCourse(), student.getNif() );
//    }
    
//    public void changePassword(Student student) {
//    	this.jdbcTemplate.update("UPDATE student SET pw = ? WHERE nif = ?", pwEncryptor.encryptPassword(student.getPw()) );
//    }

//    public void deleteStudent(Student student) {
//        this.jdbcTemplate.update("DELETE FROM student WHERE nif = ? ", student.getNif());
//    }

}
