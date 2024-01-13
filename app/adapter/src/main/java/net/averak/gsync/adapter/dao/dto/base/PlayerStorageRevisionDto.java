package net.averak.gsync.adapter.dao.dto.base;

import java.time.LocalDateTime;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class PlayerStorageRevisionDto {
	/**
	 *
	 * This field was generated by MyBatis Generator. This field corresponds to the
	 * database column gsync_player_storage_revision.player_id
	 *
	 * @mbg.generated
	 */
	private String playerId;

	/**
	 *
	 * This field was generated by MyBatis Generator. This field corresponds to the
	 * database column gsync_player_storage_revision.tenant_id
	 *
	 * @mbg.generated
	 */
	private String tenantId;

	/**
	 *
	 * This field was generated by MyBatis Generator. This field corresponds to the
	 * database column gsync_player_storage_revision.player_storage_revision_id
	 *
	 * @mbg.generated
	 */
	private String playerStorageRevisionId;

	/**
	 *
	 * This field was generated by MyBatis Generator. This field corresponds to the
	 * database column gsync_player_storage_revision.idempotency_key
	 *
	 * @mbg.generated
	 */
	private String idempotencyKey;

	/**
	 *
	 * This field was generated by MyBatis Generator. This field corresponds to the
	 * database column gsync_player_storage_revision.created_at
	 *
	 * @mbg.generated
	 */
	private LocalDateTime createdAt;

	/**
	 *
	 * This field was generated by MyBatis Generator. This field corresponds to the
	 * database column gsync_player_storage_revision.updated_at
	 *
	 * @mbg.generated
	 */
	private LocalDateTime updatedAt;

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to
	 * the database table gsync_player_storage_revision
	 *
	 * @mbg.generated
	 */
	public PlayerStorageRevisionDto(@Nonnull String playerId, @Nonnull String tenantId,
			@Nonnull String playerStorageRevisionId, @Nonnull String idempotencyKey, @Nonnull LocalDateTime createdAt,
			@Nonnull LocalDateTime updatedAt) {
		this.playerId = playerId;
		this.tenantId = tenantId;
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
	 * of the database column gsync_player_storage_revision.tenant_id
	 *
	 * @return the value of gsync_player_storage_revision.tenant_id
	 *
	 * @mbg.generated
	 */
	@Nonnull
	public String getTenantId() {
		return tenantId;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of
	 * the database column gsync_player_storage_revision.tenant_id
	 *
	 * @param tenantId
	 *            the value for gsync_player_storage_revision.tenant_id
	 *
	 * @mbg.generated
	 */
	public void setTenantId(@Nonnull String tenantId) {
		this.tenantId = tenantId;
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
}