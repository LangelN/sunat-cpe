package io.github.carlosthe19916.core.models.jpa;

import io.github.carlosthe19916.core.models.UserModel;
import io.github.carlosthe19916.core.models.UserProvider;
import io.github.carlosthe19916.core.models.jpa.entities.UserEntity;
import io.github.carlosthe19916.core.models.utils.ModelUtils;

import javax.enterprise.context.ApplicationScoped;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Transactional
@ApplicationScoped
public class JpaUserProvider implements UserProvider {

    @PersistenceContext
    private EntityManager em;

    @Override
    public UserModel addUser(String username, String identityId, String identityProvider) {
        UserEntity entity = new UserEntity();
        entity.setId(ModelUtils.generateId());
        entity.setUsername(username);
        entity.setIdentityId(identityId);
        entity.setIdentityProvider(identityProvider);
        entity.setJoinedOn(new Date());
        em.persist(entity);
        return new UserAdapter(entity);
    }

    @Override
    public Optional<UserModel> getUser(String id) {
        UserEntity userEntity = em.find(UserEntity.class, id);
        if (userEntity == null) return Optional.empty();
        return Optional.of(new UserAdapter(userEntity));
    }

    @Override
    public Optional<UserModel> getUserByUsername(String username) {
        TypedQuery<UserEntity> query = em.createNamedQuery("GetUserByUsername", UserEntity.class);
        query.setParameter("username", username);
        List<UserEntity> resultList = query.getResultList();
        if (resultList.isEmpty()) {
            return Optional.empty();
        } else if (resultList.size() == 1) {
            return Optional.of(new UserAdapter(resultList.get(0)));
        } else {
            throw new IllegalStateException("Found more than one user with equal username:" + username);
        }
    }

    @Override
    public Optional<UserModel> getUserByIdentityId(String identityId) {
        TypedQuery<UserEntity> query = em.createNamedQuery("GetUserByIdentityId", UserEntity.class);
        query.setParameter("identityId", identityId);
        List<UserEntity> resultList = query.getResultList();
        if (resultList.isEmpty()) {
            return Optional.empty();
        } else if (resultList.size() == 1) {
            return Optional.of(new UserAdapter(resultList.get(0)));
        } else {
            throw new IllegalStateException("Found more than one user with equal identityId:" + identityId);
        }
    }
}
