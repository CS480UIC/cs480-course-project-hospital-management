package user.domain;

public class Physician {
	protected int physician_id;
	protected String first;
	protected String last;
	protected String position;
	protected int dept_id;
	
	
	public Physician() {}
	
	public Physician(int id) {
		physician_id = id;
	}
	
	public Physician(int id, String first, String last, String position, int dept_id) {
		physician_id  = id;
		this.first  = first;
		this.last = last;
		this.position = position;
		this.dept_id  = dept_id;
	}
	public Physician(String first, String last, String position, int dept_id) {
		this.first  = first;
		this.last = last;
		this.position = position;
		this.dept_id  = dept_id;
	}
	
	public int getId() {
        return physician_id;
    }
    public void setId(int id) {
    	physician_id = id;
    }
    public String getFirst() {
        return this.first;
    }
    public void setFirst(String first) {
        this.first  = first;
    }
    
    public String getLast() {
        return this.last;
    }
    public void setLast(String last) {
        this.last  = last;
    }
	
    public String getPosition() {
        return this.position;
    }
    public void setPosition(String position) {
        this.position  = position;
    }
    public int getDeptId() {
        return dept_id;
    }
    public void setDeptId(int d_id) {
    	dept_id = d_id;
    }
	

}
