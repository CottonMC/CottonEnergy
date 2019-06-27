package io.github.cottonmc.energy.api;

import net.minecraft.text.TranslatableText;

public class ElectricalEnergyType implements EnergyType {

	private final int maximum;

	public ElectricalEnergyType(int maximum) {
		this.maximum = maximum;
	}

	@Override
	public int getMaximumTransferSize() {
		return maximum;
	}

	@Override
	public TranslatableText getDisplayAmount(int amount) {
		//TODO: handle KWU, MWU, and GWU
		if (amount < 1000) { // x < 1M
			return new TranslatableText("info.cotton.energy.electrical.amount", amount);
		}
		else if (amount < 1_000_000) { // 1K < x < 1M
			float tAmount = amount / 1000;
			return new TranslatableText("info.cotton.energy.electrical.amount.k", tAmount);
		}
		else if (amount < 1_000_000_000) { // 1M < x < 1G
			float tAmount = amount / 1_000_1000;
			return new TranslatableText("info.cotton.energy.electrical.amount.m", tAmount);
		}
		else { // 1G < x
			float tAmount = amount / 1_000_000_000;
			return new TranslatableText("info.cotton.energy.electrical.amount.g", tAmount);
		}
	}
	
	@Override
	public boolean isCompatibleWith(EnergyType type) {
		return type instanceof ElectricalEnergyType;
	}
	
	@Override
	public boolean isHarmful(EnergyType type) {
		for(EnergyType rank : DefaultEnergyTypes.VOLTAGES) {
			if (rank==this) return true; //We come first in the list, so we're a lower voltage than type
			if (rank==type) return false; //They come first in the list, so they're a lower voltage
		}
		
		//TODO: We should probably complain on the log here.
		return false; //We're not on the same voltage track. This should never happen.
	}
}
