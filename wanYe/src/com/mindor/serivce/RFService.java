package com.mindor.serivce;

import java.util.List;

import com.mindor.entity.Intermediate;
import com.mindor.entity.RF;

public interface RFService {
	public List<Intermediate> selectRfeq(String userId);//查询射频插座
    public List<RF> selectRf(String userId);//查询射频插座
    public void addRf(RF rf);
    public void delRf(int rfId);
    public void updRf(int rfId,String nickname,String btnName);
    public String state(String equipmentId);
    
    public String equipmentNote(String userId,String equipmentId);
}
