package com.instrantes.pojo;

public class PsRole {
    private Integer roleId;
    private String roleName;
    private PsUser psUser;

    public PsUser getPsUser() {
        return psUser;
    }

    public void setPsUser(PsUser psUser) {
        this.psUser = psUser;
    }

    public PsRole() {
        super();
    }

    public PsRole(Integer roleId, String roleName) {
        super();
        this.roleId = roleId;
        this.roleName = roleName;
    }

    public Integer getRoleId() {
        return this.roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    public String getRoleName() {
        return this.roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

}
