package com.maxmall.common.security.authorize;

import com.maxmall.common.security.SecurityUser;
import com.maxmall.common.security.authorize.demain.AuthorAccessToken;
import com.maxmall.common.util.JacksonUtil;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.io.IOException;
import java.util.*;

/**
 * JwtToken生成的工具类
 * JWT token的格式：header.payload.signature
 * header的格式（算法、token的类型）：
 * {"alg": "HS512","typ": "JWT"}
 * payload的格式（用户名、创建时间、生成时间）：
 * {"sub":"wang","created":1489079981393,"exp":1489684781}
 * signature的生成算法：
 * HMACSHA512(base64UrlEncode(header) + "." +base64UrlEncode(payload),secret)
 * Created by maxmall on 2018/4/26.
 */
public class JwtTokenManager {
    //
    public static final String ROLE_REFRESH_TOKEN = "ROLE_REFRESH_TOKEN";

    private static final String CLAIM_KEY_USER_ID = "userId";
    private static final String CLAIM_KEY_USER_NAME = "userName";
    private static final String CLAIM_KEY_LOGIN_NAME = "loginName";
    private static final String CLAIM_KEY_LOGIN_PASSWORD = "loginPwd";
    private static final String CLAIM_KEY_USER_STATUS = "status";

    private static final String CLAIM_KEY_IS_MASTER = "isMaster";
    private static final String CLAIM_KEY_MERCHANT_ID = "merchantId";
    private static final String CLAIM_KEY_MERCHANT_NAME = "merchantName";
    private static final String CLAIM_KEY_MERCHANT_SN = "merchantSn";

    private static final String CLAIM_KEY_AUTHORITIES = "scope";
    private static final String CLAIM_KEY_USER_ACCOUNT = "sub";
    private static final String CLAIM_KEY_CREATED = "created";
    //签名方式
    private final SignatureAlgorithm SIGNATURE_ALGORITHM = SignatureAlgorithm.HS256;

    public static final String BEARER_TOKEN_TYPE = "Basic ";

    //密匙
    @Value("${jwt.secret}")
    private String secret;

    @Value("${jwt.access_token_expiration}")
    private Long access_token_expiration;

    @Value("${jwt.refresh_token_expiration}")
    private Long refresh_token_expiration;

    /**
     * 根据token 获取用户信息
     * @param token
     * @return
     */
    public SecurityUser getUserFromToken(String token) {
        SecurityUser  securityUser = null;
        try {
            final Claims claims = getClaimsFromToken(token);
            Long userId = getUserIdFromToken(token);
            String loginName = (String) claims.get(CLAIM_KEY_LOGIN_NAME);
            String nickName = (String) claims.get(CLAIM_KEY_USER_NAME);
            String loginPwd = (String) claims.get(CLAIM_KEY_LOGIN_PASSWORD);
            Integer status = (Integer) claims.get(CLAIM_KEY_USER_STATUS);


            Boolean isMaster = (Boolean) claims.get(CLAIM_KEY_IS_MASTER);
            Long merchantId = (Long) claims.get(CLAIM_KEY_MERCHANT_ID);
            String merchantName = (String) claims.get(CLAIM_KEY_MERCHANT_NAME);
            String merchantSn = (String) claims.get(CLAIM_KEY_MERCHANT_SN);

            List<?> roles = (List<?>) claims.get(CLAIM_KEY_AUTHORITIES);
            Collection<? extends GrantedAuthority> authorities = parseArrayToAuthorities(roles);

            securityUser = new SecurityUser(userId,loginName,loginPwd,nickName,status);
            securityUser.setIsMaster(isMaster);
            securityUser.setMerchantId(merchantId);
            securityUser.setMerchantName(merchantName);
            securityUser.setMerchantSn(merchantSn);
        } catch (Exception e) {
            securityUser = null;
        }
        return securityUser;
    }

    /**
     * 根据token 获取用户ID
     * @param token
     * @return
     */
    public long getUserIdFromToken(String token) {
        long userId;
        try {
            final Claims claims = getClaimsFromToken(token);
            userId = Long.valueOf(claims != null ? claims.get(CLAIM_KEY_USER_ID).toString() :"0");
        } catch (Exception e) {
            e.printStackTrace();
            userId = 0;
        }
        return userId;
    }

