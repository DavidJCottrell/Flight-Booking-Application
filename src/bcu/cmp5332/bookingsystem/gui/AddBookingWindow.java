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

import bcu.cmp5332.bookingsystem.commands.AddBooking;
import bcu.cmp5332.bookingsystem.commands.Command;
import bcu.cmp5332.bookingsystem.main.FlightBookingSystemException;

public class AddBookingWindow extends JFrame implements ActionListener {

	private MainWindow mw;
    private JTextField customerIDText = new JTextField();
    private JTextField flightIDText = new JTextField();
    
    private JButton addBtn = new JButton("Add");
    private JButton cancelBtn = new JButton("Cancel");
	
	public AddBookingWindow(MainWindow mw) {
        this.mw = mw;
        initialize();
	}
	
	private void initialize() {
		try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception ex) {
        	
        }
		
		setTitle("Add a booking");
        setSize(350, 260);
        JPanel topPanel = new JPanel();
        topPanel.setLayout(new GridLayout(1, 2));
		
        topPanel.add(new JLabel("Customer ID: "));
        topPanel.add(customerIDText);
        
        topPanel.add(new JLabel("Flight ID: "));
        topPanel.add(flightIDText);
        
        JPanel bottomPanel = new JPanel();
        bottomPanel.setLayout(new GridLayout(1, 3));
        bottomPanel.add(new JLabel("     "));
        bottomPanel.add(addBtn);
        bottomPanel.add(cancelBtn);

        addBtn.addActionListener(this);
        cancelBtn.addActionListener(this);

        this.getContentPane().add(topPanel, BorderLayout.CENTER);
        this.getContentPane().add(bottomPanel, BorderLayout.SOUTH);
        setLocationRelativeTo(mw);

        setVisible(true);
		
		
	}
	
	@Override
	public void actionPerformed(ActionEvent ae) {
		if (ae.getSource() == addBtn) {
			addBooking();
        } else if (ae.getSource() == cancelBtn) {
            this.setVisible(false);
        }
	}

	
	private void addBooking() {
		try {
            int customerId = Integer.parseInt(customerIDText.getText());
            int flightId = Integer.parseInt(flightIDText.getText());
            
            // create and execute the hideCustomer Command
            Command addBooking = new AddBooking(customerId, flightId);
            addBooking.execute(mw.getFlightBookingSystem());
            // hide (close) the DeleteCustomerWindow
            this.setVisible(false);
            
        } catch (FlightBookingSystemException ex) {
            JOptionPane.showMessageDialog(this, ex, "Error", JOptionPane.ERROR_MESSAGE);
        }
	}
	
}
