package br.com.rpg.dao;

import br.com.caelum.vraptor.ioc.Component;
import br.com.rpg.model.Permission;
import br.com.rpg.model.UserGroup;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.Session;

/**
 * @author Guilherme Felipe de C. G. da Silva
 * @author Bruno Volpini
 */
@Component
public class UserGroupDao extends Dao<UserGroup> {

	private final Session session;

	public UserGroupDao(Session session) {
		super(session, UserGroup.class);
		this.session = session;
	}

	public void copyUserGroupPemission(Long permissionFrom, Long permissionTo) {
		UserGroup userGroupPermissionFrom = buscar(permissionFrom);
		List<Permission> permissions = new ArrayList<>();
		for (Permission permission : userGroupPermissionFrom.getPermissions()) {
			Permission newPermission = permission.clone();
			permissions.add(newPermission);
		}
		UserGroup userGroupPermissionTo = buscar(permissionTo);
		deleteOldPermissions(userGroupPermissionTo);
		userGroupPermissionTo.setPermissions(permissions);
		atualizar(userGroupPermissionTo);
	}

	public void deleteOldPermissions(UserGroup userGroup) {
		for (Permission permission : userGroup.getPermissions()) {
			session.delete(permission);
		}
		userGroup.getPermissions().clear();
		session.flush();
	}
}