package model;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;

/**
 * Created by Frost on 15/03/2017.
 */
public class Collaboration {
    protected int id;
    protected int skillId;
    protected int assessment;
    protected double totalHours;
    protected Date startDate;
    protected Date endDate;
    protected String evaluatorStudent;
    protected String evaluatedStudent;

    public Collaboration() {
        id = 0;
        skillId = 0;
        assessment = -1;
        totalHours = 0.0;
        evaluatorStudent = "";
        evaluatedStudent = "";
    }

    public Collaboration(int id, int skillId, int assessment, double totalHours, String startDate, String endDate, String evaluatorStudent, String evaluatedStudent) {
        this.id = id;
        this.skillId = skillId;
        this.assessment = assessment;
        this.totalHours = totalHours;
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
        this.evaluatorStudent = evaluatorStudent;
        this.evaluatedStudent = evaluatedStudent;
    }
    
    protected void setCollaboration(Collaboration collaboration) {
        this.id = collaboration.id;
        this.skillId = collaboration.skillId;
        this.assessment = collaboration.assessment;
        this.totalHours = collaboration.totalHours;
        this.startDate = collaboration.startDate;
        this.endDate = collaboration.endDate;
        this.evaluatorStudent = collaboration.evaluatorStudent;
        this.evaluatedStudent = collaboration.evaluatedStudent;    
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getAssessment() {
        return assessment;
    }

    public void setAssessment(int assessment) {
        this.assessment = assessment;
    }

    public double getTotalHours() {
        return totalHours;
    }

    public void setTotalHours(double totalHours) {
        this.totalHours = totalHours;
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

	public int getSkillId() {
		return skillId;
	}

	public void setSkillId(int skillId) {
		this.skillId = skillId;
	}

	public String getEvaluatorStudent() {
		return evaluatorStudent;
	}

	public void setEvaluatorStudent(String evaluatorStudent) {
		this.evaluatorStudent = evaluatorStudent;
	}

	public String getEvaluatedStudent() {
		return evaluatedStudent;
	}

	public void setEvaluatedStudent(String evaluatedStudent) {
		this.evaluatedStudent = evaluatedStudent;
	}
    
}
