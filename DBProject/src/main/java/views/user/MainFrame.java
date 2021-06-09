/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package views.user;

import controllers.user.CurrentUserUsername;
import javax.swing.*;
import controllers.user.XemTinhTrangYeuCauController;
import controllers.user.XemLichSuDungNhaVanHoaController;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.*;
import java.util.List;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumnModel;
import models.XemLichSuDung;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;


public class MainFrame extends JFrame {

    private JButton trangChu, xemYeuCau, xemLichSuDung, taoYeuCauMoi, hienThi,hienThi1;
    private XemTinhTrangYeuCauController tinhTrangYeuCau = new XemTinhTrangYeuCauController();
    private JTable jTable1 = tinhTrangYeuCau.hienThiTinhTrangYeuCau(tinhTrangYeuCau.getTinhTrangYeuCau(new CurrentUserUsername().getCurrentUserUsrename()));
    private JTableHeader headerTinhTrangYeuCau = jTable1.getTableHeader();
    private JTableHeader headerDanhSachSuKien;
    private TableColumnModel columnModelDanhSachSuKien;
    private JScrollPane jScrollPane1 = new JScrollPane(jTable1);
    private TableColumnModel columnModelTinhTrangYeuCau = jTable1.getColumnModel();
    XemLichSuDungNhaVanHoaController xemLichSuDungNhaVanHoaController = new XemLichSuDungNhaVanHoaController();
    private JComboBox jComboBox = new JComboBox(xemLichSuDungNhaVanHoaController.layDanhSachPhong());
    JTable jTable2;
    JScrollPane jScrollPane2;
    private boolean xemYeuCauExist = false, xemLichSuDungExist = false;
    List<XemLichSuDung> room;
    int remove = 0;
    private JLabel jLabel;
    private JLabel labelYeuCauDaTao;
    
    public MainFrame() {
//        add(new JLabel(new ImageIcon("/Volumes/Data/Eagle Pictures/patrick-hendry-L4EsOAoU8-M-unsplash.jpg")));
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        
        this.setSize(1000, 500);
        this.setBackground(Color.yellow);
        this.setLocation(dim.width / 2 - this.getSize().width / 2, dim.height / 2 - this.getSize().height / 2);
        this.setVisible(true);
       
        this.setLayout(null);
        invalidate();
        validate();
        //Thêm nút trang chủ và các chức năng cho nút này

        trangChu = new JButton("Facebook");
        int height = 80;
         trangChu.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        trangChu.setText("Facebook");
        trangChu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                try {
            String url = "https://www.facebook.com/pages/Nh%C3%A0%20V%C4%83n%20Ho%C3%A1%20Qu%E1%BA%ADn%20Hai%20B%C3%A0%20Tr%C6%B0ng/418585548201171/"; // TODO add your handling code here:
            java.awt.Desktop.getDesktop().browse(java.net.URI.create(url));
            }   catch (java.io.IOException ex) {
                    Logger.getLogger(MainFrame.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        
        trangChu.setBounds(40, 40,100, 60);
        trangChu.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ee) {
                if (xemYeuCauExist) {
                    repaint();
                    invalidate();
                    validate();
                    remove(jScrollPane1);
                    repaint();
                    invalidate();
                    validate();
                    remove(labelYeuCauDaTao);
                    repaint();
                    invalidate();
                    validate();
                    xemYeuCauExist = false;
                    repaint();
                    invalidate();
                    validate();

                }
                if (xemLichSuDungExist) {
                    remove(hienThi);
                    remove(jComboBox);
                    remove(jLabel);
                    if (remove == 1) {
                        remove(jScrollPane2);
                        remove = 0;
                    }
                    repaint();
                    xemLichSuDungExist = false;
                    invalidate();
                    validate();
                }
                repaint();
                invalidate();
                validate();
                setLayout(null);
            }
        });
        add(trangChu);

