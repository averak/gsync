package net.averak.gsync.adapter.dao.dto.base;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Objects;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;

@SuppressWarnings({"all"})
public class PlayerFriendRequestDto extends AbstractDto<PlayerFriendRequestDto> implements Serializable {
	/**
	 *
	 * This field was generated by MyBatis Generator. This field corresponds to the
	 * database column gsync_player_friend_request.player_id
	 *
	 * @mbg.generated
	 */
	@Nonnull
	private String playerId;

	/**
	 *
	 * This field was generated by MyBatis Generator. This field corresponds to the
	 * database column gsync_player_friend_request.receiver_player_id
	 *
	 * @mbg.generated
	 */
	@Nonnull
	private String receiverPlayerId;

	/**
	 *
	 * This field was generated by MyBatis Generator. This field corresponds to the
	 * database column gsync_player_friend_request.sent_at
	 *
	 * @mbg.generated
	 */
	@Nonnull
	private LocalDateTime sentAt;

	/**
	 *
	 * This field was generated by MyBatis Generator. This field corresponds to the
	 * database column gsync_player_friend_request.created_at
	 *
	 * @mbg.generated
	 */
	@Nonnull
	private LocalDateTime createdAt;

	/**
	 *
	 * This field was generated by MyBatis Generator. This field corresponds to the
	 * database column gsync_player_friend_request.updated_at
	 *
	 * @mbg.generated
	 */
	@Nonnull
	private LocalDateTime updatedAt;

	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the
	 * database table gsync_player_friend_request
	 *
	 * @mbg.generated
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to
	 * the database table gsync_player_friend_request
	 *
	 * @mbg.generated
	 */
	public PlayerFriendRequestDto(@Nonnull String playerId, @Nonnull String receiverPlayerId,
			@Nonnull LocalDateTime sentAt, @Nonnull LocalDateTime createdAt, @Nonnull LocalDateTime updatedAt) {
		this.playerId = playerId;
		this.receiverPlayerId = receiverPlayerId;
		this.sentAt = sentAt;
		this.createdAt = createdAt;
		this.updatedAt = updatedAt;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to
	 * the database table gsync_player_friend_request
	 *
	 * @mbg.generated
	 */
	public PlayerFriendRequestDto() {
		super();
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value
	 * of the database column gsync_player_friend_request.player_id
	 *
	 * @return the value of gsync_player_friend_request.player_id
	 *
	 * @mbg.generated
	 */
	@Nonnull
	public String getPlayerId() {
		return playerId;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of
	 * the database column gsync_player_friend_request.player_id
	 *
	 * @param playerId
	 *            the value for gsync_player_friend_request.player_id
	 *
	 * @mbg.generated
	 */
	public void setPlayerId(@Nonnull String playerId) {
		this.playerId = playerId;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value
	 * of the database column gsync_player_friend_request.receiver_player_id
	 *
	 * @return the value of gsync_player_friend_request.receiver_player_id
	 *
	 * @mbg.generated
	 */
	@Nonnull
	public String getReceiverPlayerId() {
		return receiverPlayerId;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of
	 * the database column gsync_player_friend_request.receiver_player_id
	 *
	 * @param receiverPlayerId
	 *            the value for gsync_player_friend_request.receiver_player_id
	 *
	 * @mbg.generated
	 */
	public void setReceiverPlayerId(@Nonnull String receiverPlayerId) {
		this.receiverPlayerId = receiverPlayerId;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value
	 * of the database column gsync_player_friend_request.sent_at
	 *
	 * @return the value of gsync_player_friend_request.sent_at
	 *
	 * @mbg.generated
	 */
	@Nonnull
	public LocalDateTime getSentAt() {
		return sentAt;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of
	 * the database column gsync_player_friend_request.sent_at
	 *
	 * @param sentAt
	 *            the value for gsync_player_friend_request.sent_at
	 *
	 * @mbg.generated
	 */
	public void setSentAt(@Nonnull LocalDateTime sentAt) {
		this.sentAt = sentAt;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value
	 * of the database column gsync_player_friend_request.created_at
	 *
	 * @return the value of gsync_player_friend_request.created_at
	 *
	 * @mbg.generated
	 */
	@Nonnull
	public LocalDateTime getCreatedAt() {
		return createdAt;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of
	 * the database column gsync_player_friend_request.created_at
	 *
	 * @param createdAt
	 *            the value for gsync_player_friend_request.created_at
	 *
	 * @mbg.generated
	 */
	public void setCreatedAt(@Nonnull LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value
	 * of the database column gsync_player_friend_request.updated_at
	 *
	 * @return the value of gsync_player_friend_request.updated_at
	 *
	 * @mbg.generated
	 */
	@Nonnull
	public LocalDateTime getUpdatedAt() {
		return updatedAt;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of
	 * the database column gsync_player_friend_request.updated_at
	 *
	 * @param updatedAt
	 *            the value for gsync_player_friend_request.updated_at
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
	public boolean isPrimaryKeyEquals(PlayerFriendRequestDto other) {
		return Objects.equals(getPlayerId(), other.getPlayerId())
				&& Objects.equals(getReceiverPlayerId(), other.getReceiverPlayerId());
	}

	/**
	 * マジックカラムにデフォルト値を設定するコンストラクタ
	 *
	 * @mbg.generated
	 */
	public PlayerFriendRequestDto(@Nonnull String playerId, @Nonnull String receiverPlayerId,
			@Nonnull LocalDateTime sentAt) {
		this.playerId = playerId;
		this.receiverPlayerId = receiverPlayerId;
		this.sentAt = sentAt;
		this.createdAt = LocalDateTime.now();
		this.updatedAt = LocalDateTime.now();
	}
}