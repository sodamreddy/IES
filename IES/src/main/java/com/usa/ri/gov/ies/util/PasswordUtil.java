package com.usa.ri.gov.ies.util;

import java.util.Base64;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
/**
 * this class is used for encrypting and dencrypting the password
 * @author sodam
 *
 */

public class PasswordUtil {
	private static Logger logger = LoggerFactory.getLogger(PasswordUtil.class);

	private static final String key = "aesEncryptionKey";
private static final String initVector = "encryptionIntVec";
	/**
	 * this method encrypts and encode the given password
	 * @param value
	 * @return encrypted value
	 */
	public static String encrypt(String value) {
		
		try {
			IvParameterSpec iv = new IvParameterSpec(initVector.getBytes("UTF-8"));
			SecretKeySpec skeySpec = new SecretKeySpec(key.getBytes("UTF-8"), "AES");

			Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5PADDING");
			cipher.init(Cipher.ENCRYPT_MODE, skeySpec, iv);

			byte[] encrypted = cipher.doFinal(value.getBytes());
			logger.info("Password Encrypted Successfully");
			return Base64.getEncoder().encodeToString(encrypted);
		} catch (Exception ex) {
			logger.error("Password Encryption failed::" + ex.getMessage());
		}
		return null;
	}

	public static String decrypt(String encrypted) {
		try {
			IvParameterSpec iv = new IvParameterSpec(initVector.getBytes("UTF-8"));
			SecretKeySpec skeySpec = new SecretKeySpec(key.getBytes("UTF-8"), "AES");

			Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5PADDING");
			cipher.init(Cipher.DECRYPT_MODE, skeySpec, iv);
			byte[] original = cipher.doFinal(Base64.getDecoder().decode(encrypted));

			return new String(original);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return null;
}
}
