package com.pytech.hrm.util;

import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.codec.binary.Base64;

import android.util.Log;

/**
 * @author Administrator
 * 
 */
public class Encrypt {

	private static String privatekey = new String("ec7c7cda32b6831b7db63f21efbfb027");

	public static String getDigest(String msg) throws Exception {

		String key = "";
		String new_val = msg.concat(privatekey);
		MessageDigest md = MessageDigest.getInstance("SHA");
		md.update(new_val.getBytes());

		byte[] digest = md.digest();
		key = toHexString(digest);
		return key;
	}

	public static boolean verifyDigest(String val, String key) throws Exception {

		String new_val = val.concat(privatekey);
		MessageDigest md = MessageDigest.getInstance("SHA");
		md.update(new_val.getBytes());
		byte[] digest = md.digest();
		String verifykey = toHexString(digest);

		if(verifykey.equals(key)) {
			return true;
		}

		return false;
	}

	public static String toHexString(byte[] b) {
		StringBuffer hexString = new StringBuffer();
		String plainText;

		for(int i = 0; i < b.length; i++) {
			plainText = Integer.toHexString(0xFF & b[i]);
			if(plainText.length() < 2) {
				plainText = "0" + plainText;
			}
			hexString.append(plainText);
		}
		return hexString.toString();
	}

	public static String getMD5(String input) throws Exception {

		String result = input;
		if(input != null) {
			MessageDigest md = MessageDigest.getInstance("MD5"); // or "SHA-1"
			md.update(input.getBytes());
			BigInteger hash = new BigInteger(1, md.digest());
			result = hash.toString(16);
			while(result.length() < 32) {
				result = "0" + result;
			}
		}
		return result;
	}

	public static String getEncrypt(String msg) {
		try {
			KeyGenerator keyG = KeyGenerator.getInstance("AES");
			SecureRandom secureRandom = SecureRandom.getInstance("SHA1PRNG");
			secureRandom.setSeed(privatekey.getBytes());

			keyG.init(128, secureRandom);
			SecretKey secuK = keyG.generateKey();
			byte[] key = secuK.getEncoded();
			SecretKeySpec spec = new SecretKeySpec(key, "AES");
			Cipher cipher = Cipher.getInstance("AES");
			cipher.init(Cipher.ENCRYPT_MODE, spec);
			byte[] encryptData = cipher.doFinal(msg.getBytes());

			Log.d(Encrypt.class.getName(), "encryptData=" + new String(Base64.encodeBase64(encryptData, false)));
			return(new String(Base64.encodeBase64(encryptData, false)));
		} catch(Exception e) {
			// TODO: handle exception
		}
		return null;
	}

	public static String getDecrypt(String msg) {
		try {
			KeyGenerator keyG = KeyGenerator.getInstance("AES");
			SecureRandom secureRandom = SecureRandom.getInstance("SHA1PRNG");
			secureRandom.setSeed(privatekey.getBytes());
			keyG.init(128, secureRandom);
			SecretKey secuK = keyG.generateKey();
			byte[] key = secuK.getEncoded();
			SecretKeySpec spec = new SecretKeySpec(key, "AES");
			Cipher cipher = Cipher.getInstance("AES");

			cipher.init(Cipher.DECRYPT_MODE, spec);
			byte[] original = cipher.doFinal(Base64.decodeBase64(msg.getBytes()));
			return(new String(original));
		} catch(Exception e) {
			// TODO: handle exception
		}
		return null;
	}

	private static String convertToHex(byte[] data) {
		StringBuffer buf = new StringBuffer();
		for(int i = 0; i < data.length; i++) {
			int halfbyte = (data[i] >>> 4) & 0x0F;
			int two_halfs = 0;
			do {
				if((0 <= halfbyte) && (halfbyte <= 9))
					buf.append((char) ('0' + halfbyte));
				else
					buf.append((char) ('a' + (halfbyte - 10)));
				halfbyte = data[i] & 0x0F;
			} while(two_halfs++ < 1);
		}
		return buf.toString();
	}

	public static String SHA1(String text) throws NoSuchAlgorithmException, UnsupportedEncodingException {
		MessageDigest md;
		md = MessageDigest.getInstance("SHA-1");
		byte[] sha1hash = new byte[40];
		md.update(text.getBytes("iso-8859-1"), 0, text.length());
		sha1hash = md.digest();
		return convertToHex(sha1hash);
	}
}
