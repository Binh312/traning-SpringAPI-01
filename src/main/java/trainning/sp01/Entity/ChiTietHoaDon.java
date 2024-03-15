package trainning.sp01.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "chitiethoadon")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ChiTietHoaDon {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "chitiethoadonid")
    private Integer chiTietHoaDonId;

    @Column(name = "hoadonid",insertable = false,updatable = false)
    private Integer hoaDonId;

    @Column(name = "sanphamid",insertable = false,updatable = false)
    private Integer sanPhamId;

    @Column(name = "soluong")
    private Integer soLuong;

    @Column(name = "donvitinh")
    private String donViTinh;

    @Column(name = "thanhtien")
    private Double thanhTien;

    @ManyToOne
    @JoinColumn(name = "sanphamid")
    @JsonIgnore
    private SanPham sanPham;

    @ManyToOne
    @JoinColumn(name = "hoadonid")
    @JsonIgnore
    private HoaDon hoaDon;
}
