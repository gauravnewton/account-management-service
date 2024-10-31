/*
 * Copyright (c) 2024.
 * This is a assignment project by Gaurav Kumar for client assessment.
 */

package com.rogers.account.management.services.impl;

import com.rogers.account.management.configs.AppConfig;
import com.rogers.account.management.configs.RestTemplateConfig;
import com.rogers.account.management.configs.UniqueIdGenerator;
import com.rogers.account.management.dtos.api.AddressLookUpResponse;
import com.rogers.account.management.dtos.request.CreateAccountRequest;
import com.rogers.account.management.dtos.response.AccountCountResponse;
import com.rogers.account.management.dtos.response.CreateAccountResponse;
import com.rogers.account.management.dtos.response.Place;
import com.rogers.account.management.dtos.response.State;
import com.rogers.account.management.entities.Account;
import com.rogers.account.management.entities.Address;
import com.rogers.account.management.enums.AccountStatus;
import com.rogers.account.management.exceptions.types.AccountNotFoundException;
import com.rogers.account.management.exceptions.types.AddressLookUpException;
import com.rogers.account.management.exceptions.types.InvalidCountryAndPostalCombinationException;
import com.rogers.account.management.repositories.AccountRepository;
import com.rogers.account.management.services.AccountService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

import static com.rogers.account.management.commons.AppConstant.*;
import static java.util.stream.Collectors.groupingBy;

/**
 * The type Account service.
 */
@Slf4j
@Service
public class AccountServiceImpl implements AccountService {
    @Autowired
    private AccountRepository accountRepository;
    @Autowired
    private AppConfig appConfig;
    @Autowired
    private RestTemplateConfig restTemplateConfig;
    @Autowired
    private UniqueIdGenerator uniqueIdGenerator;

    @Override
    public CreateAccountResponse createAccount(CreateAccountRequest createAccountRequest) {
        AddressLookUpResponse response = getAddressLookUpResponse(createAccountRequest.getCountry(), createAccountRequest.getPostalCode());
        Account newAccount = new Account();
        populateAccountInformation(newAccount, createAccountRequest, response);
        newAccount = accountRepository.save(newAccount);
        return new CreateAccountResponse(newAccount.getAccountId(), newAccount.getStatus(), newAccount.getSecurityPIN());
    }

    @Override
    public Account getAccountByEmail(String emailId) {
        return accountRepository.findByEmail(emailId);
    }

    @Override
    public Account getAccountByAccountIdAndSecurityPIN(String accountId, String securityPIN) {
        return accountRepository.findByAccountIdAndSecurityPIN(accountId, securityPIN);
    }

    @Override
    public void deleteAccount(Account existingAccount) {
        accountRepository.delete(existingAccount);
    }

    @Override
    public Account getAccountByAccountId(String accountId) {
        return accountRepository.findByAccountId(accountId);
    }

    @Override
    public void updateAccount(Account existingAccount, String name, String email, String country, String postalCode, int age, String status, Date updatedAt) {
        if (StringUtils.isNotBlank(name)) {
            existingAccount.setName(name);
        }
        if (StringUtils.isNotBlank(email)) {
            existingAccount.setEmail(email);
        }
        if (age != 0) {
            existingAccount.setAge(age);
        }
        if (StringUtils.isNotBlank(status)) {
            existingAccount.setStatus(AccountStatus.valueOf(status));
        }
        if ((StringUtils.isNotBlank(country) || (StringUtils.isNotBlank(postalCode)))
                && (!existingAccount.getAddresses().get(0).getCountryCode().equals(country)
                || !(existingAccount.getAddresses().get(0).getPostalCode()).equals(postalCode))) {
            AddressLookUpResponse response = getAddressLookUpResponse(country, postalCode);
            populateAddressInformation(existingAccount, response, updatedAt);
        }
        accountRepository.save(existingAccount);
    }

    @Override
    public Account getAccount(String accountId, String email) {
        Account existingAccount;
        if (StringUtils.isNotBlank(accountId) && StringUtils.isBlank(email)) {
            existingAccount = getAccountByAccountId(accountId);
        } else if (StringUtils.isBlank(accountId) && StringUtils.isNotBlank(email)) {
            existingAccount = getAccountByEmail(email);
        } else {
            existingAccount = getAccountByAccountIdAndEmail(accountId, email);
        }
        return existingAccount;
    }

    @Override
    public List<AccountCountResponse> getAccountCount() {
        List<Account> allAccounts = accountRepository.findAll();
        if (allAccounts.size() == 0) {
            throw new AccountNotFoundException(NO_ACCOUNT_EXISTS);
        }
        List<AccountCountResponse> collect = allAccounts.stream()
                .collect(groupingBy(a -> a.getAddresses().get(0).getCountryCode()))
                .entrySet().stream()
                .map(entry -> toCountryDTO(entry.getKey(), entry.getValue()))
                .collect(Collectors.toList());
        return collect;
    }

