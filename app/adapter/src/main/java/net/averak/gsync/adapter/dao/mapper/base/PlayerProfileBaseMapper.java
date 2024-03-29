package net.averak.gsync.adapter.dao.mapper.base;

import java.util.List;
import net.averak.gsync.adapter.dao.dto.base.PlayerProfileDto;
import net.averak.gsync.adapter.dao.dto.base.PlayerProfileExample;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

@Mapper
@SuppressWarnings({"all"})
public interface PlayerProfileBaseMapper {
	/**
	 * This method was generated by MyBatis Generator. This method corresponds to
	 * the database table gsync_player_profile
	 *
	 * @mbg.generated
	 */
	long countByExample(PlayerProfileExample example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to
	 * the database table gsync_player_profile
	 *
	 * @mbg.generated
	 */
	int deleteByExample(PlayerProfileExample example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to
	 * the database table gsync_player_profile
	 *
	 * @mbg.generated
	 */
	int deleteByPrimaryKey(String playerId);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to
	 * the database table gsync_player_profile
	 *
	 * @mbg.generated
	 */
	int insert(PlayerProfileDto row);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to
	 * the database table gsync_player_profile
	 *
	 * @mbg.generated
	 */
	int insertSelective(PlayerProfileDto row);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to
	 * the database table gsync_player_profile
	 *
	 * @mbg.generated
	 */
	List<PlayerProfileDto> selectByExampleWithRowbounds(PlayerProfileExample example, RowBounds rowBounds);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to
	 * the database table gsync_player_profile
	 *
	 * @mbg.generated
	 */
	List<PlayerProfileDto> selectByExample(PlayerProfileExample example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to
	 * the database table gsync_player_profile
	 *
	 * @mbg.generated
	 */
	PlayerProfileDto selectByPrimaryKey(String playerId);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to
	 * the database table gsync_player_profile
	 *
	 * @mbg.generated
	 */
	int updateByExampleSelective(@Param("row") PlayerProfileDto row, @Param("example") PlayerProfileExample example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to
	 * the database table gsync_player_profile
	 *
	 * @mbg.generated
	 */
	int updateByExample(@Param("row") PlayerProfileDto row, @Param("example") PlayerProfileExample example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to
	 * the database table gsync_player_profile
	 *
	 * @mbg.generated
	 */
	int updateByPrimaryKeySelective(PlayerProfileDto row);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to
	 * the database table gsync_player_profile
	 *
	 * @mbg.generated
	 */
	int updateByPrimaryKey(PlayerProfileDto row);

	/**
	 * 複数レコードを一括で INSERT する
	 *
	 * @mbg.generated
	 */
	void bulkInsert(@Param("dtos") List<PlayerProfileDto> dtos);

	/**
	 * PK でレコード検索し、存在する場合は original にセットする
	 *
	 * @mbg.generated
	 */
	default void syncOriginal(PlayerProfileDto dto) {
		dto.setOriginal(selectByPrimaryKey(dto.getPlayerId()));
	}

	/**
	 * PK でレコード検索し、存在する場合は original にセットする
	 *
	 * @mbg.generated
	 */
	default void syncOriginal(List<PlayerProfileDto> dtos) {
		if (dtos.isEmpty()) {
			return;
		}

		final var example = new PlayerProfileExample();
		example.createCriteria().andPlayerIdIn(dtos.stream().map(PlayerProfileDto::getPlayerId).toList());
		final var originals = selectByExample(example);
		originals.forEach(original -> dtos.forEach(dto -> {
			if (dto.isPrimaryKeyEquals(original)) {
				dto.setOriginal(original);
			}
		}));
	}

	/**
	 * original が null の場合は INSERT、そうでない場合は UPDATE を実行する
	 *
	 * @mbg.generated
	 */
	default void insertOrUpdate(PlayerProfileDto dto) {
		if (dto.getOriginal() == null) {
			insert(dto);
		} else {
			dto.setCreatedAt(dto.getOriginal().getCreatedAt());
			updateByPrimaryKey(dto);
		}
	}

	/**
	 * original が null の場合は INSERT、そうでない場合は UPDATE を実行する
	 *
	 * @mbg.generated
	 */
	default void insertOrUpdate(List<PlayerProfileDto> dtos) {
		final var inserts = dtos.stream().filter(dto -> dto.getOriginal() == null).toList();
		if (!inserts.isEmpty()) {
			bulkInsert(inserts);
		}

		final var updates = dtos.stream().filter(dto -> dto.getOriginal() != null).toList();
		updates.parallelStream().forEach(dto -> {
			if (dto.getOriginal() == null) {
				return;
			}
			dto.setCreatedAt(dto.getOriginal().getCreatedAt());
			updateByPrimaryKey(dto);
		});
	}
}