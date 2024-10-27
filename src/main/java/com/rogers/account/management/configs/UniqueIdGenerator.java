package com.rogers.account.management.configs;

import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.id.IdentifierGenerator;
import org.springframework.context.annotation.Configuration;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

import static com.rogers.account.management.commons.AppConstant.*;

@Configuration
public class UniqueIdGenerator implements IdentifierGenerator {
    private Set<String> generatedValues = new HashSet<>();
    private Random random = new Random();

    public String generateUniqueValueId(int length) {
        String value;
        do {
            value = generateRandomString(length);
        } while (generatedValues.contains(value));
        generatedValues.add(value);
        return value;
    }

    private String generateRandomString(int length) {
        StringBuilder sb = new StringBuilder(length);
        for (int i = 0; i < length; i++) {
            if (length == ID_LENGTH) {
                int index = random.nextInt(ID_CHARACTERS.length());
                sb.append(ID_CHARACTERS.charAt(index));
            } else if (length == PIN_LENGTH) {
                int index = random.nextInt(PIN_CHARACTER.length());
                sb.append(PIN_CHARACTER.charAt(index));
            }
        }
        return sb.toString();
    }

    @Override
    public Object generate(SharedSessionContractImplementor sharedSessionContractImplementor, Object o) {
        return generateUniqueValueId(ID_LENGTH);
    }
}
