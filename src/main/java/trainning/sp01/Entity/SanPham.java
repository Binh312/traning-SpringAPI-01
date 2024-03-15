package trainning.sp01.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "sanpham")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SanPham {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "sanphamid")
    private Integer sanPhamId;

    @Column(name = "loaisanphamid",insertable = false,updatable = false)
    private Integer loaiSanPhamId;

    @Column(name = "tensanpham")
    private String tenSanPham;

    @Column(name = "giathanh")
    private Double giaThanh;

    @Column(name = "mota")
    private String moTa;

    @Column(name = "ngayhethan")
    private LocalDate ngayHetHan;

    @Column(name = "kyhieusanpham")
    private String kyHieuSanPham;

    @ManyToOne
    @JoinColumn(name = "loaisanphamid")
    @JsonIgnore
    private LoaiSanPham loaiSanPham;

    @OneToMany(mappedBy = "sanPham")
    @JsonIgnore
    private List<ChiTietHoaDon> chiTietHoaDonList;
}
