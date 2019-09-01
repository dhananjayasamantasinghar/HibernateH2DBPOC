package com.h2.repo;

import java.util.List;

import javax.persistence.criteria.CriteriaQuery;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.h2.model.User;

@Repository
public class UserRepo {

	@Autowired
	private SessionFactory sessionFactory;

	public User save(User user) {
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		Integer id = (Integer) session.save(user);
		session.getTransaction().commit();
		User userSaved = session.get(User.class, id);
		session.close();
		return userSaved;
	}

	public List<User> findAll() {
		Session session = sessionFactory.getCurrentSession();
		CriteriaQuery<User> criteriaQuery = session.getCriteriaBuilder().createQuery(User.class);
		criteriaQuery.from(User.class);
		List<User> resultList = session.createQuery(criteriaQuery).getResultList();
		session.close();
		return resultList;

	}

}
