import com.mysql.cj.util.StringUtils;
import dao.ClientDao;
import models.Client;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class Form extends JFrame{
    private List<Client> list = new ArrayList<>();
    private JPanel formContainer;
    private JList clientList;
    private JLabel txtId;
    private JLabel idInfo;
    private JLabel txtName;
    private JTextField inputName;
    private JLabel txtLastName;
    private JTextField inputLasName;
    private JLabel txtEmail;
    private JTextField inputEmail;
    private JLabel txtPhone;
    private JTextField inputPhone;
    private JButton saveButton;
    private JButton deleteButton;
    private JButton editButton;
    private JButton newClientButton;


    public Form() {
        setTitle("models.Client system");
        setContentPane(formContainer);
        updateList();

        saveButton.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            Client person = new Client();
            person.setName(inputName.getText());
            person.setLastName(inputLasName.getText());
            person.setEmail(inputEmail.getText());
            person.setPhone(inputPhone.getText());

            if(!StringUtils.isEmptyOrWhitespaceOnly(idInfo.getText())){
                person.setId(idInfo.getText());
            }

            ClientDao dao = new ClientDao();
            dao.saveInfo(person);

            updateList(); //Carga los nombres de la base de datos y los muestra
            JOptionPane.showMessageDialog(formContainer,"The client was saved successfully");
            cleanInputs();

        }
    });
        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int index = clientList.getSelectedIndex();
                Client client = list.get(index);
                ClientDao dao = new ClientDao();
                dao.deleteClient(client.getId());
                updateList();
                JOptionPane.showMessageDialog(formContainer, "The client was successfully removed");
            }
        });


        editButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int index = clientList.getSelectedIndex();
                Client client = list.get(index);
                inputName.setText(client.getName());
                inputLasName.setText(client.getLastName());
                inputEmail.setText(client.getEmail());
                inputPhone.setText(client.getPhone());
                idInfo.setText(client.getId());
            }
        });
        newClientButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cleanInputs();
            }
        });
    }
    private void updateList(){
        ClientDao dao = new ClientDao();
        list =  dao.makeAList();
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
        idInfo.setText("");
    }


}
