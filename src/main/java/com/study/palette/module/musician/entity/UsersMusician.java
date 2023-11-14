package com.study.palette.module.musician.entity;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.study.palette.module.users.entity.Users;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Builder
public class UsersMusician {

    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    private String id;

    @OneToMany(mappedBy = "usersMusician", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    private List<UsersPosition> usersPosition = new ArrayList<>();

    @OneToMany(mappedBy = "usersMusician", cascade = CascadeType.ALL, fetch = FetchType.EAGER, orphanRemoval = true)
    private List<UsersSns> usersSns = new ArrayList<>();

    @Column(length = 100)
    private String nickName;

    @Column(length = 20)
    private String name;

    private int groupType;

    @Column(length = 3000)
    private String introduction;

    private boolean isAuthorized;

    private LocalDate cratedAt;

    @OneToOne
    @JsonIgnore
    @JoinColumn(name = "usersId")
    private Users users;

    public void setUsersPosition(List<UsersPosition> usersPosition) {
        this.usersPosition = usersPosition;
    }

    public void setUsersSns(List<UsersSns> usersSns) {
        this.usersSns = usersSns;
    }

}
