package com.semicolon.springpart.util;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.util.Base64;
import java.io.FileInputStream;
import java.util.Properties;

public class AESUtil {

    private static final String ALGORITHM = "AES";
    private static final String PROP_FILE = "config.properties";
    private static byte[] KEY;  // 키 값을 저장할 변수

    // 키를 초기화하는 정적 블록
    static {
        Properties prop = new Properties();
        try {
            prop.load(new FileInputStream(PROP_FILE));
            String keyStr = prop.getProperty("aes.secretKey");
            KEY = Base64.getDecoder().decode(keyStr); // Base64 인코딩된 키를 디코딩하여 바이트 배열로 변환
        } catch (Exception e) {
            e.printStackTrace();
            KEY = "MySuperSecretKey".getBytes(); // 파일 로드 실패 시 기본 키 사용 (실제 사용 시 제거 필요)
        }
    }

    public static String encrypt(String value) throws Exception {
        SecretKeySpec secretKeySpec = new SecretKeySpec(KEY, ALGORITHM);
        Cipher cipher = Cipher.getInstance(ALGORITHM);
        cipher.init(Cipher.ENCRYPT_MODE, secretKeySpec);
        byte[] encryptedValue = cipher.doFinal(value.getBytes());
        return Base64.getEncoder().encodeToString(encryptedValue);
    }

    public static String decrypt(String encryptedValue) throws Exception {
        SecretKeySpec secretKeySpec = new SecretKeySpec(KEY, ALGORITHM);
        Cipher cipher = Cipher.getInstance(ALGORITHM);
        cipher.init(Cipher.DECRYPT_MODE, secretKeySpec);
        byte[] decodedBytes = Base64.getDecoder().decode(encryptedValue);
        byte[] decryptedValue = cipher.doFinal(decodedBytes);
        return new String(decryptedValue);
    }
}
