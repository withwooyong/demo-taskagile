package com.demo.demotaskagile.domain.model.user;

import com.demo.demotaskagile.domain.common.security.PasswordEncryptor;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InOrder;
import org.springframework.boot.test.context.SpringBootTest;

import static org.mockito.Mockito.*;

@SpringBootTest
public class RegistrationManagementTests {

    private UserRepository repositoryMock;
    private PasswordEncryptor passwordEncryptorMock;
    private RegistrationManagement instance;

    @BeforeEach
    public void setUp() {
        repositoryMock = mock(UserRepository.class);
        passwordEncryptorMock = mock(PasswordEncryptor.class);
        instance = new RegistrationManagement(repositoryMock, passwordEncryptorMock);
    }

    //    @Test(expected = UsernameExistsException.class)
    @Test
    public void register_existedUsername_shouldFail() throws RegistrationException {
        String username = "existUsername";
        String emailAddress = "sunny@taskagile.com";
        String password = "MyPassword!";
        String firstName = "Existing";
        String lastName = "User";
        // We just return an empty user object to indicate an existing user
        when(repositoryMock.findByUsername(username)).thenReturn(new User());
        Assertions.assertThrows(UsernameExistsException.class, () -> {
            instance.register(username, emailAddress, firstName, lastName, password);
        });
    }

    //    @Test(expected = EmailAddressExistsException.class)
    @Test
    public void register_existedEmailAddress_shouldFail() throws RegistrationException {
        String username = "sunny";
        String emailAddress = "exist@taskagile.com";
        String password = "MyPassword!";
        String firstName = "Sunny";
        String lastName = "Hu";
        // We just return an empty user object to indicate an existing user
        when(repositoryMock.findByEmailAddress(emailAddress)).thenReturn(new User());
        Assertions.assertThrows(EmailAddressExistsException.class, () -> {
            instance.register(username, emailAddress, firstName, lastName, password);
        });
    }

    @Test
    public void register_uppercaseEmailAddress_shouldSucceedAndBecomeLowercase() throws RegistrationException {
        String username = "sunny";
        String emailAddress = "Sunny@TaskAgile.com";
        String password = "MyPassword!";
        String firstName = "Sunny";
        String lastName = "Hu";
        instance.register(username, emailAddress, firstName, lastName, password);
        User userToSave = User.create(username, emailAddress.toLowerCase(), firstName, lastName, password);
        verify(repositoryMock).save(userToSave);
    }

    @Test
    public void register_newUser_shouldSucceed() throws RegistrationException {
        String username = "sunny";
        String emailAddress = "sunny@taskagile.com";
        String password = "MyPassword!";
        String encryptedPassword = "EncryptedPassword";
        String firstName = "Sunny";
        String lastName = "Hu";
        User newUser = User.create(username, emailAddress, firstName, lastName, encryptedPassword);

        // Setup repository mock
        // Return null to indicate no user exists
        when(repositoryMock.findByUsername(username)).thenReturn(null);
        when(repositoryMock.findByEmailAddress(emailAddress)).thenReturn(null);
        doNothing().when(repositoryMock).save(newUser);
        // Setup passwordEncryptor mock
        when(passwordEncryptorMock.encrypt(password)).thenReturn("EncryptedPassword");

        User savedUser = instance.register(username, emailAddress, firstName, lastName, password);
        InOrder inOrder = inOrder(repositoryMock);
        inOrder.verify(repositoryMock).findByUsername(username);
        inOrder.verify(repositoryMock).findByEmailAddress(emailAddress);
        inOrder.verify(repositoryMock).save(newUser);
        verify(passwordEncryptorMock).encrypt(password);
        Assertions.assertEquals("Saved user's password should be encrypted", encryptedPassword, savedUser.getPassword());
    }
}
