package com.ll.exam.spring_restapi.app.article.entiry;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.ll.exam.spring_restapi.app.base.entity.BaseEntity;
import com.ll.exam.spring_restapi.app.member.entity.Member;
import lombok.*;
import lombok.experimental.SuperBuilder;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
@ToString(callSuper = true)
public class Article extends BaseEntity {

    private String subject;
    private String content;
    @ManyToOne
    private Member author;

    public Article(long id) {
        super(id);
    }
}