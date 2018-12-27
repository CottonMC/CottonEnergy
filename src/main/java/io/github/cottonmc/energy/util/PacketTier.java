package io.github.cottonmc.energy.util;

public enum PacketTier {
	//TODO: values not final, discuss
	/**
	 * @value TIER_1: base level. Coal-powered, early-game machines.
	 * @value TIER_2: mid-level. Coal coke-powered, first industrial machines.
	 * @value TIER_3: high-level. ??????-powered, medium-scale machines.
	 * @value TIER_4: endgame. Uranium-powered, large-scale industrial setups.
	 */
	TIER_1(40), TIER_2(80), TIER_3(160), TIER_4(320);

	private final int size;

	PacketTier(int size) {
		this.size = size;
	}

	public int getPacketSize() {
		return size;
	}
}
