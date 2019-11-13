/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sekolah;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Asus A450L
 */
public class ScrollBar extends javax.swing.JInternalFrame {

    Koneksi konek = new Koneksi();
    DefaultTableModel model;

    //mendapatkan tanggal sekarang
    Date tglSekarang = new Date();

    //menentukan format tanggal
    SimpleDateFormat formatTgl = new SimpleDateFormat("yyyy-MM-dd");

    /**
     * Creates new form ScrollBar
     */
    public ScrollBar() {
        initComponents();
        loadData("", "");
    }

    //menampilkan data berdasarkan kata kunci / keyword
    private void loadData(String key, String fk) {
        tblSiswa.setAutoResizeMode(tblSiswa.AUTO_RESIZE_OFF);
        model = new DefaultTableModel();

        tblSiswa.setModel(model);

        model.addColumn("No Induk");
        model.addColumn("Nama");
        model.addColumn("Alamat");
        model.addColumn("Jenis kelamin");
        model.addColumn("Tempat lahir");
        model.addColumn("Tanggal lahir");

        model.getDataVector().removeAllElements();
        //konfirm kalo data sudah dihapus
        model.fireTableDataChanged();

        try {
            Connection c = konek.getKoneksi();
            Statement s = c.createStatement();

            String sql = "";

//            if (key.isEmpty()) {
//                sql = "SELECT * FROM siswa";
//            } else {
//                sql = "SELECT * FROM siswa WHERE nama_siswa "
//                        + "LIKE '%" + key + "%'";
//            }
            if (key.isEmpty() && fk.isEmpty()) {
                //jika key kosong DAN fk kosong
                sql = "SELECT * FROM siswa";

            } else if (key.isEmpty() && !fk.isEmpty()) {
                //jika key kosong dan fk ada
                sql = "SELECT * FROM siswa WHERE jkelamin = '" + fk + "'";

            } else if (!key.isEmpty() && fk.isEmpty()) {
                //jika key ada DAN fk kosong
                sql = "SELECT * FROM siswa WHERE nama_siswa "
                        + "LIKE '%" + key + "%'";
            } else {
                //jika dua2nya ada
                sql = "SELECT * FROM siswa WHERE nama_siswa LIKE '%"+key+"%' "
                        + "AND jkelamin = '"+fk+"'";
            }
            //String sql = "SELECT * FROM siswa WHERE siswa LIKE ";
            ResultSet r = s.executeQuery(sql);
            while (r.next()) {

                Object[] obj = new Object[6];

                obj[0] = r.getString("no_induk");
                obj[1] = r.getString("nama_siswa");
                obj[2] = r.getString("alamat");
                obj[3] = r.getString("jkelamin");
                obj[4] = r.getString("tempat_lahir");

                Object tglLahir1 = r.getDate("tgl_lahir");
                SimpleDateFormat formatTgl1 = new SimpleDateFormat("dd MMMMM yyyy");
                obj[5] = formatTgl1.format(tglLahir1);

                model.addRow(obj);
            }
        } catch (SQLException e) {
            System.err.println("gagal mengambil data:\n" + e);
        }
    }

    //menampilkan data berdasarkan urutan / order
    private void loadData(int order) {
        tblSiswa.setAutoResizeMode(tblSiswa.AUTO_RESIZE_OFF);
        model = new DefaultTableModel();

        tblSiswa.setModel(model);

        model.addColumn("No Induk");
        model.addColumn("Nama");
        model.addColumn("Alamat");
        model.addColumn("Jenis kelamin");
        model.addColumn("Tempat lahir");
        model.addColumn("Tanggal lahir");

        model.getDataVector().removeAllElements();
        //konfirm kalo data sudah dihapus
        model.fireTableDataChanged();

        try {
            Connection c = konek.getKoneksi();
            Statement s = c.createStatement();

            String sql = "";

            //membuat kode SQL sesuai dengan kondisi
            switch (order) {
                case 1:
                    sql = "SELECT * FROM siswa ORDER BY nama_siswa ASC";
                    break;
                case 2:
                    sql = "SELECT * FROM siswa ORDER BY nama_siswa DESC";
                    break;
                case 3:
                    sql = "SELECT * FROM siswa ORDER BY tgl_lahir ASC";
                    break;
                case 4:
                    sql = "SELECT * FROM siswa ORDER BY tgl_lahir DESC";
                    break;
                case 5:
                    sql = "SELECT * FROM siswa ORDER BY no_induk ASC";
                    break;
                default:
                    sql = "SELECT * FROM siswa";
                    break;
            }

            //String sql = "SELECT * FROM siswa WHERE siswa LIKE ";
            ResultSet r = s.executeQuery(sql);
            while (r.next()) {

                Object[] obj = new Object[6];

                obj[0] = r.getString("no_induk");
                obj[1] = r.getString("nama_siswa");
                obj[2] = r.getString("alamat");
                obj[3] = r.getString("jkelamin");
                obj[4] = r.getString("tempat_lahir");

                Object tglLahir1 = r.getDate("tgl_lahir");
                SimpleDateFormat formatTgl1 = new SimpleDateFormat("dd MMMMM yyyy");
                obj[5] = formatTgl1.format(tglLahir1);

                model.addRow(obj);
            }
        } catch (SQLException e) {
            System.err.println("gagal mengambil data:\n" + e);
        }
    }

