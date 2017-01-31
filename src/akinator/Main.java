package akinator;

import java.util.ArrayList;

import akinator.graphic_design.Main_window;
import akinator.init.StoredComponent;
import akinator.weightManagement.WeightManagement;

public class Main {
	public static void main(String[] args) {
		org.apache.log4j.Logger.getRootLogger().setLevel(org.apache.log4j.Level.OFF);//Avoid log4j warning

		Main_window window = new Main_window(); //Display the main window

		window.translateAndShow(); //translate and show the first question in the window

		WeightManagement wm = new WeightManagement();


		System.out.println("**********************Test to find the triplet of the masterBranch with the greater weight********************");
		//test of the masterBranch
		System.out.println("the property is: "+wm.getMasterBranch_MaxWeight_Property());
		System.out.println("the label is: "+wm.getMasterBranch_MaxWeight_Label());
		System.out.println("the weight is: "+wm.getMasterBranch_MaxWeight_Weight());
		System.out.println("**********************Test to find the triplet of the leaves associated to the precedent masterbranch with the greater weight********************");
		//test of the leaves
		System.out.println("the value is: "+wm.getLeaf_MaxWeight_Value(wm.getMasterBranch_MaxWeight_Label()));
		System.out.println("the label is: "+wm.getLeaf_MaxWeight_Label(wm.getMasterBranch_MaxWeight_Label()));
		System.out.println("the weight is: "+wm.getLeaf_MaxWeight_Weight(wm.getMasterBranch_MaxWeight_Label()));

		System.out.println("**********************Test to get the right property and value (in order to create Storedcomponent)********************");
		ArrayList<String> propertyAndValue = new ArrayList<String>();
		propertyAndValue = wm.getPropertyAndValue();
		System.out.println("the property is: "+propertyAndValue.get(0));
		System.out.println("the value is: "+propertyAndValue.get(1));
		System.out.println("**********************Test to set the weight of the MasterBranch to null********************");
		System.out.println("Set the max weight of the MasterBranch to null");
		wm.MasterBranch_SetNullWeight(wm.getMasterBranch_MaxWeight_Label());
		System.out.println("**********************Test to find the triplet of the masterBranch with the greater weight********************");
		//test of the masterBranch
		System.out.println("the property is: "+wm.getMasterBranch_MaxWeight_Property());
		System.out.println("the label is: "+wm.getMasterBranch_MaxWeight_Label());
		System.out.println("the weight is: "+wm.getMasterBranch_MaxWeight_Weight());
		System.out.println("**********************Test to find the triplet of the leaves associated to the precedent masterbranch with the greater weight********************");
		//test of the leaves
		System.out.println("the value is: "+wm.getLeaf_MaxWeight_Value(wm.getMasterBranch_MaxWeight_Label()));
		System.out.println("the label is: "+wm.getLeaf_MaxWeight_Label(wm.getMasterBranch_MaxWeight_Label()));
		System.out.println("the weight is: "+wm.getLeaf_MaxWeight_Weight(wm.getMasterBranch_MaxWeight_Label()));
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

		System.out.println("**********************Test to set the leaf to null********************");
		System.out.println("Set the max weight of the leaf to null");
		wm.Leaf_SetNullWeight(wm.getMasterBranch_MaxWeight_Label(), wm.getLeaf_MaxWeight_Label(wm.getMasterBranch_MaxWeight_Label()));
		System.out.println("**********************Test to get the right property and value********************");
		System.out.println("The new property and value pair are: ");
		ArrayList<String> propertyAndValue11111 = new ArrayList<String>();
		propertyAndValue11111 = wm.getPropertyAndValue();
		System.out.println("the property is: "+propertyAndValue11111.get(0));
		System.out.println("the value is: "+propertyAndValue11111.get(1));
		System.out.println("**********************Test to decrement the weight of the leaf********************");
		System.out.println("Decrementation of the weight of the leaf...");
		wm.Leaf_DecrementWeight(wm.getMasterBranch_MaxWeight_Label(), wm.getLeaf_MaxWeight_Label(wm.getMasterBranch_MaxWeight_Label()));
		System.out.println("The new property and value pair are: ");
		ArrayList<String> propertyAndValue111111 = new ArrayList<String>();
		propertyAndValue111111 = wm.getPropertyAndValue();
		System.out.println("the property is: "+propertyAndValue111111.get(0));
		System.out.println("the value is: "+propertyAndValue111111.get(1));
		System.out.println("the weight of leaf is: "+wm.getLeaf_MaxWeight_Weight(wm.getMasterBranch_MaxWeight_Label()));
		System.out.println("**********************Test to decrement the weight of the leaf********************");
		System.out.println("Decrementation of the weight of the leaf...");
		wm.Leaf_DecrementWeight(wm.getMasterBranch_MaxWeight_Label(), wm.getLeaf_MaxWeight_Label(wm.getMasterBranch_MaxWeight_Label()));
		System.out.println("The new property and value pair are: ");
		ArrayList<String> propertyAndValue1111111 = new ArrayList<String>();
		propertyAndValue1111111 = wm.getPropertyAndValue();
		System.out.println("the property is: "+propertyAndValue1111111.get(0));
		System.out.println("the value is: "+propertyAndValue1111111.get(1));
		System.out.println("the weight of leaf is: "+wm.getLeaf_MaxWeight_Weight(wm.getMasterBranch_MaxWeight_Label()));

//		ArrayList<String> propertyAndValue = new ArrayList<String>();
//		propertyAndValue = wm.getPropertyAndValue();
//		System.out.println("the property is: "+propertyAndValue.get(0));
//		System.out.println("the value is: "+propertyAndValue.get(1));
//		StoredComponent sc = new StoredComponent(propertyAndValue.get(0), propertyAndValue.get(1), "");
//		wm.Leaf_SetNullWeight(wm.getMasterBranch_MaxWeight_Label(), wm.getLeaf_MaxWeight_Label(wm.getMasterBranch_MaxWeight_Label()));//set the weight of the leaf to 0.
//		ArrayList<String> propertyAndValue1 = new ArrayList<String>();
//		propertyAndValue1 = wm.getPropertyAndValue();
//		System.out.println("the property is: "+propertyAndValue1.get(0));
//		System.out.println("the value is: "+propertyAndValue1.get(1));
//		sc.setProperty(propertyAndValue1.get(0));
//		sc.setValue(propertyAndValue1.get(1));
//		sc.setFormat("");
//		wm.Leaf_SetNullWeight(wm.getMasterBranch_MaxWeight_Label(), wm.getLeaf_MaxWeight_Label(wm.getMasterBranch_MaxWeight_Label()));//set the weight of the leaf to 0.
//		ArrayList<String> propertyAndValue11 = new ArrayList<String>();
//		propertyAndValue11 = wm.getPropertyAndValue();
//		System.out.println("the property is: "+propertyAndValue11.get(0));
//		System.out.println("the value is: "+propertyAndValue11.get(1));
//		sc.setProperty(propertyAndValue11.get(0));
//		sc.setValue(propertyAndValue11.get(1));
//		sc.setFormat("");
		
		



	}
}
