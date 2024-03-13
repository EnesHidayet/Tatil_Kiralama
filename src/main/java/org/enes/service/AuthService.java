package org.enes.service;

import lombok.RequiredArgsConstructor;
import org.enes.dto.request.AuthActivationRequestDto;
import org.enes.dto.request.AuthLoginRequestDto;
import org.enes.dto.request.AuthRegisterRequestDto;
import org.enes.entity.Auth;
import org.enes.entity.UserProfile;
import org.enes.exception.ErrorType;
import org.enes.exception.HolidayException;
import org.enes.mapper.AuthMapper;
import org.enes.repository.AuthRepository;
import org.enes.utility.JwtTokenManager;
import org.enes.utility.enums.EStatus;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final AuthRepository authRepository;
    private final AuthMapper authMapper;
    private final UserProfileService userProfileService;
    private final JwtTokenManager jwtTokenManager;
    private final MailSenderService mailService;
    private final CacheManager cacheManager;

    public String register(AuthRegisterRequestDto dto){
        Optional<Auth> authKontrol = authRepository.findByEmail(dto.getEmail());
        if (authKontrol.isPresent())
            throw new HolidayException(ErrorType.ALREADY_REGISTERED);

        Auth auth = authRepository.save(authMapper.fromRequestDtoToAuth(dto));

        mailService.sendMail(auth.getActivationCode());

        userProfileService.createUser(UserProfile.builder()
                        .authId(auth.getId())
                        .email(auth.getEmail())
                        .phone(auth.getPhone())
                .build());
        Objects.requireNonNull(cacheManager.getCache("find-all-auth")).clear();

        return jwtTokenManager.createToken(auth.getId()).get();
    }

    public Boolean activation(AuthActivationRequestDto dto){
        Optional<Auth> auth = authRepository.findByIdAndActivationCode(jwtTokenManager.getIdFromToken(dto.getToken()).get(),dto.getActivationCode());
        if(auth.isEmpty())
            throw new HolidayException(ErrorType.USER_NOT_FOUND);

        if (auth.get().getActivationCode().equals(dto.getActivationCode())) {
            auth.get().setStatus(EStatus.ACTIVE);
            authRepository.save(auth.get());
            userProfileService.activation(auth.get().getId());
            return true;
        }
        return false;
    }

    public String login(AuthLoginRequestDto dto){
        Optional<Auth> auth = authRepository.findByEmailAndPassword(dto.getEmail(),dto.getPassword());
        if(auth.isEmpty())
            throw new HolidayException(ErrorType.LOGIN_FAILED);
        if (!auth.get().getStatus().equals(EStatus.ACTIVE))
            throw new HolidayException(ErrorType.ACCOUNT_NOT_ACTIVATED);
        return jwtTokenManager.createToken(auth.get().getId()).get();
    }

    public Boolean delete(String token){
        Optional<Auth> auth = authRepository.findById(jwtTokenManager.getIdFromToken(token).get());
        if(auth.isEmpty())
            throw new HolidayException(ErrorType.USER_NOT_FOUND);

        auth.get().setStatus(EStatus.DELETED);
        authRepository.save(auth.get());
        userProfileService.delete(auth.get().getId());
        return true;
    }

    public Boolean updateEmail(String token, String email){
        Optional<Auth> auth = authRepository.findById(jwtTokenManager.getIdFromToken(token).get());
        if(auth.isEmpty())
            throw new HolidayException(ErrorType.USER_NOT_FOUND);

        auth.get().setEmail(email);
        authRepository.save(auth.get());
        userProfileService.updateEmail(token, email);
        return true;
    }

    public Boolean updatePhone(String token, String phone) {
        Optional<Auth> auth = authRepository.findById(jwtTokenManager.getIdFromToken(token).get());
        if (auth.isEmpty())
            throw new HolidayException(ErrorType.USER_NOT_FOUND);

        auth.get().setPhone(phone);
        authRepository.save(auth.get());
        userProfileService.updatePhone(token, phone);
        return true;
    }

    public Optional<Auth> findById(String id){
        return authRepository.findById(id);
    }

    @Cacheable("find-all-auth")
    public List<Auth> getAll(){
        return authRepository.findAll();
    }
}
