import javax.swing.*;

import java.awt.*;
import java.awt.event.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

class WineOrdering extends JFrame implements ActionListener {
    Container c;
    JLabel title;
    JPanel titlePanel, custDetails, orderDetails, orderSummary, southPanel, custPanel, orderPanel;
    JLabel custName, custPhone, orderDate, orderTime, itemName, itemQty,
            itemPrice, itemTotal, whiteWine, redWine, yellowWine, otherWine, subTotal, tax, netPrice;
    JTextField custNameField, custPhoneField, orderDateField, orderTimeField, whiteWineQty, redWineQty, yellowWineQty,
            otherWineQty, whiteWinePrice, redWinePrice, yellowWinePrice, otherWinePrice, whiteWineTotal, redWineTotal,
            yellowWineTotal, otherWineTotal, subTotaField, taxField, netPriceField;
    Dimension textFieldDimension;
    Font gridFont;
    JButton calculate, reset, exit;

    WineOrdering() {
        gridFont = new Font("Arial", Font.BOLD, 20);
        textFieldDimension = new Dimension(100, 30);
        this.setTitle("Wine Ordering System");
        this.setSize(1000, 750);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);

        c = this.getContentPane();
        c.setLayout(null);
        c.setBackground(Color.LIGHT_GRAY);

        addTitlePanel();
        addCustomerDetails();
        addOrderDetails();
        addOrderSummary();

