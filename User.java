
public class User {
	public int userID;
	public String Name="";
	private String Password;
	public boolean activeUserFlag;
	
	public void setID(int id){
		this.userID=id;
	}
	public void setName(String name){
		this.Name=name;
	}
	public void setPassword(String password){
		this.Password=password;
	}
	public String getName(){
		return this.Name;
	}
	public String getPassword(){
		return this.Password;
	}
	public int getID(){
		return this.userID;
	}
	public void setActiveUserFlag(int value){
		if(value == 1 ) activeUserFlag=true;
		else activeUserFlag=false;
	}
	public boolean getActiveUserFlag(){
		return this.activeUserFlag;
	}
		
}
