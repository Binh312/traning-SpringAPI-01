package trainning.sp01.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import trainning.sp01.Entity.ChiTietHoaDon;
import trainning.sp01.Entity.HoaDon;
import trainning.sp01.Response.HoaDonResponse;
import trainning.sp01.Service.Implement.HoaDonService;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/api/hoadon")
public class HoaDonController {
    @Autowired
    private HoaDonService hoaDonService;

    @PostMapping(value = "/themhoadon")
    public HoaDon themHoaDon(@RequestBody HoaDon addHoaDon){
        HoaDon hoaDon = hoaDonService.themHoaDon(addHoaDon);
        return hoaDon;
    }

    @PostMapping(value = "/themchitiethoadon")
    public ChiTietHoaDon themChiTietHoaDon(@RequestBody ChiTietHoaDon addDhiTietHoaDon){
        ChiTietHoaDon chiTietHoaDon = hoaDonService.themChiTietHoaDon(addDhiTietHoaDon);
        return chiTietHoaDon;
    }

    @PutMapping(value = "/suachitiethoadon")
    public ChiTietHoaDon suaChiTietHoaDon(@RequestBody ChiTietHoaDon remakeChiTietHoaDon){
        ChiTietHoaDon chiTietHoaDon = hoaDonService.suaChiTietHoaDon(remakeChiTietHoaDon);
        return chiTietHoaDon;
    }

    @DeleteMapping(value = "/xoahoadon/{hoaDonId}")
    public HoaDon xoaHoaDon(@PathVariable Integer hoaDonId){
        HoaDon hoaDon = hoaDonService.xoaHoaDon(hoaDonId);
        return hoaDon;
    }

    @GetMapping(value = "/layhoadon")
    public List<HoaDonResponse> layHoaDon(){
        List<HoaDon> hoaDonList = hoaDonService.layHoaDon();
        List<HoaDonResponse> hoaDonResponseList = hoaDonList.stream().map(
                item ->HoaDonResponse.mapHoaDonToHoaDonResponse(item)
        ).collect(Collectors.toList());
        return hoaDonResponseList;
    }

    @GetMapping(value = "/layhoadontheonamthang")
    public List<HoaDonResponse> layHoaDonTheoNamThang(@RequestParam(value = "nam", required = false) Integer nam,
                                                      @RequestParam(value = "thang", required = false) Integer thang,
                                                      Pageable pageable){
        List<HoaDon> hoaDonList = hoaDonService.layHoaDonTheoNamThang(nam,thang,pageable);
        List<HoaDonResponse> hoaDonResponseList = hoaDonList.stream().map(
                item -> HoaDonResponse.mapHoaDonToHoaDonResponse(item)
        ).collect(Collectors.toList());
        return hoaDonResponseList;
    }

    @GetMapping(value = "/layhoadontheokhoangthoigian")
    public List<HoaDonResponse> layHoaDonTheoKhoangThoiGian(@RequestParam(value = "firstDate", required = false) LocalDate firstDate,
                                                            @RequestParam(value = "lastDate", required = false) LocalDate lastDate,
                                                            Pageable pageable){
        List<HoaDon> hoaDonList = hoaDonService.layHoaDonTheoKhoangThoiGian(firstDate,lastDate,pageable);
        List<HoaDonResponse> hoaDonResponseList = hoaDonList.stream().map(
                item -> HoaDonResponse.mapHoaDonToHoaDonResponse(item)
        ).collect(Collectors.toList());
        return hoaDonResponseList;
    }

    @GetMapping(value = "/layhoadontheotongtien")
    public List<HoaDonResponse> layHoaDonTheoTongTien(@RequestParam(value = "minMoney", required = false) Double minMoney,
                                                      @RequestParam(value = "maxMoney", required = false) Double maxMoney,
                                                      Pageable pageable){
        List<HoaDon> hoaDonList = hoaDonService.layHoaDonTheoKhoangTongTien(minMoney,maxMoney,pageable);
        List<HoaDonResponse> hoaDonResponseList = hoaDonList.stream().map(
                item -> HoaDonResponse.mapHoaDonToHoaDonResponse(item)
        ).collect(Collectors.toList());
        return hoaDonResponseList;
    }

    @GetMapping(value = "/layhoadontheomagiaodichhoactenhoadon")
    public List<HoaDonResponse> timKiemHoaDon(@RequestParam(value = "maGiaoDich", required = false) String maGiaoDich,
                                              @RequestParam(value = "tenHoaDon", required = false) String tenHoaDon,
                                              Pageable pageable){
        List<HoaDon> hoaDonList = hoaDonService.timKiemHoaDonTheoMaGiaoDichHoacTenHoaDon(maGiaoDich,tenHoaDon,pageable);
        List<HoaDonResponse> hoaDonResponseList = hoaDonList.stream().map(
                item -> HoaDonResponse.mapHoaDonToHoaDonResponse(item)
        ).collect(Collectors.toList());
        return hoaDonResponseList;
    }

    @GetMapping(value = "/findAll")
    public ResponseEntity<?> findAll(Pageable pageable){
        Page<HoaDon> hoaDons = hoaDonService.findAll(pageable);
        return ResponseEntity.ok(hoaDonService.findAll(pageable));
    }
}
