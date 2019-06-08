package com.fdmgroup.currency.view;

import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.text.DecimalFormat;
import java.util.List;
import javax.swing.ButtonGroup;
import javax.swing.JApplet;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;
import com.fdmgroup.currency.controller.CurrencyConverterControllerImpl;
import com.fdmgroup.currency.model.CurrencyList;

public class UserInterface extends JApplet implements ActionListener {
	private static final long serialVersionUID = 1L;
	CurrencyConverterControllerImpl imp = new CurrencyConverterControllerImpl();
	CurrencyList cl = new CurrencyList();
	List<String> currencies = imp.returnCurrenciesList(cl);
	CurrencyList cl2 = new CurrencyList();
	String[] currencyarray = new String[currencies.size()];
	DecimalFormat decimalFormatter = new DecimalFormat("#.##");
	// Form Items
	private JComboBox<Object> cbCurrency = new JComboBox<>();
	private JButton button = new JButton("Convert");
	private IntegerField textbox = new IntegerField(15);
	private int count = 0;
	// Radio Buttons
	private JRadioButton toEurope = new JRadioButton("To Euro");
	private JRadioButton fromEurope = new JRadioButton("From Euro");
	private ButtonGroup radioGroup = new ButtonGroup();

	// Label
	private JLabel convertResultLabel = new JLabel("Your conversion amount is: ");

	public void init() {
		currencyarray = currencies.toArray(currencyarray);
		for (int i = 0; i < currencyarray.length; i++) {
			cbCurrency.addItem(currencyarray[count++]);
		}
		button.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String userInputCurrency = cbCurrency.getSelectedItem().toString();
				if (textbox.getText().isEmpty()) {
					JOptionPane.showMessageDialog(new JFrame(), "Please enter a value");
				} else {
					double userInputAmount = Double.parseDouble(textbox.getText());
					String resultStringToEuro = decimalFormatter
							.format(imp.convertToEuro(cl, userInputCurrency, userInputAmount));
					String resultStringFromEuro = decimalFormatter
							.format(imp.convertFromEuro(cl, userInputCurrency, userInputAmount));
					if (toEurope.isSelected()) {
						convertResultLabel.setText(String.valueOf("Your conversion amount is: " + resultStringToEuro));
						textbox.setText("");
					} else if (fromEurope.isSelected()) {
						convertResultLabel
								.setText(String.valueOf("Your conversion amount is: " + resultStringFromEuro));
						textbox.setText("");
					}

				}
			}
		});
		toEurope.setMnemonic(KeyEvent.VK_B);
		toEurope.setActionCommand("To Euro");
		toEurope.setSelected(true);
		fromEurope.setMnemonic(KeyEvent.VK_B);
		fromEurope.setActionCommand("From Euro");
		radioGroup.add(toEurope);
		radioGroup.add(fromEurope);
		// Container Object
		Container cp = getContentPane();
		FlowLayout layout = new FlowLayout();
		cp.setLayout(layout);
		layout.setAlignment(FlowLayout.CENTER);
		layout.layoutContainer(cp);
		cp.add(convertResultLabel);
		cp.add(textbox);
		cp.add(cbCurrency);
		cp.add(button);
		cp.add(fromEurope);
		cp.add(toEurope);
	}

	public static void run(JApplet applet, int width, int height) {
		JFrame frame = new JFrame("Currency Convert");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		frame.getContentPane().add(applet);
		frame.setSize(width, height);
		applet.init();
		applet.start();
		frame.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
	}
}