package org.enes.mapper;

import org.enes.dto.request.*;
import org.enes.dto.response.GetCommentResponseDto;
import org.enes.entity.*;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface HotelMapper {
    HotelMapper INSTANCE = Mappers.getMapper(HotelMapper.class);

    Hotel fromRequestDtoToHotel(final HotelCreateRequestDto hotelCreateRequestDto);

    Address fromRequestDtoToAddress(final HotelAddressCreateRequestDto hotelAddressCreateRequestDto);
    FacilityFeatures fromRequestDtoToFacilityFeatures(final HotelFecilityRequestDto hotelFecilityRequestDto);
    Images fromRequestDtoToImages(final ImageHotelRequestDto imageHotelRequestDto);
    Room fromRequestDtoToRoom(HotelRoomCreateRequestDto hotelRoomCreateRequestDto);
    Category fromRequestDtoToCategory(CategoryRequestDto categoryRequestDto);

    HotelCategory fromRequestDtoToHotelCategory(HotelCategoryRequestDto hotelCategoryRequestDto);
    Reservation fromRequestDtoToReservation(ReservationRequestDto reservationRequestDto);
    Payment fromRequestDtoToPayment(PaymentRequestDto paymentRequestDto);
    Comments fromRequestDtoToComments(CommentRequestDto commentsRequestDto);
    GetCommentResponseDto fromCommentsToGetCommentResponseDto(Comments comments);
}
