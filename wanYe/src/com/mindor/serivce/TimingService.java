package com.mindor.serivce;

import java.util.List;

import com.mindor.entity.Timing;

public interface TimingService {
	public void addTiming(Timing timing);
    public void updateTiming(Timing timing);
    public List<Timing> selectTimings(String equipmentId,String userId);
    public void deleteTiming(int timingId);
    public void deleteTimings(String equipmentId,String userId);
    public void deleteTimings(String equipmentId);
    public Timing selectTiming(int timingId);
    public Timing  selectTimingByDelayId(int timeDelayId);
    public Timing selectHomePage(String equipmentId,String userId);
}
