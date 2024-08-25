package com.mrqinzh.framework.common.utils;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

public class AesEncryptUtil {

    // 密匙
    private static final String KEY = "23d$%Q#kjwgsl@@$";
    // 偏移量
    private static final String OFFSET = "ogiGRWos02oH2230";
    // 编码
    private static final String ENCODING = "UTF-8";
    //算法
    private static final String ALGORITHM = "AES";
    // 默认的加密算法
    private static final String CIPHER_ALGORITHM = "AES/CBC/PKCS5Padding";

    /**
     * AES加密字符串
     *
     * @return 密文
     */
    public static String encrypt(String data) {
        try {
            Cipher cipher = Cipher.getInstance(CIPHER_ALGORITHM);
            SecretKeySpec spec = new SecretKeySpec(KEY.getBytes(StandardCharsets.UTF_8), ALGORITHM);
            IvParameterSpec iv = new IvParameterSpec(OFFSET.getBytes());//使用CBC模式，需要一个向量iv，可增加加密算法的强度
            cipher.init(Cipher.ENCRYPT_MODE, spec, iv);
            byte[] encrypted = cipher.doFinal(data.getBytes(ENCODING));
            return new String(Base64.getEncoder().encode(encrypted));//此处使用BASE64做转码。
        }catch (Exception e){
            e.printStackTrace();
            throw new RuntimeException("加密失败");
        }

    }

    /**
     * 解密AES加密过的字符串
     *
     * @param data
     *            AES加密过过的内容
     * @return 明文
     */
    public static String decrypt(String data) {
        try {
            Cipher cipher = Cipher.getInstance(CIPHER_ALGORITHM);
            SecretKeySpec spec = new SecretKeySpec(KEY.getBytes(StandardCharsets.UTF_8), ALGORITHM);
            IvParameterSpec iv = new IvParameterSpec(OFFSET.getBytes());//使用CBC模式，需要一个向量iv，可增加加密算法的强度
            cipher.init(Cipher.DECRYPT_MODE, spec, iv);
            byte[] buffer = Base64.getDecoder().decode(data);
            byte[] encrypted = cipher.doFinal(buffer);
            return new String(encrypted, ENCODING); //此处使用BASE64做转码。
        }catch (Exception e){
            e.printStackTrace();
            throw new RuntimeException("解密失败！");
        }
    }

    public static void main(String[] args) {
        String content = "123456";
        System.out.println("加密前：" + content);

        String encrypt = encrypt(content);
        System.out.println("加密后：" + encrypt);

        String decrypt = decrypt("/m1zZ7AJeYA1LgkvQSafxQ==");
        System.out.println("解密后：" + decrypt);
    }
}
