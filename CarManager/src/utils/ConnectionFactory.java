package utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author Alexandre Marques
 */
public class ConnectionFactory
{
    private static final String URL      = ConfigurationManager.getInstance().getProperties( "prop.url" );
    private static final String USER     = ConfigurationManager.getInstance().getProperties( "prop.user" );
    private static final String PASSWORD = ConfigurationManager.getInstance().getProperties( "prop.password" );
    private static final String DRIVER   = ConfigurationManager.getInstance().getProperties( "prop.driver" );
    
    private static ConnectionFactory instance;
    private static Connection        connection;
    private static PreparedStatement statement;
    private static ResultSet         rs;
    
    public ConnectionFactory()
    {
        connection();
    }
    
    public static ConnectionFactory getInstance()
    {
        if( instance == null )
        {
            instance = new ConnectionFactory();
        }
        
        return instance;
    }
    
    public static Connection connection()
    {
        try
        {
            Class.forName( DRIVER );
            
            return connection = DriverManager.getConnection( URL, USER, PASSWORD );
        }
        
        catch( ClassNotFoundException | SQLException ex )
        {
            throw new RuntimeException( ex );
        }
    }
    
    public synchronized void executeQuery( String sql ) throws Exception
    {
        if( connection != null )
        {
            statement = connection.prepareStatement( sql );
            
            statement.execute();
            
            statement.close();
        }
    }
    
    public void closeConnection()
    {
        try
        {
            if( connection != null && rs != null )
            {
                statement.close();
            }
        }
        
        catch( SQLException ex )
        {
            throw new RuntimeException( ex );
        }
    }
}