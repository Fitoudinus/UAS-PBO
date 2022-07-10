import java.awt.Image
import javax.swing.*

class submit : JFrame() {
    private val panel2: JPanel? = null
    private val inpnama: JLabel? = null
    private val inpnim: JLabel? = null
    private val inpipk: JLabel? = null
    private val inpmatkul: JLabel? = null
    private val inppoto: JLabel? = null
    fun getData(nama: String?, nim: String?, ipk: String?, matkul: String?, paspoto: String?) {
        inpnama!!.text = nama
        inpnim!!.text = nim
        inpipk!!.text = ipk
        inpmatkul!!.text = matkul
        val Paspoto = ImageIcon(ImageIcon(paspoto).image.getScaledInstance(250, 250, Image.SCALE_SMOOTH))
        inppoto!!.icon = Paspoto
    }

    init {
        contentPane = panel2
        title = "Data Mahasiswa"
        setSize(500, 600)
        defaultCloseOperation = EXIT_ON_CLOSE
        isVisible = true
    }

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            val result = submit()
        }
    }
}