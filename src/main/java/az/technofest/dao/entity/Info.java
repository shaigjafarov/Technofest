package az.technofest.dao.entity;

import jakarta.persistence.Embeddable;
import lombok.Data;

@Embeddable
@Data
public class Info {

    private String address;
    private String phone;
    private String mail;
}
