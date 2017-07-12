package api;

import db.dao.GroupsDao;
import db.dao.UsersDao;
import db.entity.GroupsEntity;
import db.entity.UsersEntity;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@Path("/users")
public class Users {


    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<UsersEntity> getUsers() {
        UsersDao dao = new UsersDao();
        List<UsersEntity> users  = dao.getUsersEntitys();
        return users;
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public List<GroupsEntity> addUser(@FormParam("login") String login, @FormParam("firstName") String firstName, @FormParam("lastName") String lastName,  @FormParam("password") String password, @FormParam("dateOfBirth") String dateOfBirth, @FormParam("groups[]") List<Integer> groups){
//        System.out.print("POST z formularza: " + groups.get(0) + " " + groups.get(1));

        UsersEntity user = new UsersEntity(login, firstName,lastName,dateToString(dateOfBirth),password);

        List<GroupsEntity> groupsList = new ArrayList<GroupsEntity>();
        for (Integer id:groups) {
            groupsList.add(getGroup(id));
        }

        user.setGroup(groupsList);

//        for (GroupsEntity groupsEntity:groupsList) {
//            List<UsersEntity> userList = groupsEntity.getUsers();
//            userList.add(user);
//            groupsEntity.setUsers(userList);
//        }


        UsersDao usersDao = new UsersDao();
        usersDao.addUsersEntity(user);
        return groupsList;
    }

    private Timestamp dateToString(String dateOfBirth) {
        Timestamp timestamp;
        try{
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
            Date parsedDate = dateFormat.parse(dateOfBirth);
            timestamp = new java.sql.Timestamp(parsedDate.getTime());
            return timestamp;
        }catch(Exception e){//this generic but you can control another types of exception
            return null;
        }
    }

    @Path("{user}/groups")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<GroupsEntity> getUsersGroups(@PathParam("user") int id) {
        UsersDao dao = new UsersDao();
        UsersEntity user = dao.getUserEntity(id);

        List<GroupsEntity> groups  = user.getGroup();
        return groups;
    }

    @Path("{user}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public UsersEntity getUser(@PathParam("user") int id) {
        UsersDao dao = new UsersDao();
        UsersEntity user  = dao.getUserEntity(id);
        System.out.println("user: " + id);

        return user;
    }

    public GroupsEntity getGroup(int id){
        GroupsDao groupsDao = new GroupsDao();
        GroupsEntity groupsEntity;
        groupsEntity = groupsDao.getGroupEntity(id);
        return groupsEntity;
    }



}