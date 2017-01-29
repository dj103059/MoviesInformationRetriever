package akinator;

import akinator.graphic_design.Main_window;
import akinator.weightManagement.WeightManagement;

public class Main {
	public static void main(String[] args) {
		org.apache.log4j.Logger.getRootLogger().setLevel(org.apache.log4j.Level.OFF);//Avoid log4j warning

		Main_window window = new Main_window(); //Display the main window
		
		 window.translateAndShow(); //translate and show the first question in the window
		
		 WeightManagement wm = new WeightManagement();
		 
		 //test of the masterBranch
		 System.out.println("**********************Test of the MasterBranch********************");
		 System.out.println("the property is: "+wm.getMasterBranch_MaxWeight_Property());
		 System.out.println("the label is: "+wm.getMasterBranch_MaxWeight_Label());
		 System.out.println("the weight is: "+wm.getMasterBranch_MaxWeight_Weight());
		 System.out.println("**********************Test of the leaves********************");
		 //test of the leaves
		 System.out.println("the value is: "+wm.getLeaf_MaxWeight_Value(wm.getMasterBranch_MaxWeight_Label()));
		 System.out.println("the label is: "+wm.getLeaf_MaxWeight_Label(wm.getMasterBranch_MaxWeight_Label()));
		 System.out.println("the weight is: "+wm.getLeaf_MaxWeight_Weight(wm.getMasterBranch_MaxWeight_Label()));
			
	}
}
