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
@Table(name = "hoadon", uniqueConstraints = {@UniqueConstraint(columnNames = "magiaodich")})
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class HoaDon {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "hoadonid")
    private Integer hoaDonId;

    @Column(name = "khachhangid",insertable = false,updatable = false)
    private Integer khachHangId;

    @Column(name = "tenhoadon")
    private String tenHoaDon;

    @Column(name = "magiaodich")
    private String maGiaoDich;

    @Column(name = "thoigiantao")
    private LocalDate thoiGianTao;

    @Column(name = "thoigiancapnhat")
    private LocalDate thoiGianCapNhat;

    @Column(name = "ghichu")
    private String ghiChu;

    @Column(name = "tongtien")
    private Double tongTien;

    @ManyToOne
    @JoinColumn(name = "khachhangid")
    @JsonIgnore
    private KhachHang khachHang;

    @OneToMany(mappedBy = "hoaDon")
    @JsonIgnore
    private List<ChiTietHoaDon> chiTietHoaDonList;
}
