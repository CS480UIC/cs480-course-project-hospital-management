package user.domain;

public class Physician {
	protected int physician_id;
	protected String first;
	protected String last;
	protected String position;
	protected int ssn;
	
	
	public Physician() {}
	
	public Physician(int id) {
		physician_id = id;
	}
	
	public Physician(int id, String first, String last, String position, int ssn) {
		physician_id  = id;
		this.first  = first;
		this.last = last;
		this.position = position;
		this.ssn  = ssn;
	}
	public Physician(String first, String last, String position, int ssn) {
		this.first  = first;
		this.last = last;
		this.position = position;
		this.ssn  = ssn;
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
    public int getSsn() {
        return ssn;
    }
    public void setSsn(int ssn) {
    	this.ssn = ssn;
    }
	

}
