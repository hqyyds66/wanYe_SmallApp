package com.mindor.serivce;

import com.mindor.entity.ChargeGuard;

public interface ChargeGuardService {
	public ChargeGuard selectState(String userId,String equipmentId);
    public void updateState(String userId,String equipmentId,int phstate,int hmstate,int voicestate);
    public void addChargeGuard(ChargeGuard chargeGuard);
    public void delChargeGuard(String equipmentId);
}
