import javax.swing.*;
import javax.swing.filechooser.FileSystemView;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.Statement;

public class datamhs extends JFrame {
    private JTextField dmnama;
    private JTextField dmnim;
    private JTextField dmmatkul;
    private JButton tmblsubmit;
    private JPanel panel;
    private JTextField dmipk;
    private JButton tmblfoto;
    private JLabel getfoto;

    public datamhs() {
        setContentPane(panel);
        setTitle("Data Mahasiswa");
        setSize(500, 600);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);

        tmblfoto.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String com = e.getActionCommand();

                if (com.equals("save")) {
                    JFileChooser j = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());
                    int r = j.showSaveDialog(null);
                    if (r == JFileChooser.APPROVE_OPTION) {
                        getfoto.setText(j.getSelectedFile().getAbsolutePath());
                    } else
                        getfoto.setText("the user cancelled the operation");
                }
                else {
                    JFileChooser j = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());
                    int r = j.showOpenDialog(null);
                    if (r == JFileChooser.APPROVE_OPTION) {
                        getfoto.setText(j.getSelectedFile().getAbsolutePath());
                    } else
                        getfoto.setText("the user cancelled the operation");
                }
            }
        });

        tmblsubmit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String nama = dmnama.getText();
                String nim = dmnim.getText();
                String ipk = dmipk.getText();
                String matkul = dmmatkul.getText();
                String paspoto = getfoto.getText();

                submit subm = new submit();
                subm.getData(nama, nim, ipk, matkul, paspoto);
                subm.setVisible(true);
                dispose();

                mhs = addDatabase(nama, nim, ipk, matkul, paspoto);
            }

            public datamahasiswa mhs;
            private datamahasiswa addDatabase(String nama, String nim, String ipk, String matkul, String paspoto) {
                datamahasiswa mhs = null;
                final String url = "jdbc:mysql://localhost/dbmahasiswa?serverTimezone=UTC";
                final String username ="root";
                final String password = "";

                try {
                    Connection conn = DriverManager.getConnection(url, username, password);

                    Statement statement = conn.createStatement();
                    String sql = "INSERT INTO mahasiswa (nama, nim, ipk, matkul, foto)" +
                            "VALUES (?, ?, ? ,? ,?)";
                    PreparedStatement preparedStatement = conn.prepareStatement(sql);
                    preparedStatement.setString(1, nama);
                    preparedStatement.setString(2, nim);
                    preparedStatement.setString(3, ipk);
                    preparedStatement.setString(4, matkul);
                    preparedStatement.setString(5, paspoto);

                    int addRows = preparedStatement.executeUpdate();
                    if (addRows > 0) {
                        mhs = new datamahasiswa();
                        mhs.nama = nama;
                        mhs.nim = nim;
                        mhs.ipk = ipk;
                        mhs.matkul = matkul;
                        mhs.foto = paspoto;
                    }

                    statement.close();
                    conn.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                return mhs;
            }
        });
    }

    public static void main(String[] args) {
        datamhs result = new datamhs();
    }
}
