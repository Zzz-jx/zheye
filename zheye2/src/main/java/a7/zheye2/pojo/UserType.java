package a7.zheye2.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name="usertype")
@NoArgsConstructor(force=true)
@AllArgsConstructor
@Data
public class UserType {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long userTypeID;
    private String userTypeName;
    UserType (String userTypeName){
        this.userTypeName=userTypeName;
    }
}
