package Practice.testng;

import java.util.Date;

public class CaptureTimeStamp {
	public static void main(String[]args) {

	String time =new Date().toString().replace(" ", "_").replace(":", "_");
	System.out.println(time);
		// TODO Auto-generated method stub

	}
}
