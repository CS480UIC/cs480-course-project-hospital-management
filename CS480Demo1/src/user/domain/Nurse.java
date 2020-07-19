package user.domain;

public class Nurse {
	protected int nurse_id;
	protected String first;
	protected String last;
	protected String position;
	
	
	public Nurse() {}
	
	public Nurse(int id) {
		nurse_id = id;
	}
	
	public Nurse(int id, String first, String last, String position) {
		nurse_id  = id;
		this.first  = first;
		this.last = last;
		this.position = position;
	}
	public Nurse(String first, String last, String position) {
		
		this.first  = first;
		this.last = last;
		this.position = position;
	}
	
	public int getId() {
        return nurse_id;
    }
    public void setId(int id) {
    	nurse_id = id;
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
    

}
