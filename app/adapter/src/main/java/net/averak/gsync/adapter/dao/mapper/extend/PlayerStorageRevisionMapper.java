package net.averak.gsync.adapter.dao.mapper.extend;

import java.util.List;

import javax.annotation.Nonnull;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import net.averak.gsync.adapter.dao.dto.base.PlayerStorageRevisionDto;
import net.averak.gsync.adapter.dao.mapper.base.PlayerStorageRevisionBaseMapper;

@Mapper
public interface PlayerStorageRevisionMapper extends PlayerStorageRevisionBaseMapper {

	@Nonnull
	List<PlayerStorageRevisionDto> selectByPlayerIdAndGameIdOrderByCreatedAt(@Param("playerId") String playerId,
			@Param("gameId") String gameId, @Param("limit") int limit);

}
