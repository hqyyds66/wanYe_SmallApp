package com.mindor.dao.impl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.transform.Transformers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.mindor.dao.Equipmentdao;
import com.mindor.entity.Equipment;
import com.mindor.entity.Intermediate;
import com.mindor.entity.OpenUser;
import com.mindor.entity.ShareRecord;

public class Equipmenimpl extends HibernateDaoSupport implements Equipmentdao {

	@Resource(name = "sessionFactory")
	private SessionFactory sessionFactory;

	@SuppressWarnings( { "unchecked", "rawtypes" })
	public List query(String sql) {
		List<Map<String, Object>> Map = null;
		Query query = getSession().createSQLQuery(sql).setResultTransformer(
				Transformers.ALIAS_TO_ENTITY_MAP);
		Map = query.list();
		return Map;
	}

	public int excuteBySql(String sql) {
		int result;
		SQLQuery query = this.getSession().createSQLQuery(sql);
		result = query.executeUpdate();
		return result;
	}

	public void del(int id) {
		// TODO Auto-generated method stub

	}

	public Equipment findOne(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<String> equipmentList() {
		// TODO Auto-generated method stub
		List<String> equipmentList = null;
		try {
			Configuration cfg = new Configuration();
			sessionFactory = cfg.configure().buildSessionFactory();

		} catch (Throwable ex) {
			System.err.println("Failed to create sessionFactory object." + ex);
			throw new ExceptionInInitializerError(ex);
		}
		Session session = sessionFactory.openSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			Query q;
			String sql = "SELECT a.equipmentId FROM Equipment a"; // String
																	// sql="SELECT a FROM Product a limit "+(start-1)*pageSize+","+pageSize;
			q = session.createQuery(sql);
			equipmentList = q.list();

			System.gc();// 执行垃圾回收
			tx.commit();
		} catch (Exception e) {
			if (tx != null)
				tx.rollback();
			e.printStackTrace();
		} finally {
			session.getSessionFactory().close();
			session.close();
			System.gc();// 执行垃圾回收
		}

		return equipmentList;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<ShareRecord> subpermission(String equipmentId, String userId) {
		// TODO Auto-generated method stub
		String queryString = "select e from ShareRecord e where e.equipmentId='"
				+ equipmentId + "' and e.userIdRoot='" + userId + "'";
		OpenUser openUser = new OpenUser();
		ShareRecord shareRecord;
		List<ShareRecord> subpermissions = getHibernateTemplate().find(
				queryString);
		List<ShareRecord> mylist = new LinkedList<ShareRecord>();
		for (int i = 0; i < subpermissions.size(); i++) {
			shareRecord = new ShareRecord();
			openUser = getHibernateTemplate().get(OpenUser.class,
					subpermissions.get(i).getUserId());
			shareRecord.setEquipmentId(subpermissions.get(i).getEquipmentId());
			shareRecord.setPhone(openUser.getPhone());
			shareRecord.setShareRecordId(subpermissions.get(i)
					.getShareRecordId());
			shareRecord.setUserHead(openUser.getHeadPortrait());
			shareRecord.setUserId(subpermissions.get(i).getUserId());
			shareRecord.setUserIdRoot(subpermissions.get(i).getUserIdRoot());
			mylist.add(shareRecord);
		}

		return mylist;
	}

	@SuppressWarnings("rawtypes")
	@Override
	public List<String> bigSubpermission(String equipmentId) {
		// TODO Auto-generated method stub
		ArrayList<String> str = new ArrayList<String>();
		OpenUser openUser = new OpenUser();
		Intermediate intermediate = new Intermediate();
		String queryString = "select e from Intermediate e where e.equipmentId='"
				+ equipmentId + "' and e.role='1'";
		List userIdList = getHibernateTemplate().find(queryString);
		intermediate = (Intermediate) userIdList.get(0);

		String queryPhone = "select e from OpenUser e where e.userId='"
				+ intermediate.getUserId() + "'";
		List userList = getHibernateTemplate().find(queryPhone);
		openUser = (OpenUser) userList.get(0);

		if (openUser.getPhone().length() == 0) {
			str.add(openUser.getUserId());
			str.add(openUser.getHeadPortrait());
			return str;
		} else {
			str.add(openUser.getPhone());
			str.add(openUser.getHeadPortrait());
			return str;
		}

	}

	@SuppressWarnings("rawtypes")
	@Override
	public void ShareRecord(String userId, String userRoot, String equipmentId) {

		String queryString = "select e from ShareRecord e where e.equipmentId='"
				+ equipmentId
				+ "' and e.userIdRoot='"
				+ userRoot
				+ "' and e.userId='" + userId + "'";
		List Shares = getHibernateTemplate().find(queryString);
		OpenUser openUser = new OpenUser();
		ShareRecord ShareRecord = new ShareRecord();
		openUser = getHibernateTemplate().get(OpenUser.class, userId);
		ShareRecord.setPhone(openUser.getPhone());
		ShareRecord.setUserHead(openUser.getHeadPortrait());
		ShareRecord.setUserId(userId);
		ShareRecord.setUserIdRoot(userRoot);
		ShareRecord.setEquipmentId(equipmentId);

		System.out.println("Shares" + Shares);

		if (Shares.size() == 0) {
			getHibernateTemplate().save(ShareRecord);
		} else {
			ShareRecord = (ShareRecord) Shares.get(0);
			ShareRecord.setShareRecordId(ShareRecord.getShareRecordId());
			getHibernateTemplate().saveOrUpdate(ShareRecord);

		}
	}

	/*
	 * (non-Javadoc) 判断是否被解除绑定
	 * 
	 * @see com.mindor.dao.Equipmentdao#ifShareRecord(java.lang.String,
	 * java.lang.String)
	 */
	@SuppressWarnings("rawtypes")
	@Override
	public boolean ifShareRecord(String userId, String equipmentId) {
		// TODO Auto-generated method stub
		String queryString = "select e from ShareRecord e where e.equipmentId='"
				+ equipmentId + "' and e.userId='" + userId + "'";
		List Shares = getHibernateTemplate().find(queryString);
		if (Shares.size() == 0) {
			return false;
		} else {
			return true;
		}

	}

	/*
	 * (non-Javadoc) 查询设备名称
	 * 
	 * @see com.mindor.dao.Equipmentdao#equipmentName(java.lang.String,
	 * java.lang.String)
	 */
	@SuppressWarnings("rawtypes")
	@Override
	public String equipmentName(String userId, String equipmentId) {
		// TODO Auto-generated method stub
		Intermediate intermediate = new Intermediate();
		String name = null;
		String queryString = "select e from Intermediate e where e.equipmentId='"
				+ equipmentId + "' and e.userId='" + userId + "'";
		List equipment = getHibernateTemplate().find(queryString);
		if (equipment.size() > 0) {
			intermediate = (Intermediate) equipment.get(0);
			name = intermediate.getEquipmentNote();
		} else {
			name = "false";
		}

		return name;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Object> Intermediates(String hql) {
		// TODO Auto-generated method stub
		List<Object> intermediates = getHibernateTemplate().find(hql);
		return intermediates;
	}

	@SuppressWarnings( { "unchecked", "rawtypes" })
	@Override
	public void deleteRecord(final String hql) {
		// TODO Auto-generated method stub
		getHibernateTemplate().execute(new HibernateCallback() {
			public Object doInHibernate(Session session)
					throws HibernateException, SQLException {
				// TODO Auto-generated method stub
				session.createQuery(hql).executeUpdate();
				return null;
			}
		});
	}

	@SuppressWarnings( { "unchecked", "rawtypes" })
	@Override
	public List<Object> selectEquipmentBySql(final String sql) {
		// TODO Auto-generated method stub
		List<Object> equipments = (List<Object>) getHibernateTemplate()
				.execute(new HibernateCallback() {
					public Object doInHibernate(Session session)
							throws HibernateException, SQLException {
						// TODO Auto-generated method stub
						final List<Object> equipmentStr;
						// logger.debug( " debug " );
						// logger.error( " error " );
						equipmentStr = session.createSQLQuery(sql).list();
						return equipmentStr;
					}
				});
		return equipments;
	}

	@SuppressWarnings( { "unchecked", "rawtypes" })
	public void updateEquipmentByhql(final String hql) {
		getHibernateTemplate().execute(new HibernateCallback() {
			public Object doInHibernate(Session session)
					throws HibernateException, SQLException {
				// TODO Auto-generated method stub
				session.createQuery(hql).executeUpdate();
				return null;
			}
		});
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Object> selectEquipmentByHql(String hql) {
		// TODO Auto-generated method stub
		List<Object> equipments = getHibernateTemplate().find(hql);
		return equipments;
	}

	@Override
	public void saveIntermediate(Intermediate intermediate) {
		// TODO Auto-generated method stub
		getHibernateTemplate().save(intermediate);
	}

	@SuppressWarnings( { "unchecked", "rawtypes" })
	public void updEquipmentState(final String equipmentId, final int state) {
		// TODO Auto-generated method stub
		getHibernateTemplate().execute(new HibernateCallback() {
			public Object doInHibernate(Session session)
					throws HibernateException, SQLException {
				// TODO Auto-generated method stub
				String hql = "update Equipment e set e.equipmentState=" + state
						+ " where e.equipmentId='" + equipmentId + "'";
				Query query = session.createQuery(hql);
				query.executeUpdate();
				return null;
			}
		});

	}

	@SuppressWarnings( { "rawtypes", "unchecked" })
	@Override
	public void updEquipmentState() {
		// TODO Auto-generated method stub
		getHibernateTemplate().execute(new HibernateCallback() {
			public Object doInHibernate(Session session)
					throws HibernateException, SQLException {
				// TODO Auto-generated method stub
				String hql = "update Equipment e set e.equipmentState=1";
				Query query = session.createQuery(hql);
				query.executeUpdate();
				return null;
			}
		});
	}

	@Override
	public String equVersion(String equipmentId) {
		// TODO Auto-generated method stub
		String queryString = "select e.softVersion from Equipment e where e.equipmentId='"
				+ equipmentId + "'";
		String name = getHibernateTemplate().find(queryString).toString();
		name = name.substring(1, name.indexOf("]"));
		return name;
	}

	@SuppressWarnings( { "rawtypes", "unchecked" })
	@Override
	public void updLocation(final String equipmentId, final String locationStr,
			final String longitude, final String latitude) {
		// TODO Auto-generated method stub
		getHibernateTemplate().execute(new HibernateCallback() {
			public Object doInHibernate(Session session)
					throws HibernateException, SQLException {
				// TODO Auto-generated method stub
				String hql = "update Equipment e set e.locationStr='"
						+ locationStr + "',e.longitude='" + longitude
						+ "',e.latitude='" + latitude
						+ "' where e.equipmentId='" + equipmentId + "'";
				Query query = session.createQuery(hql);
				query.executeUpdate();
				return null;
			}
		});
	}

	@SuppressWarnings("unchecked")
	@Override
	public void deleteInfrared(final String equipmentId) {
		// TODO Auto-generated method stub
		getHibernateTemplate().execute(new HibernateCallback() {
			public Object doInHibernate(Session session)
					throws HibernateException, SQLException {
				// TODO Auto-generated method stub
				String hql = "delete from InfraredBin e where e.equipmentId='" + equipmentId + "'";
				Query query = session.createQuery(hql);
				query.executeUpdate();
				return null;
			}
		});
	}

	@SuppressWarnings("unchecked")
	@Override
	public void deleteInfrared(final String equipmentId, final String userId) {
		// TODO Auto-generated method stub
		getHibernateTemplate().execute(new HibernateCallback() {
			public Object doInHibernate(Session session)
					throws HibernateException, SQLException {
				// TODO Auto-generated method stub
				String hql = "delete from InfraredBin e  where e.equipmentId='" + equipmentId + "' and e.userId='"+userId+"'";
				Query query = session.createQuery(hql);
				query.executeUpdate();
				return null;
			}
		});
	}

	@Override
	public String tokenGet(String userId) {
		// TODO Auto-generated method stub
		String hql="select o.access_token from OAuth o where o.userId='"+userId+"'";
		String access_token=getHibernateTemplate().find(hql).toString();
		access_token=access_token.substring(1, access_token.indexOf("]"));
		return access_token;
	}

	@Override
	public String phoneGet(String userId) {
		// TODO Auto-generated method stub
		String hql="select i.phone from OpenUser i where i.userId='"+userId+"'";
		String phone=getHibernateTemplate().find(hql).toString();
		phone=phone.substring(1, phone.indexOf("]"));
		return phone;
	}
	
	public List<Object> getAllEquipmentByUserId(String userId){
		String hql="SELECT b.equipmentId,b.equipmentName,d.equipmentNote,d.equipmentLabel,d.specificEquipmentLabel,a.productId,a.productIcon,a.productImage,b.equipmentState,d.role,d.warnValue,d.blackout,d.warnOperate FROM Product a,Equipment b,OpenUser c,Intermediate d WHERE  a.productId=d.productId AND b.equipmentId=d.equipmentId AND c.userId=d.userId AND c.userId='" + 
				userId + "'";
		List<Object> equipments = getHibernateTemplate().find(hql);
		return equipments;
	}

}
