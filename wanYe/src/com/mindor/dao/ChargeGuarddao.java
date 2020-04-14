package com.mindor.dao;

import com.mindor.entity.ChargeGuard;

public interface ChargeGuarddao {
	public ChargeGuard selectState(String userId, String equipmentId);

	public void updateState(String userId, String equipmentId, int phstate,
			int hmstate, int voicestate);

	public void addChargeGuard(ChargeGuard chargeGuard);

	public void delChargeGuard(String equipmentId);
}
