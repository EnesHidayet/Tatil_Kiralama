package org.enes.controller;

import org.enes.dto.request.*;
import org.enes.dto.response.GetCommentResponseDto;
import org.enes.entity.Hotel;
import org.enes.entity.Payment;
import org.enes.entity.Reservation;
import org.enes.service.HotelService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/hotel")
public class HotelController {

    private final HotelService hotelService;

    @PostMapping(value = "/save")
    public ResponseEntity<Boolean> save(HotelCreateRequestDto dto){
        return ResponseEntity.ok(hotelService.create(dto));
    }

    @PostMapping(value = "/addAddress")
    public ResponseEntity<Boolean> addAddress(HotelAddressCreateRequestDto dto){
        return ResponseEntity.ok(hotelService.addAddressToHotel(dto));
    }

    @PostMapping(value = "/addFacility")
    public ResponseEntity<Boolean> addFacility(HotelFecilityRequestDto dto){
        return ResponseEntity.ok(hotelService.addFacilityFeatures(dto));
    }

    @PostMapping(value = "/addImage")
    public ResponseEntity<Boolean> addImage(ImageHotelRequestDto dto){
        return ResponseEntity.ok(hotelService.addImageToHotel(dto));
    }

    @PostMapping(value = "/addRoom")
    public ResponseEntity<Boolean> addRoom(HotelRoomCreateRequestDto dto){
        return ResponseEntity.ok(hotelService.addRoom(dto));
    }

    @PostMapping("/add-ımage-room")
    public ResponseEntity<Boolean> addImageForHotelRoom(ImageRoomRequestDto dto){
        return ResponseEntity.ok(hotelService.addImageForHotelRoom(dto));
    }

    @PostMapping("/add-category")
    public ResponseEntity<Boolean> addCategory(CategoryRequestDto dto){
        return ResponseEntity.ok(hotelService.addCategory(dto));
    }

    @PostMapping("/add-hotel-category")
    public ResponseEntity<Boolean> addHotelCategory(HotelCategoryRequestDto dto){
        return ResponseEntity.ok(hotelService.addHotelCategory(dto));
    }

    @GetMapping("/get-by-category")
    public ResponseEntity<List<Hotel>> getByCategory(String categoryId){
        return ResponseEntity.ok(hotelService.findHotelByCategory(categoryId));
    }

    @PostMapping("/add-reservation")
    public ResponseEntity<Reservation> addReservation(ReservationRequestDto dto){
        return ResponseEntity.ok(hotelService.addReservation(dto));
    }

    @PostMapping("/add-payment")
    public ResponseEntity<Payment> addPayment(PaymentRequestDto dto){
        return ResponseEntity.ok(hotelService.addPayment(dto));
    }

    @GetMapping("/get-reservation")
    public ResponseEntity<Payment> checkPayment(String reservationId){
        return ResponseEntity.ok(hotelService.checkPayment(reservationId));
    }

    @GetMapping("/get-all")
    public ResponseEntity<List<Hotel>> getAll(){
        return ResponseEntity.ok(hotelService.findAll());
    }

    @GetMapping("/get-by-id")
    public ResponseEntity<Hotel> getById(String id){
        return ResponseEntity.ok(hotelService.findById(id));
    }

    @GetMapping("/get-hotel-comments")
    public ResponseEntity<List<GetCommentResponseDto>> getComments(String hotelId){
        return ResponseEntity.ok(hotelService.getHotelComment(hotelId));
    }

    @GetMapping("/get-hotel-by-point")
    public ResponseEntity<List<Hotel>> getHotelByPoint(){
        return ResponseEntity.ok(hotelService.sortByPoint());
    }
}
