package com.palbecki.serializacja;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSeeAlso;

@XmlRootElement(name="List")
@XmlSeeAlso({Login.class})
public class JaxbList<T> implements Serializable{

	private static final long serialVersionUID = 1L;

	protected List<T> list = new ArrayList<>();

    public JaxbList(){}

    public JaxbList(List<T> list){
    	this.list=list;
    }

    @XmlElement(name="Item")
    public List<T> getList(){
    	return list;
    }
}