package akinator;

import akinator.graphic_design.Main_window;
import akinator.weightManagement.WeightManagement;

public class Main {
	public static void main(String[] args) {
		org.apache.log4j.Logger.getRootLogger().setLevel(org.apache.log4j.Level.OFF);//Avoid log4j warning

		Main_window window = new Main_window(); //Display the main window
		
		 window.translateAndShow(); //translate and show the first question in the window
		
		 WeightManagement wm = new WeightManagement();
		 System.out.println(wm.getMasterBranch_MaxWeight_Property());
		 System.out.println(wm.getMasterBranch_MaxWeight_Label());
		 System.out.println(wm.getMasterBranch_MaxWeight_Weight());
			
	}
}
