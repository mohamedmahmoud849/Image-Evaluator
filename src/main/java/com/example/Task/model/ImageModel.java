package com.example.Task.model;

import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import org.hibernate.mapping.Value;

@Entity
@Table(name="Images_table")
public class ImageModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id ;
    String Description;
    String category;

    @Nullable
    Boolean accepted =null;

    @Lob
    @Column(columnDefinition = "bigint")
    String image;
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getImage() {
        return image;
    }
    public void setImage(String image) {
        this.image = image;
    }

    @Override
    public String toString() {
        return "ImageModel{" +
                "id=" + id +
                ", Description='" + Description + '\'' +
                ", category='" + category + '\'' +
                ", image='" + image + '\'' +
                '}';
    }


}
