package com.manageuser.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.manageuser.poj.Login;
import com.manageuser.poj.User;

@Repository
@Transactional//(propagation=Propagation.MANDATORY)
public class UserDao {
  
  @Autowired
  private SessionFactory _sessionFactory;
  
  private Session getSession() {
    return _sessionFactory.getCurrentSession();
  }

  public void save(User user) {
    getSession().save(user);
    return;
  }
  
  public void delete(User user) {
    getSession().delete(user);
    return;
  }
  
  @SuppressWarnings("unchecked")
  public List<User> getAll() {
    return getSession().createQuery("from User").list();
  }
  
  public User getById(int id) {
    // return (User) getSession().load(User.class, id);
	  User user = null;
		try {
			user = (User) getSession().createQuery("from User where id = :id")
												.setParameter("id", id)
												.uniqueResult();
			return user;	
				
		}
		catch(Exception ex)
		{
			// logger.error("Invalid login Attmpt: " + ex.getMessage());
			user = null;
		}
		return user;
  }

  public void update(User user) {
    getSession().update(user);
    return;
  }
  
  	@Transactional
	public User validateUser(Login login) {
		User user = null;
		try {
			user = (User) getSession().createQuery("from User where username = :uname "
												+ " and password = :pwd" )
												.setParameter("uname", login.getUsername())
												.setParameter("pwd", login.getPassword())
												.uniqueResult();
			return user;	
				
		}
		catch(Exception ex)
		{
			// logger.error("Invalid login Attmpt: " + ex.getMessage());
			user = null;
		}
		return user;
  	}
	

} // class UserDao
