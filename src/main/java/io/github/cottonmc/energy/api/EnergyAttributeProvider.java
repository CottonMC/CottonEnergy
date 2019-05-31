package io.github.cottonmc.energy.api;

import net.minecraft.nbt.CompoundTag;

public interface EnergyAttributeProvider {
    EnergyAttribute getEnergy(CompoundTag tag);
    EnergyAttribute fromTag(CompoundTag tag);
    void toTag(EnergyAttribute attribute, CompoundTag tag);
}