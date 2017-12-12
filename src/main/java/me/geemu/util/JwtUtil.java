package me.geemu.util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import me.geemu.config.JwtConfig;

import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;
import java.security.Key;
import java.util.Date;

/**
 * @Author: Geemu
 * Email:cfmmail@sina.com
 * Date: 2017/12/7  10:36
 * Description: jwt工具类
 */
public class JwtUtil {

    /**
     * 生成JWT字符串
     *
     * @param userId
     * @return
     */
    public static String createJWT(Long userId, JwtConfig jwtConfig) {
        SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;
        /**
         * 生成签名密匙
         */
        byte[] apiKeySecretBytes = DatatypeConverter.parseBase64Binary(jwtConfig.getBase64Secret());
        Key signingKey = new SecretKeySpec(apiKeySecretBytes, signatureAlgorithm.getJcaName());
        /**
         * 添加构成JWT的参数
         */
        JwtBuilder builder = Jwts.builder().setHeaderParam("type", "JWT")
                .claim("userId", userId)
                .setIssuer(jwtConfig.getClientId())
                .setAudience(jwtConfig.getName())
                .signWith(signatureAlgorithm, signingKey);
        /**
         * 添加token过期时间
         */
        if (jwtConfig.getExpiresSecond() >= 0) {
            long nowMills = System.currentTimeMillis();
            Date now = new Date(nowMills);
            long expMills = nowMills + jwtConfig.getExpiresSecond() * 1000;
            Date exp = new Date(expMills);
            System.out.println(now.getTime());
            System.out.println(exp.getTime());
            builder.setExpiration(exp).setNotBefore(now);
        }
        return builder.compact();

    }

    /**
     * 解析JWT密文Token
     *
     * @param jsonWebToken
     * @param base64Security
     * @return
     */
    public static Claims parseJWT(String jsonWebToken, String base64Security) throws Exception {
        Claims claims = Jwts
                .parser()
                .setSigningKey(DatatypeConverter.parseBase64Binary(base64Security))
                .parseClaimsJws(jsonWebToken)
                .getBody();
        return claims;
    }


}
