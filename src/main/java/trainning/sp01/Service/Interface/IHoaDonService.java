package trainning.sp01.Service.Interface;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import trainning.sp01.Entity.ChiTietHoaDon;
import trainning.sp01.Entity.HoaDon;

import java.time.LocalDate;
import java.util.List;

public interface IHoaDonService {
    public HoaDon themHoaDon(HoaDon hoaDon);
    public ChiTietHoaDon themChiTietHoaDon(ChiTietHoaDon chiTietHoaDon);
    public ChiTietHoaDon suaChiTietHoaDon(ChiTietHoaDon chiTietHoaDon);
    public HoaDon xoaHoaDon(Integer hoaDonId);
    public List<HoaDon> layHoaDon();
    public List<HoaDon> layHoaDonTheoNamThang(Integer nam, Integer thang, Pageable pageable);
    public List<HoaDon> layHoaDonTheoKhoangThoiGian(LocalDate firstDate, LocalDate lastDate, Pageable pageable);
    public List<HoaDon> layHoaDonTheoKhoangTongTien(Double minMoney, Double maxMoney, Pageable pageable);
    public List<HoaDon> timKiemHoaDonTheoMaGiaoDichHoacTenHoaDon(String maGiaoDich, String tenHoaDon, Pageable pageable);
    public Page<HoaDon> findAll(Pageable pageable);
}
