package db.dao;

import db.entity.GroupsEntity;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;

/**
 * Created by Kacper on 2017-07-11.
 */
public class GroupsDao {
    public void addGroupEntity(GroupsEntity bean){
        Session session = SessionUtil.sessionOpen();
        Transaction tx = session.beginTransaction();
        addGroupsEntity(session,bean);
        tx.commit();
        session.close();

    }

    private void addGroupsEntity(Session session, GroupsEntity bean){
        GroupsEntity groupsEntity = new GroupsEntity(bean.getName());
        session.save(groupsEntity);
    }

    public List<GroupsEntity> getGroupsEntitys(){
        Session session = SessionUtil.sessionOpen();
        Query query = session.createQuery("from GroupsEntity");
        List<GroupsEntity> groupsEntitys =  query.list();
        session.close();

        return groupsEntitys;
    }

    public GroupsEntity getGroupEntity(int id){
        Session session = SessionUtil.sessionOpen();
        GroupsEntity group =  session.get(GroupsEntity.class, id);
        session.close();
        return group;
    }

    public int deleteGroupsEntity(int id) {
        Session session = SessionUtil.sessionOpen();
        Transaction tx = session.beginTransaction();
        String hql = "delete from GroupsEntity where id = :id";
        Query query = session.createQuery(hql);
        query.setParameter("id",id);
        int rowCount = query.executeUpdate();
        System.out.println("Rows affected: " + rowCount);
        tx.commit();
        session.close();
        return rowCount;
    }

    public int updateGroupsEntity(int id, GroupsEntity emp){
        if(id <=0)
            return 0;
        Session session = SessionUtil.sessionOpen();
        Transaction tx = session.beginTransaction();
        String hql = "update GroupsEntity set name = :name where id = :id";
        Query query = session.createQuery(hql);
        query.setParameter("id",id);
        query.setParameter("name",emp.getName());
        int rowCount = query.executeUpdate();
        System.out.println("Rows affected: " + rowCount);
        tx.commit();
        session.close();
        return rowCount;
    }
}
