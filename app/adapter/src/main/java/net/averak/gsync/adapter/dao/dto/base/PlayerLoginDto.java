package net.averak.gsync.adapter.dao.dto.base;

import java.time.LocalDateTime;
import java.util.Objects;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;

@SuppressWarnings({"all"})
public class PlayerLoginDto extends AbstractDto<PlayerLoginDto> {
	/**
	 *
	 * This field was generated by MyBatis Generator. This field corresponds to the
	 * database column gsync_player_login.player_id
	 *
	 * @mbg.generated
	 */
	private String playerId;

	/**
	 *
	 * This field was generated by MyBatis Generator. This field corresponds to the
	 * database column gsync_player_login.total_login_days
	 *
	 * @mbg.generated
	 */
	private Long totalLoginDays;

	/**
	 *
	 * This field was generated by MyBatis Generator. This field corresponds to the
	 * database column gsync_player_login.last_logged_in_at
	 *
	 * @mbg.generated
	 */
	private LocalDateTime lastLoggedInAt;

	/**
	 *
	 * This field was generated by MyBatis Generator. This field corresponds to the
	 * database column gsync_player_login.created_at
	 *
	 * @mbg.generated
	 */
	private LocalDateTime createdAt;

	/**
	 *
	 * This field was generated by MyBatis Generator. This field corresponds to the
	 * database column gsync_player_login.updated_at
	 *
	 * @mbg.generated
	 */
	private LocalDateTime updatedAt;

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to
	 * the database table gsync_player_login
	 *
	 * @mbg.generated
	 */
	public PlayerLoginDto(@Nonnull String playerId, @Nonnull Long totalLoginDays, @Nonnull LocalDateTime lastLoggedInAt,
			@Nonnull LocalDateTime createdAt, @Nonnull LocalDateTime updatedAt) {
		this.playerId = playerId;
		this.totalLoginDays = totalLoginDays;
		this.lastLoggedInAt = lastLoggedInAt;
		this.createdAt = createdAt;
		this.updatedAt = updatedAt;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to
	 * the database table gsync_player_login
	 *
	 * @mbg.generated
	 */
	public PlayerLoginDto() {
		super();
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value
	 * of the database column gsync_player_login.player_id
	 *
	 * @return the value of gsync_player_login.player_id
	 *
	 * @mbg.generated
	 */
	@Nonnull
	public String getPlayerId() {
		return playerId;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of
	 * the database column gsync_player_login.player_id
	 *
	 * @param playerId
	 *            the value for gsync_player_login.player_id
	 *
	 * @mbg.generated
	 */
	public void setPlayerId(@Nonnull String playerId) {
		this.playerId = playerId;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value
	 * of the database column gsync_player_login.total_login_days
	 *
	 * @return the value of gsync_player_login.total_login_days
	 *
	 * @mbg.generated
	 */
	@Nonnull
	public Long getTotalLoginDays() {
		return totalLoginDays;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of
	 * the database column gsync_player_login.total_login_days
	 *
	 * @param totalLoginDays
	 *            the value for gsync_player_login.total_login_days
	 *
	 * @mbg.generated
	 */
	public void setTotalLoginDays(@Nonnull Long totalLoginDays) {
		this.totalLoginDays = totalLoginDays;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value
	 * of the database column gsync_player_login.last_logged_in_at
	 *
	 * @return the value of gsync_player_login.last_logged_in_at
	 *
	 * @mbg.generated
	 */
	@Nonnull
	public LocalDateTime getLastLoggedInAt() {
		return lastLoggedInAt;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of
	 * the database column gsync_player_login.last_logged_in_at
	 *
	 * @param lastLoggedInAt
	 *            the value for gsync_player_login.last_logged_in_at
	 *
	 * @mbg.generated
	 */
	public void setLastLoggedInAt(@Nonnull LocalDateTime lastLoggedInAt) {
		this.lastLoggedInAt = lastLoggedInAt;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value
	 * of the database column gsync_player_login.created_at
	 *
	 * @return the value of gsync_player_login.created_at
	 *
	 * @mbg.generated
	 */
	@Nonnull
	public LocalDateTime getCreatedAt() {
		return createdAt;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of
	 * the database column gsync_player_login.created_at
	 *
	 * @param createdAt
	 *            the value for gsync_player_login.created_at
	 *
	 * @mbg.generated
	 */
	public void setCreatedAt(@Nonnull LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value
	 * of the database column gsync_player_login.updated_at
	 *
	 * @return the value of gsync_player_login.updated_at
	 *
	 * @mbg.generated
	 */
	@Nonnull
	public LocalDateTime getUpdatedAt() {
		return updatedAt;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of
	 * the database column gsync_player_login.updated_at
	 *
	 * @param updatedAt
	 *            the value for gsync_player_login.updated_at
	 *
	 * @mbg.generated
	 */
	public void setUpdatedAt(@Nonnull LocalDateTime updatedAt) {
		this.updatedAt = updatedAt;
	}

	public boolean isPrimaryKeyEquals(PlayerLoginDto other) {
		return Objects.equals(getPlayerId(), other.getPlayerId());
	}
}