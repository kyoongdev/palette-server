package com.study.palette.module.mrBeat.entity;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.study.palette.module.users.entity.Users;
import java.time.LocalDate;
import java.util.UUID;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Builder
public class MrBeatRequest {

  @Id
  @GeneratedValue(generator = "uuid2")
  @GenericGenerator(name = "uuid2", strategy = "uuid2")
  @Column(columnDefinition = "BINARY(16)")
  private UUID id;

  private LocalDate createAt;

  @ManyToOne
  @JoinColumn(name = "userId")
  @JsonIgnore
  private Users users;

  @ManyToOne
  @JoinColumn(name = "mrBeatInfoId")
  @JsonIgnore
  private MrBeatInfo mrBeatInfo;

}
