package net.averak.gsync.adapter.dao.dto.base;

import java.time.LocalDateTime;
import java.util.Objects;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;

@SuppressWarnings({"all"})
public class PlayerStorageEntryDto extends AbstractDto<PlayerStorageEntryDto> {
	/**
	 *
	 * This field was generated by MyBatis Generator. This field corresponds to the
	 * database column gsync_player_storage_entry.player_id
	 *
	 * @mbg.generated
	 */
	private String playerId;

	/**
	 *
	 * This field was generated by MyBatis Generator. This field corresponds to the
	 * database column gsync_player_storage_entry.game_id
	 *
	 * @mbg.generated
	 */
	private String gameId;

	/**
	 *
	 * This field was generated by MyBatis Generator. This field corresponds to the
	 * database column gsync_player_storage_entry.key
	 *
	 * @mbg.generated
	 */
	private String key;

	/**
	 *
	 * This field was generated by MyBatis Generator. This field corresponds to the
	 * database column gsync_player_storage_entry.created_at
	 *
	 * @mbg.generated
	 */
	private LocalDateTime createdAt;

	/**
	 *
	 * This field was generated by MyBatis Generator. This field corresponds to the
	 * database column gsync_player_storage_entry.updated_at
	 *
	 * @mbg.generated
	 */
	private LocalDateTime updatedAt;

	/**
	 *
	 * This field was generated by MyBatis Generator. This field corresponds to the
	 * database column gsync_player_storage_entry.value
	 *
	 * @mbg.generated
	 */
	private byte[] value;

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to
	 * the database table gsync_player_storage_entry
	 *
	 * @mbg.generated
	 */
	public PlayerStorageEntryDto(@Nonnull String playerId, @Nonnull String gameId, @Nonnull String key,
			@Nonnull LocalDateTime createdAt, @Nonnull LocalDateTime updatedAt) {
		this.playerId = playerId;
		this.gameId = gameId;
		this.key = key;
		this.createdAt = createdAt;
		this.updatedAt = updatedAt;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to
	 * the database table gsync_player_storage_entry
	 *
	 * @mbg.generated
	 */
	public PlayerStorageEntryDto(@Nonnull String playerId, @Nonnull String gameId, @Nonnull String key,
			@Nonnull LocalDateTime createdAt, @Nonnull LocalDateTime updatedAt, @Nonnull byte[] value) {
		this.playerId = playerId;
		this.gameId = gameId;
		this.key = key;
		this.createdAt = createdAt;
		this.updatedAt = updatedAt;
		this.value = value;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to
	 * the database table gsync_player_storage_entry
	 *
	 * @mbg.generated
	 */
	public PlayerStorageEntryDto() {
		super();
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value
	 * of the database column gsync_player_storage_entry.player_id
	 *
	 * @return the value of gsync_player_storage_entry.player_id
	 *
	 * @mbg.generated
	 */
	@Nonnull
	public String getPlayerId() {
		return playerId;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of
	 * the database column gsync_player_storage_entry.player_id
	 *
	 * @param playerId
	 *            the value for gsync_player_storage_entry.player_id
	 *
	 * @mbg.generated
	 */
	public void setPlayerId(@Nonnull String playerId) {
		this.playerId = playerId;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value
	 * of the database column gsync_player_storage_entry.game_id
	 *
	 * @return the value of gsync_player_storage_entry.game_id
	 *
	 * @mbg.generated
	 */
	@Nonnull
	public String getGameId() {
		return gameId;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of
	 * the database column gsync_player_storage_entry.game_id
	 *
	 * @param gameId
	 *            the value for gsync_player_storage_entry.game_id
	 *
	 * @mbg.generated
	 */
	public void setGameId(@Nonnull String gameId) {
		this.gameId = gameId;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value
	 * of the database column gsync_player_storage_entry.key
	 *
	 * @return the value of gsync_player_storage_entry.key
	 *
	 * @mbg.generated
	 */
	@Nonnull
	public String getKey() {
		return key;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of
	 * the database column gsync_player_storage_entry.key
	 *
	 * @param key
	 *            the value for gsync_player_storage_entry.key
	 *
	 * @mbg.generated
	 */
	public void setKey(@Nonnull String key) {
		this.key = key;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value
	 * of the database column gsync_player_storage_entry.created_at
	 *
	 * @return the value of gsync_player_storage_entry.created_at
	 *
	 * @mbg.generated
	 */
	@Nonnull
	public LocalDateTime getCreatedAt() {
		return createdAt;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of
	 * the database column gsync_player_storage_entry.created_at
	 *
	 * @param createdAt
	 *            the value for gsync_player_storage_entry.created_at
	 *
	 * @mbg.generated
	 */
	public void setCreatedAt(@Nonnull LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value
	 * of the database column gsync_player_storage_entry.updated_at
	 *
	 * @return the value of gsync_player_storage_entry.updated_at
	 *
	 * @mbg.generated
	 */
	@Nonnull
	public LocalDateTime getUpdatedAt() {
		return updatedAt;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of
	 * the database column gsync_player_storage_entry.updated_at
	 *
	 * @param updatedAt
	 *            the value for gsync_player_storage_entry.updated_at
	 *
	 * @mbg.generated
	 */
	public void setUpdatedAt(@Nonnull LocalDateTime updatedAt) {
		this.updatedAt = updatedAt;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value
	 * of the database column gsync_player_storage_entry.value
	 *
	 * @return the value of gsync_player_storage_entry.value
	 *
	 * @mbg.generated
	 */
	@Nonnull
	public byte[] getValue() {
		return value;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of
	 * the database column gsync_player_storage_entry.value
	 *
	 * @param value
	 *            the value for gsync_player_storage_entry.value
	 *
	 * @mbg.generated
	 */
	public void setValue(@Nonnull byte[] value) {
		this.value = value;
	}

	/**
	 * PK が一致するか判定
	 *
	 * @mbg.generated
	 */
	public boolean isPrimaryKeyEquals(PlayerStorageEntryDto other) {
		return Objects.equals(getPlayerId(), other.getPlayerId()) && Objects.equals(getGameId(), other.getGameId())
				&& Objects.equals(getKey(), other.getKey());
	}
}