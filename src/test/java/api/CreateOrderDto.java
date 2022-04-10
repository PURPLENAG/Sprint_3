package api;

import org.apache.commons.lang3.RandomUtils;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import static org.apache.commons.lang3.RandomStringUtils.randomAlphabetic;
import static org.apache.commons.lang3.RandomStringUtils.randomNumeric;
import static org.apache.commons.lang3.RandomUtils.nextInt;

public class CreateOrderDto {
    private String firstName;
    private String lastName;
    private String address;
    private Integer metroStation;
    private String phone;
    private Integer rentTime;
    private String deliveryDate;
    private String comment;
    private final List<String> color = new ArrayList<>();

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Integer getMetroStation() {
        return metroStation;
    }

    public void setMetroStation(Integer metroStation) {
        this.metroStation = metroStation;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Integer getRentTime() {
        return rentTime;
    }

    public void setRentTime(Integer rentTime) {
        this.rentTime = rentTime;
    }

    public String getDeliveryDate() {
        return deliveryDate;
    }

    public void setDeliveryDate(String deliveryDate) {
        this.deliveryDate = deliveryDate;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public List<String> getColor() {
        return color;
    }

    public void setColor(List<String> color) {
        this.color.clear();
        this.color.addAll(color);
    }

    public static CreateOrderDto createRandom() {
        var dto = new CreateOrderDto();
        dto.setFirstName(randomAlphabetic(10));
        dto.setLastName(randomAlphabetic(10));
        dto.setAddress(randomAlphabetic(10) + ", " + randomNumeric(1, 3));
        dto.setMetroStation(nextInt(1, 10));
        dto.setPhone("+7 " + randomNumeric(3) + " " + randomNumeric(3) + " " + randomNumeric(2) + " " + randomNumeric(2));
        dto.setRentTime(nextInt(1, 5));
        dto.setDeliveryDate(LocalDate.now().plusDays(nextInt(1, 5)).format(DateTimeFormatter.ISO_LOCAL_DATE));
        dto.setComment(randomAlphabetic(10));
        if (RandomUtils.nextBoolean()) {
            dto.getColor().add("BLACK");
        }
        if (RandomUtils.nextBoolean()) {
            dto.getColor().add("GREY");
        }
        return dto;
    }
}
