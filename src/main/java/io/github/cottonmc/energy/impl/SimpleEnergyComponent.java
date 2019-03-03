package io.github.cottonmc.energy.impl;

import com.google.common.collect.Lists;
import io.github.cottonmc.energy.api.EnergyComponent;
import io.github.cottonmc.energy.api.Observable;
import io.github.prospector.silk.util.ActionType;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.IntTag;
import net.minecraft.nbt.Tag;

import javax.annotation.Nonnull;
import java.util.List;

public class SimpleEnergyComponent implements EnergyComponent, Observable {

	protected int maxEnergy;
	protected int currentEnergy = 0;
	protected boolean saveMax = false;
	protected final List<Runnable> listeners = Lists.newArrayList();

	public SimpleEnergyComponent(int maxEnergy) {
		this.maxEnergy = maxEnergy;
	}
	
	/** Set this to "true" to save both current and max when serializing to NBT*/
	public SimpleEnergyComponent setSaveMax(boolean saveMax) {
		this.saveMax = saveMax;
		return this;
	}
	
	public SimpleEnergyComponent setCurrentEnergy(int amount) {
		int prev = currentEnergy;
		if (amount <= maxEnergy) currentEnergy = amount;
		else currentEnergy = maxEnergy;
		if (currentEnergy != prev) onChanged();
		return this;
	}

	public SimpleEnergyComponent setMaxEnergy(int amount) {
		int prev = maxEnergy;
		maxEnergy = amount;
		if (currentEnergy > maxEnergy) currentEnergy = maxEnergy;
		if (maxEnergy != prev) onChanged();
		return this;
	}
	
	public void onChanged() {
		listeners.forEach(Runnable::run);
	}
	
	
	//implements EnergyComponent {
		
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
			if (actionType == ActionType.PERFORM) {
				currentEnergy += insertAmount;
				if (insertAmount != 0) onChanged();
			}
			return insertAmount;
		}
	
		@Nonnull
		@Override
		public int extractEnergy(int amount, ActionType actionType) {
			int extractAmount = (amount <= currentEnergy)? amount : currentEnergy;
			if (actionType == ActionType.PERFORM) {
				currentEnergy -= extractAmount;
				if (extractAmount != 0) onChanged();
			}
			return extractAmount;
		}
	//}
	
	//implements Observable {
		@Override
		public void listen(@Nonnull Runnable r) {
			listeners.add(r);
		}
	//}
		
	//implements Component {
		@Override
		public void fromTag(Tag tag) {
			if (tag instanceof IntTag) {
				currentEnergy = ((IntTag)tag).getInt();
			} else if (tag instanceof CompoundTag) {
				CompoundTag obj = (CompoundTag)tag;
				currentEnergy = obj.getInt("Energy");
				if (obj.containsKey("MaxEnergy", 99)) {
					maxEnergy = obj.getInt("MaxEnergy");
				}
			}
		}
	
		@Override
		public Tag toTag() {
			if (saveMax) {
				CompoundTag result = new CompoundTag();
				result.putInt("Energy", currentEnergy);
				result.putInt("MaxEnergy", maxEnergy);
				return result;
			} else {
				return new IntTag(currentEnergy);
			}
		}
	//}
}
