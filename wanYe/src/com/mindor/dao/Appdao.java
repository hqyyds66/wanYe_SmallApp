package com.mindor.dao;

import com.mindor.entity.AppVersion;

public interface Appdao {
	public AppVersion selectVersionsById(int AppVersionId);

	public void updateWjMinstore(String wjMinstore);
}
