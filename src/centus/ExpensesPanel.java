package centus;

import com.centus.Image.ExpenseEvent;
import net.sourceforge.jdatepicker.impl.JDatePanelImpl;
import net.sourceforge.jdatepicker.impl.JDatePickerImpl;
import net.sourceforge.jdatepicker.impl.UtilDateModel;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;

public class ExpensesPanel extends  JPanel {
    private JTextField amountTextField;
    private TitledBorder titledBorder;
    private JLabel amountLabel;
    private JLabel categoryLabel;
    private JComboBox categoryCombo;
    private JLabel productLabel;
    private JComboBox productCombo;
    private String [] categories;
    private JButton safeButton;
    private String [] products;
    private JLabel calendarLabel;
    private UtilDateModel model;
    private JDatePanelImpl dataPanel;
    private JDatePickerImpl datePicker;

    private  ExpenseListener expenseListener;






    public ExpensesPanel() {

        setLayout(new GridBagLayout());
        GridBagConstraints gbc=new GridBagConstraints();

        titledBorder=BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), "Wydatki",
                TitledBorder.LEFT, TitledBorder.ABOVE_TOP);
        setBorder(titledBorder);

        amountLabel = new JLabel("Kwota");
        amountTextField = new JTextField(10);
        categoryLabel=new JLabel("Kategoria");
        categories = new String[]{null, "Żywność", "Prąd","Woda" , "Gaz","Edukacja",
                "Paliwo","Telefon","Internet","Ubrania","Zdrowie","Rozrywka" };
        categoryCombo = new JComboBox(categories);

        safeButton= new JButton("Zapisz");




        calendarLabel = new JLabel("Wybierz datę");
        model = new UtilDateModel();
        dataPanel= new JDatePanelImpl(model);
        datePicker = new JDatePickerImpl(dataPanel, null);





        //first row
        gbc.gridx=0;
        gbc.gridy=0;
        gbc.weightx=1;
        gbc.weighty=0.1;
        gbc.insets=new Insets(0,0,0,10);
        gbc.anchor=GridBagConstraints.LINE_END;

        add(amountLabel,gbc);
        gbc.anchor=GridBagConstraints.LINE_START;

        gbc.gridx=1;

        add(amountTextField,gbc);
        //second row
        gbc.weighty=0.1;
        gbc.gridx=0;
        gbc.gridy=1;
        gbc.anchor=GridBagConstraints.LINE_END;

        add(categoryLabel,gbc);

        gbc.gridx=1;
        gbc.anchor=GridBagConstraints.LINE_START;

        add(categoryCombo,gbc);
        //third row

        //fourth row
        gbc.gridx=0;
        gbc.gridy=3;

        add(calendarLabel,gbc);


        gbc.gridx=1;
        add(datePicker,gbc);
        //fifth row
        gbc.weighty=1;
        gbc.gridy=4;
        gbc.gridx=1;
        gbc.anchor=GridBagConstraints.FIRST_LINE_START;
        
        add(safeButton,gbc);

        setVisible(true);
        safeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String  amount = amountTextField.getText();
                String category= (String)categoryCombo.getSelectedItem();
                String product= (String) productCombo.getSelectedItem();
                Date selectedDate = (Date) datePicker.getModel().getValue();

                ExpenseEvent expenseEvent = new ExpenseEvent(this,amount,category,product,selectedDate);

                if (expenseListener != null){
                    expenseListener.zdarzenieSzastaniaPienidzmi(expenseEvent);
                }



            }
        });
    }

    public void setExpenseListener(ExpenseListener expenseListener){
        this.expenseListener = expenseListener;
    }
}
