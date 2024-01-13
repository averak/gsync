package net.averak.gsync.adapter.dao.dto.base;

import javax.annotation.Nullable;

public abstract class AbstractDto<T> {

	@Nullable
	private T original = null;

	@Nullable
	public T getOriginal() {
		return original;
	}

	public void setOriginal(@Nullable T original) {
		this.original = original;
	}
}
