package ru.cti.tve.rest.serviceutil;

import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ru.cti.tve.rest.exception.IdGenerateException;

@Service
public class IdGenerator {
	
	@javax.persistence.PersistenceContext(unitName = "iptvmw")
    private javax.persistence.EntityManager emanager;
	
		
	
	
	@Transactional(readOnly=true)
	public boolean isAlreadyExists(String extId, @SuppressWarnings("rawtypes") Class entity){
		int count = Integer.valueOf(emanager.createQuery("SELECT count(e) FROM " + entity.getSimpleName() + " e WHERE e.externalId =:id").setParameter("id", extId).getSingleResult().toString());
		return count > 0;
	}	
	
	@Transactional(readOnly=true)
	public String getNextId(@SuppressWarnings("rawtypes") Class entity) throws IdGenerateException{
		
		// генерируем значение
		String idString = String.valueOf(UUID.randomUUID().getMostSignificantBits());
		
		// начинаем с 2-х символов
		for(int i = 2; i < idString.length(); i++){
			String id = idString.substring(0, i);
			// проверяем:
			if(!isAlreadyExists(id , entity)){
				return id;
			}
		}
		
		// id так и не нашли
		throw new IdGenerateException("id" , "Не удалось сгенерировать уникальный идентификатор для объекта.");
	}
	
	
}
