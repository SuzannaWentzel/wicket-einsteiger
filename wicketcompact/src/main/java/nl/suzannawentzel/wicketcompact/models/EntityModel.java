package nl.suzannawentzel.wicketcompact.models;

import nl.suzannawentzel.wicketcompact.entities.BaseEntity;
import nl.suzannawentzel.wicketcompact.services.BaseService;
import nl.suzannawentzel.wicketcompact.services.ServiceRegistry;
import org.apache.wicket.model.LoadableDetachableModel;

public class EntityModel<T extends BaseEntity, S extends BaseService<T>> extends LoadableDetachableModel<T>
{
	private Long id;
	private Class<S> serviceClass;

	public EntityModel(Long id, Class<S> serviceClass) {
		this.id = id;
		this.serviceClass = serviceClass;
	}

	@Override
	protected T load()
	{
		return ServiceRegistry.get(serviceClass).get(id);
	}
}
