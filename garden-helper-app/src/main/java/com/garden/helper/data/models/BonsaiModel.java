package com.garden.helper.data.models;

import java.sql.Timestamp;

import org.springframework.hateoas.Link;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonRootName;
import com.garden.helper.data.enums.EBonsaiStyle;
import com.garden.helper.data.enums.EBonsaiType;

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
@JsonRootName(value = "bonsai")
@Relation(collectionRelation = "bonsais", itemRelation = "bonsai")
@JsonInclude(Include.NON_NULL)
public class BonsaiModel extends RepresentationModel<BonsaiModel> {
	
	private Long id;
	
	private String name;
	
	private byte[] thumbnailImage;
	
	private EBonsaiStyle bonsaiStyle;
	
	private EBonsaiType bonsaiType;
	
	private Timestamp creationDate;
	
	private Timestamp lastUpdatedAt;
	
	private RepottingInfoModel latestRepottingInfo;

	public BonsaiModel(Long id, String name, byte[] thumbnailImage, 
			EBonsaiStyle bonsaiStyle, EBonsaiType bonsaiType, Timestamp creationDate, Timestamp lastUpdatedAt) {
		super();
		this.id = id;
		this.name = name;
		this.thumbnailImage = thumbnailImage;
		this.bonsaiStyle = bonsaiStyle;
		this.bonsaiType = bonsaiType;
		this.creationDate = creationDate;
	}

	public BonsaiModel(Iterable<Link> initialLinks) {
		super(initialLinks);
	}

	public BonsaiModel(Link initialLink) {
		super(initialLink);
	}

}
