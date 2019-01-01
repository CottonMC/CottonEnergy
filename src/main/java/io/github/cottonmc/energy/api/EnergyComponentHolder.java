package io.github.cottonmc.energy.api;

import javax.annotation.Nullable;

/** Interface for energy holders for which sides don't make sense. Can probably go away fairly soon in favor of real-ish ECS*/
public interface EnergyComponentHolder {
    /**
     * @return the primary energy type to display in guis and probes.
     */
    EnergyType getPrimaryEnergyType();

    /**
     * @return the list of all EnergyTypes this block can handle. Note: This may include EnergyTypes which short out this
     * block!
     */
    default EnergyType[] getEnergyTypes() {
        return new EnergyType[] { getPrimaryEnergyType() };
    }

    /**
     * @param energyTypes      the kinds of energy the caller is interested in.
     * @return whether this block offers energy connectivity for one or more of the provided types on that side.
     */
    boolean canConnectTo(EnergyType...energyTypes);

    /**
     * Returns an EnergyComponent responsible for handling interactions of the given Direction and EnergyType. This may
     * include components offered solely to make the block slowly short out when the wrong EnergyType is offered!
     * 
     * @param energyType       the kind of energy being interacted with.
     * @return an EnergyComponent responsible for the given EnergyType, or null if no component is offered for that Direction and EnergyType.
     */
    @Nullable
    EnergyComponent getEnergyComponent(EnergyType energyType);
}
