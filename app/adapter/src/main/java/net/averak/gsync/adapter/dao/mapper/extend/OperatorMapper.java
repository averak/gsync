package net.averak.gsync.adapter.dao.mapper.extend;

import net.averak.gsync.adapter.dao.dto.extend.OperatorILDto;
import net.averak.gsync.adapter.dao.mapper.base.OperatorBaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.List;

@Mapper
public interface OperatorMapper extends OperatorBaseMapper {

	@Nullable
	OperatorILDto selectByOperatorId(@Param("operatorId") String operatorId);

	@Nonnull
	List<OperatorILDto> selectByGameId(@Param("gameId") String gameId);
}
