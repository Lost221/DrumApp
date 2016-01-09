//DONT FORGET TO REVISE THE INSERT SHEET AFTER YOU GET YOUR FILE TRANSFER LOGI UP AND GOING !


import java.sql.*;
import oracle.jdbc.*;
import java.util.ArrayList;
import java.util.Scanner;

import javax.xml.*;
import java.io.*;
import org.w3c.dom.*;
import org.xml.sax.*;


public class dbConnector {
	private String url="jdbc:oracle:thin:@localhost:1521:xe";
	private String driver="oracle.jdbc.driver.OracleDriver";
	private String userName="sheetapp";
	private String password="lost221";
	private Connection con=null;
	private PreparedStatement st = null;
	public void makeConnection(){
		try {
			Class.forName(driver).newInstance();
			this.con=DriverManager.getConnection(url,userName,password);
			System.out.println("Connection established!");
		} catch (Exception e) {
			e.printStackTrace();
		}
	
	
	}
	public User getUser(String username,String password) throws SQLException{
		User user=new User();
		
		String querry = "SELECT DISTINCT * FROM accounts where userName=? AND userPassword=?";
		try{
			st = con.prepareStatement(querry);
			st.setString(2,password);
			st.setString(1,username);
			ResultSet rs= st.executeQuery();
			while(rs.next()){
				user.setID(rs.getInt("userID"));
				user.setName(rs.getString("userName"));
				user.setPassword(rs.getString("userPassword"));
				user.setActiveUserFlag(rs.getInt("activeFlag"));
				}
		}catch (SQLException e){
			System.out.print("FAIL!");
			e.printStackTrace();
		}finally{
			if(st!=null){st.close();}
			}
		return user;
		}
	public void createUser(String username,String password)throws SQLException{
		String querry="INSERT INTO accounts (userName,userPassword,activeFlag) VALUES(?,?,1)";
		
			st=con.prepareStatement(querry);
			st.setString(1,username);
			st.setString(2, password);
			st.executeUpdate();
			st.close();
	}
	public void deleteUser(String username)throws SQLException{
		String querry="DELETE FROM accounts WHERE userName=?";
		st=con.prepareStatement(querry);
		st.setString(1, username);
		st.executeUpdate();
		st.close();
		
	}
	public void insertGanre(String ganre)throws SQLException{
		String querry="INSERT INTO ganres(ganreName) VALUES (?);";
		st=con.prepareStatement(querry);
		st.setString(1, ganre);
		st.executeUpdate();
		st.close();
	}
		
	public void deleteGanre(String ganre)throws SQLException{
		String querry="DELETE FROM ganres WHERE ganreName=?";
		st=con.prepareStatement(querry);
		st.setString(1, ganre);
		st.executeUpdate();
		st.close();
	}
	public ArrayList<String> getGanres(String ganre)throws SQLException{
		String querry="SELECT * FROM ganres WHERE ganreName=?";
		ArrayList <String>ganreList = new ArrayList();
		st=con.prepareStatement(querry);
		st.setString(1, ganre);
		ResultSet rs= st.executeQuery();
		while(rs.next()){
			ganreList.add(rs.getString("ganreName"));
		}
		st.close();
		return ganreList;
	}
	//Following function is to be revised after file transfer logic is implemented. MUST PLACE LINK!
	//Must transform link from string to xml file ( either you marshall it or some magical BS) ( you do that
	//in queri INSERT BLOCK , then you have to use the Solutions.text to escape the ampersand char ) 
	public void uploadSheet(String sheetName,int userID,int ganreID,String file,String description) throws SQLException, FileNotFoundException{
		String querry="INSERT INTO sheets(userID,ganreID,sheetName,sheet,description) VALUES(?,?,?,?,?)";
		 String theData="";
		Scanner scan = new Scanner (new File("file"));
		while (scan.hasNext()) {
		    theData = scan.nextLine();
		}
		scan.close();
		theData.replace("&", "&amp;");
		SQLXML sheet = con.createSQLXML();
		sheet.setString(theData);
		st=con.prepareStatement(querry);
		st.setInt(1,userID);
		st.setInt(2,ganreID);
		st.setString(3, sheetName);
		st.setSQLXML(4,sheet);
		st.setString(5, description);
		st.executeUpdate();
		st.close();
	}
	public void deleteSheet(String sheetName)throws SQLException{
		String querry="DELETE FROM .sheets WHERE sheetName=?";
		st=con.prepareStatement(querry);
		st.setString(1, sheetName);
		st.executeUpdate();
		st.close();
		
	}
	/*public Sheet getsheet(String sheetName)throws SQLException{
		String querry="SELECT * FROM SHEETS WHERE sheetName=?"
	}*/
}
