package com.expendeble.option2.model;

import com.expendeble.option2.model.Child;

import java.util.ArrayList;

public class Group {

	private String Name;
	private ArrayList<Child> Items;




	public String getName() {
		return Name;
	}

	public void setName(String name) {
		this.Name = name;
	}

	public ArrayList<Child> getItems() {
		return Items;
	}

	public void setItems(ArrayList<Child> Items) {
		this.Items = Items;
	}

}