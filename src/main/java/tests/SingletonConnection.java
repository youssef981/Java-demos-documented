package tests;

public class SingletonConnection {
    private static SingletonConnection connection;

    private SingletonConnection(){
    }

    public static enum Connection{
        connectionenum;
        SingletonConnection getInstance(){
            connection = new SingletonConnection();
            return connection;
        }
    }

}
