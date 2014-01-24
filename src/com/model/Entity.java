package com.model;

import javax.xml.bind.annotation.XmlRootElement;


@XmlRootElement
public class Entity {
	private int id;
    private int type;
    private String value;
    public Entity(int _id, int _type, String _value)
    {
    	id = _id;
    	type = _type;
    	value = _value;
    }
    public Entity()
    {
    	
    }
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}


    
}
