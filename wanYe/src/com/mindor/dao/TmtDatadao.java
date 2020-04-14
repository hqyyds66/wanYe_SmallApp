package com.mindor.dao;

import java.util.List;

import com.mindor.entity.TmtData;

public interface TmtDatadao {
    public void addTmtdata(TmtData tmtdata);
    public  List<TmtData> selectDataByUser(String userId,String humOrThings);
    public  List<TmtData> selectDataByUserEq(String userId,String equipmentId,String humOrThings);
    
    public String selectHisHum(String userId,String equipmentId,String yearMonth);
}
