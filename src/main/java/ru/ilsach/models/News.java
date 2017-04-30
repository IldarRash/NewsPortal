package ru.ilsach.models;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;

/**
 * Created by ilsac on 30.04.2017.
 */
@Entity
@Data
@Table(name = "news")
public class News {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @NotNull
    @Size(min=2, max=30)
    @Column(name = "title")
    public String title;
    @NotNull
    @Size(min=2, max=30)
    @Column(name = "category")
    public String category;
    @NotNull
    @Size(min=2, max=100)
    @Column(name = "content")
    public String content;

    @Temporal(TemporalType.DATE)
    @Column(name = "datatime")
    public Date date;


}
