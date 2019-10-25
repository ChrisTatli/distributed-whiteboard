package Server;

import java.rmi.AlreadyBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class AppServer {
    private static int port;
    public static void main(String[] args) throws RemoteException {
        if(args.length != 1){
            System.err.println("Usage: Port");
            System.exit(0);
        }
        port = Integer.parseInt(args[0]);


        Registry registry = LocateRegistry.createRegistry(port);
        try {
            registry.bind("draw", new DrawServant());
            registry.bind("management", new ManagementServant());
            registry.bind("chat", new ChatServant());
        } catch (AlreadyBoundException e) {
            System.err.println("Error setting up server on port " + port + ".");
            System.exit(-1);
        }
    }
}
