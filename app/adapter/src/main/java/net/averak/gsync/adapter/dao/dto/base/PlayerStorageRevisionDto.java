package net.averak.gsync.adapter.dao.dto.base;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Objects;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;

@SuppressWarnings({"all"})
public class PlayerStorageRevisionDto extends AbstractDto<PlayerStorageRevisionDto> implements Serializable {
	/**
	 *
	 * This field was generated by MyBatis Generator. This field corresponds to the
	 * database column gsync_player_storage_revision.player_id
	 *
	 * @mbg.generated
	 */
	@Nonnull
	private String playerId;

	/**
	 *
	 * This field was generated by MyBatis Generator. This field corresponds to the
	 * database column gsync_player_storage_revision.game_id
	 *
	 * @mbg.generated
	 */
	@Nonnull
	private String gameId;

	/**
	 *
	 * This field was generated by MyBatis Generator. This field corresponds to the
	 * database column gsync_player_storage_revision.player_storage_revision_id
	 *
	 * @mbg.generated
	 */
	@Nonnull
	private String playerStorageRevisionId;

	/**
	 *
	 * This field was generated by MyBatis Generator. This field corresponds to the
	 * database column gsync_player_storage_revision.idempotency_key
	 *
	 * @mbg.generated
	 */
	@Nonnull
	private String idempotencyKey;

	/**
	 *
	 * This field was generated by MyBatis Generator. This field corresponds to the
	 * database column gsync_player_storage_revision.created_at
	 *
	 * @mbg.generated
	 */
	@Nonnull
	private LocalDateTime createdAt;

	/**
	 *
	 * This field was generated by MyBatis Generator. This field corresponds to the
	 * database column gsync_player_storage_revision.updated_at
	 *
	 * @mbg.generated
	 */
	@Nonnull
	private LocalDateTime updatedAt;

	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the
	 * database table gsync_player_storage_revision
	 *
	 * @mbg.generated
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to
	 * the database table gsync_player_storage_revision
	 *
	 * @mbg.generated
	 */
	public PlayerStorageRevisionDto(@Nonnull String playerId, @Nonnull String gameId,
			@Nonnull String playerStorageRevisionId, @Nonnull String idempotencyKey, @Nonnull LocalDateTime createdAt,
			@Nonnull LocalDateTime updatedAt) {
		this.playerId = playerId;
		this.gameId = gameId;
		this.playerStorageRevisionId = playerStorageRevisionId;
		this.idempotencyKey = idempotencyKey;
		this.createdAt = createdAt;
		this.updatedAt = updatedAt;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to
	 * the database table gsync_player_storage_revision
	 *
	 * @mbg.generated
	 */
	public PlayerStorageRevisionDto() {
		super();
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value
	 * of the database column gsync_player_storage_revision.player_id
	 *
	 * @return the value of gsync_player_storage_revision.player_id
	 *
	 * @mbg.generated
	 */
	@Nonnull
	public String getPlayerId() {
		return playerId;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of
	 * the database column gsync_player_storage_revision.player_id
	 *
	 * @param playerId
	 *            the value for gsync_player_storage_revision.player_id
	 *
	 * @mbg.generated
	 */
	public void setPlayerId(@Nonnull String playerId) {
		this.playerId = playerId;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value
	 * of the database column gsync_player_storage_revision.game_id
	 *
	 * @return the value of gsync_player_storage_revision.game_id
	 *
	 * @mbg.generated
	 */
	@Nonnull
	public String getGameId() {
		return gameId;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of
	 * the database column gsync_player_storage_revision.game_id
	 *
	 * @param gameId
	 *            the value for gsync_player_storage_revision.game_id
	 *
	 * @mbg.generated
	 */
	public void setGameId(@Nonnull String gameId) {
		this.gameId = gameId;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value
	 * of the database column
	 * gsync_player_storage_revision.player_storage_revision_id
	 *
	 * @return the value of gsync_player_storage_revision.player_storage_revision_id
	 *
	 * @mbg.generated
	 */
	@Nonnull
	public String getPlayerStorageRevisionId() {
		return playerStorageRevisionId;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of
	 * the database column gsync_player_storage_revision.player_storage_revision_id
	 *
	 * @param playerStorageRevisionId
	 *            the value for
	 *            gsync_player_storage_revision.player_storage_revision_id
	 *
	 * @mbg.generated
	 */
	public void setPlayerStorageRevisionId(@Nonnull String playerStorageRevisionId) {
		this.playerStorageRevisionId = playerStorageRevisionId;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value
	 * of the database column gsync_player_storage_revision.idempotency_key
	 *
	 * @return the value of gsync_player_storage_revision.idempotency_key
	 *
	 * @mbg.generated
	 */
	@Nonnull
	public String getIdempotencyKey() {
		return idempotencyKey;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of
	 * the database column gsync_player_storage_revision.idempotency_key
	 *
	 * @param idempotencyKey
	 *            the value for gsync_player_storage_revision.idempotency_key
	 *
	 * @mbg.generated
	 */
	public void setIdempotencyKey(@Nonnull String idempotencyKey) {
		this.idempotencyKey = idempotencyKey;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value
	 * of the database column gsync_player_storage_revision.created_at
	 *
	 * @return the value of gsync_player_storage_revision.created_at
	 *
	 * @mbg.generated
	 */
	@Nonnull
	public LocalDateTime getCreatedAt() {
		return createdAt;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of
	 * the database column gsync_player_storage_revision.created_at
	 *
	 * @param createdAt
	 *            the value for gsync_player_storage_revision.created_at
	 *
	 * @mbg.generated
	 */
	public void setCreatedAt(@Nonnull LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value
	 * of the database column gsync_player_storage_revision.updated_at
	 *
	 * @return the value of gsync_player_storage_revision.updated_at
	 *
	 * @mbg.generated
	 */
	@Nonnull
	public LocalDateTime getUpdatedAt() {
		return updatedAt;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of
	 * the database column gsync_player_storage_revision.updated_at
	 *
	 * @param updatedAt
	 *            the value for gsync_player_storage_revision.updated_at
	 *
	 * @mbg.generated
	 */
	public void setUpdatedAt(@Nonnull LocalDateTime updatedAt) {
		this.updatedAt = updatedAt;
	}

	/**
	 * PK が一致するか判定
	 *
	 * @mbg.generated
	 */
	public boolean isPrimaryKeyEquals(PlayerStorageRevisionDto other) {
		return Objects.equals(getPlayerId(), other.getPlayerId()) && Objects.equals(getGameId(), other.getGameId())
				&& Objects.equals(getPlayerStorageRevisionId(), other.getPlayerStorageRevisionId());
	}

	/**
	 * マジックカラムにデフォルト値を設定するコンストラクタ
	 *
	 * @mbg.generated
	 */
	public PlayerStorageRevisionDto(@Nonnull String playerId, @Nonnull String gameId,
			@Nonnull String playerStorageRevisionId, @Nonnull String idempotencyKey) {
		this.playerId = playerId;
		this.gameId = gameId;
		this.playerStorageRevisionId = playerStorageRevisionId;
		this.idempotencyKey = idempotencyKey;
		this.createdAt = LocalDateTime.now();
		this.updatedAt = LocalDateTime.now();
	}
}