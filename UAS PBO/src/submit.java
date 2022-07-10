import javax.swing.*;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import java.awt.*;

public class submit extends JFrame {
    private JPanel panel2;
    private JLabel msknama;
    private JLabel msknim;
    private JLabel mskipk;
    private JLabel mskmatkul;
    private JLabel mskpoto;

    public void getData(String nama, String nim, String ipk, String matkul, String paspoto) {
        msknama.setText(nama);
        msknim.setText(nim);
        mskipk.setText(ipk);
        mskmatkul.setText(matkul);
        ImageIcon Paspoto = new ImageIcon(new ImageIcon(paspoto).getImage().getScaledInstance(250, 250, Image.SCALE_SMOOTH));
        mskpoto.setIcon(Paspoto);
    }

    public submit() {
        setContentPane(panel2);
        setTitle("Data Mahasiswa");
        setSize(500, 600);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);
    }

    public static void main(String[] args) {
        submit result = new submit();
    }
}