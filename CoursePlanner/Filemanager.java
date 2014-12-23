package cp5.model;

public class Filemanager {


	public String ccode;

	public String ctitle;

	public String cprerec;

	String uname;

	String password;

	String fname;

	String lname;

	public Filemanager()
	{}



	public Filemanager (String ccode,String ctitle,String cprerec)
	{

		this.ccode=ccode;
		this.ctitle=ctitle;
		this.cprerec=cprerec;
	}

	public Filemanager(String uname,String password)
	{
		this.uname = uname;
		this.password = password;
	}

	public Filemanager(String uname,String password,String fname,String lname)
	{
		this.uname = uname;
		this.password = password;
		this.fname=fname;
		this.lname=lname;
	}

	public Filemanager(String ccode)
	{
		this.ccode=ccode;
	}

	public String getUname() {
		return uname;
	}



	public void setUname(String uname) {
		this.uname = uname;
	}



	public String getPassword() {
		return password;
	}



	public void setPassword(String password) {
		this.password = password;
	}







	public String getCcode() {
		return ccode;
	}



	public void setCcode(String ccode) {
		this.ccode = ccode;
	}



	public String getCtitle() {
		return ctitle;
	}



	public void setCtitle(String ctitle) {
		this.ctitle = ctitle;
	}



	public String getCprerec() {
		return cprerec;
	}



	public void setCprerec(String cprerec) {
		this.cprerec = cprerec;
	}



	public String getFname() {
		return fname;
	}



	public void setFname(String fname) {
		this.fname = fname;
	}



	public String getLname() {
		return lname;
	}



	public void setLname(String lname) {
		this.lname = lname;
	}



}

