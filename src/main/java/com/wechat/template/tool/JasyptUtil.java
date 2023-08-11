package com.wechat.template.tool;

import org.jasypt.encryption.pbe.PooledPBEStringEncryptor;
import org.jasypt.encryption.pbe.StandardPBEByteEncryptor;
import org.jasypt.encryption.pbe.config.SimpleStringPBEConfig;

/**
 * @author 张腾飞(zhangtf)
 * @date 2023/8/2 13:40
 * @description <p> JasyptUtil。</p>
 */
public class JasyptUtil {

    private static final String ENCRYPTED_VALUE_PREFIX = "ENC(";
    private static final String ENCRYPTED_VALUE_SUFFIX = ")";
    private static final String PASS_WORD = "EbfYkitulv73I2p0mXI50JMXoaxZTKJ7";

    /**
     * 判断是否是 prefixes/suffixes 包裹的属性
     *
     * @param value
     * @return
     */
    public static boolean isEncryptedValue(final String value) {
        if (value == null) {
            return false;
        }
        final String trimmedValue = value.trim();
        return (trimmedValue.startsWith(ENCRYPTED_VALUE_PREFIX) &&
                trimmedValue.endsWith(ENCRYPTED_VALUE_SUFFIX));
    }

    /**
     * 如果通过 prefixes/suffixes 包裹的属性，那么返回密文的值；如果没有被包裹，返回原生的值。
     *
     * @param value
     * @return
     */
    private static String getInnerEncryptedValue(final String value) {
        return value.substring(
            ENCRYPTED_VALUE_PREFIX.length(),
            (value.length() - ENCRYPTED_VALUE_SUFFIX.length()));
    }



    /**
     * Jasypt生成加密结果
     *
     * @param value    待加密值
     * @return
     */
    public static String encryptPwd( String value) {
        PooledPBEStringEncryptor encryptOr = new PooledPBEStringEncryptor();
        encryptOr.setConfig(cryptOr());
        return String.format("ENC(%s)", encryptOr.encrypt(value));
    }

    /**
     * 解密
     *
     * @param value    待解密密文
     * @return
     */
    public static String decyptPwd(String value) {
        PooledPBEStringEncryptor encryptOr = new PooledPBEStringEncryptor();
        encryptOr.setConfig(cryptOr());
        return encryptOr.decrypt(isEncryptedValue(value) ? getInnerEncryptedValue(value) : value);
    }

    /**
     * @return
     */
    public static SimpleStringPBEConfig cryptOr() {
        SimpleStringPBEConfig config = new SimpleStringPBEConfig();
        config.setPassword(PASS_WORD);
        config.setAlgorithm(StandardPBEByteEncryptor.DEFAULT_ALGORITHM);
        config.setKeyObtentionIterations("1000");
        config.setPoolSize("1");
        config.setProviderName(null);
        config.setSaltGeneratorClassName("org.jasypt.salt.RandomSaltGenerator");
        config.setStringOutputType("base64");
        return config;
    }

    public static void main(String[] args) {
        System.out.println(encryptPwd("1234124"));
    }
}
