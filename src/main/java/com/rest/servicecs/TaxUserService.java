package com.rest.servicecs;

import java.util.List;

import com.rest.model.TaxUser;

	public interface TaxUserService {
	public List<TaxUser> getListUser();
	
	public void saveOrUpdate(TaxUser taxuser);
	
	public void deleteUser(int id);
	
	public TaxUser findUserbyId(int id);
	
	public int findId(String username, String contra);
}
