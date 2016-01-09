
public class Sheet {
	public int sheetID;
	public int ganreTableID;
	public String name;
	public String link;
	public String XML;//takes xml for typeXML in oracle as a string format ( for now);
	public String description;
	
	public void setSheetID(int id){
		this.sheetID=id;
	}
	public void setDescription(String description){
		this.description=description;
	}
	public void setName(String name){
		this.name=name;
	}
	public void setLink(String link){
		this.link=link;
	}
	public void setXML(String something){
		this.XML=something;//when in doubt make it a string 
	}
	public String getName(){
		return this.name;
	}
	public String getLink(){
		return this.link;
	}
	public String getXML(){
		return this.XML;
	}
	public int getSheetID(){
		return this.sheetID;
	}
	public String getDescription(){
		return this.description;
	}
}
