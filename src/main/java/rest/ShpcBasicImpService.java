package rest;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.base.Strings;
import com.google.gson.Gson;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientService;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.core.OAuth2AccessToken;
import org.springframework.security.oauth2.core.OAuth2RefreshToken;
import org.springframework.security.oauth2.core.oidc.OidcIdToken;
import org.springframework.security.oauth2.core.oidc.user.DefaultOidcUser;
import org.springframework.stereotype.Service;
import retrofit2.Call;
import retrofit2.GsonConverterFactory;
import retrofit2.Response;
import retrofit2.Retrofit;


import java.io.IOException;
import java.security.Principal;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.List;


public class ShpcBasicImpService {

    @Autowired
    FapApiConfig fapApiConfig;

    @Autowired
    private OAuth2AuthorizedClientService clientService;

    private static String zone ="Asia/Shanghai";

    @Value("${spring.security.oauth2.client.registration.fap.client-id}")
    private String clientId;

    @Value("${spring.security.oauth2.client.registration.fap.client-secret}")
    private String secret;

    Retrofit retrofit1 = new Retrofit.Builder()
            .baseUrl("http://202.121.23.66/FAP.Api/").addConverterFactory(GsonConverterFactory.create(new Gson())).build();

    Retrofit retrofit = new Retrofit.Builder()
            .baseUrl("http://202.121.23.66/fap.identityserver/").addConverterFactory(GsonConverterFactory.create(new Gson())).build();

    ShpcBasicService service = retrofit.create(ShpcBasicService.class);

    ShpcBasicService service1 = retrofit1.create(ShpcBasicService.class);

    private static Logger logger = LoggerFactory.getLogger(ShpcBasicImpService.class);

    ObjectMapper objectMapper = new ObjectMapper();


    /**
     * 查询人员基本信息
     *
     * @param principal
     * @return
     * @throws IOException
     * @throws FapRequestException
     */

    public Result getUserInfoByUid(Principal principal) throws IOException, FapRequestException, NoSuchFieldException {
        oAuth2TokenDetaiInject(principal);
        OAuth2AuthenticationToken oauthToken = (OAuth2AuthenticationToken) principal;
        OidcIdToken token = ((DefaultOidcUser)oauthToken.getPrincipal()).getIdToken();
        String uid = token.getClaims().get("sub").toString();
        String accessToken = getAccessTokenValue(oauthToken);
        //String accessToken = client.getAccessToken().getTokenValue();
        Call<BodyResult> call = service1.
                getUserById(accessToken,uid);
        Response response= call.execute();
        BodyResult result = getBodyResult(response);
        FapUserInfo userInfo = result.getResult();
        FapUserInfoQo fapUserInfoQo = new FapUserInfoQo();
        fapUserInfoQo.transformUserInfo(userInfo);
        return Result.initResult().suc(fapUserInfoQo);
    }

    private String getAccessTokenValue(OAuth2AuthenticationToken oauthToken) {
        RefreshTokenQo qo = objectMapper.convertValue(oauthToken.getDetails(),RefreshTokenQo.class);
        return "Bearer "+qo.getAccessTokenValue();
    }

    /**
     *  查询部门下的相关岗位
     * @param principal
     * @param orgId
     * @param roleType
     * @return
     * @throws FapRequestException
     * @throws IOException
     */

    public List<FapUserRoleInfo> getUserRoleByUnitRole(Principal principal, String orgId, String roleType) throws FapRequestException, IOException {
        oAuth2TokenDetaiInject(principal);
        OAuth2AuthenticationToken oauthToken = (OAuth2AuthenticationToken) principal;
        String accessToken = getAccessTokenValue(oauthToken);
        String roleId = getRoleId(roleType);
        Call<BodyResult> call = service1.getUserRoleByUnitRole(accessToken,orgId,roleId,true,true,0,20);
        Response response= call.execute();
        BodyResult result = getBodyResult(response);
        return result.getResults();
    }

    public List<FapUserRoleInfo> getUserInfoByUserInfo(Principal principal, String query) throws FapRequestException, IOException {
        oAuth2TokenDetaiInject(principal);
        OAuth2AuthenticationToken oauthToken = (OAuth2AuthenticationToken) principal;
        String accessToken = getAccessTokenValue(oauthToken);
        Call<BodyResult> call = service1.getUserInfoByUserInfo(accessToken,fapApiConfig.getDomainId(),query,true,0,20);
        Response response= call.execute();
        BodyResult result = getBodyResult(response);
        return result.getResults();
    }


