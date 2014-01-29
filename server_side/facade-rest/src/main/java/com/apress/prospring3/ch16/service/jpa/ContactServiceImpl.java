/**
 * Created on Nov 25, 2011
 */
package com.apress.prospring3.ch16.service.jpa;

import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.apress.prospring3.ch16.domain.Contact;
import com.apress.prospring3.ch16.service.ContactService;
import com.google.common.collect.Lists;

/**
 * @author Clarence
 *
 */
@Service("contactService")
@Repository
@Transactional
public class ContactServiceImpl implements ContactService {

		
	@Transactional(readOnly=true)
	public List<Contact> findAll() {
		return Lists.newArrayList();
	}

	@Transactional(readOnly=true)
	public List<Contact> findByFirstName(String firstName) {
		return Lists.newArrayList();
		
	}

	@Transactional(readOnly=true)	
	public Contact findById(Long id) {
		return new Contact();
	}

	public Contact save(Contact contact) {
		return new Contact();
	}

	public void delete(Contact contact) {
		
	}

}
