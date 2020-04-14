package com.mindor.dao.impl;

import java.util.List;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.mindor.dao.TmtDatadao;
import com.mindor.entity.TmtData;

public class TmtDataImpl extends HibernateDaoSupport implements TmtDatadao{

	@Override
	public void addTmtdata(TmtData tmtdata) {
		// TODO Auto-generated method stub
		getHibernateTemplate().save(tmtdata);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<TmtData> selectDataByUser(String userId,String humOrThings) {
		// TODO Auto-generated method stub
		System.out.println("是人还是物体："+humOrThings);
		List<TmtData> tmtDatas;
		String hql="select t from TmtData t where t.userId='"+userId+"'";
		if(humOrThings!=null&&humOrThings!="") {
			hql+=" and t.humOrThings='"+humOrThings+"'";
		}
		hql+="ORDER BY t.dataTime DESC";
		tmtDatas=getHibernateTemplate().find(hql);
		return tmtDatas;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<TmtData> selectDataByUserEq(String userId, String equipmentId,String humOrThings) {
		// TODO Auto-generated method stub
		System.out.println("是人还是物体："+humOrThings);
		List<TmtData> tmtDatas;
		String hql="select t from TmtData t where t.userId='"+userId+"' and t.equipmentId='"+equipmentId+"'";
		if(humOrThings!=null&&humOrThings!="") {
			hql+=" and t.humOrThings='"+humOrThings+"'";
		}
		hql+="ORDER BY t.dataTime DESC";
		tmtDatas=getHibernateTemplate().find(hql);
		return tmtDatas;
		
	}

	@Override
	public String selectHisHum(String userId, String equipmentId,String yearMonth) {
		// TODO Auto-generated method stub
		
        String hql="select ROUND(AVG(t.temp),2) from TmtData t where t.userId='"+userId+"' and t.equipmentId='"+equipmentId+"' and t.humOrThings='0' and t.yearMonth='"+yearMonth+"'";
        List values=getHibernateTemplate().find(hql);
        String value = null;
        if(values.size()>0) {
        	if(values.get(0)!=null) {
        		value=values.get(0).toString();
        	}else {
        		value=null;
        	}
        }else {
        	value=null;
        }
		return value;
	}
         
}
