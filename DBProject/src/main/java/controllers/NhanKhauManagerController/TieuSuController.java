package controllers.NhanKhauManagerController;

import Bean.NhanKhauBean;
import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.event.ChangeEvent;
import javax.swing.table.DefaultTableModel;
import models.GiaDinhModel;
import models.TieuSuModel;
import utility.ClassTableModel;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.Vector;

/**
 *
 * class dieu khien hoat dong cua TieuSuJFrame
 */
public class TieuSuController {
    private NhanKhauBean nhanKhauBean;
    private JPanel tieuSuJpn;
    private DefaultTableModel modelTieuSu;
    private DefaultTableModel modelGiaDinh;
    private ClassTableModel classTableModel = null;
    private final String[] COLUMNS_TIEU_SU = {"Từ ngày", "Đến ngày", "Địa chỉ", "Nghề nghiệp", "Nơi làm việc"};
    private final String[] COLUMNS_GIA_DINH = {"Họ tên", "Năm Sinh", "Giới tính", "Quan hệ với nhân khẩu", "Nghề nghiệp", "Địa chỉ"};

    public TieuSuController(JPanel tieuSuJpn, NhanKhauBean nhanKhauBean) {
        this.nhanKhauBean = nhanKhauBean;
        this.tieuSuJpn = tieuSuJpn;
        this.classTableModel = new ClassTableModel();
    }

    
    
    public void setDataTable() {
        this.modelTieuSu =  this.classTableModel.setTableTieuSu(this.nhanKhauBean.getListTieuSuModels(), COLUMNS_TIEU_SU);
        JTable tableTieuSu = new JTable(modelTieuSu) {
            // nue sua hoan thien dong cuoi cua row thi tu gen them 1 row moi
            @Override
            public void editingStopped(ChangeEvent e) {
                int row = getEditingRow();
                int col = getEditingColumn();
                super.editingStopped(e);
                if (row == getRowCount() - 1 && col == getColumnCount() - 1)
                {
                    // thay doi vung nho heap
                    modelTieuSu.addRow(new Object[] {"", "", "", "", ""});
                }
            }
            
        };
        /*
        JTable tableGiaDinh = new JTable(modelGiaDinh) {
            @Override
            public void editingStopped(ChangeEvent e) {
                int row = getEditingRow();
                int col = getEditingColumn();
                super.editingStopped(e);
                if (row == getRowCount() - 1 && col == getColumnCount() - 1)
                {
                    // thay doi vung nho heap
                    modelGiaDinh.addRow(new Object[] {"", "", "", "", "", ""});
                }
            }
            
        };*/
        
        tableTieuSu.getTableHeader().setFont(new Font("Arial", Font.BOLD, 14));
        tableTieuSu.getTableHeader().setPreferredSize(new Dimension(100, 40));
        tableTieuSu.setRowHeight(30);
        tableTieuSu.validate();
        tableTieuSu.repaint();
        tableTieuSu.setFont(new Font("Arial", Font.PLAIN, 14));
        /*
        tableGiaDinh.getTableHeader().setFont(new Font("Arial", Font.BOLD, 14));
        tableGiaDinh.getTableHeader().setPreferredSize(new Dimension(100, 40));
        tableGiaDinh.setRowHeight(30);
        tableGiaDinh.validate();
        tableGiaDinh.repaint();
        tableGiaDinh.setFont(new Font("Arial", Font.PLAIN, 14));
        */
        JScrollPane scroll_tieu_su= new JScrollPane();
        scroll_tieu_su.getViewport().add(tableTieuSu);
        scroll_tieu_su.setPreferredSize(new Dimension(675, 220));
        
        //JScrollPane scroll_gia_dinh = new JScrollPane();
        //scroll_gia_dinh.getViewport().add(tableGiaDinh);
        //scroll_gia_dinh.setPreferredSize(new Dimension(675, 220));
        
        
        tieuSuJpn.removeAll();
        tieuSuJpn.setLayout(new BorderLayout());
        tieuSuJpn.add(scroll_tieu_su);
        tieuSuJpn.validate();
        tieuSuJpn.repaint();
    }
    
    public void changeData() {
        List<TieuSuModel> listTieuSuModels = new ArrayList<>();
        List<GiaDinhModel> listGiaDinhModels = new ArrayList<>();
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");  
        this.modelTieuSu.getDataVector().forEach((Object t) -> {
            Vector temp = (Vector) t;
            try {
                if (temp.elementAt(0) != "" && temp.elementAt(1) != "" 
                        && temp.elementAt(2) != "" && temp.elementAt(3) != "" 
                        && temp.elementAt(4) != "") {
                    TieuSuModel tieuSuModel = new TieuSuModel();
                    java.util.Date tuNgay = dateFormat.parse((String) temp.elementAt(0));
                    java.sql.Date sqlTuNgay = new java.sql.Date(tuNgay.getTime());
                    System.out.print("1");
                    tieuSuModel.setTuNgay(sqlTuNgay);
                    java.util.Date denNgay = dateFormat.parse((String) temp.elementAt(1));
                    java.sql.Date sqlDenNgay = new java.sql.Date(denNgay.getTime());
                    System.out.print("1");
                    tieuSuModel.setDenNgay(sqlDenNgay);
                    tieuSuModel.setDiaChi((String) temp.elementAt(2));
                    tieuSuModel.setNgheNghiep((String) temp.elementAt(3));
                    tieuSuModel.setNoiLamViec((String) temp.elementAt(4));
                    listTieuSuModels.add(tieuSuModel);
                }
            } catch (Exception e) {
                System.out.print(e);
                JOptionPane.showMessageDialog(null, "Sai định dạng dữ liệu", "Warning!", JOptionPane.ERROR_MESSAGE);
            }
        });
        /*
        this.modelGiaDinh.getDataVector().forEach((g) -> {
            Vector temp = (Vector) g;
            try {
                if (temp.elementAt(0) != "" && temp.elementAt(1) != "" 
                        && temp.elementAt(2) != "" && temp.elementAt(3) != "" 
                        && temp.elementAt(4) != "" && temp.elementAt(5) != "") {
                    GiaDinhModel giaDinhModel = new GiaDinhModel();
                    giaDinhModel.setHoTen((String) temp.elementAt(0));
                    java.util.Date namSinh = dateFormat.parse((String) temp.elementAt(1));
                    java.sql.Date sqlNamSinh = new java.sql.Date(namSinh.getTime());
                    System.out.print("1");
                    giaDinhModel.setNamSinh(sqlNamSinh);
                    giaDinhModel.setGioiTinh((String) temp.elementAt(2));
                    giaDinhModel.setQuanHeVoiNhanKhau((String) temp.elementAt(3));
                    giaDinhModel.setNgheNghiep((String) temp.elementAt(4));
                    giaDinhModel.setDiaChiHienTai((String)temp.elementAt(5));
                    listGiaDinhModels.add(giaDinhModel);
                }  
            } catch (Exception e) {
                System.out.print(e);
                JOptionPane.showMessageDialog(null, "Sai định dạng dữ liệu", "Warning!", JOptionPane.ERROR_MESSAGE);
            }
        });
        */
        this.nhanKhauBean.setListTieuSuModels(listTieuSuModels);
        //this.nhanKhauBean.setListGiaDinhModels(listGiaDinhModels);
    }
}
