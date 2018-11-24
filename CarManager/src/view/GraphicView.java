package view;

import java.awt.BorderLayout;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import javax.swing.JButton;
import javax.swing.JFrame;
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
    
    private void initComponents()
    {
        setLayout( new BorderLayout() );
        setTitle( "Gerenciamento do Sistema Veicular" );
        setSize( 800, 600 );
        
        pieDataset.setValue( "RPM", new Integer( 2000 ) );
        pieDataset.setValue( "Km", new Integer( 25 ) );
        pieDataset.setValue( "Aceleração", new Integer( 1200 ) );
        pieDataset.setValue( "Carga", new Integer( 400 ) );
        pieDataset.setValue( "Tanque", new Integer( 300 ) );
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