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
    public GraphicView() throws HeadlessException
    {
        initComponents();
        setLocationRelativeTo( null );
        setDefaultCloseOperation( JFrame.DISPOSE_ON_CLOSE );
        setResizable( false );
    }
    
    private void runtimeProcess()
    {
        while( true )
        {
            try
            {
                Thread.sleep( 1000 );
                refresh();
            }
            
            catch( Exception e )
            {
                throw new RuntimeException( e );
            }
        }
    }
    
    private void refresh()
    {
        try
        {
            CarDAO dao = new CarDAO();
        
            for ( Car c : dao.getInformationCar() )
            {
                String[] logs = c.getLogs().split( "," );
                
                pieDataset.setValue( "RPM", new Integer( logs[0] ) );
                pieDataset.setValue( "Km", new Integer( logs[1] ) );
                pieDataset.setValue( "Aceleração", new Integer( logs[2] ) );
                pieDataset.setValue( "Carga", new Integer( logs[3] ) );
                pieDataset.setValue( "Tanque", new Integer( logs[4] ) );
                pieDataset.setValue( "Tanque", new Integer( logs[5] ) );
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
        setTitle( "Gerenciamento do Sistema Veicular" );
        setSize( 800, 600 );
        
        runtimeProcess();
        chart = ChartFactory.createPieChart( "Gerenciamento Gráfico do Sistema", pieDataset, true, true, false );
        
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