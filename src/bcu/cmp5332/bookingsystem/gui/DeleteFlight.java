package bcu.cmp5332.bookingsystem.gui;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.UIManager;

import bcu.cmp5332.bookingsystem.commands.Command;
import bcu.cmp5332.bookingsystem.commands.HideFlight;
import bcu.cmp5332.bookingsystem.main.FlightBookingSystemException;

public class DeleteFlight extends JFrame implements ActionListener {
	
	private MainWindow mw;
	
    private JTextField flightIDText = new JTextField();
    
    private JButton deleteBtn = new JButton("Delete");
    private JButton cancelBtn = new JButton("Cancel");
	
	
    public DeleteFlight(MainWindow mw) {
        this.mw = mw;
        initialize();
    }
    
	private void initialize() {
		try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception ex) {

        }

        setTitle("Delete a flight");

        setSize(350, 260);
        JPanel topPanel = new JPanel();
        topPanel.setLayout(new GridLayout(1, 2));
        
        topPanel.add(new JLabel("Flight ID: "));
        topPanel.add(flightIDText);


        JPanel bottomPanel = new JPanel();
        bottomPanel.setLayout(new GridLayout(1, 3));
        bottomPanel.add(new JLabel("     "));
        bottomPanel.add(deleteBtn);
        bottomPanel.add(cancelBtn);

        deleteBtn.addActionListener(this);
        cancelBtn.addActionListener(this);

        this.getContentPane().add(topPanel, BorderLayout.CENTER);
        this.getContentPane().add(bottomPanel, BorderLayout.SOUTH);
        setLocationRelativeTo(mw);

        setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		try {
            int flightId = Integer.parseInt(flightIDText.getText());
            
            // create and execute the hideCustomer Command
            Command hideFlight = new HideFlight(flightId);
            hideFlight.execute(mw.getFlightBookingSystem());
            // refresh the view with the list of customers
            mw.displayFlights();
            // hide (close) the DeleteFlightWindow
            this.setVisible(false);
            
        } catch (FlightBookingSystemException ex) {
            JOptionPane.showMessageDialog(this, ex, "Error", JOptionPane.ERROR_MESSAGE);
        }
	}

}
