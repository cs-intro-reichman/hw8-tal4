/** Represents a user in a social network. A user is characterized by a name,
 *  a list of user names that s/he follows, and the list's size. */
 public class User {

    // Maximum number of users that a user can follow
    static int maxfCount = 10;

    private String name;       // name of this user
    private String[] follows;  // array of user names that this user follows
    private int fCount;        // actual number of followees (must be <= maxfCount)

    /** Creates a user with an empty list of followees. */
    public User(String name) {
        this.name = name;
        follows = new String[maxfCount]; // fixed-size array for storing followees
        fCount = 0;                      // initial number of followees
    }

    /** Creates a user with some followees. The only purpose of this constructor is 
     *  to allow testing the toString and follows methods, before implementing other methods. */
    public User(String name, boolean gettingStarted) {
        this(name);
        follows[0] = "Foo";
        follows[1] = "Bar";
        follows[2] = "Baz";
        fCount = 3;
    }

    /** Returns the name of this user. */
    public String getName() {
        return name;
    }

    /** Returns the follows array. */
    public String[] getfFollows() {
        return follows;
    }

    /** Returns the number of users that this user follows. */
    public int getfCount() {
        return fCount;
    }

    /** If this user follows the given name, returns true; otherwise returns false. */
    public boolean follows(String name) {
        String givenNameLower = name.toLowerCase();
        for(int i = 0; i < fCount; i++){
            String otherNameLower = follows[i].toLowerCase();
            if(givenNameLower.equals(otherNameLower)){
                return true;
            }
        }
        return false;
    }
    /** Makes this user follow the given name. If successful, returns true. 
     *  If this user already follows the given name, or if the follows list is full, does nothing and returns false; */
    public boolean addFollowee(String name) {
        if(follows == null) {
            System.out.println("The follows list is null");
            return false;
        }
        if(fCount == maxfCount) {
            System.out.println("The follows list is full");
            return false;
        }
        if(follows(name)) {
            System.out.println("The user already follows this name");
            return false;
        }
        follows[fCount] = name;
        fCount++;
        System.out.println("The user " + this.name + " now follows " + name);
        return true;
    }

    /** Removes the given name from the follows list of this user. If successful, returns true.
     *  If the name is not in the list, does nothing and returns false. */
    public boolean removeFollowee(String name) {
        boolean isFound = false;
        for(int i = 0; i < fCount - 1; i++){
            if(isFound || follows[i].equals(name)){
                isFound = true;
                follows[i] = follows[i + 1];
            }
        }
        if(isFound || follows[fCount - 1].equals(name)){
            follows[fCount - 1] = null;
            fCount--;
            System.out.println("The user " + this.name + " no longer follows " + name);
            return true;            
        }
        else{
            System.out.println("The user " + this.name + " does not follow " + name);
            return false;
        }
    }

    /** Counts the number of users that both this user and the other user follow.
    /*  Notice: This is the size of the intersection of the two follows lists. */
    public int countMutual(User other) {
        int count = 0;
        for(int i = 0; i < fCount; i++){
            if(other.follows(follows[i])) {
                count++;
            }
        }
        return count;
    }

    /** Checks is this user is a friend of the other user.
     *  (if two users follow each other, they are said to be "friends.") */
    public boolean isFriendOf(User other) {
        return follows(other.name) && other.follows(name);
    }
    
    /** Returns this user's name, and the names that s/he follows. */
    public String toString() {
        String ans = name + " -> ";
        for (int i = 0; i < fCount; i++) {
            ans = ans + follows[i] + " ";
        }
        return ans;
    }
}
