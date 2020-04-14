package com.mindor.dao;

import java.util.List;

import com.mindor.entity.MessageManage;

public interface MessageManagedao {
          public int addMessage(MessageManage messageManage);
          public void delMessage(String messageManageId);
          public void updorderIf(String messageManageId,String readIf);
          public List<MessageManage> selectMessage(String userId);
          public Boolean readIfs(String userId);
          
          public void updOperation(String messageManageId);
        	  
}
