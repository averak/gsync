package net.averak.gsync.adapter.dao.mapper.extend;

import net.averak.gsync.adapter.dao.dto.base.PlayerStorageRevisionDto;
import net.averak.gsync.adapter.dao.mapper.base.PlayerStorageRevisionBaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import javax.annotation.Nonnull;
import java.util.List;

@Mapper
public interface PlayerStorageRevisionMapper extends PlayerStorageRevisionBaseMapper {

	@Nonnull
	List<PlayerStorageRevisionDto> selectByPlayerIdAndTenantIdOrderByCreatedAt(@Param("playerId") String playerId,
			@Param("tenantId") String tenantId, @Param("limit") int limit);

}
