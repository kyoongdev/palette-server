package com.study.palette.module.albumArt.dto.contact;


import com.study.palette.common.enums.Contact;
import com.study.palette.module.albumArt.entity.AlbumArtContact;
import com.study.palette.module.mixMastering.entity.MixMasteringContact;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class AlbumArtContactDto {
    @Schema(description = "아이디")
    private String id;

    @Schema(description = "종류", example = "1 : 전화번호, 2 : 이메일, 3 : 카카오톡, 4 : 인스타그램, 5 : 기타")
    private Contact type;

    @Schema(description = "내용")
    private String content;

    public AlbumArtContactDto(AlbumArtContact albumArtContact) {
        this.id = albumArtContact.getId().toString();
        this.type = Contact.findContact(albumArtContact.getType());
        this.content = albumArtContact.getContent();
    }
}
