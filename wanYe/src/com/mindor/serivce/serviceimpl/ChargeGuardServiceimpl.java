package com.mindor.serivce.serviceimpl;

import com.mindor.dao.ChargeGuarddao;
import com.mindor.entity.ChargeGuard;
import com.mindor.serivce.ChargeGuardService;

public class ChargeGuardServiceimpl implements ChargeGuardService {

	private ChargeGuarddao chargeGuarddao;

	@Override
	public ChargeGuard selectState(String userId, String equipmentId) {
		// TODO Auto-generated method stub
		ChargeGuard chargeGuard = chargeGuarddao.selectState(userId,
				equipmentId);
		return chargeGuard;
	}

	@Override
	public void updateState(String userId, String equipmentId, int phstate,
			int hmstate, int voicestate) {
		// TODO Auto-generated method stub
		chargeGuarddao.updateState(userId, equipmentId, phstate, hmstate,
				voicestate);

	}

	public ChargeGuarddao getChargeGuarddao() {
		return chargeGuarddao;
	}

	public void setChargeGuarddao(ChargeGuarddao chargeGuarddao) {
		this.chargeGuarddao = chargeGuarddao;
	}

	@Override
	public void addChargeGuard(ChargeGuard chargeGuard) {
		// TODO Auto-generated method stub
		chargeGuarddao.addChargeGuard(chargeGuard);

	}

	@Override
	public void delChargeGuard(String equipmentId) {
		// TODO Auto-generated method stub
		chargeGuarddao.delChargeGuard(equipmentId);
	}

}
