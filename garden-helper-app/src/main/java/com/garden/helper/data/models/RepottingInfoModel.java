package com.garden.helper.data.models;

import java.sql.Timestamp;

import org.springframework.hateoas.Link;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonRootName;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
@JsonRootName(value = "repottingInfo")
@Relation(collectionRelation = "repottingInfos", itemRelation = "repottingInfo")
@JsonInclude(Include.NON_NULL)
public class RepottingInfoModel extends RepresentationModel<RepottingInfoModel> {
	
	private Long id;
	
	private String note;
	
	private Timestamp repotedAt;
	
	private Timestamp nextRepottingDate;
	
	private RepottingInfoModel(Builder builder) {
		this.id = builder.id;
		this.repotedAt = builder.repotedAt;
		this.note = builder.note;
		this.nextRepottingDate = builder.nextRepottingDate;
	}

	public RepottingInfoModel(Iterable<Link> initialLinks) {
		super(initialLinks);
	}

	public RepottingInfoModel(Link initialLink) {
		super(initialLink);
	}

	public static IId builder() {
		return new Builder();
	}

	public interface IId {
		public IRepotedAt id(Long id);
	}

	public interface IRepotedAt {
		public IBuild repotedAt(Timestamp repotedAt);
	}

	public interface IBuild {
		public IBuild note(String note);

		public IBuild nextRepottingDate(Timestamp nextRepottingDate);

		public RepottingInfoModel build();
	}

	public static final class Builder implements IId, IRepotedAt, IBuild {
		private Long id;
		private Timestamp repotedAt;
		private String note;
		private Timestamp nextRepottingDate;

		private Builder() {
		}

		@Override
		public IRepotedAt id(Long id) {
			this.id = id;
			return this;
		}

		@Override
		public IBuild repotedAt(Timestamp repotedAt) {
			this.repotedAt = repotedAt;
			return this;
		}

		@Override
		public IBuild note(String note) {
			this.note = note;
			return this;
		}

		@Override
		public IBuild nextRepottingDate(Timestamp nextRepottingDate) {
			this.nextRepottingDate = nextRepottingDate;
			return this;
		}

		@Override
		public RepottingInfoModel build() {
			return new RepottingInfoModel(this);
		}
	}

}
