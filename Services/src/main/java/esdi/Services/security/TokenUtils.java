//package esdi.Services.security;
//
//import io.jsonwebtoken.Claims;
//import io.jsonwebtoken.JwtException;
//import io.jsonwebtoken.Jwts;
//import io.jsonwebtoken.security.Keys;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import java.util.Collections;
//import java.util.Date;
//import java.util.HashMap;
//import java.util.Map;
//
//public class TokenUtils {
//
//    private final static String ACCESS_TOKEN_SECRET = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9";
//    private final static Long ACCESS_TOKEN_VALIDITY_SECONDS = 2_592_000L; //30 dias
//
//    public static String createToken(String nombre, String userName){
//        long experationTime = ACCESS_TOKEN_VALIDITY_SECONDS * 1_000;
//        Date expirationDate = new Date(System.currentTimeMillis() + experationTime);
//
//        Map<String, Object> extra = new HashMap<>();
//        extra.put("Nombre de Usuario", userName);
//
//        return Jwts.builder()
//                .setSubject(userName)
//                .setExpiration(expirationDate)
//                .setClaims(extra)
//                .signWith(Keys.hmacShaKeyFor(ACCESS_TOKEN_SECRET.getBytes()))
//                .compact();
//    }
//
//    public static UsernamePasswordAuthenticationToken getAuthentication(String token){
//        try{
//            Claims claims = Jwts.parserBuilder()
//                    .setSigningKey(ACCESS_TOKEN_SECRET.getBytes())
//                    .build()
//                    .parseClaimsJws(token)
//                    .getBody();
//
//            String userName = claims.getSubject();
//            return new UsernamePasswordAuthenticationToken(userName, null, Collections.emptyList());
//        }
//        catch (JwtException e){
//            return null;
//        }
//    }
//
//}

