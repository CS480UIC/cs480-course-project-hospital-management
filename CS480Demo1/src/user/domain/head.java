package user.domain;

public class head {
	protected String deptName;
	protected String physicianFirst;
	protected String physicianLast;
	
	public head() {}
	
	public head(String deptName, String first, String last) {
		this.deptName = deptName;
		this.physicianFirst = first;
		this.physicianLast = last;
	}
	
	public void setDeptName(String name) {
		this.deptName = name;
	}
	public String getDeptName() {
		return this.deptName;
	}
	public void setPhysicianFirst(String name) {
		this.physicianFirst = name;
	}
	public String getPhysicianFirst() {
		return this.physicianFirst;
	}
	public void setPhysicianLast(String name) {
		this.physicianLast = name;
	}
	public String getPhysicianLast() {
		return this.physicianLast;
	}

}