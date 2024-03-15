package trainning.sp01.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import trainning.sp01.Entity.SanPham;

@Repository
public interface SanPhamRepository extends JpaRepository<SanPham, Integer> {
}
