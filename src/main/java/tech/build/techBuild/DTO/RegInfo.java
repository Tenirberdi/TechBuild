package tech.build.techBuild.DTO;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class RegInfo {
    String fullName;
    String login;
    String pass;
    String email;
    String photo;
    String phone;
    String org;
    String role;
}
