package io.github.cottonmc.energy.impl;

import com.google.common.collect.Lists;
import io.github.cottonmc.energy.api.ActionType;
import io.github.cottonmc.energy.api.EnergyComponent;
import io.github.cottonmc.energy.api.Observable;

import javax.annotation.Nonnull;
import java.util.List;

public class EnergyHandler implements EnergyComponent, Observable {

	private int maxEnergy;
	private int currentEnergy = 0;
	private final List<Runnable> listeners = Lists.newArrayList();

	public EnergyHandler(int maxEnergy) {
		this.maxEnergy = maxEnergy;
	}

	@Override
	public int getMaxEnergy() {
		return maxEnergy;
	}

	@Override
	public int getCurrentEnergy() {
		return currentEnergy;
	}

	@Override
	public boolean canInsertEnergy() {
		return true;
	}

	@Override
	public boolean canExtractEnergy() {
		return true;
	}

	@Nonnull
	@Override
	public int insertEnergy(int amount, ActionType actionType) {
		int insertRoom = maxEnergy - currentEnergy;
		int insertAmount = (amount <= insertRoom)? amount : insertRoom;
		if (actionType == ActionType.PERFORM) currentEnergy += insertAmount;
		onChanged();
		return insertAmount;
	}

	@Nonnull
	@Override
	public int extractEnergy(int amount, ActionType actionType) {
		int extractAmount = (amount <= currentEnergy)? amount : currentEnergy;
		if (actionType == ActionType.PERFORM) currentEnergy -= extractAmount;
		onChanged();
		return extractAmount;
	}

	void setCurrentEnergy(int amount) {
		if (amount <= maxEnergy) currentEnergy = amount;
		else currentEnergy = maxEnergy;
		onChanged();
	}

	void setMaxEnergy(int amount) {
		maxEnergy = amount;
		if (currentEnergy > maxEnergy) currentEnergy = maxEnergy;
		onChanged();
	}

	@Override
	public void listen(@Nonnull Runnable r) {
		listeners.add(r);
	}

	public void onChanged() {
		listeners.forEach(Runnable::run);
	}
}
