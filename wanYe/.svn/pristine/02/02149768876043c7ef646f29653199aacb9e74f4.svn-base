package com.mindor.serivce.serviceimpl;

import com.mindor.dao.Appdao;
import com.mindor.entity.AppVersion;
import com.mindor.serivce.AppService;

public class AppServiceImpl implements AppService {

	private Appdao appdao;

	public Appdao getAppdao() {
		return appdao;
	}

	public void setAppdao(Appdao appdao) {
		this.appdao = appdao;
	}

	@Override
	public AppVersion selectVersionsById(int AppVersionId) {
		AppVersion versions = null;
		versions = appdao.selectVersionsById(AppVersionId);
		return versions;
	}

	@Override
	public void updateWjMinstore(String wjMinstore) {
		// TODO Auto-generated method stub
		appdao.updateWjMinstore(wjMinstore);
	}

}