    //method untuk membersihkan form
    private void bersihkanForm() {
        txtNama.setText("");
        txtInduk.setText("");
        txtAlamat.setText("");
        txtTanggalLahir.setDate(tglSekarang);
        txtTempatLahir.setText("");

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jPanel1 = new javax.swing.JPanel();
        btnHapus = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        txtTanggalLahir = new com.toedter.calendar.JDateChooser();
        jLabel8 = new javax.swing.JLabel();
        jComboBox1 = new javax.swing.JComboBox<>();
        jPanel2 = new javax.swing.JPanel();
        jLabel9 = new javax.swing.JLabel();
        cbxFilterKelamin = new javax.swing.JComboBox<>();
        btnFilter = new javax.swing.JButton();
        txtNama = new javax.swing.JTextField();
        txtAlamat = new javax.swing.JTextField();
        cboJenisKelamin = new javax.swing.JComboBox<>();
        txtTempatLahir = new javax.swing.JTextField();
        rdoMultimedia = new javax.swing.JRadioButton();
        rdoRPL = new javax.swing.JRadioButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblSiswa = new javax.swing.JTable();
        btnSimpan = new javax.swing.JButton();
        txtCari = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        txtInduk = new javax.swing.JTextField();
        btnCari = new javax.swing.JButton();
        btnEdit = new javax.swing.JButton();

        btnHapus.setText("Hapus");
        btnHapus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnHapusActionPerformed(evt);
            }
        });

        jButton1.setText("Batal");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        txtTanggalLahir.setDateFormatString("dd MMMMM yyyy");

        jLabel8.setText("Urut berdasarkan:");

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Nama (A - Z)", "Nama (Z - A)", "Tanggal Lahir (A - Z)", "Tanggal Lahir (Z - A)", "No. Induk" }));
        jComboBox1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox1ActionPerformed(evt);
            }
        });

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder("Filter Data"));

        jLabel9.setText("Jenis Kelamin:");

        cbxFilterKelamin.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Laki-laki", "Perempuan" }));

        btnFilter.setText("Filter");
        btnFilter.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnFilterActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel9)
                    .addComponent(cbxFilterKelamin, 0, 126, Short.MAX_VALUE)
                    .addComponent(btnFilter, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel9)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cbxFilterKelamin, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 72, Short.MAX_VALUE)
                .addComponent(btnFilter)
                .addContainerGap())
        );

        cboJenisKelamin.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Laki-laki", "Perempuan" }));

        rdoMultimedia.setText("MM");

        rdoRPL.setText("RPL");

        tblSiswa.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        tblSiswa.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblSiswaMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tblSiswa);

        btnSimpan.setText("Simpan");
        btnSimpan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSimpanActionPerformed(evt);
            }
        });

        txtCari.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtCariKeyReleased(evt);
            }
        });

        jLabel1.setText("Nama");

        jLabel2.setText("Alamat");

        jLabel3.setText("Jenis Kelamin");

        jLabel4.setText("Tempat Lahir");

        jLabel5.setText("Jurusan");

        jLabel6.setText("Tanggal Lahir");

        jLabel7.setText("Nomor Induk");

        btnCari.setText("Cari");
        btnCari.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCariActionPerformed(evt);
            }
        });

        btnEdit.setText("Edit");
        btnEdit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel7)
                            .addComponent(jLabel1)
                            .addComponent(jLabel2)
                            .addComponent(jLabel3)
                            .addComponent(jLabel4)
                            .addComponent(jLabel6)
                            .addComponent(jLabel5))
                        .addGap(30, 30, 30)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(rdoMultimedia)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(rdoRPL))
                            .addComponent(txtInduk, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtNama)
                            .addComponent(txtAlamat)
                            .addComponent(cboJenisKelamin, 0, 206, Short.MAX_VALUE)
                            .addComponent(txtTempatLahir)
                            .addComponent(txtTanggalLahir, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(btnSimpan)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnEdit)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnHapus)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton1)))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(txtCari, javax.swing.GroupLayout.PREFERRED_SIZE, 151, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnCari)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel8)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 426, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(35, 35, 35))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(41, 41, 41)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(txtInduk, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(txtNama, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(txtAlamat, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(cboJenisKelamin, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(txtTempatLahir, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel6)
                    .addComponent(txtTanggalLahir, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel5)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(rdoMultimedia)
                        .addComponent(rdoRPL)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnSimpan)
                    .addComponent(btnEdit)
                    .addComponent(btnHapus)
                    .addComponent(jButton1))
                .addGap(26, 183, Short.MAX_VALUE))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtCari, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnCari)
                    .addComponent(jLabel8)
                    .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addContainerGap())
        );

        jScrollPane1.setViewportView(jPanel1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 938, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 419, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnHapusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnHapusActionPerformed
        // TODO add your handling code here:
        String induk = txtInduk.getText();

        int i = JOptionPane.showConfirmDialog(rootPane, "Apakah ingin menghapus data?", "Konfirmasi Hapus", JOptionPane.OK_CANCEL_OPTION);

        if (i == 0) {
            try {
                Connection c = konek.getKoneksi();
                Statement s = c.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);

                String sql = "DELETE FROM siswa WHERE no_induk=?";

                try ( PreparedStatement p = c.prepareStatement(sql)) {
                    p.setString(1, induk);
                    p.executeUpdate();
                }
            } catch (SQLException e) {
                System.err.println("Error saat menghapus data: \n" + e);
            } finally {
                JOptionPane.showMessageDialog(rootPane, "Data Berhasil Dihapus", "Sukses", JOptionPane.CLOSED_OPTION);
                loadData("", "");

            }
        }
    }//GEN-LAST:event_btnHapusActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        bersihkanForm();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jComboBox1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox1ActionPerformed
        String order = String.valueOf(jComboBox1.getSelectedItem());
        /**
         * Nama (A - Z), Nama (Z - A), Tanggal Lahir (A - Z), Tanggal Lahir (Z -
         * A), No. Induk
         */
        //memanggil method loadData sesuai kondisi
        if (order.equals("Nama (A - Z)")) {
            loadData(1);
        } else if (order.equals("Nama (Z - A)")) {
            loadData(2);
        } else if (order.equals("Tanggal Lahir (A - Z)")) {
            loadData(3);
        } else if (order.equals("Tanggal Lahir (Z - A)")) {
            loadData(4);
        } else {
            loadData(5);
        }
    }//GEN-LAST:event_jComboBox1ActionPerformed

    private void tblSiswaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblSiswaMouseClicked
        // TODO add your handling code here:
        int i = tblSiswa.getSelectedRow();
        if (i == -1) {
            return;
        }

        String noInduk = (String) model.getValueAt(i, 0);
        String nama = (String) model.getValueAt(i, 1);
        String alamat = (String) model.getValueAt(i, 2);
        String jKelamin = (String) model.getValueAt(i, 3);
        String tempatLahir = (String) model.getValueAt(i, 4);

        String tglLahir, jurusan;

        try {
            Connection c = konek.getKoneksi();
            Statement s = c.createStatement();

            String sql = "SELECT tgl_lahir, jurusan FROM "
                    + "siswa WHERE no_induk = '" + noInduk + "'";

            ResultSet rs = s.executeQuery(sql);

            while (rs.next()) {
                Object[] obj = new Object[2];
                tglLahir = rs.getString("tgl_lahir");
                jurusan = rs.getString("jurusan");

                //memformat tanggal yang akan ditampilkan
                Date tanggal = null;
                try {
                    tanggal = new SimpleDateFormat("yyyy-MM-dd")
                            .parse(tglLahir);
                } catch (ParseException ex) {
                    Logger.getLogger(DataSiswa.class.getName())
                            .log(Level.SEVERE, null, ex);
                }
                //menampilkan tanggal ke jcalendar
                txtTanggalLahir.setDate(tanggal);

                if (jurusan.equals("Multimedia")) {
                    rdoMultimedia.setSelected(true);
                    rdoRPL.setSelected(false);
                } else {
                    rdoRPL.setSelected(true);
                    rdoMultimedia.setSelected(false);
                }
            }

        } catch (SQLException e) {
            System.err.println("gagal " + e);
        }

        //menampilkan data ke dalam form
        txtInduk.setText(noInduk);
        txtNama.setText(nama);
        txtAlamat.setText(alamat);
        txtTempatLahir.setText(tempatLahir);
        cboJenisKelamin.setSelectedItem(jKelamin);

    }//GEN-LAST:event_tblSiswaMouseClicked

    private void btnSimpanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSimpanActionPerformed
        // TODO add your handling code here:
        String induk = txtInduk.getText();
        String nama = txtNama.getText();
        String alamat = txtAlamat.getText();
        String tempatLahir = txtTempatLahir.getText();

        //mengambil data tanggal dari jDateChooser
        //Date tgl = txtTanggalLahir.getDate();
        String tanggalLahir
                = formatTgl.format(txtTanggalLahir.getDate());

        String jenisKelamin = String.valueOf(
                cboJenisKelamin.getSelectedItem());

        String jurusan = "";
        if (rdoMultimedia.isSelected()) {
            jurusan = "Multimedia";
        } else if (rdoRPL.isSelected()) {
            jurusan = "Rekayasa Perangkat Lunak";
        }

        try {
            Connection c = konek.getKoneksi();

            String sql = "INSERT INTO siswa "
                    + "(no_induk,nama_siswa,alamat, jkelamin, "
                    + "tempat_lahir, tgl_Lahir, jurusan) "
                    + "VALUES(?,?,?,?,?,?,?)";

            PreparedStatement p = c.prepareStatement(sql);
            p.setString(1, induk);
            p.setString(2, nama);
            p.setString(3, alamat);
            p.setString(4, jenisKelamin);
            p.setString(5, tempatLahir);
            p.setString(6, tanggalLahir);
            p.setString(7, jurusan);

            p.executeUpdate();

        } catch (SQLException e) {
            System.err.println("gagal input data: \n" + e);
        } finally {
            JOptionPane.showMessageDialog(rootPane,
                    "Berhasil menyimpan data", "Berhasil",
                    JOptionPane.INFORMATION_MESSAGE);

            loadData("", "");
            bersihkanForm();
        }
    }//GEN-LAST:event_btnSimpanActionPerformed

    private void txtCariKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCariKeyReleased
        // TODO add your handling code here:
        String kataKunci = txtCari.getText();
        loadData(kataKunci, "");
    }//GEN-LAST:event_txtCariKeyReleased

    private void btnCariActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCariActionPerformed
        // TODO add your handling code here:
        String kataKunci = txtCari.getText();
        loadData(kataKunci, "");
    }//GEN-LAST:event_btnCariActionPerformed

    private void btnEditActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditActionPerformed
        // TODO add your handling code here:
        String induk = txtInduk.getText();
        String nama = txtNama.getText();
        String alamat = txtAlamat.getText();
        String tempatLahir = txtTempatLahir.getText();
        //mengambil data tanggal dari jDateChooser
        Date tgl = txtTanggalLahir.getDate();
        String tanggalLahir = formatTgl.format(tgl);

        String jenisKelamin = String.valueOf(
                cboJenisKelamin.getSelectedItem());

        String jurusan = "";
        if (rdoMultimedia.isSelected()) {
            jurusan = "Multimedia";
        } else if (rdoRPL.isSelected()) {
            jurusan = "Rekayasa Perangkat Lunak";
        }

        try {
            Connection c = konek.getKoneksi();
            Statement s = c.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);

            String sql = "UPDATE siswa SET "
                    + "nama_siswa = ?, "
                    + "alamat = ?, "
                    + "jkelamin = ?, "
                    + "tempat_lahir = ?, "
                    + "tgl_lahir = ?, "
                    + "jurusan = ? "
                    + "WHERE no_induk = ?";

            PreparedStatement p = c.prepareStatement(sql);
            p.setString(1, nama);
            p.setString(2, alamat);
            p.setString(3, jenisKelamin);
            p.setString(4, tempatLahir);
            p.setString(5, tanggalLahir);
            p.setString(6, jurusan);
            p.setString(7, induk);

            p.executeUpdate();

        } catch (SQLException e) {
            System.out.println("sekolah.DataSiswa.btnEditActionPerformed() \n" + e);
        } finally {
            JOptionPane.showMessageDialog(rootPane,
                    "Berhasil menyimpan data", "Berhasil",
                    JOptionPane.INFORMATION_MESSAGE);

            loadData("", "");
            bersihkanForm();
        }
    }//GEN-LAST:event_btnEditActionPerformed

    private void btnFilterActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnFilterActionPerformed
        // TODO add your handling code here:
        String fk = String.valueOf(cbxFilterKelamin.getSelectedItem());
        String key = txtCari.getText();
        
        loadData(key, fk);
        
    }//GEN-LAST:event_btnFilterActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCari;
    private javax.swing.JButton btnEdit;
    private javax.swing.JButton btnFilter;
    private javax.swing.JButton btnHapus;
    private javax.swing.JButton btnSimpan;
    private javax.swing.JComboBox<String> cboJenisKelamin;
    private javax.swing.JComboBox<String> cbxFilterKelamin;
    private javax.swing.JButton jButton1;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JRadioButton rdoMultimedia;
    private javax.swing.JRadioButton rdoRPL;
    private javax.swing.JTable tblSiswa;
    private javax.swing.JTextField txtAlamat;
    private javax.swing.JTextField txtCari;
    private javax.swing.JTextField txtInduk;
    private javax.swing.JTextField txtNama;
    private com.toedter.calendar.JDateChooser txtTanggalLahir;
    private javax.swing.JTextField txtTempatLahir;
    // End of variables declaration//GEN-END:variables
}
