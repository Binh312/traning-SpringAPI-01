package trainning.sp01.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import trainning.sp01.Entity.LoaiSanPham;

@Repository
public interface LoaiSanPhamRepository extends JpaRepository<LoaiSanPham, Integer> {
}
