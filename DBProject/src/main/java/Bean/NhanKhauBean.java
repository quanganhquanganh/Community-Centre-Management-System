package Bean;

import java.util.ArrayList;
import java.util.List;
import models.ChungMinhThuModel;
import models.GiaDinhModel;
import models.NhanKhauModel;
import models.TieuSuModel;


public class NhanKhauBean {
    private NhanKhauModel nhanKhauModel;
    private ChungMinhThuModel chungMinhThuModel;
    private List<TieuSuModel> listTieuSuModels;
    private List<GiaDinhModel> listGiaDinhModels;
    
    public NhanKhauBean(NhanKhauModel nhanKhauModel, ChungMinhThuModel chungMinhThuModel, List<TieuSuModel> listTieuSuModels, List<GiaDinhModel> listGiaDinhModels) {
        this.nhanKhauModel = nhanKhauModel;
        this.chungMinhThuModel = chungMinhThuModel;
        this.listTieuSuModels = listTieuSuModels;
        this.listGiaDinhModels = listGiaDinhModels;
    }

    public NhanKhauBean() {
        this.nhanKhauModel = new NhanKhauModel();
        this.chungMinhThuModel = new ChungMinhThuModel();
        this.listGiaDinhModels = new ArrayList<>();
        this.listTieuSuModels = new ArrayList<>();
    }
    
    public NhanKhauModel getNhanKhauModel() {
        return nhanKhauModel;
    }

    public void setNhanKhauModel(NhanKhauModel nhanKhauModel) {
        this.nhanKhauModel = nhanKhauModel;
    }

    public ChungMinhThuModel getChungMinhThuModel() {
        return chungMinhThuModel;
    }

    public void setChungMinhThuModel(ChungMinhThuModel chungMinhThuModel) {
        this.chungMinhThuModel = chungMinhThuModel;
    }

    public List<TieuSuModel> getListTieuSuModels() {
        return listTieuSuModels;
    }

    public void setListTieuSuModels(List<TieuSuModel> listTieuSuModels) {
        this.listTieuSuModels = listTieuSuModels;
    }

    public List<GiaDinhModel> getListGiaDinhModels() {
        return listGiaDinhModels;
    }

    public void setListGiaDinhModels(List<GiaDinhModel> listGiaDinhModels) {
        this.listGiaDinhModels = listGiaDinhModels;
    }
    
    @Override
    public String toString() {
        String res =  "<html><style>p {padding: 5px; margin-left: 20px} table, th, td {border: 1px solid black; border-collapse: collapse;} table {width: 500px}</style> <div>"
                + "<h3>Thông tin cơ bản:"
                + "<p>Họ Tên: <b>" + nhanKhauModel.getHoTen() + "</p>"
                + "<p>Năm Sinh: <b>" + nhanKhauModel.getNamSinh() + "</p>"
                + "<p>Giới Tính: <b>" + nhanKhauModel.getGioiTinh() + "</p>"
                + "<p>Nguyên Quán: <b>" + nhanKhauModel.getNguyenQuan()+ "</p>"
                + "<p>Dân Tộc: <b>" + nhanKhauModel.getDanToc()+ "</p>"
                + "<p>Tôn Giáo: <b>" + nhanKhauModel.getTonGiao()+ "</p>"
                + "<p>Quốc Tịch: <b>" + nhanKhauModel.getQuocTich()+ "</p>"
                + "<p>Số CMT: <b>" + chungMinhThuModel.getSoCMT()+ "</p>"
                + "<p>Hộ Chiếu Số : <b>" + nhanKhauModel.getSoHoChieu()+ "</p>"
                + "<p>Nơi Thường Trú : <b>" + nhanKhauModel.getNoiThuongTru()+ "</p>"
                + "<p>Địa Chỉ Hiện Tại : <b>" + nhanKhauModel.getDiaChiHienNay()+ "</p>"
                + "<p>Trình Độ Học Vấn : <b>" +nhanKhauModel.getTrinhDoHocVan()+ "</p>"
                + "<p>Trình Độ Chuyên Môn: <b>" + nhanKhauModel.getTrinhDoChuyenMon()+ "</p>"
                + "<p>Trình Độ Ngoại Ngữ: <b>" + nhanKhauModel.getTrinhDoNgoaiNgu()+ "</p>"
                + "<p>Nghề Nghiệp : <b>" + nhanKhauModel.getNgheNghiep()+ "</p>"
                + "<p>Nơi Làm Việc : <b>" + nhanKhauModel.getNoiLamViec()+ "</p>"
                + "<p>Tiền Án : <b>" + nhanKhauModel.getTienAn()+ "</p>" 
                + "<h4>Tiểu sử<table>"
                        + "<tr>"
                        + "<th>Từ ngày</th>"
                        + "<th>Đến ngày</th>"
                        + "<th>Địa chỉ</th>"
                        + "<th>Nơi làm việc</th>"
                        + "</tr>";
        for (TieuSuModel tieuSuModel: listTieuSuModels) {
            res += "<tr>"
                    + "<td>"
                    + tieuSuModel.getTuNgay().toString()
                    + "</td>"
                    + "<td>"
                    + tieuSuModel.getDenNgay().toString()
                    + "</td>"
                    + "<td>"
                    + tieuSuModel.getDiaChi()
                    + "</td>"
                    + "<td>"
                    + tieuSuModel.getNoiLamViec()
                    + "</td>"
                    + "</tr>";
        }
        res += "</table>"+ "</div></html>";
        return res;
    }
}
