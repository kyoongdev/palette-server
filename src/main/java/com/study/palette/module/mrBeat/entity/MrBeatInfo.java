package com.study.palette.module.mrBeat.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.study.palette.common.enums.mrBeat.MrBeatGenreType;
import com.study.palette.common.enums.mrBeat.MrBeatMoodType;
import com.study.palette.common.enums.mrBeat.MrBeatSalesType;
import com.study.palette.module.users.entity.Users;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.data.annotation.CreatedDate;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class MrBeatInfo {

  @Id
  @GeneratedValue(generator = "uuid2")
  @GenericGenerator(name = "uuid2", strategy = "uuid2")
  @Column(columnDefinition = "BINARY(16)")
  private UUID id;

  @Column(length = 50)
  private String serviceName;

  private MrBeatMoodType mood;

  private MrBeatSalesType salesType;

  private MrBeatGenreType genre;

  private boolean serviceStatus;

  @CreationTimestamp
  @Column(columnDefinition = "TIMESTAMP")
  private LocalDateTime createdAt;

  @OneToMany(mappedBy = "mrBeatInfo", cascade = CascadeType.ALL, orphanRemoval = true)
  private List<MrBeatLicenseInfo> mrBeatLicenseInfo = new ArrayList<>();

  @OneToMany(mappedBy = "mrBeatInfo", cascade = CascadeType.ALL, orphanRemoval = true)
  private List<MrBeatContact> mrBeatContact = new ArrayList<>();


  @OneToOne(mappedBy = "mrBeatInfo", cascade = CascadeType.ALL, fetch = FetchType.EAGER, orphanRemoval = true)
  private MrBeatFile mrBeatFile;

  @OneToOne(mappedBy = "mrBeatInfo", cascade = CascadeType.ALL, fetch = FetchType.EAGER, orphanRemoval = true)
  private MrBeatMusicFile mrBeatMusicFile;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "usersId")
  @JsonIgnore
  private Users users;


}
