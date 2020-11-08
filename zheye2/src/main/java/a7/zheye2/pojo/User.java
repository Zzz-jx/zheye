package a7.zheye2.pojo;

import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.text.SimpleDateFormat;
import java.util.*;

@Entity
@Data
@NoArgsConstructor(force=true)
@Table(name="user")
public class User implements UserDetails{
    private static final long serialVersionUID = 1L;
    private SimpleDateFormat date = new SimpleDateFormat("MM-dd");
    private SimpleDateFormat datet = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;
    @NotNull
    @Column(unique = true)
    @Size(min=3)
    private String username;
    @NotNull
    @Size(min=6)
    private String password;
    @Pattern(regexp = "^(.+)@(.+)$",message = "邮箱的格式不合法")
    private String mail;
    @NotNull
    @Size(min=11,max=11,message = "请输入11位有效手机号")
    private String phone;
    private String sex;
    private Date birthday;
    private Date datetime; // 注册时间
    private String userStatement; // 用户个人说明
    // n对1 user-usertype
    @ManyToOne(targetEntity = UserType.class)
    @JoinColumn(name = "userTypeID",referencedColumnName = "userTypeID")
    private UserType type;
    public User(String username,String password,String phone){
        this.username = username;
        this.password = password;
        this.phone = phone;
    }
    @PrePersist
    void set(){
        this.datetime = new Date();
        this.type=new UserType("USER");
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<SimpleGrantedAuthority> authorityList = new ArrayList<>();
        authorityList.add(new SimpleGrantedAuthority(getType().getUserTypeName()));
        return authorityList;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
