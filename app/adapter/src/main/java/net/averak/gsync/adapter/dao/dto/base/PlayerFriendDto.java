package net.averak.gsync.adapter.dao.dto.base;

import java.time.LocalDateTime;
import java.util.Objects;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;

@SuppressWarnings({"all"})
public class PlayerFriendDto extends AbstractDto<PlayerFriendDto> {
	/**
	 *
	 * This field was generated by MyBatis Generator. This field corresponds to the
	 * database column gsync_player_friend.player_id
	 *
	 * @mbg.generated
	 */
	private String playerId;

	/**
	 *
	 * This field was generated by MyBatis Generator. This field corresponds to the
	 * database column gsync_player_friend.friend_player_id
	 *
	 * @mbg.generated
	 */
	private String friendPlayerId;

	/**
	 *
	 * This field was generated by MyBatis Generator. This field corresponds to the
	 * database column gsync_player_friend.become_friend_at
	 *
	 * @mbg.generated
	 */
	private LocalDateTime becomeFriendAt;

	/**
	 *
	 * This field was generated by MyBatis Generator. This field corresponds to the
	 * database column gsync_player_friend.created_at
	 *
	 * @mbg.generated
	 */
	private LocalDateTime createdAt;

	/**
	 *
	 * This field was generated by MyBatis Generator. This field corresponds to the
	 * database column gsync_player_friend.updated_at
	 *
	 * @mbg.generated
	 */
	private LocalDateTime updatedAt;

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to
	 * the database table gsync_player_friend
	 *
	 * @mbg.generated
	 */
	public PlayerFriendDto(@Nonnull String playerId, @Nonnull String friendPlayerId,
			@Nonnull LocalDateTime becomeFriendAt, @Nonnull LocalDateTime createdAt, @Nonnull LocalDateTime updatedAt) {
		this.playerId = playerId;
		this.friendPlayerId = friendPlayerId;
		this.becomeFriendAt = becomeFriendAt;
		this.createdAt = createdAt;
		this.updatedAt = updatedAt;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to
	 * the database table gsync_player_friend
	 *
	 * @mbg.generated
	 */
	public PlayerFriendDto() {
		super();
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value
	 * of the database column gsync_player_friend.player_id
	 *
	 * @return the value of gsync_player_friend.player_id
	 *
	 * @mbg.generated
	 */
	@Nonnull
	public String getPlayerId() {
		return playerId;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of
	 * the database column gsync_player_friend.player_id
	 *
	 * @param playerId
	 *            the value for gsync_player_friend.player_id
	 *
	 * @mbg.generated
	 */
	public void setPlayerId(@Nonnull String playerId) {
		this.playerId = playerId;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value
	 * of the database column gsync_player_friend.friend_player_id
	 *
	 * @return the value of gsync_player_friend.friend_player_id
	 *
	 * @mbg.generated
	 */
	@Nonnull
	public String getFriendPlayerId() {
		return friendPlayerId;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of
	 * the database column gsync_player_friend.friend_player_id
	 *
	 * @param friendPlayerId
	 *            the value for gsync_player_friend.friend_player_id
	 *
	 * @mbg.generated
	 */
	public void setFriendPlayerId(@Nonnull String friendPlayerId) {
		this.friendPlayerId = friendPlayerId;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value
	 * of the database column gsync_player_friend.become_friend_at
	 *
	 * @return the value of gsync_player_friend.become_friend_at
	 *
	 * @mbg.generated
	 */
	@Nonnull
	public LocalDateTime getBecomeFriendAt() {
		return becomeFriendAt;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of
	 * the database column gsync_player_friend.become_friend_at
	 *
	 * @param becomeFriendAt
	 *            the value for gsync_player_friend.become_friend_at
	 *
	 * @mbg.generated
	 */
	public void setBecomeFriendAt(@Nonnull LocalDateTime becomeFriendAt) {
		this.becomeFriendAt = becomeFriendAt;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value
	 * of the database column gsync_player_friend.created_at
	 *
	 * @return the value of gsync_player_friend.created_at
	 *
	 * @mbg.generated
	 */
	@Nonnull
	public LocalDateTime getCreatedAt() {
		return createdAt;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of
	 * the database column gsync_player_friend.created_at
	 *
	 * @param createdAt
	 *            the value for gsync_player_friend.created_at
	 *
	 * @mbg.generated
	 */
	public void setCreatedAt(@Nonnull LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value
	 * of the database column gsync_player_friend.updated_at
	 *
	 * @return the value of gsync_player_friend.updated_at
	 *
	 * @mbg.generated
	 */
	@Nonnull
	public LocalDateTime getUpdatedAt() {
		return updatedAt;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of
	 * the database column gsync_player_friend.updated_at
	 *
	 * @param updatedAt
	 *            the value for gsync_player_friend.updated_at
	 *
	 * @mbg.generated
	 */
	public void setUpdatedAt(@Nonnull LocalDateTime updatedAt) {
		this.updatedAt = updatedAt;
	}

	public boolean isPrimaryKeyEquals(PlayerFriendDto other) {
		return Objects.equals(getPlayerId(), other.getPlayerId())
				&& Objects.equals(getFriendPlayerId(), other.getFriendPlayerId());
	}
}