package model;

/**
 * Created by Frost on 15/03/2017.
 */
public class Skill {
	private int id;
    private String name;
    private String lvl;
    private String status;
    private String kind;
    private String description;

    public Skill() {
    	this.id = 0;
        this.name = "";
        this.lvl = "";
        this.status = "";
        this.kind = "";
        this.description = "";
    }

    public Skill(int id, String name, String lvl, String status, String kind, String description) {
    	this.id = id;
    	this.name = name;
        this.lvl = lvl;
        this.status = status;
        this.kind = kind;
        this.description = description;
    }
    
    public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLvl() {
        return lvl;
    }

    public void setLvl(String lvl) {
        this.lvl = lvl;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getKind() {
        return kind;
    }

    public void setKind(String kind) {
        this.kind = kind;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
