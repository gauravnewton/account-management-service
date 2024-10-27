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

import java.util.*;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

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
        // Setup
        final Account expectedResult = new Account();
        expectedResult.setAccountId("accountId");
        expectedResult.setName("name");
        expectedResult.setEmail("email");
        expectedResult.setAge(0);
        expectedResult.setStatus(AccountStatus.IN_ACTIVE);
        expectedResult.setSecurityPIN("securityPIN");
        final Address address = new Address();
        address.setCountry("country");
        address.setCountryCode("countryCode");
        address.setPostalCode("postalCode");
        address.setState("state");
        address.setStateCode("stateCode");
        address.setCity("city");
        address.setLatitude("latitude");
        address.setLongitude("longitude");
        address.setCreatedAt(new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime());
        address.setUpdatedAt(new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime());
        expectedResult.setAddresses(List.of(address));
        expectedResult.setCreatedAt(new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime());
        expectedResult.setUpdatedAt(new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime());

        final Account account = new Account();
        account.setAccountId("accountId");
        account.setName("name");
        account.setEmail("email");
        account.setAge(0);
        account.setStatus(AccountStatus.IN_ACTIVE);
        account.setSecurityPIN("securityPIN");

        final Address address1 = new Address();
        address1.setCountry("country");
        address1.setCountryCode("countryCode");
        address1.setPostalCode("postalCode");
        address1.setState("state");
        address1.setStateCode("stateCode");
        address1.setCity("city");
        address1.setLatitude("latitude");
        address1.setLongitude("longitude");
        address1.setCreatedAt(new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime());
        address1.setUpdatedAt(new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime());

        account.setAddresses(List.of(address1));
        account.setCreatedAt(new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime());
        account.setUpdatedAt(new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime());

        when(mockAccountRepository.findByEmail("email")).thenReturn(account);

        final Account result = accountServiceImplUnderTest.getAccountByEmail("email");

        assertThat(result).isEqualTo(expectedResult);
    }

    @Test
    void testGetAccountByAccountIdAndSecurityPIN() {
        // Setup
        final Account expectedResult = new Account();
        expectedResult.setAccountId("accountId");
        expectedResult.setName("name");
        expectedResult.setEmail("email");
        expectedResult.setAge(0);
        expectedResult.setStatus(AccountStatus.IN_ACTIVE);
        expectedResult.setSecurityPIN("securityPIN");
        final Address address = new Address();
        address.setCountry("country");
        address.setCountryCode("countryCode");
        address.setPostalCode("postalCode");
        address.setState("state");
        address.setStateCode("stateCode");
        address.setCity("city");
        address.setLatitude("latitude");
        address.setLongitude("longitude");
        address.setCreatedAt(new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime());
        address.setUpdatedAt(new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime());
        expectedResult.setAddresses(List.of(address));
        expectedResult.setCreatedAt(new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime());
        expectedResult.setUpdatedAt(new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime());

        // Configure AccountRepository.findByAccountIdAndSecurityPIN(...).
        final Account account = new Account();
        account.setAccountId("accountId");
        account.setName("name");
        account.setEmail("email");
        account.setAge(0);
        account.setStatus(AccountStatus.IN_ACTIVE);
        account.setSecurityPIN("securityPIN");
        final Address address1 = new Address();
        address1.setCountry("country");
        address1.setCountryCode("countryCode");
        address1.setPostalCode("postalCode");
        address1.setState("state");
        address1.setStateCode("stateCode");
        address1.setCity("city");
        address1.setLatitude("latitude");
        address1.setLongitude("longitude");
        address1.setCreatedAt(new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime());
        address1.setUpdatedAt(new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime());
        account.setAddresses(List.of(address1));
        account.setCreatedAt(new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime());
        account.setUpdatedAt(new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime());
        when(mockAccountRepository.findByAccountIdAndSecurityPIN("accountId", "securityPIN")).thenReturn(account);

        // Run the test
        final Account result = accountServiceImplUnderTest.getAccountByAccountIdAndSecurityPIN("accountId",
                "securityPIN");

        // Verify the results
        assertThat(result).isEqualTo(expectedResult);
    }

    @Test
    void testDeleteAccount() {
        // Setup
        final Account existingAccount = new Account();
        existingAccount.setAccountId("accountId");
        existingAccount.setName("name");
        existingAccount.setEmail("email");
        existingAccount.setAge(0);
        existingAccount.setStatus(AccountStatus.IN_ACTIVE);
        existingAccount.setSecurityPIN("securityPIN");
        final Address address = new Address();
        address.setCountry("country");
        address.setCountryCode("countryCode");
        address.setPostalCode("postalCode");
        address.setState("state");
        address.setStateCode("stateCode");
        address.setCity("city");
        address.setLatitude("latitude");
        address.setLongitude("longitude");
        address.setCreatedAt(new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime());
        address.setUpdatedAt(new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime());
        existingAccount.setAddresses(List.of(address));
        existingAccount.setCreatedAt(new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime());
        existingAccount.setUpdatedAt(new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime());

        // Run the test
        accountServiceImplUnderTest.deleteAccount(existingAccount);

        // Verify the results
        // Confirm AccountRepository.delete(...).
        final Account entity = new Account();
        entity.setAccountId("accountId");
        entity.setName("name");
        entity.setEmail("email");
        entity.setAge(0);
        entity.setStatus(AccountStatus.IN_ACTIVE);
        entity.setSecurityPIN("securityPIN");
        final Address address1 = new Address();
        address1.setCountry("country");
        address1.setCountryCode("countryCode");
        address1.setPostalCode("postalCode");
        address1.setState("state");
        address1.setStateCode("stateCode");
        address1.setCity("city");
        address1.setLatitude("latitude");
        address1.setLongitude("longitude");
        address1.setCreatedAt(new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime());
        address1.setUpdatedAt(new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime());
        entity.setAddresses(List.of(address1));
        entity.setCreatedAt(new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime());
        entity.setUpdatedAt(new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime());
        verify(mockAccountRepository).delete(entity);
    }

    @Test
    void testGetAccountByAccountId() {
        // Setup
        final Account expectedResult = new Account();
        expectedResult.setAccountId("accountId");
        expectedResult.setName("name");
        expectedResult.setEmail("email");
        expectedResult.setAge(0);
        expectedResult.setStatus(AccountStatus.IN_ACTIVE);
        expectedResult.setSecurityPIN("securityPIN");
        final Address address = new Address();
        address.setCountry("country");
        address.setCountryCode("countryCode");
        address.setPostalCode("postalCode");
        address.setState("state");
        address.setStateCode("stateCode");
        address.setCity("city");
        address.setLatitude("latitude");
        address.setLongitude("longitude");
        address.setCreatedAt(new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime());
        address.setUpdatedAt(new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime());
        expectedResult.setAddresses(List.of(address));
        expectedResult.setCreatedAt(new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime());
        expectedResult.setUpdatedAt(new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime());

        // Configure AccountRepository.findByAccountId(...).
        final Account account = new Account();
        account.setAccountId("accountId");
        account.setName("name");
        account.setEmail("email");
        account.setAge(0);
        account.setStatus(AccountStatus.IN_ACTIVE);
        account.setSecurityPIN("securityPIN");
        final Address address1 = new Address();
        address1.setCountry("country");
        address1.setCountryCode("countryCode");
        address1.setPostalCode("postalCode");
        address1.setState("state");
        address1.setStateCode("stateCode");
        address1.setCity("city");
        address1.setLatitude("latitude");
        address1.setLongitude("longitude");
        address1.setCreatedAt(new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime());
        address1.setUpdatedAt(new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime());
        account.setAddresses(List.of(address1));
        account.setCreatedAt(new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime());
        account.setUpdatedAt(new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime());
        when(mockAccountRepository.findByAccountId("accountId")).thenReturn(account);

        // Run the test
        final Account result = accountServiceImplUnderTest.getAccountByAccountId("accountId");

        // Verify the results
        assertThat(result).isEqualTo(expectedResult);
    }

    @Test
    void testUpdateAccount() {
        // Setup
        final Account existingAccount = new Account();
        existingAccount.setAccountId("accountId");
        existingAccount.setName("name");
        existingAccount.setEmail("email");
        existingAccount.setAge(0);
        existingAccount.setStatus(AccountStatus.IN_ACTIVE);
        existingAccount.setSecurityPIN("securityPIN");
        final Address address = new Address();
        address.setCountry("country");
        address.setCountryCode("countryCode");
        address.setPostalCode("postalCode");
        address.setState("state");
        address.setStateCode("stateCode");
        address.setCity("city");
        address.setLatitude("latitude");
        address.setLongitude("longitude");
        address.setCreatedAt(new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime());
        address.setUpdatedAt(new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime());
        existingAccount.setAddresses(List.of(address));
        existingAccount.setCreatedAt(new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime());
        existingAccount.setUpdatedAt(new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime());

        when(mockAppConfig.getAddressLookUpUrl()).thenReturn("result");
        when(mockRestTemplateConfig.getRestTemplate()).thenReturn(new RestTemplate(List.of()));

        // Run the test
        accountServiceImplUnderTest.updateAccount(existingAccount, "name", "email", "country", "postalCode", 0,
                "status");

        // Verify the results
        // Confirm AccountRepository.save(...).
        final Account entity = new Account();
        entity.setAccountId("accountId");
        entity.setName("name");
        entity.setEmail("email");
        entity.setAge(0);
        entity.setStatus(AccountStatus.IN_ACTIVE);
        entity.setSecurityPIN("securityPIN");
        final Address address1 = new Address();
        address1.setCountry("country");
        address1.setCountryCode("countryCode");
        address1.setPostalCode("postalCode");
        address1.setState("state");
        address1.setStateCode("stateCode");
        address1.setCity("city");
        address1.setLatitude("latitude");
        address1.setLongitude("longitude");
        address1.setCreatedAt(new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime());
        address1.setUpdatedAt(new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime());
        entity.setAddresses(List.of(address1));
        entity.setCreatedAt(new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime());
        entity.setUpdatedAt(new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime());
        verify(mockAccountRepository).save(entity);
    }

    @Test
    void testGetAccount() {
        // Setup
        final Account expectedResult = new Account();
        expectedResult.setAccountId("accountId");
        expectedResult.setName("name");
        expectedResult.setEmail("email");
        expectedResult.setAge(0);
        expectedResult.setStatus(AccountStatus.IN_ACTIVE);
        expectedResult.setSecurityPIN("securityPIN");
        final Address address = new Address();
        address.setCountry("country");
        address.setCountryCode("countryCode");
        address.setPostalCode("postalCode");
        address.setState("state");
        address.setStateCode("stateCode");
        address.setCity("city");
        address.setLatitude("latitude");
        address.setLongitude("longitude");
        address.setCreatedAt(new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime());
        address.setUpdatedAt(new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime());
        expectedResult.setAddresses(List.of(address));
        expectedResult.setCreatedAt(new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime());
        expectedResult.setUpdatedAt(new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime());

        // Configure AccountRepository.findByAccountId(...).
        final Account account = new Account();
        account.setAccountId("accountId");
        account.setName("name");
        account.setEmail("email");
        account.setAge(0);
        account.setStatus(AccountStatus.IN_ACTIVE);
        account.setSecurityPIN("securityPIN");
        final Address address1 = new Address();
        address1.setCountry("country");
        address1.setCountryCode("countryCode");
        address1.setPostalCode("postalCode");
        address1.setState("state");
        address1.setStateCode("stateCode");
        address1.setCity("city");
        address1.setLatitude("latitude");
        address1.setLongitude("longitude");
        address1.setCreatedAt(new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime());
        address1.setUpdatedAt(new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime());
        account.setAddresses(List.of(address1));
        account.setCreatedAt(new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime());
        account.setUpdatedAt(new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime());
        when(mockAccountRepository.findByAccountId("accountId")).thenReturn(account);

        // Configure AccountRepository.findByEmail(...).
        final Account account1 = new Account();
        account1.setAccountId("accountId");
        account1.setName("name");
        account1.setEmail("email");
        account1.setAge(0);
        account1.setStatus(AccountStatus.IN_ACTIVE);
        account1.setSecurityPIN("securityPIN");
        final Address address2 = new Address();
        address2.setCountry("country");
        address2.setCountryCode("countryCode");
        address2.setPostalCode("postalCode");
        address2.setState("state");
        address2.setStateCode("stateCode");
        address2.setCity("city");
        address2.setLatitude("latitude");
        address2.setLongitude("longitude");
        address2.setCreatedAt(new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime());
        address2.setUpdatedAt(new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime());
        account1.setAddresses(List.of(address2));
        account1.setCreatedAt(new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime());
        account1.setUpdatedAt(new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime());
        when(mockAccountRepository.findByEmail("email")).thenReturn(account1);

        // Configure AccountRepository.findByAccountIdAndEmail(...).
        final Account account2 = new Account();
        account2.setAccountId("accountId");
        account2.setName("name");
        account2.setEmail("email");
        account2.setAge(0);
        account2.setStatus(AccountStatus.IN_ACTIVE);
        account2.setSecurityPIN("securityPIN");
        final Address address3 = new Address();
        address3.setCountry("country");
        address3.setCountryCode("countryCode");
        address3.setPostalCode("postalCode");
        address3.setState("state");
        address3.setStateCode("stateCode");
        address3.setCity("city");
        address3.setLatitude("latitude");
        address3.setLongitude("longitude");
        address3.setCreatedAt(new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime());
        address3.setUpdatedAt(new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime());
        account2.setAddresses(List.of(address3));
        account2.setCreatedAt(new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime());
        account2.setUpdatedAt(new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime());
        when(mockAccountRepository.findByAccountIdAndEmail("accountId", "email")).thenReturn(account2);

        // Run the test
        final Account result = accountServiceImplUnderTest.getAccount("accountId", "email");

        // Verify the results
        assertThat(result).isEqualTo(expectedResult);
    }

    @Test
    void testGetAccountCount() {
        // Setup
        final AccountCountResponse accountCountResponse = new AccountCountResponse();
        accountCountResponse.setCountry("key");
        accountCountResponse.setCount(0);
        final State state = new State();
        state.setState("state");
        state.setCount(0);
        final Place place = new Place();
        place.setPlace("place");
        place.setCount(0);
        state.setPlaces(List.of(place));
        accountCountResponse.setStates(List.of(state));
        final List<AccountCountResponse> expectedResult = List.of(accountCountResponse);

        // Configure AccountRepository.findAll(...).
        final Account account = new Account();
        account.setAccountId("accountId");
        account.setName("name");
        account.setEmail("email");
        account.setAge(0);
        account.setStatus(AccountStatus.IN_ACTIVE);
        account.setSecurityPIN("securityPIN");
        final Address address = new Address();
        address.setCountry("country");
        address.setCountryCode("countryCode");
        address.setPostalCode("postalCode");
        address.setState("state");
        address.setStateCode("stateCode");
        address.setCity("city");
        address.setLatitude("latitude");
        address.setLongitude("longitude");
        address.setCreatedAt(new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime());
        address.setUpdatedAt(new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime());
        account.setAddresses(List.of(address));
        account.setCreatedAt(new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime());
        account.setUpdatedAt(new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime());
        final List<Account> accounts = List.of(account);
        when(mockAccountRepository.findAll()).thenReturn(accounts);

        // Run the test
        final List<AccountCountResponse> result = accountServiceImplUnderTest.getAccountCount();

        // Verify the results
        assertThat(result).isEqualTo(expectedResult);
    }

    @Test
    void testGetAccountCount_AccountRepositoryReturnsNoItems() {
        // Setup
        when(mockAccountRepository.findAll()).thenReturn(Collections.emptyList());

        // Run the test
        assertThatThrownBy(() -> accountServiceImplUnderTest.getAccountCount())
                .isInstanceOf(AccountNotFoundException.class);
    }

    @Test
    void testGetAddressLookUpResponse() {
        // Setup
        final AddressLookUpResponse expectedResult = new AddressLookUpResponse();
        expectedResult.setPostalCode("postalCode");
        expectedResult.setCountry("country");
        expectedResult.setCountryCode("countryCode");
        final PlaceLookUpResponse placeLookUpResponse = new PlaceLookUpResponse();
        placeLookUpResponse.setPlaceName("city");
        placeLookUpResponse.setLongitude("longitude");
        placeLookUpResponse.setState("state");
        placeLookUpResponse.setStateCode("stateCode");
        placeLookUpResponse.setLatitude("latitude");
        expectedResult.setPlaces(List.of(placeLookUpResponse));

        when(mockAppConfig.getAddressLookUpUrl()).thenReturn("result");
        when(mockRestTemplateConfig.getRestTemplate()).thenReturn(new RestTemplate(List.of()));

        // Run the test
        final AddressLookUpResponse result = accountServiceImplUnderTest.getAddressLookUpResponse("countryCode",
                "postalCode");

        // Verify the results
        assertThat(result).isEqualTo(expectedResult);
    }

    private AddressLookUpResponse mockAddressLookUpResponse() {
        AddressLookUpResponse addressLookUpResponse = new AddressLookUpResponse();
        addressLookUpResponse.setPostalCode("00501");
        addressLookUpResponse.setCountryCode("US");
        addressLookUpResponse.setCountry("United States");

        PlaceLookUpResponse placeLookUpResponse = new PlaceLookUpResponse();
        placeLookUpResponse.setPlaceName("Holtsville");
        placeLookUpResponse.setLongitude("-72.6371");
        placeLookUpResponse.setState("New York");
        placeLookUpResponse.setStateCode("NY");
        placeLookUpResponse.setLatitude("40.9223");

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
}
