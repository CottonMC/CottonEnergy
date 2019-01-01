package io.github.cottonmc.energy.impl;

import io.github.cottonmc.energy.api.EnergyComponent;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.Tag;

public class EnergySerializer {

	public static Tag serialize(EnergyComponent instance) {
		CompoundTag tag = new CompoundTag();
		tag.putInt("CurrentEnergy", instance.getCurrentEnergy());
		tag.putInt("MaxEnergy", instance.getMaxEnergy());

		return tag;
	}

	public static void deserialize(EnergyComponent instance, Tag nbt) {
		if(instance instanceof EnergyHandler) {
			if (!(nbt instanceof CompoundTag)) return;
			CompoundTag tag = (CompoundTag)nbt;

			((EnergyHandler)instance).setCurrentEnergy(tag.getInt("CurrentEnergy"));
			((EnergyHandler)instance).setMaxEnergy(tag.getInt("MaxEnergy"));
		}
	}

}
