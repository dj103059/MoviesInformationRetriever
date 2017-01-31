package akinator;

import java.util.ArrayList;

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
		 
		 System.out.println("**********************Test to get the right property and value********************");
		 ArrayList<String> propertyAndValue = new ArrayList<String>();
		 propertyAndValue = wm.getPropertyAndValue();
		 System.out.println("the property is: "+propertyAndValue.get(0));
		 System.out.println("the value is: "+propertyAndValue.get(1));
		 System.out.println("**********************Test to set the weight to null********************");
		 System.out.println("Set the max weight of the MasterBranch to null");
		 wm.MasterBranch_SetNullWeight(wm.getMasterBranch_MaxWeight_Label());
		 System.out.println("**********************Test to get the right property and value********************");
		 System.out.println("The new property and value pair are: ");
		 ArrayList<String> propertyAndValue1 = new ArrayList<String>();
		 propertyAndValue1 = wm.getPropertyAndValue();
		 System.out.println("the property is: "+propertyAndValue1.get(0));
		 System.out.println("the value is: "+propertyAndValue1.get(1));
		 System.out.println("**********************Decrement the weight of the masterBranch********************");
		 System.out.println("Decrementation of the weight of the masterBranch...");
		 wm.MasterBranch_DecrementWeight(wm.getMasterBranch_MaxWeight_Label());
		 System.out.println("The new property and value pair are: ");
		 ArrayList<String> propertyAndValue11 = new ArrayList<String>();
		 propertyAndValue11 = wm.getPropertyAndValue();
		 System.out.println("the property is: "+propertyAndValue11.get(0));
		 System.out.println("the value is: "+propertyAndValue11.get(1));
		 System.out.println("the weight of MasterBranch is: "+wm.getMasterBranch_MaxWeight_Weight());
		 System.out.println("**********************Decrement the weight of the masterBranch********************");
		 System.out.println("Decrementation of the weight of the masterBranch...");
		 wm.MasterBranch_DecrementWeight(wm.getMasterBranch_MaxWeight_Label());
		 System.out.println("The new property and value pair are: ");
		 ArrayList<String> propertyAndValue111 = new ArrayList<String>();
		 propertyAndValue111 = wm.getPropertyAndValue();
		 System.out.println("the property is: "+propertyAndValue111.get(0));
		 System.out.println("the value is: "+propertyAndValue111.get(1));
		 System.out.println("the weight of MasterBranch is: "+wm.getMasterBranch_MaxWeight_Weight());
		 System.out.println("**********************Decrement the weight of the masterBranch********************");
		 System.out.println("Decrementation of the weight of the masterBranch...");
		 wm.MasterBranch_DecrementWeight(wm.getMasterBranch_MaxWeight_Label());
		 System.out.println("The new property and value pair are: ");
		 ArrayList<String> propertyAndValue1111 = new ArrayList<String>();
		 propertyAndValue1111 = wm.getPropertyAndValue();
		 System.out.println("the property is: "+propertyAndValue1111.get(0));
		 System.out.println("the value is: "+propertyAndValue1111.get(1));
		 System.out.println("the weight of MasterBranch is: "+wm.getMasterBranch_MaxWeight_Weight());

//		 System.out.println("**********************Test to set the weight to null********************");
//		 System.out.println("Set the max weight of the MasterBranch to null");
//		 System.out.println("**********************Test to get the right property and value********************");
//
//		 wm.MasterBranch_SetNullWeight(wm.getMasterBranch_MaxWeight_Label());
//		 System.out.println("The new property and value pair are: ");
//		 ArrayList<String> propertyAndValue11 = new ArrayList<String>();
//		 propertyAndValue11 = wm.getPropertyAndValue();
//		 System.out.println("the property is: "+propertyAndValue11.get(0));
//		 System.out.println("the value is: "+propertyAndValue11.get(1));
			
	}
}
