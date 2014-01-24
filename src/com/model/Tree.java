package com.model;

import javax.xml.bind.annotation.XmlRootElement;


@XmlRootElement
public class Tree {
	private int id;
    private String parent;
    private String text;
    private int type;
    private int entityId;
    public Tree(int _id, String _parent,String _text,int _type, int _entityId)
    {
    	id = _id;
    	parent = _parent;
    	text = _text;
    	type = _type;
    	entityId = _entityId;
    }
    public Tree()
    {
    	
    }
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getParent() {
		return parent;
	}
	public void setParent(String parent) {
		this.parent = parent;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	public int getEntityId() {
		return entityId;
	}
	public void setEntityId(int entityId) {
		this.entityId = entityId;
	}


    
}
