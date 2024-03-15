package trainning.sp01.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import trainning.sp01.Entity.KhachHang;

@Repository
public interface KhachHangRepository extends JpaRepository<KhachHang, Integer> {
}
