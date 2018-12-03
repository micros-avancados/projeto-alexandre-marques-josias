package view;

import controller.CarDAO;
import java.awt.BorderLayout;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import javax.swing.JButton;
import javax.swing.JFrame;
import model.Car;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.general.DefaultPieDataset;

/**
 *
 * @author Alexandre Marques - Josias
 */
public class GraphicView 
    extends 
        JFrame
{
    private Double km1 = 0.0;
    private Double km2 = 0.0;
    private Double km3 = 0.0;
    private Double km4 = 0.0;
    private Double km5 = 0.0;
    
    public GraphicView() throws HeadlessException
    {
        initComponents();
        setLocationRelativeTo( null );
        setDefaultCloseOperation( JFrame.DISPOSE_ON_CLOSE );
        setResizable( false );
    }
    
    private void refresh()
    {
        try
        {
            CarDAO dao = new CarDAO();
        
            for ( Car c : dao.getInformationCar() )
            {
                String[] logs = c.getLogs().split( "," );
                
                Double value = Double.parseDouble( logs[2] ) / 1000000000;
                
                if( value < 25 )
                {
                    km1 = km1 + 1;
                }
                
                else if( value < 50 )
                {
                    km2 = km2 + 1;
                }
                
                else if( value < 75 )
                {
                    km3 = km3 + 1;
                }
                
                else if( value < 100 )
                {
                    km4 = km4 + 1;
                }
                
                else
                {
                    km5 = km5 + 1;
                }
                
                pieDataset.setValue( "Km(0-25)", km1 );
                pieDataset.setValue( "Km(25-50)", km2 );
                pieDataset.setValue( "Km(50-75)", km3 );
                pieDataset.setValue( "Km(75-100)", km4 );
                pieDataset.setValue( "Km > 100", km5 );
            }
        }
        
        catch( Exception e )
        {
            throw new RuntimeException( e );
        }
    }
    
    private void initComponents()
    {
        setLayout( new BorderLayout() );
        setTitle( "Monitoramento do Sistema" );
        setSize( 800, 600 );
        
        refresh();
        chart = ChartFactory.createPieChart( "Monitoramento Gráfico do Sistema", pieDataset, true, true, false );
        
        add( new ChartPanel( chart ), BorderLayout.CENTER );
        add( btnExit, BorderLayout.SOUTH );
        
        btnExit.addActionListener( ( ActionEvent e ) ->
        {
            dispose();
        } );
    }
    
    private DefaultPieDataset pieDataset = new DefaultPieDataset();
    private JFreeChart chart;
    private JButton btnExit = new JButton( "Voltar" );
}