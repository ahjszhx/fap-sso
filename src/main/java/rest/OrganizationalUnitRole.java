package rest;





public class OrganizationalUnitRole {
    private OrganizationalUnit organizationalUnit;
    private String roleId;
    private FapRoleInfo role;

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
