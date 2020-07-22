package rest;


import org.springframework.beans.BeanUtils;

import java.util.LinkedList;
import java.util.List;


public class FapUserInfoQo extends FapUserInfo{
    private List<OrganizationalUnitRole> roles;

    public void transformUserInfo(FapUserInfo userInfo) {
        List<OrganizationalUnitRole> roles = new LinkedList<OrganizationalUnitRole>();
        if(userInfo.getOrganizationalUnitRoleDefault()!=null){
            roles.add(userInfo.getOrganizationalUnitRoleDefault());
        }
        if(userInfo.getOrganizationalUnitRoleExtend()!=null){
            roles.addAll(userInfo.getOrganizationalUnitRoleExtend());
        }
        if(userInfo.getOrganizationalUnitRoleOther()!=null){
            roles.addAll(userInfo.getOrganizationalUnitRoleOther());
        }
        BeanUtils.copyProperties(userInfo,this,"organizationalUnitRoleExtend","organizationalUnitRoleOther");
        this.setRoles(roles);
    }

    public List<OrganizationalUnitRole> getRoles() {
        return roles;
    }

    public void setRoles(List<OrganizationalUnitRole> roles) {
        this.roles = roles;
    }
}
