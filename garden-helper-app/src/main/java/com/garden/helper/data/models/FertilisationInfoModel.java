package com.garden.helper.data.models;

import java.sql.Timestamp;
import java.util.List;

import org.springframework.hateoas.Link;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonRootName;

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
@JsonRootName(value = "fertilisationInfo")
@Relation(collectionRelation = "fertilisationInfoRecord", itemRelation = "fertilisationInfo")
@JsonInclude(Include.NON_NULL)
public class FertilisationInfoModel extends RepresentationModel<FertilisationInfoModel> {
	
	private Long id;
	
	private Timestamp fertilizedAt;
	
	private List<FertilizerModel> fertilizers;

	public FertilisationInfoModel(Iterable<Link> initialLinks) {
		super(initialLinks);
	}

	public FertilisationInfoModel(Link initialLink) {
		super(initialLink);
	}

}
