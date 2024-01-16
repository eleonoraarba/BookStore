package org.launchcode.Project2Is.Proiect2Is.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Book {
    @GeneratedValue
    @Id
    private Long id;

    private String title;
    private String author;
    private LocalDate publishedDate;
    private Long stock;
    private Float price;

}
