package utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

/**
 *
 * @author Alexandre Marques - Josias
 */
public class ConfigurationManager
{
    private static ConfigurationManager instance;
    private static Properties properties;

    public ConfigurationManager()
    {
        loadProperties();
    }

    public static ConfigurationManager getInstance()
    {
        if ( instance == null )
        {
            instance = new ConfigurationManager();
        }

        return instance;
    }

    private void loadProperties()
    {
        try
        {
            if ( properties == null )
            {
                properties = new Properties();

                FileInputStream file = new FileInputStream( "configuration.properties" );

                properties.load( file );

                file.close();
            }
        } 
        
        catch ( IOException ex )
        {
            throw new RuntimeException( ex );
        }
    }

    public String getProperties( String value )
    {
        return properties.getProperty( value );
    }
}
