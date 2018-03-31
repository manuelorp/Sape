package dao;

import model.Collaboration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import domain.UserDetails;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;


/**
 * Created by Frost on 15/03/2017.
 */

@Repository
public class CollaborationDao {

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public void setDataSource(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    private static final class CollaborationMapper implements RowMapper<Collaboration> {
        public Collaboration mapRow(ResultSet rs, int rowNum) throws SQLException {
            Collaboration collaboration = new Collaboration();
            collaboration.setId(rs.getInt("collaboration_id"));
            collaboration.setSkillId(rs.getInt("skill_id"));
            collaboration.setAssessment(rs.getInt("assessment"));
            collaboration.setStartDate(rs.getDate("start_date").toString());
            collaboration.setEndDate(rs.getDate("end_date").toString());
            collaboration.setTotalHours(rs.getDouble("total_hours"));
            collaboration.setEvaluatedStudent(rs.getString("evaluated_email"));
            collaboration.setEvaluatorStudent(rs.getString("evaluator_email"));
            return collaboration;
        }
    }

    public List<Collaboration> getCollaborations() {
        return this.jdbcTemplate.query("SELECT * FROM collaboration", new CollaborationMapper() );
    }

    public List<Collaboration> getUserCollaborations(UserDetails user) {
    	return this.jdbcTemplate.query("SELECT * FROM collaboration WHERE evaluator_email LIKE ? OR evaluated_email LIKE ?", new Object[] {user.getEmail().toLowerCase(), user.getEmail().toLowerCase()}, new CollaborationMapper() );
    }

    
    public Collaboration getCollaboration(int id) {
        return this.jdbcTemplate.queryForObject("SELECT * FROM collaboration WHERE collaboration_id = ?", new Object[] {id}, new CollaborationMapper());
    }

    public void addCollaboration( Collaboration collaboration ) {
        this.jdbcTemplate.update("INSERT INTO collaboration (start_date, end_date, total_hours, assessment, evaluator_email, evaluated_email, skill_id) VALUES (?,?,?,?,?,?,?)",
                collaboration.getStartDate(), collaboration.getEndDate(), collaboration.getTotalHours(), collaboration.getAssessment(), collaboration.getEvaluatorStudent().toLowerCase(), collaboration.getEvaluatedStudent().toLowerCase(), collaboration.getSkillId());
    }

    public void updateCollaboration(Collaboration collaboration) {
        this.jdbcTemplate.update("UPDATE collaboration SET start_date = ?, end_date = ?, total_hours = ?, assessment = ?, evaluator_email = ?, evaluated_email= ?, skill_id = ? WHERE collaboration_id = ?",
                collaboration.getStartDate(), collaboration.getEndDate(), collaboration.getTotalHours(), collaboration.getAssessment(), collaboration.getEvaluatorStudent(), collaboration.getEvaluatedStudent(), collaboration.getSkillId(), collaboration.getId());
    }

    public void deleteCollaboration(Collaboration collaboration) {
        this.jdbcTemplate.update("DELETE FROM collaboration WHERE collaboration_id = ?", collaboration.getId());
    }

}
