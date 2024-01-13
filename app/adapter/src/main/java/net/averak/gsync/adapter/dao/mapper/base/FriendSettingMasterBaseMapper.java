package net.averak.gsync.adapter.dao.mapper.base;

import java.util.List;
import net.averak.gsync.adapter.dao.dto.base.FriendSettingMasterDto;
import net.averak.gsync.adapter.dao.dto.base.FriendSettingMasterExample;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

@Mapper
@SuppressWarnings({"all"})
public interface FriendSettingMasterBaseMapper {
	/**
	 * This method was generated by MyBatis Generator. This method corresponds to
	 * the database table gsync_friend_setting_master
	 *
	 * @mbg.generated
	 */
	long countByExample(FriendSettingMasterExample example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to
	 * the database table gsync_friend_setting_master
	 *
	 * @mbg.generated
	 */
	int deleteByExample(FriendSettingMasterExample example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to
	 * the database table gsync_friend_setting_master
	 *
	 * @mbg.generated
	 */
	int deleteByPrimaryKey(String masterVersion);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to
	 * the database table gsync_friend_setting_master
	 *
	 * @mbg.generated
	 */
	int insert(FriendSettingMasterDto row);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to
	 * the database table gsync_friend_setting_master
	 *
	 * @mbg.generated
	 */
	int insertSelective(FriendSettingMasterDto row);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to
	 * the database table gsync_friend_setting_master
	 *
	 * @mbg.generated
	 */
	List<FriendSettingMasterDto> selectByExampleWithRowbounds(FriendSettingMasterExample example, RowBounds rowBounds);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to
	 * the database table gsync_friend_setting_master
	 *
	 * @mbg.generated
	 */
	List<FriendSettingMasterDto> selectByExample(FriendSettingMasterExample example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to
	 * the database table gsync_friend_setting_master
	 *
	 * @mbg.generated
	 */
	FriendSettingMasterDto selectByPrimaryKey(String masterVersion);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to
	 * the database table gsync_friend_setting_master
	 *
	 * @mbg.generated
	 */
	int updateByExampleSelective(@Param("row") FriendSettingMasterDto row,
			@Param("example") FriendSettingMasterExample example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to
	 * the database table gsync_friend_setting_master
	 *
	 * @mbg.generated
	 */
	int updateByExample(@Param("row") FriendSettingMasterDto row, @Param("example") FriendSettingMasterExample example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to
	 * the database table gsync_friend_setting_master
	 *
	 * @mbg.generated
	 */
	int updateByPrimaryKeySelective(FriendSettingMasterDto row);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to
	 * the database table gsync_friend_setting_master
	 *
	 * @mbg.generated
	 */
	int updateByPrimaryKey(FriendSettingMasterDto row);

	/**
	 * 複数レコードを一括で INSERT する
	 *
	 * @mbg.generated
	 */
	void bulkInsert(@Param("dtos") List<FriendSettingMasterDto> dtos);

	/**
	 * PK でレコード検索し、存在する場合は original にセットする
	 *
	 * @mbg.generated
	 */
	default void syncOriginal(FriendSettingMasterDto dto) {
		dto.setOriginal(selectByPrimaryKey(dto.getMasterVersion()));
	}

	/**
	 * PK でレコード検索し、存在する場合は original にセットする
	 *
	 * @mbg.generated
	 */
	default void syncOriginal(List<FriendSettingMasterDto> dtos) {
		if (dtos.isEmpty()) {
			return;
		}

		final var example = new FriendSettingMasterExample();
		example.createCriteria()
				.andMasterVersionIn(dtos.stream().map(FriendSettingMasterDto::getMasterVersion).toList());
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
	default void insertOrUpdate(FriendSettingMasterDto dto) {
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
	default void insertOrUpdate(List<FriendSettingMasterDto> dtos) {
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