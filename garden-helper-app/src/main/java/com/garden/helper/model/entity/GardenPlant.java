package com.garden.helper.model.entity;

import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

@Entity
@Table(name = "Garden_plant")
@PrimaryKeyJoinColumn(name = "Plant_id")
public class GardenPlant extends Plant {

}
