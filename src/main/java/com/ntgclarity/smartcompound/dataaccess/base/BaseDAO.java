package com.ntgclarity.smartcompound.dataaccess.base;

import java.lang.reflect.Field;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.transaction.Transactional;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ntgclarity.smartcompound.common.base.BaseEntity;
import com.ntgclarity.smartcompound.common.entity.Compound;
import com.ntgclarity.smartcompound.common.utils.Utils;

@Repository
@Transactional
public abstract class BaseDAO {

	@Autowired
	private SessionFactory sessionFactory;

	public Session getCurrentSession() {
		return sessionFactory.getCurrentSession();
	}

	public Object saveOrUpdate(Object entity) {
		getCurrentSession().saveOrUpdate(entity);

		//System.out.println("Object inserted successfully");
		return entity;
	}
	
	
	public List getAll(Class cls) {
		Query query = getCurrentSession().createQuery(
				"from " + cls.getCanonicalName());
		List result = query.list();
		return result;
	}

	
	
	public Object get(Class<? extends BaseEntity> clazz, Long id) {
		return getCurrentSession().get(clazz, id);
	}

	public List load(Class cls, int first, int pageSize, String sortField,
			Boolean ascending, Map<String, Object> filters) {

		Query query = constructQuery(cls, first, pageSize, sortField,
				ascending, filters, false);
		query.setFirstResult(first);
		query.setMaxResults(pageSize);
		List<Object> result = query.list();
		return result;

	}

	public int getNumOfRows(Class cls, Map<String, Object> filters) {
		int count = 0;
		Query query = constructQuery(cls, 0, 0, null, false, filters, true);

		Object result = query.uniqueResult();
		if (result != null && result instanceof Long) {
			count = ((Long) result).intValue();

		}
		return count;
	}

	public List getAllByCompound(Class cls, Compound compound) {

		Query query = getCurrentSession().createQuery(
				"from " + cls.getCanonicalName()
						+ " x where x.compound =:compound");
		query.setParameter("compound", compound);
		List result = query.list();
		return result;
	}

	// public String getQueryIfLong() {
	//
	// filtersObject = new Long(filtersObject.toString());
	// String currentAlias = alias += "x";
	//
	// paramValues.put(currentAlias, filtersObject);
	// alias = currentAlias;
	// return " and o." + filtersKey + " = :" + currentAlias;
	//
	// }

	// public String getQueryIfInteger() {
	//
	// filtersObject = new Integer(filtersObject.toString());
	// String currentAlias = alias += "x";
	//
	// paramValues.put(currentAlias, filtersObject);
	// alias = currentAlias;
	// return " and o." + filtersKey + " = :" + currentAlias;
	// }

	// public String getQueryIfShort() {
	//
	// filtersObject = new Short(filtersObject.toString());
	// String currentAlias = alias += "x";
	//
	// paramValues.put(currentAlias, filtersObject);
	// alias = currentAlias;
	// return " and o." + filtersKey + " = :" + currentAlias;
	// }

	// public String getQueryIfDouble() {
	//
	// filtersObject = new Double(filtersObject.toString());
	// String currentAlias = alias += "x";
	//
	// paramValues.put(currentAlias, filtersObject);
	// alias = currentAlias;
	// return " and o." + filtersKey + " = :" + currentAlias;
	// }
	//
	// public String getQueryIfFloat() {
	//
	// filtersObject = new Float(filtersObject.toString());
	//
	// String currentAlias = alias += "x";
	//
	// paramValues.put(currentAlias, filtersObject);
	// alias = currentAlias;
	// return " and o." + filtersKey + " = :" + currentAlias;
	// }

	// public String getQueryIfDate() {
	// Date endOfDay = Utils.getEndOfDay((Date) filtersObject);
	// Date startOfDay = Utils.getStartOfDay((Date) filtersObject);
	//
	// return " and o." + filtersKey + " between " + " ' " + startOfDay
	// + " ' " + " and " + " ' " + endOfDay + " ' ";
	//
	// }

	// public String getQueryIfString() {
	// // filtersObject = filtersObject.toString();
	// // //System.out.println("in string" + filtersObject);
	// // filtersObject = "'%" + filtersObject + "%'";
	// // String currentAlias = alias += "x";
	// // paramValues.put(currentAlias, filtersObject);
	// // alias = currentAlias;
	// // return " and o." + filtersKey + " like :" + currentAlias;
	// }
	//
	// public String getQueryIfWrapper(Class type) {
	// if (type == Long.class) {
	// return getQueryIfLong();
	// }
	// if (type == Integer.class) {
	// return getQueryIfInteger();
	// }
	// if (type == Float.class) {
	// return getQueryIfFloat();
	// }
	// if (type == Double.class) {
	// return getQueryIfDouble();
	// }
	// if (type == Short.class) {
	// return getQueryIfShort();
	// }
	// return null;
	// }

