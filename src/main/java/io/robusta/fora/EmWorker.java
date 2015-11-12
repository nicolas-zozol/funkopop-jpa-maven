package io.robusta.fora;

import javax.persistence.EntityManager;

@FunctionalInterface
public interface EmWorker {

	public void work(EntityManager em);
}
