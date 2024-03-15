package trainning.sp01.Service.Implement;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import trainning.sp01.Entity.ChiTietHoaDon;
import trainning.sp01.Entity.HoaDon;
import trainning.sp01.Entity.KhachHang;
import trainning.sp01.Entity.SanPham;
import trainning.sp01.Repository.ChiTietHoaDonRepository;
import trainning.sp01.Repository.HoaDonRepository;
import trainning.sp01.Repository.KhachHangRepository;
import trainning.sp01.Repository.SanPhamRepository;
import trainning.sp01.Service.Interface.IHoaDonService;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class HoaDonService implements IHoaDonService {
    @Autowired
    private HoaDonRepository hoaDonRepo;
    @Autowired
    private ChiTietHoaDonRepository chiTietHoaDonRepo;
    @Autowired
    private SanPhamRepository sanPhamRepo;
    @Autowired
    private KhachHangRepository khachHangRepo;

    @Override
    public HoaDon themHoaDon(HoaDon hoaDon) {
        Optional<KhachHang> khachHangOptional = khachHangRepo.findById(hoaDon.getKhachHangId());
        if (khachHangOptional.isEmpty()) return null;

        hoaDon.setThoiGianTao(LocalDate.now());
        hoaDon.setThoiGianCapNhat(LocalDate.now());
        hoaDon.setMaGiaoDich(taoMaGiaoDich());
        hoaDon.setTongTien(0.0);
        hoaDon.setKhachHang(khachHangOptional.get());

        return hoaDonRepo.save(hoaDon);
    }

    public String taoMaGiaoDich() {
        LocalDate localDate = LocalDate.now();
        String maGiaoDich = localDate.getYear() + "" + localDate.getMonthValue() + "" + localDate.getDayOfMonth();
        int soHoaDon = hoaDonRepo.soHoaDonTrongNgay(localDate);
        maGiaoDich = maGiaoDich + "_00" + (soHoaDon + 1);
        return maGiaoDich;
    }

    @Override
    public ChiTietHoaDon themChiTietHoaDon(ChiTietHoaDon chiTietHoaDon) {
        Optional<HoaDon> hoaDonOptional = hoaDonRepo.findById(chiTietHoaDon.getHoaDonId());
        if (hoaDonOptional.isEmpty()) return null;

        Optional<SanPham> sanPhamOptional = sanPhamRepo.findById(chiTietHoaDon.getSanPhamId());
        if (sanPhamOptional.isEmpty()){
            System.out.println("Sản phẩm chưa tồn tại. Vui lòng kiểm tra lại!");
            return null;
        }

        chiTietHoaDon.setThanhTien(chiTietHoaDon.getSoLuong() * sanPhamOptional.get().getGiaThanh());
        hoaDonOptional.get().setTongTien(hoaDonOptional.get().getTongTien() + chiTietHoaDon.getThanhTien());

        chiTietHoaDon.setHoaDon(hoaDonOptional.get());
        chiTietHoaDon.setSanPham(sanPhamOptional.get());
        hoaDonRepo.save(hoaDonOptional.get());
        return chiTietHoaDonRepo.save(chiTietHoaDon);
    }

    @Override
    public ChiTietHoaDon suaChiTietHoaDon(ChiTietHoaDon remakeChiTietHoaDon) {
        Optional<ChiTietHoaDon> chiTietHoaDonOptional = chiTietHoaDonRepo.findById(remakeChiTietHoaDon.getChiTietHoaDonId());
        if (chiTietHoaDonOptional.isEmpty()) return null;

        Optional<HoaDon> hoaDonOptional = hoaDonRepo.findById(remakeChiTietHoaDon.getHoaDonId());
        if (hoaDonOptional.isEmpty()) return null;

        Optional<SanPham> sanPhamOptional = sanPhamRepo.findById(remakeChiTietHoaDon.getSanPhamId());
        if (sanPhamOptional.isEmpty()){
            System.out.println("Sản phẩm chưa tồn tại. Vui lòng kiểm tra lại!");
            return null;
        }

        remakeChiTietHoaDon.setThanhTien(remakeChiTietHoaDon.getSoLuong() * sanPhamOptional.get().getGiaThanh());
        hoaDonOptional.get().setTongTien(hoaDonOptional.get().getTongTien() + remakeChiTietHoaDon.getThanhTien());

        remakeChiTietHoaDon.setHoaDon(hoaDonOptional.get());
        remakeChiTietHoaDon.setSanPham(sanPhamOptional.get());
        hoaDonRepo.save(hoaDonOptional.get());
        return chiTietHoaDonRepo.save(remakeChiTietHoaDon);
    }

    @Override
    public HoaDon xoaHoaDon(Integer hoaDonId) {
        Optional<HoaDon> hoaDonOptional = hoaDonRepo.findById(hoaDonId);
        if (hoaDonOptional.isEmpty()) return null;

        chiTietHoaDonRepo.findAll().forEach(item->{
            if (item.getHoaDon().getHoaDonId() == hoaDonId){
                chiTietHoaDonRepo.delete(item);
            }
        });

        hoaDonRepo.delete(hoaDonOptional.get());
        return hoaDonOptional.get();
    }

    @Override
    public List<HoaDon> layHoaDon() {
        return hoaDonRepo.findHoaDonByThoiGianTao();
    }

    @Override
    public List<HoaDon> layHoaDonTheoNamThang(Integer nam, Integer thang, Pageable pageable) {
        return hoaDonRepo.findHoaDonByThoiGianTao_Year(nam,thang,pageable);
    }

    @Override
    public List<HoaDon> layHoaDonTheoKhoangThoiGian(LocalDate firstDate, LocalDate lastDate, Pageable pageable) {
        return hoaDonRepo.findHoaDonByThoiGianTao_DayOfYear(firstDate,lastDate,pageable);
    }

    @Override
    public List<HoaDon> layHoaDonTheoKhoangTongTien(Double minMoney, Double maxMoney, Pageable pageable) {
        return hoaDonRepo.findHoaDonByTongTien(minMoney,maxMoney,pageable);
    }

    @Override
    public List<HoaDon> timKiemHoaDonTheoMaGiaoDichHoacTenHoaDon(String maGiaoDich, String tenHoaDon, Pageable pageable) {
        return hoaDonRepo.findHoaDonByMaGiaoDichAndTenHoaDon(maGiaoDich,tenHoaDon,pageable);
    }

    @Override
    public Page<HoaDon> findAll(Pageable pageable){
        return hoaDonRepo.findAll(pageable);
    }
}
