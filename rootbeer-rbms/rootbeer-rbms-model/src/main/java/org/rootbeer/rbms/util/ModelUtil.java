package org.rootbeer.rbms.util;

import com.google.gson.*;

public abstract class ModelUtil {
	public static final Gson GSON;
	
	static {
		GSON = new GsonBuilder()
		.setDateFormat("yyyy-MM-dd HH:mm:ss.SSS")
		.create();
	}
}
