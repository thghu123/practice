package sample.userservice.service;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.ModelMap;
import sample.userservice.dto.UserDto;
import sample.userservice.jpa.UserEntity;
import sample.userservice.jpa.UserRepository;

import java.util.ArrayList;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService{

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserEntity userEntity = userRepository.findByEmail(username);

        if (userEntity == null) throw new UsernameNotFoundException(username);

        //UserDetail > User 생성자의 인자 확인
        return new User(userEntity.getEmail(), userEntity.getEncryptedPwd(),
                true,true,true,true,
                new ArrayList<>());
    }

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

    @Override
    @Transactional
    public UserDto getUserDetailsByEmail(String email) {
        UserEntity userEntity = userRepository.findByEmail(email);

        if (userEntity == null) {
            throw new UsernameNotFoundException(email);
        }

        UserDto userDto = new ModelMapper().map(userEntity, UserDto.class);
        return userDto;
    }
}
