package data.dto;


import lombok.Data;

@Data
public class EmployeeDto {
    private String name;
    private String gender;
    private int age;
    private Long salary;
    private String email;
    private String address;
    private String imageURL;

}
