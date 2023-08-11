package com.wechat.template.tool;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
/**
 * @author 张腾飞(zhangtf)
 * @date 2023/8/5 2:59
 * @description <p> PasswordHashingExample。</p>
 */

public class PasswordHashingExample {

    // 使用 SHA-224 哈希算法计算密码的哈希值
    public static String hashPassword(final String password) {
        try {
            final MessageDigest digest = MessageDigest.getInstance("SHA-224");
            final byte[] hashBytes = digest.digest(password.getBytes(StandardCharsets.UTF_8));

            final StringBuilder hexHash = new StringBuilder();
            for (byte b : hashBytes) {
                hexHash.append(String.format("%02x", b));
            }

            return hexHash.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return null;
    }

    // 将字符串进行 Base64 编码
    public static String base64Encode(final String input) {
        return Base64.getEncoder().encodeToString(input.getBytes(StandardCharsets.UTF_8));
    }
}
