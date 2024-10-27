package com.rogers.account.management.service;

import com.rogers.account.management.dtos.api.AddressLookUpResponse;
import com.rogers.account.management.dtos.api.PlaceLookUpResponse;
import com.rogers.account.management.dtos.request.CreateAccountRequest;
import com.rogers.account.management.dtos.response.CreateAccountResponse;
import com.rogers.account.management.entities.Account;
import com.rogers.account.management.enums.AccountStatus;
import com.rogers.account.management.repositories.AccountRepository;
import com.rogers.account.management.services.impl.AccountServiceImpl;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;

import java.util.List;

import static org.mockito.Mockito.when;

public class AccountServiceTest {

    @Mock
    private AccountServiceImpl accountService = Mockito.mock(AccountServiceImpl.class);

    @InjectMocks
    private AccountRepository accountRepository = Mockito.mock(AccountRepository.class);

    @Test
    @DisplayName("Should create an account: success scenario")
    void testCreateAccount() {
        CreateAccountRequest request = new CreateAccountRequest();
        request.setName("Gaurav Kumar");
        request.setEmail("gaurav.mute@gmail.com");
        request.setCountry("US");
        request.setPostalCode("00501");
        request.setAge(22);
        request.setStatus(AccountStatus.REQUESTED.name());

        AddressLookUpResponse addressLookUpResponse = mockAddressLookUpResponse();
        Account accountEntity = new Account();
        accountEntity.setAccountId("A9K09O");
        when(accountService.getAddressLookUpResponse("US", "00501")).thenReturn(addressLookUpResponse);
        when(accountRepository.save(accountEntity)).thenReturn(accountEntity);

        CreateAccountResponse response = accountService.createAccount(request);
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
}
