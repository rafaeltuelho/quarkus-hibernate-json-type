package org.acme;

import java.util.List;
import java.util.Random;

import javax.enterprise.context.ApplicationScoped;

import io.quarkus.hibernate.reactive.panache.PanacheRepository;
// import io.quarkus.hibernate.orm.panache.PanacheRepository;
import io.smallrye.mutiny.Uni;

/**
 * Repository class for managing data operations on a {@link Hero}.
 */
@ApplicationScoped
public class HeroRepository implements PanacheRepository<Hero> {
	public Uni<Hero> findRandom() {
		// return Uni.createFrom().item(count())
		return count()
			.map(count -> (count > 0) ? count : null)
			.onItem().ifNotNull().transform(count -> new Random().nextInt(count.intValue()))
			.onItem().ifNotNull().transformToUni(randomHero -> findAll().page(randomHero, 1).firstResult());
	}

  // public Uni<List<Hero>> listAllWhereNameLike(String name) {
	public Uni<List<Hero>> listAllWhereNameLike(String name) {
    return (name != null) ?
					//  Uni.createFrom().item(list("LOWER(name) LIKE CONCAT('%', ?1, '%')", name.toLowerCase())) :
          //  Uni.createFrom().item(List::of);
					 list("LOWER(name) LIKE CONCAT('%', ?1, '%')", name.toLowerCase()) :
           Uni.createFrom().item(List::of);
  }
}
