/**
 * 
 */
package it.unibo.oop.lab.collections2;

import java.util.Collection;

public final class TestSocialNetworkUser {

    private static final String WRITERS = "writers";

    private TestSocialNetworkUser() {
    }

    public static void main(final String... args) {

        final SocialNetworkUser<User> kbacon = new SocialNetworkUserImpl<>("Kevin", "Bacon", "kbacon", 56);
        final SocialNetworkUser<User> dwashington = new SocialNetworkUserImpl<>("Denzel", "Washington", "dwashington", 59);
        final SocialNetworkUser<User> mgladwell = new SocialNetworkUserImpl<>("Malcom", "Gladwell", "mgladwell", 51);
        final SocialNetworkUser<User> ntaleb = new SocialNetworkUserImpl<>("Nicholas", "Taleb", "ntaleb", 54);
        final User asmith = new UserImpl("Adam", "Smith", "asmith");
        /*
         * Make people follow each other
         */
        mgladwell.addFollowedUser("acquaintances", ntaleb);
        dwashington.addFollowedUser("myths", asmith);
        dwashington.addFollowedUser(WRITERS, ntaleb);
        dwashington.addFollowedUser("colleagues", kbacon);
        dwashington.addFollowedUser(WRITERS, mgladwell);
        /*
         * All tests must return true
         */
        System.out.println("Smith has not set any age at all: " + (!asmith.isAgeDefined()));
        final Collection<User> kbaconFriends = kbacon.getFollowedUsersInGroup("Malcom");
        System.out.println("K Bacon must have no followed people called Malcom: " + kbaconFriends.isEmpty());
        final Collection<User> mgladFriends = mgladwell.getFollowedUsersInGroup("Close friends");
        System.out.println("M Gladwell has not set yet any group called \"Close friends\": " + mgladFriends.isEmpty());
        final Collection<User> dwashFriends = dwashington.getFollowedUsersInGroup(WRITERS);
        System.out.println("Denzel has 2 followed people in group \"writers\": " + (dwashFriends.size() == 2));
        /*
         * Adding another friend to Denzel's "writers" group...
         */
        dwashFriends.add(asmith);
        /*
         * The above operation *MUST* have no effect on Denzel's profile itself:
         * STILL TWO PEOPLE in denzel's group called writers
         */
        System.out.println("Denzel has STILL 2 followed people in group \"writers\": "
                + (dwashington.getFollowedUsersInGroup(WRITERS).size() == 2));

        //stampo follower di un user
        System.out.println(dwashington.getFollowedUsers());
    }
}
