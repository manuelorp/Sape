package dao;

import model.Offer;

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
public class OfferDao {

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public void setDataSource(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    private static final class OfferMapper implements RowMapper<Offer> {
        public Offer mapRow(ResultSet rs, int rowNum) throws SQLException {
            Offer offer= new Offer();
            offer.setId(rs.getInt("offer_id"));
            offer.setSkillId(rs.getInt("skill_id"));
            offer.setStartDate(rs.getDate("start_date").toString());
            offer.setEndDate(rs.getDate("end_date").toString());
            offer.setDescription(rs.getString("description"));
            offer.setEmail(rs.getString("student_email"));
            return offer;
        }
    }

    public List<Offer> getOffers() {
        return this.jdbcTemplate.query("SELECT * FROM offer", new OfferMapper() );
    }
    
    public List<Offer> getUserOffers(UserDetails user) {
    	return this.jdbcTemplate.query("SELECT * FROM offer WHERE student_email = ?", new Object[] {user.getEmail().toLowerCase()}, new OfferMapper() );
    }

    public List<Offer> getOffersBySkillIdAndDate(int skillId, Date startDate, Date endDate) {
    	return this.jdbcTemplate.query("SELECT * FROM offer WHERE skill_id = ? AND start_date >= ? AND end_date <= ?", new Object[] {skillId, startDate, endDate}, new OfferMapper() );
    }

    public Offer getOffer(int id) {
        return this.jdbcTemplate.queryForObject("SELECT * FROM offer WHERE offer_id = ?", new Object[] {id}, new OfferMapper());
    }

    public void addOffer( Offer offer ) {
        this.jdbcTemplate.update("INSERT INTO offer (start_date, end_date, student_email, skill_id, description) VALUES (?,?,?,?,?)",
               offer.getStartDate(), offer.getEndDate(), offer.getEmail(), offer.getSkillId(), offer.getDescription() );
    }

    public void updateOffer(Offer offer) {
        this.jdbcTemplate.update("UPDATE offer SET start_date = ?, end_date = ?, student_email = ?, skill_id = ?, description = ? WHERE offer_id = ?",
                offer.getStartDate(), offer.getEndDate(), offer.getEmail(), offer.getSkillId(), offer.getDescription(), offer.getId());
    }

    public void deleteOffer(Offer offer) {
        this.jdbcTemplate.update("DELETE FROM offer WHERE offer_id = ?", offer.getId());
    }

}
