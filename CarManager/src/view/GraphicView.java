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
    
    private void refresh()
    {
        try
        {
            CarDAO dao = new CarDAO();
        
            for ( Car c : dao.getInformationCar() )
            {
                String[] logs = c.getLogs().split( "," );
                
                pieDataset.setValue( "RPM", Long.parseLong( logs[1] ) );
                pieDataset.setValue( "Km", Double.parseDouble( logs[2] ) );
                pieDataset.setValue( "Aceleração", Double.parseDouble( logs[3] ) );
                pieDataset.setValue( "Carregamento Bateria", Double.parseDouble( logs[4] ) );
                pieDataset.setValue( "Reservatório", Double.parseDouble( logs[5] ) );
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