    /**
     * 根据token 获取用户名
     * @param token
     * @return
     */
    public String getUsernameFromToken(String token) {
        String username;
        try {
            final Claims claims = getClaimsFromToken(token);
            username = claims.getSubject();
        } catch (Exception e) {
            username = null;
        }
        return username;
    }

    /**
     * 根据token 获取生成时间
     * @param token
     * @return
     */
    public Date getCreatedDateFromToken(String token) {
        Date created;
        try {
            final Claims claims = getClaimsFromToken(token);
            created = claims.getIssuedAt();
        } catch (Exception e) {
            created = null;
        }
        return created;
    }

    /**
     * 根据token 获取过期时间
     * @param token
     * @return
     */
    public Date getExpirationDateFromToken(String token) {
        Date expiration;
        try {
            final Claims claims = getClaimsFromToken(token);
            expiration = claims.getExpiration();
        } catch (Exception e) {
            expiration = null;
        }
        return expiration;
    }

    /***
     * 解析token 信息
     * @param token
     * @return
     */
    private Claims getClaimsFromToken(String token) {
        Claims claims;
        try {
            claims = Jwts.parser()
                    .setSigningKey(secret)   //签名的key
                    .parseClaimsJws(token)   // 签名token
                    .getBody();
        } catch (Exception e) {
            claims = null;
        }
        return claims;
    }

    /**
     * 生成失效时间
     * @param expiration
     * @return
     */
    private Date generateExpirationDate(long expiration) {
        return new Date(System.currentTimeMillis() + expiration * 1000);
    }

    /**
     * token 是否过期
     * @param token
     * @return
     */
    private Boolean isTokenExpired(String token) {
        final Date expiration = getExpirationDateFromToken(token);
        return expiration.before(new Date());
    }

    /**
     * 生成时间是否在最后修改时间之前
     * @param created   生成时间
     * @param lastPasswordReset  最后修改密码时间
     * @return
     */
    private Boolean isCreatedBeforeLastPasswordReset(Date created, Date lastPasswordReset) {
        return (lastPasswordReset != null && created.before(lastPasswordReset));
    }

    /**
     * 根据用户信息 生成token
     * @param userDetails
     * @return
     */
    public AuthorAccessToken generateAccessToken(UserDetails userDetails) {
        SecurityUser user = (SecurityUser) userDetails;
        Map<String, Object> claims = generateClaims(user);
        claims.put(CLAIM_KEY_AUTHORITIES, authoritiesToArray(user.getAuthorities()));
        String tokenStr = generateAccessToken(user.getUsername(), claims);

        AuthorAccessToken token = new AuthorAccessToken();
        String access_token = JwtTokenManager.BEARER_TOKEN_TYPE+tokenStr;
        String refresh_token = JwtTokenManager.BEARER_TOKEN_TYPE+this.refreshToken(tokenStr);

        token.setAccess_token(access_token);
        token.setRefresh_token(refresh_token);
        token.setUserName(user.getNickName());

        return token;
    }

    /**
     * 重置(更新)token 过期时间
     * @param token
     * @param expiration
     */
    public String restTokenExpired(String token,long expiration){

        final Claims claims = getClaimsFromToken(token);
        Jwts.builder()
                .setClaims(claims)   //一个map 可以资源存放东西进去
                .setSubject(claims.getSubject()) //  用户名写入标题
                .setExpiration(new Date(expiration));
        //claims.setExpiration(new Date(expiration));
        // String refreshedToken = generateAccessToken(claims.getSubject(), claims,expiration);
        return "";
    }

    private Map<String, Object> generateClaims(SecurityUser user) {
        Map<String, Object> claims = new HashMap<>();
        claims.put(CLAIM_KEY_USER_ID, user.getUserId());
        claims.put(CLAIM_KEY_USER_NAME, user.getNickName());
        claims.put(CLAIM_KEY_LOGIN_NAME, user.getLoginName());
        claims.put(CLAIM_KEY_LOGIN_PASSWORD, user.getLoginPwd());
        claims.put(CLAIM_KEY_USER_STATUS, user.getStatus());
        claims.put(CLAIM_KEY_USER_ACCOUNT,user.getLoginName());
        claims.put(CLAIM_KEY_CREATED,new Date());

        return claims;
    }

