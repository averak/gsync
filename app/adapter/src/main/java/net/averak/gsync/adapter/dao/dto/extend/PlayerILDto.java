package net.averak.gsync.adapter.dao.dto.extend;

import net.averak.gsync.adapter.dao.dto.base.PlayerDto;
import net.averak.gsync.adapter.dao.dto.base.PlayerLoginDto;
import net.averak.gsync.adapter.dao.dto.base.PlayerProfileDto;
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.time.LocalDateTime;

@SuppressWarnings("NotNullFieldNotInitialized")
public class PlayerILDto extends PlayerDto {

	@Nonnull
	PlayerProfileDto playerProfile;

	@Nullable
	PlayerLoginDto playerLogin;

	public PlayerILDto(@NotNull String playerId, @NotNull String friendId, @NotNull Boolean isBanned,
			@NotNull LocalDateTime createdAt, @NotNull LocalDateTime updatedAt) {
		super(playerId, friendId, isBanned, createdAt, updatedAt);
	}

	@Nonnull
	public PlayerProfileDto getPlayerProfile() {
		return playerProfile;
	}

	public void setPlayerProfile(@Nonnull PlayerProfileDto playerProfile) {
		this.playerProfile = playerProfile;
	}

	@Nullable
	public PlayerLoginDto getPlayerLogin() {
		return playerLogin;
	}

	public void setPlayerLogin(@Nullable PlayerLoginDto playerLogin) {
		this.playerLogin = playerLogin;
	}
}
