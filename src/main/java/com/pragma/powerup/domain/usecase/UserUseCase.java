package com.pragma.powerup.domain.usecase;

import com.pragma.powerup.domain.api.IUserServicePort;
import com.pragma.powerup.domain.exception.EmailFormatException;
import com.pragma.powerup.domain.exception.EmptyValueException;
import com.pragma.powerup.domain.exception.LengthValueException;
import com.pragma.powerup.domain.exception.NumberPhoneFormatException;
import com.pragma.powerup.domain.model.UserModel;
import com.pragma.powerup.domain.spi.IUserPersistencePort;

import java.util.regex.Pattern;

public class UserUseCase implements IUserServicePort {
    private final IUserPersistencePort userPersistencePort;

    public UserUseCase(IUserPersistencePort userPersistencePort) {
        this.userPersistencePort = userPersistencePort;
    }

    @Override
    public UserModel saveUser(UserModel userModel) {
        validateStringEmpty(userModel.getName(), "name");
        validateStringEmpty(userModel.getLastName(), "lastName");
        validateStringEmpty(userModel.getPhone(), "phone");
        validateStringEmpty(userModel.getEmail(), "email");
        validateStringEmpty(userModel.getPassword(), "password");
        validateEmail(userModel.getEmail());
        validatePhone(userModel.getPhone());
        return userPersistencePort.saveUser(userModel);
    }

    @Override
    public UserModel getUser(Long id) {
       return userPersistencePort.getUser(id);
    }

    @Override
    public void validateRolUser(Long id) {
        userPersistencePort.validateRolUser(id);
    }

    @Override
    public UserModel validateOwner(Long id, String owner) {
        return userPersistencePort.validateOwner(id, owner);
    }

    @Override
    public Long getIdUser(String userName) {
        return userPersistencePort.getIdUser(userName);
    }

    private void validateEmail(String email) {
        String regex = "^[\\w!#$%&amp;'*+/=?`{|}~^-]+(?:\\.[\\w!#$%&amp;'*+/=?`{|}~^-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}$";
        Pattern pattern = Pattern.compile(regex);
        if (!pattern.matcher(email).matches()) {
            throw new EmailFormatException("Email "+email);
        }
    }

    private void validatePhone(String phone) {
        final int INIT_LENGTH_NUMBER = 10;
        final int END_LENGTH_NUMBER = 13;
        final String MSG_EXCEPTION = "phone " + phone;
        if ((phone.length() < INIT_LENGTH_NUMBER || phone.length() > END_LENGTH_NUMBER) ||
                (phone.length() > INIT_LENGTH_NUMBER && phone.length() < END_LENGTH_NUMBER)) {
            throw new LengthValueException(MSG_EXCEPTION);
        }

        String regexSigne = "^\\+(\\d{12})$";
        String regexNumber = "^\\d{10}$";

        if ((phone.length() == INIT_LENGTH_NUMBER && !phone.matches(regexNumber))
                || (phone.length() == END_LENGTH_NUMBER && !phone.matches(regexSigne))) {

            throw new NumberPhoneFormatException(MSG_EXCEPTION);
        }
    }

    private void validateStringEmpty(String arg, String nameArg){
        if(arg == null){
            throw new NullPointerException(nameArg);
        }
        if(arg.isEmpty()){
            throw new EmptyValueException(nameArg);
        }
    }


}
