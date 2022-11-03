package com.garden.helper.data.models;

import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonRootName;
import com.garden.helper.data.enums.EFertilizerType;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
@JsonRootName(value = "fertilizer")
@Relation(collectionRelation = "fertilizers", itemRelation = "fertilizer")
@JsonInclude(Include.NON_NULL)
public class FertilizerModel extends RepresentationModel<FertilizerModel> {
	
	private Long id;
	
	private String name;
	
	private EFertilizerType type;
	
	private int nitrogen;
	
	private int phosphorus;
	
	private int potassium;

}
