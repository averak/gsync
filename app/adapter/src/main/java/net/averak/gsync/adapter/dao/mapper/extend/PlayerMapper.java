package net.averak.gsync.adapter.dao.mapper.extend;

import net.averak.gsync.adapter.dao.dto.extend.PlayerILDto;
import net.averak.gsync.adapter.dao.mapper.base.PlayerBaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import javax.annotation.Nullable;

@Mapper
public interface PlayerMapper extends PlayerBaseMapper {

	@Nullable
	PlayerILDto selectByPlayerId(@Param("playerId") String playerId);
}
