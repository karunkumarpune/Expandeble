package com.expendeble.option1.model;

import java.util.ArrayList;

public class Group {

	private String Name;
	private ArrayList<Child> Items;


	public String getName() {
		return Name;
	}

	public void setName(String name) {
		Name = name;
	}

	public ArrayList<Child> getItems() {
		return Items;
	}

	public void setItems(ArrayList<Child> items) {
		Items = items;
	}
}