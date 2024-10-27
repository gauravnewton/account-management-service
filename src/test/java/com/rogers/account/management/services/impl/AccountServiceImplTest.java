package com.rogers.account.management.services.impl;

import com.rogers.account.management.configs.AppConfig;
import com.rogers.account.management.configs.RestTemplateConfig;
import com.rogers.account.management.configs.UniqueIdGenerator;
import com.rogers.account.management.dtos.api.AddressLookUpResponse;
import com.rogers.account.management.dtos.api.PlaceLookUpResponse;
import com.rogers.account.management.dtos.request.CreateAccountRequest;
import com.rogers.account.management.dtos.response.AccountCountResponse;
import com.rogers.account.management.dtos.response.CreateAccountResponse;
import com.rogers.account.management.dtos.response.Place;
import com.rogers.account.management.dtos.response.State;
import com.rogers.account.management.entities.Account;
import com.rogers.account.management.entities.Address;
import com.rogers.account.management.enums.AccountStatus;
import com.rogers.account.management.exceptions.types.AccountNotFoundException;
import com.rogers.account.management.repositories.AccountRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import javax.print.attribute.standard.MediaSize;
import java.util.*;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class AccountServiceImplTest {

    @Mock
    private AccountRepository mockAccountRepository;
    @Mock
    private AppConfig mockAppConfig;
    @Mock
    private RestTemplateConfig mockRestTemplateConfig;
    @Mock
    private UniqueIdGenerator mockUniqueIdGenerator;
    @Mock
    private RestTemplate restTemplate;
    @InjectMocks
    private AccountServiceImpl accountServiceImplUnderTest;
    public static final String ACCOUNT_ID = "A9K09O";
    public static final String NAME = "Gaurav Kumar";
    public static final String EMAIL = "gaurav.mute@gmail.com";
    public static final String COUNTRY = "United States";
    public static final String COUNTRY_CODE = "US";
    public static final String POSTAL_CODE = "00501";
    public static final int AGE = 20;
    public static final String STATUS_ACTIVE = "ACTIVE";
    public static final Date CREATED_DATE = new Date();
    public static final Date UPDATED_DATE = new Date();
    public static final String SECURITY_PIN = "7289";
    public static final String STATE = "New York";
    public static final String STATE_CODE = "NY";
    public static final String CITY = "Holtsville";
    public static final String LATITUDE = "40.9223";
    public static final String LONGITUDE = "-72.6371";
    public static final String BACK_SLASH = "/";
    public static final String UPDATE_POSTAL_CODE = "60661";
    public static final String UPDATE_STATE = "Illinois";
    public static final String UPDATE_STATE_CODE = "IL";
    public static final String UPDATE_LATITUDE = "41.8814";
    public static final String UPDATE_LONGITUDE = "-87.643";
    public static final String UPDATE_CITY = "Chicago";

    @Test
    void testCreateAccount() {
        final CreateAccountRequest createAccountRequest = mockCreateAccountRequest();
        final CreateAccountResponse expectedResult = new CreateAccountResponse(ACCOUNT_ID, AccountStatus.ACTIVE, SECURITY_PIN);
        final Account account = mockAccount();
        final Address address = mockAddress();
        final Account entity = mockAccount();
        final Address address1 = mockAddress();

        account.setAddresses(List.of(address));
        account.setCreatedAt(CREATED_DATE);
        entity.setAddresses(List.of(address1));
        entity.setCreatedAt(CREATED_DATE);

        when(mockAppConfig.getAddressLookUpUrl()).thenReturn("");
        when(restTemplate.getForObject(COUNTRY_CODE + BACK_SLASH + POSTAL_CODE, AddressLookUpResponse.class))
                .thenReturn(mockAddressLookUpResponse());
        when(mockRestTemplateConfig.getRestTemplate()).thenReturn(restTemplate);
        when(mockUniqueIdGenerator.generateUniqueValueId(4)).thenReturn(SECURITY_PIN);
        when(mockAccountRepository.save(any(Account.class))).thenReturn(account);

        final CreateAccountResponse result = accountServiceImplUnderTest.createAccount(createAccountRequest);

        assertThat(result).isEqualTo(expectedResult);
    }

    @Test
    void testGetAccountByEmail() {
        final Account expectedResult = mockAccount();
        final Address address = mockAddress();
        address.setCreatedAt(CREATED_DATE);
        address.setUpdatedAt(UPDATED_DATE);
        expectedResult.setAddresses(List.of(address));
        expectedResult.setCreatedAt(CREATED_DATE);
        expectedResult.setUpdatedAt(UPDATED_DATE);

        final Account account = mockAccount();

        final Address address1 = mockAddress();
        address1.setCreatedAt(CREATED_DATE);
        address1.setUpdatedAt(UPDATED_DATE);

        account.setAddresses(List.of(address1));
        account.setCreatedAt(CREATED_DATE);
        account.setUpdatedAt(UPDATED_DATE);

        when(mockAccountRepository.findByEmail(EMAIL)).thenReturn(account);

        final Account result = accountServiceImplUnderTest.getAccountByEmail(EMAIL);

        assertThat(result).isEqualTo(expectedResult);
    }

    @Test
    void testGetAccountByAccountIdAndSecurityPIN() {
        final Account expectedResult = mockAccount();
        final Address address = mockAddress();
        address.setCreatedAt(CREATED_DATE);
        address.setUpdatedAt(UPDATED_DATE);
        expectedResult.setAddresses(List.of(address));
        expectedResult.setCreatedAt(CREATED_DATE);
        expectedResult.setUpdatedAt(UPDATED_DATE);

        final Account account = mockAccount();
        final Address address1 = mockAddress();
        address1.setCreatedAt(CREATED_DATE);
        address1.setUpdatedAt(UPDATED_DATE);
        account.setAddresses(List.of(address1));
        account.setCreatedAt(CREATED_DATE);
        account.setUpdatedAt(UPDATED_DATE);
        when(mockAccountRepository.findByAccountIdAndSecurityPIN(ACCOUNT_ID, SECURITY_PIN)).thenReturn(account);

        final Account result = accountServiceImplUnderTest.getAccountByAccountIdAndSecurityPIN(ACCOUNT_ID, SECURITY_PIN);

        assertThat(result).isEqualTo(expectedResult);
    }

    @Test
    void testDeleteAccount() {
        final Account existingAccount = mockAccount();
        final Address address = mockAddress();
        address.setCreatedAt(CREATED_DATE);
        address.setUpdatedAt(UPDATED_DATE);
        existingAccount.setAddresses(List.of(address));
        existingAccount.setCreatedAt(CREATED_DATE);
        existingAccount.setUpdatedAt(UPDATED_DATE);

        accountServiceImplUnderTest.deleteAccount(existingAccount);

        final Account entity = mockAccount();
        final Address address1 = mockAddress();
        address1.setCreatedAt(CREATED_DATE);
        address1.setUpdatedAt(UPDATED_DATE);
        entity.setAddresses(List.of(address1));
        entity.setCreatedAt(CREATED_DATE);
        entity.setUpdatedAt(UPDATED_DATE);
        verify(mockAccountRepository).delete(entity);
    }

    @Test
    void testGetAccountByAccountId() {
        final Account expectedResult = mockAccount();
        final Address address = mockAddress();
        address.setCreatedAt(CREATED_DATE);
        address.setUpdatedAt(UPDATED_DATE);
        expectedResult.setAddresses(List.of(address));
        expectedResult.setCreatedAt(CREATED_DATE);
        expectedResult.setUpdatedAt(UPDATED_DATE);

        final Account account = mockAccount();
        final Address address1 = mockAddress();
        address1.setCreatedAt(CREATED_DATE);
        address1.setUpdatedAt(UPDATED_DATE);
        account.setAddresses(List.of(address1));
        account.setCreatedAt(CREATED_DATE);
        account.setUpdatedAt(UPDATED_DATE);
        when(mockAccountRepository.findByAccountId(ACCOUNT_ID)).thenReturn(account);

        final Account result = accountServiceImplUnderTest.getAccountByAccountId(ACCOUNT_ID);

        assertThat(result).isEqualTo(expectedResult);
    }

    @Test
    void testUpdateAccount() {
        final Account existingAccount = mockAccount();
        final Address address = mockAddress();
        address.setUpdatedAt(UPDATED_DATE);
        existingAccount.setAddresses(List.of(address));
        existingAccount.setUpdatedAt(UPDATED_DATE);

        accountServiceImplUnderTest.updateAccount(existingAccount, NAME, EMAIL, COUNTRY_CODE, POSTAL_CODE, AGE, STATUS_ACTIVE, UPDATED_DATE);

        final Account entity = mockAccount();
        final Address address1 = mockAddress();
        address1.setUpdatedAt(UPDATED_DATE);
        entity.setAddresses(List.of(address1));
        entity.setUpdatedAt(UPDATED_DATE);
        verify(mockAccountRepository).save(entity);
    }

    @Test
    void testUpdateAccountWithExternalAddressLookUpCall() {
        final Account existingAccount = mockAccount();
        final Address address = mockAddress();
        address.setUpdatedAt(UPDATED_DATE);
        existingAccount.setAddresses(List.of(address));
        existingAccount.setCreatedAt(CREATED_DATE);
        existingAccount.setUpdatedAt(UPDATED_DATE);

        when(mockAppConfig.getAddressLookUpUrl()).thenReturn("");
        when(restTemplate.getForObject(COUNTRY_CODE + BACK_SLASH + UPDATE_POSTAL_CODE, AddressLookUpResponse.class))
                .thenReturn(mockUpdateAddressLookUpResponse());
        when(mockRestTemplateConfig.getRestTemplate()).thenReturn(restTemplate);

        accountServiceImplUnderTest.updateAccount(existingAccount, NAME, EMAIL, COUNTRY_CODE, UPDATE_POSTAL_CODE, AGE, STATUS_ACTIVE, UPDATED_DATE);

        final Account entity = mockAccount();
        final Address address1 = mockUpdateAddress();
        address1.setUpdatedAt(UPDATED_DATE);
        entity.setAddresses(List.of(address1));
        entity.setCreatedAt(CREATED_DATE);
        entity.setUpdatedAt(UPDATED_DATE);
        verify(mockAccountRepository).save(entity);
    }

    @Test
    void testGetAccountWhenEmailIdProvided() {
        final Account expectedResult = mockAccount();
        final Address address = mockAddress();
        address.setCreatedAt(CREATED_DATE);
        address.setUpdatedAt(UPDATED_DATE);
        expectedResult.setAddresses(List.of(address));
        expectedResult.setCreatedAt(CREATED_DATE);
        expectedResult.setUpdatedAt(UPDATED_DATE);

        final Account account1 = mockAccount();
        final Address address2 = mockAddress();
        address2.setCreatedAt(CREATED_DATE);
        address2.setUpdatedAt(UPDATED_DATE);
        account1.setAddresses(List.of(address2));
        account1.setCreatedAt(CREATED_DATE);
        account1.setUpdatedAt(UPDATED_DATE);
        when(mockAccountRepository.findByEmail(EMAIL)).thenReturn(account1);

        final Account result = accountServiceImplUnderTest.getAccount("", EMAIL);

        assertThat(result).isEqualTo(expectedResult);
    }

    @Test
    void testGetAccountWhenOnlyAccountIdProvided() {
        final Account expectedResult = mockAccount();
        final Address address = mockAddress();
        address.setCreatedAt(CREATED_DATE);
        address.setUpdatedAt(UPDATED_DATE);
        expectedResult.setAddresses(List.of(address));
        expectedResult.setCreatedAt(CREATED_DATE);
        expectedResult.setUpdatedAt(UPDATED_DATE);

        final Account account = mockAccount();
        final Address address1 = mockAddress();
        address1.setCreatedAt(CREATED_DATE);
        address1.setUpdatedAt(UPDATED_DATE);
        account.setAddresses(List.of(address1));
        account.setCreatedAt(CREATED_DATE);
        account.setUpdatedAt(UPDATED_DATE);
        when(mockAccountRepository.findByAccountId(ACCOUNT_ID)).thenReturn(account);

        final Account result = accountServiceImplUnderTest.getAccount(ACCOUNT_ID, "");

        assertThat(result).isEqualTo(expectedResult);
    }

    @Test
    void testGetAccountWhenBothAccountIdAndEmailIdProvided() {
        final Account expectedResult = mockAccount();
        final Address address = mockAddress();
        address.setCreatedAt(CREATED_DATE);
        address.setUpdatedAt(UPDATED_DATE);
        expectedResult.setAddresses(List.of(address));
        expectedResult.setCreatedAt(CREATED_DATE);
        expectedResult.setUpdatedAt(UPDATED_DATE);

        final Account account2 = mockAccount();
        final Address address3 = mockAddress();
        address3.setCreatedAt(CREATED_DATE);
        address3.setUpdatedAt(UPDATED_DATE);
        account2.setAddresses(List.of(address3));
        account2.setCreatedAt(CREATED_DATE);
        account2.setUpdatedAt(UPDATED_DATE);
        when(mockAccountRepository.findByAccountIdAndEmail(ACCOUNT_ID, EMAIL)).thenReturn(account2);

        final Account result = accountServiceImplUnderTest.getAccount(ACCOUNT_ID, EMAIL);

        assertThat(result).isEqualTo(expectedResult);
    }

    @Test
    void testGetAccountCount() {
        final AccountCountResponse accountCountResponse = new AccountCountResponse();
        accountCountResponse.setCountry(COUNTRY_CODE);
        accountCountResponse.setCount(1);
        final State state = new State();
        state.setState(STATE_CODE);
        state.setCount(1);
        final Place place = new Place();
        place.setPlace(CITY);
        place.setCount(1);
        state.setPlaces(List.of(place));
        accountCountResponse.setStates(List.of(state));
        final List<AccountCountResponse> expectedResult = List.of(accountCountResponse);

        final Account account = mockAccount();
        final Address address = mockAddress();
        address.setUpdatedAt(UPDATED_DATE);
        account.setAddresses(List.of(address));
        account.setCreatedAt(CREATED_DATE);
        account.setUpdatedAt(UPDATED_DATE);
        final List<Account> accounts = List.of(account);
        when(mockAccountRepository.findAll()).thenReturn(accounts);

        final List<AccountCountResponse> result = accountServiceImplUnderTest.getAccountCount();

        assertThat(result).isEqualTo(expectedResult);
    }

    @Test
    void testGetAccountCount_AccountRepositoryReturnsNoItems() {
        when(mockAccountRepository.findAll()).thenReturn(Collections.emptyList());

        assertThatThrownBy(() -> accountServiceImplUnderTest.getAccountCount())
                .isInstanceOf(AccountNotFoundException.class);
    }

    @Test
    void testGetAddressLookUpResponse() {
        final AddressLookUpResponse expectedResult = mockAddressLookUpResponse();

        when(mockAppConfig.getAddressLookUpUrl()).thenReturn("");
        when(restTemplate.getForObject(COUNTRY_CODE + BACK_SLASH + POSTAL_CODE, AddressLookUpResponse.class))
                .thenReturn(mockAddressLookUpResponse());
        when(mockRestTemplateConfig.getRestTemplate()).thenReturn(restTemplate);

        final AddressLookUpResponse result = accountServiceImplUnderTest.getAddressLookUpResponse(COUNTRY_CODE, POSTAL_CODE);

        assertThat(result).isEqualTo(expectedResult);
    }

    private AddressLookUpResponse mockAddressLookUpResponse() {
        AddressLookUpResponse addressLookUpResponse = new AddressLookUpResponse();
        addressLookUpResponse.setPostalCode(POSTAL_CODE);
        addressLookUpResponse.setCountryCode(COUNTRY_CODE);
        addressLookUpResponse.setCountry(COUNTRY);

        PlaceLookUpResponse placeLookUpResponse = new PlaceLookUpResponse();
        placeLookUpResponse.setPlaceName(CITY);
        placeLookUpResponse.setLongitude(LONGITUDE);
        placeLookUpResponse.setState(STATE);
        placeLookUpResponse.setStateCode(STATE_CODE);
        placeLookUpResponse.setLatitude(LATITUDE);

        addressLookUpResponse.setPlaces(List.of(placeLookUpResponse));
        return addressLookUpResponse;
    }

    private Address mockAddress() {
        Address address = new Address();
        address.setCountry(COUNTRY);
        address.setCountryCode(COUNTRY_CODE);
        address.setPostalCode(POSTAL_CODE);
        address.setState(STATE);
        address.setStateCode(STATE_CODE);
        address.setCity(CITY);
        address.setLatitude(LATITUDE);
        address.setLongitude(LONGITUDE);
        address.setCreatedAt(CREATED_DATE);
        return address;
    }

    private Address mockUpdateAddress() {
        Address address = new Address();
        address.setCountry(COUNTRY);
        address.setCountryCode(COUNTRY_CODE);
        address.setPostalCode(UPDATE_POSTAL_CODE);
        address.setState(UPDATE_STATE);
        address.setStateCode(UPDATE_STATE_CODE);
        address.setCity(UPDATE_CITY);
        address.setLatitude(UPDATE_LATITUDE);
        address.setLongitude(UPDATE_LONGITUDE);
        address.setCreatedAt(CREATED_DATE);
        return address;
    }

    private Account mockAccount() {
        final Account account = new Account();
        account.setAccountId(ACCOUNT_ID);
        account.setName(NAME);
        account.setEmail(EMAIL);
        account.setAge(AGE);
        account.setStatus(AccountStatus.ACTIVE);
        account.setSecurityPIN(SECURITY_PIN);
        return account;
    }

    private CreateAccountRequest mockCreateAccountRequest() {
        CreateAccountRequest createAccountRequest = new CreateAccountRequest();
        createAccountRequest.setName(NAME);
        createAccountRequest.setEmail(EMAIL);
        createAccountRequest.setCountry(COUNTRY_CODE);
        createAccountRequest.setPostalCode(POSTAL_CODE);
        createAccountRequest.setAge(AGE);
        createAccountRequest.setStatus(STATUS_ACTIVE);
        return createAccountRequest;
    }

    private AddressLookUpResponse mockUpdateAddressLookUpResponse() {
        AddressLookUpResponse addressLookUpResponse = new AddressLookUpResponse();
        addressLookUpResponse.setPostalCode(UPDATE_POSTAL_CODE);
        addressLookUpResponse.setCountryCode(COUNTRY_CODE);
        addressLookUpResponse.setCountry(COUNTRY);

        PlaceLookUpResponse placeLookUpResponse = new PlaceLookUpResponse();
        placeLookUpResponse.setPlaceName(UPDATE_CITY);
        placeLookUpResponse.setLongitude(UPDATE_LONGITUDE);
        placeLookUpResponse.setState(UPDATE_STATE);
        placeLookUpResponse.setStateCode(UPDATE_STATE_CODE);
        placeLookUpResponse.setLatitude(UPDATE_LATITUDE);

        addressLookUpResponse.setPlaces(List.of(placeLookUpResponse));
        return addressLookUpResponse;
    }
}
