package data.dto;


import lombok.Data;



@Data
public class EmployeeDto {
    private String firstName;
    private String lastName;
    private String gender;
    private String  dateOfBirth;
    private Long salary;
    private String email;
    private String phoneNumber;
    private String address;
    private String imageURL;

}

