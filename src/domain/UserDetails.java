package domain;

public class UserDetails {
    private String nif;
    private String username;
    private String pw;
    private String email;
    private String name;
    private String degree;
    private int course;
    private String isAdmin;
	
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

	public String getNif() {
		return nif;
	}

	public void setNif(String nif) {
		this.nif = nif;
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
		return isAdmin.equals("t");
	}

	public void setAdmin(String isAdmin) {
		this.isAdmin = isAdmin;
	}
	
}
