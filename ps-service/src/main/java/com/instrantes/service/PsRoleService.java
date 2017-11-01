package com.instrantes.service;
import com.instrantes.pojo.PsRole;

import java.util.List;

public interface PsRoleService{

    PsRole selectUserRoleById(Integer id);

    long getPsRoleRowCount();
    List<PsRole> selectPsRole();
    PsRole selectPsRoleById(Integer id);
    int insertPsRole(PsRole value);
    int insertNonEmptyPsRole(PsRole value);
    int deletePsRoleById(Integer id);
    int updatePsRoleById(PsRole enti);
    int updateNonEmptyPsRoleById(PsRole enti);
}