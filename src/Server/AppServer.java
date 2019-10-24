package Server;

import java.rmi.AlreadyBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class AppServer {

    public static void main(String[] args) throws RemoteException {
        Registry registry = LocateRegistry.createRegistry(5099);
        try {
            registry.bind("draw", new DrawServant());
            registry.bind("management", new ManagementServant());
            registry.bind("chat", new ChatServant());
        } catch (AlreadyBoundException e) {
            e.printStackTrace();
        }
    }
}
