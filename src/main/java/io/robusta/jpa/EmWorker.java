package io.robusta.jpa;

import javax.persistence.EntityManager;

@FunctionalInterface
public interface EmWorker {

	public Object work(EntityManager em);
}
