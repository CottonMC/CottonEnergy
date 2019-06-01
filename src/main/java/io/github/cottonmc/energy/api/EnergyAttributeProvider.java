package io.github.cottonmc.energy.api;

/**
 * This should NEVER ever be used on ANYTHING that's NOT
 * an ENTITY or WORLD.
 *
 * For BlockEntities use LibBlockAttributes' existing
 * capabilities. Blocks should never have the attribute.
 *
 * You're warned.
 */
public interface EnergyAttributeProvider {
    EnergyAttribute getEnergyAttribute();
}