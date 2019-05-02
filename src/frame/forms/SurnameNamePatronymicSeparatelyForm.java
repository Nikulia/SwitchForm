package frame.forms;

import person.Person;

import javax.swing.*;
import java.awt.event.ActionListener;
import java.awt.event.InputEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class SurnameNamePatronymicSeparatelyForm extends JPanel {
    private JPanel rootPanel;
    private JTextField surname;
    private JTextField name;
    private JTextField patronymic;
    private JButton switchButton;

    public void addActionListenerForSwitchAction (ActionListener actionListener) {
        switchButton.addActionListener(actionListener);
    }
    public Person getPerson() {
        return new Person(surname.getText().trim(), name.getText().trim(), patronymic.getText().trim());
    }
    public void setPerson(Person person) {
        surname.setText(person.getSurname());
        name.setText(person.getName());
        patronymic.setText(person.getPatronymic());
    }
    private void createUIComponents() {
        rootPanel = this;
    }
    public void patronymicCursorPosition() {
        patronymic.requestFocusInWindow();
    }
    public void beginCursorPosition() {
        surname.requestFocusInWindow();
    }
    public void cursorOnName() {
        name.requestFocusInWindow();
    }
}
