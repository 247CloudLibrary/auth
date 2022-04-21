package com.cloudlibrary.auth.application.domain;


import com.cloudlibrary.auth.exception.CloudLibraryException;
import com.cloudlibrary.auth.exception.MessageType;
import lombok.Getter;
import org.springframework.util.ObjectUtils;

import java.util.Optional;
import java.util.OptionalInt;

@Getter
public enum Gender {
    M("남"),
    F("여"),
    INVALID("유효하지않음");


    private final String type;

    Gender(String type) {
        this.type = type;
    }

    public static Gender find(String type) {

        if (ObjectUtils.isEmpty(type)) {
            throw new CloudLibraryException(MessageType.BAD_REQUEST);
        }
        for (Gender gender : Gender.values()) {
            if (gender.type.equals(type)) {
                return gender;
            }
        }
        return INVALID;
    }
}
