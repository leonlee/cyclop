package org.cyclop.service.queryprotocoling.impl;

import net.jcip.annotations.NotThreadSafe;
import org.cyclop.model.QueryFavourites;
import org.cyclop.service.queryprotocoling.FavouritesService;
import org.cyclop.validation.EnableValidation;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;

import javax.inject.Named;

/** @author Maciej Miklas */
@NotThreadSafe
@Named
@Scope(value = "session", proxyMode = ScopedProxyMode.TARGET_CLASS)
@EnableValidation
class FavouritesServiceImpl extends AbstractQueryProtocolingService<QueryFavourites> implements FavouritesService {

	@Override
	protected Class<QueryFavourites> getClazz() {
		return QueryFavourites.class;
	}

	@Override
	protected QueryFavourites createEmpty() {
		return new QueryFavourites();
	}

}
