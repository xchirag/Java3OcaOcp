package com.fdmgroup.currency.view;

import javax.swing.JTextField;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;
import javax.swing.text.PlainDocument;

public class IntegerField extends JTextField {
	private static final long serialVersionUID = 1L;

	public IntegerField() {
		super();
	}

	public IntegerField(int columnSize) {
		super(columnSize);
	}

	@Override
	protected Document createDefaultModel() {
		return new UpperCaseDocument();
	}

	static class UpperCaseDocument extends PlainDocument {
		private static final long serialVersionUID = 1L;

		@Override
		public void insertString(int offset, String inputString, AttributeSet attributes) throws BadLocationException {
			if (inputString == null) {
				return;
			}
			char[] characters = inputString.toCharArray();
			boolean isNumber = true;
			for (int i = 0; i < characters.length; i++) {
				try {
					Integer.parseInt(String.valueOf(characters[i]));
				} catch (NumberFormatException error) {
					isNumber = false;
					break;
				}
			}
			if (isNumber)
				super.insertString(offset, new String(characters), attributes);
		}
	}
}