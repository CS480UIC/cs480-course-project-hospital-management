package user.domain;

public class Department {
	protected int dept_id;
	protected String name;
	
	public Department() {}
	
	public Department(int dept_id) {
		this.dept_id = dept_id;
	}
	public Department(int dept_id, String name) {
		this.dept_id = dept_id;
		this.name = name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	public String getName() {
		return this.name;
	}
	public void setDeptId(int id) {
		this.dept_id = id;
	}
	public int getDeptId() {
		return this.dept_id;
	}

}
