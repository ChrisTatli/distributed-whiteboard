package Client;

import java.io.Serializable;
import java.util.UUID;

public class User implements Serializable {
    public String userId;
    public String userName;
    public boolean isAdmin;

    public User(){
        userName = new String();
        this.userId = UUID.randomUUID().toString();
        this.isAdmin = false;
    }

}
