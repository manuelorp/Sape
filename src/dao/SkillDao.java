package dao;

import model.Skill;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by Frost on 15/03/2017.
 */

public class SkillDao {

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public void setDataSource(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    private static final class SkillMapper implements RowMapper<Skill> {
        public Skill mapRow(ResultSet rs, int rowNum) throws SQLException {
            Skill skill = new Skill();
            skill.setId(rs.getInt("skill_id"));
            skill.setName(rs.getString("name"));
            skill.setLvl(rs.getString("lvl"));
            skill.setStatus(rs.getString("status"));
            skill.setKind(rs.getString("kind"));
            skill.setDescription(rs.getString("description"));
            return skill;
        }
    }

    public List<Skill> getSkills() {
        return this.jdbcTemplate.query("SELECT * FROM skill", new SkillMapper() );
    }

    public Skill getSkill(int id) {
        return this.jdbcTemplate.queryForObject("SELECT * FROM skill WHERE skill_id = ?", new Object[] {id}, new SkillMapper());
    }

    public void addSkill( Skill skill) {
        this.jdbcTemplate.update("INSERT INTO skill (name, lvl, status, kind, description) VALUES (?,?,?,?,?)",
                skill.getName(), skill.getLvl(), skill.getStatus(), skill.getKind(), skill.getDescription() );
    }

    public void updateSkill(Skill skill) {
        this.jdbcTemplate.update("UPDATE skill SET name = ?, lvl = ?, status = ?, kind = ?, description = ? WHERE skill_id = ?",
        		skill.getName(), skill.getLvl(), skill.getStatus(), skill.getKind(), skill.getDescription(), skill.getId() ) ;
        System.out.println("----- Actualizando skill - DAO -----");
        System.out.println("Skill ID: " + skill.getId() + " - Name: " + skill.getName() + " - Lvl: " + skill.getLvl() + " - Status: " + skill.getStatus() + " - Kind: " + skill.getKind() + " - Description: " + skill.getDescription());
        System.out.println("-----------------------");
    }

    public void deleteSkill(Skill skill) {
        this.jdbcTemplate.update("DELETE FROM skill WHERE skill_id = ?", skill.getId());
    }

}
