package net.averak.gsync.adapter.dao.dto.extend;

import net.averak.gsync.adapter.dao.dto.base.OperatorDto;
import net.averak.gsync.adapter.dao.dto.base.RTenantOperatorDto;
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nonnull;
import java.time.LocalDateTime;
import java.util.List;

@SuppressWarnings("NotNullFieldNotInitialized")
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
