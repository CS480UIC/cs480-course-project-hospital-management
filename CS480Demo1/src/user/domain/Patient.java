package user.domain;

public class Patient {
	protected int patient_id;
	protected String first;
	protected String last;
	protected int age;
	protected String gender;
	protected String address;
	protected int phone;
	
	/* Constructor */
	public Patient() {}
	
	public Patient(int id) {
		patient_id = id;
	}
	
	public Patient(int id, String first, String last, int age, String gender, String address, int phone) {
		patient_id = id;
		this.first = first;
		this.address = address;
		this.last = last;
		this.age = age;
		this.gender = gender;
		this.phone = phone;
	}
	public Patient(String first, String last, int age, String gender, String address, int phone) {
		this.first = first;
		this.address = address;
		this.last = last;
		this.age = age;
		this.gender = gender;
		this.phone = phone;
		
	}
	
	public int getId() {
        return patient_id;
    }
    public void setId(int id) {
        patient_id = id;
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
 
    public int getAge() {
        return age;
    }
 
    public void setAge(int age) {
        this.age = age;
    }
  
    public int getPhone() {
    	return phone;
    }
    public void setPhone(int phone) {
        this.phone = phone;
    }
    
    public String getGender() {
    	return gender;
    }
    public void setGender(String gender) {
    	this.gender = gender;
    }
    
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	

}
