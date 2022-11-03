package com.garden.helper.repositories.specifications;

import org.springframework.data.jpa.domain.Specification;

import com.garden.helper.data.entities.Bonsai;
import com.garden.helper.data.enums.EBonsaiStyle;
import com.garden.helper.data.enums.EBonsaiType;

public class BonsaiSpecifications {
	
	public static Specification<Bonsai> withBonsaiType(EBonsaiType bonsaiType) {
		return (root, query, builder) -> {
			return builder.equal(root.get("type"), bonsaiType);
		};
	}
	
	public static Specification<Bonsai> withBonsaiStyle(EBonsaiStyle bonsaiStyle) {
		return (root, query, builder) -> {
			return builder.equal(root.get("style"), bonsaiStyle);
		};
	}
	
	public static Specification<Bonsai> belongsToUserWithId(Long userId) {
		return (root, query, builder) -> {
			return builder.equal(root.join("user").get("id"), userId);
		};
	}

}
