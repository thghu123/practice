package sample.userservice.service;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.ModelMap;
import sample.userservice.dto.UserDto;
import sample.userservice.jpa.UserEntity;
import sample.userservice.jpa.UserRepository;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService{

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    @Override
    @Transactional
    public UserDto createUser(UserDto userDto) {
        //random UUID 생성
        userDto.setUserId(UUID.randomUUID().toString());

        //DB 저장을 위해 요청을 User Entity로 변환 필요

        ModelMapper mapper = new ModelMapper();
        //매칭 전략을 강경하게, 딱 맞아 떨어지지 않으면 불가
        mapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);

        //mapping
        UserEntity userEntity = mapper.map(userDto, UserEntity.class);

        //암호화
        userEntity.setEncryptedPwd(passwordEncoder.encode(userDto.getPwd()));

        userRepository.save(userEntity);

        UserDto returnUserDto = mapper.map(userEntity, UserDto.class); //잘 변환됐는지 확인

        return returnUserDto;
    }
}
