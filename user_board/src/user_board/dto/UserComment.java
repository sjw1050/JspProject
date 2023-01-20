package user_board.dto;

import java.sql.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserComment {

      private int comment_id;
      private int board_num;
      private String cm_content;
      private Date cm_date;
      private String user_name;      
   }
