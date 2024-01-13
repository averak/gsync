package net.averak.gsync.adapter.dao.mapper.extend;

import java.util.List;

import javax.annotation.Nonnull;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import net.averak.gsync.adapter.dao.dto.base.PlayerStorageEntryDto;
import net.averak.gsync.adapter.dao.mapper.base.PlayerStorageEntryBaseMapper;

@Mapper
public interface PlayerStorageEntryMapper extends PlayerStorageEntryBaseMapper {

	@Nonnull
	List<PlayerStorageEntryDto> selectByPlayerIdAndGameId(@Param("playerId") String playerId,
			@Param("gameId") String gameId, @Param("exactKeyMatch") List<String> exactKeyMatch,
			@Param("forwardKeyMatch") List<String> forwardKeyMatch);
}
