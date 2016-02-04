package module.service;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import module.dao._07_StoreDAO;
import module.dao._07_Store_InterfaceDAO;
import module.model._07_StoreVO;
import module.util.HibernateUtil;

public class StoreService {
	private SessionFactory sessionFactory;
	public StoreService(){
		dao = new _07_StoreDAO();
		sessionFactory = HibernateUtil.getSessionFactory();
	}
	public Session getSession() {
		if (sessionFactory != null) {
			return sessionFactory.getCurrentSession();
		}
		return null;
	}
	private _07_Store_InterfaceDAO dao;
	
	
	public static void main(String args[]){
		try {
			HibernateUtil.getSessionFactory().getCurrentSession().beginTransaction();
			StoreService dao=new StoreService();
			dao.xxxx("g");
			HibernateUtil.getSessionFactory().getCurrentSession().getTransaction().commit();
		}finally{
			HibernateUtil.closeSessionFactory();
		}
		
	}
	public void xxxx(String k){
		System.out.println(dao.getStoreName(k));
	}
}
