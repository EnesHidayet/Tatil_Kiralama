package org.enes.service;

import lombok.RequiredArgsConstructor;
import org.enes.entity.UserProfile;
import org.enes.exception.ErrorType;
import org.enes.exception.HolidayException;
import org.enes.repository.UserProfileRepository;
import org.enes.utility.enums.EStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserProfileService {
    private final UserProfileRepository userProfileRepository;

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
}
