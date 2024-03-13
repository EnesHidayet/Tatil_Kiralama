package org.enes.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum ErrorType {

    INTERNAL_SERVER_ERROR(5200, "Sunucu Hatasi...",HttpStatus.INTERNAL_SERVER_ERROR),
    BAD_REQUEST (4200,"Parametre hatasi...", HttpStatus.BAD_REQUEST),
    EMAIL_DUPLICATE(4211,"Bu mail adresi zaten kullanımdadır...", HttpStatus.BAD_REQUEST),
    USER_NOT_FOUND(4212,"Böyle bir kullanici bulunamadi...",HttpStatus.BAD_REQUEST),
    HOTEL_NOT_FOUND(4212,"Böyle bir otel bulunamadi...",HttpStatus.BAD_REQUEST),
    USER_NOT_CREATED(4213,"Kullanıcı oluşturulamadı..." ,HttpStatus.BAD_REQUEST ),
    INVALID_TOKEN(4214,"Geçersiz token..." ,HttpStatus.BAD_REQUEST),
    ACCOUNT_NOT_ACTIVATED(4215,"Giriş yapabilmek için önce hesabınızı aktifleştirin..." ,HttpStatus.BAD_REQUEST),
    LOGIN_FAILED(4216,"Kullanıcı adı ya da şifre hatalı..." ,HttpStatus.BAD_REQUEST),
    ALREADY_REGISTERED(4217,"Bu mail adresi ile daha önce kayıt olunmuştur...." ,HttpStatus.BAD_REQUEST ),
    CATEGORY_ALREADY_EXISTS(4218,"Bu kategori zaten mevcut...",HttpStatus.BAD_REQUEST),
    ROOM_NOT_FOUND(4219,"Böyle bir oda bulunamadı..." ,HttpStatus.BAD_REQUEST );

    private int code;
    private String message;
    private HttpStatus httpStatus;
}
