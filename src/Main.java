import javax.swing.*;

public class Main {
    public static void main (String[] args){
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                JFrame form = new Form();
                form.setBounds(500,250,500,200);
                form.setVisible(true);
            }
        });
    }
}
