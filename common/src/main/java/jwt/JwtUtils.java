package jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;
import java.security.Key;
import java.util.Date;


/**
 * @author xingguoqing
 * @date 2018/2/14 上午12:48
 */
public class JwtUtils {

    private static final Logger LOGGER = LoggerFactory.getLogger(JwtUtils.class);


    public static String createJwt(Long userId) {
        SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;

        long nowMillis = System.currentTimeMillis();
        Date now = new Date(nowMillis);

        byte[] apiKeySecretBytes = DatatypeConverter.parseBase64Binary("123456");
        Key signingKey = new SecretKeySpec(apiKeySecretBytes, signatureAlgorithm.getJcaName());

        //Let's set the JWT Claims
        JwtBuilder builder = Jwts.builder().setId(userId.toString())
                .setIssuedAt(now)
                .setSubject("sub")
                .setIssuer("issuer")
                .signWith(signatureAlgorithm, signingKey);
        return builder.compact();
    }

    public static Long parseJWT(String jwt) {
        Claims claims = Jwts.parser()
                .setSigningKey(DatatypeConverter.parseBase64Binary("123456"))
                .parseClaimsJws(jwt).getBody();
        return Long.valueOf(claims.getId());
    }

    public static void main(String[] args) {
//        JwtUtils.
    }
}
