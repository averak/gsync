package net.averak.gsync.adapter.dao.mapper.extend;

import net.averak.gsync.adapter.dao.dto.base.PlayerStorageEntryDto;
import net.averak.gsync.adapter.dao.mapper.base.PlayerStorageEntryBaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import javax.annotation.Nonnull;
import java.util.List;

@Mapper
public interface PlayerStorageEntryMapper extends PlayerStorageEntryBaseMapper {

	@Nonnull
	List<PlayerStorageEntryDto> selectByPlayerIdAndTenantId(@Param("playerId") String playerId,
			@Param("tenantId") String tenantId, @Param("exactKeyMatch") List<String> exactKeyMatch,
			@Param("forwardKeyMatch") List<String> forwardKeyMatch);

	void deleteByPlayerIdAndTenantId(@Param("playerId") String playerId, @Param("tenantId") String tenantId,
			@Param("exactKeyMatch") List<String> exactKeyMatch, @Param("forwardKeyMatch") List<String> forwardKeyMatch);

	void bulkInsert(@Param("dtos") List<PlayerStorageEntryDto> dtos);

}
