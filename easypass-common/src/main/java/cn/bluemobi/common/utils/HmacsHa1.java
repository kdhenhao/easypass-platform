package cn.bluemobi.common.utils;

import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

/**
 * Description: <br/>
 * Date: 2016年3月25日 下午2:59:11 <br/>
 * 
 * @author hut
 * @version
 * @see
 */
public class HmacsHa1 {

    private static final String HMAC_SHA1 = "HmacSHA1";

    /**
     * 生成签名数据
     * 
     * @param data 待加密的数据
     * @param key 加密使用的key
     * @return 生成MD5编码的字符串
     * @throws InvalidKeyException
     * @throws NoSuchAlgorithmException
     * @throws UnsupportedEncodingException
     */
    public static String getSignature(String dataStr, String keyStr)
            throws InvalidKeyException, NoSuchAlgorithmException, UnsupportedEncodingException {
        byte[] data = dataStr.getBytes("UTF-8");
        byte[] key = keyStr.getBytes("UTF-8");
        SecretKeySpec signingKey = new SecretKeySpec(key, HMAC_SHA1);
        Mac mac = Mac.getInstance(HMAC_SHA1);
        mac.init(signingKey);
        byte[] rawHmac = mac.doFinal(data);
        return MD5Tools.encode(new String(rawHmac, "UTF-8"));
    }
}
