package com.model;

import javax.xml.bind.annotation.XmlRootElement;


@XmlRootElement
public class User {
	private int id;
    private String name;
    private String email;
    private int type;
    public User(int _id, String _name,String _email,int _type)
    {
    	id = _id;
    	name= _name;
    	email= _email;
    	type=_type;
    }
    public User()
    {
    	
    }
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	

    
}