        this.setVisible(true);
    }

    void addTitlePanel() {
        titlePanel = new JPanel();
        titlePanel.setBounds(10, 10, 1000, 50);
        titlePanel.setBackground(Color.LIGHT_GRAY);
        title = new JLabel("Wine Ordering System");
        title.setFont(new Font("Arial", Font.BOLD, 30));
        titlePanel.add(title, BorderLayout.CENTER);
        titlePanel.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
        c.add(titlePanel, BorderLayout.NORTH);
    }

    void addCustomerDetails() {
        custDetails = new JPanel();
        custDetails.setBounds(10, 70, 1000, 200);
        custDetails.setBackground(Color.LIGHT_GRAY);
        custDetails.setBorder(
                BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.BLACK, 2), "Customer Details"));
        custDetails.setLayout(null);

        custName = new JLabel("Customer Name");
        custName.setBounds(50, 30, 200, 40);
        custName.setFont(gridFont);
        custDetails.add(custName);
        custNameField = new JTextField();
        custNameField.setBounds(250, 30, 200, 40);
        custDetails.add(custNameField);
        custPhone = new JLabel("Customer Phone");
        custPhone.setBounds(50, 80, 200, 40);
        custPhone.setFont(gridFont);
        custDetails.add(custPhone);
        custPhoneField = new JTextField();
        custPhoneField.setBounds(250, 80, 200, 40);
        custDetails.add(custPhoneField);

        orderDate = new JLabel("Order Date");
        orderDate.setBounds(600, 30, 200, 40);
        orderDate.setFont(gridFont);
        custDetails.add(orderDate);
        orderDateField = new JTextField();
        orderDateField.setBounds(800, 30, 150, 40);
        orderTime = new JLabel("Order Time");
        orderTime.setBounds(600, 80, 200, 40);
        orderTime.setFont(gridFont);
        custDetails.add(orderTime);
        orderTimeField = new JTextField();
        orderTimeField.setBounds(800, 80, 150, 40);
        custDetails.add(orderDateField);
        custDetails.add(orderTimeField);

        orderDateField.setEditable(false);
        orderTimeField.setEditable(false);

        c.add(custDetails);
    }

    void addOrderDetails() {
        orderDetails = new JPanel();
        orderDetails.setBounds(10, 280, 600, 350);
        orderDetails.setBackground(Color.LIGHT_GRAY);
        orderDetails.setBorder(
                BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.BLACK, 2), "Order Details"));
        orderDetails.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        gbc.ipadx = 10;
        gbc.ipady = 10;
        gbc.insets = new Insets(25, 10, 0, 0);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.fill = GridBagConstraints.VERTICAL;

        itemName = new JLabel("Item Name");
        itemName.setFont(gridFont);
        gbc.gridx = 0;
        gbc.gridy = 0;
        orderDetails.add(itemName, gbc);
        itemQty = new JLabel("Quantity");
        itemQty.setFont(gridFont);
        gbc.gridx = 1;
        gbc.gridy = 0;
        orderDetails.add(itemQty, gbc);
        itemPrice = new JLabel("Price");
        itemPrice.setFont(gridFont);
        gbc.gridx = 2;
        gbc.gridy = 0;
        orderDetails.add(itemPrice, gbc);
        itemTotal = new JLabel("Total");
        itemTotal.setFont(gridFont);
        gbc.gridx = 3;
        gbc.gridy = 0;
        orderDetails.add(itemTotal, gbc);

        whiteWine = new JLabel("White Wine");
        whiteWine.setFont(gridFont);
        gbc.gridx = 0;
        gbc.gridy = 1;
        orderDetails.add(whiteWine, gbc);
        whiteWineQty = new JTextField(10);
        gbc.gridx = 1;
        gbc.gridy = 1;
        orderDetails.add(whiteWineQty, gbc);
        whiteWinePrice = new JTextField(10);
        gbc.gridx = 2;
        gbc.gridy = 1;
        orderDetails.add(whiteWinePrice, gbc);
        whiteWineTotal = new JTextField(10);
        gbc.gridx = 3;
        gbc.gridy = 1;
        orderDetails.add(whiteWineTotal, gbc);

        redWine = new JLabel("Red Wine");
        redWine.setFont(gridFont);
        gbc.gridx = 0;
        gbc.gridy = 2;
        orderDetails.add(redWine, gbc);
        redWineQty = new JTextField(10);
        gbc.gridx = 1;
        gbc.gridy = 2;
        orderDetails.add(redWineQty, gbc);
        redWinePrice = new JTextField(10);
        gbc.gridx = 2;
        gbc.gridy = 2;
        orderDetails.add(redWinePrice, gbc);
        redWineTotal = new JTextField(10);
        gbc.gridx = 3;
        gbc.gridy = 2;
        orderDetails.add(redWineTotal, gbc);

        yellowWine = new JLabel("Yellow Wine");
        yellowWine.setFont(gridFont);
        gbc.gridx = 0;
        gbc.gridy = 3;
        orderDetails.add(yellowWine, gbc);
        yellowWineQty = new JTextField(10);
        gbc.gridx = 1;
        gbc.gridy = 3;
        orderDetails.add(yellowWineQty, gbc);
        yellowWinePrice = new JTextField(10);
        gbc.gridx = 2;
        gbc.gridy = 3;
        orderDetails.add(yellowWinePrice, gbc);
        yellowWineTotal = new JTextField(10);
        gbc.gridx = 3;
        gbc.gridy = 3;
        orderDetails.add(yellowWineTotal, gbc);

        otherWine = new JLabel("Other Wine");
        otherWine.setFont(gridFont);
        gbc.gridx = 0;
        gbc.gridy = 4;
        orderDetails.add(otherWine, gbc);
        otherWineQty = new JTextField(10);
        gbc.gridx = 1;
        gbc.gridy = 4;
        orderDetails.add(otherWineQty, gbc);
        otherWinePrice = new JTextField(10);
        gbc.gridx = 2;
        gbc.gridy = 4;
        orderDetails.add(otherWinePrice, gbc);
        otherWineTotal = new JTextField(10);
        gbc.gridx = 3;
        gbc.gridy = 4;
        orderDetails.add(otherWineTotal, gbc);

        whiteWinePrice.setEditable(false);
        whiteWineTotal.setEditable(false);

        redWinePrice.setEditable(false);
        redWineTotal.setEditable(false);

        yellowWinePrice.setEditable(false);
        yellowWineTotal.setEditable(false);

        otherWinePrice.setEditable(false);
        otherWineTotal.setEditable(false);

        c.add(orderDetails);
    }

    void addOrderSummary() {
        orderSummary = new JPanel();
        orderSummary.setBounds(650, 280, 350, 350);
        orderSummary.setBackground(Color.LIGHT_GRAY);
        orderSummary.setBorder(
                BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.BLACK, 2), "Order Summary"));
        orderSummary.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        gbc.ipadx = 10;
        gbc.ipady = 10;

        gbc.insets = new Insets(25, 10, 0, 0);

        gbc.fill = GridBagConstraints.HORIZONTAL;

        subTotal = new JLabel("Sub Total");
        subTotal.setFont(gridFont);
        gbc.gridx = 0;
        gbc.gridy = 0;
        orderSummary.add(subTotal, gbc);
        gbc.gridwidth = 2;
        subTotaField = new JTextField(10);
        gbc.gridx = 1;
        gbc.gridy = 0;
        orderSummary.add(subTotaField, gbc);

        gbc.gridwidth = 1;
        tax = new JLabel("Tax");
        tax.setFont(gridFont);
        gbc.gridx = 0;
        gbc.gridy = 1;
        orderSummary.add(tax, gbc);
        taxField = new JTextField(10);
        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.gridwidth = 2;
        orderSummary.add(taxField, gbc);

        gbc.gridwidth = 1;
        netPrice = new JLabel("Net Price");
        netPrice.setFont(gridFont);
        gbc.gridx = 0;
        gbc.gridy = 2;
        orderSummary.add(netPrice, gbc);
        netPriceField = new JTextField(10);
        gbc.gridx = 1;
        gbc.gridy = 2;
        gbc.gridwidth = 2;
        orderSummary.add(netPriceField, gbc);

        gbc.gridwidth = 1;
        calculate = new JButton("Calculate");
        gbc.gridx = 0;
        gbc.gridy = 3;
        orderSummary.add(calculate, gbc);
        reset = new JButton("Reset");
        gbc.gridx = 1;
        gbc.gridy = 3;
        orderSummary.add(reset, gbc);
        exit = new JButton("Exit");
        gbc.gridx = 2;
        gbc.gridy = 3;
        orderSummary.add(exit, gbc);

        subTotaField.setEditable(false);
        taxField.setEditable(false);
        netPriceField.setEditable(false);

        calculate.addActionListener(this);
        reset.addActionListener(this);
        exit.addActionListener(this);

        c.add(orderSummary);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == calculate) {
            double whiteWineQty = Double.parseDouble(this.whiteWineQty.getText());
            this.whiteWinePrice.setText("35.8");
            double whiteWinePrice = Double.parseDouble(this.whiteWinePrice.getText());
            double whiteWineTotal = whiteWineQty * whiteWinePrice;
            this.whiteWineTotal.setText(String.valueOf(whiteWineTotal));

            double redWineQty = Double.parseDouble(this.redWineQty.getText());
            this.redWinePrice.setText("32.6");
            double redWinePrice = Double.parseDouble(this.redWinePrice.getText());
            double redWineTotal = redWineQty * redWinePrice;
            this.redWineTotal.setText(String.valueOf(redWineTotal));

            double yellowWineQty = Double.parseDouble(this.yellowWineQty.getText());
            this.yellowWinePrice.setText("28.3");
            double yellowWinePrice = Double.parseDouble(this.yellowWinePrice.getText());
            double yellowWineTotal = yellowWineQty * yellowWinePrice;
            this.yellowWineTotal.setText(String.valueOf(yellowWineTotal));

            double otherWineQty = Double.parseDouble(this.otherWineQty.getText());
            this.otherWinePrice.setText("21.7");
            double otherWinePrice = Double.parseDouble(this.otherWinePrice.getText());
            double otherWineTotal = otherWineQty * otherWinePrice;
            this.otherWineTotal.setText(String.valueOf(otherWineTotal));

            double subTotal = whiteWineTotal + redWineTotal + yellowWineTotal + otherWineTotal;
            this.subTotaField.setText(String.valueOf(subTotal));

            double tax = subTotal * 0.05;
            this.taxField.setText(String.valueOf(tax));

            double netPrice = subTotal + tax;
            this.netPriceField.setText(String.valueOf(netPrice));

            orderDateField.setText(DateTimeFormatter.ofPattern("yyyy-MM-dd").format(LocalDateTime.now()));
            orderTimeField.setText(DateTimeFormatter.ofPattern("HH:mm:ss").format(LocalDateTime.now()));
        } else if (e.getSource() == reset) {
            this.custNameField.setText("");
            this.custPhoneField.setText("");
            this.orderDateField.setText("");
            this.orderTimeField.setText("");
            this.whiteWineQty.setText("");
            this.redWineQty.setText("");
            this.yellowWineQty.setText("");
            this.otherWineQty.setText("");
            this.whiteWineTotal.setText("");
            this.redWineTotal.setText("");
            this.yellowWineTotal.setText("");
            this.otherWineTotal.setText("");
            this.subTotaField.setText("");
            this.taxField.setText("");
            this.netPriceField.setText("");
        } else if (e.getSource() == exit) {
            System.exit(0);
        }
    }

}

public class wineOrderingSystem {
    public static void main(String[] args) {
        new WineOrdering();
    }
}
