package com.mindor.serivce.serviceimpl;

import com.mindor.dao.TimeDelaydao;
import com.mindor.entity.TimeDelay;
import com.mindor.serivce.TimeDelayService;

public class TimeDelayServiceimpl implements TimeDelayService {

	private TimeDelaydao timeDelaydao;

	public TimeDelaydao getTimeDelaydao() {
		return timeDelaydao;
	}

	public void setTimeDelaydao(TimeDelaydao timeDelaydao) {
		this.timeDelaydao = timeDelaydao;
	}

	@Override
	public Integer addTimeDelay(TimeDelay timeDelay) {
		// TODO Auto-generated method stub
		Integer timeDelayId = timeDelaydao.addTimeDelay(timeDelay);
		return timeDelayId;
	}

	@Override
	public void deleteTimeDelay(int timeDelayId) {
		// TODO Auto-generated method stub
		timeDelaydao.deleteTimeDelay(timeDelayId);
	}

	@Override
	public void deleteTimeDelays(String equipmentId, String userId) {
		// TODO Auto-generated method stub
		timeDelaydao.deleteTimeDelays(equipmentId, userId);
	}

	@Override
	public void deleteTimeDelays(String equipmentId) {
		// TODO Auto-generated method stub
		timeDelaydao.deleteTimeDelays(equipmentId);
	}

	@Override
	public TimeDelay selectTimeDelays(String equipmentId, String userId) {
		// TODO Auto-generated method stub
		TimeDelay TimeDelay = timeDelaydao
				.selectTimeDelays(equipmentId, userId);
		return TimeDelay;
	}

	@Override
	public TimeDelay selectTimeDelay(int timeDelayId) {
		// TODO Auto-generated method stub
		TimeDelay timeDelay = timeDelaydao.selectTimeDelay(timeDelayId);
		return timeDelay;
	}

	@Override
	public void updateTimeDelay(TimeDelay timeDelay) {
		// TODO Auto-generated method stub
		timeDelaydao.updateTimeDelay(timeDelay);
	}

}
