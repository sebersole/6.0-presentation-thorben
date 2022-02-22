package org.hibernate.presentation.type.registration;

import org.hibernate.annotations.JavaTypeRegistration;
import org.hibernate.presentation.type.FizzyWidget;
import org.hibernate.presentation.type.FizzyWidgetJavaType;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

/**
 * Demonstrates the use of `@JavaTypeRegistration`
 *
 * @author Steve Ebersole
 */
@Entity
@JavaTypeRegistration( javaType = FizzyWidget.class, descriptorClass = FizzyWidgetJavaType.class )
public class RegistrationEntity {
	@Id
	private Integer id;
	private String name;
	private FizzyWidget widget;

	private RegistrationEntity() {
		// for Hibernate use
	}

	public RegistrationEntity(Integer id, String name) {
		this.id = id;
		this.name = name;
	}

	public RegistrationEntity(Integer id, String name, FizzyWidget widget) {
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