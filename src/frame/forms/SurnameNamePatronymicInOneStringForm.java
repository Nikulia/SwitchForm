package frame.forms;

import jdk.internal.util.xml.impl.Input;
import person.Person;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.event.ActionListener;
import java.awt.event.InputEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

/**
 *
 */
public class SurnameNamePatronymicInOneStringForm extends JPanel {
    private JTextField surnameNamePatronymic;
    private JButton switchButton;
    private JPanel rootPanel;
    private JProgressBar textProgress;

    public SurnameNamePatronymicInOneStringForm () {
        surnameNamePatronymic.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                updateTextProgress();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                updateTextProgress();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {

            }
        });
    }
    protected void updateTextProgress() {
        textProgress.setValue(getTokens().length);
    }
    protected String[] getTokens() {
        String value = surnameNamePatronymic.getText().trim();
        if (value.isEmpty())
            return new String[0];
        else
            return value.split("\\s+");
    }
    public void addActionListenerForSwitchAction (ActionListener actionListener) {
        switchButton.addActionListener(actionListener);
    }
    public Person getPerson() {
        String[] surnameNamePatronymicArray =
                getTokens();
        switch (surnameNamePatronymicArray.length) {
            case 0:
                return new Person();
            case 1:
                return new Person(surnameNamePatronymicArray[0], "", "");
            case 2:
                return new Person(surnameNamePatronymicArray[0], surnameNamePatronymicArray[1], "");
            case 3:
                return new Person(surnameNamePatronymicArray[0], surnameNamePatronymicArray[1], surnameNamePatronymicArray[2]);
            default:
                return null;
        }
    }
    public void setPerson(Person person) {
        surnameNamePatronymic.setText(person.toString());
    }

    public JTextField getSurnameNamePatronymic() {
        return surnameNamePatronymic;
    }

    public void defaultCursorPosition() {
        surnameNamePatronymic.requestFocusInWindow();
    }

    private void createUIComponents() {
        rootPanel = this;
    }
}
