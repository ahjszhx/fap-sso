package rest;




public class RefreshTokenQo {

    private String accessTokenValue;

    private String accessExpireTime;

    private String refreshTokenValue;

    private String refreshExpireTime;

    public RefreshTokenQo(String accessTokenValue, String accessExpireTime, String refreshTokenValue, String refreshExpireTime) {
        this.accessTokenValue = accessTokenValue;
        this.accessExpireTime = accessExpireTime;
        this.refreshTokenValue = refreshTokenValue;
        this.refreshExpireTime = refreshExpireTime;
    }

    public RefreshTokenQo() {
    }

    public String getAccessTokenValue() {
        return accessTokenValue;
    }

    public void setAccessTokenValue(String accessTokenValue) {
        this.accessTokenValue = accessTokenValue;
    }

    public String getAccessExpireTime() {
        return accessExpireTime;
    }

    public void setAccessExpireTime(String accessExpireTime) {
        this.accessExpireTime = accessExpireTime;
    }

    public String getRefreshTokenValue() {
        return refreshTokenValue;
    }

    public void setRefreshTokenValue(String refreshTokenValue) {
        this.refreshTokenValue = refreshTokenValue;
    }

    public String getRefreshExpireTime() {
        return refreshExpireTime;
    }

    public void setRefreshExpireTime(String refreshExpireTime) {
        this.refreshExpireTime = refreshExpireTime;
    }
}
