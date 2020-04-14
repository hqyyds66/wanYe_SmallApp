package com.mindor.serivce.serviceimpl;

import java.util.List;

import com.mindor.dao.Timingdao;
import com.mindor.entity.Timing;
import com.mindor.serivce.TimingService;

public class TimingServiceimpl implements TimingService {

	private Timingdao timingdao;

	@Override
	public void addTiming(Timing timing) {
		// TODO Auto-generated method stub
		timingdao.addTiming(timing);
	}

	@Override
	public void updateTiming(Timing timing) {
		// TODO Auto-generated method stub
		timingdao.updateTiming(timing);
	}

	public Timingdao getTimingdao() {
		return timingdao;
	}

	public void setTimingdao(Timingdao timingdao) {
		this.timingdao = timingdao;
	}

	@Override
	public List<Timing> selectTimings(String equipmentId, String userId) {
		// TODO Auto-generated method stub
		List<Timing> timing;
		timing = timingdao.selectTimings(equipmentId, userId);
		return timing;
	}

	@Override
	public void deleteTiming(int timingId) {
		// TODO Auto-generated method stub
		timingdao.deleteTiming(timingId);
	}

	@Override
	public void deleteTimings(String equipmentId, String userId) {
		// TODO Auto-generated method stub
		timingdao.deleteTimings(equipmentId, userId);
	}

	@Override
	public void deleteTimings(String equipmentId) {
		// TODO Auto-generated method stub
		timingdao.deleteTimings(equipmentId);
	}

	@Override
	public Timing selectTiming(int timingId) {
		// TODO Auto-generated method stub
		Timing timing = new Timing();
		timing = timingdao.selectTiming(timingId);
		return timing;
	}

	@Override
	public Timing selectTimingByDelayId(int timeDelayId) {
		// TODO Auto-generated method stub
		Timing timing = new Timing();
		timing = timingdao.selectTimingByDelayId(timeDelayId);
		return timing;
	}

	@Override
	public Timing selectHomePage(String equipmentId, String userId) {
		// TODO Auto-generated method stub
		Timing timing = new Timing();
		timing = timingdao.selectHomePage(equipmentId, userId);
		return timing;
	}

}
