package com.pytech.hrm.util.rest;

import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.security.KeyManagementException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.UnrecoverableKeyException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;

import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import org.apache.http.HttpVersion;
import org.apache.http.conn.ClientConnectionManager;
import org.apache.http.conn.scheme.PlainSocketFactory;
import org.apache.http.conn.scheme.Scheme;
import org.apache.http.conn.scheme.SchemeRegistry;
import org.apache.http.conn.ssl.SSLSocketFactory;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.conn.tsccm.ThreadSafeClientConnManager;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.apache.http.params.HttpProtocolParams;
import org.apache.http.protocol.HTTP;

import com.pytech.hrm.util.constants.HRM;

public class HRMHttpClientFactory extends SSLSocketFactory {
	private SSLContext mSSLContext = SSLContext.getInstance("TLS");
	private static DefaultHttpClient client;

	@Override
	public Socket createSocket() throws IOException {
		return mSSLContext.getSocketFactory().createSocket();
	}

	@Override
	public Socket createSocket(Socket socket, String host, int port, boolean autoClose) throws IOException, UnknownHostException {
		return mSSLContext.getSocketFactory().createSocket(socket, host, port, autoClose);
	}

	public HRMHttpClientFactory(KeyStore truststore) throws NoSuchAlgorithmException, KeyManagementException, KeyStoreException, UnrecoverableKeyException {
		super(truststore);

		TrustManager mTrustManager = new X509TrustManager() {

			@Override
			public void checkClientTrusted(X509Certificate[] chain, String authType) throws CertificateException {
			}

			@Override
			public void checkServerTrusted(X509Certificate[] chain, String authType) throws CertificateException {
			}

			@Override
			public X509Certificate[] getAcceptedIssuers() {
				return null;
			}
		};
		mSSLContext.init(null, new TrustManager[] { mTrustManager }, null);
	}

	public static synchronized DefaultHttpClient getClient() {
		if(client == null) {
			try {
				KeyStore trustStore = KeyStore.getInstance(KeyStore.getDefaultType());
				trustStore.load(null, null);
	
				SSLSocketFactory mSSLSocketFactory = new HRMHttpClientFactory(trustStore);
				mSSLSocketFactory.setHostnameVerifier(SSLSocketFactory.ALLOW_ALL_HOSTNAME_VERIFIER);
	
				HttpParams params = new BasicHttpParams();
				HttpProtocolParams.setVersion(params, HttpVersion.HTTP_1_1);
				HttpProtocolParams.setContentCharset(params, HTTP.UTF_8);
				HttpConnectionParams.setConnectionTimeout(params, HRM.REST_CONNECT_TIMEOUT);
				HttpConnectionParams.setSoTimeout(params, HRM.REST_SOCKET_TIMEOUT);
	
				SchemeRegistry registry = new SchemeRegistry();
				registry.register(new Scheme("http", PlainSocketFactory.getSocketFactory(), 80));
				registry.register(new Scheme("https", mSSLSocketFactory, 443));
	
				ClientConnectionManager ccm = new ThreadSafeClientConnManager(params, registry);
				client = new DefaultHttpClient(ccm, params);
			} catch(Exception e) {
				// If preparation job failed -> create default http client instead.
				e.printStackTrace();
				client = new DefaultHttpClient();
			} 
		}
		return client;
	}
}
