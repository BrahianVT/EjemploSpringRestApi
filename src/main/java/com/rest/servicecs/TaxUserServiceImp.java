package com.rest.servicecs;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.rest.dao.TaxUserDao;
import com.rest.model.TaxUser;
@Service
@Transactional
public class TaxUserServiceImp implements TaxUserService{

	@Autowired 
	private TaxUserDao taxUserDao;
	
	public void setTaxUserDao(TaxUserDao taxUserDao){this.taxUserDao = taxUserDao;}
	
	@Override
	public List<TaxUser> getListUser() {
		
		return taxUserDao.getListUser();
	}

	@Override
	public void saveOrUpdate(TaxUser taxuser) {
		taxUserDao.saveOrUpdate(taxuser);
		
	}

	@Override
	public void deleteUser(int id) {
		taxUserDao.deleteUser(id);
		
	}

	@Override
	public TaxUser findUserbyId(int id) {
		
		return taxUserDao.findUserbyId(id);
	}

	@Override
	public int findId(String username, String contra) {
		return taxUserDao.findId(username, contra);
	}

}