	public Query constructQuery(Class cls, int first, int pageSize,
			String sortField, Boolean ascending, Map<String, Object> filters,
			Boolean isCount) {

		String filtersKey;
		Object filtersObject;
		Map<String, Object> paramValues = new HashMap();
		String alias = "x";

		String queryString;
		if (isCount) {
			queryString = "select count(o) from " + cls.getCanonicalName()
					+ " o where 1=1 ";
		} else {
			queryString = "select o from " + cls.getCanonicalName()
					+ " o where 1=1 ";
			if (sortField != null && ascending != null) {
				queryString += (ascending) ? " order by o." + sortField
						: " order by o." + sortField + " desc";
			}

		}

		Field field = null;
		Class c = null;
		if (filters.size() != 0) {
			for (Map.Entry<String, Object> entry : filters.entrySet()) {
				filtersKey = entry.getKey();
				filtersObject = entry.getValue();

				try {

					//System.out.println(filtersKey);
					String[] keys = filtersKey.split("\\.");

					for (int i = 0; i < keys.length; i++) {

						if ((keys.length - 1) == i) {
							if (c == null) {
								c = cls;
							}

							field = c.getDeclaredField(keys[i]);
						} else {
							c = cls.getDeclaredField(keys[i]).getType();
							field = c.getDeclaredField(keys[i + 1]);

						}
					}
					Class type = field.getType();

					if (type == String.class) {
						filtersObject = filtersObject.toString();
						//System.out.println("in string" + filtersObject);
						String currentAlias = alias += "x";
						paramValues.put(currentAlias, filtersObject);
						alias = currentAlias;
						queryString += " and o." + filtersKey + " like :"
								+ currentAlias;
					}
					else if (type == Date.class) {

						Date endOfDay = Utils.getEndOfDay((Date) filtersObject);
						Date startOfDay = Utils
								.getStartOfDay((Date) filtersObject);

						queryString += " and o." + filtersKey + " between "
								+ " ' " + startOfDay + " ' " + " and " + " ' "
								+ endOfDay + " ' ";
					}
					else if (Utils.isWrapperClass(type)) 
					{

						// //////////////
						if (type == Long.class) {
							filtersObject = new Long(filtersObject.toString());
							String currentAlias = alias += "x";

							paramValues.put(currentAlias, filtersObject);
							alias = currentAlias;
							queryString += " and o." + filtersKey + " = :"
									+ currentAlias;
						}
						else if (type == Integer.class) {
							filtersObject = new Integer(
									filtersObject.toString());
							String currentAlias = alias += "x";

							paramValues.put(currentAlias, filtersObject);
							alias = currentAlias;
							queryString += " and o." + filtersKey + " = :"
									+ currentAlias;
						}
						else if (type == Float.class) {
							filtersObject = new Float(filtersObject.toString());

							String currentAlias = alias += "x";

							paramValues.put(currentAlias, filtersObject);
							alias = currentAlias;
							queryString += " and o." + filtersKey + " = :"
									+ currentAlias;
						}
						else if (type == Double.class) {
							filtersObject = new Double(filtersObject.toString());
							String currentAlias = alias += "x";

							paramValues.put(currentAlias, filtersObject);
							alias = currentAlias;
							queryString += " and o." + filtersKey + " = :"
									+ currentAlias;
						}
						else if (type == Short.class) {
							filtersObject = new Short(filtersObject.toString());
							String currentAlias = alias += "x";

							paramValues.put(currentAlias, filtersObject);
							alias = currentAlias;
							queryString += " and o." + filtersKey + " = :"
									+ currentAlias;
						}

					
					}	else {
						//System.out.println("in else of filters "+ type.getCanonicalName());
								
						//System.out.println("in else2 of filters "+ filtersObject.getClass());
								
						String currentAlias = alias += "x";
						queryString += " and o." + filtersKey + " = :"
								+ currentAlias;
						paramValues.put(currentAlias, filtersObject);
						alias = currentAlias;

					}

				} catch (NoSuchFieldException e) {

					e.printStackTrace();
				} catch (SecurityException e) {

					e.printStackTrace();
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}
		}
		Query query = getCurrentSession().createQuery(queryString);
		String[] parameters = query.getNamedParameters();
		if (parameters.length != 0) {
			for (Map.Entry<String, Object> entry : paramValues.entrySet())
				if(entry.getValue() instanceof String){
				query.setParameter(entry.getKey(), "%"+entry.getValue()+"%");
				}else
				{
					
					query.setParameter(entry.getKey(), entry.getValue());
	
				}
			

		}
		return query;
	}

}
