package com.study.palette.module.mrBeat.dto.contact;

import com.study.palette.module.mrBeat.entity.MrBeatContact;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MrBeatContactDto {

  private String id;
  private int type;
  private String content;

  public MrBeatContactDto(MrBeatContact mrBeatContact) {
    this.id = mrBeatContact.getId().toString();
    this.type = mrBeatContact.getType().getContact();
    this.content = mrBeatContact.getContent();
  }

}
