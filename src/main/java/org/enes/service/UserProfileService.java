package org.enes.service;

import lombok.RequiredArgsConstructor;
import org.enes.dto.request.CommentRequestDto;
import org.enes.entity.Comments;
import org.enes.entity.Hotel;
import org.enes.entity.Reservation;
import org.enes.entity.UserProfile;
import org.enes.exception.ErrorType;
import org.enes.exception.HolidayException;
import org.enes.mapper.HotelMapper;
import org.enes.repository.CommentsRepository;
import org.enes.repository.HotelRepository;
import org.enes.repository.ReservationRepository;
import org.enes.repository.UserProfileRepository;
import org.enes.utility.JwtTokenManager;
import org.enes.utility.enums.EStatus;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserProfileService {
    private final UserProfileRepository userProfileRepository;
    private final HotelService hotelService;
    private final JwtTokenManager jwtTokenManager;
    private final ReservationRepository reservationRepository;
    private final HotelMapper hotelMapper;
    private final CommentsRepository commentsRepository;
    private final HotelRepository hotelRepository;


    public Boolean createUser(UserProfile userProfile){
        userProfileRepository.save(userProfile);
        return true;
    }

    public Boolean activation(String authId){
        Optional<UserProfile> userProfile = userProfileRepository.findByAuthId(authId);
        if(userProfile.isEmpty())
            throw new HolidayException(ErrorType.USER_NOT_FOUND);

        userProfile.get().setStatus(EStatus.ACTIVE);
        userProfileRepository.save(userProfile.get());
        return true;
    }

    public Boolean delete(String authId){
        Optional<UserProfile> userProfile = userProfileRepository.findByAuthId(authId);
        if(userProfile.isEmpty())
            throw new HolidayException(ErrorType.USER_NOT_FOUND);

        userProfile.get().setStatus(EStatus.DELETED);
        userProfileRepository.save(userProfile.get());
        return true;
    }

    public Optional<UserProfile> findById(String id){
        return userProfileRepository.findById(id);
    }

    public List<UserProfile> getAll(){
        return userProfileRepository.findAll();
    }

    //find-by-token
    public Optional<UserProfile> findByToken(String token){
        return userProfileRepository.findByAuthId(jwtTokenManager.getIdFromToken(token).get());
    }

    public boolean addHotelToFavorites(String token, String hotelId) {
        UserProfile userProfile = userProfileRepository.findByAuthId(jwtTokenManager.getIdFromToken(token).get()).get();
        userProfile.getFavoriteHotelsId().add(hotelId);
        userProfileRepository.save(userProfile);
        //hotel e bir puan gibi bir field ekleyip kullancı favorisine eklediğinde puan mı arttırsaqk favori hotelleri sıralamak için
        return true;
    }

    //favori otel listesi kullanıcın
    public List<Hotel> getFavoriteHotels(String token) {
        UserProfile userProfile = userProfileRepository.findByAuthId(jwtTokenManager.getIdFromToken(token).get()).get();
        List<String> favoriteHotelIds = userProfile.getFavoriteHotelsId();

        List<Hotel> favoriteHotels = new ArrayList<>();
        for (String hotelId : favoriteHotelIds) {
            favoriteHotels.add(hotelService.findById(hotelId));
        }
        return favoriteHotels;
    }

    //update passwprd email, phone, passport bilgileri

    public Boolean updateEmail(String token, String email) {
        UserProfile userProfile = userProfileRepository.findByAuthId(jwtTokenManager.getIdFromToken(token).get()).get();
        userProfile.setEmail(email);
        userProfileRepository.save(userProfile);
        return true;
    }

    public Boolean updatePhone(String token, String phone) {
        UserProfile userProfile = userProfileRepository.findByAuthId(jwtTokenManager.getIdFromToken(token).get()).get();
        userProfile.setPhone(phone);
        userProfileRepository.save(userProfile);
        return true;
    }

    public Boolean updatePassport(String token, String passportNo, String passportExpiry) {
        UserProfile userProfile = userProfileRepository.findByAuthId(jwtTokenManager.getIdFromToken(token).get()).get();
        userProfile.setPassportNo(passportNo);
        userProfile.setPassportExpiry(passportExpiry);
        userProfileRepository.save(userProfile);
        return true;
    }

    public List<Reservation> checkUrReservation(String token){
        List<Reservation> reservationList = new ArrayList<>();
        UserProfile userProfile = userProfileRepository.findByAuthId(jwtTokenManager.getIdFromToken(token).get()).get();
        userProfile.getReservationListId().stream().forEach(x->{
            reservationRepository.findById(x).ifPresent(reservationList::add);
        });
        return reservationList;
    }

    public Boolean doComment(CommentRequestDto commentRequestDto) {
        String authId = jwtTokenManager.getIdFromToken(commentRequestDto.getToken()).get();
        UserProfile userProfile = userProfileRepository.findByAuthId(authId).get();
        Comments comments = hotelMapper.fromRequestDtoToComments(commentRequestDto);
        userProfile.getCommentId().add(comments.getId());
        comments.setUserId(userProfile.getId());
        Double hotelPoint=0.0;
        List<Comments> commentsList = commentsRepository.findByHotelId(comments.getHotelId());
        for (Comments comment : commentsList) {
            hotelPoint += comment.getPoint();
        }
        Hotel hotel = hotelRepository.findById(commentRequestDto.getHotelId()).get();
        hotel.setPoint(hotelPoint/commentsList.size());
        hotelRepository.save(hotel);
        userProfileRepository.save(userProfile);
        commentsRepository.save(comments);
        return true;

    }
}
