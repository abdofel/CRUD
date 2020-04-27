package com.example.demo.entities;

import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Date;


@Entity
@Data
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Patient  {
    @Id  @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long  id ;
    @NonNull
    @Size(min =2,max = 20, message = "errer")
    private String cin;

    private String name;

 @Temporal(TemporalType.DATE)
 @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date date;
    private boolean malade;


}
