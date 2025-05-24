package com.ecommerce_yt.Model;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Data
public class Home {

    private List<HomeCategory> grid;

    private List<HomeCategory> shopByCategories;

    private List<HomeCategory> electricalCategories;

    private List<HomeCategory> dealCategories;

    private List<HomeCategory> deals;
}
