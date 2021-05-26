package com.app.repos;

import com.app.domain.Domain;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.data.repository.PagingAndSortingRepository;

@NoRepositoryBean
public interface Repo<T, ID> extends PagingAndSortingRepository<T, ID> {
}
