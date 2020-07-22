package rest;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;


@Order(1)
@Configuration
@EnableConfigurationProperties({FapApiConfig.class})
@ConfigurationProperties(prefix = "fapapi")
public class FapApiConfig {

    private String url;

    private String primaryManager;

    private String minorManager;

    private String secretary;

    private String domainId;

    private String testCount;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getPrimaryManager() {
        return primaryManager;
    }

    public void setPrimaryManager(String primaryManager) {
        this.primaryManager = primaryManager;
    }

    public String getMinorManager() {
        return minorManager;
    }

    public void setMinorManager(String minorManager) {
        this.minorManager = minorManager;
    }

    public String getSecretary() {
        return secretary;
    }

    public void setSecretary(String secretary) {
        this.secretary = secretary;
    }

    public String getDomainId() {
        return domainId;
    }

    public void setDomainId(String domainId) {
        this.domainId = domainId;
    }

    public String getTestCount() {
        return testCount;
    }

    public void setTestCount(String testCount) {
        this.testCount = testCount;
    }
}
