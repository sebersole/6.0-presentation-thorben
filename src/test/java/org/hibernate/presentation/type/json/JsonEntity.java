package org.hibernate.presentation.type.json;

import org.hibernate.annotations.JavaType;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.presentation.type.FizzyWidget;
import org.hibernate.presentation.type.FizzyWidgetJavaType;
import org.hibernate.type.SqlTypes;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

/**
 * @author Steve Ebersole
 */
@Entity
public class JsonEntity {
	@Id
	private Integer id;
	private String name;
	@JavaType( FizzyWidgetJavaType.class )
	@JdbcTypeCode(SqlTypes.JSON )
	private FizzyWidget widget;

	private JsonEntity() {
		// for Hibernate use
	}

	public JsonEntity(Integer id, String name) {
		this.id = id;
		this.name = name;
	}

	public JsonEntity(Integer id, String name, FizzyWidget widget) {
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