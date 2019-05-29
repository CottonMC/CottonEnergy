package io.github.cottonmc.energy.impl;

import alexiil.mc.lib.attributes.Simulation;
import com.google.common.collect.Lists;
import io.github.cottonmc.energy.api.DefaultEnergyTypes;
import io.github.cottonmc.energy.api.EnergyAttribute;
import io.github.cottonmc.energy.api.EnergyType;
import io.github.cottonmc.energy.api.Observable;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.IntTag;
import net.minecraft.nbt.Tag;

import javax.annotation.Nonnull;
import java.util.List;
import java.util.Optional;

public class SimpleEnergyAttribute implements EnergyAttribute, Observable {
	protected EnergyType energyType = DefaultEnergyTypes.LOW_VOLTAGE;
	protected int maxEnergy;
	protected int currentEnergy = 0;
	protected int harm;
	protected boolean saveMax = false;
	protected final List<Runnable> listeners = Lists.newArrayList();

	public SimpleEnergyAttribute(int maxEnergy) {
		this.maxEnergy = maxEnergy;
	}

	public SimpleEnergyAttribute(int maxEnergy, EnergyType type) {
		this.maxEnergy = maxEnergy;
		this.energyType = type;
	}
	
	/** Set this to "true" to save both current and max when serializing to NBT*/
	public SimpleEnergyAttribute setSaveMax(boolean saveMax) {
		this.saveMax = saveMax;
		return this;
	}
	
	public SimpleEnergyAttribute setCurrentEnergy(int amount) {
		int prev = currentEnergy;
		if (amount <= maxEnergy) currentEnergy = amount;
		else currentEnergy = maxEnergy;
		if (currentEnergy != prev) onChanged();
		return this;
	}

	public SimpleEnergyAttribute setMaxEnergy(int amount) {
		int prev = maxEnergy;
		maxEnergy = amount;
		if (currentEnergy > maxEnergy) currentEnergy = maxEnergy;
		if (maxEnergy != prev) onChanged();
		return this;
	}
	
	public void onChanged() {
		listeners.forEach(Runnable::run);
	}
	
	public void fromTag(Tag tag) {
		if (tag instanceof IntTag) {
			currentEnergy = ((IntTag)tag).getInt();
		} else if (tag instanceof CompoundTag) {
			CompoundTag obj = (CompoundTag)tag;
			currentEnergy = obj.getInt("Energy");
			if (obj.containsKey("MaxEnergy", 99)) {
				maxEnergy = obj.getInt("MaxEnergy");
			}
			if (obj.containsKey("Harm", 99)) {
				harm = obj.getInt("Harm");
			}
		}
	}
	
	public Tag toTag() {
		if (saveMax || harm>0) {
			CompoundTag result = new CompoundTag();
			result.putInt("Energy", currentEnergy);
			result.putInt("MaxEnergy", maxEnergy);
			if (harm>0) result.putInt("Harm", harm);
			return result;
		} else {
			return new IntTag(currentEnergy);
		}
	}
	
	public int getHarm() {
		return harm;
	}
	
	public void resetHarm() {
		this.harm = 0;
		onChanged();
	}
	
	//implements EnergyAttribute {
		
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
		
		@Override
		public int insertEnergy(EnergyType type, int amount, Simulation actionType) {
			Optional<Integer> converted = EnergyType.convert(type, amount, this.energyType);
			if (!converted.isPresent()) return amount; //converted is now in our locally-understood EnergyType.
			
			int insertAmount = converted.get();
			if (insertAmount < this.energyType.getMinimumTransferSize()) return amount; //Don't accept a transfer below the granularity.
			if (insertAmount > this.energyType.getMaximumTransferSize()) insertAmount = this.energyType.getMaximumTransferSize();
			
			int insertRoom = maxEnergy - currentEnergy;
			if (insertAmount > insertRoom) insertAmount = insertRoom;
			
			if (actionType == Simulation.ACTION) {
				currentEnergy += insertAmount;
				
				if (this.energyType.isHarmful(type)) {
					harm += insertAmount;
				}
				
				if (insertAmount != 0) onChanged();
			}
			
			return amount - EnergyType.convert(this.energyType, insertAmount, type).orElse(0); //Result is now in the *requested* EnergyType
		}
		
		@Override
		public int extractEnergy(EnergyType type, int amount, Simulation actionType) {
			Optional<Integer> converted = EnergyType.convert(type, amount, this.energyType);
			if (!converted.isPresent()) return 0; //converted is now in our locally-understood EnergyType.
			
			int extractAmount = converted.get();
			if (extractAmount < this.energyType.getMinimumTransferSize()) return 0; //Don't accept a transfer below the granularity.
			if (extractAmount > this.energyType.getMaximumTransferSize()) extractAmount = this.energyType.getMaximumTransferSize();
			
			if (extractAmount > currentEnergy) extractAmount = currentEnergy;
			
			if (actionType == Simulation.ACTION) {
				currentEnergy -= extractAmount;
				if (extractAmount != 0) onChanged();
			}
			return EnergyType.convert(this.energyType, extractAmount, type).orElse(0); //Result is now in the *requested* EnergyType
		}
		
		@Override
		public EnergyType getPreferredType() {
			return this.energyType;
		}
	//}
	
	//implements Observable {
		@Override
		public void listen(@Nonnull Runnable r) {
			listeners.add(r);
		}
	//}
}
