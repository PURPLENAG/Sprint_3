package util;

import api.model.CreateOrderDto;
import api.model.LoginCourierDto;
import api.model.RegisterCourierDto;
import org.apache.commons.lang3.RandomStringUtils;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static org.apache.commons.lang3.RandomStringUtils.randomAlphabetic;
import static org.apache.commons.lang3.RandomStringUtils.randomNumeric;
import static org.apache.commons.lang3.RandomUtils.nextBoolean;
import static org.apache.commons.lang3.RandomUtils.nextInt;

public class DataGenerator {
    public static RegisterCourierDto generateRegisterCourierDto() {
        return new RegisterCourierDto(
                RandomStringUtils.randomAlphabetic(10),
                RandomStringUtils.randomAlphabetic(10),
                RandomStringUtils.randomAlphabetic(10));
    }

    public static CreateOrderDto generateCreateOrderDto() {
        var dto = new CreateOrderDto();
        dto.setFirstName(randomAlphabetic(10));
        dto.setLastName(randomAlphabetic(10));
        dto.setAddress(randomAlphabetic(10) + ", " + randomNumeric(1, 3));
        dto.setMetroStation(nextInt(1, 10));
        dto.setPhone("+7 " + randomNumeric(3) + " " + randomNumeric(3) + " " + randomNumeric(2) + " " + randomNumeric(2));
        dto.setRentTime(nextInt(1, 5));
        dto.setDeliveryDate(LocalDate.now().plusDays(nextInt(1, 5)).format(DateTimeFormatter.ISO_LOCAL_DATE));
        dto.setComment(randomAlphabetic(10));
        if (nextBoolean()) {
            dto.getColor().add("BLACK");
        }
        if (nextBoolean()) {
            dto.getColor().add("GREY");
        }
        return dto;
    }

    public static LoginCourierDto generateLoginCourierDto() {
        return new LoginCourierDto(
                RandomStringUtils.randomAlphabetic(10),
                RandomStringUtils.randomAlphabetic(10));
    }
}
