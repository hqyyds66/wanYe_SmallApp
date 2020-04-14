package com.mindor.dao;

import java.io.Serializable;
import java.util.List;

import com.mindor.entity.ClientId;
import com.mindor.entity.OpenUser;

public interface Userdao {
        public OpenUser selectNamebyId(String userId);
        
        public List<Object> openUsers(String hql);
        
        public List<String> userIds(String hql);
        
        public Serializable saveOpuser(OpenUser openUser);
        
        public OpenUser selectOpUser(String userId);
        
        public void upaClientId(ClientId clientId);
        
        public void upOpuser(OpenUser openUser);
        
        public List<String> selectStr(String hql);
        
        public List<String> allIds();
        
}
