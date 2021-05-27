/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package views.mng;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JFrame;
import controllers.mng.PheDuyetYeuCauNguoiDung;
import java.awt.Dimension;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumnModel;
import controllers.mng.ThongTinNguoiSuDungController;
import java.awt.Toolkit;
import models.ThongTinNguoSuDungModels;
import javax.swing.JLabel;

/**
 *
 * @author macbookpro
 */
public class PheDuyetYeuCauNguoiDungViews {

    private PheDuyetYeuCauNguoiDung pheDuyet = new PheDuyetYeuCauNguoiDung();
    private JTable jTable1 = pheDuyet.danhSachChuaPheDuyetTable(pheDuyet.getDanhSachChuaPheDuyet());
    private boolean reset = false;

    public boolean isReset() {
        return reset;
    }

    public void setReset(boolean reset) {
        this.reset = reset;
    }

    public JScrollPane pheDuyetYeuCauNguoiDung() {
        // Phương thức này trả về JScrollPane. Dùng nó để thêm trực tiếp vào JFrame        
        // Các danh sách đang chờ phê duyệt        
        jTable1.setRowHeight(40);
        JTableHeader headerJTable1 = jTable1.getTableHeader();
        headerJTable1.setPreferredSize(new Dimension(100, 50));
        TableColumnModel jTable1ColumnModel = jTable1.getColumnModel();
        jTable1ColumnModel.getColumn(0).setPreferredWidth(5);
        jTable1.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2) {
                    JTable target = (JTable) e.getSource();
                    int clickedRow = target.getSelectedRow();
                    int clickedColumn = target.getSelectedColumn();
                    if (clickedColumn == 1) {
                        // Nếu cột được chọn là cột username thì hiển thị thông tin của người đăng kí lên
                        JFrame jFrame2 = new JFrame("Thông tin người sử dung");

                        jFrame2.setSize(380, 450);
                        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
                        jFrame2.setLocation(dim.width / 2 - jFrame2.getSize().width / 2, dim.height / 2 - jFrame2.getSize().height / 2);
                        jFrame2.setLayout(null);
                        jFrame2.setVisible(true);
                        //
                        ThongTinNguoiSuDungController nguoiSuDung = new ThongTinNguoiSuDungController();
                        ThongTinNguoSuDungModels thongTinNSD = nguoiSuDung.nguoiSuDung(jTable1.getValueAt(clickedRow, 1).toString());
                        //
                        JLabel userUsername = new JLabel();
                        userUsername.setText("Tên đăng nhập: " + thongTinNSD.getUserUsername());
                        userUsername.setBounds(50, 30, 400, 30);
                        jFrame2.add(userUsername);
                        //
                        JLabel fullName = new JLabel();
                        fullName.setText("Họ Tên: " + thongTinNSD.getUserFullname());
                        fullName.setBounds(50, 80, 400, 30);
                        jFrame2.add(fullName);
                        //
                        JLabel phoneNumber = new JLabel();
                        phoneNumber.setText("Số điện thoại: " + thongTinNSD.getPhoneNumber());
                        phoneNumber.setBounds(50, 130, 400, 30);
                        jFrame2.add(phoneNumber);
                        //
                        JLabel address = new JLabel();
                        address.setText("Địa chỉ: " + thongTinNSD.getUserAddress());
                        address.setBounds(50, 180, 400, 30);
                        jFrame2.add(address);
                        //                        
                        JLabel birthDay = new JLabel();
                        birthDay.setText("Ngày sinh: " + thongTinNSD.getBirthDay());
                        birthDay.setBounds(50, 230, 400, 30);
                        jFrame2.add(birthDay);
                        // 20
                        JLabel job = new JLabel();
                        job.setText("Công việc: " + thongTinNSD.getJob());
                        job.setBounds(50, 280, 400, 30);
                        jFrame2.add(job);

                        //
                        JLabel signUpDate = new JLabel();
                        signUpDate.setText("Ngày đăng kí: " + thongTinNSD.getSignUpDate());
                        signUpDate.setBounds(50, 330, 400, 30);
                        jFrame2.add(signUpDate);
                    } else if (clickedColumn == 0) {
                        // Nếu cột được chọn là cột RequestID thì hiển thị giao diện phê duyệt của yêu cầu đó lên
                        int requestID = Integer.valueOf(jTable1.getValueAt(clickedRow, 0).toString());
                        setReset(true);
                        XacNhanYeuCau xacNhanYeuCau = new XacNhanYeuCau(requestID);
                        setReset(xacNhanYeuCau.isReset());

                    } else if (clickedColumn == 5) {
                        // Nếu cột được chọn là mô tả yêu cầu thì hiển thị mô tả yêu cầu ở một JFrame mới. Phòng trường
                        // hợp mô tả quá dài không hiển thị hết được trong bảng.
                        JFrame jFrame3 = new JFrame("Mô tả yêu cầu");
                        jFrame3.setSize(400, 400);
                        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
                        jFrame3.setLocation(dim.width / 2 - jFrame3.getSize().width / 2, dim.height / 2 - jFrame3.getSize().height / 2);
                        jFrame3.setLayout(null);
                        jFrame3.setVisible(true);
                        JLabel moTaYeuCau = new JLabel(jTable1.getValueAt(clickedRow, 5).toString());
                        JScrollPane moTaYeuCauPane = new JScrollPane(moTaYeuCau);
                        moTaYeuCauPane.setBounds(35, 35, 300, 300);
                        jFrame3.add(moTaYeuCauPane);
                    }
                }
            }

            @Override
            public void mousePressed(MouseEvent e) {
//                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }

            @Override
            public void mouseReleased(MouseEvent e) {
//                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }

            @Override
            public void mouseEntered(MouseEvent e) {
//                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }

            @Override
            public void mouseExited(MouseEvent e) {
//                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }

        });

        JScrollPane mainJScrollPane = new JScrollPane(jTable1);
        return mainJScrollPane;
    }

    public PheDuyetYeuCauNguoiDungViews() {

    }

    public static void main(String args[]) {
        PheDuyetYeuCauNguoiDungViews vies = new PheDuyetYeuCauNguoiDungViews();
        JFrame jFrame = new JFrame();
        jFrame.setLayout(null);
        jFrame.setSize(1000, 1000);
        jFrame.setVisible(true);
        JScrollPane jScrollPane = vies.pheDuyetYeuCauNguoiDung();
        jScrollPane.setBounds(10, 10, 800, 500);
        jFrame.add(jScrollPane);
        while (true) {
            if (vies.isReset()) {
                jFrame.remove(jScrollPane);
                jFrame.repaint();
                jFrame.invalidate();
                jFrame.validate();
                jFrame.setLayout(null);
                vies = new PheDuyetYeuCauNguoiDungViews();
                jScrollPane = vies.pheDuyetYeuCauNguoiDung();
                jScrollPane.setBounds(10, 10, 800, 500);
                jFrame.add(jScrollPane);
                vies.setReset(false);
            }

        }
    }
}
