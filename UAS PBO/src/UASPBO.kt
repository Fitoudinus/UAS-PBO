import java.awt.event.ActionEvent
import java.awt.event.ActionListener
import java.sql.DriverManager
import javax.swing.*
import javax.swing.filechooser.FileSystemView

class datamhs : JFrame() {
    private val tfnama: JTextField? = null
    private val tfnim: JTextField? = null
    private val tfmatkul: JTextField? = null
    private val btnsubmit: JButton? = null
    private val panel: JPanel? = null
    private val tfipk: JTextField? = null
    private val btnfoto: JButton? = null
    private val getfoto: JLabel? = null

    init {
        contentPane = panel
        title = "Data Mahasiswa"
        setSize(500, 600)
        defaultCloseOperation = EXIT_ON_CLOSE
        isVisible = true
        btnfoto!!.addActionListener { e ->
            val com = e.actionCommand
            if (com == "save") {
                val j = JFileChooser(FileSystemView.getFileSystemView().homeDirectory)
                val r = j.showSaveDialog(null)
                if (r == JFileChooser.APPROVE_OPTION) {
                    getfoto!!.text = j.selectedFile.absolutePath
                } else getfoto!!.text = "the user cancelled the operation"
            } else {
                val j = JFileChooser(FileSystemView.getFileSystemView().homeDirectory)
                val r = j.showOpenDialog(null)
                if (r == JFileChooser.APPROVE_OPTION) {
                    getfoto!!.text = j.selectedFile.absolutePath
                } else getfoto!!.text = "the user cancelled the operation"
            }
        }
        btnsubmit!!.addActionListener(object : ActionListener {
            override fun actionPerformed(e: ActionEvent) {
                val nama = tfnama!!.text
                val nim = tfnim!!.text
                val ipk = tfipk!!.text
                val matkul = tfmatkul!!.text
                val paspoto = getfoto!!.text
                val subm = submit()
                subm.getData(nama, nim, ipk, matkul, paspoto)
                subm.isVisible = true
                dispose()
                mhs = addDatabase(nama, nim, ipk, matkul, paspoto)
            }

            var mhs: datamahasiswa? = null
            private fun addDatabase(
                nama: String,
                nim: String,
                ipk: String,
                matkul: String,
                paspoto: String
            ): datamahasiswa? {
                var mhs: datamahasiswa? = null
                val url = "jdbc:mysql://localhost/mahasiswa?serverTimezone=UTC"
                val username = "root"
                val password = ""
                try {
                    val conn = DriverManager.getConnection(url, username, password)
                    val statement = conn.createStatement()
                    val sql = "INSERT INTO users (nama, nim, ipk, matkul, foto)" +
                            "VALUES (?, ?, ? ,? ,?)"
                    val preparedStatement = conn.prepareStatement(sql)
                    preparedStatement.setString(1, nama)
                    preparedStatement.setString(2, nim)
                    preparedStatement.setString(3, ipk)
                    preparedStatement.setString(4, matkul)
                    preparedStatement.setString(5, paspoto)
                    val addRows = preparedStatement.executeUpdate()
                    if (addRows > 0) {
                        mhs = datamahasiswa()
                        mhs!!.nama = nama
                        mhs!!.nim = nim
                        mhs!!.ipk = ipk
                        mhs!!.matkul = matkul
                        mhs!!.foto = paspoto
                    }
                    statement.close()
                    conn.close()
                } catch (e: Exception) {
                    e.printStackTrace()
                }
                return mhs
            }
        })
    }

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            val result = datamhs()
        }
    }
}