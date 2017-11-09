package com.instrantes.service;
import java.util.List;
import com.instrantes.pojo.PsComment;
public interface PsCommentService{
	/**
	 * 获得PsComment数据的总行数
	 * @return
	 */
    long getPsCommentRowCount();
	/**
	 * 获得PsComment数据集合
	 * @return
	 */
    List<PsComment> selectPsComment();
	/**
	 * 获得一个PsComment对象,以参数PsComment对象中不为空的属性作为条件进行查询
	 * @param obj
	 * @return
	 */
    PsComment selectPsCommentByObj(PsComment obj);
	/**
	 * 通过PsComment的id获得PsComment对象
	 * @param id
	 * @return
	 */
    PsComment selectPsCommentById(Object id);
	/**
	 * 插入PsComment到数据库,包括null值
	 * @param value
	 * @return
	 */
    int insertPsComment(PsComment value);
	/**
	 * 插入PsComment中属性值不为null的数据到数据库
	 * @param value
	 * @return
	 */
    int insertNonEmptyPsComment(PsComment value);
	/**
	 * 批量插入PsComment到数据库
	 * @param value
	 * @return
	 */
    int insertPsCommentByBatch(List<PsComment> value);
	/**
	 * 通过PsComment的id删除PsComment
	 * @param id
	 * @return
	 */
    int deletePsCommentById(Object id);
	/**
	 * 通过PsComment的id更新PsComment中的数据,包括null值
	 * @param enti
	 * @return
	 */
    int updatePsCommentById(PsComment enti);
	/**
	 * 通过PsComment的id更新PsComment中属性不为null的数据
	 * @param enti
	 * @return
	 */
    int updateNonEmptyPsCommentById(PsComment enti);
}