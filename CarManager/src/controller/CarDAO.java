package controller;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import model.Car;
import utils.ConnectionFactory;

/**
 *
 * @author Alexandre Marques - Josias
 */
public class CarDAO 
{
    public List<Car> getInformationCar() throws Exception
    {
        String sql = "select info.log_main log from informations info";
        
        PreparedStatement ps;
        ResultSet rs;
        
        List<Car> listCars = new ArrayList<>();
        
        ps = ConnectionFactory.connection().prepareStatement( sql );
        rs = ps.executeQuery();
        
        while( rs.next() )
        {
            Car car = new Car();
            
            car.setLogs( rs.getString( "log" ) );
            
            listCars.add( car );
        }
        
        return listCars;
    }
}