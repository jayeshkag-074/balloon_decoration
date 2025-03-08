package com.amstech.balloon.decoration.system.converter.modal;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.amstech.balloon.decoration.system.entity.Decoration;
import com.amstech.balloon.decoration.system.modal.response.DecorationDetailResponseModal;
import com.amstech.balloon.decoration.system.modal.response.DecorationResponseModal;

@Component
public class DecorationEntityToModalConverter {

	public List<DecorationResponseModal> findAll(List<Decoration> decorations){
		List<DecorationResponseModal> decorationResponseModals = new ArrayList<DecorationResponseModal>();
		for (Decoration decoration : decorations) {
			DecorationResponseModal  decorationResponseModal =new DecorationResponseModal();
			decorationResponseModal.setId(decoration.getId());
			decorationResponseModal.setName(decoration.getName());
			decorationResponseModal.setPrice(decoration.getPrice());
			decorationResponseModal.setDescription(decoration.getDescription());
			decorationResponseModal.setStatusName(decoration.getStatus().getName());
			
			decorationResponseModals.add(decorationResponseModal);
		}
		
		return decorationResponseModals;
	}

	public DecorationDetailResponseModal findById (Decoration decoration) {

		DecorationDetailResponseModal decorationDetailResponseModal = new DecorationDetailResponseModal();
	
		decorationDetailResponseModal.setId(decoration.getId());
		decorationDetailResponseModal.setLocationName(decoration.getLocation().getName());
		decorationDetailResponseModal.setCityName(decoration.getLocation().getCity().getName());
		decorationDetailResponseModal.setFirstname(decoration.getDecorater().getUser().getFirstName());
		decorationDetailResponseModal.setLastname(decoration.getDecorater().getUser().getLastName());
		decorationDetailResponseModal.setDescription(decoration.getDescription());
		decorationDetailResponseModal.setImgURL(decoration.getImageUrl());
		decorationDetailResponseModal.setPrice(decoration.getPrice());
		decorationDetailResponseModal.setStateName(decoration.getLocation().getCity().getState().getName());
		decorationDetailResponseModal.setStatusName(decoration.getStatus().getName());
	
		 return decorationDetailResponseModal;
	}
 
}
