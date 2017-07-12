package db.dao;

import db.entity.UsersEntity;
import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;


import java.util.List;

/**
 * Created by Kacper on 2017-07-10.
 */
public class UsersDao{

    public void addUsersEntity(UsersEntity bean){
        Session session = SessionUtil.sessionOpen();
        Transaction tx = session.beginTransaction();
        addUsersEntity(session,bean);
        tx.commit();
        session.close();

    }

    private void addUsersEntity(Session session, UsersEntity bean){
        UsersEntity usersEntity = new UsersEntity();
        usersEntity.setDateOfBirth(bean.getDateOfBirth());
        usersEntity.setFirstName(bean.getFirstName());
        usersEntity.setLastName(bean.getLastName());
        usersEntity.setGroup(bean.getGroup());
        usersEntity.setLogin(bean.getLogin());
        usersEntity.setPassword(bean.getPassword());

        session.save(usersEntity);
    }

    public List<UsersEntity> getUsersEntitys(){
        Session session = SessionUtil.sessionOpen();
        Query query = session.createQuery("from UsersEntity");
        List<UsersEntity> usersEntitys =  query.list();
        session.close();

        return usersEntitys;
    }

    public UsersEntity getUserEntity(int id){
        Session session = SessionUtil.sessionOpen();
        UsersEntity user =  session.get(UsersEntity.class, id);
        session.close();
        return user;
    }

    public int deleteUsersEntity(int id) {
        Session session = SessionUtil.sessionOpen();
        Transaction tx = session.beginTransaction();
        String hql = "delete from UsersEntity where id = :id";
        Query query = session.createQuery(hql);
        query.setParameter("id",id);
        int rowCount = query.executeUpdate();
        System.out.println("Rows affected: " + rowCount);
        tx.commit();
        session.close();
        return rowCount;
    }

    public int updateUsersEntity(int id, UsersEntity emp){
        if(id <=0)
            return 0;
        Session session = SessionUtil.sessionOpen();
        Transaction tx = session.beginTransaction();
        String hql = "update UsersEntity set login = :login, firstName = :firstName, lastName = :lastName, dateOfBirth = :dateOfBirth, password = :password where id = :id";
        Query query = session.createQuery(hql);
        query.setParameter("id",id);
        query.setParameter("firstName",emp.getFirstName());
        query.setParameter("lastName",emp.getLastName());
        query.setParameter("dateOfBirth",emp.getDateOfBirth());
        query.setParameter("password",emp.getPassword());
        int rowCount = query.executeUpdate();
        System.out.println("Rows affected: " + rowCount);
        tx.commit();
        session.close();
        return rowCount;
    }




}
