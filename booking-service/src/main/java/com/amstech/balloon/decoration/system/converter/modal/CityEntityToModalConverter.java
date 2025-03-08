package com.amstech.balloon.decoration.system.converter.modal;

import java.util.ArrayList;

import java.util.List;

import org.springframework.stereotype.Component;

import com.amstech.balloon.decoration.system.entity.City;
import com.amstech.balloon.decoration.system.modal.response.CityResponseModal;

@Component
public class CityEntityToModalConverter {
	public List<CityResponseModal> findAll(List<City> cities){
		List<CityResponseModal> cityResponseModals = new ArrayList<>();
		for (City city : cities) {
			CityResponseModal cityResponseModal = new CityResponseModal();
			cityResponseModal.setId(city.getId());
			cityResponseModal.setName(city.getName());
			cityResponseModals.add(cityResponseModal);
		}
		return cityResponseModals;
	}
	
}
