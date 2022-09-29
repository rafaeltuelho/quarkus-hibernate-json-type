package org.acme;

import static javax.ws.rs.core.MediaType.APPLICATION_JSON;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.core.UriInfo;

import org.jboss.logging.Logger;

import io.smallrye.mutiny.Uni;

@Path("/hero")
public class HeroResource {
	private final Logger logger;
	private final HeroService heroService;

	public HeroResource(Logger logger, HeroService heroService) {
		this.logger = logger;
		this.heroService = heroService;
	}

	@GET
	@Path("/random")
    // @Blocking
	public Uni<Response> getRandomHero() {
		return this.heroService.findRandomHero()
			.onItem().ifNotNull().transform(h -> {
				this.logger.debugf("Found random hero: %s", h);
				return Response.ok(h).build();
			})
			.onItem().ifNull().continueWith(() -> {
				this.logger.debug("No random villain found");
				return Response.status(Status.NOT_FOUND).build();
			});
	}

	@GET
    // @Blocking
	public Uni<List<Hero>> getAllHeroes(@QueryParam("name_filter") Optional<String> nameFilter) {
    return nameFilter
      .map(this.heroService::findAllHeroesHavingName)
      .orElseGet(this.heroService::findAllHeroes)
      .invoke(heroes -> this.logger.debugf("Total number of heroes: %d", heroes.size()));
	}

	@GET
	@Path("/{id}")
    // @Blocking
	public Uni<Response> getHero(@PathParam("id") Long id) {
		return this.heroService.findHeroById(id)
			.onItem().ifNotNull().transform(h -> {
				this.logger.debugf("Found hero: %s", h);
				return Response.ok(h).build();
			})
			.onItem().ifNull().continueWith(() -> {
				this.logger.debugf("No hero found with id %d", id);
				return Response.status(Status.NOT_FOUND).build();
			});
	}

	@POST
	@Consumes(APPLICATION_JSON)
    // @Blocking
	public Uni<Response> createHero(@Valid @NotNull Hero hero, @Context UriInfo uriInfo) {
		return this.heroService.persistHero(hero)
			.map(h -> {
				var uri = uriInfo.getAbsolutePathBuilder().path(Long.toString(h.getId())).build();
				this.logger.debugf("New Hero created with URI %s", uri.toString());
				return Response.created(uri).build();
			});
	}

    @GET
    @Path("/hello")
    @Produces(MediaType.TEXT_PLAIN)
    public String hello() {
        return "Hello from RESTEasy Reactive";
    }

}