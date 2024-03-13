package org.enes.controller;

import org.enes.dto.request.CommentRequestDto;
import org.enes.entity.Hotel;
import org.enes.entity.Reservation;
import org.enes.entity.UserProfile;
import org.enes.service.UserProfileService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

    private final UserProfileService userService;

    @GetMapping("/find-by-token")
    public ResponseEntity<Optional<UserProfile>> findByToken(String token){
        return ResponseEntity.ok(userService.findByToken(token));
    }

    //add hotel to users favorite list
    @GetMapping("/add-favorite")
    public ResponseEntity<Boolean> addFavorite(String token, String hotelId){
        return ResponseEntity.ok(userService.addHotelToFavorites(token, hotelId));
    }

    //get favorite hotels
    @GetMapping("/get-favorites")
    public ResponseEntity<List<Hotel>> getFavoriteHotels(String token){
        return ResponseEntity.ok(userService.getFavoriteHotels(token));
    }

    @PutMapping("/update-passport")
    public ResponseEntity<Boolean> updatePassport(String token, String passportNo, String passportExpiry){
        return ResponseEntity.ok(userService.updatePassport(token, passportNo, passportExpiry));
    }

    @GetMapping("/check-reservation")
    public ResponseEntity<List<Reservation>> checkReservation(String token){
        return ResponseEntity.ok(userService.checkUrReservation(token));
    }

    @PostMapping("/add-comment")
    public ResponseEntity<Boolean> addComment(CommentRequestDto dto){
        return ResponseEntity.ok(userService.doComment(dto));
    }

}
