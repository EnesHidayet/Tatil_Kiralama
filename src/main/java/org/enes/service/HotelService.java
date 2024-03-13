package org.enes.service;

import lombok.RequiredArgsConstructor;
import org.enes.dto.request.*;
import org.enes.dto.response.GetCommentResponseDto;
import org.enes.entity.*;
import org.enes.exception.ErrorType;
import org.enes.exception.HolidayException;
import org.enes.mapper.HotelMapper;
import org.enes.repository.*;
import org.enes.utility.JwtTokenManager;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class HotelService {
    private final HotelRepository hotelRepository;
    private final HotelMapper hotelMapper;
    private final AddressRepository addressRepository;
    private final FacilityFeaturesRepository facilityFeaturesRepository;
    private final ImagesRepository imagesRepository;
    private final RoomRepository roomRepository;
    private final CategoryRepository categoryRepository;
    private final HotelCategoryRepository hotelCategoryRepository;
    private final ReservationRepository reservationRepository;
    private final PaymentRepository paymentRepository;
    private final JwtTokenManager tokenManager;
    private final UserProfileRepository userProfileRepository;
    private final CommentsRepository commentsRepository;

    public Boolean create(HotelCreateRequestDto hotel) {
        hotelRepository.save(hotelMapper.fromRequestDtoToHotel(hotel));
        return true;
    }

    public Boolean addAddressToHotel(HotelAddressCreateRequestDto dto){
        Address address = addressRepository.save(hotelMapper.fromRequestDtoToAddress(dto));
        Optional<Hotel> hotel = hotelRepository.findById(dto.getHotelId());
        if (hotel.isEmpty())
            throw new HolidayException(ErrorType.HOTEL_NOT_FOUND);

        hotel.get().setAddressId(address.getId());
        hotelRepository.save(hotel.get());
        return true;
    }

    public Boolean addFacilityFeatures(HotelFecilityRequestDto dto){
        Optional<Hotel> hotel = hotelRepository.findById(dto.getHotelId());
        if (hotel.isEmpty())
            throw new HolidayException(ErrorType.HOTEL_NOT_FOUND);

        facilityFeaturesRepository.save(hotelMapper.fromRequestDtoToFacilityFeatures(dto));
        return true;
    }

    public Boolean addImageToHotel(ImageHotelRequestDto dto){
        Optional<Hotel> hotel = hotelRepository.findById(dto.getHotelId());
        if (hotel.isEmpty())
            throw new HolidayException(ErrorType.HOTEL_NOT_FOUND);
        Images images = imagesRepository.save(hotelMapper.fromRequestDtoToImages(dto));
        hotel.get().getImageIdList().add(images.getId());
        hotelRepository.save(hotel.get());
        return true;
    }

    public Boolean addRoom(HotelRoomCreateRequestDto dto){
        Optional<Hotel> hotel = hotelRepository.findById(dto.getHotelId());
        if (hotel.isEmpty())
            throw new HolidayException(ErrorType.HOTEL_NOT_FOUND);
        roomRepository.save(hotelMapper.fromRequestDtoToRoom(dto));
        return true;
    }

    public Boolean addImageForHotelRoom(ImageRoomRequestDto dto){
        Optional<Room> room = roomRepository.findById(dto.getRoomId());
        if (room.isEmpty())
            throw new HolidayException(ErrorType.ROOM_NOT_FOUND);
        room.get().getImageId().add(dto.getImageUrl());
        roomRepository.save(room.get());
        return true;
    }

    public Boolean addCategory(CategoryRequestDto dto){
        categoryRepository.save(hotelMapper.fromRequestDtoToCategory(dto));
        return true;
    }

    public Boolean addHotelCategory(HotelCategoryRequestDto dto){
        hotelCategoryRepository.save(hotelMapper.fromRequestDtoToHotelCategory(dto));
        return true;
    }

    public List<Hotel> findHotelByCategory(String categoryId){
        List<Category> categories = categoryRepository.findByParentId(categoryId);
        categories.add(categoryRepository.findById(categoryId).get());
        List<Hotel> hotels = new ArrayList<>();
        for (Category category : categories) {
            List<HotelCategory> hotelCategories = hotelCategoryRepository.findByCategoryId(category.getId());
            for (HotelCategory hotelCategory : hotelCategories) {
                hotels.add(hotelRepository.findById(hotelCategory.getHotelId()).get());
            }
        }
        return hotels;
    }

    public Reservation addReservation(ReservationRequestDto dto){
        String authId=tokenManager.getIdFromToken(dto.getToken()).get();
        Optional<UserProfile> userProfile = userProfileRepository.findByAuthId(authId);
        if (userProfile.isEmpty())
            throw new HolidayException(ErrorType.USER_NOT_FOUND);

        Optional<Room> room = roomRepository.findById(dto.getRoomId());
        if (room.isEmpty())
            throw new HolidayException(ErrorType.ROOM_NOT_FOUND);
        Reservation reservation = hotelMapper.fromRequestDtoToReservation(dto);
        reservation.setAuthId(authId);
        reservation.setTotalPrice(room.get().getPrice()*reservation.getNumberOfPeople());
        Reservation reservation1 = reservationRepository.save(reservation);
        userProfile.get().getReservationListId().add(reservation1.getId());
        userProfileRepository.save(userProfile.get());
        return reservation1;
    }

    public Payment addPayment(PaymentRequestDto dto){
        Payment payment = hotelMapper.fromRequestDtoToPayment(dto);
        if (payment.getCouponCode().equals("Enes"))
            payment.setPaymentAmount(payment.getPaymentAmount()*0.9);
        return paymentRepository.save(payment);
    }

    public Payment checkPayment(String reservationId){
        return paymentRepository.findById(reservationId).get();
    }

    public List<GetCommentResponseDto> getHotelComment(String hotelId){
        List<Comments> comments = commentsRepository.findByHotelId(hotelId);
        List<GetCommentResponseDto> getCommentResponseDtos = new ArrayList<>();
        for (Comments comment : comments) {
            getCommentResponseDtos.add(hotelMapper.fromCommentsToGetCommentResponseDto(comment));
        }
        return getCommentResponseDtos;
    }

    public Hotel findById(String id){
        Optional<Hotel> hotel = hotelRepository.findById(id);
        if (hotel.isEmpty())
            throw new HolidayException(ErrorType.HOTEL_NOT_FOUND);
        return hotel.get();
    }

    public List<Hotel> findAll(){
        return hotelRepository.findAll();
    }

    public List<Hotel> sortByPoint(){
        return hotelRepository.findAllByOrderByPointDesc();
    }

}