        //Thêm nút Xem Yêu Cầu và các chức năng cho nút này.
        xemYeuCau = new JButton("<html>Yêu Cầu Đã Tạo<html>");
        xemYeuCau.setFont(new java.awt.Font("Tahoma", 1, 12));
        labelYeuCauDaTao = new JLabel("Danh sách các yêu cầu đã tạo");
        labelYeuCauDaTao.setBounds(380, 50, 500, 50);
        labelYeuCauDaTao.setFont(new Font("tahoma", 1, 24));
        xemYeuCau.setBounds(40, 150, 100, 60);
        xemYeuCau.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {               
                repaint();
                invalidate();
                validate();
                add(labelYeuCauDaTao);
                xemYeuCauExist = true;
                jTable1.setRowHeight(40);
                headerTinhTrangYeuCau.setPreferredSize(new Dimension(100, 50));
                jScrollPane1.setBounds(220, 120, 700, 300);
                columnModelTinhTrangYeuCau.getColumn(0).setPreferredWidth(5);
                add(jScrollPane1);
                
                if (xemLichSuDungExist) {
                    remove(hienThi);
                    remove(jComboBox);
                    remove(jLabel);
                    if (remove == 1) {
                        remove(jScrollPane2);
                        remove = 0;
                    }
                    repaint();
                    xemLichSuDungExist = false;
                    invalidate();
                    validate();
                }
            }
        });

        getContentPane().add(xemYeuCau);

        //Thêm nút xem lịch sử dụng và các chức năng cho nút này
        xemLichSuDung = new JButton("<html>Lịch Sử Dụng<html>");
        xemLichSuDung.setFont(new java.awt.Font("Tahoma", 1, 12));
        xemLichSuDung.setBounds(40, 240, 100, 60);
        jComboBox.setBounds(440, 50, 250, 50);
        hienThi = new JButton("Hiển thị");
        hienThi.setFont(new java.awt.Font("Tahoma", 1, 12));
        hienThi.setBounds(720, 50, 100, 50);
        jLabel = new JLabel("Chọn phòng muốn xem: ");
        jLabel.setFont(new Font("Tahoma", 1, 16));
        jLabel.setBounds(240, 50, 200, 50);

        xemLichSuDung.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ee) {
                add(jLabel);
                if (xemYeuCauExist) {
                    remove(jScrollPane1);
                    remove(labelYeuCauDaTao);
                    
                    xemYeuCauExist = false;
                    repaint();
                    invalidate();
                    validate();
                }
                xemLichSuDungExist = true;
                invalidate();
                validate();
                repaint();
                add(jComboBox);
                invalidate();
                validate();
                repaint();
                add(hienThi);
                invalidate();
                validate();
                repaint();

            }
        });
        add(xemLichSuDung);

        //Phần code để in ra danh sách sự kiện trong phòng mà người dùng chọn                       
        hienThi.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ee) {
                XemLichSuDungNhaVanHoaController xemLichSuDungNhaVanHoaController2 = new XemLichSuDungNhaVanHoaController();
                room = xemLichSuDungNhaVanHoaController2.getLichSuDung(jComboBox.getItemAt(jComboBox.getSelectedIndex()).toString());
                jTable2 = xemLichSuDungNhaVanHoaController2.setTableModel(room);
                jTable2.setRowHeight(40);
                headerDanhSachSuKien = jTable2.getTableHeader();
                headerDanhSachSuKien.setPreferredSize(new Dimension(100, 50));
                columnModelDanhSachSuKien = jTable2.getColumnModel();
                if (remove == 1) {
                    remove(jScrollPane2);
                    repaint();
                }
                remove = 1;
                jScrollPane2 = new JScrollPane(jTable2);
                jScrollPane2.setBounds(220, 120, 700, 300);
                setLayout(null);
                add(jScrollPane2);
            }
        });

        taoYeuCauMoi = new JButton("<html>Tạo Yêu Cầu Mới<html>");
        taoYeuCauMoi.setBounds(40, 330, 100, 60);
        taoYeuCauMoi.setFont(new java.awt.Font("Tahoma", 1, 12));
        taoYeuCauMoi.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new DienDonSuDungNhaVanHoaViews().hienThi();
                dispose();
            }
        });
        add(taoYeuCauMoi);
        invalidate();
        validate();
        repaint();
        hienThi1 = new JButton("Cập Nhật");
        hienThi1.setBounds(720, 80, 100, 60);
        hienThi1.setFont(new java.awt.Font("Tahoma", 1, 12));
        hienThi1.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                dispose();
                new MainFrame();
            }
        });
        
    }
    
//    public static void main(String args[]) {
//        new MainFrame();
//    }
    private ImageIcon loadImage() {

        ImageIcon ii = new ImageIcon("D:\\Desktop\\Community-Centre-Management-System\\PoCProject\\src\\main\\java\\views\\user\\rsz_rsz_uidesign3.jpg");
        return ii;
    }

    private void setIconImage(ImageIcon ii) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
