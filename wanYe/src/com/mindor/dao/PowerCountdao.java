package com.mindor.dao;


public interface PowerCountdao {
      public String powerList(String equipmentId,String time,String timeStr);
      
      public void delPower(String equipmentId);
}
