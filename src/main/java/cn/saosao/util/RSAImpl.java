package cn.saosao.util;

import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.SecureRandom;
import java.security.Signature;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.crypto.Cipher;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.stereotype.Component;



public class RSAImpl {
	
	/**加密
	 * 
	 */

	public static String getCode(String original, String commKey) {
		byte[] decoded = Base64.decodeBase64(commKey);
		RSAPublicKey pubKey;
		String outStr="";
		try {
			pubKey = (RSAPublicKey) KeyFactory.getInstance("RSA").generatePublic(new X509EncodedKeySpec(decoded));
			//RSA加密
			Cipher cipher = Cipher.getInstance("RSA");
			cipher.init(Cipher.ENCRYPT_MODE, pubKey);
			outStr = Base64.encodeBase64String(cipher.doFinal(original.getBytes("UTF-8")));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return outStr;
	}
	
	/**解密
	 * 
	 */

	public static String getOriginal(String code, String privaKey)throws Exception {
		//64位解码加密后的字符串
		byte[] inputByte = null; 
		inputByte=Base64.decodeBase64(code.getBytes("UTF-8"));
		//base64编码的私钥
		byte[] decoded = Base64.decodeBase64(privaKey);  
        RSAPrivateKey priKey = (RSAPrivateKey) KeyFactory.getInstance("RSA").generatePrivate(new PKCS8EncodedKeySpec(decoded));  
      //RSA解密
		Cipher cipher = Cipher.getInstance("RSA");
		cipher.init(Cipher.DECRYPT_MODE, priKey);
		String outStr = new String(cipher.doFinal(inputByte));
		return outStr;
	}
	/**获取钥对
	 * 
	 */

	public static Map<Integer, String> getCommAndPrivaKey() {
		Map<Integer, String> keyMap = new HashMap<Integer, String>();
		// KeyPairGenerator类用于生成公钥和私钥对，基于RSA算法生成对象
		KeyPairGenerator keyPairGen;
		try {
			keyPairGen = KeyPairGenerator.getInstance("RSA");
			// 初始化密钥对生成器，密钥大小为96-1024位   
			keyPairGen.initialize(1024,new SecureRandom());  
			// 生成一个密钥对，保存在keyPair中  
			KeyPair keyPair = keyPairGen.generateKeyPair();  
			RSAPrivateKey privateKey = (RSAPrivateKey) keyPair.getPrivate();   // 私钥
			RSAPublicKey publicKey = (RSAPublicKey) keyPair.getPublic();  // 公钥
			String publicKeyString = new String(Base64.encodeBase64(publicKey.getEncoded()));  
			// 得到私钥字符串  
			String privateKeyString = new String(Base64.encodeBase64((privateKey.getEncoded())));  
			// 将公钥和私钥保存到Map
			keyMap.put(0,publicKeyString);  //0表示公钥
			keyMap.put(1,privateKeyString);  //1表示私钥
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}  
		return keyMap;
	}
	/**
     * 签名
     * 
     * @param data 待签名数据
     * @param privateKey 私钥
     * @return 签名
     */

    public static String sign(String data, PrivateKey privateKey) throws Exception {
        byte[] keyBytes = privateKey.getEncoded();
        PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(keyBytes);
        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        PrivateKey key = keyFactory.generatePrivate(keySpec);
        Signature signature = Signature.getInstance("MD5withRSA");
        signature.initSign(key);
        signature.update(data.getBytes());
        return new String(Base64.encodeBase64(signature.sign()));
    }

    /**
     * 验签
     * 
     * @param srcData 原始字符串
     * @param publicKey 公钥
     * @param sign 签名
     * @return 是否验签通过
     */

    public static boolean verify(String srcData, PublicKey publicKey, String sign) throws Exception {
        byte[] keyBytes = publicKey.getEncoded();
        X509EncodedKeySpec keySpec = new X509EncodedKeySpec(keyBytes);
        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        PublicKey key = keyFactory.generatePublic(keySpec);
        Signature signature = Signature.getInstance("MD5withRSA");
        signature.initVerify(key);
        signature.update(srcData.getBytes());
        return signature.verify(Base64.decodeBase64(sign.getBytes()));
    }

    /**
     * 获取私钥
     * 
     * @param privateKey 私钥字符串
     * @return
     */

    public static PrivateKey getPrivateKey(String privateKey) throws Exception {
        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        byte[] decodedKey = Base64.decodeBase64(privateKey.getBytes());
        PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(decodedKey);
        return keyFactory.generatePrivate(keySpec);
    }

    /**
     * 获取公钥
     * 
     * @param publicKey 公钥字符串
     * @return
     */

    public static PublicKey getPublicKey(String publicKey) throws Exception {
        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        byte[] decodedKey = Base64.decodeBase64(publicKey.getBytes());
        X509EncodedKeySpec keySpec = new X509EncodedKeySpec(decodedKey);
        return keyFactory.generatePublic(keySpec);
    }
	/**通过MD5获取明文的摘要
	 * 
	 */

	public static String getMD5Hash(String original) {
		
		return DigestUtils.md5Hex(original);
	}
	
	


	

}
