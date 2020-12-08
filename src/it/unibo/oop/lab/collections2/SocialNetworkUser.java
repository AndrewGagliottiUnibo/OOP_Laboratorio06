package it.unibo.oop.lab.collections2;

import java.util.Collection;
import java.util.List;

public interface SocialNetworkUser<U extends User> extends User {

    boolean addFollowedUser(String group, U user);

    List<U> getFollowedUsers();

    Collection<U> getFollowedUsersInGroup(String groupName);

}
