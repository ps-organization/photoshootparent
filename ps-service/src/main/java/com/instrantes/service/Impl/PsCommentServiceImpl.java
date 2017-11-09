package com.instrantes.service.Impl;
import java.util.List;
import com.instrantes.dao.PsCommentDao;
import com.instrantes.pojo.PsComment;
import com.instrantes.service.PsCommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
@Service
public class PsCommentServiceImpl implements PsCommentService{
    @Autowired
    private PsCommentDao psCommentDao;
    @Override
    public long getPsCommentRowCount(){
        return psCommentDao.getPsCommentRowCount();
    }
    @Override
    public List<PsComment> selectPsComment(){
        return psCommentDao.selectPsComment();
    }
    @Override
    public PsComment selectPsCommentByObj(PsComment obj){
        return psCommentDao.selectPsCommentByObj(obj);
    }
    @Override
    public PsComment selectPsCommentById(Object id){
        return psCommentDao.selectPsCommentById(id);
    }
    @Override
    public int insertPsComment(PsComment value){
        return psCommentDao.insertPsComment(value);
    }
    @Override
    public int insertNonEmptyPsComment(PsComment value){
        return psCommentDao.insertNonEmptyPsComment(value);
    }
    @Override
    public int insertPsCommentByBatch(List<PsComment> value){
        return psCommentDao.insertPsCommentByBatch(value);
    }
    @Override
    public int deletePsCommentById(Object id){
        return psCommentDao.deletePsCommentById(id);
    }
    @Override
    public int updatePsCommentById(PsComment enti){
        return psCommentDao.updatePsCommentById(enti);
    }
    @Override
    public int updateNonEmptyPsCommentById(PsComment enti){
        return psCommentDao.updateNonEmptyPsCommentById(enti);
    }

    public PsCommentDao getPsCommentDao() {
        return this.psCommentDao;
    }

    public void setPsCommentDao(PsCommentDao psCommentDao) {
        this.psCommentDao = psCommentDao;
    }

}