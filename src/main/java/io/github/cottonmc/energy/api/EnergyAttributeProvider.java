package io.github.cottonmc.energy.api;

/**
 * This interface MUST NOT be implemented on any subclass
 * of Block or BlockEntity.
 *
 * For BlockEntities use LibBlockAttributes' existing
 * capabilities. Blocks should never have the attribute.
 *
 * You're warned.
 */
public interface EnergyAttributeProvider {
    EnergyAttribute getEnergyAttribute();
}