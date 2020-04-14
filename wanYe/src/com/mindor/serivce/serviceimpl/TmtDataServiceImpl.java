package com.mindor.serivce.serviceimpl;

import java.util.List;

import com.mindor.dao.TmtDatadao;
import com.mindor.entity.TmtData;
import com.mindor.serivce.TmtDataService;

public class TmtDataServiceImpl implements TmtDataService{
	
	private TmtDatadao tmtDatadao;

	@Override
	public void addTmtdata(TmtData tmtdata) {
		// TODO Auto-generated method stub
		tmtDatadao.addTmtdata(tmtdata);
	}

	public TmtDatadao getTmtDatadao() {
		return tmtDatadao;
	}

	public void setTmtDatadao(TmtDatadao tmtDatadao) {
		this.tmtDatadao = tmtDatadao;
	}

	@Override
	public List<TmtData> selectDataByUser(String userId,String humOrThings) {
		// TODO Auto-generated method stub
		return tmtDatadao.selectDataByUser(userId,humOrThings);
	}

	@Override
	public List<TmtData> selectDataByUserEq(String userId, String equipmentId,String humOrThings) {
		// TODO Auto-generated method stub
		return tmtDatadao.selectDataByUserEq(userId, equipmentId,humOrThings);
	}

	@Override
	public String selectHisHum(String userId, String equipmentId,String yearMonth) {
		// TODO Auto-generated method stub
		return tmtDatadao.selectHisHum(userId,equipmentId,yearMonth);
	}

}
