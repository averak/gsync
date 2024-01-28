package net.averak.gsync.adapter.dao.mapper.base;

import java.util.List;
import net.averak.gsync.adapter.dao.dto.base.RequiredClientVersionDto;
import net.averak.gsync.adapter.dao.dto.base.RequiredClientVersionExample;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

@Mapper
@SuppressWarnings({"all"})
public interface RequiredClientVersionBaseMapper {
	/**
	 * This method was generated by MyBatis Generator. This method corresponds to
	 * the database table gsync_required_client_version
	 *
	 * @mbg.generated
	 */
	long countByExample(RequiredClientVersionExample example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to
	 * the database table gsync_required_client_version
	 *
	 * @mbg.generated
	 */
	int deleteByExample(RequiredClientVersionExample example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to
	 * the database table gsync_required_client_version
	 *
	 * @mbg.generated
	 */
	int deleteByPrimaryKey(@Param("masterVersion") String masterVersion, @Param("clientVersion") String clientVersion,
			@Param("os") Long os);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to
	 * the database table gsync_required_client_version
	 *
	 * @mbg.generated
	 */
	int insert(RequiredClientVersionDto row);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to
	 * the database table gsync_required_client_version
	 *
	 * @mbg.generated
	 */
	int insertSelective(RequiredClientVersionDto row);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to
	 * the database table gsync_required_client_version
	 *
	 * @mbg.generated
	 */
	List<RequiredClientVersionDto> selectByExampleWithRowbounds(RequiredClientVersionExample example,
			RowBounds rowBounds);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to
	 * the database table gsync_required_client_version
	 *
	 * @mbg.generated
	 */
	List<RequiredClientVersionDto> selectByExample(RequiredClientVersionExample example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to
	 * the database table gsync_required_client_version
	 *
	 * @mbg.generated
	 */
	RequiredClientVersionDto selectByPrimaryKey(@Param("masterVersion") String masterVersion,
			@Param("clientVersion") String clientVersion, @Param("os") Long os);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to
	 * the database table gsync_required_client_version
	 *
	 * @mbg.generated
	 */
	int updateByExampleSelective(@Param("row") RequiredClientVersionDto row,
			@Param("example") RequiredClientVersionExample example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to
	 * the database table gsync_required_client_version
	 *
	 * @mbg.generated
	 */
	int updateByExample(@Param("row") RequiredClientVersionDto row,
			@Param("example") RequiredClientVersionExample example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to
	 * the database table gsync_required_client_version
	 *
	 * @mbg.generated
	 */
	int updateByPrimaryKeySelective(RequiredClientVersionDto row);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to
	 * the database table gsync_required_client_version
	 *
	 * @mbg.generated
	 */
	int updateByPrimaryKey(RequiredClientVersionDto row);

	/**
	 * 複数レコードを一括で INSERT する
	 *
	 * @mbg.generated
	 */
	void bulkInsert(@Param("dtos") List<RequiredClientVersionDto> dtos);

	/**
	 * PK でレコード検索し、存在する場合は original にセットする
	 *
	 * @mbg.generated
	 */
	default void syncOriginal(RequiredClientVersionDto dto) {
		dto.setOriginal(selectByPrimaryKey(dto.getMasterVersion(), dto.getClientVersion(), dto.getOs()));
	}

	/**
	 * PK でレコード検索し、存在する場合は original にセットする
	 *
	 * @mbg.generated
	 */
	default void syncOriginal(List<RequiredClientVersionDto> dtos) {
		if (dtos.isEmpty()) {
			return;
		}

		final var example = new RequiredClientVersionExample();
		example.createCriteria()
				.andMasterVersionIn(dtos.stream().map(RequiredClientVersionDto::getMasterVersion).toList());
		example.createCriteria()
				.andClientVersionIn(dtos.stream().map(RequiredClientVersionDto::getClientVersion).toList());
		example.createCriteria().andOsIn(dtos.stream().map(RequiredClientVersionDto::getOs).toList());
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
	default void insertOrUpdate(RequiredClientVersionDto dto) {
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
	default void insertOrUpdate(List<RequiredClientVersionDto> dtos) {
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