    public boolean userBelongToRole(String uid,List<FapUserRoleInfo> list){
        boolean valid = false;
        for(FapUserRoleInfo userRole:list){
            if(userRole.getUserId().equals(uid)){
                valid = true;
            }
        }
        return valid;
    }


    private String getRoleId(String roleType){
        String roleId = "";
        if(roleType.equals("primaryManager")){
            roleId = fapApiConfig.getPrimaryManager();
        }
        else if(roleType.equals("minorManager")){
            roleId = fapApiConfig.getMinorManager();
        }
        else if(roleType.equals("secretary")){
            roleId = fapApiConfig.getSecretary();
        }
        else if(roleType.equals("groupDiscussion")){
            roleId = fapApiConfig.getSecretary();
        }
        else if(roleType.equals("recheckManager")){
            roleId = fapApiConfig.getSecretary();
        }
        else if(roleType.equals("finalManager")){
            roleId = fapApiConfig.getSecretary();
        }
//        switch (roleType){
//            case "primaryManager":
//                roleId = fapApiConfig.getPrimaryManager();
//                break;
//            case "minorManager":
//                roleId = fapApiConfig.getMinorManager();
//                break;
//            case "secretary":
//                roleId = fapApiConfig.getSecretary();
//                break;
//                //todo 更新这三个岗位
//            case "groupDiscussion":
//                roleId = fapApiConfig.getSecretary();
//                break;
//            case "recheckManager":
//                roleId = fapApiConfig.getSecretary();
//                break;
//            case "finalManager":
//                roleId = fapApiConfig.getSecretary();
//                break;
//        }
        return roleId;
    }


    private BodyResult getBodyResult(Response response) throws FapRequestException {
        if(response.raw().code()==401)
        {
            throw new FapRequestException(100);
        }
        if(!response.isSuccessful()){
            throw new FapRequestException(100);
        }
        BodyResult result = (BodyResult) response.body();
        assert result != null;
        if(!Strings.isNullOrEmpty(result.getError())){
            throw new FapRequestException(200);
        }
        return result;
    }


    private String getAccessToken(OAuth2AuthenticationToken oauthToken){
        OAuth2AuthorizedClient client =
                clientService.loadAuthorizedClient(
                        oauthToken.getAuthorizedClientRegistrationId(),
                        oauthToken.getName());
        OAuth2AccessToken accessToken = client.getAccessToken();
        OAuth2RefreshToken refreshToken = client.getRefreshToken();
        String tokenStr = "Bearer "+accessToken.getTokenValue();
        logger.info("【accessToken】:{}",tokenStr);
        logger.info("【refreshToken】:{}",refreshToken.getTokenValue());
        return tokenStr;
    }

    public void oAuth2TokenDetaiInject(Principal principal) throws IOException {
        OAuth2AuthenticationToken oauthToken = (OAuth2AuthenticationToken) principal;
        OidcIdToken token = ((DefaultOidcUser)oauthToken.getPrincipal()).getIdToken();
        OAuth2AuthorizedClient client = clientService.loadAuthorizedClient(
                oauthToken.getAuthorizedClientRegistrationId(), oauthToken.getName());
        //判断detail是否为空 为空就存进去
        ObjectMapper objectMapper = new ObjectMapper();
        //RefreshTokenQo initQo = objectMapper.convertValue(oauthToken.getDetails(),RefreshTokenQo.class);
        Class cls = oauthToken.getDetails().getClass();
        if(!cls.getSimpleName().equals("RefreshTokenQo")){
            RefreshTokenQo tokenQo = new RefreshTokenQo(client.getAccessToken().getTokenValue(),LocalDateTime.now().toString(),client.getRefreshToken().getTokenValue(),LocalDateTime.now().toString());
            oauthToken.setDetails(tokenQo);
        }
        ZonedDateTime zonedDateTime = token.getExpiresAt().atZone(ZoneId.of(zone));
        boolean expire = zonedDateTime.toLocalDateTime().isBefore(LocalDateTime.now());
        RefreshInfo refreshInfo = new RefreshInfo();
        if(expire) {
            //从detail中取两个token
            RefreshTokenQo qo = objectMapper.convertValue(oauthToken.getDetails(),RefreshTokenQo.class);
            Call<RefreshInfo> accessCall = service.getAccessToken(clientId, secret, "refresh_token",qo.getRefreshTokenValue());
            Response aresponse = accessCall.execute();
            refreshInfo = (RefreshInfo) aresponse.body();
            //获取后再存进去
            oauthToken.setDetails(new RefreshTokenQo(refreshInfo.getAccess_token(),LocalDateTime.now().plusSeconds(3600).toString(),refreshInfo.getRefresh_token(),LocalDateTime.now().toString()));
        }
    }



}
