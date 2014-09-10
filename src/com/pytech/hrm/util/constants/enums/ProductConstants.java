package com.pytech.hrm.util.constants.enums;

import java.util.HashMap;
import java.util.Map;

import com.pytech.hrm.util.constants.enums.OSType.OS;

public enum ProductConstants {
	BLENDER_2_2_6("23c63464-7918-4245-af3e-625688cdd719", "Blender2.66","2.66",null,null,null),
	BLENDER_2_2_8_a("a00d70ac-2fe4-436b-a032-75df1d345907", "Blender2.68a","2.68a",null,null,null),
	MAYA_2015("5f9cba67-b55f-4778-b883-8089e3000d49", "Maya_2015","2015",null,null,new String[]{"0","1","2","3","4","5","6"}),
	MAYA_2014("093a72aa-6602-4fb0-9665-53279dec9e1f", "Maya_2014","2014",null,null,new String[]{"0","1","2","3","4","5","6"}),
	MAYA_2013("c3cba6f2-eb2e-4f9e-b7bc-9c0a41708dd2", "Maya_2013","2013",null,null,new String[]{"0","1","2","3","4","5","6"}),
	MAYA_2012("9c8bac37-83f1-4b59-87d4-329a376c2d04", "Maya_2012","2012",null,null,new String[]{"0","1","2","3","4","5","6"}),
	MAYA_2015_MENTAL_RAY("ed129ffe-e640-4bad-869b-efb6d87029e7", "Maya_2015_Mental_ray","2015",null,null,new String[]{"0","1","2","3","4","5","6"}),
	MAYA_2014_MENTAL_RAY("314d6e60-37a0-11e3-aa6e-0800200c9a66", "Maya_2014_Mental_ray","2014",null,null,new String[]{"0","1","2","3","4","5","6"}),
	MAYA_2013_MENTAL_RAY("18502ddd-25e9-4aea-9a44-010b8e58f30b", "Maya_2013_Mental_ray","2013",null,null,new String[]{"0","1","2","3","4","5","6"}),	
	MAYA_2012_MENTAL_RAY("6ccbb74b-d1d3-41cf-8193-8f166541e36a", "Maya_2012_Mental_ray","2012",null,null,new String[]{"0","1","2","3","4","5","6"}),
	MAYA_2015_V_RAY_2_4("469f6230-5d24-46eb-9343-b97d4d7dbc85", "Maya_2015_V_RAY_2_4","2015",null,ImagePattern.NAME_INDEX_EXT,new String[]{"0","1","2","3","4","5","6"}),
	MAYA_2014_V_RAY_2_4("20151abc-e6cb-4a45-9e8b-08c702b7a325", "Maya_2014_V_RAY_2_4","2014",null,ImagePattern.NAME_INDEX_EXT,new String[]{"0","1","2","3","4","5","6"}),
	MAYA_2013_V_RAY_2_4("126101bd-aca7-44ea-ac72-400591d3668f", "Maya_2013_V_RAY_2_4","2013",null,ImagePattern.NAME_INDEX_EXT,new String[]{"0","1","2","3","4","5","6"}),
	MAYA_2012_V_RAY_2_4("d82f5a41-0dd3-4467-83da-7f366bc9b85e", "Maya_2012_V_RAY_2_4","2012",null,ImagePattern.NAME_INDEX_EXT,new String[]{"0","1","2","3","4","5","6"}),
	MAYA_2013_ARNOLD("7c39d099-a5f8-4a9d-afb6-3b683e25b382","Maya_2013_Arnold","2013",null,ImagePattern.NAME_EXT_INDEX,new String[]{"0","1","2","3","4","5","6"}),
	MAYA_2014_ARNOLD("bc599c40-eb05-11e3-ac10-0800200c9a66","Maya_2014_Arnold","2014",null,ImagePattern.NAME_EXT_INDEX,new String[]{"0","1","2","3","4","5","6"}),
	MAX_2013("7504346c-3831-4695-a028-5998b7ec43d7","3ds_Max_2013","2013",OS.WINDOWS,null,new String[]{"0","1","2","3","4","5","6"}),
	MAX_2014("0cd63c8d-871a-4452-bbb3-c99572f5b618","3ds_Max_2014","2014",OS.WINDOWS,null,new String[]{"0","1","2","3","4","5","6"});
	
	private static final Map<String, ProductConstants> strToTypeMap = new HashMap<String, ProductConstants>();
	static {
		for(ProductConstants type : ProductConstants.values()) {
			strToTypeMap.put(type.getProductId(), type);
		}
	}	
	private String productId;
	private String name;
	private String version;
	private OS neededOS;
	private ImagePattern reNameImagePattern;
	private String[] logLevel;
	public static ProductConstants getType(String productOid) {
		return strToTypeMap.get(productOid);
	}	

	private ProductConstants(String productId, String name,String version,OS neededOS,ImagePattern reNameImagePattern,String[] logLevel) {
		this.productId = productId;
		this.name = name;	
		this.version = version;
		this.neededOS = neededOS;
		this.reNameImagePattern = reNameImagePattern;
		this.logLevel = logLevel;
	}
	
	public static ProductConstants convertToEnumType(String productId) {
		for(ProductConstants productConstant:ProductConstants.values()) {
			if(productConstant.getProductId().equals(productId)) {
				return productConstant;
			}
		}
		return null;
	}
	
	
	public  String getVersion(){
		return version;
	}
		
	public  String getProductId() {
		return this.productId;
	}

	public String getName() {
		return name;
	}
	
	public ImagePattern getReNameImagePattern() {
		return reNameImagePattern;
	}

	public OS getNeededOS() {
		return neededOS;
	}
	
	public String getLogLevel() {
		if(logLevel!=null && logLevel.length!=0) {
			return logLevel[3];
		}else {
			return null;
		}
	}
	
}
