package az.technofest.model.dto;

import java.util.List;
import lombok.Data;

@Data
public class UserDto {


Long id;
String username;
String password;


    List<String> roles;
}
