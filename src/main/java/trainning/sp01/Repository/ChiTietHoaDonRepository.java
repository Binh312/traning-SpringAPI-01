package trainning.sp01.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import trainning.sp01.Entity.ChiTietHoaDon;
import trainning.sp01.Entity.HoaDon;

import java.util.List;

@Repository
public interface ChiTietHoaDonRepository extends JpaRepository<ChiTietHoaDon, Integer> {
}
