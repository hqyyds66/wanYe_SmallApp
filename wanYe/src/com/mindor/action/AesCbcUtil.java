package com.mindor.action;

import org.apache.commons.codec.binary.Base64;
import org.bouncycastle.jce.provider.BouncyCastleProvider;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.io.UnsupportedEncodingException;
import java.security.*;
import java.security.spec.InvalidParameterSpecException;
public class AesCbcUtil {
	
	 static {
	        //BouncyCastle是一个开源的加解密解决方案，主页在http://www.bouncycastle.org/
	        Security.addProvider(new BouncyCastleProvider());
	    }

	    /**
	     * AES解密
	     *
	     * @param data           //密文，被加密的数据
	     * @param key            //秘钥
	     * @param iv             //偏移量
	     * @param encodingFormat //解密后的结果需要进行的编码
	     * @return
	     * @throws Exception
	     */
	    public static String decrypt(String data, String key, String iv, String encodingFormat) throws Exception {

	    	System.out.println("data=="+data);
	    	System.out.println("key=="+key);
	    	data=data.replaceAll(" ", "+");
	    	iv=iv.replaceAll(" ", "+");
//	    	data="a8taE9R6EyC2ehNLiNquHH81tmMXr24mJfHUZQ3OrgETpJ9QwksoJWwUQLNqc7ISUGw8ixdHRpaT5UccqubIPYtpj7xJEQlzm9UNb03qTGCmvZ39Zr8i+sFh+RSrwIJ7bmOk0KAki4PyM8zbKS7inpPDr6DE4WHBYsgzLhP6o1U2cc+BUgZHpwOpcse5S6P5KQ7k9CTXhSeBc63Pgm9SGF1ERSRN5wEABOD60Q6bfVruMxbR8UggQecPR+VjdKiSKzax+2Zxui6y7T0RBDjqkewVVy1FMafbbKckMX9sQASJ92g02ywc3kyF7knfDCd9mNMysz65UYSFvNntKd3JBYwsv07ZivvwTIm2qUUrPrg1u1Kp0vQqu+DaFpy/0pBPaSmWTeJ7FxaXUOSrYxRGusPG7U12z7Eb5TfeCQbtmtZGpKv5Bb5Ec3selsqRqyW/FmNkrPMl7TG+5kevonqIN7Qs3zGDWiikewQ1uV6Llgg=";
//	    	key="";
//	    	iv="QRo9ueVejHzrmSUDj4Qwlw==";
	        //被加密的数据
	        byte[] dataByte = Base64.decodeBase64(data);
	        //加密秘钥
	        byte[] keyByte = Base64.decodeBase64(key);
	        //偏移量
	        byte[] ivByte = Base64.decodeBase64(iv);
	        
	        try {
	            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS7Padding");

	            SecretKeySpec spec = new SecretKeySpec(keyByte, "AES");

	            AlgorithmParameters parameters = AlgorithmParameters.getInstance("AES");
	            parameters.init(new IvParameterSpec(ivByte));

	            cipher.init(Cipher.DECRYPT_MODE, spec, parameters);// 初始化

	            byte[] resultByte = cipher.doFinal(dataByte);
	            if (null != resultByte && resultByte.length > 0) {
	                String result = new String(resultByte, encodingFormat);
	                return result;
	            }
	            return null;
	        } catch (NoSuchAlgorithmException e) {
	            e.printStackTrace();
	        } catch (NoSuchPaddingException e) {
	            e.printStackTrace();
	        } catch (InvalidParameterSpecException e) {
	            e.printStackTrace();
	        } catch (InvalidKeyException e) {
	            e.printStackTrace();
	        } catch (InvalidAlgorithmParameterException e) {
	            e.printStackTrace();
	        } catch (IllegalBlockSizeException e) {
	            e.printStackTrace();
	        } catch (BadPaddingException e) {
	            e.printStackTrace();
	        } catch (UnsupportedEncodingException e) {
	            e.printStackTrace();
	        }

	        return null;
	    }

}