    /**
     * 生成token
     * @param subject  用户名
     * @param claims
     * @return
     */
    private String generateAccessToken(String subject, Map<String, Object> claims) {
        return generateToken(subject, claims, access_token_expiration);
    }


    /**
     * 生成token
     * @param subject  用户名
     * @param claims
     * @return
     */
    private String generateAccessToken(String subject, Map<String, Object> claims,long expiration) {
        return generateToken(subject, claims, expiration);
    }

    /**
     * 用户所拥有的资源权限
     * @param authorities
     * @return
     */
    private List<?> authoritiesToArray(Collection<? extends GrantedAuthority> authorities) {
        List<String> list = new ArrayList<>();
        for (GrantedAuthority ga : authorities) {
            list.add(ga.getAuthority());
        }
        return list;
    }

    private Collection<? extends GrantedAuthority> parseArrayToAuthorities(List<?> roles) {
        Collection<GrantedAuthority> authorities = new ArrayList<>();
        SimpleGrantedAuthority authority;
        for (Object role : roles) {
            authority = new SimpleGrantedAuthority(role.toString());
            authorities.add(authority);
        }
        return authorities;
    }

    /**
     * 根据用户信息 重新获取token
     * @param userDetails
     * @return
     */
    public String generateRefreshToken(UserDetails userDetails) throws IOException {
        SecurityUser user = (SecurityUser) userDetails;
        Map<String, Object> claims = generateClaims(user);
        // 只授于更新 token 的权限
        String roles[] = new String[]{ROLE_REFRESH_TOKEN};
        claims.put(CLAIM_KEY_AUTHORITIES, JacksonUtil.toJson(roles));
        return generateRefreshToken(user.getUsername(), claims);
    }

    /**
     * 重新获取token
     * @param subject 用户名
     * @param claims
     * @return
     */
    private String generateRefreshToken(String subject, Map<String, Object> claims) {
        return generateToken(subject, claims, refresh_token_expiration);
    }

    public Boolean canTokenBeRefreshed(String token, Date lastPasswordReset) {
        final Date created = getCreatedDateFromToken(token);
        return !isCreatedBeforeLastPasswordReset(created, lastPasswordReset)
                && (!isTokenExpired(token));
    }

    /**
     * 刷新重新获取token
     * @param token 源token
     * @return
     */
    public String refreshToken(String token) {
        String refreshedToken;
        try {
            final Claims claims = getClaimsFromToken(token);
            refreshedToken = generateAccessToken(claims.getSubject(), claims);
        } catch (Exception e) {
            refreshedToken = null;
        }
        return refreshedToken;
    }

    /**
     * 删除token
     * @param access_token 源token
     * @return
     */
    public void removeToken(String access_token){
        String token = StringUtils.substringAfter(access_token, JwtTokenManager.BEARER_TOKEN_TYPE);
        final Claims claims = getClaimsFromToken(token);
        if (claims != null){
            claims.remove(token);
        }
    }

    private String generateToken(String subject, Map<String, Object> claims, long expiration) {
        return Jwts.builder()
                .setClaims(claims)   //一个map 可以资源存放东西进去
                .setSubject(subject) //  用户名写入标题
                .setId(UUID.randomUUID().toString())
                .setIssuedAt(new Date())
                .setExpiration(generateExpirationDate(expiration))  //过期时间
                .signWith(SIGNATURE_ALGORITHM, secret) //数字签名
                .compact();
    }

    /**
     * 验证token 是否合法
     * @param token  token
     * @param userDetails  用户信息
     * @return
     */
    public Boolean validateToken(String token, UserDetails userDetails) {
        SecurityUser user = (SecurityUser) userDetails;
        final long userId = getUserIdFromToken(token);
        final String username = getUsernameFromToken(token);
        return (userId == user.getUserId()
                && username.equals(user.getUsername())
                && !isTokenExpired(token)
        );
    }
}
