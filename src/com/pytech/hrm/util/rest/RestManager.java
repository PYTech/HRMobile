package com.pytech.hrm.util.rest;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.StatusLine;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.client.utils.URLEncodedUtils;
import org.apache.http.cookie.Cookie;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;

import android.util.Log;

import com.pytech.hrm.exception.AuthException;
import com.pytech.hrm.models.rest.ParamPair;
import com.pytech.hrm.models.rest.RestTask;
import com.pytech.hrm.util.constants.HRM;
import com.pytech.hrm.util.constants.REST;
import com.pytech.hrm.util.constants.enums.ReqMethod;

public class RestManager {
	private static String userId;
	private static List<Cookie> cookieList;

	public static void login(String username, String password) throws AuthException, IOException {
		prepareCookies(username, password);
		userId = username;
	}
	
	public static RestTask sendHttpGetRequest(String path, String type, List<ParamPair> params) throws IOException, AuthException, IllegalStateException {
		return sendHttpRequest(ReqMethod.GET, REST.SERVER_URL + path, type, params);
	}
	
	public static RestTask sendHttpPostRequest(String path, String type, String jsonStr) throws IOException, AuthException, IllegalStateException {
		return sendHttpRequest(ReqMethod.POST, REST.SERVER_URL + path, type, jsonStr);
	}
	
	public static String getEntityAsString(HttpResponse response) throws IllegalStateException, IOException {
		// Extract login status from response entity.
		StringBuffer buffer = new StringBuffer();
		InputStream input = response.getEntity().getContent();
		BufferedReader reader = new BufferedReader(new InputStreamReader(input));
		String line = null;
		while((line = reader.readLine()) != null) {
			buffer.append(line);
		}
		return buffer.toString();
	}
	
	public static void cleanCookies() {
		getClient().getCookieStore().clear();
		userId = null;
	}
	
	public static String getUserId() {
		if(userId != null) {
			return userId;
		}
		return HRM.ANNONYMOUS;
	}

	protected synchronized static void prepareCookies(String username, String password) throws AuthException, IOException {
		if(cookieList == null) {
			cookieList = CookieManager.getCookies(username, password);
		}
	}
	
	protected synchronized static DefaultHttpClient getClient() {
		return HRMHttpClientFactory.getClient();
	}
	
	protected static RestTask sendHttpRequest(ReqMethod method, String url, String type, Object params) throws IOException, AuthException {
		DefaultHttpClient httpClient = getClient();
		HttpRequestBase request = null;
		
		switch(method) {
			case GET:
				request = new HttpGet(genParmas(url, params));
				break;
			case POST:
				request = new HttpPost(url);
				String jsonStr = JsonConverter.toJson(params);
				StringEntity entity = new StringEntity(jsonStr);
				entity.setContentType(type);
				((HttpPost) request).setEntity(entity);
				break;
			case PUT:
				request = new HttpPut(url);
				jsonStr = JsonConverter.toJson(params);
				entity = new StringEntity(jsonStr);
				entity.setContentType(type);
				((HttpPut) request).setEntity(entity);
				break;
			default:
				throw new IllegalArgumentException(String.format("Method type[%s] current NOT supported! Please use GET or POST instead.", method));
		}
		
		// Handle http response.
		request.addHeader(REST.HEADER_CONTENT_TYPE_KEY, type);
		HttpResponse response = httpClient.execute(request);
		return processResponse(response);
	}

	protected static RestTask processResponse(HttpResponse response) throws IOException, AuthException {
		// Check status code.
		StatusLine status = response.getStatusLine();
		if(HttpStatus.SC_UNAUTHORIZED == status.getStatusCode()) {
			throw new AuthException();
		}
		
		// Check entity string.
		String responseContent = getEntityAsString(response);
		Log.d(RestManager.class.getName(), String.format("Get response[%s] from web server.", responseContent));
		if(StringUtils.contains(responseContent, REST.MSG_AUTH_EXPIRED)) {
			throw new AuthException();
		}
		
		// Parse entity string.
		RestTask restTask = JsonConverter.convertFromJson(RestTask.class, responseContent);
		if(restTask == null) {
			throw new AuthException();
		}
		return restTask;
	}
	
	@SuppressWarnings("unchecked")
	protected static String genParmas(String url, Object obj) {
		if(!url.endsWith("?")) {
			url += "?";
		}
		List<BasicNameValuePair> params = new LinkedList<BasicNameValuePair>();
		List<ParamPair> pairList = (List<ParamPair>) obj;
		for(ParamPair pair : pairList) {
			params.add(new BasicNameValuePair(pair.getKey(), pair.getValue()));
		}
		String paramString = URLEncodedUtils.format(params, HRM.DEFAULT_ENCODING);
		url += paramString;
		return url;
	}
}
