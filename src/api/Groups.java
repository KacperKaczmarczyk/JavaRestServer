package api;

import db.dao.GroupsDao;
import db.dao.UsersDao;
import db.entity.GroupsEntity;
import db.entity.UsersEntity;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

/**
 * Created by Kacper on 2017-07-12.
 */
@Path("/groups")
public class Groups {

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<GroupsEntity> getGroups() {
        GroupsDao dao = new GroupsDao();
        List<GroupsEntity> groups  = dao.getGroupsEntitys();
        return groups;
    }
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public List<GroupsEntity> addGroups(@FormParam("name") String name){
        GroupsEntity group = new GroupsEntity(name);
        GroupsDao groupsDao = new GroupsDao();
        groupsDao.addGroupEntity(group);
        List<GroupsEntity> groupsEntityList;
        groupsEntityList = groupsDao.getGroupsEntitys();
        return groupsEntityList;
    }

}
