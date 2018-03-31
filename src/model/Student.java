package model;

/**
 * Created by Frost on 15/03/2017.
 */
public class Student {
    private String nif;
    private String username;
    private String pw;
    private String pw2;
    private String email;
    private String name;
    private String degree;
    private int course;
    private String isAdmin = "f";

    public Student() {
    	this.degree ="";
    	this.course = 0;
        this.nif = "";
        this.username = "";
        this.pw = "";
        this.pw2 = "";
        this.email = "";
        this.name = "";
        this.isAdmin = "f";
    }

    public Student(String nif, String username, String pw, String pw2, String email, String name, String degree, int course, String isAdmin) {
        this.nif = nif;
        this.username = username;
        this.pw = pw;
        this.pw2 = pw2;
        this.email = email;
        this.name = name;
    	this.degree = degree;
    	this.course = course;    
    	this.isAdmin = isAdmin;
    }

	public String getNif() {
		return nif;
	}

	public void setNif(String nif) {
		this.nif = nif;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPw() {
		return pw;
	}

	public void setPw(String pw) {
		this.pw = pw;
	}

	public String getPw2() {
		return pw2;
	}

	public void setPw2(String pw2) {
		this.pw2 = pw2;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDegree() {
		return degree;
	}

	public void setDegree(String degree) {
		this.degree = degree;
	}

	public int getCourse() {
		return course;
	}

	public void setCourse(int course) {
		this.course = course;
	}
	
	public boolean isAdmin() {
		return this.isAdmin.equals("t");
	}

	public void setAdmin(String t) {
		this.isAdmin = t;
	}
}
