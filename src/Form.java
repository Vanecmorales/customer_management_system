import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class Form extends JFrame{
    private JPanel formContainer;
    private JLabel txtName;
    private JTextField inputName;
    private JButton saveButton;
    private JList clientList;
    private JButton deleteButton;
    private JLabel txtLastName;
    private JLabel txtEmail;
    private JLabel txtPhone;
    private JTextField inputLasName;
    private JTextField inputEmail;
    private JTextField inputPhone;

    List<Client> list = new ArrayList<Client>();

    public Form() {
        setTitle("Client system");
        setContentPane(formContainer);

        saveButton.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            Client person = new Client();
            person.setName(inputName.getText());
            person.setLastName(inputLasName.getText());
            person.setEmail(inputEmail.getText());
            person.setPhone(inputPhone.getText());

            list.add(person); //Toma los nombres ingresados en el inputName, pero debe declararse la variable list antes (lin: 21)
            updateList(); //Carga los nombres y los muestra
            JOptionPane.showMessageDialog(formContainer,"The client was saved successfully");
            cleanInputs();
        }
    });
        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int index = clientList.getSelectedIndex();
                list.remove(index);
                updateList();
                JOptionPane.showMessageDialog(formContainer, "The client was successfully removed");
            }
        });
    }
    private void updateList(){
        DefaultListModel data = new DefaultListModel<>(); //El formato del modelo de datos es distinto al list<sting> por ello se crea una variable para almacenar los datos del for y crear el array
        for(int i = 0; i < list.size(); i++){
            Client updatePerson = list.get(i);
            data.addElement(updatePerson.getFullName());
        }
        clientList.setModel(data);
    }

    private void cleanInputs(){
        inputName.setText("");
        inputLasName.setText("");
        inputEmail.setText("");
        inputPhone.setText("");
    }
}
