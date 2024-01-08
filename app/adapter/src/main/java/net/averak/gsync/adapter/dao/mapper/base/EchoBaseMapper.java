package net.averak.gsync.adapter.dao.mapper.base;

import java.util.List;
import net.averak.gsync.adapter.dao.entity.base.EchoEntity;
import net.averak.gsync.adapter.dao.entity.base.EchoExample;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

@Mapper
public interface EchoBaseMapper {
	/**
	 * This method was generated by MyBatis Generator. This method corresponds to
	 * the database table gsync_echo
	 *
	 * @mbg.generated
	 */
	long countByExample(EchoExample example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to
	 * the database table gsync_echo
	 *
	 * @mbg.generated
	 */
	int deleteByExample(EchoExample example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to
	 * the database table gsync_echo
	 *
	 * @mbg.generated
	 */
	int deleteByPrimaryKey(String echoId);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to
	 * the database table gsync_echo
	 *
	 * @mbg.generated
	 */
	int insert(EchoEntity row);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to
	 * the database table gsync_echo
	 *
	 * @mbg.generated
	 */
	int insertSelective(EchoEntity row);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to
	 * the database table gsync_echo
	 *
	 * @mbg.generated
	 */
	List<EchoEntity> selectByExampleWithRowbounds(EchoExample example, RowBounds rowBounds);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to
	 * the database table gsync_echo
	 *
	 * @mbg.generated
	 */
	List<EchoEntity> selectByExample(EchoExample example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to
	 * the database table gsync_echo
	 *
	 * @mbg.generated
	 */
	EchoEntity selectByPrimaryKey(String echoId);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to
	 * the database table gsync_echo
	 *
	 * @mbg.generated
	 */
	int updateByExampleSelective(@Param("row") EchoEntity row, @Param("example") EchoExample example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to
	 * the database table gsync_echo
	 *
	 * @mbg.generated
	 */
	int updateByExample(@Param("row") EchoEntity row, @Param("example") EchoExample example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to
	 * the database table gsync_echo
	 *
	 * @mbg.generated
	 */
	int updateByPrimaryKeySelective(EchoEntity row);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to
	 * the database table gsync_echo
	 *
	 * @mbg.generated
	 */
	int updateByPrimaryKey(EchoEntity row);
}