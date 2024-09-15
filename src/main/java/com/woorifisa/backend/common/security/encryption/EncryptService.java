package com.woorifisa.backend.common.security.encryption;

import org.springframework.security.crypto.encrypt.AesBytesEncryptor;
import java.nio.charset.StandardCharsets;
import java.nio.ByteBuffer;
import lombok.RequiredArgsConstructor;


@RequiredArgsConstructor
public class EncryptService {
    private final AesBytesEncryptor encryptor;

	// 암호화
    public String encryptEmail(String email) {
        byte[] encrypt = encryptor.encrypt(email.getBytes(StandardCharsets.UTF_8));
        return byteArrayToString(encrypt);
    }
	
    // 복호화
    public String decryptEmail(String encryptString) {
        byte[] decryptBytes = stringToByteArray(encryptString);
        byte[] decrypt = encryptor.decrypt(decryptBytes);
        return new String(decrypt, StandardCharsets.UTF_8);
    }

	// byte -> String
    public String byteArrayToString(byte[] bytes) {
        StringBuilder sb = new StringBuilder();
        for (byte abyte : bytes) {
            sb.append(abyte);
            sb.append(" ");
        }
        return sb.toString();
    }

	// String -> byte
    public byte[] stringToByteArray(String byteString) {
        String[] split = byteString.split("\\s");
        ByteBuffer buffer = ByteBuffer.allocate(split.length);
        for (String s : split) {
            buffer.put((byte) Integer.parseInt(s));
        }
        return buffer.array();
    }
}
