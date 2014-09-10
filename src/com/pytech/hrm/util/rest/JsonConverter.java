package com.pytech.hrm.util.rest;

import java.util.List;

import org.codehaus.jackson.map.DeserializationConfig;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.annotate.JsonSerialize.Inclusion;
import org.codehaus.jackson.map.type.CollectionType;

import android.util.Log;

public class JsonConverter {
	public static <T> List<T> convertFromJsons(Class<T> clazz, String json) {
		return convertFromJsons(clazz, json, false);
	}

	public static <T> List<T> convertFromJsons(Class<T> clazz, String json, boolean showErr) {
		ObjectMapper mapper = new ObjectMapper();
		try {
			CollectionType type = mapper.getTypeFactory().constructCollectionType(List.class, clazz);
			return mapper.readValue(json, type);
		} catch(Exception e) {
			Log.d(JsonConverter.class.getName(),
					String.format("JSON string[%s] cannot be convert to java class[%s], reason:[%s]", json, clazz.getName(), e.getMessage()));
		}
		return null;
	}

	public static <T> T convertFromJson(Class<T> clazz, String json) {
		return convertFromJson(clazz, json, false);
	}

	public static <T> T convertFromJson(Class<T> clazz, String json, boolean showErr) {
		ObjectMapper mapper = new ObjectMapper();
		mapper.configure(DeserializationConfig.Feature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		try {
			return mapper.readValue(json, clazz);
		} catch(Exception e) {
			Log.d(JsonConverter.class.getName(),
					String.format("JSON string[%s] cannot be convert to java class[%s], reason:[%s]", json, clazz.getName(), e.getMessage()));
		}
		return null;
	}

	public static <T> T convertFromObject(Class<T> clazz, Object obj) {
		return convertFromObject(clazz, obj, false);
	}

	public static <T> T convertFromObject(Class<T> clazz, Object obj, boolean showErr) {
		ObjectMapper mapper = new ObjectMapper();
		try {
			mapper.configure(DeserializationConfig.Feature.FAIL_ON_UNKNOWN_PROPERTIES, false);
			return mapper.convertValue(obj, clazz);
		} catch(Exception e) {
			Log.d(JsonConverter.class.getName(),
					String.format("Object[%s] cannot be convert to java class[%s], reason:[%s]", obj, clazz.getName(), e.getMessage()));
		}
		return null;
	}

	public static <T> List<T> convertFromObjects(Class<T> clazz, Object obj) {
		return convertFromObjects(clazz, obj, false);
	}

	public static <T> List<T> convertFromObjects(Class<T> clazz, Object obj, boolean showErr) {
		ObjectMapper mapper = new ObjectMapper();
		try {
			mapper.configure(DeserializationConfig.Feature.FAIL_ON_UNKNOWN_PROPERTIES, false);
			CollectionType type = mapper.getTypeFactory().constructCollectionType(List.class, clazz);
			return mapper.convertValue(obj, type);
		} catch(Exception e) {
			Log.d(JsonConverter.class.getName(),
					String.format("Objects[%s] cannot be convert to java class[%s], reason:[%s]", obj, clazz.getName(), e.getMessage()));
		}
		return null;
	}

	public static String toJson(Object model) {
		return toJson(model, false);
	}

	public static String toJson(Object model, boolean showErr) {
		ObjectMapper mapper = new ObjectMapper();
		mapper.setSerializationInclusion(Inclusion.NON_NULL);
		try {
			return mapper.writeValueAsString(model);
		} catch(Exception e) {
			Log.d(JsonConverter.class.getName(),
					String.format("Model[%s] of java class[%s] cannot be convert to JSON string, reason:[%s]", model, model.getClass().getName(),
							e.getMessage()));
		}
		return null;
	}
}
