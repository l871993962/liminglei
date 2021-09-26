package com.yss.ifa.szt.tool.util;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

/**
 * @author limh 民生银行提供的AES加密方式
 * BUG #130668 YSS-深证通发送完民生银行余额明细查询后无法解析返回报文，更新民生银行加密方式
 */
public class CmbcEncryptor {
	private String password;
	private SecretKeySpec key;
	private Cipher cipher;

	public void setPassword(String password) {
		this.password = password;
	}

	public String decrypt(String content) throws Exception {
		try {
			this.key = new SecretKeySpec(this.password.getBytes("UTF-8"), "AES");
			this.cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");

			this.cipher.init(2, this.key);
			byte[] res = this.cipher.doFinal(parseHexStr2Byte(content));
			return new String(res, "utf-8");
		} catch (Exception e) {
			throw new Exception(e);
		}
	}

	public String encrypt(String content) throws Exception {
		try {
			this.key = new SecretKeySpec(this.password.getBytes("UTF-8"), "AES");
			this.cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");

			this.cipher.init(1, this.key);
			byte[] res = this.cipher.doFinal(content.getBytes("utf-8"));
			return parseByte2HexStr(res);
		} catch (Exception e) {
			throw new Exception(e);
		}
	}

	public static String parseByte2HexStr(byte[] buf) {
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < buf.length; ++i) {
			String hex = Integer.toHexString(buf[i] & 0xFF);
			if (hex.length() == 1) {
				hex = '0' + hex;
			}
			sb.append(hex.toUpperCase());
		}
		return sb.toString();
	}

	public static byte[] parseHexStr2Byte(String hexStr) {
		if (hexStr.length() < 1)
			return null;
		byte[] result = new byte[hexStr.length() / 2];
		for (int i = 0; i < hexStr.length() / 2; ++i) {
			int high = Integer.parseInt(hexStr.substring(i * 2, i * 2 + 1), 16);
			int low = Integer.parseInt(hexStr.substring(i * 2 + 1, i * 2 + 2),
					16);
			result[i] = (byte) (high * 16 + low);
		}
		return result;
	}
}
