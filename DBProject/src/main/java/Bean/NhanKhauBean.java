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
                + "<h1>Th??ng tin c?? b???n:"
                + "<p>H??? T??n: <b>" + nhanKhauModel.getHoTen() + "</p>"
                + "<p>N??m Sinh: <b>" + nhanKhauModel.getNamSinh() + "</p>"
                + "<p>Gi???i T??nh: <b>" + nhanKhauModel.getGioiTinh() + "</p>"
                + "<p>Nguy??n Qu??n: <b>" + nhanKhauModel.getNguyenQuan()+ "</p>"
                + "<p>D??n T???c: <b>" + nhanKhauModel.getDanToc()+ "</p>"
                + "<p>T??n Gi??o: <b>" + nhanKhauModel.getTonGiao()+ "</p>"
                + "<p>Qu???c T???ch: <b>" + nhanKhauModel.getQuocTich()+ "</p>"
                + "<p>S??? CMT: <b>" + chungMinhThuModel.getSoCMT()+ "</p>"
                + "<p>H??? Chi???u S??? : <b>" + nhanKhauModel.getSoHoChieu()+ "</p>"
                + "<p>N??i Th?????ng Tr?? : <b>" + nhanKhauModel.getNoiThuongTru()+ "</p>"
                + "<p>?????a Ch??? Hi???n T???i : <b>" + nhanKhauModel.getDiaChiHienNay()+ "</p>"
                + "<h1>Ti???u s???<table>"
                        + "<tr>"
                        + "<th>T??? ng??y</th>"
                        + "<th>?????n ng??y</th>"
                        + "<th>?????a ch???</th>"
                        + "<th>N??i l??m vi???c</th>"
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
