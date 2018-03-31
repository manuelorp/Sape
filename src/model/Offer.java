package model;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;

/**
 * Created by Frost on 15/03/2017.
 */
public class Offer {
    private int id;
    private int skillId;
    private Date startDate;
    private Date endDate;
    private String description;
    private String email;

    public Offer() {
        this.id = 0;
        this.skillId = 0;
        this.description = "";
        this.email = "";
    }

    public Offer(int id, int skillId, String startDate, String endDate, String email, String description) {
        this.id = id;
        this.skillId = skillId;
        try {
			this.startDate = new Date(new SimpleDateFormat("yyyy-MM-dd").parse(startDate).getTime());
		} catch (ParseException e) {
			e.printStackTrace();
		}
        try {
			this.endDate = new Date(new SimpleDateFormat("yyyy-MM-dd").parse(endDate).getTime());
		} catch (ParseException e) {
			e.printStackTrace();
		}        
        this.description = description;
        this.email = email;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        try {
			this.startDate = new Date(new SimpleDateFormat("yyyy-MM-dd").parse(startDate).getTime());
		} catch (ParseException e) {
			e.printStackTrace();
		}
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        try {
			this.endDate = new Date(new SimpleDateFormat("yyyy-MM-dd").parse(endDate).getTime());
		} catch (ParseException e) {
			e.printStackTrace();
		}        
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

	public int getSkillId() {
		return skillId;
	}

	public void setSkillId(int skillId) {
		this.skillId = skillId;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
    public String toString() {
    	return id + " - " + startDate.toString() + " - " + endDate.toString() + " - " + email + " - " + skillId + " - " + description + "\n";
    }   
}
