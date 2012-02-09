package JSON_tests;

import net.sf.json.JSONObject;

public class FirstJSONJava {


	public static void main(String args[]){
		JSONObject object=new JSONObject();
		object.put("name","Amit Kumar");
		object.put("Max.Marks",new Integer(100));
		object.put("Min.Marks",new Double(40));
		object.put("Scored",new Double(66.67));
		object.put("nickname","Amit");
		System.out.println(object);
	}
}
