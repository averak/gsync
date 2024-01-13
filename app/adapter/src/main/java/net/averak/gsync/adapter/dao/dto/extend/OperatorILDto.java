package net.averak.gsync.adapter.dao.dto.extend;

import java.time.LocalDateTime;
import java.util.List;

import javax.annotation.Nonnull;

import org.jetbrains.annotations.NotNull;

import net.averak.gsync.adapter.dao.dto.base.OperatorDto;
import net.averak.gsync.adapter.dao.dto.base.RGameOperatorDto;

@SuppressWarnings({"NotNullFieldNotInitialized", "java:S2637"})
public class OperatorILDto extends OperatorDto {

	@Nonnull
	List<RGameOperatorDto> rGameOperators;

	public OperatorILDto(@NotNull String operatorId, @NotNull String email, @NotNull LocalDateTime createdAt,
			@NotNull LocalDateTime updatedAt) {
		super(operatorId, email, createdAt, updatedAt);
	}

	@Nonnull
	public List<RGameOperatorDto> getRGameOperators() {
		return rGameOperators;
	}

	public void setRGameOperators(@Nonnull List<RGameOperatorDto> rGameOperators) {
		this.rGameOperators = rGameOperators;
	}
}
