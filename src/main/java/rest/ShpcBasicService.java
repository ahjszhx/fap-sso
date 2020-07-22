package rest;

import retrofit2.Call;
import retrofit2.http.*;


public interface ShpcBasicService {


    @GET("Users/GetUser")
    Call<BodyResult> getUserById(@Header("Authorization") String authorization, @Query("id") String id);

    @GET("Users/GetUsersByOrganizationalUnitRoleRelation")
    Call<BodyResult> getUserRoleByUnitRole(@Header("Authorization") String authorization, @Query("organizationalUnitId") String orgId,
                                           @Query("roleId") String roleId, @Query("recursive") boolean recursive,
                                           @Query("isDefault") boolean isDefault, @Query("skip") Integer skip, @Query("take") Integer take);

    @GET("Users/GetUsersByDomainAndQuery")
    Call<BodyResult> getUserInfoByUserInfo(@Header("Authorization") String authorization, @Query("domainId") String domainId,
                                           @Query("query") String query, @Query("isDefault") boolean isDefault, @Query("skip") Integer skip, @Query("take") Integer take);

    //@Headers({"Content-type:application/json;charset=UTF-8"})
    @POST("connect/token")
    @FormUrlEncoded
    Call<RefreshInfo> getAccessToken(@Field("client_id") String client_id, @Field("client_secret") String client_secret,
                                     @Field("grant_type") String grant_type, @Field("refresh_token") String refresh_token);


}
