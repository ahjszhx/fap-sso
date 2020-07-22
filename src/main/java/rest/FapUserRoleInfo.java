package rest;




public class FapUserRoleInfo {

    private String userId;

    private FapUserInfo user;

    private String organizationalUnitRoleRelationId;

    private String organizationalUnitId;

    private OrganizationalUnit organizationalUnit;

    private String roleId;

    private FapRoleInfo role;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public FapUserInfo getUser() {
        return user;
    }

    public void setUser(FapUserInfo user) {
        this.user = user;
    }

    public String getOrganizationalUnitRoleRelationId() {
        return organizationalUnitRoleRelationId;
    }

    public void setOrganizationalUnitRoleRelationId(String organizationalUnitRoleRelationId) {
        this.organizationalUnitRoleRelationId = organizationalUnitRoleRelationId;
    }

    public String getOrganizationalUnitId() {
        return organizationalUnitId;
    }

    public void setOrganizationalUnitId(String organizationalUnitId) {
        this.organizationalUnitId = organizationalUnitId;
    }

    public OrganizationalUnit getOrganizationalUnit() {
        return organizationalUnit;
    }

    public void setOrganizationalUnit(OrganizationalUnit organizationalUnit) {
        this.organizationalUnit = organizationalUnit;
    }

    public String getRoleId() {
        return roleId;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }

    public FapRoleInfo getRole() {
        return role;
    }

    public void setRole(FapRoleInfo role) {
        this.role = role;
    }
}
