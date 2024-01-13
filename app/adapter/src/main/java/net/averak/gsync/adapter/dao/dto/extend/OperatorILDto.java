package net.averak.gsync.adapter.dao.dto.extend;

import java.time.LocalDateTime;
import java.util.List;

import javax.annotation.Nonnull;

import org.jetbrains.annotations.NotNull;

import net.averak.gsync.adapter.dao.dto.base.OperatorDto;
import net.averak.gsync.adapter.dao.dto.base.RTenantOperatorDto;

@SuppressWarnings({"NotNullFieldNotInitialized", "java:S2637"})
public class OperatorILDto extends OperatorDto {

	@Nonnull
	List<RTenantOperatorDto> rTenantOperators;

	public OperatorILDto(@NotNull String operatorId, @NotNull String email, @NotNull LocalDateTime createdAt,
			@NotNull LocalDateTime updatedAt) {
		super(operatorId, email, createdAt, updatedAt);
	}

	@Nonnull
	public List<RTenantOperatorDto> getRTenantOperators() {
		return rTenantOperators;
	}

	public void setRTenantOperators(@Nonnull List<RTenantOperatorDto> rTenantOperators) {
		this.rTenantOperators = rTenantOperators;
	}
}
