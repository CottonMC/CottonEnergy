package io.github.cottonmc.energy.api;

import net.minecraft.nbt.CompoundTag;

public interface EnergyAttributeProvider {
    default EnergyAttribute getEnergy(CompoundTag tag) {
        return fromTag(tag);
    }
    EnergyAttribute fromTag(CompoundTag tag);
    void toTag(EnergyAttribute attribute, CompoundTag tag);
}