    /**
     * Gets address look up response.
     *
     * @param countryCode the country code
     * @param postalCode  the postal code
     * @return the address look up response
     */
    public AddressLookUpResponse getAddressLookUpResponse(String countryCode, String postalCode) {
        AddressLookUpResponse response;
        try {
            log.info("Getting address info for postal code {} and country code {} from address {}",
                    postalCode, countryCode, appConfig.getAddressLookUpUrl());
            RestTemplate template = restTemplateConfig.getRestTemplate();
            response = template.getForObject(appConfig.getAddressLookUpUrl() + countryCode
                    + BACK_SLASH + postalCode, AddressLookUpResponse.class);
            log.info("Address look up response {}", response);
        } catch (Exception exception) {
            if (exception.getMessage().equals(INVALID_COUNTRY_AND_POSTAL_COMBINATION)) {
                throw new InvalidCountryAndPostalCombinationException("Invalid Country Code and Postal Code provided");
            }
            throw new AddressLookUpException(exception.getMessage());
        }
        return response;
    }

    private void populateAccountInformation(Account newAccount, CreateAccountRequest createAccountRequest, AddressLookUpResponse response) {
        if (Objects.isNull(response)) {
            throw new AddressLookUpException("Empty response received while performing address lookup");
        }
        newAccount.setName(createAccountRequest.getName());
        newAccount.setEmail(createAccountRequest.getEmail());
        newAccount.setAge(createAccountRequest.getAge());
        newAccount.setStatus(Objects.nonNull(createAccountRequest.getStatus()) ? AccountStatus.valueOf(createAccountRequest.getStatus()) : AccountStatus.ACTIVE);

        newAccount.setSecurityPIN(uniqueIdGenerator.generateUniqueValueId(PIN_LENGTH));
        newAccount.setCreatedAt(new Date());

        Address address = new Address();
        address.setPostalCode(response.getPostalCode());
        address.setCountry(response.getCountry());
        address.setCountryCode(response.getCountryCode());

        address.setCity(response.getPlaces().get(0).getPlaceName());
        address.setLongitude(response.getPlaces().get(0).getLongitude());
        address.setState(response.getPlaces().get(0).getState());
        address.setStateCode(response.getPlaces().get(0).getStateCode());
        address.setLatitude(response.getPlaces().get(0).getLatitude());
        address.setCreatedAt(new Date());

        newAccount.setAddresses(List.of(address));
    }

    private AccountCountResponse toCountryDTO(String key, List<Account> accounts) {
        int totalCount = accounts.size();

        List<State> stateDTOs = accounts.stream()
                .collect(Collectors.groupingBy(a -> a.getAddresses().get(0).getStateCode())) // Assuming you have a getState method
                .entrySet().stream()
                .sorted(Map.Entry.comparingByKey())
                .map(entry -> toStateDTO(entry.getKey(), entry.getValue()))
                .collect(Collectors.toList());

        return new AccountCountResponse(key, totalCount, stateDTOs);
    }

    private State toStateDTO(String state, List<Account> accounts) {
        int totalCount = accounts.size();

        List<Place> placeDTOs = accounts.stream()
                .collect(Collectors.groupingBy(acc -> acc.getAddresses().get(0).getCity()))
                .entrySet().stream()
                .sorted(Map.Entry.comparingByKey())
                .map(entry -> toPlaceDTO(entry.getKey(), entry.getValue()))
                .collect(Collectors.toList());

        return new State(state, totalCount, placeDTOs);
    }

    private Place toPlaceDTO(String place, List<Account> accounts) {
        int totalCount = accounts.size();
        return new Place(place, totalCount);
    }

    private Account getAccountByAccountIdAndEmail(String accountId, String email) {
        return accountRepository.findByAccountIdAndEmail(accountId, email);
    }

    private void populateAddressInformation(Account existingAccount, AddressLookUpResponse response, Date updatedAt) {
        if (Objects.isNull(response)) {
            throw new AddressLookUpException("Empty response received while performing address lookup");
        }
        List<Address> existingAddress = existingAccount.getAddresses();
        existingAddress.get(0).setPostalCode(response.getPostalCode());
        existingAddress.get(0).setCountry(response.getCountry());
        existingAddress.get(0).setCountryCode(response.getCountryCode());

        existingAddress.get(0).setCity(response.getPlaces().get(0).getPlaceName());
        existingAddress.get(0).setLongitude(response.getPlaces().get(0).getLongitude());
        existingAddress.get(0).setState(response.getPlaces().get(0).getState());
        existingAddress.get(0).setStateCode(response.getPlaces().get(0).getStateCode());
        existingAddress.get(0).setLatitude(response.getPlaces().get(0).getLatitude());
        existingAddress.get(0).setUpdatedAt(updatedAt);

        existingAccount.setAddresses(existingAddress);
        existingAccount.setUpdatedAt(updatedAt);
    }
}
