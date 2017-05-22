package com.vehicle.rentservice.persistence.specification;

import java.util.Objects;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;

import com.vehicle.rentservice.model.entity.Vehicle;
import com.vehicle.rentservice.model.enumerations.BodyTypes;

public class VehicleSearchSpecification {

	public static Specification<Vehicle> isInCountry(String name) {
		return new Specification<Vehicle>() {
			@Override
			public Predicate toPredicate(Root<Vehicle> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				return cb.equal(root.get("location").get("city").get("country").get("name"), name);
			}
		};
	}

	public static Specification<Vehicle> isInCity(String name) {
		return new Specification<Vehicle>() {
			@Override
			public Predicate toPredicate(Root<Vehicle> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				return cb.equal(root.get("location").get("city").get("name"), name);
			}
		};
	}

	public static Specification<Vehicle> isInLocation(String name) {
		return new Specification<Vehicle>() {
			@Override
			public Predicate toPredicate(Root<Vehicle> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				return cb.equal(root.get("location").get("name"), name);
			}
		};
	}

	public static Specification<Vehicle> withBodyType(BodyTypes name) {
		return new Specification<Vehicle>() {
			@Override
			public Predicate toPredicate(Root<Vehicle> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				return cb.equal(root.get("bodyType").get("name"), name);
			}
		};
	}

	public static Specification<Vehicle> withBrand(String name) {
		return new Specification<Vehicle>() {
			@Override
			public Predicate toPredicate(Root<Vehicle> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				return cb.equal(root.get("brand").get("name"), name);
			}
		};
	}
	
	public static Specification<Vehicle> unordered() {
		return new Specification<Vehicle>() {
			@Override
			public Predicate toPredicate(Root<Vehicle> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				return cb.and(cb.isNull(root.get("currentContract")), cb.equal(root.get("preordered"), false));
			}
		};
	}
	
	public static Specification<Vehicle> notArchived() {
		return new Specification<Vehicle>() {
			@Override
			public Predicate toPredicate(Root<Vehicle> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				return cb.equal(root.get("archived"), false);
			}
		};
	}

	public static Specification<Vehicle> betweenYears(Integer fromYear, Integer toYear) {
		return new Specification<Vehicle>() {
			@Override
			public Predicate toPredicate(Root<Vehicle> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				if (!Objects.isNull(fromYear) && !Objects.isNull(toYear)) {
					return cb.between(root.get("manufactureYear"), fromYear, toYear);
				} else if(!Objects.isNull(fromYear) && Objects.isNull(toYear)) {
					return cb.gt(root.get("manufactureYear"), fromYear);
				} else if(Objects.isNull(fromYear) && !Objects.isNull(toYear)) {
					return cb.lt(root.get("manufactureYear"), toYear);
				} else {
					return null;
				}
			}
		};
	}

}
