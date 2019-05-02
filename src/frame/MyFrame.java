package frame;

import frame.forms.SurnameNamePatronymicInOneStringForm;
import frame.forms.SurnameNamePatronymicSeparatelyForm;
import person.Person;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;



public class MyFrame extends JFrame{
    private SurnameNamePatronymicSeparatelyForm surnameNamePatronymicSeparatelyForm;
    private SurnameNamePatronymicInOneStringForm surnameNamePatronymicInOneStringForm;
    private final int DEFAULT_WIDTH = 400;
    private final int DEFAULT_HIGH = 300;
    private final int MINIMAL_WIDTH = 350;
    private final int MINIMAL_HIGH = 200;

    public MyFrame() {
        surnameNamePatronymicInOneStringForm =
                new SurnameNamePatronymicInOneStringForm();
        surnameNamePatronymicSeparatelyForm =
                new SurnameNamePatronymicSeparatelyForm();
        setContentPane(surnameNamePatronymicSeparatelyForm);
        setSize(DEFAULT_WIDTH, DEFAULT_HIGH);
        setMinimumSize(new Dimension(MINIMAL_WIDTH, MINIMAL_HIGH));

        surnameNamePatronymicSeparatelyForm.addActionListenerForSwitchAction(
                e -> switchToOneStringForm()
        );
        surnameNamePatronymicInOneStringForm.addActionListenerForSwitchAction(
                e -> switchToSeparatelyForm()
        );
        getRootPane().getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW)
                .put(KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, InputEvent.CTRL_DOWN_MASK) , "switch");
        getRootPane().getActionMap().put("switch", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                switchForm();
            }
        });
    }

    protected void switchForm() {
        if (getContentPane() == surnameNamePatronymicSeparatelyForm)
            switchToOneStringForm();
        else if (getContentPane() == surnameNamePatronymicInOneStringForm)
            switchToSeparatelyForm();
        else
            throw new IllegalStateException();
    }

    protected void switchToSeparatelyForm() {
        Person  person = surnameNamePatronymicInOneStringForm.getPerson();
        if (canSwitch(checkPerson(person))) {
            surnameNamePatronymicSeparatelyForm.setPerson(person);
            setForm(surnameNamePatronymicSeparatelyForm);
            returnCursorForSeparatelyForm(checkPerson(person));
        }
        else
            returnCursorForOneStringForm();
    }
    protected void switchToOneStringForm() {
        Person person = surnameNamePatronymicSeparatelyForm.getPerson();
        if (canSwitch(checkPerson(person))) {
            surnameNamePatronymicInOneStringForm.setPerson(person);
            setForm(surnameNamePatronymicInOneStringForm);
            returnCursorForOneStringForm();
        }
        else
            returnCursorForSeparatelyForm(checkPerson(person));
    }
    protected void setForm(JPanel jPanel) {
        setContentPane(jPanel);
        getContentPane().revalidate();
        getContentPane().repaint();
    }
    public static boolean isEmpty(String value) {
        return value == null || value.trim().isEmpty();
    }

    public static final int PERSON_OK = 0, SURNAME_MISSING = 1, NAME_MISSING = 2, PATRONYMIC_MISSING = 3, INCORRECT_PERSON = 4;
    public static int checkPerson (Person person) {
        if (person == null)
            return INCORRECT_PERSON;
        else if (isEmpty(person.getSurname()))
            return SURNAME_MISSING;
        else if (isEmpty(person.getName()))
            return NAME_MISSING;
        else if (isEmpty(person.getPatronymic()))
            return PATRONYMIC_MISSING;
        else
            return PERSON_OK;
    }
    protected boolean canSwitch (int personStatus) {
        if (personStatus == PERSON_OK)
            return true;
        else if (personStatus == INCORRECT_PERSON) {
            JOptionPane.showOptionDialog(this, "Не похоже на данные",
                    "Не все данные введены", JOptionPane.DEFAULT_OPTION, JOptionPane.ERROR_MESSAGE,
                    null, null, null);
            return false;
        }
        else if (personStatus == SURNAME_MISSING || personStatus == NAME_MISSING) {
            JOptionPane.showOptionDialog(this, "Необходимо заполнить имя и фамилию!",
                    "Не все данные введены", JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE,
                    null, null, null);
            return false;
        }
        else if (personStatus == PATRONYMIC_MISSING)
            return JOptionPane.YES_OPTION == JOptionPane.showOptionDialog(this, "Вы уверены, что не нужно заполнять отчество?",
                    "Не все данные введены", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE,
                    null, null, JOptionPane.YES_OPTION);
        else
            throw new IllegalArgumentException();

    }
    protected void returnCursorForSeparatelyForm (int personStatus) {
        if (personStatus == SURNAME_MISSING)
            surnameNamePatronymicSeparatelyForm.beginCursorPosition();
        else if (personStatus == NAME_MISSING)
            surnameNamePatronymicSeparatelyForm.cursorOnName();
        else
            surnameNamePatronymicSeparatelyForm.patronymicCursorPosition();
    }
    protected void returnCursorForOneStringForm () {
        surnameNamePatronymicInOneStringForm.defaultCursorPosition();
    }
}
