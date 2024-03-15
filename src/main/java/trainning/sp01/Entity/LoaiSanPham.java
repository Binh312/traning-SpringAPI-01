package trainning.sp01.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "loaisanpham")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class LoaiSanPham {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "loaisanphamid")
    private Integer loaiSanPhamId;

    @Column(name = "tenloaisanpham")
    private String tenLoaiSanPham;

    @OneToMany(mappedBy = "loaiSanPham")
    @JsonIgnore
    private List<SanPham> sanPhamList;
}
