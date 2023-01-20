package user_board.dto;

import java.sql.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserBoard {

    private int board_num;
    private int user_num;
    private String user_name;
    private String board_title ;
    private String board_content;
    private String picture_path;
    private Date board_date;
    

 }
