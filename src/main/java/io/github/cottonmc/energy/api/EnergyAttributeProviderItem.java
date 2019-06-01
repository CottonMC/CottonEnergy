package io.github.cottonmc.energy.api;

import net.minecraft.item.ItemStack;

/**
 * This class MUST NOT be implemented on any class that 
 * doesn't also subclass Item.
 *
 * Once you've made changes to the attribute in the item,
 * you probably want to set the ItemStack into the
 * inventory or ItemAttribute and mark it dirty too. Like
 * if it's sitting in a chest, you want to mark the chest
 * or block inventory dirty, since the ItemStack doesn't
 * know what block or inventory it's in.
 *
 * You're warned.
 */
public interface EnergyAttributeProviderItem {
    EnergyAttribute getEnergyAttribute(ItemStack itemStack);
}