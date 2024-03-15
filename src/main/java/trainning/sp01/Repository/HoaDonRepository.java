package trainning.sp01.Repository;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import trainning.sp01.Entity.HoaDon;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface HoaDonRepository extends JpaRepository<HoaDon, Integer> {
    @Query("SELECT COUNT(hd.hoaDonId) FROM HoaDon hd WHERE hd.thoiGianTao = ?1 ")
    int soHoaDonTrongNgay(LocalDate ngayHienTai);

    @Query("select hd from HoaDon hd order by hd.thoiGianTao desc")
    List<HoaDon> findHoaDonByThoiGianTao();

    @Query("select hd from HoaDon hd where YEAR(hd.thoiGianTao) = ?1 and MONTH(hd.thoiGianTao) = ?2")
    List<HoaDon> findHoaDonByThoiGianTao_Year(Integer nam, Integer thang, Pageable pageable);

    @Query("select hd from HoaDon hd where hd.thoiGianTao between ?1 and ?2")
    List<HoaDon> findHoaDonByThoiGianTao_DayOfYear(LocalDate firstDate, LocalDate lastDate, Pageable pageable);

    @Query("select hd from HoaDon hd where hd.tongTien between ?1 and ?2")
    List<HoaDon> findHoaDonByTongTien(double minMoney, double maxMony, Pageable pageable);

    @Query("select hd from HoaDon hd where hd.maGiaoDich like %?1% or hd.tenHoaDon like %?2%")
    List<HoaDon> findHoaDonByMaGiaoDichAndTenHoaDon(String maGiaoDich, String tenHoaDon, Pageable pageable);
}
