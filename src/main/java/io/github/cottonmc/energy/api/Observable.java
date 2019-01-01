package io.github.cottonmc.energy.api;

import javax.annotation.Nonnull;

public interface Observable {
	public void listen(@Nonnull Runnable r);
}
