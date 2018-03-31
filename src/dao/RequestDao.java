package dao;

import model.Request;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import domain.UserDetails;

import javax.sql.DataSource;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by Frost on 15/03/2017.
 */

@Repository
public class RequestDao {

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public void setDataSource(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    private static final class RequestMapper implements RowMapper<Request> {
        public Request mapRow(ResultSet rs, int rowNum) throws SQLException {
            Request request = new Request();
            request.setId(rs.getInt("request_id"));
            request.setStartDate(rs.getDate("start_date").toString());
            request.setEndDate(rs.getDate("end_date").toString());
            request.setDescription(rs.getString("description"));
            request.setSkillId(rs.getInt("skill_id"));
            request.setEmail(rs.getString("student_email"));
            return request;
        }
    }

    public List<Request> getRequests() {
        return this.jdbcTemplate.query("SELECT * FROM request", new RequestMapper() );
    }

    public List<Request> getUserRequests(UserDetails user) {
		return this.jdbcTemplate.query("SELECT * FROM request WHERE student_email = ?", new Object[] {user.getEmail().toLowerCase()}, new RequestMapper() );
    }

    public List<Request> getRequestsBySkillIdAndDate(int skillId, Date startDate, Date endDate) {
    	return this.jdbcTemplate.query("SELECT * FROM request WHERE skill_id = ? AND start_date >= ? AND end_date <= ?", new Object[] {skillId, startDate, endDate}, new RequestMapper() );
    }
    
    public Request getRequest(int id) {
        return this.jdbcTemplate.queryForObject("SELECT * FROM request WHERE request_id = ?", new Object[] {id}, new RequestMapper());
    }

    public void addRequest( Request request ) {
        this.jdbcTemplate.update("INSERT INTO request (start_date, end_date, student_email, skill_id, description) VALUES (?,?,?,?,?)",
                request.getStartDate(), request.getEndDate(), request.getEmail(), request.getSkillId(), request.getDescription() );
    }

    public void updateRequest(Request request) {
        this.jdbcTemplate.update("UPDATE request SET start_date = ?, end_date = ?, student_email = ?, skill_id = ?, description = ? WHERE request_id = ?",
                request.getStartDate(), request.getEndDate(), request.getEmail(), request.getSkillId(), request.getDescription(), request.getId());
    }

    public void deleteRequest(Request request) {
        this.jdbcTemplate.update("DELETE FROM request WHERE request_id = ?", request.getId());
    }

}
