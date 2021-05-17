/*
 * File này dùng để hiển thị giao diện cho phép người quản lí phê duyệt một yêu cầu 
 * sau khi nhấp vào một yêu cầu cụ thể trong bảng danh sách các yêu cầu đang chờ 
 * phê duyệt
 */
package views.mng;

import java.awt.*;
import java.awt.event.*;
import javax.swing.JButton;
import javax.swing.JFrame;
import controllers.mng.PheDuyetYeuCauNguoiDung;
import controllers.mng.CurrentMngUsername;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

/**
 *
 * @author macbookpro
 */
public class XacNhanYeuCau {

    private JButton accept, deny;
    private JFrame jFrame;
    private JTextArea note, fee;
    private boolean reset = false;

    public boolean isReset() {
        return reset;
    }
    

    public XacNhanYeuCau(int requestID) {
        note = new JTextArea();
        fee = new JTextArea();
        accept = new JButton("Chấp nhận");
        deny = new JButton("Từ chối");
        jFrame = new JFrame("Xử lí yêu cầu");
        jFrame.setSize(700, 600);
        jFrame.setLayout(null);
        jFrame.setVisible(true);
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();        
        jFrame.setLocation(dim.width / 2 - jFrame.getSize().width / 2, dim.height / 2 - jFrame.getSize().height / 2);
        // Label mang dòng "Quyết định đối với yêu cầu".
        JLabel phanQuyet = new JLabel("Quyết định đối với yêu cầu");
        phanQuyet.setBounds(170, 30, 350, 50);
        phanQuyet.setFont(new Font("Lucida Grande", Font.PLAIN, 24));
        jFrame.add(phanQuyet);
        // Thêm vùng để người quản lí nhập thêm chú thích cho việc quyết định đối với yêu cầu
        JLabel noteLabel = new JLabel("<html>Lưu ý cho đơn đăng kí này: <html>");
        noteLabel.setBounds(50, 110, 100, 50);
        JScrollPane notePane = new JScrollPane(noteLabel);
        noteLabel.setBounds(50, 110, 100, 50);
        jFrame.add(noteLabel);
        note.setBounds(200, 110, 250, 150);
        jFrame.add(note);
        // Label mang dòng "Chi phí cho yêu cầu"
        JLabel feeLabel = new JLabel("<html>Phí đối với yêu cầu<html>");
        feeLabel.setBounds(50, 290, 100, 50);
        jFrame.add(feeLabel);
        // Thêm vùng để người quản lí nhập phí cho trường hợp yêu cầu được thông qua
        fee.setBounds(200, 290, 250, 50);
        jFrame.add(fee);
        // Thêm nút "Chấp nhận" và các thuộc tính cho nút này.
        accept.setBounds(180, 400, 100, 50);
        jFrame.add(accept);
        accept.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                CurrentMngUsername currentMngUsername = new CurrentMngUsername();
                PheDuyetYeuCauNguoiDung pheDuyetYeuCauNguoiDung = new PheDuyetYeuCauNguoiDung();
                if (fee.getText().length() == 0) {
                    JOptionPane.showMessageDialog(null, "Phải bắt buộc có phí nếu yêu cầu được chấp nhận", "Message", JOptionPane.WARNING_MESSAGE);
                } else {                    
                    if (pheDuyetYeuCauNguoiDung.ketQuaPheDuyet(requestID, "Chấp nhận", currentMngUsername.getCurrentMngusername(), note.getText(), Integer.valueOf(fee.getText().toString()))) {
                        JOptionPane.showMessageDialog(null, "Phê duyệt thành công", "Message", JOptionPane.WARNING_MESSAGE);
                        jFrame.dispose();
                        reset = true;
                    } else {
                        JOptionPane.showMessageDialog(null, "Đã có lỗi xảy ra. Yêu cầu chưa được phê duyệt thành công", "Warning", JOptionPane.WARNING_MESSAGE);
                    }
                    pheDuyetYeuCauNguoiDung.loaiBoYeuCauDaPheDuyet(requestID);
                }
            }
        });

        // Thêm nút "Từ chối" và các thuộc tính cho nút này
        deny.setBounds(380, 400, 100, 50);
        jFrame.add(deny);
        deny.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                CurrentMngUsername currentMngUsername = new CurrentMngUsername();
                PheDuyetYeuCauNguoiDung pheDuyetYeuCauNguoiDung = new PheDuyetYeuCauNguoiDung();
                if (pheDuyetYeuCauNguoiDung.ketQuaPheDuyet(requestID, "Từ chối", currentMngUsername.getCurrentMngusername(), note.getText(), 0)) {
                    JOptionPane.showMessageDialog(null, "Phê duyệt thành công", "Message", JOptionPane.WARNING_MESSAGE);
                   jFrame.dispose();
                   reset = true;
                } else {
                    JOptionPane.showMessageDialog(null, "Đã có lỗi xảy ra. Yêu cầu chưa được phê duyệt thành công", "Warning", JOptionPane.WARNING_MESSAGE);
                }
                pheDuyetYeuCauNguoiDung.loaiBoYeuCauDaPheDuyet(requestID);
            }
        });
        jFrame.invalidate();
        jFrame.validate();
    }

    public static void main(String args[]) {
        new XacNhanYeuCau(63);
    }

}
