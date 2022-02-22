package org.hibernate.presentation.type.convert;

import org.hibernate.presentation.type.FizzyWidget;

import jakarta.persistence.Convert;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;

/**
 * @author Steve Ebersole
 */
@Entity
public class ConversionEntity {
	@Id
	private Integer id;
	private String name;
	@Convert( converter = FizzyWidgetConverter.class )
	private FizzyWidget widget;

	private ConversionEntity() {
		// for Hibernate use
	}

	public ConversionEntity(Integer id, String name) {
		this.id = id;
		this.name = name;
	}

	public ConversionEntity(Integer id, String name, FizzyWidget widget) {
		this.id = id;
		this.name = name;
		this.widget = widget;
	}

	public Integer getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public FizzyWidget getWidget() {
		return widget;
	}

	public void setWidget(FizzyWidget widget) {
		this.widget = widget;
	}
}