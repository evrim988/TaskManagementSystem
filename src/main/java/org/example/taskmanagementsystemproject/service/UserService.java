package org.example.taskmanagementsystemproject.service;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.taskmanagementsystemproject.dto.request.user.LoginRequestDto;
import org.example.taskmanagementsystemproject.dto.request.user.RegisterRequestDto;
import org.example.taskmanagementsystemproject.entity.User;
import static org.example.taskmanagementsystemproject.mapper.UserMapper.*;

import org.example.taskmanagementsystemproject.exception.ErrorType;
import org.example.taskmanagementsystemproject.exception.TaskManagementSystemException;
import org.example.taskmanagementsystemproject.repository.UserRepository;
import org.example.taskmanagementsystemproject.utility.JwtManager;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final JwtManager jwtManager;

    /**
     * Kullanıcı kaydetme metodu
     *
     *  swagger üzerinden istek atılırken ordaki ifade string olarak kaldığı için
     *  execute dediğimizde o veriyi direk veritabanına kaydediyor. Bu yüzden böyle bir exception kullandım.
     *
     * @param registerRequestDto
     * @return User
     */
    public User register(@Valid RegisterRequestDto registerRequestDto) {
        if (registerRequestDto.getName().equalsIgnoreCase("string") && registerRequestDto.getSurname().equalsIgnoreCase("string")) {
            throw new TaskManagementSystemException(ErrorType.USER_NOTSTRING);
        }
        User user = INSTANCE.fromRegisterRequestDto(registerRequestDto);
        return userRepository.save(user);
    }

    /**
     * Kullanıcı adı ve şifre ile giriş yapıldıktan sonra,
     * girilen bilgiler doğru ise bize bir assignedByUserId üretiyor ve geri dönüyor.
     * @param loginRequestDto
     * @return String
     */
    public String login(@Valid LoginRequestDto loginRequestDto) {
        Optional<User> optionalUser = userRepository.findOptionalByUsernameAndPassword(loginRequestDto.username(), loginRequestDto.password());
        if(optionalUser.isEmpty())
            throw new TaskManagementSystemException(ErrorType.INVALID_USERNAME_OR_PASSWORD);
        String token = jwtManager.createToken(optionalUser.get().getId());
        return token;
    }

    /**
     * Bir önceki metodda oluşturduğumuz assignedByUserId ile buraya gelip parametre olarak veriyoruz.
     * Öncelikle aldığımız assignedByUserId doğrulama işlemleri yapıyoruz.
     * Sonra bu assignedByUserId a ait kullanıcı Id ile veritabanına sorgu atıyoruz, böyle bir kullanıcı var mı diye...
     * eğer var ise bu kullanıcı bilgilerini geri dönüyoruz.
     * yok ise kullanıcıya exception fırlatıp hata konusunda bilgilendiriyoruz. (Kullanıcı bulunamadı)
     * @param token
     * @return User
     */
    public User getMyProfile(String token) {
        Optional<Long> optionalUserId = jwtManager.validateToken(token);
        if(optionalUserId.isEmpty())
            throw new TaskManagementSystemException(ErrorType.INVALID_TOKEN);

        Optional<User> optionalUser = userRepository.findById(optionalUserId.get());
        if(optionalUser.isEmpty())
            throw new TaskManagementSystemException(ErrorType.USER_NOT_FOUND);
        return optionalUser.get();
    }


    /**
     * Tüm kullanıcıları listeler.
     * @return List<User>
     */
    public List<User> findAllUsers() {
        return userRepository.findAll();
    }

    /**
     * Kullanıcı adına göre user döner.
     * @param username
     * @return Optional<User>
     */
    public User findByUsername(String username) {
        Optional<User> optionalByUsername = userRepository.findOptionalByUsername(username);
        if(optionalByUsername.isPresent()){
            return optionalByUsername.get();
        }
        else {
            throw new TaskManagementSystemException(ErrorType.USER_NOT_FOUND);
        }
    }

}
