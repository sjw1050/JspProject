package user_board.dto;

import java.sql.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {

	private int user_num;
    private String user_id;
    private String user_pw;
    private String user_name;
    private int user_age;
    private String user_phone;
    private String user_gender;
    private Date reg_date;     

   }
