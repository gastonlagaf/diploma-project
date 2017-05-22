package com.vehicle.rentservice.ui.converter;

public interface RequestConverter<T, V> {
	
	T convertRequest(V request);
	
}
