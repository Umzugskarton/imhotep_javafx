package database;


public class DbParam {

    private IDbConnection connection = null;

    public void setConnection(final IDbConnection CONNECTION){
        connection = CONNECTION;
    }

    public void open(){
        if(connection != null){
            connection.open();
        }
    }

    public void close(){
        if(connection != null){
            connection.close();
        }
    }
}
