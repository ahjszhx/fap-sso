package rest;


import com.google.common.base.Strings;
import retrofit2.Response;

import java.util.List;


public class BodyResult {

    private String error;

    private FapUserInfo result;

    private List<FapUserRoleInfo> results;

    private Integer totolRows;


    public BodyResult getBodyResult(Response response) throws FapRequestException {
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

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public FapUserInfo getResult() {
        return result;
    }

    public void setResult(FapUserInfo result) {
        this.result = result;
    }

    public List<FapUserRoleInfo> getResults() {
        return results;
    }

    public void setResults(List<FapUserRoleInfo> results) {
        this.results = results;
    }

    public Integer getTotolRows() {
        return totolRows;
    }

    public void setTotolRows(Integer totolRows) {
        this.totolRows = totolRows;
    }
}
