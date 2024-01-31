package net.averak.gsync.adapter.dao.dto.base;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Objects;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;

@SuppressWarnings({"all"})
public class EchoDto extends AbstractDto<EchoDto> implements Serializable {
	/**
	 *
	 * This field was generated by MyBatis Generator. This field corresponds to the
	 * database column gsync_echo.echo_id
	 *
	 * @mbg.generated
	 */
	@Nonnull
	private String echoId;

	/**
	 *
	 * This field was generated by MyBatis Generator. This field corresponds to the
	 * database column gsync_echo.message
	 *
	 * @mbg.generated
	 */
	@Nonnull
	private String message;

	/**
	 *
	 * This field was generated by MyBatis Generator. This field corresponds to the
	 * database column gsync_echo.timestamp
	 *
	 * @mbg.generated
	 */
	@Nonnull
	private LocalDateTime timestamp;

	/**
	 *
	 * This field was generated by MyBatis Generator. This field corresponds to the
	 * database column gsync_echo.created_at
	 *
	 * @mbg.generated
	 */
	@Nonnull
	private LocalDateTime createdAt;

	/**
	 *
	 * This field was generated by MyBatis Generator. This field corresponds to the
	 * database column gsync_echo.updated_at
	 *
	 * @mbg.generated
	 */
	@Nonnull
	private LocalDateTime updatedAt;

	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the
	 * database table gsync_echo
	 *
	 * @mbg.generated
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to
	 * the database table gsync_echo
	 *
	 * @mbg.generated
	 */
	public EchoDto(@Nonnull String echoId, @Nonnull String message, @Nonnull LocalDateTime timestamp,
			@Nonnull LocalDateTime createdAt, @Nonnull LocalDateTime updatedAt) {
		this.echoId = echoId;
		this.message = message;
		this.timestamp = timestamp;
		this.createdAt = createdAt;
		this.updatedAt = updatedAt;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to
	 * the database table gsync_echo
	 *
	 * @mbg.generated
	 */
	public EchoDto() {
		super();
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value
	 * of the database column gsync_echo.echo_id
	 *
	 * @return the value of gsync_echo.echo_id
	 *
	 * @mbg.generated
	 */
	@Nonnull
	public String getEchoId() {
		return echoId;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of
	 * the database column gsync_echo.echo_id
	 *
	 * @param echoId
	 *            the value for gsync_echo.echo_id
	 *
	 * @mbg.generated
	 */
	public void setEchoId(@Nonnull String echoId) {
		this.echoId = echoId;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value
	 * of the database column gsync_echo.message
	 *
	 * @return the value of gsync_echo.message
	 *
	 * @mbg.generated
	 */
	@Nonnull
	public String getMessage() {
		return message;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of
	 * the database column gsync_echo.message
	 *
	 * @param message
	 *            the value for gsync_echo.message
	 *
	 * @mbg.generated
	 */
	public void setMessage(@Nonnull String message) {
		this.message = message;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value
	 * of the database column gsync_echo.timestamp
	 *
	 * @return the value of gsync_echo.timestamp
	 *
	 * @mbg.generated
	 */
	@Nonnull
	public LocalDateTime getTimestamp() {
		return timestamp;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of
	 * the database column gsync_echo.timestamp
	 *
	 * @param timestamp
	 *            the value for gsync_echo.timestamp
	 *
	 * @mbg.generated
	 */
	public void setTimestamp(@Nonnull LocalDateTime timestamp) {
		this.timestamp = timestamp;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value
	 * of the database column gsync_echo.created_at
	 *
	 * @return the value of gsync_echo.created_at
	 *
	 * @mbg.generated
	 */
	@Nonnull
	public LocalDateTime getCreatedAt() {
		return createdAt;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of
	 * the database column gsync_echo.created_at
	 *
	 * @param createdAt
	 *            the value for gsync_echo.created_at
	 *
	 * @mbg.generated
	 */
	public void setCreatedAt(@Nonnull LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value
	 * of the database column gsync_echo.updated_at
	 *
	 * @return the value of gsync_echo.updated_at
	 *
	 * @mbg.generated
	 */
	@Nonnull
	public LocalDateTime getUpdatedAt() {
		return updatedAt;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of
	 * the database column gsync_echo.updated_at
	 *
	 * @param updatedAt
	 *            the value for gsync_echo.updated_at
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
	public boolean isPrimaryKeyEquals(EchoDto other) {
		return Objects.equals(getEchoId(), other.getEchoId());
	}

	/**
	 * マジックカラムにデフォルト値を設定するコンストラクタ
	 *
	 * @mbg.generated
	 */
	public EchoDto(@Nonnull String echoId, @Nonnull String message, @Nonnull LocalDateTime timestamp) {
		this.echoId = echoId;
		this.message = message;
		this.timestamp = timestamp;
		this.createdAt = LocalDateTime.now();
		this.updatedAt = LocalDateTime.now();
	}
}