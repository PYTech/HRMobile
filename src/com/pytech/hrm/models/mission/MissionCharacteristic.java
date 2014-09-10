package com.pytech.hrm.models.mission;

import java.io.Serializable;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.map.annotate.JsonSerialize;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
public class MissionCharacteristic implements Serializable {

	private static final long serialVersionUID = 3293361050910505761L;
	private String charSpecId;
	private String name;
	private String value;
	private String valueType;
	
	
	public String getCharSpecId() {
		return charSpecId;
	}
	public void setCharSpecId(String charSpecId) {
		this.charSpecId = charSpecId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	
	public String getValueType() {
		return valueType;
	}
	public void setValueType(String valueType) {
		this.valueType = valueType;
	}
	@Override
	public String toString() {
		return "MissionCharacteristic [charSpecId=" + charSpecId + ", name=" + name + ", value=" + value + ", valueType=" + valueType + "]";
	}
	
	
	
	
	

	
	
}
