package trainning.sp01.Response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import trainning.sp01.Entity.ChiTietHoaDon;
import trainning.sp01.Entity.HoaDon;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class HoaDonResponse {
    private Integer hoaDonId;
    private Integer khachHangId;
    private String tenHoaDon;
    private String maGiaoDich;
    private LocalDate thoiGianTao;
    private LocalDate thoiGianCapNhat;
    private String ghiChu;
    private Double tongTien;
    private List<ChiTietHoaDon> chiTietHoaDonList;

    public static HoaDonResponse mapHoaDonToHoaDonResponse(HoaDon hoaDon){
        return new HoaDonResponse(
            hoaDon.getHoaDonId(),
            hoaDon.getKhachHangId(),
            hoaDon.getTenHoaDon(),
            hoaDon.getMaGiaoDich(),
            hoaDon.getThoiGianTao(),
            hoaDon.getThoiGianCapNhat(),
            hoaDon.getGhiChu(),
            hoaDon.getTongTien(),
            hoaDon.getChiTietHoaDonList()
        );
    }
}
