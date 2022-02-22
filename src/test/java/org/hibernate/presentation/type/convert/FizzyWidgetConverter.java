package org.hibernate.presentation.type.convert;

import org.hibernate.presentation.type.FizzyWidget;

import jakarta.persistence.AttributeConverter;

/**
 * @author Steve Ebersole
 */
public class FizzyWidgetConverter implements AttributeConverter<FizzyWidget, String> {
	@Override
	public String convertToDatabaseColumn(FizzyWidget widget) {
		return widget == null ? null : widget.getSomeInternalState();
	}

	@Override
	public FizzyWidget convertToEntityAttribute(String state) {
		return state == null ? null : new FizzyWidget( state );
	}
}
